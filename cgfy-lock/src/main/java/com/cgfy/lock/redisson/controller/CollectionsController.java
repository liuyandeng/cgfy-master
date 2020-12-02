package com.cgfy.lock.redisson.controller;
import com.cgfy.lock.redisson.service.CollectionsService;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 集合功能演示
 */
@RestController
@RequestMapping("/collections")
public class CollectionsController {

    @Autowired
    private CollectionsService service;
    @Autowired
    private RedissonClient client;

    @GetMapping("/rlist")
    public RList rlist() {
        return service.rlist(client);
    }

    @GetMapping("/rmap")
    public RMap rmap() {
        return service.rmap(client);
    }

}
