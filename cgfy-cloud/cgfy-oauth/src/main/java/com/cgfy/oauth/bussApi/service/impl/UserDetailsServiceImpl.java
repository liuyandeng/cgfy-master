package com.cgfy.oauth.bussApi.service.impl;


import com.cgfy.oauth.bussApi.bean.UserDetailsEx;
import com.cgfy.oauth.base.interceptor.CustomRequestContext;
import com.cgfy.oauth.bussApi.domain.mapper.UserInfoMapper;
import com.cgfy.oauth.bussApi.domain.model.UserInfo;
import com.cgfy.oauth.bussApi.service.OauthLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	public static String AUTH_PASSWORD_PARAM_NAME = "password";
	
	public static String AUTH_TYPE_PARAM_NAME = "auth_type";
	
	public static String AUTH_TYPE_MOBILE = "mobile";

	public static String AUTH_TYPE_NOPASSWORD = "portalcheck";

	/**
     * Mapper
     */
    @Autowired
    private UserInfoMapper mapper;

    @Autowired
    private OauthLoginService oauthLoginService;
    
	/**
	 * 密码生成器
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
    
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		HttpServletRequest request = CustomRequestContext.getContext().getRequest();
		
		if(request!=null) {
			String authType = request.getParameter(AUTH_TYPE_PARAM_NAME);
			String password = request.getParameter(AUTH_PASSWORD_PARAM_NAME);
			
			// 手机短信验证
			if(AUTH_TYPE_MOBILE.equalsIgnoreCase(authType)) {
				
				UserInfo sysUserInfo = oauthLoginService.loadUserByPhoneNumber(username);
				
				if(sysUserInfo==null) {
					throw new InternalAuthenticationServiceException("未找到指定用户！");
				}
				
				String smsCode = oauthLoginService.getSmsCode(username);
				if(StringUtils.isEmpty(smsCode)) {
					throw new InternalAuthenticationServiceException("验证码已过期！");
				}
				
				if(!smsCode.equals(password)) {
					throw new InternalAuthenticationServiceException("验证码错误！");
				}
				
				sysUserInfo.setPassword(passwordEncoder.encode(smsCode));
				
				return new UserDetailsEx(sysUserInfo);
			}else if(AUTH_TYPE_NOPASSWORD.equalsIgnoreCase(authType)){
				UserInfo sysUserInfo = oauthLoginService.loadUserByUsername(username);

				if(sysUserInfo==null) {
					throw new InternalAuthenticationServiceException("未找到指定用户！");
				}

				sysUserInfo.setPassword(passwordEncoder.encode(username));

				return new UserDetailsEx(sysUserInfo);
			}
		}
		
		UserInfo sysUserInfo = oauthLoginService.loadUserByUsername(username);
		
		if(sysUserInfo==null) {
			throw new InternalAuthenticationServiceException("未找到指定用户！");
		}
		
		return new UserDetailsEx(sysUserInfo);
	}

}
