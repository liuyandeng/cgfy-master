package com.cgfy.mq.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.cgfy.mq.bean.QueueBean;
import com.cgfy.mq.rabbitmq.MQSender;
import com.cgfy.mq.utils.AjaxResponse;
import com.cgfy.mq.utils.LayuiResponse;
import com.cgfy.mq.utils.MQConstant;
import com.cgfy.mq.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * rabbitmq增删改查
 */

@RestController
@RequestMapping("/api")
public class MQController {
    private static final Logger logger = LoggerFactory.getLogger(MQController.class);
    @Resource
    MQSender sender;
    @Resource
    WebUtils web;

    //直接模式
    @GetMapping("/sendings")
    public AjaxResponse<String> mq(String msg) {
        sender.send(msg);
        return AjaxResponse.success(msg);
    }

    //广播模式
    @GetMapping("/sendings/fanout")
    public AjaxResponse<String> fanout(String msg) {
        sender.sendFanout(msg);
        return AjaxResponse.success(msg);
    }

    //主题模式
    @GetMapping("/sendings/topic")
    public AjaxResponse<String> topic(String msg) {
        sender.sendTopic(msg);
        return AjaxResponse.success(msg);
    }

    //消息头模式
    @GetMapping("/sendings/header")
    public AjaxResponse<String> header(String msg) {
        sender.sendHeader(msg);
        return AjaxResponse.success(msg);
    }

    //获取全部队列
    @GetMapping("/queues")
    public LayuiResponse queues() throws Exception {
        String returnData=web.doGet(MQConstant.getQUEUES());
        JSONArray array=JSONObject.parseArray(returnData);
        List<QueueBean> list = JSONObject.parseArray(array.toJSONString(), QueueBean.class);

        return  LayuiResponse.success(list,list.size());
    }



}
