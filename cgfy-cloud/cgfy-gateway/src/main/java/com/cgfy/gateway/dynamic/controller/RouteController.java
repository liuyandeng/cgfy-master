package com.cgfy.gateway.dynamic.controller;

import com.cgfy.gateway.dynamic.bean.GatewayRouteBean;
import com.cgfy.gateway.dynamic.config.GatewayServiceHandler;
import com.cgfy.gateway.dynamic.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/jbdp-route")
public class RouteController {

	@Autowired
	private GatewayServiceHandler gatewayServiceHandler;

	@Autowired
	private GatewayRouteService gatewayRouteService;

	@Autowired
	private RouteDefinitionLocator routeDefinitionLocator;

	@Autowired
	private RouteLocator routeLocator;

	/**
	 * 刷新路由配置
	 *
	 * @return
	 */
	@GetMapping("/refresh")
	public String refresh() throws Exception {
		return this.gatewayServiceHandler.loadRouteConfig();
	}

	@PostMapping("/query")
	public Mono<List<GatewayRouteBean>> query(
			@RequestParam(value="serviceId", required=false) String serviceId,
			@RequestParam(value="routePath", required=false) String routePath) {
		Mono<Map<String, RouteDefinition>> routeDefs = this.routeDefinitionLocator.getRouteDefinitions()
				.collectMap(RouteDefinition::getId);
		Mono<List<Route>> routes = this.routeLocator.getRoutes().collectList();

		return Mono.zip(routeDefs, routes).map(tuple -> {
			List<GatewayRouteBean> result = new ArrayList<>();
			List<GatewayRouteBean> allRoutes = this.getAllRoutes(tuple.getT1(), tuple.getT2());

			boolean idMatch = true, pathMatch = true;
			for (GatewayRouteBean route: allRoutes) {
				if(serviceId!=null && serviceId.length()>0) {
					idMatch = Pattern.matches(".*"+serviceId+".*", route.getServiceId());
				}
				if(routePath!=null && routePath.length()>0) {
					pathMatch = Pattern.matches(".*"+routePath+".*", route.getPredicates());
				}
				if(idMatch && pathMatch)
					result.add(route);
			}

			return result;
		});
	}

	/**
	 * 增加路由记录
	 *
	 * @param gatewayRouteDto
	 * @return
	 */
	@PostMapping("/add")
	public Mono<String> add(@RequestBody GatewayRouteBean gatewayRouteDto) throws Exception {

		Mono<Map<String, RouteDefinition>> routeDefs = this.routeDefinitionLocator
				.getRouteDefinitions()
				.collectMap(RouteDefinition::getId);
		Mono<List<Route>> routes = this.routeLocator.getRoutes().collectList();

		return Mono.zip(routeDefs, routes).map(tuple -> {
			List<GatewayRouteBean> allRoutes = this.getAllRoutes(tuple.getT1(), tuple.getT2());
			for(GatewayRouteBean route: allRoutes) {
				if(gatewayRouteDto.getId().equals(route.getId()))
					return "路由ID重复" ;
				if(gatewayRouteDto.getServiceId().equals(route.getServiceId()))
					return "服务ID重复" ;
				if(gatewayRouteDto.getUri().equals(route.getUri()))
					return "路由uri重复" ;
				if(gatewayRouteDto.getPredicates().equals(route.getPredicates()))
					return "路由转发路径重复" ;
			}

			gatewayRouteService.add(gatewayRouteDto);
			gatewayServiceHandler.loadRouteConfig();

			return "success";
		});

	}

	@PostMapping("/update")
	public Mono<String> update(@RequestBody GatewayRouteBean gatewayRouteDto) throws Exception {

		Mono<Map<String, RouteDefinition>> routeDefs = this.routeDefinitionLocator
				.getRouteDefinitions()
				.collectMap(RouteDefinition::getId);
		Mono<List<Route>> routes = this.routeLocator.getRoutes().collectList();

		return Mono.zip(routeDefs, routes).map(tuple -> {
			List<GatewayRouteBean> allRoutes = this.getAllRoutes(tuple.getT1(), tuple.getT2());
			for(GatewayRouteBean route: allRoutes) {
				if(gatewayRouteDto.getId().equals(route.getId()))
					continue;
				if(gatewayRouteDto.getServiceId().equals(route.getServiceId()))
					return "服务ID重复" ;
				if(gatewayRouteDto.getUri().equals(route.getUri()))
					return "路由uri重复" ;
				if(gatewayRouteDto.getPredicates().equals(route.getPredicates()))
					return "路由转发路径重复" ;
			}
			try {
				URI uri = UriComponentsBuilder.fromUriString("lb://" + gatewayRouteDto.getUri()).build().toUri();
			} catch (Exception e) {
				return "路由uri格式错误" ;
			}

			gatewayRouteService.update(gatewayRouteDto);
			gatewayServiceHandler.loadRouteConfig();

			return "success";
		});
	}

	@PostMapping("/delete/{id}")
	public Mono<String> delete(@PathVariable String id) throws Exception {
		Mono<Map<String, RouteDefinition>> routeDefs = this.routeDefinitionLocator
				.getRouteDefinitions()
				.collectMap(RouteDefinition::getId);
		Mono<List<Route>> routes = this.routeLocator.getRoutes().collectList();

		return Mono.zip(routeDefs, routes).map(tuple -> {
			List<GatewayRouteBean> allRoutes = this.getAllRoutes(tuple.getT1(), tuple.getT2());
			for(GatewayRouteBean route: allRoutes) {
				if(route.getId().equals(id)) {
					gatewayRouteService.delete(id);
					gatewayServiceHandler.deleteRoute(id);
					return "success";
				}
			}
			return "当前ID网关不存在";
		});
	}


	private List<GatewayRouteBean> getAllRoutes(
			Map<String, RouteDefinition> defs,
			List<Route> routeList) {
		List<GatewayRouteBean> allRoutes = new ArrayList<>();

		routeList.forEach(route -> {
			String pkey = null;
			GatewayRouteBean gatewayRoute = new GatewayRouteBean();
			gatewayRoute.setId(route.getId());

			if (defs.containsKey(route.getId())) {
				RouteDefinition routeDefine = defs.get(route.getId());
				gatewayRoute.setUri(routeDefine.getUri().getHost());
				gatewayRoute.setServiceId(routeDefine.getUri().getHost());

				if(routeDefine.getPredicates()!=null && routeDefine.getPredicates().size()>0
						&& routeDefine.getPredicates().get(0).getArgs().keySet().iterator().hasNext()) {
					pkey = routeDefine.getPredicates().get(0).getArgs().keySet().iterator().next();
					gatewayRoute.setPredicates(routeDefine.getPredicates().get(0).getArgs().get(pkey));
				}
				if(routeDefine.getFilters()!=null && routeDefine.getFilters().size()>0 &&
						routeDefine.getFilters().get(0).getArgs().keySet().iterator().hasNext()) {
					pkey = routeDefine.getFilters().get(0).getArgs().keySet().iterator().next();
					gatewayRoute.setFilters(routeDefine.getFilters().get(0).getArgs().get(pkey));
				}
			} else {
				gatewayRoute.setUri(route.getUri().getHost());
				gatewayRoute.setServiceId(route.getUri().getHost());
				gatewayRoute.setPredicates(route.getPredicate().toString());
			}

			allRoutes.add(gatewayRoute);
		});

		return allRoutes;
	}

}