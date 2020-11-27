package com.cgfy.gateway.feign;

import com.cgfy.gateway.bean.SysExceptionLogInfoInsertInputBean;
import com.cgfy.gateway.bean.SysLogonLogInfoInsertInputBean;
import com.cgfy.gateway.bean.SysOptLogInfoInsertInputBean;
import com.cgfy.gateway.bean.SysPriApiUrlNoEmpty;
import com.cgfy.gateway.config.FeignCommonConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "cgfy-user", configuration= FeignCommonConfig.class)
public interface UserFeignClient {

	
	@RequestMapping(value = "/FreeCertification/SysPriCommon/getSysPriApiUrlNoEmpty", method = RequestMethod.POST)
	public List<SysPriApiUrlNoEmpty> getSysPriApiUrlNoEmpty();
	
    /**
	 *保存登录日志
	 * 
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/log/SysLogonLogInfo/saveLoginLog")
	public void saveLoginLog(SysLogonLogInfoInsertInputBean input);
	
    /**
	 *保存操作日志
	 * 
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/log/SysOptLogInfo/saveOptLog")
	public void saveOptLog(SysOptLogInfoInsertInputBean input);
	
    /**
	 *保存异常日志
	 * 
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/log/SysExceptionLogInfo/saveExceptionLog")
	public void saveExceptionLog(SysExceptionLogInfoInsertInputBean input);
	
}

