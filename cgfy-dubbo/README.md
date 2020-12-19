# dubbo
dubbo本身并不是一个服务软件。它其实就是一个jar包能够帮你的java程序连接到zookeeper，并利用zookeeper消费、提供服务。所以你不用在Linux上启动什么dubbo服务。

项目案例说明：consumer产品购买,调用provider,传入消费的金额,返回产品总共消费的金额
- 1.cgfy-api:公共接口层（model，service，exception…）,定义了成本增加接口add
- 2.cgfy-provider:提供者,对成本增加接口add的实现
- 3.cgfy-consumer:消费者,引入api模块调用add接口

consumer仅仅引入api模块的话,是无法进行调用的,因为add接口的实现是provider,我们并没有引入，而且实际他可能还在别的服务器中。

dubbo 2.6以前的版本引入zkclient操作zookeeper,dubbo 2.6及以后的版本引入curator操作zookeeper



## windows下搭建zookeeper环境
Windows环境下安装:https://blog.csdn.net/qq877507054/article/details/110739854

## 测试dubbo远程服务调用
启动consumer,provider项目

访问消费者启动consumer的add接口,会调用提供者cgfy-provider的add接口,调用成功,

在浏览器中http://localhost:8062/add?a=100 , 出现"该产品总共消费:1100",即为调用成功

## 安装控制台 dubbo-admin
但是为了让用户更好的管理监控众多的dubbo服务，官方提供了一个可视化的监控程序，不过这个监控即使不装也不影响使用。
- 1.dubbo运维master分支地址: https://github.com/apache/incubator-dubbo-ops/tree/master

目录:
```
    dubbo-admin:控制台
    dubbo-monitor-simple:简单的监控中心
    dubbo-registry-simple:简单的注册中心
 ```
- 2.这里需要修改一个配置进入dubbo-admin\src\main\resources\application.properties

```text
#服务器端口
server.port=7001
spring.velocity.cache=false
spring.velocity.charset=UTF-8
spring.velocity.layout-url=/templates/default.vm
spring.messages.fallback-to-system-locale=false
spring.messages.basename=i18n/message
#访问的密码配置
spring.root.password=root
spring.guest.password=guest

#zookeeper地址
dubbo.registry.address=zookeeper://127.0.0.1:2181

```

- 3.在dubbo-admin目录下 ，进入cmd窗口执行,mvn claen package命令,打包项目
- 4.然后进入dubbo-admin的target,进入cmd窗口执行java -jar dubbo-admin-0.0.1-SNAPSHOT.jar命令,运行项目
- 5.启动成功后 浏览器访问http://localhost:7001 账号/密码:root/root

