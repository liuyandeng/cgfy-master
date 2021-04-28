# RabbitMQ

##介绍
消息中间件RabbitMQ测试

- 1.[文档文献](https://blog.csdn.net/qq877507054/article/details/102967445)
- 2.[百度云下载](https://pan.baidu.com/s/1-BYcg0tIeCEiuKo6m-EhSQ ) 提取码：xafo

## 安装
- 1.安装Erlang 
- 2.安装RabbitMQ
- 3.在rabbitmq的安装目录下找到sbin目录，进入并在此目录打开cmd窗口(进入sbin的cmd窗口),输入rabbitmq-plugins enable rabbitmq_management (需要重启服务)
- 4.浏览器输入:http://localhost:15672,用户名/密码 guest/guest,进入官方管理界面
- 5.浏览器输入:http://localhost:9999,进入自定义管理界面(页面完善ing...)
- 6.API文档:http://localhost:9999/swagger-ui.html 或者 http://localhost:9999/doc.html















##文档介绍

###1.rabbitMQ是什么
RabbitMQ是实现了高级消息队列协议（AMQP）的开源消息代理软件（亦称面向消息的中间件）。RabbitMQ服务器是用Erlang语言编写的

RabbitMQ支持以下操作系统：
- 1.Windows
- 2.Linux/Unix
- 3.MAC OS X

MQ全称为Message Queue, 是一种分布式应用程序的的通信方法，它是消费-生产者模型的一个典型的代表，producer往消息队列中不断写入消息，而另一端consumer则可以读取或者订阅队列中的消息。RabbitMQ是MQ产品的典型代表，是一款基于AMQP协议可复用的企业消息系统。业务上，可以实现服务提供者和消费者之间的数据解耦，提供高可用性的消息传输机制，在实际生产中应用相当广泛。本文意在介绍Rabbitmq的基本原理，包括rabbitmq基本框架，概念，通信过程等

RabbitMQ,遵循AMQP协议，由内在高并发的erlang语言开发，用在实时的对可靠性要求比较高的消息传递上。

AMQP，即Advanced Message Queuing Protocol，高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。消息中间件主要用于组件之间的解耦，消息的发送者无需知道消息使用者的存在，反之亦然。
AMQP的主要特征是面向消息、队列、路由（包括点对点和发布/订阅）、可靠性、安全。
RabbitMQ是一个开源的AMQP实现，服务器端用Erlang语言编写，支持多种客户端，如：Python、Ruby、.NET、Java、JMS、C、PHP、ActionScript、XMPP、STOMP等，支持AJAX。用于在分布式系统中存储转发消息，在易用性、扩展性、高可用性等方面表现不俗。

学过websocket的来理解rabbitMQ应该是非常简单的了，websocket是基于服务器和页面之间的通信协议，一次握手，多次通信。 而rabbitMQ就像是服务器之间的socket，一个服务器连上MQ监听，而另一个服务器只要通过MQ发送消息就能被监听服务器所接收。

但是MQ和socket还是有区别的，socket相当于是页面直接监听服务器。而MQ就是服务器之间的中转站，例如邮箱，一个人投递信件给邮箱，另一个人去邮箱取，他们中间没有直接的关系，所以耦合度相比socket小了很多。

![avatar](https://img-blog.csdnimg.cn/20191108093054380.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxODc3NTA3MDU0,size_16,color_FFFFFF,t_70)

###### 扩展

1、AMQP

AMQP，即Advanced Message Queuing Protocol,一个提供统一消息服务的应用层标准高级消息队列协议,是应用层协议的一个开放标准,为面向消息的中间件设计。基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同的开发语言等条件的限制。Erlang中的实现有 RabbitMQ等。

2、Erlang语言

Erlang是一种通用的并发程序设计语言，它由乔·阿姆斯特朗（Joe Armstrong）在瑞典电信设备制造商爱立信所辖的计算机科学研究室开发，目的是创造一种可以应付大规模开发活动的程序设计语言和运行环境。Erlang于1987年发布正式版本，最早是爱立信拥有的私有软件，经过十年的发展，于1998年发表开放源代码版本。

Erlang是运作于虚拟机的解释型语言，但是现在也包含有乌普萨拉大学高性能Erlang计划（HiPE）[3]开发的原生代码编译器，自R11B-4版本开始，Erlang也支持脚本方式执行。在编程范型上，Erlang属于多重典范编程语言，涵盖函数式、并行及分布式。循序运行的Erlang是一个及早求值, 单次赋值和动态类型的函数式编程语言。

3.消息队列(Message Queue)

在计算机科学中，消息队列（英语：Message queue）是一种进程间通信或同一进程的不同线程间的通信方式，软件的贮列用来处理一系列的输入，通常是来自用户。消息队列提供了异步的通信协议，每一个贮列中的纪录包含详细说明的数据，包含发生的时间，输入设备的种类，以及特定的输入参数，也就是说：消息的发送者和接收者不需要同时与消息队列交互。消息会保存在队列中，直到接收者取回它。

实际上，消息队列常常保存在链表结构中。拥有权限的进程可以向消息队列中写入或读取消息。 

目前，有很多消息队列有很多开源的实现，包括JBoss Messaging、JORAM、Apache ActiveMQ、Sun Open Message Queue、RabbitMQ、IBM MQ、Apache Qpid和HTTPSQS。

https://blog.csdn.net/wangbing25307/article/details/80845641


###2.MQ使用场景
别啥固定式使用场景了，说的透彻一点，他就是服务器之间通信的，前面博文中提到的Httpclient也可以做到，但是这个相对于其他通信在中间做了一个中间仓库。

好处1：降低了两台服务器之间的耦合，哪怕是一台服务器挂了，另外一台服务器也不会报错或者休克，反正他监听的是MQ，只要服务器恢复再重新连上MQ发送消息，监听服务器就能再次接收。


好处2：MQ作为一个仓库，本身就提供了非常强大的功能，例如不再是简单的一对一功能，还能一对多，多对一，自己脑补保险箱场景，只要有特定的密码，谁都能存，谁都能取。也就是说能实现群发消息和以此衍生的功能。

![avatar](https://img-blog.csdnimg.cn/20191108093206235.png)

好处3：现在普遍化的持久化功能，当MQ挂掉可以存储在磁盘等下重启恢复。（需要设置）

你是否遇到过两个（多个）系统间需要通过定时任务来同步某些数据？你是否在为异构系统的不同进程间相互调用、通讯的问题而苦恼、挣扎？如果是，那么恭喜你，消息服务让你可以很轻松地解决这些问题。
消息服务擅长于解决多系统、异构系统间的数据交换（消息通知/通讯）问题，你也可以把它用于系统间服务的相互调用（RPC）。

###3.专业术语介绍
- 1.生产者： 在现实生活中就好比制造商品的工厂，他们是商品的生产者。生产者只意味着发送。发送消息的程序称之为一个生产者。

- 2.队列：rabbitMQ就像一个仓库，一个仓库里面可以 有很多队列，每个队列才是服务器之间消息通信的载体。

- 3.消费者：消费者就好比是从商店购买或从仓库取走商品的人，消费的意思就是接收。消费者是一个程序，主要是等待接收消息。

- 4.交换器：在生产者和消息队列之间的交换器，功能类似于网络宽带的交换机，可以根据不同的关键字，将信息发送到不同的队列。
 
![avatar](https://img-blog.csdnimg.cn/20191108093456365.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxODc3NTA3MDU0,size_16,color_FFFFFF,t_70)

上图的E就是交换器，通过关键字绑定，如果生产者给的消息中指定类型是ERROR，就给队列1，如果是INFO或者WARN就给队列2。当然也可以一个关键字绑定两个队列。（INFO等字段自己可以定义，也可以用*，#来匹配。*（星号）表示一个单词#（井号）表示零个或者多个单词。 比如ok.yes可以被ok.*匹配到）
- 5.临时队列：根据需求临时创建的一条队列，在断开连接后自动删除。

###4.流程介绍
　　生产者发送一条消息给交换机——交换机根据关键字匹配到对应的队列——将消息存入队列——消费者从队列中取出消息使用。
 
###5.一些细节说明
MQ不能进行批量的消息处理，你看到的传输再多也只是消息被一条一条的存入队列，消费者从队列中一条一条的取出。这kafKa有区别，所以在效率上比不上kafKa，但是MQ主打的是稳定。
每次消费者取出消息时会通知队列，我拿到了，当队列接收到这条消息，就会把消息删除，这是默认的ACK机制。如果在接收消息之后，消费者挂掉，或者任何情况没有返回ack，队列中这条消息将不会删除，可以一直存着，等待其他消费者来取。    
注意，但是如果设置不返回ack，在不断的发送消息到队列又不删除，会导致MQ仓库boom~~~~
https://blog.csdn.net/chizizhixin/article/details/78488735


###6.系统架构
Rabbitmq系统最核心的组件是Exchange和Queue，下图是系统简单的示意图。Exchange和Queue是在rabbitmq server（又叫做broker）端，producer和consumer在应用端。

![avatar](https://img-blog.csdnimg.cn/20191108093545736.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxODc3NTA3MDU0,size_16,color_FFFFFF,t_70)

**producer&Consumer**

producer指的是消息生产者，consumer消息的消费者。

**Queue:队列**

消息队列，提供了FIFO的处理机制，具有缓存消息的能力。rabbitmq中，队列消息可以设置为持久化，临时或者自动删除。
1.	设置为持久化的队列，queue中的消息会在server本地硬盘存储一份，防止系统crash，数据丢失
2.	设置为临时队列，queue中的数据在系统重启之后就会丢失
3.	设置为自动删除的队列，当不存在用户连接到server，队列中的数据会被自动删除

Queue（队列）是RabbitMQ的内部对象，用于存储消息，用下图表示。

 ![avatar](https://img-blog.csdnimg.cn/20191108093647428.png)

RabbitMQ中的消息都只能存储在Queue中，生产者（下图中的P）生产消息并最终投递到Queue中，消费者（下图中的C）可以从Queue中获取消息并消费。
 
 ![avatar](https://img-blog.csdnimg.cn/2019110809372876.png)

多个消费者可以订阅同一个Queue，这时Queue中的消息会被平均分摊给多个消费者进行处理，而不是每个消费者都收到所有的消息并处理。 

 ![avatar](https://img-blog.csdnimg.cn/20191108093816270.png)

**Exchange:交换机**

自己的理解:队列中的中转站
Exchange类似于数据通信网络中的交换机，提供消息路由策略。rabbitmq中，producer不是通过信道直接将消息发送给queue，而是先发送给Exchange。一个Exchange可以和多个Queue进行绑定，producer在传递消息的时候，会传递一个ROUTING_KEY(生产者提供路由key)，Exchange会根据这个ROUTING_KEY按照特定的路由算法，将消息路由给指定的queue。和Queue一样，Exchange也可设置为持久化，临时或者自动删除。

**Exchange Types**

RabbitMQ常用的Exchange Type有4种类型：direct(默认)，fanout, topic, 和headers,（AMQP规范里还提到两种Exchange Type，分别为system与自定义，这里不予以描述）,不同类型的Exchange转发(路由)消息的策略有所区别,下面分别进行介绍。

- Direct:直接

直接交换器，工作方式类似于单播，Exchange会将消息发送完全匹配ROUTING_KEY的Queue

direct类型的Exchange路由规则也很简单，它会把消息路由到那些binding key与routing key完全匹配的Queue中。

 ![avatar](https://img-blog.csdnimg.cn/20191108093924645.png)

以上图的配置为例，我们以routingKey=”error”发送消息到Exchange，则消息会路由到Queue1（amqp.gen-S9b…，这是由RabbitMQ自动生成的Queue名称）和Queue2（amqp.gen-Agl…）；如果我们以routingKey=”info”或routingKey=”warning”来发送消息，则消息只会路由到Queue2。如果我们以其他routingKey发送消息，则消息不会路由到这两个Queue中。

- Fanout:广播

广播式交换器，不管消息的ROUTING_KEY设置为什么，Exchange都会将消息转发给所有绑定的Queue。fanout类型的Exchange路由规则非常简单，它会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中。
  
 ![avatar](https://img-blog.csdnimg.cn/20191108094013856.png)

上图中，生产者（P）发送到Exchange（X）的所有消息都会路由到图中的两个Queue，并最终被两个消费者（C1与C2）消费。


- Topic:主题

主题交换器，工作方式类似于组播，Exchange会将消息转发和ROUTING_KEY匹配模式相同的所有队列，比如，ROUTING_KEY为user.stock的Message会转发给绑定匹配模式为 * .stock,user.stock， * . * 和#.user.stock.#的队列。（ * 表是匹配一个任意词组，#表示匹配0个或多个词组）
前面讲到direct类型的Exchange路由规则是完全匹配binding key与routing key，但这种严格的匹配方式在很多情况下不能满足实际业务需求。topic类型的Exchange在匹配规则上进行了扩展，它与direct类型的Exchage相似，也是将消息路由到binding key与routing key相匹配的Queue中，但这里的匹配规则有些不同，它约定：
routing key为一个句点号“. ”分隔的字符串（我们将被句点号“. ”分隔开的每一段独立的字符串称为一个单词），如“stock.usd.nyse”、“nyse.vmw”、“quick.orange.rabbit”
binding key与routing key一样也是句点号“. ”分隔的字符串
binding key中可以存在两种特殊字符“*”与“#”，用于做模糊匹配，其中“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）

  ![avatar](https://img-blog.csdnimg.cn/20191108094049856.png)
  
以上图中的配置为例，routingKey=”quick.orange.rabbit”的消息会同时路由到Q1与Q2，routingKey=”lazy.orange.fox”的消息会路由到Q1与Q2，routingKey=”lazy.brown.fox”的消息会路由到Q2，routingKey=”lazy.pink.rabbit”的消息会路由到Q2（只会投递给Q2一次，虽然这个routingKey与Q2的两个bindingKey都匹配）；routingKey=”quick.brown.fox”、routingKey=”orange”、routingKey=”quick.orange.male.rabbit”的消息将会被丢弃，因为它们没有匹配任何bindingKey。

- Headers:消息体

消息体的header匹配（ignore）
headers类型的Exchange不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配。
在绑定Queue与Exchange时指定一组键值对；当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对的形式），对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对；如果完全匹配则消息会路由到该Queue，否则不会路由到该Queue。
该类型的Exchange没有用到过（不过也应该很有用武之地），所以不做介绍。


我们看到生产者将消息投递到Queue中，实际上这在RabbitMQ中这种事情永远都不会发生。实际的情况是，生产者将消息发送到Exchange（交换器，下图中的X），由Exchange将消息路由到一个或多个Queue中（或者丢弃）。(需要一个中转站)
 
 ![avatar](https://img-blog.csdnimg.cn/20191108094125677.png)





Exchange是按照什么逻辑将消息路由到Queue的？

**Binding**

所谓绑定就是将一个特定的 Exchange 和一个特定的 Queue 绑定起来。Exchange 和Queue的绑定可以是多对多的关系。
RabbitMQ中通过Binding将Exchange与Queue关联起来，这样RabbitMQ就知道如何正确地将消息路由到指定的Queue了。

  ![avatar](https://img-blog.csdnimg.cn/20191108094201991.png)

- binding key

发消息到交换机需要routing key,交换机绑定队列需要binding key,	
交换机:左手routing右手binding
在绑定（Binding）Exchange与Queue的同时，一般会指定一个binding key；生产者将消息发送给Exchange时，一般会指定一个routing key(生产者提供路由key);当binding key与routing key相匹配时，消息将会被路由到对应的Queue中。
在绑定多个Queue到同一个Exchange的时候，这些Binding允许使用相同的binding key。
binding key 并不是在所有情况下都生效，它依赖于Exchange Type，比如fanout类型的Exchange就会无视binding key，而是将消息路由到所有绑定到该Exchange的Queue。

- routing key

生产者在将消息发送给Exchange的时候，一般会指定一个routing key，来指定这个消息的路由规则，而这个routing key需要与Exchange Type及binding key联合使用才能最终生效。
在Exchange Type与binding key固定的情况下（在正常使用时一般这些内容都是固定配置好的），我们的生产者就可以在发送消息给Exchange时，通过指定routing key来决定消息流向哪里。
RabbitMQ为routing key设定的长度限制为255 bytes。

**virtual host:虚拟主机**

在rabbitmq server上可以创建多个虚拟的message broker(消息代理)，又叫做virtual hosts (vhosts)(虚拟主机)。每一个vhost本质上是一个mini-rabbitmq server，分别管理各自的exchange，和bindings。vhost相当于物理的server，可以为不同app提供边界隔离，使得应用安全的运行在不同的vhost实例上，相互之间不会干扰。producer和consumer连接rabbit server需要指定一个vhost。
virtual host其实是一个虚拟概念，类似于权限控制组，一个virtual host里面可以有若干个Exchange和Queue,但是权限控制的最小粒度是virtual host

###7.通信过程
假设P1和C1注册了相同的Broker，Exchange和Queue。P1发送的消息最终会被C1消费。基本的通信流程大概如下所示：
1.	P1生产消息，发送给服务器端的Exchange
2.	Exchange收到消息，根据ROUTINKEY，将消息转发给匹配的Queue1
3.	Queue1收到消息，将消息发送给订阅者C1
4.	C1收到消息，发送ACK给队列确认收到消息(收到以后消息就可以删了,消息回执)
5.	Queue1收到ACK，删除队列中缓存的此条消息
Consumer收到消息时需要显式的向rabbit broker发送basic.ack消息或者consumer订阅消息时设置auto_ack参数为true。在通信过程中，队列对ACK的处理有以下几种情况：
1.	如果consumer接收了消息，发送ack, rabbitmq会删除队列中这个消息，发送另一条消息给consumer。
2.	如果cosumer接受了消息, 但在发送ack之前断开连接，rabbitmq会认为这条消息没有被deliver(发送),在consumer在次连接的时候，这条消息会被redeliver(再次发送)。
3.	如果consumer接受了消息，但是程序中有bug,忘记了ack,rabbitmq不会重复发送消息。
4.	rabbitmq2.0.0和之后的版本支持consumer reject某条（类）消息，可以通过设置requeue参数中的reject为true达到目地，那么rabbitmq将会把消息发送给下一个注册的consumer。
https://www.cnblogs.com/jun-ma/p/4840869.html

###8.API
ConnectionFactory、Connection、Channel都是RabbitMQ对外提供的API中最基本的对象。Connection是RabbitMQ的socket链接，它封装了socket协议相关部分逻辑。

ConnectionFactory为Connection的制造工厂。

Channel是我们与RabbitMQ打交道的最重要的一个接口，我们大部分的业务操作是在Channel这个接口中完成的，包括定义Queue、定义Exchange、绑定Queue与Exchange、发布消息等。

###9.消息回执

在实际应用中，可能会发生消费者收到Queue中的消息，但没有处理完成就宕机（或出现其他意外）的情况，这种情况下就可能会导致消息丢失。为了避免这种情况发生，我们可以要求消费者在消费完消息后发送一个回执给RabbitMQ，RabbitMQ收到消息回执（Message acknowledgment）后才将该消息从Queue中移除；如果RabbitMQ没有收到回执并检测到消费者的RabbitMQ连接断开，则RabbitMQ会将该消息发送给其他消费者（如果存在多个消费者）进行处理。这里不存在timeout概念，一个消费者处理消息时间再长也不会导致该消息被发送给其他消费者，除非它的RabbitMQ连接断开。
	这里会产生另外一个问题，如果我们的开发人员在处理完业务逻辑后，忘记发送回执给RabbitMQ，这将会导致严重的bug——Queue中堆积的消息会越来越多；消费者重启后会重复消费这些消息并重复执行业务逻辑…
另外pub message是没有ack的。

###10消息持久化

如果我们希望即使在RabbitMQ服务重启的情况下，也不会丢失消息，我们可以将Queue与Message都设置为可持久化的（durable），这样可以保证绝大部分情况下我们的RabbitMQ消息不会丢失。但依然解决不了小概率丢失事件的发生（比如RabbitMQ服务器已经接收到生产者的消息，但还没来得及持久化该消息时RabbitMQ服务器就断电了），如果我们需要对这种小概率事件也要管理起来，那么我们要用到事务。由于这里仅为RabbitMQ的简单介绍，所以这里将不讲解RabbitMQ相关的事务。

###11.预取数量

前面我们讲到如果有多个消费者同时订阅同一个Queue中的消息，Queue中的消息会被平摊给多个消费者。这时如果每个消息的处理时间不同，就有可能会导致某些消费者一直在忙，而另外一些消费者很快就处理完手头工作并一直空闲的情况。我们可以通过设置prefetchCount来限制Queue每次发送给每个消费者的消息数，比如我们设置prefetchCount=1，则Queue每次给每个消费者发送一条消息；消费者处理完这条消息后Queue会再给该消费者发送一条消息。
  
   ![avatar](https://img-blog.csdnimg.cn/20191108094256448.png)
###12.RPC

MQ本身是基于异步的消息处理，前面的示例中所有的生产者（P）将消息发送到RabbitMQ后不会知道消费者（C）处理成功或者失败（甚至连有没有消费者来处理这条消息都不知道）。
但实际的应用场景中，我们很可能需要一些同步处理，需要同步等待服务端将我的消息处理完成后再进行下一步处理。这相当于RPC（Remote Procedure Call，远程过程调用）。在RabbitMQ中也支持RPC。
   ![avatar](https://img-blog.csdnimg.cn/20191108094325453.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxODc3NTA3MDU0,size_16,color_FFFFFF,t_70)

**RabbitMQ中实现RPC的机制是：**

- 1.客户端发送请求（消息）时，在消息的属性（MessageProperties，在AMQP协议中定义了14中properties，这些属性会随着消息一起发送）中设置两个值replyTo（一个Queue名称，用于告诉服务器处理完成后将通知我的消息发送到这个Queue中）和correlationId（此次请求的标识号，服务器处理完成后需要将此属性返还，客户端将根据这个id了解哪条请求被成功执行了或执行失败）
- 2 服务器端收到消息并处理
- 3.服务器端处理完消息后，将生成一条应答消息到replyTo指定的Queue，同时带上correlationId属性
- 4.客户端之前已订阅replyTo指定的Queue，从中收到服务器的应答消息后，根据其中的correlationId属性分析哪条请求被执行了，根据执行结果进行后续业务处理

利用RabbitMQ提供的这些功能就可以处理我们绝大部分的异步业务了。



