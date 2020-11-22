package com.cgfy.redisson.service;


import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

public interface CollectionsService {
    public RList rlist(RedissonClient client);
    public RMap rmap(RedissonClient client);
}
