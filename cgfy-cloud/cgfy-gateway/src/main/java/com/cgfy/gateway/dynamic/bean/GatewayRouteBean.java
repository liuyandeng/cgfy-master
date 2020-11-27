package com.cgfy.gateway.dynamic.bean;

import lombok.Data;

@Data
public class GatewayRouteBean {

    private String id;

    private String serviceId;

    private String uri;

    private String predicates;

    private String filters;

    private String orders;

    private String remarks;

}