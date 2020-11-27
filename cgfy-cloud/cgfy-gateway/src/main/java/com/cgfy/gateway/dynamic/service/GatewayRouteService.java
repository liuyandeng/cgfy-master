package com.cgfy.gateway.dynamic.service;

import com.cgfy.gateway.dynamic.bean.GatewayRouteBean;
import com.cgfy.gateway.dynamic.domain.mapper.SysGatewayRouteMapper;
import com.cgfy.gateway.dynamic.domain.model.SysGatewayRoute;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GatewayRouteService {

	@Autowired
	private SysGatewayRouteMapper gatewayRouteMapper;

	public Integer add(GatewayRouteBean gatewayRouteDto) {
		SysGatewayRoute gatewayRoute = new SysGatewayRoute();
		BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
		gatewayRoute.setCreateDate(new Date());
		gatewayRoute.setCreateId("");
		return gatewayRouteMapper.insertSelective(gatewayRoute);
	}

	public Integer update(GatewayRouteBean gatewayRouteDto) {
		SysGatewayRoute gatewayRoute = new SysGatewayRoute();
		BeanUtils.copyProperties(gatewayRouteDto, gatewayRoute);
		gatewayRoute.setUpdateDate(new Date());
		gatewayRoute.setUpdateId("");
		return gatewayRouteMapper.updateByPrimaryKeySelective(gatewayRoute);
	}

	public Integer delete(String id) {
		return gatewayRouteMapper.deleteByPrimaryKey(id);
	}

}