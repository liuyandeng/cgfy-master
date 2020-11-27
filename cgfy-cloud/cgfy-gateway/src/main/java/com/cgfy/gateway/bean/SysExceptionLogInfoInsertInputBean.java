package com.cgfy.gateway.bean;

import lombok.Data;

/**
 * 平台异常日志信息插入用Bean
 */
@Data
public class SysExceptionLogInfoInsertInputBean {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 异常类型
     */
    private String expType;

    /**
     * 异常时间
     */
    private java.util.Date expTime;

    /**
     * 异常信息
     */
    private String expMsg;

    /**
     * 操作用户ID
     */
    private String userId;

    /**
     * 操作用户登录名
     */
    private String userLoginName;

    /**
     * IP地址
     */
    private String ip;


}