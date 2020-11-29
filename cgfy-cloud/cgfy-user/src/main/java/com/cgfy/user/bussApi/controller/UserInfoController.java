package com.cgfy.user.bussApi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.cgfy.user.base.controller.BaseController;
import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.bean.CgfyListResponse;
import com.cgfy.user.base.bean.CgfySelectInputBean;
import com.cgfy.user.bussApi.bean.UserInfoOutputBean;
import com.cgfy.user.bussApi.service.UserInfoService;
import com.cgfy.user.bussApi.bean.UserInfoInputBean;
/**
 * 「平台用户信息」基础Controller
 *
 * @author cgfy_web
 */
@Api(tags = "平台用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("UserInfoController")
@RequestMapping("/UserInfo")
public class UserInfoController extends  BaseController {

	/**
	* 「平台用户信息」Service
	*/
    @Resource
	private UserInfoService service;




    /**
    * 检索
    * @param cgfyInput 查询参数
    * @return 输入对象
    */
    @ApiOperation(value = "检索", hidden=false)
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public AjaxResponse<CgfyListResponse<UserInfoOutputBean>> select(@RequestBody  @ApiParam(required=true) CgfySelectInputBean cgfyInput) {
        return AjaxResponse.success(service.select(cgfyInput));
    }

    /**
    * 保存
    * @param input 输入参数
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "保存", hidden=false)
    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public AjaxResponse<UserInfoOutputBean> save(@RequestBody  @ApiParam(required=true) UserInfoInputBean input,@PathVariable String id) {
        return AjaxResponse.success(service.save(input,id));
    }

    /**
    * 获取详情
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "获取详情", hidden=false)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResponse<UserInfoOutputBean> getDetail(@PathVariable String id) {
        return AjaxResponse.success(service.getDetail(id));
    }

    /**
    * 删除
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "物理删除", hidden=false)
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public AjaxResponse<Object> deleteForce(@PathVariable String id) {
        service.deleteForce(id);
        return AjaxResponse.success();
    }



	


	

}

