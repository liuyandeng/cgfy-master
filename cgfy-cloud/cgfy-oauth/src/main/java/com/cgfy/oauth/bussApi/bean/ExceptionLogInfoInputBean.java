package com.cgfy.oauth.bussApi.bean;

/**
 * 平台异常日志信息插入用Bean
 */
public class ExceptionLogInfoInputBean {
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

    /**
     * 获取主键ID
     *
     * @return 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取异常类型
     *
     * @return 异常类型
     */
    public String getExpType() {
        return expType;
    }

    /**
     * 设置异常类型
     *
     * @param expType 异常类型
     */
    public void setExpType(String expType) {
        this.expType = expType;
    }

    /**
     * 获取异常时间
     *
     * @return 异常时间
     */
    public java.util.Date getExpTime() {
        return expTime;
    }

    /**
     * 设置异常时间
     *
     * @param expTime 异常时间
     */
    public void setExpTime(java.util.Date expTime) {
        this.expTime = expTime;
    }

    /**
     * 获取异常信息
     *
     * @return 异常信息
     */
    public String getExpMsg() {
        return expMsg;
    }

    /**
     * 设置异常信息
     *
     * @param expMsg 异常信息
     */
    public void setExpMsg(String expMsg) {
        this.expMsg = expMsg;
    }

    /**
     * 获取操作用户ID
     *
     * @return 操作用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置操作用户ID
     *
     * @param userId 操作用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取操作用户登录名
     *
     * @return 操作用户登录名
     */
    public String getUserLoginName() {
        return userLoginName;
    }

    /**
     * 设置操作用户登录名
     *
     * @param userLoginName 操作用户登录名
     */
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    /**
     * 获取IP地址
     *
     * @return IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置IP地址
     *
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}