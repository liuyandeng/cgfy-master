package com.cgfy.gateway.bean;

import lombok.Data;

/**
 * 平台用户操作日志信息插入用Bean
 */
@Data
public class SysOptLogInfoInsertInputBean {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 操作时间
     */
    private java.util.Date optTime;

    /**
     * 操作Method
     */
    private String optMethod;

    /**
     * 操作Url
     */
    private String optUrl;

    /**
     * 操作菜单
     */
    private String optMenu;

    /**
     * 操作名称
     */
    private String optName;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 浏览器类型
     */
    private String browserType;


}