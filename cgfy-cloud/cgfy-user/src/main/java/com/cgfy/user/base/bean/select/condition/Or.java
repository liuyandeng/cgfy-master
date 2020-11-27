package com.cgfy.user.base.bean.select.condition;

import com.cgfy.user.base.bean.select.Condition;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 相等
 *
 * @author tjpanda88@sina.com
 */
public class Or extends Condition{

    @ApiModelProperty(value = "值", required = true)
    private String[] value;

    @ApiModelProperty(value = "属性", required = true)
    private String property;

    @ApiModelProperty(value = "值1", required = true)
    private String[] value1;



    @ApiModelProperty(value = "属性1", required = true)
    private String property1;

    public Or() {
    }

    public Or(String property, String[] value,String property1,String[] value1) {
        super();
        this.value = value;
        this.property = property;
        this.value1 = value1;
        this.property1 = property1;

    }

    public String[] getValue() {
        return value;
    }
    public void setValue(String[] value) {
        this.value = value;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    public String[] getValue1() {
        return value1;
    }

    public void setValue1(String[] value1) {
        this.value1 = value1;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }


    public void addCondition(Example example, Criteria criteria, Class clazz) {
        criteria = example.createCriteria();
        for (String value : value) {
            criteria.orEqualTo(property, getValue(value, clazz));
        }
        for (String valuex : value1) {
            criteria.orEqualTo(property1, getValue(valuex, clazz));
        }
        example.and(criteria);
    }

}
