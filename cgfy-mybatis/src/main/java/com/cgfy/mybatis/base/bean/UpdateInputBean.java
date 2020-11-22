package com.cgfy.mybatis.base.bean;

import com.cgfy.mybatis.base.bean.select.Condition;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class UpdateInputBean<T> {

    @ApiModelProperty(value = "条件")
    private List<Condition> condition = new ArrayList<Condition>();
    
    @ApiModelProperty(value = "更新内容")
    private T value = null;

	public List<Condition> getCondition() {
		return condition;
	}

	public void setCondition(List<Condition> condition) {
		this.condition = condition;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
    
}
