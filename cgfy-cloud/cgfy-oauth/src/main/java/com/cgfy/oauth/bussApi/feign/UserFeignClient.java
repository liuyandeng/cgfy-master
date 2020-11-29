package com.cgfy.oauth.bussApi.feign;
import com.cgfy.oauth.base.bean.AjaxResponse;
import com.cgfy.oauth.bussApi.feign.bean.UserInfoOutputBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
/**
 * feign连接用户管理服务，获取登录用户的用户详细信息
 */
@FeignClient(name = "cgfy-user")
public interface UserFeignClient {
  @ApiOperation(value = "获取用户详情")
  @RequestMapping(value = "/UserInfo/{id}", method = RequestMethod.GET)
  public AjaxResponse<UserInfoOutputBean> getDetail(@PathVariable String id);

}
