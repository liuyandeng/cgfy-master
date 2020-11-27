package com.cgfy.user.generator.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class PageBean<T> {

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



}
