package com.cgfy.user.bussApi.feign;

import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.bussApi.feign.bean.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * feign连接用户管理服务，获取登录用户的用户详细信息
 */
@FeignClient(name = "cgfy-user")
public interface UserFeignClient {

  @RequestMapping(value = "/api/v1/profile/users/{id}/organ/role/{en}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  public AjaxResponse<UserInfoOutputBean> getUserOfCurrRole(
          @PathVariable(value = "id") String userId, @PathVariable(value = "en") String en);

  @ApiOperation(value = "通过门户人事系统添加人员")
  @RequestMapping(value = "/SysUserInfo/insertFromHr", method = RequestMethod.POST)
  public AjaxResponse<UserInfoOutputBean> insertFromHr(@RequestBody @Validated @ApiParam(required=true) UserInfoInputBean input);

  @ApiOperation(value = "获取子公司ID列表")
  @RequestMapping(value = "/SysOrgInfo/selectOrgIdListById", method = RequestMethod.POST)
  public AjaxResponse<List<String>> selectOrgIdListById();
  @ApiOperation(value = "获取子公司及部门ID列表")
  @RequestMapping(value = "/SysOrgInfo/selectChildIdListByOrgId", method = RequestMethod.POST)
  public AjaxResponse<List<String>> selectChildIdListByOrgId(@ApiParam(name = "orgId", value = "主键ID", required = true) @RequestParam(value = "orgId", required = true) String orgId);

}
