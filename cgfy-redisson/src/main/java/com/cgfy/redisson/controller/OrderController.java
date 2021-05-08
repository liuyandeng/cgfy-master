package com.cgfy.redisson.controller;


import com.cgfy.redisson.bean.OrderDto;
import com.cgfy.redisson.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 这应该是一个业务网关服务
     */
    @PostMapping("/create")
    public boolean create(@RequestBody OrderDto orderRequestVO,
                          @RequestHeader(name = "userid") Integer userId) {
        orderService.save(userId, orderRequestVO.getProductId());
        return true;
    }

}
