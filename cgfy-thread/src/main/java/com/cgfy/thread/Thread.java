package com.cgfy.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Thread {
    /**
     * @link - https://blog.csdn.net/weixin_41884010/article/details/88844946
     * 启动springboot的启动类，springboot会帮助我们准备所有的环境，包括server，监听器，装配spring的上下文等等
     * SpringApplication.run一共做了两件事
     * 1.创建SpringApplication对象；在对象初始化时保存事件监听器，容器初始化类以及判断是否为web应用，保存包含main方法的主配置类。
     * 2.利用创建好的SpringApplication对象调用run方法；准备spring的上下文，完成容器的初始化，创建，加载等。会在不同的时机触发监听器的不同事件。
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Thread.class, args);
        //context.getBeanFactory();
    }


    /*

    创建SpringApplication对象
    在执行SpringApplication的run方法时首先会创建一个SpringApplication类的对象，利用构造方法创建SpringApplication对象时会调用initialize方法。
    SpringApplication.run()方法的基本调用流程：

    public static ConfigurableApplicationContext run(Object source, String... args) {
        return run(new Object[] { source }, args);
    }

    public static ConfigurableApplicationContext run(Object[] sources, String[] args) {
        return new SpringApplication(sources).run(args);
    }

    public SpringApplication(Object... sources) {
        initialize(sources);
    }

    */




   /*
    initialize方法：

    private void initialize(Object[] sources) {
        // 在sources不为空时，保存配置类
        if (sources != null && sources.length > 0) {
            this.sources.addAll(Arrays.asList(sources));
        }
        // 判断是否为web应用
        this.webEnvironment = deduceWebEnvironment();
        // 获取并保存容器初始化类，通常在web应用容器初始化使用
        // 利用loadFactoryNames方法从路径MEAT-INF/spring.factories中找到所有的ApplicationContextInitializer
        setInitializers((Collection) getSpringFactoriesInstances(
                ApplicationContextInitializer.class));
        // 获取并保存监听器
        // 利用loadFactoryNames方法从路径MEAT-INF/spring.factories中找到所有的ApplicationListener
        setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
        // 从堆栈信息获取包含main方法的主配置类
        this.mainApplicationClass = deduceMainApplicationClass();
    }*/


    /*调用run方法
    public ConfigurableApplicationContext run(String... args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ConfigurableApplicationContext context = null;
        FailureAnalyzers analyzers = null;
        // 配置属性
        configureHeadlessProperty();
        // 获取监听器
        // 利用loadFactoryNames方法从路径MEAT-INF/spring.factories中找到所有的SpringApplicationRunListener
        SpringApplicationRunListeners listeners = getRunListeners(args);
        // 启动监听
        // 调用每个SpringApplicationRunListener的starting方法
        listeners.starting();
        try {
            // 将参数封装到ApplicationArguments对象中
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(
                    args);
            // 准备环境
            // 触发监听事件——调用每个SpringApplicationRunListener的environmentPrepared方法
            ConfigurableEnvironment environment = prepareEnvironment(listeners,
                    applicationArguments);
            // 从环境中取出Banner并打印
            Banner printedBanner = printBanner(environment);
            // 依据是否为web环境创建web容器或者普通的IOC容器
            context = createApplicationContext();
            analyzers = new FailureAnalyzers(context);
            // 准备上下文
            // 1.将environment保存到容器中
            // 2.触发监听事件——调用每个SpringApplicationRunListeners的contextPrepared方法
            // 3.调用ConfigurableListableBeanFactory的registerSingleton方法向容器中注入applicationArguments与printedBanner
            // 4.触发监听事件——调用每个SpringApplicationRunListeners的contextLoaded方法
            prepareContext(context, environment, listeners, applicationArguments,
                    printedBanner);
            // 刷新容器，完成组件的扫描，创建，加载等
            refreshContext(context);
            afterRefresh(context, applicationArguments);
            // 触发监听事件——调用每个SpringApplicationRunListener的finished方法
            listeners.finished(context, null);
            stopWatch.stop();
            if (this.logStartupInfo) {
                new StartupInfoLogger(this.mainApplicationClass)
                        .logStarted(getApplicationLog(), stopWatch);
            }
            // 返回容器
            return context;
        } catch (Throwable ex) {
            handleRunFailure(context, listeners, analyzers, ex);
            throw new IllegalStateException(ex);
        }
    }*/
}
