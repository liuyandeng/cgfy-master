package com.cgfy.user.base.bean.select.condition;

import com.cgfy.user.base.bean.select.Condition;

import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**O
 * 大于等于
 * 
 * @author tjpanda88@sina.com
 */
public class GreatEqual extends Condition{

    @ApiModelProperty(value = "值", required = true)
    private String value;
    
    @ApiModelProperty(value = "属性", required = true)
    private String property;
    
    public GreatEqual() {
    	
    }
    
    public GreatEqual(String property, String value) {
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
        criteria.andGreaterThanOrEqualTo(property, getValue(value, clazz));
    }
}
