package com.cgfy.oauth.base.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * 树形结构输出Bean
 *
 * @author liuyandeng
 */
public class TreeNodeBean{
	
	 /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String id;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private String parent_id;
    
	 /**
     * 数据真实ID
     */
    @ApiModelProperty(value = "数据真实ID，当多张表组成树形结构时，树形结构的ID不能重复，但是数据主见ID可能重复")
    private String data_id;

    /**
     * 数据真实父ID
     */
    @ApiModelProperty(value = "数据真实父ID")
    private String data_parent_id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String sort;

    /**
     * 其他信息
     */
    @ApiModelProperty(value = "其他信息")
    private Map<String, String> other = new HashMap<String, String>();

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getParent_id() {
		return parent_id;
	}


	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}


	public String getData_id() {
		return data_id;
	}


	public void setData_id(String data_id) {
		this.data_id = data_id;
	}


	public String getData_parent_id() {
		return data_parent_id;
	}


	public void setData_parent_id(String data_parent_id) {
		this.data_parent_id = data_parent_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}

	
	public Map<String, String> getOther() {
		return other;
	}


	public void setOther(Map<String, String> other) {
		this.other = other;
	}

}
