#网关
## 项目:
启动后访问:http://localhost:8840/index.html



## 限流

限流可以认为服务降级的一种，限流就是限制系统的输入和输出流量已达到保护系统 的目的。 一般来说系统的吞吐量是可以被测算的，为了保证系统的稳定运行，一旦达到的需要限制的阈值，就需要限制流量并采取一些措施以完成限制流量的目的。 比如：延迟处理，拒绝处理，或者部分拒绝处理等等
### 限流方法
#### 计数器
控制单位时间内的请求数量

#### Leaky Bucket 漏桶
规定固定容量的桶, 有水进入, 有水流出. 对于流进的水我们无法估计进来的数量、速度,  对于流出的水我们可以控制速度.

#### Token Bucket 令牌桶
规定固定容量的桶, token 以固定速度往桶内填充, 当桶满时 token 不会被继续放入, 每过来一个请求把 token 从桶中移除, 如果桶中没有 token 不能请求

####工作中的使用
spring cloud gateway, 默认使用redis进行限流, 笔者一般只是修改修改参数属于拿来即用. 并没有去从头实现上述那些算法

sentinel,通过配置来控制每个url的流量

## 熔断
Sentinel 是阿里中间件团队研发的面向分布式服务架构的轻量级高可用流量控制组件，于 2018 年 7 月正式开源。Sentinel 主要以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度来帮助用户提升服务的稳定性。大家可能会问：Sentinel 和之前经常用到的熔断降级库
 
Netflix Hystrix  有什么异同呢？本文将从资源模型和执行模型、隔离设计、熔断降级、实时指标统计设计等角度将 Sentinel 和 Hystrix 进行对比，希望在面临技术选型的时候，对各位开发者能有所帮助。

Sentinel 项目地址：https://github.com/alibaba/Sentinel
总体说明
先来看一下 Hystrix 的官方介绍：

    Hystrix is a library that helps you control the interactions between these distributed  
    services by adding latency tolerance and fault tolerance logic. Hystrix does this by isolating points
    of access between the services, stopping cascading failures across them, and providing fallback options, 
    all of which improve your system’s overall resiliency.

可以看到 Hystrix 的关注点在于以隔离和熔断为主的容错机制，超时或被熔断的调用将会快速失败，并可以提供 fallback 机制。

而 Sentinel 的侧重点在于：

多样化的流量控制熔断降级

系统负载保护

实时监控和控制台

**总结:**

![avatar](https://liuyandeng.github.io/website/img/sentinel_hystrix.png)





