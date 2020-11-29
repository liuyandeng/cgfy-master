package com.cgfy.user.base.controller;

import com.cgfy.user.base.bean.AjaxResponse;
import com.cgfy.user.base.util.BaseCommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "工具控制器，完成通用的一些接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("UtilController")
@RequestMapping("/util")
public class UtilController {
    @ApiOperation(value = "获取UUID", hidden = false)
    @GetMapping("/getUuid")
    public AjaxResponse<String> getUuid() {
        return AjaxResponse.success(BaseCommonUtil.getUUID());
    }
}
