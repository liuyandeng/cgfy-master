package com.cgfy.oauth.base.domain.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper基础接口类
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
