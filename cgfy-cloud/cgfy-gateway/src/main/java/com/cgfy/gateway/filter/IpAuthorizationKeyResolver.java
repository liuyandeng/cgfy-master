package com.cgfy.gateway.filter;

import com.cgfy.gateway.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;
import java.util.function.Function;

@Component
public class IpAuthorizationKeyResolver implements KeyResolver {
	
	private final static String REQUEST_RATELIMITER_PREFIX = "RequestRateLimiter:";
	
	public static String AUTHORIZATION_HEADER = "Authorization";

	@Override
	public Mono<String> resolve(ServerWebExchange exchange) {
		String key = REQUEST_RATELIMITER_PREFIX;
		ServerHttpRequest request = exchange.getRequest();
		HttpHeaders headers = request.getHeaders();
		URI uri = request.getURI();
		String ip = HttpUtil.getIpAddr(request);
		String auth = headers.getFirst(AUTHORIZATION_HEADER);
		if(StringUtils.isEmpty(auth)) {
			auth = "NoAuthorization";
		}
		
		Mono<String> temp = exchange.getPrincipal().map(new Function<Principal, String>() {

			@Override
			public String apply(Principal t) {
				// TODO Auto-generated method stub
				return t.getName();
			}
			
		});
		
		key += ip + ";" + auth;
		return Mono.just(key);
	}

}
