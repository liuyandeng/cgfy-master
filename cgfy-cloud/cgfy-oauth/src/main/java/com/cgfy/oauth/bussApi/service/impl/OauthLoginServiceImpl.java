package com.cgfy.oauth.bussApi.service.impl;

import com.cgfy.oauth.base.bean.AjaxResponse;

import com.cgfy.oauth.bussApi.bean.SendSmsInputBean;
import com.cgfy.oauth.bussApi.bean.SendSmsOutputBean;
import com.cgfy.oauth.bussApi.domain.mapper.UserInfoMapper;
import com.cgfy.oauth.bussApi.domain.model.UserInfo;
import com.cgfy.oauth.bussApi.service.OauthLoginService;
import com.cgfy.oauth.bussApi.feign.UserFeignClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 登录
 * 
 */
@Service
public class OauthLoginServiceImpl implements OauthLoginService {
	
	public static String SMS_LONG_CODE_REDIS_KEY_NAMESPACE = "oauth_login_sms_code";
	
	public static String SMS_LONG_CODE_COUNT_REDIS_KEY_NAMESPACE = "oauth_login_sms_code_count";
	
	/**
     * Mapper
     */
    @Autowired
    private UserInfoMapper mapper;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	
	/**
	 * 发送短信验证码
	 * @param phoneNumber
	 * @return
	 */
	public AjaxResponse<Object> sendSmsCode(String phoneNumber) {
		boolean isSuccess = true;
		String msg = "";
		
		/** 判断系统是是否存在此手机号的用户信息 **/ 
		UserInfo sysUserInfo = this.loadUserByPhoneNumber(phoneNumber);
		if(sysUserInfo==null) {
			isSuccess = false;
			msg = "获取验证码失败，未能根据电话号码查找到系统用户！";
		}
		
		String redisKey = getSmsCodeKey(phoneNumber, 1);
		String redisKeyCodeCount = getSmsCodeKey(phoneNumber, 2);
		
		/** 判断是否在1分钟内已经发送过验证码 **/
		if(isSuccess) {
			if(redisTemplate.hasKey(redisKey)) {
				isSuccess = false;
				msg = "获取验证码失败，请在1分钟后重试！";
			}
		}
		
		/** 判断是否在1天内，发送验证码的此时已经达到上限 **/
		if(isSuccess) {
			if(redisTemplate.hasKey(redisKeyCodeCount)) {
				BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(redisKeyCodeCount);
				String sendCountStr = Objects.toString(boundValueOps.get());
				long sendCount = -1;
				
				try {
					sendCount = Long.parseLong(sendCountStr);
				} catch (Exception e) {
					// TODO: handle exception
				}
				int defaulCount = 10;
				if(sendCount>=defaulCount) {
					isSuccess = false;
					msg = "获取验证码失败，登录验证码在1天内，只能发送" + defaulCount + "次！";
				}
			}
		}
		
		/** 进行验证码信息发送 **/
		if(isSuccess) {
			// 生成验证码
			String smsCode = RandomStringUtils.randomNumeric(6);
			
			String smsText = "手机号" + phoneNumber + "登录的验证码是：" + smsCode + "。为保障纤细安全，请勿告诉他人。";
			List<String> phoneNumbers = new ArrayList<String>();
			phoneNumbers.add(phoneNumber);
			
			SendSmsInputBean input = new SendSmsInputBean();
			input.setPhoneNumbers(phoneNumbers);
			input.setSmsText(smsText);
			
			SendSmsOutputBean out = null;
			
			try {
				//调用发送短信服务
				//AjaxResponse<List<SendSmsOutputBean>> sendSms = userFeignClient.sendSms(input);
				AjaxResponse<List<SendSmsOutputBean>> sendSms =null;
				if(sendSms!=null) {
					List<SendSmsOutputBean> data = sendSms.getData();
					if(data!=null && !data.isEmpty()) {
						out = data.get(0);
					}
				}
			} catch (Exception e) {
			}
			
			if(out==null) {
				isSuccess = false;
				msg = "获取验证码失败，服务器进行短信发送异常！";
			}else {
				String sendStatusCode = Objects.toString(out.getSendStatusCode(),"");
				String sendStatusMsg = out.getSendStatusMsg();
				if(sendStatusCode.startsWith("-")) {
					isSuccess = false;
					msg = "获取验证码失败，" + sendStatusMsg;
				}else {
					// 记录发送的验证码
					redisTemplate.boundValueOps(redisKey).set(smsCode, 1, TimeUnit.MINUTES);
					
					// 记录验证码发送次数
					if(redisTemplate.hasKey(redisKeyCodeCount)) {
						redisTemplate.boundValueOps(redisKeyCodeCount).increment(1L);
					}else {
						BoundValueOperations<String, Object> codeCountValueOps = redisTemplate.boundValueOps(redisKeyCodeCount);
						codeCountValueOps.increment(1L);
						codeCountValueOps.expireAt(getExpireAt());
					}
				}
			}
		}
		
		AjaxResponse<Object> res = null;
		if(isSuccess) {
			res = AjaxResponse.success();
		}else {
			res = AjaxResponse.fail(HttpStatus.BAD_REQUEST.value(), msg);
		}
		
		return res;
	}
	
	/**
	 * 根据电话号码获取服务器存储的验证码
	 * @param phoneNumber
	 * @return
	 */
	public String getSmsCode(String phoneNumber) {
		String smsCode = null;
		String redisKey = getSmsCodeKey(phoneNumber, 1);
		if(redisTemplate.hasKey(redisKey)) {
			smsCode = Objects.toString(redisTemplate.opsForValue().get(redisKey),"");
		}
		return smsCode;
	}
	
	/***
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public UserInfo loadUserByUsername(String username){
		UserInfo record = new UserInfo();
		record.setLoginName(username);
		return mapper.selectOne(record);
	}
	
	/***
	 * 根据手机号查找用户
	 * @param mobilePhone
	 * @return
	 */
	public UserInfo loadUserByPhoneNumber(String mobilePhone){
		UserInfo record = new UserInfo();
		record.setMobilePhone(mobilePhone);
		return mapper.selectOne(record);
	}
	
	/**
	 * 获取redis存储的key
	 * @param phoneNumber
	 * @param type
	 * @return
	 */
	private String getSmsCodeKey(String phoneNumber, int type) {
		if(type==2) {
			return SMS_LONG_CODE_COUNT_REDIS_KEY_NAMESPACE + ":" + phoneNumber;
		}
		return SMS_LONG_CODE_REDIS_KEY_NAMESPACE + ":" + phoneNumber;
	}
	
	/***
	 * 获取过期时间点
	 * @return
	 */
	private static Date getExpireAt() {
		
		/** 设置过期时间点为当前日期的 23:59:59 **/
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 0, 0, 0);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.SECOND, -1);
		
		return calendar.getTime();
	}
	
}
