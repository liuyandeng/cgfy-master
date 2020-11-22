# spring-trans-jta-demo

#### 介绍
Spring JTA实现多数据源(DB,MQ)的事务管理

#### 软件架构
mq连接:ConnectionFactory  
消息模板:JmsTemplate  
监听器:JmsListenerContainerFactory  
事务:PlatformTransactionManager/JmsTransactionManager  
db数据持久层:JPA


#### 安装教程

H2数据库:运行后访问:http://localhost:8888/h2  
ActiveMQ:需要自行安装,  http://archive.apache.org/dist/activemq/5.15.3/apache-activemq-5.15.3-bin.zip


