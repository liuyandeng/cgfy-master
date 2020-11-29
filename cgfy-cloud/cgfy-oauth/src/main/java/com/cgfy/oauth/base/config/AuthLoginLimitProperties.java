package com.cgfy.oauth.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 共同属性获取类
 * 
 */
@Component
@ConfigurationProperties(prefix="cgfy.auth.login-limit")
public class AuthLoginLimitProperties{
	
	public static String LOGIN_FAIL_COUNT_REDIS_KEY_NAMESPACE = "LONIN_FAIL_COUNT";

	// 是否启用登录次数限制
	private Boolean enable = false;
	
	// 单位时间内允许失败次数
	private Integer numberOfAllowableFail = 5;
	
	// 单位时间，单位分钟
	private Integer refreshInterval = 24*60;
	
	// 登录限制时间，单位分钟, 如果不设置，将使用refreshInterval
	private Integer limitTime;
	
	// 登录失败次数Redis key 命名空间
	private String redisKeyNamespace;

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getNumberOfAllowableFail() {
		return numberOfAllowableFail;
	}

	public void setNumberOfAllowableFail(Integer numberOfAllowableFail) {
		if(numberOfAllowableFail!=null && numberOfAllowableFail<=0) {
			throw new RuntimeException("[numberOfAllowableFail] must greater than 0 ");
		}
		this.numberOfAllowableFail = numberOfAllowableFail;
	}

	public Integer getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(Integer refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	public Integer getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}

	public String getRedisKeyNamespace() {
		String namespace = redisKeyNamespace;
		if(namespace==null || namespace.isEmpty()) {
			namespace = LOGIN_FAIL_COUNT_REDIS_KEY_NAMESPACE;
		}
		
		if(!namespace.endsWith(":")) {
			namespace += ":" ;
		}
		
		return namespace;
	}

	public void setRedisKeyNamespace(String redisKeyNamespace) {
		this.redisKeyNamespace = redisKeyNamespace;
	}
	
	
	
}