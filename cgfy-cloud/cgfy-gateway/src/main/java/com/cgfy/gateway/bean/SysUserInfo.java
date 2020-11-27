package com.cgfy.gateway.bean;

import java.io.Serializable;

/**
 * 平台用户信息
 *
 * @author qiucw
 */
public class SysUserInfo implements Serializable{
	
    /**
     * 主键ID
     */
    private String id;

    /**
     * 登录名
     */
    private String loginName;


    /**
     * 用户名称
     */
    private String name;
    


    private static final long serialVersionUID = 1L;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}