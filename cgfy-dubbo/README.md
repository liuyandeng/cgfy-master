# 一.dubbo的简单集成
dubbo本身并不是一个服务软件。它其实就是一个jar包能够帮你的java程序连接到zookeeper，并利用zookeeper消费、提供服务。所以你不用在Linux上启动什么dubbo服务。

项目案例说明：consumer产品购买,调用provider,传入消费的金额,返回产品总共消费的金额
- 1.cgfy-api:公共接口层（model，service，exception…）,定义了成本增加接口add
- 2.cgfy-provider:提供者,对成本增加接口add的实现
- 3.cgfy-consumer:消费者,引入api模块调用add接口

consumer仅仅引入api模块的话,是无法进行调用的,因为add接口的实现是provider,我们并没有引入，而且实际他可能还在别的服务器中。

dubbo 2.6以前的版本引入zkclient操作zookeeper,dubbo 2.6及以后的版本引入curator操作zookeeper



## 1.1 windows下搭建zookeeper环境
Windows环境下安装:https://blog.csdn.net/qq877507054/article/details/110739854

## 1.2 测试dubbo远程服务调用
启动consumer,provider项目

访问消费者启动consumer的add接口,会调用提供者cgfy-provider的add接口,调用成功,

在浏览器中http://localhost:8062/add?a=100 , 出现"该产品总共消费:1100",即为调用成功

## 1.3 安装控制台 dubbo-admin
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

## 1.4 安装监控中间 dubbo-monitor
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

## 附录：
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

# 二.[分布式基础理论](https://blog.csdn.net/qq_39736103/article/details/82796563)
## 2.1 什么是分布式系统？
分布式系统是若干独立计算机的集合，这些计算机对于用户来说就像单个相关系统,分布式系统（distributed system）是建立在网络之上的软件系统。

随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，亟需一个治理系统确保架构有条不紊的演进。
## 2.2 发展演变
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

## 2.3 RPC
### 什么叫RPC
RPC【Remote Procedure Call】是指远程过程调用，是一种进程间通信方式，他是一种技术的思想，而不是规范。
它允许程序调用另一个地址空间（通常是共享网络的另一台机器上）的过程或函数，而不用程序员显式编码这个远程调用的细节。
即程序员无论是调用本地的还是远程的函数，本质上编写的调用代码基本相同。
### RPC基本原理
RPC两个核心模块：通讯，序列化。


### RPC TCP协议

RPC(Remote Procedure  Call)远程过程调用，简单的理解是一个节点请求另一个节点提供的服务。它的工作流程是这样的：

- 1. 执行客户端调用语句，传送参数 
- 2. 调用本地系统发送网络消息 
- 3. 消息传送到远程主机 
- 4. 服务器得到消息并取得参数 
- 5. 根据调用请求以及参数执行远程过程（服务） 
- 6. 执行过程完毕，将结果返回服务器句柄 
- 7. 服务器句柄返回结果，调用远程主机的系统网络服务发送结果 
- 8. 消息传回本地主机 
- 9. 客户端句柄由本地主机的网络服务接收消息 
- 10. 客户端接收到调用语句返回的结果数据


# 三.dubbo核心概念
## 3.1 简介
Apache Dubbo (incubating) |ˈdʌbəʊ| 是一款高性能、轻量级的开源Java RPC框架，
它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现。
官网：http://dubbo.apache.org/
源码地址:https://github.com/apache/incubator-dubbo
## 3.2 基本概念
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


## 3.3 配置原则
dubbo推荐在Provider上尽量多配置Consumer端属性：
```html
1、作服务的提供者，比服务使用方更清楚服务性能参数，如调用的超时时间，合理的重试次数，等等
2、在Provider配置后，Consumer不配置则会使用Provider的配置值，即Provider配置可以作为Consumer的缺省值。否则，Consumer会使用Consumer端的全局设置，这对于Provider不可控的，并且往往是不合理的
```
配置的覆盖规则：
- 方法级配置别优于接口级别，即小Scope优先
- Consumer端配置优于Provider配置 优于 全局配置，
- 最后是Dubbo Hard Code的配置值（见配置文档）

## 3.4高可用

### 负载均衡
在集群负载均衡时，Dubbo 提供了多种均衡策略，缺省为 random 随机调用。

