package com.cgfy.oauth.base.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * Feign 请求拦截器
 * 解决服务之间调用传递token问题
 * https://blog.csdn.net/yueloveme/article/details/93124950
 */
@Configuration
public class CustomFeignRequestInterceptor implements RequestInterceptor {

	public static String AUTHORIZATION_HEADER = "authorization";
	
	public static String CONTENT_TYPE_NAME = "Content-Type";

	@Override
	public void apply(RequestTemplate template) {

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			final HttpServletRequest request = attributes.getRequest();
			final Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					final String name = headerNames.nextElement();
					final String values = request.getHeader(name);
					if(!CONTENT_TYPE_NAME.equalsIgnoreCase(name)) {
						template.header(name, values);
					}
				}
			}
		}

		this.addJwtAuthHeader(template);
	}

	/**
	 * 添加线程中认证信息
	 * 
	 * @param requestTemplate
	 *            请求模板
	 * @author liuyandeng
	 */
	private void addJwtAuthHeader(final RequestTemplate requestTemplate) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth = (OAuth2Authentication) authentication;
			Object detailsObj = auth.getDetails();
			if (detailsObj instanceof OAuth2AuthenticationDetails) {
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) detailsObj;
				String tokenType = Objects.toString(details.getTokenType(), "");
				String tokenValue = Objects.toString(details.getTokenValue(), "");
				requestTemplate.header(AUTHORIZATION_HEADER, tokenType + " " + tokenValue);
			}
		}
	}

}
