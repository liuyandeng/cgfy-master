package com.cgfy.user.base.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CgfyListResponse<T> {


    @ApiModelProperty(value = "内容")
    private List<T> forms ;

    @ApiModelProperty(value = "记录总数")
    private int totalCount;

    @ApiModelProperty(value = "当前页")
    private int page;

    @ApiModelProperty(value = "每页大小")
    private int pageSize;

    @ApiModelProperty(value = "总页数")
    private int pageCount;


    public List<T> getForms() {
        return forms;
    }

    public void setForms(List<T> forms) {
        this.forms = forms;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


}
