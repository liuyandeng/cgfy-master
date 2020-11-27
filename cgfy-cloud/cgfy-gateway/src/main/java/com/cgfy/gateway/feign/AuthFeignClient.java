package com.cgfy.gateway.feign;

import com.cgfy.gateway.config.FeignCommonConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "cgfy-oauth", configuration= FeignCommonConfig.class)
public interface AuthFeignClient {

    /**
	 * 取得当前用户
	 * 
	 * @return 用户信息
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/oauth/ext/getCurrentUser")
	public String getCurrentUser(@RequestHeader Map<String, String> headers);
	
}

