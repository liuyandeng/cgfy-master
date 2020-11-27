package com.cgfy.user.base.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CgfySelectInputBean {

    @ApiModelProperty(value = "条件")
    private List<Map<String,Object>> options = new ArrayList<Map<String,Object>>();

    @ApiModelProperty(value = "当前页")
    private int page = 1;

    @ApiModelProperty(value = "页大小")
    private int pageSize = Integer.MAX_VALUE;

    @ApiModelProperty(value = "排序字段")
    private String orderBy = "";

    @ApiModelProperty(value = "是否降序")
    private boolean desc = true;

    @ApiModelProperty(value = "类型")
    private String type = "";

    public List<Map<String, Object>> getOptions() {
        return options;
    }

    public void setOptions(List<Map<String, Object>> options) {
        this.options = options;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public SelectInputBean transToSelectInputBean(){
        return BeanTrans.zgcfzBeanToNormal(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
