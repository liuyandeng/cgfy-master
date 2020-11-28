package com.cgfy.oauth.bussApi.feign;

import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.config.FeignCommonConfig;
import com.cgfy.oauth.bussApi.bean.ExceptionLogInfoInputBean;
import com.cgfy.oauth.bussApi.bean.LogonLogInfoInputBean;
import com.cgfy.oauth.bussApi.bean.SendSmsInputBean;
import com.cgfy.oauth.bussApi.bean.SendSmsOutputBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "cgfy-user", configuration= FeignCommonConfig.class)
public interface UserFeignClient {

    /**
	 *发送短信息
	 * 
	 * @param input 日志信息
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/common/SysMsgSend/sendSms")
	public AjaxResponse<List<SendSmsOutputBean>> sendSms(SendSmsInputBean input);
	
	
    /**
	 *保存登录日志
	 * 
	 * @param input 日志信息
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/log/SysLogonLogInfo/saveLoginLog")
	public void saveLoginLog(LogonLogInfoInputBean input);
	
	
    /**
	 *保存异常日志
	 * 
	 * @param input 日志信息
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/log/SysExceptionLogInfo/saveExceptionLog")
	public void saveExceptionLog(ExceptionLogInfoInputBean input);
	
	
}

