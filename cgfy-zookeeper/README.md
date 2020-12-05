# Zookeeper

##介绍
Zookeeper相关学习


### [zookeeper集群的角色](https://blog.csdn.net/u010391342/article/details/100404588)
zk中共有三种角色：leader,follower,observer。
leader可以为客户端提供读写服务。follower和observer只能提供读服务，并且observer不参与leader选择过程，也不参与’过半写成功‘的策略。

### ZAB协议的核心思想
    ZAB协议的核心是定义了对于那些会改变Zookeeper服务器数据状态的事务请求的处理方式即：
所有事务请求必须由一个全局唯一的服务器来协调处理，这样的服务器被称为leader服务器，而余下的其它服务则称为follower服务。leader服务器负责将客户端事务请求转换成一个事务提议，并将提议分发给集群中所有的ollower服务，之后leader服务器需要等待所有follower服务反馈，一旦超过半数的follower服务进行了正确的反馈后，那么leader服务器机会再次向所有的follower服务分发commit消息，要求将前一个的提议进行提交。

### ZAB协议两种模式
- ZAB协议包括两种基本模式：崩溃恢复和原子广播。
- 当整个服务框架子啊启动过程中，或是当leader服务器出现网络故障，崩溃退出与重启等异常情况时，ZAB协议就会进入恢复模式并选举产生新的Leader服务器。
- 当选择产生了新的leader服务器，同时集群中已经有过半的机器与该leader服务器完成了同步状态之后，ZAB协议就会退出恢复模式。所谓的状态同步是指数据同步，用来保证集群中存在过半的机器能够和Leader服务器的数据状态保持一致。
### ZAB协议消息广播流程图
![avatar](https://liuyandeng.gitee.io/gitpages/img/ZAB协议消息广播流程图.jpg)

- ZAB协议和二阶段提交有所不同，移除了中断逻辑，所有Follower服务的正常反馈Leader的提议，要吗就丢弃
- 同时过半Follower服务反馈ACK后就可以开始提交事务的提议Proposal,而不需要等待集群中所有的Follower都反馈ACK
- 但是这种模式无法解决Leader服务器崩溃退出而带来的数据不一致性问题。
- 在消息广播的过程中，Leader会为每一个事务Proposal分配一个全局递增唯一的ID。
- 每一个Follower在接收到这个事务的Proposal之后，会将其以事务日志的形式写入到磁盘中去，并向Leader反馈ACK。
- 当Leader收到半数节点的ACK时，会广播一个commit消息通知其他节点进行事务提交，同时自己也完成事务提交
### ZAB协议奔溃恢复
- 当leader服务出现故障或过半的follower与leader服务失去联系就会进入崩溃恢复模式
- Leader选举算法应该保证：已经在Leader上提交的事务最终也被其他节点都提交，即使出现了Leader挂掉，Commit消息没发出去这种情况。
- 确保丢弃只在Leader上被提出的事务。Leader提出一个事务后挂了，集群中别的节点都没收到，当挂掉的节点恢复后，要确保丢弃那个事务。
- 让Leader选举算法能够保证新选举出来的Leader拥有最大的事务ID的Proposal。