# Zookeeper

## 介绍
Zookeeper相关学习

[下载zookeeper](https://zookeeper.apache.org/releases.html#download)


# ZooKeeper是什么



## 一.ZooKeeper是什么
ZooKeeper由雅虎研究院开发，是Google Chubby的开源实现，后来托管到Apache，于2010年11月正式成为Apache的顶级项目。
ZooKeeper是一个经典的分布式数据一致性解决方案，致力于为分布式应用提供一个高性能、高可用，且具有严格顺序访问控制能力的分布式协调服务。
分布式应用程序可以基于ZooKeeper实现数据
- 发布与订阅、
- 负载均衡、
- 命名服务、
- 分布式协调与通知、
- 集群管理、
- Leader选举、
- 分布式锁、
- 分布式队列等功能。




## 二.ZooKeeper目标
ZooKeeper致力于为分布式应用提供一个高性能、高可用，且具有严格顺序访问控制能力的分布式协调服务

### 2.1 高性能
ZooKeeper将全量数据存储在内存中，并直接服务于客户端的所有非事务请求，尤其适用于以读为主的应用场景

### 2.2 高可用
ZooKeeper一般以集群的方式对外提供服务，一般3 ~ 5台机器就可以组成一个可用的Zookeeper集群了，每台机器都会在内存中维护当前的服务器状态，并且每台机器之间都相互保持着通信。只要集群中超过一般的机器都能够正常工作，那么整个集群就能够正常对外服务

### 2.3 严格顺序访问
对于来自客户端的每个更新请求，ZooKeeper都会分配一个全局唯一的递增编号，这个编号反映了所有事务操作的先后顺序



## 三. ZooKeeper五大特性
ZooKeeper一般以集群的方式对外提供服务，一个集群包含多个节点，每个节点对应一台ZooKeeper服务器，所有的节点共同对外提供服务，整个集群环境对分布式数据一致性提供了全面的支持，具体包括以下五大特性：

### 3.1 顺序一致性
从同一个客户端发起的请求，最终将会严格按照其发送顺序进入ZooKeeper中

### 3.2 原子性
所有请求的响应结果在整个分布式集群环境中具备原子性，即要么整个集群中所有机器都成功的处理了某个请求，要么就都没有处理，绝对不会出现集群中一部分机器处理了某一个请求，而另一部分机器却没有处理的情况

### 3.3 单一性
无论客户端连接到ZooKeeper集群中哪个服务器，每个客户端所看到的服务端模型都是一致的，不可能出现两种不同的数据状态，因为ZooKeeper集群中每台服务器之间会进行数据同步

### 3.4 可靠性
一旦服务端数据的状态发送了变化，就会立即存储起来，除非此时有另一个请求对其进行了变更，否则数据一定是可靠的

### 3.5 实时性
当某个请求被成功处理后，ZooKeeper仅仅保证在一定的时间段内，客户端最终一定能从服务端上读取到最新的数据状态，即ZooKeeper保证数据的最终一致性




## 四.ZooKeeper集群角色
在分布式系统中，集群中每台机器都有自己的角色，ZooKeeper没有沿用传统的Master/Slave模式（主备模式），而是引入了Leader、Follower和Observer三种角色

### 4.1 Leader
集群通过一个Leader选举过程从所有的机器中选举一台机器作为”Leader”，Leader能为客户端提供读和写服务,Leader在服务启动时被选举。
Leader服务器是整个集群工作机制的核心，主要工作：

事务请求的唯一调度者和处理者，保证集群事务处理的顺序性
集群内部各服务器的调度者

### 4.2 Follower
顾名思义，Follower是追随者，主要工作：

参与Leader选举投票
处理客户端非事务请求 - 即读服务
转发事务请求给Leader服务器
参与事务请求Proposal的投票
### 4.3 Observer
Observer是ZooKeeper自3.3.0版本开始引入的一个全新的服务器角色，充当一个观察者角色，工作原理和Follower基本是一致的，和Follower唯一的区别是Observer不参与任何形式的投票

- 处理客户端非事务请求 - 即读服务
- 转发事务请求给Leader服务器
- 不参与Leader选举投票
- 参与事务请求Proposal的投票
所以Observer可以在不影响写性能的情况下提升集群的读性能

以上是服务端的角色




## 五.原子广播协议 - Zab
ZooKeeper并非采用经典的分布式一致性协议 - Paxos，而是参考了Paxos设计了一种更加轻量级的支持崩溃可恢复的原子广播协议-Zab（ZooKeeper Atomic Broadcast）。
ZAB协议分为两个阶段 - Leader Election（领导选举）和Atomic Broadcast（原子广播）

### 5.1 领导选举 - Leader Election
当集群启动时，会选举一台节点为Leader，而其他节点为Follower，当Leader节点出现网络中断、崩溃退出与重启等异常情况，ZAB会进入恢复模式并选举产生新的Leader服务器，当集群中已有过半机器与该Leader服务器完成数据状态同步，退出恢复模式,
所谓的状态同步是指数据同步，用来保证集群中存在过半的机器能够和Leader服务器的数据状态保持一致。

### 5.2 原子广播 - Atomic Broadcast
当领导选举完成后，就进入原子广播阶段。此时集群中已存在一个Leader服务器在进行消息广播，当一台同样遵循ZAB协议的服务器启动后加入到集群中，新加的服务器会自动进入数据恢复阶段




## 六.事务请求
在ZooKeeper中，事务是指能够改变ZooKeeper服务器状态的请求，一般指创建节点、更新数据、删除节点以及创建会话操作

### 6.1 事务转发
为了保证事务请求被顺序执行，从而确保ZooKeeper集群的数据一致性，所有的事务请求必须由Leader服务器处理，ZooKeeper实现了非常特别的事务请求转发机制：
所有非Leader服务器如果接收到来自客户端的事务请求，必须将其转发给Leader服务器来处理

### 6.2 事务ID - ZXID
在分布式系统中，事务请求可能存在依赖关系，如变更C需要依赖变更A和变更B，这样就要求ZAB协议能够保证如果一个状态变更成功被处理了，那么其所有依赖的状态变更都应该已经提前被处理掉了。
在ZooKeeper中对每一个事务请求，都会为其分配一个全局唯一的事务ID，使用ZXID表示，通常是一个64位的数字。每一个ZXID对应一次事务，从这些ZXID可以间接识别出ZooKeeper处理这些事务请求的全局顺序




## 七.数据节点 - ZNode
ZooKeeper内部拥有一个树状的内存模型，类似文件系统，只是在ZooKeeper中将这些目录与文件系统统称为ZNode，ZNode是ZooKeeper中数据的最小单元，每个ZNode上可以保存数据，还可以挂载子节点，因此构成了一个层次化的命名空间

### 7.1 节点路径
ZooKeeper中使用斜杠（/）分割的路径表示ZNode路径，斜杠（/）表示根节点

![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk1.png)


### 7.2 节点特性
在ZooKeeper中，每个数据节点ZNode都是有生命周期的，其生命周期的长短取决于ZNode的节点类型

![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk2.png)

### 7.3 权限控制 - ACL
为了有效保障ZooKeeper中数据的安全，避免因误操作而带来数据随意变更导致分布式系统异常，ZooKeeper提供了一套完善的ACL（Access Contro List）权限控制机制来保障数据的安全。
可以从三个方面理解ACL机制，分别是：权限模式（Scheme）、授权对象（ID）和权限（Permission），通常使用”scheme:id:permission”来标识一个有效的ACL信息
![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk3.png)

### 7.4 节点状态信息
每个数据节点ZNode除了存储数据内容外，还存储了数据节点本身的一些状态信息

![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk4.png)

### 7.5 节点版本
ZooKeeper为数据节点引入版本的概念，对个数据节点都具有三种类型的版本信息，对数据节点的任何更新操作都会引起版本号的变化

![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk5.png)

在分布式系统中，在运行过程中往往需要保证数据访问的排他性。Java并发中是实现了对CAS的指令支持，即对于值V，每次更新前都会比对其值是否是预期值A，只有符合预期，才会将V原子化的更新到新值B
而ZooKeeper每个节点都有数据版本的概念，在调用更新操作的时候，先从请求中获取当前请求的版本version，同时获取服务器上该数据最新版本currentVersion，如果无法匹配，就无法更新成功，这样可以有效避免一些分布式更新的并发问题




## 八. Watcher - 数据变更的通知
在ZooKeeper中，引入Watcher机制来实现分布式数据的发布/订阅功能。ZooKeeper允许客户端向服务器注册一个Watcher监听，当服务器的一些指定事件触发了这个Watcher，那么就会向指定客户端发送一个事件通知来实现分布式的通知功能

Watcher机制为以下三个过程：

### 8.1 客户端注册Watcher
在创建一个ZooKeeper客户端对象实例时，可以向构造方法中传入一个Watcher，这个Watcher将作为整个ZooKeeper会话期间的默认Watcher，一致保存在客户端，并向ZooKeeper服务器注册Watcher
客户端并不会把真实的Watcher对象传递到服务器，仅仅只是在客户端请求中使用boolean类型属性进行标记，降低网络开销和服务器内存开销

### 8.2 服务端处理Watcher
服务端执行数据变更，当Watcher监听的对应数据节点的数据内容发生变更，如果找到对应的Watcher，会将其提取出来，同时从管理中将其删除（说明Watcher在服务端是一次性的，即触发一次就失效了），触发Watcher，向客户端发送通知

### 8.3 客户端回调Watcher
客户端获取通知，识别出事件类型，从相应的Watcher存储中去除对应的Watcher（说明客户端也是一次性的，即一旦触发就会失效）

### 8.4 总结
一致性：无论是客户端还是服务器，一旦一个Watcher被处罚，ZooKeeper都会将其从相应的存储中移除，因此开发人员在Watcher使用上要反复注册，这样可以有效减轻服务器压力
客户端串行执行：客户端Watcher回调的过程是一个串行同步的过程，这保证了顺序
轻量：客户端并不会把真实的Watcher对象传递到服务器，仅仅只是在客户端请求中使用boolean类型属性进行标记，降低网络开销和服务器内存开销




## 九. Session - 会话
Session是指客户端连接 - 客户端和服务器之间的一个TCP长连接

### 9.1 会话状态
会话在整个生命周期中，会在不同的会话转态之间进行切换

![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk6.png)
### 9.2 Session属性
Session是ZooKeeper中的会话实体，代表了一个客户端会话，其包含4个属性：

![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zk7.png)

### 9.3 心跳检测
为了保证客户端会话的有效性，客户端会在会话超时时间范围内向服务器发送PING请求来保持会话的有效性，即心跳检测。
服务器接收到客户端的这个心跳检测，就会重新激活对应的客户端会话

### 9.4 会话清理
服务器的超级检查线程会在指定时间点进行检查，整理出一些已经过期的会话后，就要开始进行会话清理了：

关闭会话
清理相关的临时节点
### 9.5 重连
当客户端和服务器之间网络连接断开，客户端会自动进行反复的重连，直到最终成功连接上ZooKeeper集群中的一台机器

在会话超时时间内重新连接上，被视为重连成功
在会话超时时间外重新连接上，此时服务器已经进行了会话清理，但客户端不知道会话已经失效，重新连接服务器会告诉客户端会话已失效，被视为非法会话
参考
《从Paxos到Zookeeper 分布式一致性原理与实践》
《轻量级微服务架构（上册）》


### Zookeeper集群安装
    wget http://www.apache.org/dist//zookeeper/zookeeper-3.3.3/zookeeper-3.3.3.tar.gz
    tar zxvf zookeeper-3.3.3.tar.gz
    cd zookeeper-3.3.3
    cp conf/zoo_sample.cfg conf/zoo.cfg
zookeeper-3.4.10/conf新建立zoo.cfg，zoo2.cfg，zoo3.cfg三个文件，配置如下

    #心跳间隔 毫秒每次
    tickTime = 2000
    ##日志位置 伪集群设置不同目录
    dataDir = /home/zookeeper-3.4.10/data/data1
    #监听客户端连接的端口 伪集群设置不同端口
    clientPort = 2181
    #多少个心跳时间内，允许其他server连接并初始化数据，如果ZooKeeper管理的数据较大，则应相应增大这个值
    initLimit = 10
    #多少个tickTime内，允许follower同步，如果follower落后太多，则会被丢弃
    syncLimit = 5
    
    #伪集群配置 不需要集群去掉（vim /etc/host 映射ip的hostname的关系）
    server.1=CentOS124:2886:3886
    server.2=CentOS124:2888:3888
    server.3=CentOS124:2889:3889
并在zookeeper-3.4.10/data的 data1,data2,data3 目录下放置myid文件，文件内容为1,2,3
进入bi目录 启动
    
    ./zkServer.sh start zoo.cfg
    ./zkServer.sh start zoo2.cfg
    ./zkServer.sh start zoo3.cfg
查看服务状态

    ./zkServer.sh status zoo.cfg
    ./zkServer.sh status zoo2.cfg
    ./zkServer.sh status zoo3.cfg
使用Zookeeper的客户端来连接并测试了

    [root@CentOS124 bin]# ./zkCli.sh
    #查看根节点
    [zk: localhost:2181(CONNECTED) 0] ls /
    [firstNode, SecodeZnode, firstNode0000000002, hbase, zookeeper]
    [zk: localhost:2181(CONNECTED) 0] create /mykey1 myvalue1  #创建一个新节点mykey1 
    Created /mykey1
    [zk: localhost:2181(CONNECTED) 1] get /mykey1   #获取mykey1节点  
    
    #要创建顺序节点
    create -s /FirstZnode second-data
    
    #要创建临时节点
    create -e /SecondZnode “Ephemeral-data"

参考

https://blog.csdn.net/lipinganq/article/details/81029499

https://blog.csdn.net/u010391342/article/details/81671239

https://blog.csdn.net/u010391342/article/details/100404588