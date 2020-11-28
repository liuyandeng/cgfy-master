package com.cgfy.oauth.base.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@JsonFilter("SelectFieldFilter")
public class BaseSelectField {
	
	@JsonIgnore
	private List<String> selectFieldList;

	public List<String> getSelectFieldList() {
		return selectFieldList;
	}

	public void setSelectFieldList(List<String> selectFieldList) {
		this.selectFieldList = selectFieldList;
	}

	
}
