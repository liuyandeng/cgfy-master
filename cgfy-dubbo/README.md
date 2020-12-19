# dubbo
dubbo本身并不是一个服务软件。它其实就是一个jar包能够帮你的java程序连接到zookeeper，并利用zookeeper消费、提供服务。所以你不用在Linux上启动什么dubbo服务。


- 1.cgfy-api:提供者provider的service接口
- 2.cgfy-provider:提供者,api接口的具体实现
- 3.cgfy-consumer:消费者,调用提供者远程提供的接口实现
假设三个服务在不同的服务器上

项目案例说明：consumer产品购买,调用provider,传入消费的金额,返回产品总共消费的金额

- dubbo官网: http://dubbo.apache.org
- dubbo github  源码地址:https://github.com/apache/incubator-dubbo

参考文档:https://blog.csdn.net/qq_39736103/article/details/82796563

# windows下搭建zookeeper环境
Windows环境下安装:https://blog.csdn.net/qq877507054/article/details/110739854

# 测试dubbo远程服务调用
启动consumer,provider项目
在浏览器中访问  http://localhost:8062/add?a=100,
出现"该产品总共消费:1100",即为调用成功

# 监控中心 dubbo-admin
但是为了让用户更好的管理监控众多的dubbo服务，官方提供了一个可视化的监控程序，不过这个监控即使不装也不影响使用。
- 1.dubbo运维master分支地址: https://github.com/apache/incubator-dubbo-ops/tree/master
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
- 5.启动成功后 浏览器访问http://localhost:7001 输入账号：root /  密码：root 即可。

![dubbo-admin UI](http://liuyandeng.gitee.io/gitpages/img/dubbo/admin/dubbo-admin.png)
![service](http://liuyandeng.gitee.io/gitpages/img/dubbo/admin/service.png)
![app](http://liuyandeng.gitee.io/gitpages/img/dubbo/admin/app.png)




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
