package com.cgfy.user.base.bean;

import java.util.ArrayList;
import java.util.List;

import com.cgfy.user.base.bean.select.Condition;

import io.swagger.annotations.ApiModelProperty;

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
