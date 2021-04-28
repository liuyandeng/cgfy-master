package com.cgfy.rabbitmq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.cgfy.rabbitmq.bean.QueueBean;
import com.cgfy.rabbitmq.rabbitmq.MQSender;
import com.cgfy.rabbitmq.utils.AjaxResponse;
import com.cgfy.rabbitmq.utils.LayuiResponse;
import com.cgfy.rabbitmq.utils.MQConstant;
import com.cgfy.rabbitmq.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * rabbitmq增删改查
 */
@Api(tags= "RabbitMQ基础操作")
@RestController
@RequestMapping("/rmq")
public class MQController {
    @Resource
    MQSender sender;
    @Resource
    WebUtils web;

    @ApiOperation(value = "直接模式")
    @GetMapping("/sendings/direct")
    public AjaxResponse<String> direct(@ApiParam(value = "消息内容" , required=true)String msg) {
        sender.send(msg);
        return AjaxResponse.success(msg);
    }

    @ApiOperation(value = "广播模式")
    @GetMapping("/sendings/fanout")
    public AjaxResponse<String> fanout(@ApiParam(value = "消息内容" , required=true)String msg) {
        sender.sendFanout(msg);
        return AjaxResponse.success(msg);
    }

    @ApiOperation(value = "主题模式")
    @GetMapping("/sendings/topic")
    public AjaxResponse<String> topic(@ApiParam(value = "消息内容" , required=true)String msg) {
        sender.sendTopic(msg);
        return AjaxResponse.success(msg);
    }

    @ApiOperation(value = "消息头模式")
    @GetMapping("/sendings/header")
    public AjaxResponse<String> header(@ApiParam(value = "消息内容" , required=true)String msg) {
        sender.sendHeader(msg);
        return AjaxResponse.success(msg);
    }

    @ApiOperation(value = "获取全部队列")
    @GetMapping("/queues")
    public LayuiResponse queues() throws Exception {
        String returnData=web.doGet(MQConstant.getQUEUES());
        JSONArray array=JSONObject.parseArray(returnData);
        List<QueueBean> list = JSONObject.parseArray(array.toJSONString(), QueueBean.class);

        return  LayuiResponse.success(list,list.size());
    }



}
