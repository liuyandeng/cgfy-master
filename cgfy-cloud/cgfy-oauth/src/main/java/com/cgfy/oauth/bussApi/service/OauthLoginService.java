package com.cgfy.oauth.bussApi.service;


import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.bussApi.domain.model.UserInfo;

/**
 * 登录
 *
 */
public interface OauthLoginService{

	/**
	 * 发送短信验证码
	 * @param phoneNumber
	 * @return
	 */
	public AjaxResponse<Object> sendSmsCode(String phoneNumber) ;
	
	/**
	 * 根据电话号码获取服务器存储的验证码
	 * @param phoneNumber
	 * @return
	 */
	public String getSmsCode(String phoneNumber) ;
	
	/***
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public UserInfo loadUserByUsername(String username);
	
	/***
	 * 根据手机号查找用户
	 * @param mobilePhone
	 * @return
	 */
	public UserInfo loadUserByPhoneNumber(String mobilePhone);

}
