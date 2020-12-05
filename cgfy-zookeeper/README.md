# Zookeeper

## 介绍
Zookeeper相关学习



## [Zookeeper 概述](https://blog.csdn.net/u010391342/article/details/81671239)
ZooKeeper是一种分布式协调服务，用于管理大型主机。在分布式环境中协调和管理服务是一个复杂的过程。ZooKeeper通过其简单的架构和API解决了这个问题。ZooKeeper允许开发人员专注于核心应用程序逻辑，而不必担心应用程序的分布式特性。

### 分布式应用的优点

- 可靠性 - 单个或几个系统的故障不会使整个系统出现故障。
- 可扩展性 - 可以在需要时增加性能，通过添加更多机器，在应用程序配置中进行微小的更改，而不会有停机时间。
- 透明性 - 隐藏系统的复杂性，并将其显示为单个实体/应用程序。
### 分布式应用的挑战

- 竞争条件 - 两个或多个机器尝试执行特定任务，实际上只需在任意给定时间由单个机器完成。例如，共享资源只能在任意给定时间由单个机器修改。
- 死锁 - 两个或多个操作等待彼此无限期完成。
- 不一致 - 数据的部分失败。
### 什么是Apache ZooKeeper？
Apache ZooKeeper是由集群（节点组）使用的一种服务，用于在自身之间协调，并通过稳健的同步技术维护共享数据。ZooKeeper本身是一个分布式应用程序，为写入分布式应用程序提供服务。

ZooKeeper提供的常见服务如下 :

- 命名服务 - 按名称标识集群中的节点。它类似于DNS，但仅对于节点。
- 配置管理 - 加入节点的最近的和最新的系统配置信息。
- 集群管理 - 实时地在集群和节点状态中加入/离开节点。
- 选举算法 - 选举一个节点作为协调目的的leader。
- 锁定和同步服务 - 在修改数据的同时锁定数据。此机制可帮助你在连接其他分布式应用程序（如Apache HBase）时进行自动故障恢复。
- 高度可靠的数据注册表 - 即使在一个或几个节点关闭时也可以获得数据。
### ZooKeeper的好处
- 简单的分布式协调过程
- 同步 - 服务器进程之间的相互排斥和协作。此过程有助于Apache HBase进行配置管理。
- 有序的消息
- 序列化 - 根据特定规则对数据进行编码。确保应用程序运行一致。这种方法可以在MapReduce中用来协调队列以执行运行的线程。
- 可靠性
- 原子性 - 数据转移完全成功或完全失败，但没有事务是部分的。
### ZooKeeper的架构
![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zookeeper.png)

#### Client（客户端）

客户端，我们的分布式应用集群中的一个节点，从服务器访问信息。对于特定的时间间隔，每个客户端向服务器发送消息以使服务器知道客户端是活跃的。类似地，当客户端连接时，服务器发送确认码。如果连接的服务器没有响应，客户端会自动将消息重定向到另一个服务器。
#### Server（服务器）

服务器，我们的ZooKeeper总体中的一个节点，为客户端提供所有的服务。向客户端发送确认码以告知服务器是活跃的。
#### Ensemble

ZooKeeper服务器组。形成ensemble所需的最小节点数为3。
#### Leader:

服务器节点，如果任何连接的节点失败，则执行自动恢复。Leader在服务启动时被选举。
#### Follower

跟随leader指令的服务器节点。
### 层次命名空间
ZooKeeper节点称为 znode 。每个znode由一个名称标识，并用路径(/)序列分隔。
![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zookeeper2.png)
- config 命名空间用于集中式配置管理，workers 命名空间用于命名。
- 在 config 命名空间下，每个znode最多可存储1MB的数据。这与UNIX文件系统相类似，除了父znode也可以存储数据。这种结构的主要目的是存储同步数据并描述znode的元数据。此结构称为 ZooKeeper数据模型。
- ZooKeeper数据模型中的每个znode都维护着一个 stat 结构。一个stat仅提供一个znode的元数据。它由版本号，操作控制列表(ACL)，时间戳和数据长度组成。

1.版本号 - 每个znode都有版本号，这意味着每当与znode相关联的数据发生变化时，其对应的版本号也会增加。当多个zookeeper客户端尝试在同一znode上执行操作时，版本号的使用就很重要。

2.操作控制列表(ACL) - ACL基本上是访问znode的认证机制。它管理所有znode读取和写入操作。

3.时间戳 - 时间戳表示创建和修改znode所经过的时间。它通常以毫秒为单位。ZooKeeper从“事务ID"(zxid)标识znode的每个更改。Zxid是唯一的，并且为每个事务保留时间，以便你可以轻松地确定从一个请求到另一个请求所经过的时间。

4.数据长度 - 存储在znode中的数据总量是数据长度。你最多可以存储1MB的数据。
### Znode的类型
Znode被分为持久（persistent）节点，顺序（sequential）节点和临时（ephemeral）节点。

