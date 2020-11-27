package com.cgfy.gateway.filter;

import com.cgfy.gateway.bean.SysOptLogInfoInsertInputBean;
import com.cgfy.gateway.bean.SysPriApiUrlNoEmpty;
import com.cgfy.gateway.bean.SysUserInfo;
import com.cgfy.gateway.config.GatewayLogProperties;
import com.cgfy.gateway.service.AuthRemoteService;
import com.cgfy.gateway.service.UumsRemoteService;
import com.cgfy.gateway.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Component
@Slf4j
public class RequestLogGlobalFilter implements GlobalFilter, Ordered {
	
	@Autowired
	private AuthRemoteService authRemoteService;
	
	@Autowired
	private UumsRemoteService uumsRemoteService;
	
	@Autowired
	private GatewayLogProperties gatewayLogProperties;
 
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	
    	ServerHttpRequest request = exchange.getRequest();
		String requestURI = request.getURI().getPath();
		String method = request.getMethodValue();
		HttpHeaders headers = request.getHeaders();
		
		Map<String, String> headerMap = new HashMap<>();
		for(String key : headers.keySet()) {
			headerMap.put(key, headers.getFirst(key));
		}
		
		boolean isFilter = true;
		
		if(requestURI.startsWith("/error")) {
			isFilter = false;
		}
		
		if(isFilter) {
			List<String> optLogIgnore = gatewayLogProperties.getOptLogIgnore();
			if (optLogIgnore != null && !optLogIgnore.isEmpty()) {
				for (String item : optLogIgnore) {
					if (requestURI.startsWith(item)) {
						isFilter = false;
						break;
					}
				}
			}
		}
		
		if(isFilter) {
			Stream<SysPriApiUrlNoEmpty> matcherSysPri = null;
			
			List<SysPriApiUrlNoEmpty> SysPriList = null;
			try {
				SysPriList = uumsRemoteService.getSysPriApiUrlNoEmpty();
			}catch (Exception e) {}
			
			if(SysPriList!=null && !SysPriList.isEmpty()) {
				matcherSysPri = this.getMatcherSysPri(requestURI, method, SysPriList);
			}
			
			if(matcherSysPri!=null) {
				List<SysPriApiUrlNoEmpty> result = matcherSysPri.collect(Collectors.toList());
				
				if(result!=null && !result.isEmpty()) {
					SysPriApiUrlNoEmpty sysPri = result.get(0);
					if(sysPri!=null) {
						
						String userId = "";
						String userName = "";
//						String userLoginName = "";
						String ip = HttpUtil.getIpAddr(request);
						String browserType = HttpUtil.getBrowserType(request);
						
						SysUserInfo user = this.getCurrentUser(headerMap);
						if(user!=null) {
							userId = user.getId();
							userName = user.getName();
//							userLoginName = user.getLoginName();
						}
						
						String id = UUID.randomUUID().toString().replaceAll("-", "");
						
						SysOptLogInfoInsertInputBean input = new SysOptLogInfoInsertInputBean();
						input.setId(id);
						input.setUserId(userId);
						input.setUserName(userName);
						input.setIp(ip);
						input.setBrowserType(browserType);
						input.setOptTime(new Date());
						input.setOptMethod(method);
						input.setOptUrl(requestURI);
						input.setOptMenu(sysPri.getMenuName());
						input.setOptName( sysPri.getOptName());
						
						try {
							uumsRemoteService.saveOptLog(input);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
    	return chain.filter(exchange);
    }
    
    /**
     * 获取匹配的平台资源
     *
     * @param requestUri
     * @param method
     * @return
     */
    private Stream<SysPriApiUrlNoEmpty> getMatcherSysPri(final String requestUri, final String method, List<SysPriApiUrlNoEmpty> SysPriList) {
        return SysPriList.parallelStream().filter(new Predicate<SysPriApiUrlNoEmpty>() {
            @Override
            public boolean test(SysPriApiUrlNoEmpty SysPri) {
                String url = SysPri.getApiUrl();
                String uri = url.replaceAll("\\{[^)]+\\}", "[^)]+");
                String regEx = "^" + uri + "$";
                boolean isUrlMatcher = (Pattern.compile(regEx).matcher(requestUri).find());
                //boolean isUrlMatcher = (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"));
                String apiMethod = SysPri.getApiMethod();
                boolean isMethodMatcher = true;
                if(!StringUtils.isEmpty(apiMethod)) {
                	isMethodMatcher = method.equals(apiMethod);
                }
                return isUrlMatcher && isMethodMatcher;
            }
        });
    }
    
    
    /**
	 * 返回登录用户信息
	 *
	 * @return
	 */
	private SysUserInfo getCurrentUser(Map<String, String> headerMap) {
		SysUserInfo user = null;
		try {
			String rvStr = authRemoteService.getCurrentUser(headerMap);
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
 
    @Override
    public int getOrder() {
        return -200;
    }
}
