package com.cgfy.user.bussApi.feign;

import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.bean.SelectInputBean;
import com.cgfy.user.base.bean.SelectOutputBean;
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
@FeignClient(name = "jbdp-uums")
public interface UserFeignClient {

  @RequestMapping(value = "/common/user/getUserInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
  public AjaxResponse<UserInfoOutputBean> getUserInfo(@RequestParam("userId") String userId);

  @RequestMapping(value = "/common/user/getUserAllInfo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
  public AjaxResponse<UserInfoAllOutputBean> getUserAllInfo(@RequestParam("userId") String userId);



  @RequestMapping(value = "/api/v1/profile/users/{id}/organ/role/{en}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
  public AjaxResponse<SysUserInfoInternalOutputBean> getUserOfCurrRole(
          @PathVariable(value = "id") String userId, @PathVariable(value = "en") String en);

  @ApiOperation(value = "获取所有缓存的码表信息")
  @RequestMapping(value = "/FreeCertification/SysCodeCommon/selectEx", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
  public AjaxResponse<SelectOutputBean<SysCodeInfoInternalOutputBean>> selectEx();

  /**
   * 用户信息检索
   */
  @ApiOperation(value = "用户信息检索")
  @RequestMapping(value = "/SysUserInfo/select", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
  public AjaxResponse<SelectOutputBean<UserInfoOutputBean>> select(@RequestBody @Validated @ApiParam(required=true) SelectInputBean input);

  /**
   * 通过主键ID获取机构详情
   */
  @ApiOperation(value = "通过主键ID获取机构详情")
  @RequestMapping(value = "/SysOrgInfo/getOrgInfoById", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
  public AjaxResponse<SysOrgInfoInternalOutputBean> getOrgInfoById(@RequestParam("orgId") @ApiParam(value = "机构id") String orgId);


  @ApiOperation(value = "用户复合检索（可查询组织机构子集用户）")
  @RequestMapping(value = "/SysUserCompound/selectUsercompound", method = RequestMethod.POST)
  public AjaxResponse<SelectOutputBean<SysUserCompoundOutputBean>> selectUsercompound(
          @RequestBody @Validated @ApiParam(required=true) SelectInputBean input,
          @ApiParam(name = "orgId", value = "机构ID", required = true) @RequestParam(value = "orgId", required = true) String orgId);


  @ApiOperation(value = "通过门户人事系统添加人员")
  @RequestMapping(value = "/SysUserInfo/insertFromHr", method = RequestMethod.POST)
  public AjaxResponse<SysUserInfoInternalOutputBean> insertFromHr(@RequestBody @Validated @ApiParam(required=true) SysUserInfoInsertInputBean input);

  @ApiOperation(value = "条件更新")
  @RequestMapping(value = "/SysUserInfo/updateUserAndPtOrg/{id}", method = RequestMethod.POST)
  public AjaxResponse<Boolean> updateUserAndPtOrg(@RequestBody @Validated @ApiParam(required=true) SysUserAndPtOrgUpdateInputBean input , @PathVariable("id") String id);

  @ApiOperation(value = "获取子公司ID列表")
  @RequestMapping(value = "/SysOrgInfo/selectOrgIdListById", method = RequestMethod.POST)
  public AjaxResponse<List<String>> selectOrgIdListById();
  @ApiOperation(value = "获取子公司及部门ID列表")
  @RequestMapping(value = "/SysOrgInfo/selectChildIdListByOrgId", method = RequestMethod.POST)
  public AjaxResponse<List<String>> selectChildIdListByOrgId(@ApiParam(name = "orgId", value = "主键ID", required = true) @RequestParam(value = "orgId", required = true) String orgId);

}
