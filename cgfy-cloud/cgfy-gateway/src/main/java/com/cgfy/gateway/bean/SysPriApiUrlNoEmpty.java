package com.cgfy.gateway.bean;

import java.io.Serializable;

/**
 * 平台权限信息（请求url不为空）
 *
 * @author qiucw
 */
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

  
    private static final long serialVersionUID = 1L;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getOptName() {
		return optName;
	}


	public void setOptName(String optName) {
		this.optName = optName;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getApiUrl() {
		return apiUrl;
	}


	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	

	public String getApiMethod() {
		return apiMethod;
	}


	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}


	public String getSubsysid() {
		return subsysid;
	}


	public void setSubsysid(String subsysid) {
		this.subsysid = subsysid;
	}
	
	
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