package com.cgfy.gateway.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 平台用户信息

 */
@Data
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


}