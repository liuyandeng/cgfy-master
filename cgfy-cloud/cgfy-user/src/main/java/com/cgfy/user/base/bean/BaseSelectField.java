package com.cgfy.user.base.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Map;

@JsonFilter("SelectFieldFilter")
@Data
public class BaseSelectField {

	@JsonIgnore
	private List<String> selectFieldList;
	//业务附件,没有时为空
	//private List<Map<String, Object>> attachments;


	
}
