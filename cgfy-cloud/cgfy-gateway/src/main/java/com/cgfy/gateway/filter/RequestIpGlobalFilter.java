package com.cgfy.gateway.filter;


import com.cgfy.gateway.bean.SysExceptionLogInfoInsertInputBean;
import com.cgfy.gateway.bean.SysUserInfo;
import com.cgfy.gateway.config.GatewayIpCheckProperties;
//import com.cgfy.gateway.feign.UumsRemoteService;
import com.cgfy.gateway.feign.AuthFeignClient;
import com.cgfy.gateway.feign.UserFeignClient;
import com.cgfy.gateway.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Component
@Slf4j
public class RequestIpGlobalFilter implements GlobalFilter, Ordered {
	
	public static final String GATEWAY_PROXY_CLIENT_IP = "CGFY-GATEWAY-PROXY-CLIENT-IP";
	
	private final static String REQUEST_DATA_MAP_KEY = "RequestIpGlobalFilter.request_data_map_key";
	
	private final static String IP_CHECK_LOG_COUNT_REDIS_NAMESPACE = "RequestIpGlobalFilter.ip_check_log_count:";
	
	private boolean isInit = false;
	
	private Map<String, Map<String, Integer>> allowIpMap = null;
	
	@Autowired
	private AuthFeignClient authFeign;
	
	@Autowired
	private UserFeignClient userFeign;
	
	@Autowired
	private GatewayIpCheckProperties gatewayIpCheckProperties;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
 
