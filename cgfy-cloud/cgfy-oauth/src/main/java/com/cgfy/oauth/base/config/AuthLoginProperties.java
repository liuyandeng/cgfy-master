package com.cgfy.oauth.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 共同属性获取类
 * 
 */
@Component
@ConfigurationProperties(prefix="cgfy.auth.login")
public class AuthLoginProperties{
	
	// 二维码扫描登录URl
	private String qrLoginUrl;

	public String getQrLoginUrl() {
		return qrLoginUrl;
	}

	public void setQrLoginUrl(String qrLoginUrl) {
		this.qrLoginUrl = qrLoginUrl;
	}
	
}