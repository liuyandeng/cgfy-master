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
@ConfigurationProperties(prefix="jbdp.gateway.log")
public class GatewayLogProperties{

	// 不进行操作日志记录的前缀
	private List<String> optLogIgnore;

	public List<String> getOptLogIgnore() {
		if(optLogIgnore==null) {
			optLogIgnore = new ArrayList<String>();
		}
		return optLogIgnore;
	}

	public void setOptLogIgnore(List<String> optLogIgnore) {
		this.optLogIgnore = optLogIgnore;
	}

	
}