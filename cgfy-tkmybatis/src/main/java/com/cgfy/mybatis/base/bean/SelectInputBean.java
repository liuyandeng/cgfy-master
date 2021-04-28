package com.cgfy.mybatis.base.bean;

import com.cgfy.mybatis.base.bean.select.Condition;
import com.cgfy.mybatis.base.bean.select.Order;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;

public class SelectInputBean {





    @ApiModelProperty(value = "条件")
    private List<Condition> condition = new ArrayList<Condition>();
    
    @ApiModelProperty(value = "排序")
    private List<Order> sort = new ArrayList<Order>();
    
    @ApiModelProperty(value = "检索项目")
    private List<String> fields = new ArrayList<String>();
    
    @ApiModelProperty(value = "分页信息")
    private RowBounds rowBounds;

    public List<Condition> getCondition() {
        return condition;
    }

    public void setCondition(List<Condition> condition) {
        this.condition = condition;
    }

    public List<Order> getSort() {
        return sort;
    }

    public void setSort(List<Order> sort) {
        this.sort = sort;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public RowBounds getRowBounds() {
        return rowBounds;
    }

    public void setRowBounds(RowBounds rowBounds) {
        this.rowBounds = rowBounds;
    }



}