![dubbo-admin UI](http://liuyandeng.gitee.io/gitpages/img/dubbo/ops/dubbo-admin.png)
![service](http://liuyandeng.gitee.io/gitpages/img/dubbo/ops/service.png)
![app](http://liuyandeng.gitee.io/gitpages/img/dubbo/ops/app.png)

## 安装监控中间 dubbo-monitor
主要用来统计服务的调用次数和调用时间，务消费者和提供者，在内存中累计调用次数和调用时间,
定时每分钟发送一次统计数据到监控中心，监控中心则使用数据绘制图表来显示。
Simple Monitor 挂掉不会影响到 Consumer 和 Provider 之间的调用，所以用于生产环境不会有风险。


- 1.进入dubbo-monitor-simple\src\main\resources\application.properties修改相应配置
```html

dubbo.container=log4j,spring,registry,jetty-monitor
dubbo.application.name=simple-monitor
dubbo.application.owner=dubbo
#dubbo.registry.address=multicast://224.5.6.7:1234
dubbo.registry.address=zookeeper://127.0.0.1:2181
#dubbo.registry.address=redis://127.0.0.1:6379
#dubbo.registry.address=dubbo://127.0.0.1:9090
#监控中心通讯端口,我用的是7060
dubbo.protocol.port=7070
#监控中心页面访问端口,我用的是8070
dubbo.jetty.port=8080
dubbo.jetty.directory=${user.home}/monitor
dubbo.charts.directory=${user.home}/monitor/charts
dubbo.statistics.directory=${user.home}/monitor/statistics
dubbo.log4j.file=logs/dubbo-monitor-simple.log
dubbo.log4j.level=WARN

```
- 2.mvn claen package
- 3.解压dubbo-monitor-simple-2.0.0-assembly.tar.gz,进入assembly.bin目录中,点击start.bat
- 4.http://localhost:8080
![dubbo-monitor](http://liuyandeng.gitee.io/gitpages/img/dubbo/ops/dubbo-monitor.png)

### 附录：
1.各个软件版本对应
<table width="100%">
<thead>
<tr>
<th>versions</th>
<th>Java</th>
<th>Spring Boot</th>
<th>Dubbo</th>
</tr>
</thead>
<tbody>
<tr>
<td><code>0.2.0</code></td>
<td>1.8+</td>
<td><code>2.0.x</code></td>
<td><code>2.6.2</code> +</td>
</tr>
<tr>
<td><code>0.1.1</code></td>
<td>1.7+</td>
<td><code>1.5.x</code></td>
<td><code>2.6.2</code> +</td>
</tr>
</tbody>
</table>

# [分布式基础理论](https://blog.csdn.net/qq_39736103/article/details/82796563)
## 什么是分布式系统？
分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统,分布式系统（distributed system）是建立在网络之上的软件系统。

随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，亟需一个治理系统确保架构有条不紊的演进。
## 发展演变
### 单一应用架构

当网站流量很小时，只需一个应用，将所有功能都部署在一起，以减少部署节点和成本。此时，用于简化增删改查工作量的数据访问框架(ORM)是关键。

适用于小型网站，小型管理系统，将所有功能都部署到一个功能里，简单易用。

缺点： 
- 1、性能扩展比较难
- 2、协同开发问题
- 3、不利于升级维护

### 垂直应用架构

当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的Web框架(MVC)是关键。

通过切分业务来实现各个模块独立部署，降低了维护和部署的难度，团队各司其职更易管理，性能扩展也更方便，更有针对性。

缺点： 公用模块无法重复利用，开发性的浪费

### 分布式服务架构

当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。
此时，用于提高业务复用及整合的分布式服务框架(RPC)是关键。

### 流动计算架构

当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。
此时，用于提高机器利用率的资源调度和治理中心(SOA)[ Service Oriented Architecture]是关键。

## RPC
### 什么叫RPC
RPC【Remote Procedure Call】是指远程过程调用，是一种进程间通信方式，他是一种技术的思想，而不是规范。
它允许程序调用另一个地址空间（通常是共享网络的另一台机器上）的过程或函数，而不用程序员显式编码这个远程调用的细节。
即程序员无论是调用本地的还是远程的函数，本质上编写的调用代码基本相同。
### RPC基本原理
RPC两个核心模块：通讯，序列化。

# dubbo核心概念
## 简介
Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC框架，
它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。
官网：http://dubbo.apache.org/
源码地址:https://github.com/apache/incubator-dubbo
## 基本概念
![base](http://liuyandeng.gitee.io/gitpages/img/dubbo/base.png)

- 服务提供者（Provider）：暴露服务的服务提供方，服务提供者在启动时，向注册中心注册自己提供的服务。
- 服务消费者（Consumer）: 调用远程服务的服务消费方，服务消费者在启动时，向注册中心订阅自己所需的服务，
  服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
- 注册中心（Registry）：注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者
- 监控中心（Monitor）：服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心

**调用关系说明**

- 1.服务容器负责启动，加载，运行服务提供者。
- 2.服务提供者在启动时，向注册中心注册自己提供的服务。
- 3.服务消费者在启动时，向注册中心订阅自己所需的服务。
- 4.注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
- 5.服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
- 6.服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。


# 配置原则
dubbo推荐在Provider上尽量多配置Consumer端属性：
```html
1、作服务的提供者，比服务使用方更清楚服务性能参数，如调用的超时时间，合理的重试次数，等等
2、在Provider配置后，Consumer不配置则会使用Provider的配置值，即Provider配置可以作为Consumer的缺省值。否则，Consumer会使用Consumer端的全局设置，这对于Provider不可控的，并且往往是不合理的
```
配置的覆盖规则：
- 方法级配置别优于接口级别，即小Scope优先
- Consumer端配置优于Provider配置 优于 全局配置，
- 最后是Dubbo Hard Code的配置值（见配置文档）

# 高可用
## 1.zookeeper宕机与dubbo直连
现象：zookeeper注册中心宕机，还可以消费dubbo暴露的服务。

原因：
```html
健壮性
1.监控中心宕掉不影响使用，只是丢失部分采样数据
2.数据库宕掉后，注册中心仍能通过缓存提供服务列表查询，但不能注册新服务
3.注册中心对等集群，任意一台宕掉后，将自动切换到另一台
4.注册中心全部宕掉后，服务提供者和服务消费者仍能通过本地缓存通讯
5.服务提供者无状态，任意一台宕掉后，不影响使用
6.服务提供者全部宕掉后，服务消费者应用将无法使用，并无限次重连等待服务提供者恢复
```

高可用：通过设计，减少系统不能提供服务的时间；

## 2.集群下dubbo负载均衡配置
在集群负载均衡时，Dubbo 提供了多种均衡策略，缺省为 random 随机调用。

负载均衡策略
```html
Random LoadBalance
随机，按权重设置随机概率。
在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。

RoundRobin LoadBalance
轮循，按公约后的权重设置轮循比率。
存在慢的提供者累积请求的问题，比如：第二台机器很慢，但没挂，当请求调到第二台时就卡在那，久而久之，所有请求都卡在调到第二台上。

LeastActive LoadBalance
最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。

ConsistentHash LoadBalance
一致性 Hash，相同参数的请求总是发到同一提供者。
当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。算法参见：http://en.wikipedia.org/wiki/Consistent_hashing
缺省只对第一个参数 Hash，如果要修改，请配置 <dubbo:parameter key="hash.arguments" value="0,1" />
缺省用 160 份虚拟节点，如果要修改，请配置 <dubbo:parameter key="hash.nodes" value="320" />
```

## 3.整合hystrix，服务熔断与降级处理
### 1、服务降级
什么是服务降级？

当服务器压力剧增的情况下，根据实际业务情况及流量，对一些服务和页面有策略的不处理或换种简单的方式处理，从而释放服务器资源以保证核心交易正常运作或高效运作。

可以通过服务降级功能临时屏蔽某个出错的非关键服务，并定义降级后的返回策略。

向注册中心写入动态配置覆盖规则：
```html
RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://10.20.153.10:2181"));
registry.register(URL.valueOf("override://0.0.0.0/com.foo.BarService?category=configurators&dynamic=false&application=foo&mock=force:return+null"));

```
其中：

- mock=force:return+null 表示消费方对该服务的方法调用都直接返回 null 值，不发起远程调用。用来屏蔽不重要服务不可用时对调用方的影响。
- 还可以改为 mock=fail:return+null 表示消费方对该服务的方法调用在失败后，再返回 null 值，不抛异常。用来容忍不重要服务不稳定时对调用方的影响。
### 集群容错
在集群调用失败时，Dubbo 提供了多种容错方案，缺省为 failover 重试。
集群容错模式
```html
Failover Cluster
失败自动切换，当出现失败，重试其它服务器。通常用于读操作，但重试会带来更长延迟。可通过 retries="2" 来设置重试次数(不含第一次)。
重试次数配置如下：
<dubbo:service retries="2" />
或
<dubbo:reference retries="2" />
或
<dubbo:reference>
    <dubbo:method name="findFoo" retries="2" />
</dubbo:reference>

Failfast Cluster
快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。

Failsafe Cluster
失败安全，出现异常时，直接忽略。通常用于写入审计日志等操作。

Failback Cluster
失败自动恢复，后台记录失败请求，定时重发。通常用于消息通知操作。

Forking Cluster
并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 forks="2" 来设置最大并行数。

Broadcast Cluster
广播调用所有提供者，逐个调用，任意一台报错则报错 [2]。通常用于通知所有提供者更新缓存或日志等本地资源信息。

集群模式配置
按照以下示例在服务提供方和消费方配置集群模式
<dubbo:service cluster="failsafe" />
或
<dubbo:reference cluster="failsafe" />
```
### 整合hystrix
Hystrix 旨在通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力。Hystrix具备拥有回退机制和断路器功能的线程和信号隔离，请求缓存和请求打包，以及监控和配置等功能

**1、配置spring-cloud-starter-netflix-hystrix**

spring boot官方提供了对hystrix的集成，直接在pom.xml里加入依赖：
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>1.4.4.RELEASE</version>
</dependency>
```
然后在Application类上增加@EnableHystrix来启用hystrix starter：
```java
@SpringBootApplication
@EnableHystrix
public class ProviderApplication {
```
**2、配置Provider端**

在Dubbo的Provider上增加@HystrixCommand配置，这样子调用就会经过Hystrix代理。
```java
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    @HystrixCommand(commandProperties = {
    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") })
    @Override
    public String sayHello(String name) {
        // System.out.println("async provider received: " + name);
        // return "annotation: hello, " + name;
        throw new RuntimeException("Exception to show hystrix enabled.");
    }
}
```
**3、配置Consumer端**

对于Consumer端，则可以增加一层method调用，并在method上配置@HystrixCommand。当调用出错时，会走到fallbackMethod = "reliable"的调用里。
```java
@Reference(version = "1.0.0")
private HelloService demoService;
@HystrixCommand(fallbackMethod = "reliable")
public String doSayHello(String name) {
    return demoService.sayHello(name);
}
public String reliable(String name) {
    return "hystrix fallback value";
}
```
# dubbo原理  
## 1.RPC原理
```html
一次完整的RPC调用流程（同步调用，异步另说）如下：
1）服务消费方（client）调用以本地调用方式调用服务；
2）client stub接收到调用后负责将方法、参数等组装成能够进行网络传输的消息体；
3）client stub找到服务地址，并将消息发送到服务端；
4）server stub收到消息后进行解码；
5）server stub根据解码结果调用本地的服务；
6）本地服务执行并将结果返回给server stub；
7）server stub将返回结果打包成消息并发送至消费方；
8）client stub接收到消息，并进行解码；
9）服务消费方得到最终结果。
RPC框架的目标就是要2~8这些步骤都封装起来，这些细节对用户来说是透明的，不可见的。
```
## 2.netty通信原理
Netty是一个异步事件驱动的网络应用程序框架， 用于快速开发可维护的高性能协议服务器和客户端。它极大地简化并简化了TCP和UDP套接字服务器等网络编程。

BIO：(Blocking IO)
![base](http://liuyandeng.gitee.io/gitpages/img/dubbo/BIO.png)

Selector 一般称 为选择器 ，也可以翻译为 多路复用器，

Connect（连接就绪）、Accept（接受就绪）、Read（读就绪）、Write（写就绪）

Netty基本原理：
![base](http://liuyandeng.gitee.io/gitpages/img/dubbo/Netty.png)

## 3.dubbo原理
**dubbo原理 -框架设计**
- config 配置层：对外配置接口，以 ServiceConfig, ReferenceConfig 为中心，可以直接初始化配置类，也可以通过 spring 解析配置生成配置类
- proxy 服务代理层：服务接口透明代理，生成服务的客户端 Stub 和服务器端 Skeleton, 以 ServiceProxy 为中心，扩展接口为 ProxyFactory
- registry 注册中心层：封装服务地址的注册与发现，以服务 URL 为中心，扩展接口为 RegistryFactory, Registry, RegistryService
- cluster 路由层：封装多个提供者的路由及负载均衡，并桥接注册中心，以 Invoker 为中心，扩展接口为 Cluster, Directory, Router, LoadBalance
- monitor 监控层：RPC 调用次数和调用时间监控，以 Statistics 为中心，扩展接口为 MonitorFactory, Monitor, MonitorService
- protocol 远程调用层：封装 RPC 调用，以 Invocation, Result 为中心，扩展接口为 Protocol, Invoker, Exporter
- exchange 信息交换层：封装请求响应模式，同步转异步，以 Request, Response 为中心，扩展接口为 Exchanger, ExchangeChannel, ExchangeClient, ExchangeServer
- transport 网络传输层：抽象 mina 和 netty 为统一接口，以 Message 为中心，扩展接口为 Channel, Transporter, Client, Server, Codec
- serialize 数据序列化层：可复用的一些工具，扩展接口为 Serialization, ObjectInput, ObjectOutput, ThreadPool

**dubbo原理 -启动解析、加载配置信息**
 
**dubbo原理 -服务暴露**
 
**dubbo原理 -服务引用**
 
**dubbo原理 -服务调用**
 























