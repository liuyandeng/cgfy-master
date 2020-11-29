package com.cgfy.oauth.bussApi.controller;

import com.cgfy.oauth.base.bean.AjaxResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@Api(tags = "身份认证扩展", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/oauth/ext")
public class OauthController {

	/**
	 * 密码生成器
	 */
	@Resource
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    @Qualifier("consumerTokenServices")
	private ConsumerTokenServices tokenServices;
    

    @ApiOperation(value = "用户注销")
    @RequestMapping(value = "/revokeToken", method=RequestMethod.POST)
    public AjaxResponse<Object> revokeToken(
    		@ApiParam(name = "token", value = "用户 access_token", required = true) @RequestParam(value="token") String token) {
    	AjaxResponse<Object> aRes = new AjaxResponse<Object>();
    	if (tokenServices.revokeToken(token)){
    		aRes.setSuccess(true);
    		aRes.setMessage("注销成功！");
    		aRes.setStatusCode(HttpStatus.OK.value());
        }else{
        	aRes.setSuccess(true);
    		aRes.setMessage("注销失败！");
    		aRes.setStatusCode(HttpStatus.BAD_REQUEST.value());
        }
		return aRes;
    }
    
    /**
	 * 生成密码
	 * 
	 * @param password 密码（明文）
	 * @return 密码（密文）
	 */
    @ApiOperation(value = "原始密码加密")
	@RequestMapping(value = "/encodedPassword", method=RequestMethod.POST)
	public String encodedPassword(
			@ApiParam(name = "password", value = "原始密码") @RequestParam("password") String password) {
    	String encodedPassword=passwordEncoder.encode(password);
		System.out.println("加密密码:"+encodedPassword);
        return encodedPassword;
    }
	
	/**
	 * 验证密码
	 * 
	 * @param rawPassword 原始密码
	 * @param encodedPassword 密码（密文）
	 * @return 成功/失败
	 */
    @ApiOperation(value = "密码校验")
	@RequestMapping(value = "/checkPassword", method=RequestMethod.POST)
	public boolean checkPassword(
			@ApiParam(name = "rawPassword", value = "原始密码") @RequestParam("rawPassword") String rawPassword,
			@ApiParam(name = "encodedPassword", value = "加密后密码") @RequestParam("encodedPassword") String encodedPassword) {
        boolean isMache = false;
        try {
        	isMache = passwordEncoder.matches(rawPassword, encodedPassword);
		} catch (Exception e) {
			
		}
    	
    	return isMache;
    }
    
    /**
	 * 取得当前用户
	 * /oauth/ext/getCurrentUser?access_token=token
	 * @param user 用户信息
	 * @return 用户信息
	 */
    @ApiOperation(value = "获取用户详细")
	@RequestMapping(value = "/getCurrentUser")
	public Principal user(Principal user) {
        return user;
    }
	
    /**
	 * 取得当前用户
	 * 
	 * @param userToken 用户信息
	 * @return 用户信息
	 */
	@ApiOperation(value = "获取 Token 详细")
	@RequestMapping(value = "/readAccessToken", method=RequestMethod.POST)
	public OAuth2AccessToken readAccessToken(
			@ApiParam(name = "userToken", value = "用户凭据access_token", required=true) @RequestParam(value="userToken", required=true) String userToken) {
		OAuth2AccessToken accessToken = null;
		
		if(tokenServices instanceof ResourceServerTokenServices) {
			accessToken = ((ResourceServerTokenServices) tokenServices).readAccessToken(userToken);
		}
		
		
		return accessToken;
    }
	
}