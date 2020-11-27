package com.cgfy.user.common.controller;

import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.util.BaseCommonUtil;
//import com.jbdp.base.util.MysqlHelper;
import com.cgfy.user.base.util.ZgcConstant;
import com.cgfy.user.common.utils.CodeListUtil;
import com.cgfy.user.bussApi.feign.UserFeignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "工具控制器，完成通用的一些接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("UtilController")
@RequestMapping("/util")
public class UtilController {

//    @Autowired
//    @Qualifier("resService")
//    ResourceService resourceService;
//    @Autowired
//    private MysqlHelper mysqlHelper;

    @Autowired
    private UserFeignService userFeignService;

    @ApiOperation(value = "获取UUID", hidden = false)
    @GetMapping("/getUuid")
    public AjaxResponse<String> getUuid() {
        return AjaxResponse.success(BaseCommonUtil.getUUID());
    }



    @ApiOperation(value = "获取发文类型列表", hidden = false)
    @GetMapping("/getOutGoingType")
    public AjaxResponse<List<Map<String,Object>>> getOutGoingType() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getType(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取紧急程度列表,只包含一般和特急", hidden = false)
    @GetMapping("/getUrgency")
    public AjaxResponse<List<Map<String,Object>>> getUrgency() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getUrgency(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取行文方向列表", hidden = false)
    @GetMapping("/getDirection")
    public AjaxResponse<List<Map<String,Object>>> getDirection() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getDirection(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取收文类型列表", hidden = false)
    @GetMapping("/getIncomingType")
    public AjaxResponse<List<Map<String,Object>>> getIncomingType() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getIncoming_type(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取来文机关列表", hidden = false)
    @GetMapping("/getComingOrg")
    public AjaxResponse<List<Map<String,Object>>> getComingOrg() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getComing_org(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取紧急程度(收文)列表", hidden = false)
    @GetMapping("/getEmergencyLevel")
    public AjaxResponse<List<Map<String,Object>>> getEmergencyLevel() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getEmergencyLevel(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取事项类别列表", hidden = false)
    @GetMapping("/getContract")
    public AjaxResponse<List<Map<String,Object>>> getContract() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getContract(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取反馈频率列表", hidden = false)
    @GetMapping("/getFeedback")
    public AjaxResponse<List<Map<String,Object>>> getFeedback() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getFeedback(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取单据状态(无工作流)列表", hidden = false)
    @GetMapping("/getStatusSimple")
    public AjaxResponse<List<Map<String,Object>>> getStatusSimple() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getStatus_simple(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取出访目的列表", hidden = false)
    @GetMapping("/getPurpose")
    public AjaxResponse<List<Map<String,Object>>> getPurpose() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getPurpose(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取状态(对私报销)列表", hidden = false)
    @GetMapping("/getStatusPrivate")
    public AjaxResponse<List<Map<String,Object>>> getStatusPrivate() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getStatus_private(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取结算方式列表", hidden = false)
    @GetMapping("/getClearing")
    public AjaxResponse<List<Map<String,Object>>> getClearing() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getClearing(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取出差人员最高职务列表", hidden = false)
    @GetMapping("/getTopPower")
    public AjaxResponse<List<Map<String,Object>>> getTopPower() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getTopPower(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取币种列表", hidden = false)
    @GetMapping("/getCurrency")
    public AjaxResponse<List<Map<String,Object>>> getCurrency() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getCurrency(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取职务列表", hidden = false)
    @GetMapping("/getJob")
    public AjaxResponse<List<Map<String,Object>>> getJob() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getJob(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取状态(通用)列表", hidden = false)
    @GetMapping("/getStatusCommon")
    public AjaxResponse<List<Map<String,Object>>> getStatusCommon() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getStatus_common(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取结算方式(借款)列表", hidden = false)
    @GetMapping("/getBorrowClearing")
    public AjaxResponse<List<Map<String,Object>>> getBorrowClearing() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getBorrow_clearing(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取信息类型列表", hidden = false)
    @GetMapping("/getInfoType")
    public AjaxResponse<List<Map<String,Object>>> getInfoType() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getInfo_type(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取车辆类型列表", hidden = false)
    @GetMapping("/getVehicleType")
    public AjaxResponse<List<Map<String,Object>>> getVehicleType() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getVehicle_type(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取车辆状态列表", hidden = false)
    @GetMapping("/getVehicleStatus")
    public AjaxResponse<List<Map<String,Object>>> getVehicleStatus() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getVehicle_status(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取司机状态列表", hidden = false)
    @GetMapping("/getDriverStatus")
    public AjaxResponse<List<Map<String,Object>>> getDriverStatus() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getDriver_status(),ZgcConstant.getSavedStatus()));
    }
    @ApiOperation(value = "获取品牌相关状态列表", hidden = false)
    @GetMapping("/getBrandingStatus")
    public AjaxResponse<List<Map<String,Object>>> getBrandingStatus() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getBranding_status(),ZgcConstant.getSavedStatus()));
    }

    @ApiOperation(value = "根据数据字典类型获取对应的列表", hidden = false)
    @GetMapping("/getKeyValueByCode")
    public AjaxResponse<List<Map<String,Object>>> getKeyValueByCode(@ApiParam(name = "codeType", value = "数据字典code类型", required = true) @RequestParam(value = "codeType", required = true) String codeType) {
        return AjaxResponse.success(CodeListUtil.getCodeList(codeType,ZgcConstant.getSavedStatus()));
    }

    @ApiOperation(value = "获取状态(协同资源)", hidden = false)
    @GetMapping("/getStatusResource")
    public AjaxResponse<List<Map<String,Object>>> getStatusResource() {
        return AjaxResponse.success(CodeListUtil.getCodeList(ZgcConstant.getStatus_resource(),ZgcConstant.getSavedStatus()));
    }

}
