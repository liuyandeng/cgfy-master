package com.cgfy.oauth.bussApi.controller;

import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.bussApi.feign.UserFeignClient;
import com.cgfy.oauth.bussApi.feign.bean.UserInfoOutputBean;
import com.cgfy.oauth.base.config.AuthLoginLimitProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 验证账户
 */
@Api(tags = "验证账户", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("OauthAccountController")
@RequestMapping("/oauth/account")
public class OauthAccountController {
	
	@Autowired
	private AuthLoginLimitProperties loginLimitProperties;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private UserFeignClient userFeignClient;
	/**
	 * 账户登录锁定解除
	 */
	@ApiOperation(value = "账户登录锁定解除")
	@RequestMapping(value = "/accountLockoutRelease/{username}", method = RequestMethod.POST)
	public AjaxResponse<Object> accountLockoutRelease(@ApiParam(name = "username", value = "解锁用户登录名", required = true) @PathVariable("username") String username,
													  Principal user) {
		String redisKey = loginLimitProperties.getRedisKeyNamespace() + username;
		redisTemplate.delete(redisKey);
		return AjaxResponse.success();
	}
	@ApiOperation(value = "通过主键id获取用户详情")
	@RequestMapping(value = "/selectUserById/{id}", method=RequestMethod.GET)
	public AjaxResponse<UserInfoOutputBean> selectUserById(@PathVariable String id) {
		return userFeignClient.getDetail(id);
	}
}