- 持久节点 - 即使在创建该特定znode的客户端断开连接后，持久节点仍然存在。默认情况下，除非另有说明，否则所有znode都是持久的。
- 临时节点 - 客户端活跃时，临时节点就是有效的。当客户端与ZooKeeper集合断开连接时，临时节点会自动删除。因此，只有临时节点不允许有子节点。如果临时节点被删除，则下一个合适的节点将填充其位置。临时节点在leader选举中起着重要作用。
- 顺序节点 - 顺序节点可以是持久的或临时的。当一个新的znode被创建为一个顺序节点时，ZooKeeper通过将10位的序列号附加到原始名称来设置znode的路径。例如，如果将具有路径 /myapp 的znode创建为顺序节点，则ZooKeeper会将路径更改为 /myapp0000000001 ，并将下一个序列号设置为0000000002。如果两个顺序节点是同时创建的，那么ZooKeeper不会对每个znode使用相同的数字。顺序节点在锁定和同步中起重要作用。
### Sessions（会话）
- 会话对于ZooKeeper的操作非常重要。会话中的请求按FIFO顺序执行。一旦客户端连接到服务器，将建立会话并向客户端分配会话ID 。
- 客户端以特定的时间间隔发送心跳以保持会话有效。如果ZooKeeper集合在超过服务器开启时指定的期间（会话超时）都没有从客户端接收到心跳，则它会判定客户端死机。
- 会话超时通常以毫秒为单位。当会话由于任何原因结束时，在该会话期间创建的临时节点也会被删除。
### Watches（监视）
- 监视是一种简单的机制，使客户端收到关于ZooKeeper集合中的更改的通知。客户端可以在读取特定znode时设置Watches。Watches会向注册的客户端发送任何znode（客户端注册表）更改的通知。
- Znode更改是与znode相关的数据的修改或znode的子项中的更改。只触发一次watches。如果客户端想要再次通知，则必须通过另一个读取操作来完成。当连接会话过期时，客户端将与服务器断开连接，相关的watches也将被删除。
### Zookeeper 工作流
一旦ZooKeeper集合启动，它将等待客户端连接。客户端将连接到ZooKeeper集合中的一个节点。它可以是leader或follower节点。一旦客户端被连接，节点将向特定客户端分配会话ID并向该客户端发送确认。如果客户端没有收到确认，它将尝试连接ZooKeeper集合中的另一个节点。 一旦连接到节点，客户端将以有规律的间隔向节点发送心跳，以确保连接不会丢失。

- 如果客户端想要读取特定的znode，它将会向具有znode路径的节点发送读取请求，并且节点通过从其自己的数据库获取来返回所请求的znode。为此，在ZooKeeper集合中读取速度很快。
- 如果客户端想要将数据存储在ZooKeeper集合中，则会将znode路径和数据发送到服务器。连接的服务器将该请求转发给leader，然后leader将向所有的follower重新发出写入请求。如果只有大部分节点成功响应，而写入请求成功，则成功返回代码将被发送到客户端。 否则，写入请求失败。绝大多数节点被称为 Quorum 。
### ZooKeeper集合中的节点
- 如果我们有单个节点，则当该节点故障时，ZooKeeper集合将故障。它有助于“单点故障"，不建议在生产环境中使用。
- 如果我们有两个节点而一个节点故障，我们没有占多数，因为两个中的一个不是多数。
- 如果我们有三个节点而一个节点故障，那么我们有大多数，因此，这是最低要求。ZooKeeper集合在实际生产环境中必须至少有三个节点。
- 如果我们有四个节点而两个节点故障，它将再次故障。类似于有三个节点，额外节点不用于任何目的，因此，最好添加奇数的节点，例如3，5，7。
- 写入过程比ZooKeeper集合中的读取过程要贵，因为所有节点都需要在数据库中写入相同的数据
![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/zookeeper3.png)
#### 写入（write）

写入过程由leader节点处理。leader将写入请求转发到所有znode，并等待znode的回复。如果一半的znode回复，则写入过程完成。
#### 读取（read）

读取由特定连接的znode在内部执行，因此不需要与集群进行交互。
#### 复制数据库（replicated database）

它用于在zookeeper中存储数据。每个znode都有自己的数据库，每个znode在一致性的帮助下每次都有相同的数据。
Leader负责处理写入请求的Znode。

Follower:从客户端接收写入请求，并将它们转发到leader znode。

请求处理器（request processor）:只存在于leader节点。它管理来自follower节点的写入请求。

原子广播（atomic broadcasts）:负责广播从leader节点到follower节点的变化。

### Zookeeper leader选举
分析如何在ZooKeeper集合中选举leader节点。考虑一个集群中有N个节点。leader选举的过程如下：

- 所有节点创建具有相同路径 /app/leader_election/guid_ 的顺序、临时节点。
- ZooKeeper集合将附加10位序列号到路径，创建的znode将是 /app/leader_election/guid_0000000001，/app/leader_election/guid_0000000002等。
- 对于给定的实例，在znode中创建最小数字的节点成为leader，而所有其他节点是follower。
- 每个follower节点监视下一个具有最小数字的znode。例如，创建znode/app/leader_election/guid_0000000008的节点将监视znode/app/leader_election/guid_0000000007，创建znode/app/leader_election/guid_0000000007的节点将监视znode/app/leader_election/guid_0000000006。
- 如果leader关闭，则其相应的znode/app/leader_electionN会被删除。
- 下一个在线follower节点将通过监视器获得关于leader移除的通知。
- 下一个在线follower节点将检查是否存在其他具有最小数字的znode。如果没有，那么它将承担leader的角色。否则，它找到的创建具有最小数字的znode的节点将作为leader。
- 类似地，所有其他follower节点选举创建具有最小数字的znode的节点作为leader。





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
![avatar](https://liuyandeng.gitee.io/gitpages/img/zookeeper/ZAB协议消息广播流程图.jpg)

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