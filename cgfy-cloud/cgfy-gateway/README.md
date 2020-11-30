# 网关
## 项目:
启动后访问:http://localhost:8840/index.html


## 网关限流
spring cloud gateway 默认使用redis进行限流, 笔者一般只是修改修改参数属于拿来即用. 并没有去从头实现上述那些算法.


**xml:**

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
    </dependency>

**json:**

    spring:
      cloud:
        gateway:
          routes:
            - id: requestratelimiter_route
              uri: lb://pigx-upms
              order: 10000
              predicates:
                - Path=/admin/**
              filters:
                - name: RequestRateLimiter
                  args:
                    redis-rate-limiter.replenishRate: 1 # 令牌桶的容积
                    redis-rate-limiter.burstCapacity: 3 # 流速 每秒
                    key-resolver: "#{@remoteAddrKeyResolver}" #SPEL表达式去的对应的bean
                - StripPrefix=1

**java:**

    @Bean
    KeyResolver remoteAddrKeyResolver(){
       return exchange->Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }
