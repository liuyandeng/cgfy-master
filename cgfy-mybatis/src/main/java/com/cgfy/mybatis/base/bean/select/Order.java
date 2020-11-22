package com.cgfy.mybatis.base.bean.select;

import io.swagger.annotations.ApiModelProperty;

public class Order {
    
    @ApiModelProperty(value = "方向", required = true)
    private Direction direction;
    
    @ApiModelProperty(value = "属性", required = true)
    private String property;
    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public String getProperty() {
        return property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    
    
}
