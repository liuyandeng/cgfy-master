package com.cgfy.lock.redisson.service;


public interface StockService {

    int getByProduct(Integer productId);

    boolean decrease(Integer productId);

}
