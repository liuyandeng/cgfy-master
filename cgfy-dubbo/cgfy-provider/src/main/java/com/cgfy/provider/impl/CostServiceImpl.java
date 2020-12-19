package com.cgfy.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.cgfy.api.service.CostService;

/**
 * 花费服务
 */
@Service //使用dubbo提供的service注解，注册暴露服务
public class CostServiceImpl implements CostService {

    /**
     * 假设之前总花费了1000
     */
    private final Integer totalCost = 1000;

    /**
     * 之前消费的1000+新消费的金额
     * @param cost 新消费的金额
     * @return 返回总共消费的金额
     */
    @Override
    public Integer add(int cost) {
        return totalCost + cost;
    }
}
