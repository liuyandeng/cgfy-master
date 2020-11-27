package com.cgfy.gateway.filter;

import com.cgfy.gateway.bean.SysLogonLogInfoInsertInputBean;
import com.cgfy.gateway.feign.UserFeignClient;
import com.cgfy.gateway.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;


@Component
@Slf4j
public class LoginGatewayFilterFactory extends AbstractGatewayFilterFactory<LoginGatewayFilterFactory.Config> {

	private final static String REQUEST_DATA_MAP_KEY = "LoginGatewayFilterFactory.request_data_map_key";

	@Autowired
	private UserFeignClient userFeign;

	public LoginGatewayFilterFactory() {
		super(Config.class);
		log.info("Loaded GatewayFilterFactory [Authorize]");
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("enabled");
	}

	@Override
	public GatewayFilter apply(Config config) {
		return new GatewayFilter() {
			@Override
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				if (!config.isEnabled()) {
					return chain.filter(exchange);
				}
				
				ServerHttpRequest request = exchange.getRequest();
				String requestURI = request.getURI().getPath();
				if(!requestURI.startsWith("/oauth/token")) {
					return chain.filter(exchange);
				}
				
				String ip = HttpUtil.getIpAddr(request);
				String browserType = HttpUtil.getBrowserType(request);
				String username = "";
				String clientId = "";
				String grantType = "";
				
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("ip", ip);
				paramMap.put("browserType", browserType);
				
				MultiValueMap<String, String> queryParams = request.getQueryParams();
				if(queryParams!=null) {
					username = queryParams.getFirst("username");
					clientId = queryParams.getFirst("client_id");
					grantType = queryParams.getFirst("grant_type");
					
					paramMap.put("username", username);
					paramMap.put("client_id", clientId);
					paramMap.put("grant_type", grantType);
				}
				
				// 未从路径上获取到参数时，从requesBody中获取
				if(StringUtils.isEmpty(username)) {
					
					ServerHttpResponseDecorator response = new ServerHttpResponseDecorator(exchange.getResponse());
					ServerWebExchange newEx = exchange.mutate()
			                .request(new RecorderServerHttpRequestDecorator(request))
			                .response(response)
			                .build();
					
					newEx.getAttributes().put(REQUEST_DATA_MAP_KEY, paramMap);
					
					return recorderOriginalRequest(newEx).then(Mono.defer(new Supplier<Mono<Void>>() {

						@Override
						public Mono<Void> get() {
//							Map<String, String> paramMap = newEx.getAttribute(REQUEST_DATA_MAP_KEY);
//							System.out.println("****************"+System.currentTimeMillis()+"*****************");
//							for(String key : paramMap.keySet()) {
//								System.out.println(key+"="+paramMap.get(key));
//							}
//							
//							String username = paramMap.get("username");
//							if(StringUtils.isEmpty(username)) {
//								response.setStatusCode(HttpStatus.UNAUTHORIZED);
//						        byte[] bytes = "达到撒大所".getBytes(StandardCharsets.UTF_8);
//						        DataBuffer buffer = response.bufferFactory().wrap(bytes);
//						        return response.writeWith(Mono.just(buffer));
//							}
							return Mono.empty();
						}
						
					})).then(chain.filter(newEx)).then(Mono.defer(() -> recorderResponse(newEx)));
					
				}else {
					exchange.getAttributes().put(REQUEST_DATA_MAP_KEY, paramMap);
					return chain.filter(exchange).then(Mono.defer(() -> recorderResponse(exchange)));
				}
			}
		};
	}
	
	private Mono<Void> recorderOriginalRequest(ServerWebExchange exchange) {
		ServerHttpRequest newRequest = exchange.getRequest();
		HttpHeaders headers = newRequest.getHeaders();
		MediaType contentType = headers.getContentType();
		
		Map<String, String> paramMap = exchange.getAttribute(REQUEST_DATA_MAP_KEY);
		
		Charset charset = null;
		
		if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)) {
			long length = headers.getContentLength();
			if(length>0) {
				charset = contentType.getCharset();
				if(charset==null) {
					charset = StandardCharsets.UTF_8;
				}
			}
		}
		
		if(charset!=null) {
			Flux<DataBuffer> body = newRequest.getBody();
			return doRecordBody(paramMap, body, charset);
		}else {
			return Mono.empty();
		}
    }
	
	
    private Mono<Void> doRecordBody(Map<String, String> paramMap, Flux<DataBuffer> body, Charset charset) {
        return DataBufferUtils.join(body).doOnNext(buffer -> {
                CharBuffer charBuffer = charset.decode(buffer.asByteBuffer());
                if(charBuffer!=null) {
                	Map<String, String> bodyMap = null;
        			try {
        		        bodyMap = HttpUtil.parseParamString(charBuffer.toString());
        			} catch (Exception e) {
        				// TODO: handle exception
        			}
        			if(bodyMap!=null && !bodyMap.isEmpty()) {
        				for(String key : bodyMap.keySet()) {
        					paramMap.put(key, bodyMap.get(key));
        				}
        			}
                }
                DataBufferUtils.release(buffer);
        }).then();
    }
    
    private Mono<Void> recorderResponse(ServerWebExchange exchange) {
    	ServerHttpResponse response = exchange.getResponse();
    	Map<String, String> paramMap = exchange.getAttribute(REQUEST_DATA_MAP_KEY);
    	if(paramMap!=null) {
    		String username = paramMap.get("username");
    		String clientId = paramMap.get("client_id");
    		String grantType = paramMap.get("grant_type");
    		String ip = paramMap.get("ip");
			String browserType = paramMap.get("browserType");
			
			if(!StringUtils.isEmpty(username)) {
				HttpStatus status = response.getStatusCode();
    	        
    	        String logonFlag = "0";
    			String logonMsg = "";
    			if(HttpStatus.OK == status) {
    				logonFlag = "1";
    				logonMsg = "登陆成功！";
    			}else if(HttpStatus.BAD_REQUEST == status || HttpStatus.UNAUTHORIZED == status){
    				logonMsg = "用户名或密码错误！";
    			}else {
    				logonMsg = "登录失败，未知异常！";
    			}
    			/** 记录登陆日志 **/
    			String id = UUID.randomUUID().toString().replaceAll("-", "");
    			
				SysLogonLogInfoInsertInputBean input = new SysLogonLogInfoInsertInputBean();
				input.setId(id);
				input.setUsername(username);
				input.setClientId(clientId);
				input.setGrantType(grantType);
				input.setIp(ip);
				input.setBrowserType(browserType);
				input.setLogonTime(new Date());
				input.setLogonFlag(logonFlag);
				input.setLogonStatus(status.value() + "");
				input.setLogonMsg(logonMsg);
				
				try {
					//userFeign.saveLoginLog(input);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    	}
    	
		return Mono.empty();
    }
	
	public static class Config {
		// 控制是否开启认证
		private boolean enabled;

		public Config() {

		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}

}
