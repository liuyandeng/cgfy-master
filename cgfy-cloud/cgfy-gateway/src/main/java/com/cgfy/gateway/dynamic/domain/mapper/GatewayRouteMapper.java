package com.cgfy.gateway.dynamic.domain.mapper;

import com.cgfy.gateway.dynamic.domain.model.GatewayRoute;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网关动态路由表 Mapper
 */
@Mapper
public interface GatewayRouteMapper extends BaseMapper<GatewayRoute> {
}