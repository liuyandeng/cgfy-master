package com.cgfy.redisson.redisson.service;


public interface StockService {

    int getByProduct(Integer productId);

    boolean decrease(Integer productId);

}
