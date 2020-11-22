package com.cgfy.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson连接配置
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient client() {
        Config config = new Config();
        config.useSingleServer()
                .setTimeout(1000000)
                .setAddress("redis://172.16.4.27:6379")
                .setPassword("123456");
        //默认连接上 127.0.0.1:6379
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

//    @Bean
//    public RedissonClient client() {
//        Config config = new Config();
//        config.useClusterServers()
//        .setScanInterval(2000)
//        .addNodeAddress("redis://172.16.4.27:7000")
//        .addNodeAddress("redis://172.16.4.27:7001")
//        .addNodeAddress("redis://172.16.4.27:7002")
//        .addNodeAddress("redis://172.16.4.27:7003")
//        .addNodeAddress("redis://172.16.4.27:7004")
//        .addNodeAddress("redis://172.16.4.27:7005")
//        .setPassword("123456");
//        RedissonClient redisson = Redisson.create(config);
//        return redisson;
//    }
}
