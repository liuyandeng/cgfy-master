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

## [集成hystrix](https://blog.csdn.net/wlddhj/article/details/85243764)
引入依赖:

    <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    
启用hystrix

    @EnableHystrix(包含@EnableCircuitBreaker)
    
使用示例:

    @Component
    @RestController
    public class HelloService {
        @RequestMapping("/hi")
        @HystrixCommand(fallbackMethod = "hiFail")
        public String hi(String name){
    //        try {
    //            Thread.sleep(5000);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //        throw new RuntimeException("test");
            return "hi "+name;
        }
    
    
        public String hiFail(String name){
            return "hiFail "+name;
        }
    }    
通过注解@HystrixCommand标记方法调用失败或超时时，跳转的fallbackMethod方法 

配置监控路径

    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);  //系统启动时加载顺序
        registrationBean.addUrlMappings("/actuator/hystrix.stream");//路径
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }    

更多配置示例

配置线程池

    @Component
    @RestController
    public class Hello2Service {
        @RequestMapping("/hello2")
        @HystrixCommand(fallbackMethod = "hiFail", commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        },
                threadPoolProperties = {
                        @HystrixProperty(name = "coreSize", value = "10"),
                        @HystrixProperty(name = "maxQueueSize", value = "20"),
                        @HystrixProperty(name = "keepAliveTimeMinutes", value = "0"),
                        @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                        @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
                })
        public String hi(String name) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hi " + name;
        }
    
    
        public String hiFail(String name, Throwable e) {
            if (e != null) {
                e.printStackTrace();
            }
            return "hiFail " + name;
        }
    }    
    
配置信号量    
    
     @Component
     @RestController
     public class Hello3Service {
         @RequestMapping("/hello3")
         @HystrixCommand(fallbackMethod = "hiFail", commandProperties = {
                 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                 @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "9"),
                 @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "20"),
                 @HystrixProperty(name = "execution.isolation.strategy", value ="SEMAPHORE")
         })
         public String hi(String name) {
             try {
                 Thread.sleep(5);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             return "hi " + name;
         }
     
     
         public String hiFail(String name, Throwable e) {
             if (e != null) {
                 e.printStackTrace();
             }
             return "hiFail " + name;
         }
     }   
    
配合feignClient使用
   
