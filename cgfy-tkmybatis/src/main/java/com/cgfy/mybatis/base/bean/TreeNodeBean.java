package com.cgfy.mybatis.base.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 树形结构输出Bean
 */
@Data
public class TreeNodeBean {
	
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
	 * 机构类型
	 */
	@ApiModelProperty(value = "机构类型")
	private String type;

	/**
     * 其他信息
     */
    @ApiModelProperty(value = "其他信息")
    private Map<String, String> other = new HashMap<String, String>();


}
