package com.cgfy.gateway.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 平台权限信息（请求url不为空）
 */
@Data
public class SysPriApiUrlNoEmpty implements Serializable{
    /**
     * 主键
     */
    private String id;
    
    /**
     * 菜单名称
     */
    private String menuName;
    
    /**
     * 操作名称
     */
    private String optName;

    /**
     * 父ID
     */
    private String parentId;

   
    /**
     * 功能类型（0-导航菜单，1-按钮，2-组件）
     */
    private String type;

    /**
     * API 访问地址
     */
    private String apiUrl;
    
    /**
     * API Method
     */
    private String apiMethod;

    /**
     * 所属子系统id
     */
    private String subsysid;

	
	public SysPriApiUrlNoEmpty() {
		
	}

	public SysPriApiUrlNoEmpty(String id, String menuName, String optName, String parentId, String type, String apiUrl,
			String apiMethod, String subsysid) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.optName = optName;
		this.parentId = parentId;
		this.type = type;
		this.apiUrl = apiUrl;
		this.apiMethod = apiMethod;
		this.subsysid = subsysid;
	}
	
}