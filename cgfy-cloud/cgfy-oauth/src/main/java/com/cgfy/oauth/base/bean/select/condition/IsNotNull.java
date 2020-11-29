package com.cgfy.oauth.base.bean.select.condition;

import com.cgfy.oauth.base.bean.select.Condition;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 不为空
 * 
 * @author liuyandeng 2018.07.06
 */
public class IsNotNull extends Condition{
    
    @ApiModelProperty(value = "属性", required = true)
    private String property;
    
    public IsNotNull() {
    	
    }
    
    public IsNotNull(String property) {
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
        criteria.andIsNotNull(property);
    }
    
}
