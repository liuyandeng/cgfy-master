package com.cgfy.gateway.bean;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * 平台登录日志信息插入用Bean
 *
 */
@Data
public class SysLogonLogInfoInsertInputBean {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 校验类型
     */
    private String grantType;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 浏览器类型
     */
    private String browserType;

    /**
     * 登录时间
     */
    private java.util.Date logonTime;

    /**
     * 是否成功登录（0_否，1_是）
     */
    @Size(max = 1)
    private String logonFlag;

    /**
     * 登录消息
     */
    @Size(max = 200)
    private String logonMsg;

    /**
     * 登录状态
     */
    @Size(max = 32)
    private String logonStatus;


}