    /* (non-Javadoc)
     * @see org.springframework.cloud.gateway.filter.GlobalFilter#filter(org.springframework.web.server.ServerWebExchange, org.springframework.cloud.gateway.filter.GatewayFilterChain)
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	
    	ServerHttpRequest request = exchange.getRequest();
		String requestURI = request.getURI().getPath();
		HttpHeaders headers = request.getHeaders();
		String ip = HttpUtil.getIpAddr(request);
		String clientType=HttpUtil.getClientType(headers);
		String tokenInfo=HttpUtil.getTokenInfo(headers);
		log.info("拦截请求URI:"+requestURI+",IP:"+ip+",客户端类型:"+clientType+",token:"+tokenInfo);
		Map<String, String> headerMap = new HashMap<>();
		for(String key : headers.keySet()) {
			headerMap.put(key, headers.getFirst(key));
		}
		
		boolean isFilter = gatewayIpCheckProperties.getEnable();
		
		// 判断请求是否需要IP校验
		if(isFilter) {
			if(requestURI.startsWith("/error")) {
				isFilter = false;
			}else {
				List<String> optLogIgnore = gatewayIpCheckProperties.getUrlPrefixIgnore();
				if (optLogIgnore != null && !optLogIgnore.isEmpty()) {
					for (String item : optLogIgnore) {
						if (requestURI.startsWith(item)) {
							isFilter = false;
							break;
						}
					}
				}
			}
		}
		
		if(isFilter) {
			boolean isAllowIp = false;
			if(StringUtils.isEmpty(ip) || "localhost".equalsIgnoreCase(ip)|| "127.0.0.1".equalsIgnoreCase(ip)) {
				isAllowIp = true;
			}else {
				// 获取允许访问的IP信息
				Map<String, Map<String, Integer>> ipMap =  getAllowIpMap();
				String start = ip.substring(0, ip.lastIndexOf("."));
				//获取该ip段的起始ip的最后一段
				String end = ip.substring(ip.lastIndexOf(".")+1);
				int lastLeg = 0;
				try {
					lastLeg = Integer.parseInt(end);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				Map<String, Integer> map = ipMap.get(start);
				if(map!=null && !map.isEmpty()) {
					Integer ipStart = map.get("start");
					Integer ipEnd = map.get("end");
					if(lastLeg >= ipStart && lastLeg<= ipEnd) {
						isAllowIp = true;
					}
				}
			}
			
			isFilter = !isAllowIp;
		}
		
		if(isFilter) {
			if(requestURI.startsWith("/oauth/token")) {
				// 登录IP 校验
				return loginFilter(exchange, chain, ip);
			}else {
				// 其他 IP 校验
				return otherFilter(exchange, chain, ip);
			}
		}
		
		ServerHttpRequest newRequest = exchange.getRequest().mutate()
				.header(GATEWAY_PROXY_CLIENT_IP, ip)
				.build();
		
    	return chain.filter(exchange.mutate().request(newRequest).build());
    }
    
    private Mono<Void> loginFilter(ServerWebExchange exchange, GatewayFilterChain chain, String ip) {
    	ServerHttpRequest request = exchange.getRequest();
    	String username = "";
		String clientId = "";
		String grantType = "";
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		MultiValueMap<String, String> queryParams = request.getQueryParams();
		if(queryParams!=null) {
			username = queryParams.getFirst("username");
			clientId = queryParams.getFirst("client_id");
			grantType = queryParams.getFirst("grant_type");
			
			paramMap.put("username", username);
			paramMap.put("clientId", clientId);
			paramMap.put("grantType", grantType);
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
					
					Map<String, String> paramMap = newEx.getAttribute(REQUEST_DATA_MAP_KEY);
					String username = paramMap.get("username");
		    		String clientId = paramMap.get("client_id");
		    		String grantType = paramMap.get("grant_type");
					
					String expType = "登录IP异常";
					String expMsg = "用户【" + username + "】在客户端【" + clientId + "】 验证类型 【" + grantType + "】" 
							+ "登录IP【" + ip + "】未被允许！";
					String msg = "{\"error\":\"invalid_ip\",\"error_description\": \"请求失败，您的IP被限制访问！如有需要，请联系管理员。\"}";
					
			    	String id = UUID.randomUUID().toString().replaceAll("-", "");
					
					SysExceptionLogInfoInsertInputBean input = new SysExceptionLogInfoInsertInputBean();
					input.setId(id);
					input.setExpType(expType);
					input.setExpMsg(expMsg);
					input.setExpTime(new Date());
					input.setUserLoginName(username);
					input.setIp(ip);
					
					writeLog(input);
					
			    	ServerHttpResponse response = newEx.getResponse();
			    	response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
			    	response.setStatusCode(HttpStatus.BAD_REQUEST);
			        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
			        DataBuffer buffer = response.bufferFactory().wrap(bytes);
			        return response.writeWith(Mono.just(buffer));
				}
			}));
			
		}else {
			
			String expType = "登录IP异常";
			String expMsg = "用户【" + username + "】在客户端【" + clientId + "】 验证类型 【" + grantType + "】" 
					+ "登录IP【" + ip + "】未被允许！";
			String msg = "{\"error\":\"invalid_ip\",\"error_description\": \"请求失败，您的IP被限制访问！如有需要，请联系管理员。\"}";
			
	    	String id = UUID.randomUUID().toString().replaceAll("-", "");
			
			SysExceptionLogInfoInsertInputBean input = new SysExceptionLogInfoInsertInputBean();
			input.setId(id);
			input.setExpType(expType);
			input.setExpMsg(expMsg);
			input.setExpTime(new Date());
			input.setUserLoginName(username);
			input.setIp(ip);
			
			this.writeLog(input);
			
	    	ServerHttpResponse response = exchange.getResponse();
	    	response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
	    	response.setStatusCode(HttpStatus.BAD_REQUEST);
	        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
	        DataBuffer buffer = response.bufferFactory().wrap(bytes);
	        
	        return response.writeWith(Mono.just(buffer)).then(chain.filter(exchange));
		}
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
				charset = getMediaTypeCharset(contentType);
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
    
    private Mono<Void> otherFilter(ServerWebExchange exchange, GatewayFilterChain chain, String ip) {
    	ServerHttpRequest request = exchange.getRequest();
		String requestURI = request.getURI().getPath();
		HttpHeaders headers = request.getHeaders();
		
		Map<String, String> headerMap = new HashMap<>();
		for(String key : headers.keySet()) {
			headerMap.put(key, headers.getFirst(key));
		}
		
		String userId = "";
		String userName = "";
		
		SysUserInfo user = this.getCurrentUser(headerMap);
		if(user!=null) {
			userId = user.getId();
			userName = user.getLoginName();
//			userLoginName = user.getLoginName();
		}
		
    	String expType = "请求IP异常";
		String expMsg = "请求【" + requestURI + "】中的IP【" + ip + "】未被允许！";
		String msg = "{\"error\":\"invalid_ip\",\"error_description\": \"请求失败，您的IP被限制访问！如有需要，请联系管理员。\"}";
		
    	String id = UUID.randomUUID().toString().replaceAll("-", "");
		
		SysExceptionLogInfoInsertInputBean input = new SysExceptionLogInfoInsertInputBean();
		input.setId(id);
		input.setExpType(expType);
		input.setExpMsg(expMsg);
		input.setExpTime(new Date());
		input.setUserId(userId);
		input.setUserLoginName(userName);
		input.setIp(ip);
		
		this.writeLog(input);
		
    	ServerHttpResponse response = exchange.getResponse();
    	response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
    	response.setStatusCode(HttpStatus.BAD_REQUEST);
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        
        return response.writeWith(Mono.just(buffer));
    }
    
    private Charset getMediaTypeCharset(MediaType mediaType) {
        if (mediaType != null && mediaType.getCharset() != null) {
            return mediaType.getCharset();
        }
        else {
            return StandardCharsets.UTF_8;
        }
    }
    
    private void writeLog(SysExceptionLogInfoInsertInputBean input) {
    	if(input!=null) {
    		long checkLogCount = -1;
    		String ip = input.getIp();
    		String redisKey = IP_CHECK_LOG_COUNT_REDIS_NAMESPACE + ip;
    		boolean isHasKey = redisTemplate.hasKey(redisKey);
    		if(isHasKey) {
    			String loginCountStr = Objects.toString(redisTemplate.opsForValue().get(redisKey));
				try {
					checkLogCount = Long.parseLong(loginCountStr);
				} catch (Exception e) {
					// TODO: handle exception
				}
    		}
    		
    		/** 24小时内，只记录10次日志 **/
    		if(checkLogCount<10) {
    			try {
					//userFeign.saveExceptionLog(input);
        			
        			if(isHasKey) {
        				BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(redisKey);
    					boundValueOps.increment(1l);
        			}else {
        				BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(redisKey);
    					boundValueOps.increment(1l);
    					boundValueOps.expire(1, TimeUnit.DAYS);
        			}
					
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
    		}
    	}
    }
    
    /**
	 * 返回登录用户信息
	 *
	 * @param headerMap
	 * @return
	 */
	private SysUserInfo getCurrentUser(Map<String, String> headerMap) {
		SysUserInfo user = null;
		try {
			//String rvStr = authFeign.getCurrentUser(headerMap);
			String rvStr = "cgfy";
			log.debug("principal info:\t" + rvStr);
			if (rvStr != null && rvStr.length() > 0) {
				JSONObject rvObj = new JSONObject(rvStr);
				if (rvStr != null) {
					JSONObject principalObj = rvObj.getJSONObject("principal");
					if (principalObj != null) {
						JSONObject infoObj = principalObj.getJSONObject("info");
						if (infoObj != null) {
							user = new SysUserInfo();
							user.setId(Objects.toString(infoObj.get("id"), ""));
							user.setLoginName(Objects.toString(infoObj.get("loginName"), ""));
							user.setName(Objects.toString(infoObj.get("name"), ""));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
	
	private Map<String, Map<String, Integer>> getAllowIpMap(){
		if(!isInit) {
			List<String> allowIPRange = gatewayIpCheckProperties.getAllowIPRange();
			if(allowIPRange!=null && !allowIPRange.isEmpty()) {
				allowIpMap = new HashMap<String, Map<String, Integer>>(); 
				for(String ipRange : allowIPRange) {
					if(ipRange != null && !"".equals(ipRange.trim())) {
						//对该段的ip进行解析
						String[] ips = ipRange.split("-");
						if(ips.length > 0 && ips.length < 3) {
							String from = ips[0];//得到该段的起始ip
							String to = ips[1];  //得到该段的结束ip
							
							//获取该ip段地址的前三段，因为起始和结束的ip的前三段一样
							String share = from.substring(0, from.lastIndexOf("."));
							
							//获取该ip段的起始ip的最后一段
							int start = Integer.parseInt(from.substring(from.lastIndexOf(".")+1));
							//获取该ip段的结束ip的最后一段
							int end = Integer.parseInt(to.substring(to.lastIndexOf(".")+1));
							
							Map<String, Integer> lastLegMap = new HashMap<String, Integer>();
							lastLegMap.put("start", start);
							lastLegMap.put("end", end);
							
							allowIpMap.put(share, lastLegMap);
						} else {
							throw new RuntimeException("配置文件有错，请检查！");
						}
					}
				}
			}
			
			isInit = true;
		}
		
		return allowIpMap;
	}
 
    @Override
    public int getOrder() {
        return -201;
    }
}
