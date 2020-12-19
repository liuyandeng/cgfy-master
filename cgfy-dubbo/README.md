# dubbo
dubbo本身并不是一个服务软件。它其实就是一个jar包能够帮你的java程序连接到zookeeper，并利用zookeeper消费、提供服务。所以你不用在Linux上启动什么dubbo服务。


- 1.cgfy-api:中间暴露接口项目,提供者provider的service接口
- 2.cgfy-provider:提供者,api接口的具体实现
- 3.cgfy-consumer:消费者,调用提供者远程提供的接口实现
假设三个服务在不同的服务器上

项目案例说明：consumer产品购买,调用provider,传入消费的金额,返回产品总共消费的金额

- dubbo官网: http://dubbo.apache.org
- dubbo github  源码地址:https://github.com/apache/incubator-dubbo

## windows下搭建zookeeper环境
Windows环境下安装:https://blog.csdn.net/qq877507054/article/details/110739854

## 测试dubbo远程服务调用
启动consumer,provider项目
在浏览器中访问  http://localhost:8062/add?a=100,
出现"该产品总共消费:1100",即为调用成功

## 安装控制台 dubbo-admin
但是为了让用户更好的管理监控众多的dubbo服务，官方提供了一个可视化的监控程序，不过这个监控即使不装也不影响使用。
- 1.dubbo运维master分支地址: https://github.com/apache/incubator-dubbo-ops/tree/master

目录:
```
    dubbo-admin:控制台
    dubbo-monitor-simple:监控中心
    dubbo-registry-simple:注册中心
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
主要用来统计服务的调用次数和调用时间，务消费者和提供者，在内存中累计调用次数和调用时间， 服定时每分钟发送一次统计数据到监控中心，监控中心则使用数据绘制图表来显示。

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











































