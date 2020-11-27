package com.cgfy.user.bussApi.feign.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * MetaBean
 *
 * @author qiucw 2018.05.18
 */
public class UserInfoAllOutputBean extends UserInfoOutputBean {

	 /**
     * 用户岗位信息
     */
    @ApiModelProperty(value = "用户岗位信息")
    private List<SysGroupInfoExtOutputBean> userGroupInfos = new ArrayList<SysGroupInfoExtOutputBean>();

	 /**
     * 用户岗位信息
     */
    @ApiModelProperty(value = "用户角色信息，包括岗位角色")
    private List<SysRoleInfoExtOutputBean> userRoleInfos = new ArrayList<SysRoleInfoExtOutputBean>();

	 /**
     * 用户兼职机构信息
     */
    @ApiModelProperty(value = "用户兼职机构信息")
    private List<SysOrgInfoExtOutputBean> userPtOrgInfos = new ArrayList<SysOrgInfoExtOutputBean>();


	public List<SysGroupInfoExtOutputBean> getUserGroupInfos() {
		return userGroupInfos;
	}

	public void setUserGroupInfos(List<SysGroupInfoExtOutputBean> userGroupInfos) {
		this.userGroupInfos = userGroupInfos;
	}

	public List<SysRoleInfoExtOutputBean> getUserRoleInfos() {
		return userRoleInfos;
	}

	public void setUserRoleInfos(List<SysRoleInfoExtOutputBean> userRoleInfos) {
		this.userRoleInfos = userRoleInfos;
	}

	public List<SysOrgInfoExtOutputBean> getUserPtOrgInfos() {
		return userPtOrgInfos;
	}

	public void setUserPtOrgInfos(List<SysOrgInfoExtOutputBean> userPtOrgInfos) {
		this.userPtOrgInfos = userPtOrgInfos;
	}

}
