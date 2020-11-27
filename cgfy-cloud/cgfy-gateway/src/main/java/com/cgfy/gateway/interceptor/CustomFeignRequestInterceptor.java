package com.cgfy.gateway.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 请求拦截器
 */
@Configuration
public class CustomFeignRequestInterceptor implements RequestInterceptor {

	public static String AUTHORIZATION_HEADER = "Authorization";
	
	public static String CONTENT_TYPE_NAME = "Content-Type";

	@Override
	public void apply(RequestTemplate template) {
		
	}

}
