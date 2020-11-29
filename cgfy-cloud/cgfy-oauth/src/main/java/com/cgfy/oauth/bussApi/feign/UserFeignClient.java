package com.cgfy.oauth.bussApi.feign;
import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.base.interceptor.CustomFeignRequestInterceptor;
import com.cgfy.oauth.bussApi.feign.bean.UserInfoOutputBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
/**
 * feign连接用户管理服务，获取登录用户的用户详细信息
 */
@FeignClient(name = "cgfy-user",configuration= CustomFeignRequestInterceptor.class)
public interface UserFeignClient {
  @ApiOperation(value = "获取用户详情")
  @RequestMapping(value = "/UserInfo/{id}", method = RequestMethod.GET,consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public AjaxResponse<UserInfoOutputBean> getDetail(@PathVariable String id);

}
