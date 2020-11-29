package com.cgfy.gateway.dynamic.domain.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper基础接口类
 * 
 * @author liuyandeng 2018.08.17
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
