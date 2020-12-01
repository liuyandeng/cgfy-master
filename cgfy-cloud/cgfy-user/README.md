# user

##介绍
用户管理

接口地址:
http://localhost:8840/user/swagger-ui.html

## springboot的启动过程
### @SpringBootApplication
很多Spring Boot开发者经常使用 @Configuration ， @EnableAutoConfiguration ， @ComponentScan 注解他们的main类，由于这些注解如此频繁地一块使用（特别是遵循以上最佳实践的时候），Spring Boot就提供了一个方便的 @SpringBootApplication 注解作为代替。
@SpringBootApplication 注解等价于以默认属性使用 @Configuration ， @EnableAutoConfiguration 和 @ComponentScan 

默认扫描的包的路径是当前包以及子包下面的所有类,可以通过scanBasePackages修改这个扫描路径