负载均衡策略
```html
1.Random LoadBalance
随机，按权重设置随机概率。
默认情况下，dubbo 是 RandomLoadBalance ，即随机调用实现负载均衡，可以对 provider 不同实例设置不同的权重，会按照权重来负载均衡，权重越大分配流量越高，一般就用这个默认 的就可以了。

2.RoundRobin LoadBalance
轮循，按公约后的权重设置轮循比率。
这个的话默认就是均匀地将流量打到各个机器上去，但是如果各个机器的性能不一样，容易导 致性能差的机器负载过高。所以此时需要调整权重，让性能差的机器承载权重小一些，流量少 一些。
举个栗子。
跟运维同学申请机器，有的时候，我们运气好，正好公司资源比较充足，刚刚有一批热气腾  腾、刚刚做好的虚拟机新鲜出炉，配置都比较高：8 核 + 16G 机器，申请到 2 台。过了一段时间，我们感觉 2 台机器有点不太够，我就去找运维同学说，“哥儿们，你能不能再给我一台机器”，但是这时只剩下一台 4 核 + 8G 的机器。我要还是得要。
这个时候，可以给两台 8 核 16G 的机器设置权重 4，给剩余 1 台 4 核 8G 的机器设置权重 2。

3.LeastActive LoadBalance
最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
这个就是自动感知一下，如果某个机器性能越差，那么接收的请求越少，越不活跃，此时就会 给不活跃的性能差的机器更少的请求。

4.ConsistentHash LoadBalance
一致性 Hash，相同参数的请求总是发到同一提供者。
当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。算法参见：http://en.wikipedia.org/wiki/Consistent_hashing
缺省只对第一个参数 Hash，如果要修改，请配置 <dubbo:parameter key="hash.arguments" value="0,1" />
缺省用 160 份虚拟节点，如果要修改，请配置 <dubbo:parameter key="hash.nodes" value="320" />
相同参数的请求一定分发到一个 provider 上去，provider 挂掉的时候，会基于虚拟节点均匀分配剩余的流量，抖动不会太大。如果你需要的不是随机负载均衡，是要一类请求都到一个节点，那就走这个一致性 Hash 策略。

关于 dubbo 负载均衡策略更加详细的描述，
可以查看官网:http://dubbo.apache.org/zh-cn/docs/source_code_guide/loadbalance.html
```


### 集群容错
在集群调用失败时，Dubbo 提供了多种容错方案，缺省为 failover 重试。

集群容错模式
```html
1.Failover Cluster
失败自动切换，自动重试其他机器，默认就是这个，常见于读操作。（失败重试其它机器） 

重试次数配置如下：
<dubbo:service retries="2" />
或
<dubbo:reference retries="2" />
或
<dubbo:reference>
    <dubbo:method name="findFoo" retries="2" />
</dubbo:reference>


2.Failfast Cluster
快速失败，只发起一次调用，失败立即报错。通常用于非幂等性的写操作，比如新增记录。

3.Failsafe Cluster
失败安全，出现异常时忽略掉，常用于不重要的接口调用，比如记录日志。
配置示例如下：
<dubbo:service cluster="failsafe" />
或
<dubbo:reference cluster="failsafe" />

4.Failback Cluster
失败自动恢复，失败了后台自动记录请求，然后定时重发，比较适合于写消息队列这种。

5.Forking Cluster
并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 forks="2" 来设置最大并行数。

6.Broadcast Cluster
广播调用所有提供者，逐个调用所有的 provider。任何一个 provider 出错则报错（从 2.1.0  版本开始支持）。通常用于通知所有提供者更新缓存或日志等本地资源信息。



关于 dubbo 集群容错策略更加详细的描述，
可以查看官网 http://dubbo.apache.org/zh- cn/docs/source_code_guide/cluster.html 。

```


### 服务降级
什么是服务降级？

比如说服务 A 调用服务 B，结果服务 B 挂掉了，服务 A 重试几次调用服务 B，还是不行，那么直接降级，走一个备用的逻辑，给用户返回响应。

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

### 失败重试和超时重试

失败重试和超时重试

所谓失败重试，就是 consumer 调用 provider 要是失败了，比如抛异常了，此时应该是可以重试的，或者调用超时了也可以重试。配置如下：
 
