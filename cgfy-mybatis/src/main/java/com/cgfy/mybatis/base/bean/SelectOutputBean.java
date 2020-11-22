package com.cgfy.mybatis.base.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SelectOutputBean<T> {

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private List<T> records ;
    
    /**
     * 件数
     */
    @ApiModelProperty(value = "件数")
    private int count;

    /**
     * 读取内容
     */
    public List<T> getRecords() {
        return records;
    }

    /**
     * 设定内容
     */
    public void setRecords(List<T> records) {
        this.records = records;
    }

    /**
     * 读取件数
     */
    public int getCount() {
        return count;
    }

    /**
     * 设定件数
     */
    public void setCount(int count) {
        this.count = count;
    }
}