引入依赖 

     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-openfeign</artifactId>
     </dependency>   
    
 在application.properties里面添加配置   
 
     feign.hystrix.enabled=true
 创建feignClient接口，并指定fallback类，fallback类必须实现该接口
 
     @FeignClient(name ="hystrix-consumer", path = "",url = "http://127.0.0.1:8080/",fallback = IHelloFailback.class)
     public interface IHelloClient {
         @RequestMapping("/hi")
         public String hi(@RequestParam("name") String name);
     }   
    
    @Component
    public class IHelloFailback implements IHelloClient {
        @Override
        public String hi(String name) {
            return "Hi，服务异常";
        }
    }
 启用FeignClient，在启动类添加注解
 
    @EnableFeignClients(basePackages = {"com.example.hj.springcloudhystrixprovider.client"})   
  
  默认参数  
    
    final class Default implements SetterFactory {
    
    @Override
    public HystrixCommand.Setter create(Target<?> target, Method method) {
      String groupKey = target.name();
      String commandKey = Feign.configKey(target.type(), method);
      return HystrixCommand.Setter
          .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
          .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
    }
    }
  自定义参数  
    
    @Bean
    public Feign.Builder feignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory(new SetterFactory() {
            @Override
            public HystrixCommand.Setter create(Target<?> target, Method method) {
                return HystrixCommand.Setter
                        .withGroupKey(HystrixCommandGroupKey.Factory.asKey(RemoteProductService.class.getSimpleName()))// 控制 RemoteProductService 下,所有方法的Hystrix Configuration
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000) // 超时配置
                        );
            }
        });
    }
    
   基本配置
   参考[官方配置](https://github.com/Netflix/Hystrix/wiki/Configuration)
   
       配置项	默认值	说明
       hystrix.command.default.execution.isolation.strategy	THREAD	隔离策略，THREAD, SEMAPHORE
       hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds	1000	线程超时时间
       hystrix.command.default.execution.timeout.enabled	true	启用超时设置
       hystrix.command.default.execution.isolation.thread.interruptOnTimeout	true	线程超时时，是否可被中断
       hystrix.command.default.execution.isolation.thread.interruptOnCancel	false	线程取消时，是否可被中断
       hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests	10	最大并发信号量
       hystrix.command.default.fallback.isolation.semaphore.maxConcurrentRequests	10	最大并发备用信号量
       hystrix.command.default.fallback.enabled	true	启用fallback
       hystrix.command.default.circuitBreaker.enabled	true	启用断路器
       hystrix.command.default.circuitBreaker.requestVolumeThreshold	20	在一个时间窗口内触发断路器的最小请求量
       hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds	5000	触发断路器时，服务休眠时间
       hystrix.command.default.circuitBreaker.errorThresholdPercentage	50	触发断路器时，最小错误比率
       hystrix.command.default.circuitBreaker.forceOpen	false	强制打开断路器
       hystrix.command.default.circuitBreaker.forceClosed	false	强制关闭断路器
       hystrix.command.default.metrics.rollingStats.timeInMilliseconds	10000	数据采集时间段
       hystrix.command.default.metrics.rollingStats.numBuckets	10	采集数据份，必须能被timeInMilliseconds整除
       hystrix.command.default.metrics.rollingPercentile.enabled	true	开启采集滚动百分比
       hystrix.command.default.metrics.rollingPercentile.timeInMilliseconds	60000	滚动百分收集时间段
       hystrix.command.default.metrics.rollingPercentile.numBuckets	6	滚动百分比收集份数，必须能被timeInMilliseconds整除
       hystrix.command.default.metrics.rollingPercentile.bucketSize	100	每份数据的最大统计请求量
       hystrix.command.default.metrics.healthSnapshot.intervalInMilliseconds	500	健康检查间隔时间
       hystrix.command.default.requestCache.enabled	true	开启请求缓存，HystrixRequestCache
       hystrix.command.default.requestLog.enabled	true	开启请求日志，记录在HystrixRequestLog
       hystrix.collapser.default.maxRequestsInBatch	Integer.MAX_VALUE	单批次最大请求量
       hystrix.collapser.default.timerDelayInMilliseconds	10	批次请求延迟启动时间
       hystrix.collapser.default.requestCache.enabled	true	开启批次请求缓存, HystrixCollapser.execute(), HystrixCollapser.queue()
       hystrix.threadpool.default.coreSize	10	核心线程数
       hystrix.threadpool.default.maximumSize	10	最大线程数
       hystrix.threadpool.default.maxQueueSize	-1	最大阻塞线程队列
       hystrix.threadpool.default.queueSizeRejectionThreshold	5	队列上限，超过会拒绝请求
       hystrix.threadpool.default.keepAliveTimeMinutes	1	线程保持活跃时间（分钟）
       hystrix.threadpool.default.allowMaximumSizeToDivergeFromCoreSize	false	启用maximumSize参数
       hystrix.threadpool.default.metrics.rollingStats.timeInMilliseconds	10000	线程池数据采集时间段
       hystrix.threadpool.default.metrics.rollingStats.numBuckets	10	线程池数据采集份数
   可视化组件
   
   视图hystrix-dashboard
   
       <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
       </dependency>
       @EnableTurbine
       @EnableHystrixDashboard
  启动工程后，访问路径：http://localhost:9001/hystrix
  
  在打开的页面输入需要监控的链接，如：http://localhost:8081/actuator/hystrix.stream     
  汇总监控turbine
  能够使用turbine的前提条件时，所有的服务必须在同一个服务注册中心（同一个eureka），每个待监控的服务都必须有监控路径（/actuator/hystrix.stream）
  
  添加依赖   
  
      <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
      </dependency>
      
配置需要监控的服务

在application.properties中添加配置，配置每个需要监控的服务名称

    turbine.app-config=turbine,hystrix-provider,hystrix-consumer
启动工程，在打开的页面输入汇总监控链接，如：http://localhost:9001/turbine.stream


