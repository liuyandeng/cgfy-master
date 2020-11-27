package com.cgfy.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 共同属性获取类
 * 
 */
@Component
@ConfigurationProperties(prefix="cgfy.gateway.ip-check")
public class GatewayIpCheckProperties{

	// 是否启用登录IP校验
	private Boolean enable = false;
	
	// 允许登录的IP段列表 示例：192.168.1.1-192.168.1.254
	private List<String> allowIPRange;
	
	// 不进行IP 校验的url前缀
	private List<String> urlPrefixIgnore;

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<String> getAllowIPRange() {
		return allowIPRange;
	}

	public void setAllowIPRange(List<String> allowIPRange) {
		this.allowIPRange = allowIPRange;
	}

	public List<String> getUrlPrefixIgnore() {
		if(urlPrefixIgnore==null) {
			return new ArrayList<String>();
		}
		return urlPrefixIgnore;
	}

	public void setUrlPrefixIgnore(List<String> urlPrefixIgnore) {
		this.urlPrefixIgnore = urlPrefixIgnore;
	}
	
	
}