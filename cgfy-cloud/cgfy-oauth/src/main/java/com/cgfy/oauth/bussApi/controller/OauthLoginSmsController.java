package com.cgfy.oauth.bussApi.controller;

import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.bussApi.service.OauthLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信验证码登录
 */
@Api(tags = "短信验证码登录", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("OauthLoginSmsController")
@RequestMapping("/oauth/login/sms")
public class OauthLoginSmsController {
	
	@Autowired
	private OauthLoginService oauthLoginService;
	
	/**
	 * 获取短信验证码
	 * @throws Exception 
	 */
	@ApiOperation(value = "获取短信验证码")
	@RequestMapping(value = "/getSmsCode", method = RequestMethod.POST)
	public AjaxResponse<Object> getSmsCode(
			@ApiParam(name = "phoneNumber", value = "登录手机号码", required = true) @RequestParam(value="phoneNumber", required=true) String phoneNumber){
		return oauthLoginService.sendSmsCode(phoneNumber);
	}
	
}
