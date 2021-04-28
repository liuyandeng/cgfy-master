package com.cgfy.mybatis.bussApi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.cgfy.mybatis.base.controller.BaseController;
import com.cgfy.mybatis.base.bean.AjaxResponse;
import com.cgfy.mybatis.base.bean.CgfyListResponse;
import com.cgfy.mybatis.base.bean.CgfySelectInputBean;
import com.cgfy.mybatis.bussApi.bean.TestGenOutputBean;
import com.cgfy.mybatis.bussApi.service.TestGenService;
import com.cgfy.mybatis.bussApi.bean.TestGenInputBean;
/**
 * 「cgfy」基础Controller
 *
 * @author cgfy_web
 */
@Api(value = "cgfy", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("TestGenController")
@RequestMapping("/TestGen")
public class TestGenController extends  BaseController {

	/**
	* 「cgfy」Service
	*/
    @Resource
	private TestGenService service;




    /**
    * 检索
    * @param cgfyInput 查询参数
    * @return 输入对象
    */
    @ApiOperation(value = "检索", hidden=true)
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public AjaxResponse<CgfyListResponse<TestGenOutputBean>> select(@RequestBody  @ApiParam(required=true) CgfySelectInputBean cgfyInput) {
        return AjaxResponse.success(service.select(cgfyInput));
    }

    /**
    * 保存
    * @param input 输入参数
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "保存", hidden=true)
    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public AjaxResponse<TestGenOutputBean> save(@RequestBody  @ApiParam(required=true) TestGenInputBean input,@PathVariable String id) {
        return AjaxResponse.success(service.save(input,id));
    }

    /**
    * 获取详情
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "获取详情", hidden=true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AjaxResponse<TestGenOutputBean> getDetail(@PathVariable String id) {
        return AjaxResponse.success(service.getDetail(id));
    }

    /**
    * 删除
    * @param id 主键id
    * @return 输出对象
    */
    @ApiOperation(value = "物理删除", hidden=true)
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public AjaxResponse<Object> deleteForce(@PathVariable String id) {
        service.deleteForce(id);
        return AjaxResponse.success();
    }



	


	

}