```html
<dubbo:reference id="xxxx" interface="xx" check="true" async="false" retries="3" timeout="2000"/>
```

举个栗子。

某个服务的接口，要耗费 5s，你这边不能干等着，你这边配置了 timeout 之后，我等待 2s，还没返回，我直接就撤了，不能干等你。

可以结合你们公司具体的场景来说说你是怎么设置这些参数的：

1.timeout ：一般设置为 200ms ，我们认为不能超过 200ms 还没返回。

2.retries ：设置 retries，一般是在读请求的时候，比如你要查询个数据，你可以设置个retries，如果第一次没读到，报错，重试指定的次数，尝试再次读取。


### zookeeper宕机与dubbo直连
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
## 3.5 服务治理

### 调用链路自动生成

一个大型的分布式系统，或者说是用现在流行的微服务架构来说吧，分布式系统由大量的服务组成。那么这些服务之间互相是如何调用的？调用链路是啥？说实话，几乎到后面没人搞的清楚了，因为服务实在太多了，可能几百个甚至几千个服务。

那就需要基于 dubbo 做的分布式系统中，对各个服务之间的调用自动记录下来，然后自动将各个服务之间的依赖关系和调用链路生成出来，做成一张图，显示出来，大家才可以看到对吧。
 
 

### 服务访问压力以及时长统计

需要自动统计各个接口和服务之间的调用次数以及访问延时，而且要分成两个级别。

1.一个级别是接口粒度，就是每个服务的每个接口每天被调用多少次，TP50/TP90/TP99，三 个档次的请求延时分别是多少；

2.第二个级别是从源头入口开始，一个完整的请求链路经过几十个服务之后，完成一次请求，每天全链路走多少次，全链路请求延时的 TP50/TP90/TP99，分别是多少。

这些东西都搞定了之后，后面才可以来看当前系统的压力主要在哪里，如何来扩容和优化啊。

### 其它

- 1.服务分层（避免循环依赖） 
- 2.调用链路失败监控和报警 
- 3.服务鉴权
- 4.每个服务的可用性的监控（接口调用成功率？几个 9？99.99%，99.9%，99%）


## 3.6 dubbo原理  
### RPC原理
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
### netty通信原理
Netty是一个异步事件驱动的网络应用程序框架， 用于快速开发可维护的高性能协议服务器和客户端。它极大地简化并简化了TCP和UDP套接字服务器等网络编程。

