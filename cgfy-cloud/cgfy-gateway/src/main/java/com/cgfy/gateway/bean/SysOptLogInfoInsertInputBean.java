package com.cgfy.gateway.bean;

/**
 * 平台用户操作日志信息插入用Bean
 *
 * @author qiucw
 */
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
     * 获取用户ID
     *
     * @return 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户姓名
     *
     * @return 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作时间
     *
     * @return 操作时间
     */
    public java.util.Date getOptTime() {
        return optTime;
    }

    /**
     * 设置操作时间
     *
     * @param optTime 操作时间
     */
    public void setOptTime(java.util.Date optTime) {
        this.optTime = optTime;
    }

    /**
     * 获取操作Method
     *
     * @return 操作Method
     */
    public String getOptMethod() {
        return optMethod;
    }

    /**
     * 设置操作Method
     *
     * @param optMethod 操作Method
     */
    public void setOptMethod(String optMethod) {
        this.optMethod = optMethod;
    }

    /**
     * 获取操作Url
     *
     * @return 操作Url
     */
    public String getOptUrl() {
        return optUrl;
    }

    /**
     * 设置操作Url
     *
     * @param optUrl 操作Url
     */
    public void setOptUrl(String optUrl) {
        this.optUrl = optUrl;
    }

    /**
     * 获取操作菜单
     *
     * @return 操作菜单
     */
    public String getOptMenu() {
        return optMenu;
    }

    /**
     * 设置操作菜单
     *
     * @param optMenu 操作菜单
     */
    public void setOptMenu(String optMenu) {
        this.optMenu = optMenu;
    }

    /**
     * 获取操作名称
     *
     * @return 操作名称
     */
    public String getOptName() {
        return optName;
    }

    /**
     * 设置操作名称
     *
     * @param optName 操作名称
     */
    public void setOptName(String optName) {
        this.optName = optName;
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
}