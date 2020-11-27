package com.cgfy.user.bussApi.controller;


import com.cgfy.user.base.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 「员工信息」基础Controller
 *
 * @author scgk_hr
 */
@Api(value = "员工信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController("StaffInfoController")
@RequestMapping("/StaffInfo")
public class StaffInfoController extends BaseController {

}

