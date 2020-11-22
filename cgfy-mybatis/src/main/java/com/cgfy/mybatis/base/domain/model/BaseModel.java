package com.cgfy.mybatis.base.domain.model;

import java.io.Serializable;

public interface BaseModel extends Serializable {
    default void setId(String id){};
}