BIO：(Blocking IO)
![base](http://liuyandeng.gitee.io/gitpages/img/dubbo/BIO.png)

Selector 一般称 为选择器 ，也可以翻译为 多路复用器，

Connect（连接就绪）、Accept（接受就绪）、Read（读就绪）、Write（写就绪）

Netty基本原理：
![base](http://liuyandeng.gitee.io/gitpages/img/dubbo/Netty.png)

### dubbo原理
**dubbo原理 -框架设计**
- 第一层:service 层，接口层，给服务提供者和消费者来实现的
- 第二层:config 配置层：对外配置接口，以 ServiceConfig, ReferenceConfig 为中心，可以直接初始化配置类，也可以通过 spring 解析配置生成配置类
- 第三层:proxy 服务代理层：服务接口透明代理，生成服务的客户端 Stub 和服务器端 Skeleton, 以 ServiceProxy 为中心，扩展接口为 ProxyFactory
- 第四层:registry 注册中心层：封装服务地址的注册与发现，以服务 URL 为中心，扩展接口为 RegistryFactory, Registry, RegistryService
- 第五层:cluster 路由层：封装多个提供者的路由及负载均衡，并桥接注册中心，以 Invoker 为中心，扩展接口为 Cluster, Directory, Router, LoadBalance
- 第六层:monitor 监控层：RPC 调用次数和调用时间监控，以 Statistics 为中心，扩展接口为 MonitorFactory, Monitor, MonitorService
- 第七层:protocol 远程调用层：封装 RPC 调用，以 Invocation, Result 为中心，扩展接口为 Protocol, Invoker, Exporter
- 第八层:exchange 信息交换层：封装请求响应模式，同步转异步，以 Request, Response 为中心，扩展接口为 Exchanger, ExchangeChannel, ExchangeClient, ExchangeServer
- 第九层:transport 网络传输层：抽象 mina 和 netty 为统一接口，以 Message 为中心，扩展接口为 Channel, Transporter, Client, Server, Codec
- 第十层:serialize 数据序列化层：可复用的一些工具，扩展接口为 Serialization, ObjectInput, ObjectOutput, ThreadPool

**工作流程**

- 第一步：provider 向注册中心去注册
- 第二步：consumer 从注册中心订阅服务，注册中心会通知 consumer 注册好的服务
- 第三步：consumer 调用 provider
- 第四步：consumer 和 provider 都异步通知监控中心

![howitwork](http://liuyandeng.gitee.io/gitpages/img/dubbo/howitwork.png)


## 3.7 spi
spi 是啥？

spi，简单来说，就是 service provider interface ，说白了是什么意思呢，比如你有个接口，现在这个接口有 3 个实现类，那么在系统运行的时候对这个接口到底选择哪个实现类呢？ 这就需要 spi  了，需要根据指定的配置或者是默认的配置，去找到对应的实现类加载进来， 然后用这个实现类的实例对象。

举个栗子。

你有一个接口 A。A1/A2/A3 分别是接口A的不同实现。你通过配置 接口 A = 实现 A2 ，那么在系统实际运行的时候，会加载你的配置，用实现 A2 实例化一个对象来提供服务。

spi   机制一般用在哪儿？插件扩展的场景，比如说你开发了一个给别人使用的开源框架，如果你想让别人自己写个插件，插到你的开源框架里面，从而扩展某个功能，这个时候 spi 思想就用上了。

Java spi 思想的体现
spi 经典的思想体现，大家平时都在用，比如说 jdbc。

Java 定义了一套 jdbc 的接口，但是 Java 并没有提供 jdbc 的实现类。

但是实际上项目跑的时候，要使用 jdbc 接口的哪些实现类呢？一般来说，我们要根据自己使用的数据库，比如 mysql，你就将 mysql-jdbc-connector.jar 引入进来；oracle，你就将oracle-jdbc-connector.jar 引入进来。

在系统跑的时候，碰到你使用 jdbc 的接口，他会在底层使用你引入的那个 jar 中提供的实现类。

dubbo 的 spi 思想
dubbo 也用了 spi 思想，不过没有用 jdk 的 spi 机制，是自己实现的一套 spi 机制。
 
Protocol protocol= ExtensionLoader.getExtensionLoader(Protocol.class).get

Protocol 接口，在系统运行的时候，，dubbo 会判断一下应该选用这个 Protocol 接口的哪个实现类来实例化对象来使用。

它会去找一个你配置的 Protocol，将你配置的 Protocol 实现类，加载到 jvm 中来，然后实例化对象，就用你的那个 Protocol 实现类就可以了。
 
上面那行代码就是 dubbo 里大量使用的，就是对很多组件，都是保留一个接口和多个实现，然后在系统运行的时候动态根据配置去找到对应的实现类。如果你没配置，那就走默认的实现好 了，没问题。

 
```java
@SPI("dubbo")
public interface Protocol {
   int getDefaultPort();
   @Adaptive
   <T> Exporter<T> export(Invoker<T> invoker) throws RpcException;
   @Adaptive
   <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;
   void destroy();
}
```


在 dubbo 自己的 jar 里，在 /META_INF/dubbo/internal/com.alibaba.dubbo.rpc.Protocol
文件中：
```html
dubbo=com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol 
http=com.alibaba.dubbo.rpc.protocol.http.HttpProtocol 
hessian=com.alibaba.dubbo.rpc.protocol.hessian.HessianProtocol
```

所以说，这就看到了 dubbo 的 spi 机制默认是怎么玩儿的了，其实就是 Protocol 接口， @SPI("dubbo") 说的是，通过 SPI 机制来提供实现类，实现类是通过 dubbo 作为默认 key 去配置文件里找到的，配置文件名称与接口全限定名一样的，通过 dubbo 作为 key 可以找到默认的实现类就是 com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol 。

如果想要动态替换掉默认的实现类，需要使用 @Adaptive  接口，Protocol 接口中，有两个方法加了 @Adaptive 注解，就是说那俩接口会被代理实现。

啥意思呢？

比如这个 Protocol 接口搞了俩 @Adaptive 注解标注了方法，在运行的时候会针对 Protocol 生成代理类，这个代理类的那俩方法里面会有代理代码，代理代码会在运行的时候动态根据  url 中的 protocol 来获取那个 key，默认是 dubbo，你也可以自己指定，你如果指定了别的 key，那么就会获取别的实现类的实例了。


## 3.8 分布式服务接口的幂等性
分布式服务接口的幂等性如何设计（比如不能重复扣款）？

一个分布式系统中的某个接口，该如何保证幂等性？这个事儿其实是你做分布式系统的时候必须要考虑的一个生产环境的技术问题。啥意思呢？

你看，假如你有个服务提供一些接口供外部调用，这个服务部署在了 5  台机器上，接着有个接口就是付款接口。然后人家用户在前端上操作的时候，不知道为啥，总之就是一个订单不小心 发起了两次支付请求，然后这俩请求分散在了这个服务部署的不同的机器上，好了，结果一   个订单扣款扣两次。

或者是订单系统调用支付系统进行支付，结果不小心因为网络超时了，然后订单系统走了前面 我们看到的那个重试机制，咔嚓给你重试了一把，好，支付系统收到一个支付请求两次，而且 因为负载均衡算法落在了不同的机器上，尴尬了。。。

所以你肯定得知道这事儿，否则你做出来的分布式系统恐怕容易埋坑。

这个不是技术问题，这个没有通用的一个方法，这个应该结合业务来保证幂等性。

所谓幂等性，就是说一个接口，多次发起同一个请求，你这个接口得保证结果是准确的，比如 不能多扣款、不能多插入一条数据、不能将统计值多加了 1。这就是幂等性。

其实保证幂等性主要是三点：

1.对于每个请求必须有一个唯一的标识，举个栗子：订单支付请求，肯定得包含订单 id，一个订单 id 最多支付一次，对吧。
2.每次处理完请求之后，必须有一个记录标识这个请求处理过了。常见的方案是在 mysql 中记录个状态啥的，比如支付之前记录一条这个订单的支付流水。
3.每次接收请求需要进行判断，判断之前是否处理过。比如说，如果有一个订单已经支付了，就已经有了一条支付流水，那么如果重复发送这个请求，则此时先插入支付流水， orderId 已经存在了，唯一键约束生效，报错插入不进去的。然后你就不用再扣款了。

实际运作过程中，你要结合自己的业务来，比如说利用 Redis，用 orderId 作为唯一键。只有成功插入这个支付流水，才可以执行实际的支付扣款。

要求是支付一个订单，必须插入一条支付流水，order_id 建一个唯一键 unique key 。你在支付一个订单之前，先插入一条支付流水，order_id 就已经进去了。你就可以写一个标识到 Redis 里面去， set order_id payed ，下一次重复请求过来了，先查 Redis 的 order_id 对应的value，如果是 payed 就说明已经支付过了，你就别重复支付了。

## 3.8 分布式服务接口请求的顺序性

其实分布式系统接口的调用顺序，也是个问题，一般来说是不用保证顺序的。但是有时候可能确实是需要严格的顺序保证。给大家举个例子，你服务 A 调用服务 B，先插入再删除。
好，结果俩请求过去了，落在不同机器上，可能插入请求因为某些原因执行慢了一些，导致删除请求 先执行了，此时因为没数据所以啥效果也没有；结果这个时候插入请求过来了，好，数据插入 进去了，那就尴尬了。

本来应该是 “先插入 -> 再删除”，这条数据应该没了，结果现在 “先删除 -> 再插入”，数据还存在，最后你死都想不明白是怎么回事。

所以这都是分布式系统一些很常见的问题。

首先，一般来说，个人建议是，你们从业务逻辑上设计的这个系统最好是不需要这种顺序性的 保证，因为一旦引入顺序性保障，比如使用分布式锁，会导致系统复杂度上升，而且会带来  效率低下，热点数据压力过大等问题。

下面我给个我们用过的方案吧，简单来说，首先你得用 Dubbo 的一致性 hash 负载均衡策略， 将比如某一个订单 id 对应的请求都给分发到某个机器上去，接着就是在那个机器上，因为可能还是多线程并发执行的，你可能得立即将某个订单 id 对应的请求扔一个内存队列里去，强制排队，这样来确保他们的顺序性。

但是这样引发的后续问题就很多，比如说要是某个订单对应的请求特别多，造成某台机器成热 点怎么办？解决这些问题又要开启后续一连串的复杂技术方案	曾经这类问题弄的我们头疼
不已，所以，还是建议什么呢？

最好是比如说刚才那种，一个订单的插入和删除操作，能不能合并成一个操作，就是一个删 除，或者是其它什么，避免这种问题的产生。

## 3.9 如何自己设计一个类似 Dubbo 的 RPC 框架
说实话，就这问题，其实就跟问你如何自己设计一个 MQ 一样的道理，就考两个：

1.你有没有对某个 rpc 框架原理有非常深入的理解。

2.你能不能从整体上来思考一下，如何设计一个 rpc 框架，考考你的系统设计能力。

其实问到你这问题，你起码不能认怂，因为是知识的扫盲，那我不可能给你深入讲解什么 kafka 源码剖析，dubbo  源码剖析，何况我就算讲了，你要真的消化理解和吸收，起码个把月以后了。

所以我给大家一个建议，遇到这类问题，起码从你了解的类似框架的原理入手，自己说说参照dubbo 的原理，你来设计一下，举个例子，dubbo  不是有那么多分层么？而且每个分层是干啥的，你大概是不是知道？那就按照这个思路大致说一下吧，起码你不能懵逼，要比那些上来就 懵，啥也说不出来的人要好一些。

举个栗子，我给大家说个最简单的回答思路：

1.上来你的服务就得去注册中心注册吧，你是不是得有个注册中心，保留各个服务的信息， 可以用 zookeeper 来做，对吧。

2.然后你的消费者需要去注册中心拿对应的服务信息吧，对吧，而且每个服务可能会存在于多台机器上。

3.接着你就该发起一次请求了，咋发起？当然是基于动态代理了，你面向接口获取到一个动态代理，这个动态代理就是接口在本地的一个代理，然后这个代理会找到服务对应的机器地址。

4.然后找哪个机器发送请求？那肯定得有个负载均衡算法了，比如最简单的可以随机轮询是不是。

5.接着找到一台机器，就可以跟它发送请求了，第一个问题咋发送？你可以说用 netty  了， nio 方式；第二个问题发送啥格式数据？你可以说用 hessian 序列化协议了，或者是别的， 对吧。然后请求过去了。

6.服务器那边一样的，需要针对你自己的服务生成一个动态代理，监听某个网络端口了，然后代理你本地的服务代码。接收到请求的时候，就调用对应的服务代码，对吧。

这就是一个最最基本的 rpc 框架的思路，先不说你有多牛逼的技术功底，哪怕这个最简单的思路你先给出来行不行？

## dubbo协议
### dubbo 支持不同的通信协议
1.dubbo 协议

默认就是走 dubbo 协议，单一长连接，进行的是 NIO 异步通信，基于 hessian 作为序列化协议。使用的场景是：传输数据量小（每次请求在 100kb 以内），但是并发量很高。

为了要支持高并发场景，一般是服务提供者就几台机器，但是服务消费者有上百台，可能每天 调用量达到上亿次！此时用长连接是最合适的，就是跟每个服务消费者维持一个长连接就可以，可能总共就 100 个连接。然后后面直接基于长连接 NIO 异步通信，可以支撑高并发请求。

长连接，通俗点说，就是建立连接过后可以持续发送请求，无须再建立连接。

而短连接，每次要发送请求之前，需要先重新建立一次连接。

2.rmi 协议

走 Java 二进制序列化，多个短连接，适合消费者和提供者数量差不多的情况，适用于文件的传输，一般较少用。

3.hessian 协议

走 hessian 序列化协议，多个短连接，适用于提供者数量比消费者数量还多的情况，适用于文件的传输，一般较少用。

4.http协议

走表单序列化。

5.webservice

走 SOAP 文本序列化


### dubbo 支持的序列化协议
dubbo 支持 hession、Java 二进制序列化、json、SOAP 文本序列化多种序列化协议。但是
hessian 是其默认的序列化协议。













