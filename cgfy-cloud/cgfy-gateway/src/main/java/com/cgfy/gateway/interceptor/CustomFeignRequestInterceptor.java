package com.cgfy.gateway.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 请求拦截器
 * @CopyRright (c)2018-xxxx: <DHC-未来出行事业部 >
 * @JdkVersion 1.8
 * @version <1.0>
 * @date 2018-05-10
 * @author qiucw
 */
@Configuration
public class CustomFeignRequestInterceptor implements RequestInterceptor {

	public static String AUTHORIZATION_HEADER = "Authorization";
	
	public static String CONTENT_TYPE_NAME = "Content-Type";

	@Override
	public void apply(RequestTemplate template) {
		
	}

}
