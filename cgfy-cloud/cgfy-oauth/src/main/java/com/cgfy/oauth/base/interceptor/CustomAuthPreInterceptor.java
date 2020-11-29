package com.cgfy.oauth.base.interceptor;

import com.cgfy.oauth.base.util.HttpUtil;
import com.cgfy.oauth.bussApi.bean.ExceptionLogInfoInputBean;
import com.cgfy.oauth.bussApi.bean.LogonLogInfoInputBean;
import com.cgfy.oauth.base.config.AuthLoginLimitProperties;
import com.cgfy.oauth.bussApi.feign.UserFeignClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class CustomAuthPreInterceptor implements HandlerInterceptor {

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private AuthLoginLimitProperties loginLimitProperties;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		CustomRequestContext.getContext().setRequest(request);
		CustomRequestContext.getContext().setResponse(response);
		String requestURI = request.getRequestURI();
		if(!requestURI.startsWith("/oauth/token")) {
			return true;
		}

		// 登录次数限定
		if(loginLimitProperties.getEnable()) {
			String ip = HttpUtil.getIpAddr(request);
			String username = request.getParameter("username");
			String clientId = request.getParameter("client_id");
			String grantType = request.getParameter("grant_type");

			String redisKey = loginLimitProperties.getRedisKeyNamespace() + username;
			if(redisTemplate.hasKey(redisKey)) {
				String loginCountStr = Objects.toString(redisTemplate.opsForValue().get(redisKey));
				long loginCount = -1;
				try {
					loginCount = Long.parseLong(loginCountStr);
				} catch (Exception e) {
					// TODO: handle exception
				}

				if(loginCount>=loginLimitProperties.getNumberOfAllowableFail()) {
					Integer limitTime = loginLimitProperties.getLimitTime();
					if(limitTime==null) {
						limitTime = loginLimitProperties.getRefreshInterval();
					}

					String msg = "登录失败，用户登陆次数过多，请 " + limitTime + " 分钟后重试！";

					// 记录超过允许登录失败次数后的5次异常日志
					if(loginCount<loginLimitProperties.getNumberOfAllowableFail()+5) {
						String id = UUID.randomUUID().toString().replaceAll("-", "");

						String expType = "登录异常";
						String expMsg = "用户【" + username + "】在客户端【" + clientId + "】 验证类型 【" + grantType + "】"
								+ loginLimitProperties.getRefreshInterval()
								+ " 分钟内登录失败的次数大于等于【" + loginLimitProperties.getNumberOfAllowableFail() + "】次后登录！";

						ExceptionLogInfoInputBean input = new ExceptionLogInfoInputBean();
						input.setId(id);
						input.setExpType(expType);
						input.setExpMsg(expMsg);
						input.setExpTime(new Date());
						input.setUserLoginName(username);
						input.setIp(ip);

						try {
							//userFeignClient.saveExceptionLog(input);
						} catch (Exception e) {
							e.printStackTrace();
						}

						BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(redisKey);
						boundValueOps.increment(1l);
					}

					response.sendError(HttpStatus.BAD_REQUEST.value(), msg);

					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		Enumeration<String> headerNames = request.getHeaderNames();
//		while(headerNames.hasMoreElements()) {
//			String key = headerNames.nextElement();
//
//			if(key!=null) {
//				System.out.println(key+"="+request.getHeader(key));
//			}else {
//				System.out.println(headerNames.nextElement());
//			}
//		}
		String requestURI = request.getRequestURI();
		if(!requestURI.startsWith("/oauth/token")) {
			return;
		}

		// 记录登录日志
		String ip = HttpUtil.getIpAddr(request);
		String browserType = HttpUtil.getBrowserType(request);
		String username = request.getParameter("username");
		String clientId = request.getParameter("client_id");
		String grantType = request.getParameter("grant_type");
		String password = request.getParameter("password");

		if(!StringUtils.isEmpty(username)) {
			int status = response.getStatus();

	        String logonFlag = "0";
			String logonMsg = "";
			if(HttpStatus.OK.value() == status) {
				logonFlag = "1";
				logonMsg = "登陆成功！";
			}else if(HttpStatus.BAD_REQUEST.value() == status || HttpStatus.UNAUTHORIZED.value() == status){
				logonMsg = "用户名或密码错误！";
			}else {
				logonMsg = "登录失败，未知异常！";
			}
			/** 记录登陆日志 **/
			String id = UUID.randomUUID().toString().replaceAll("-", "");

			LogonLogInfoInputBean input = new LogonLogInfoInputBean();
			input.setId(id);
			input.setUsername(username);
			input.setClientId(clientId);
			input.setGrantType(grantType);
			input.setIp(ip);
			input.setBrowserType(browserType);
			input.setLogonTime(new Date());
			input.setLogonFlag(logonFlag);
			input.setLogonStatus(status + "");
			input.setLogonMsg(logonMsg);

			try {
				//userFeignClient.saveLoginLog(input);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 记录登录失败次数
			try {
				if(loginLimitProperties.getEnable()) {
					String redisKey = loginLimitProperties.getRedisKeyNamespace() + username;

					// 登录失败
					if("0".equals(logonFlag)) {
						if(redisTemplate.hasKey(redisKey)) {
							BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(redisKey);
							boundValueOps.increment(1l);
						}else {
							BoundValueOperations<String, Object> boundValueOps = redisTemplate.boundValueOps(redisKey);
							boundValueOps.increment(1l);
							boundValueOps.expire(loginLimitProperties.getRefreshInterval(), TimeUnit.MINUTES);
						}
					}else {
						redisTemplate.delete(redisKey);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
