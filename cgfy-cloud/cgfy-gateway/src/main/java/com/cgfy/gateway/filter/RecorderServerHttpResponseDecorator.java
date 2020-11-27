package com.cgfy.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;

//解决response的body只能读一次的问题
public class RecorderServerHttpResponseDecorator extends ServerHttpResponseDecorator {

    public RecorderServerHttpResponseDecorator(ServerHttpResponse delegate) {
        super(delegate);
    }

}