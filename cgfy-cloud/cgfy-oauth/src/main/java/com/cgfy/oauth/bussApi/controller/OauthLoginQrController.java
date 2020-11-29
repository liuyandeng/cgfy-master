package com.cgfy.oauth.bussApi.controller;

import com.cgfy.oauth.base.config.AuthLoginProperties;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.base.util.JsonUtil;
import com.cgfy.oauth.bussApi.bean.QrInfoOutputBean;
import com.cgfy.oauth.bussApi.bean.QrLoginInfoOutputBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 二维码扫描登录
 */
@SuppressWarnings({"unchecked","rawtypes"})
@Api(tags = "二维码扫描登录", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("OauthLoginQrController")
@RequestMapping("/oauth/login/qr")
public class OauthLoginQrController {
	
	private static final String CHARACTER_SET = "UTF-8";
	private static final int QR_WIDTH = 300;
	private static final int QR_HEIGHT = 300;
	
	private static String QR_REDIS_KEY_PREFIX = "_rq_login_uuid_";
	
	@Autowired
	private AuthLoginProperties authLoginProperties;
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate redisTemplate;
	
	@Autowired
	private TokenStore tokenStore;
	
	/**
	 * 获取二维码
	 * @throws Exception 
	 */
	@ApiOperation(value = "获取二维码")
	@RequestMapping(value = "/getQrCode", method = RequestMethod.POST)
	public AjaxResponse<QrInfoOutputBean> getQrCode(){
		boolean success = true;
		int statusCode = HttpStatus.OK.value();
		String msg = "";
		
		String uuid = RandomStringUtils.randomAlphanumeric(32);
		String loginUrl = authLoginProperties.getQrLoginUrl();
		String qrCode = "";
		
		Map<String, String> qrData = new HashMap<String, String>();
		qrData.put("uuid", uuid);
		qrData.put("loginUrl", loginUrl);
		
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARACTER_SET);
		hints.put(EncodeHintType.MARGIN, 1);

		try {
			/** 生成二维码 **/
			String content = JsonUtil.toJson(qrData);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			
			/** 转换为图片 **/
			int width = bitMatrix.getWidth();
	        int height = bitMatrix.getHeight();
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
	            }
	        }
	        
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        ImageIO.write(image, "PNG", out);
	        
	        /** 生成二位码文件字符串 **/
	        qrCode = Base64.encodeBase64String(out.toByteArray());
	        
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			statusCode = HttpStatus.BAD_REQUEST.value();
			msg = "生成二维码异常！"+e.getMessage();
		}
		
		if(success) {
			try {
				QrLoginInfoOutputBean loginInfo = new QrLoginInfoOutputBean();
				loginInfo.setUuid(uuid);
				loginInfo.setStatus("0");
				loginInfo.setMsg("初始化二维码完成！");
				String key  = QR_REDIS_KEY_PREFIX + uuid;
				
				redisTemplate.boundValueOps(key).set(loginInfo, 5, TimeUnit.MINUTES);// 将二维码登录信息放到缓存中
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				statusCode = HttpStatus.BAD_REQUEST.value();
				msg = "二维码放入缓存中异常！"+e.getMessage();
			}
		}
		AjaxResponse<QrInfoOutputBean> aRes = new AjaxResponse<QrInfoOutputBean>();
		aRes.setSuccess(success);
		aRes.setMessage(msg);
		aRes.setStatusCode(statusCode);
		
		if(success) {
			QrInfoOutputBean qrInfoOutputBean = new QrInfoOutputBean();
			qrInfoOutputBean.setUuid(uuid);
			qrInfoOutputBean.setLogin_url(loginUrl);
			qrInfoOutputBean.setImage(qrCode);
			aRes.setData(qrInfoOutputBean);
		}
		
		return aRes;
	}
	
	/**
	 * 二维码扫码登录
	 * @throws Exception 
	 */
	@ApiOperation(value = "二维码扫码登录")
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public AjaxResponse<String> doLogin(
			@ApiParam(name = "uuid", value = "扫码唯一性标识", required = true) @RequestParam(value="uuid", required=true) String uuid, 
			@ApiParam(name = "userToken", value = "用户验证凭据", required = true) @RequestParam(value="userToken", required=true) String userToken){
		boolean success = true;
		int statusCode = HttpStatus.OK.value();
		String msg = "";
		String key  = QR_REDIS_KEY_PREFIX + uuid;
		QrLoginInfoOutputBean qrLoginInfoOutputBean = null;
		try {
			// 获取缓存中的二维码登录信息
			BoundValueOperations<String, QrLoginInfoOutputBean> boundValueOps = redisTemplate.boundValueOps(key);
			qrLoginInfoOutputBean = boundValueOps.get();
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			statusCode = HttpStatus.BAD_REQUEST.value();
			msg = "获取缓存中的二维码登录信息异常！"+e.getMessage();
		} 
		
		if(success) {
			if(qrLoginInfoOutputBean==null) {
				success = false;
				statusCode = HttpStatus.BAD_REQUEST.value();
				msg = "二维码已过期，请刷新二维码页面后扫描登录！";
			}
		}
		
		if(success) {
			// 校验access_token
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(userToken);
			if(accessToken!=null && !accessToken.isExpired()) {
				msg = "扫描登录成功！";
				qrLoginInfoOutputBean.setStatus("2");// 
				qrLoginInfoOutputBean.setMsg("扫描登录成功！");
				qrLoginInfoOutputBean.setToken_info(accessToken);
			}else {
				success = false;
				statusCode = HttpStatus.BAD_REQUEST.value();
				msg = "用户凭据已过期！";
				qrLoginInfoOutputBean.setStatus("1");
				qrLoginInfoOutputBean.setMsg("已扫描登录，但凭据无效！");
			}
			
			try {
				
				// 获取缓存中的二维码登录信息
				redisTemplate.boundValueOps(key).set(qrLoginInfoOutputBean, 5, TimeUnit.MINUTES); // 将二维码登录信息放到缓存中
			} catch (Exception e) {
				e.printStackTrace();
				success = false;
				statusCode = HttpStatus.BAD_REQUEST.value();
				msg = "二维码放入缓存中异常！"+e.getMessage();
			}
		}
		
		AjaxResponse<String> aRes = new AjaxResponse<String>();
		aRes.setSuccess(success);
		aRes.setMessage(msg);
		aRes.setStatusCode(statusCode);
		
		return aRes;
	}
	
	/**
	 * 获取二维码扫码登录信息
	 * @throws Exception 
	 */
	@ApiOperation(value = "获取二维码扫码登录信息")
	@RequestMapping(value = "/getQrLoginInfo", method = RequestMethod.POST)
	public AjaxResponse<QrLoginInfoOutputBean> getQrLoginInfo(
			@ApiParam(name = "uuid", value = "扫码唯一性标识", required = true) @RequestParam(value="uuid", required=true) String uuid){
		boolean success = true;
		int statusCode = HttpStatus.OK.value();
		String msg = "";
		String key  = QR_REDIS_KEY_PREFIX + uuid;
		QrLoginInfoOutputBean qrLoginInfoOutputBean = null;
		try {
			// 获取缓存中的二维码登录信息
			BoundValueOperations<String, QrLoginInfoOutputBean> boundValueOps = redisTemplate.boundValueOps(key);
			qrLoginInfoOutputBean = boundValueOps.get();
			
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
			statusCode = HttpStatus.BAD_REQUEST.value();
			msg = "获取缓存中的二维码登录信息异常！"+e.getMessage();
		} 
		
		if(success) {
			if(qrLoginInfoOutputBean==null) {
				success = false;
				statusCode = HttpStatus.BAD_REQUEST.value();
				msg = "二维码已过期，请刷新二维码页面后扫描登录！";
			}
		}
		
		AjaxResponse<QrLoginInfoOutputBean> aRes = new AjaxResponse<QrLoginInfoOutputBean>();
		aRes.setSuccess(success);
		aRes.setMessage(msg);
		aRes.setStatusCode(statusCode);
		aRes.setData(qrLoginInfoOutputBean);
		return aRes;
	}
	
}
