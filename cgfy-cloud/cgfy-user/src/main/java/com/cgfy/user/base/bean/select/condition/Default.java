package com.cgfy.user.base.bean.select.condition;

import com.cgfy.user.base.bean.select.Condition;

import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 不相等相等
 * 
 * @author tjpanda88@sina.com
 */
public class Default extends Condition{

    @ApiModelProperty(value = "值", required = true)
    private String value;
    
    @ApiModelProperty(value = "属性", required = true)
    private String property;
    
    public Default() {
    	
    }
    
    public Default(String property, String value) {
        super();
        this.value = value;
        this.property = property;
    }
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    
    public void addCondition(Example example, Criteria criteria, Class clazz) {
       // criteria.andNotEqualTo(property, getValue(value, clazz));
    	if(property.equals("title")) {
    		criteria.andLike(property, "%" +getValue(value, clazz)+ "%");
    	}else if(property.equals("type")) {
    		criteria.andEqualTo(property, getValue(value, clazz));
    	}else if(property.equals("status")) {
    		criteria.andEqualTo(property, getValue(value, clazz));
    	}
    }
    
}
