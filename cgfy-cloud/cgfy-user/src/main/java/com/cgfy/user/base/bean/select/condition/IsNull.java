package com.cgfy.user.base.bean.select.condition;

import com.cgfy.user.base.bean.select.Condition;

import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 不为空
 * 
 * @author qiucw 2018.07.06
 */
public class IsNull extends Condition{
    
    @ApiModelProperty(value = "属性", required = true)
    private String property;
    
    public IsNull() {
    	
    }
    
    public IsNull(String property) {
        super();
        this.property = property;
    }
    
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    
    public void addCondition(Example example, Criteria criteria, Class clazz) {
        criteria.andIsNull(property);
    }
    
}
