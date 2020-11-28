package com.cgfy.oauth.bussApi.bean;

import javax.validation.constraints.Size;

/**
 * 平台登录日志信息插入用Bean
 */
public class LogonLogInfoInputBean {
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
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取客户端ID
     *
     * @return 客户端ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * 设置客户端ID
     *
     * @param clientId 客户端ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取校验类型
     *
     * @return 校验类型
     */
    public String getGrantType() {
        return grantType;
    }

    /**
     * 设置校验类型
     *
     * @param grantType 校验类型
     */
    public void setGrantType(String grantType) {
        this.grantType = grantType;
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

    /**
     * 获取浏览器类型
     *
     * @return 浏览器类型
     */
    public String getBrowserType() {
        return browserType;
    }

    /**
     * 设置浏览器类型
     *
     * @param browserType 浏览器类型
     */
    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    /**
     * 获取登录时间
     *
     * @return 登录时间
     */
    public java.util.Date getLogonTime() {
        return logonTime;
    }

    /**
     * 设置登录时间
     *
     * @param logonTime 登录时间
     */
    public void setLogonTime(java.util.Date logonTime) {
        this.logonTime = logonTime;
    }

    /**
     * 获取是否成功登录（0_否，1_是）
     *
     * @return 是否成功登录（0_否，1_是）
     */
    public String getLogonFlag() {
        return logonFlag;
    }

    /**
     * 设置是否成功登录（0_否，1_是）
     *
     * @param logonFlag 是否成功登录（0_否，1_是）
     */
    public void setLogonFlag(String logonFlag) {
        this.logonFlag = logonFlag;
    }

    /**
     * 获取登录消息
     *
     * @return 登录消息
     */
    public String getLogonMsg() {
        return logonMsg;
    }

    /**
     * 设置登录消息
     *
     * @param logonMsg 登录消息
     */
    public void setLogonMsg(String logonMsg) {
        this.logonMsg = logonMsg;
    }

    /**
     * 获取登录状态
     *
     * @return 登录状态
     */
    public String getLogonStatus() {
        return logonStatus;
    }

    /**
     * 设置登录状态
     *
     * @param logonStatus 登录状态
     */
    public void setLogonStatus(String logonStatus) {
        this.logonStatus = logonStatus;
    }
}