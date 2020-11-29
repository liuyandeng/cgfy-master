package com.cgfy.mybatis.base.aware;


import com.cgfy.mybatis.base.cache.CacheManager;
import com.cgfy.mybatis.base.util.Globals;
import com.cgfy.mybatis.base.util.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * 获取上下文
 * @see # https://www.jianshu.com/p/4c0723615a52
 */
@Component
public class SpringContextAware implements ApplicationContextAware {
    public SpringContextAware() {
        //System.out.println("此时servletContext还未被依赖注入: servletContext = " + servletContext);
    }
    @Resource
    private ServletContext servletContext;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextAware.applicationContext = applicationContext;

        /** 构建缓存管理实例 **/
        CacheManager.creatInstance(applicationContext);

        System.out.println("system resource is initializing...");

        //初始化Globals中的servletContext
        Globals.getInstance().WEB_SERVLET_CONTEXT = servletContext;

        /**20200902注释,初始化获取码表信息**/
        //加载静态资源
         //ResourceService resService = applicationContext.getBean("resService", ResourceService.class);
         //加载码表
        //resService.loadCodeTable();
        System.out.println("system resource was initialized!");
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }


    /**
     * 为RedisUtil工具初始化redisTemplate
     * '@PostConstruct该注解被用来修饰一个非静态的void（）方法。被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，
     * 并且只会被服务器执行一次。该注解的方法在整个Bean初始化中的执行顺序：
     * Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     * 如果想在生成对象时完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入,可以使用@PostConstruct注解一个方法来完成初始化，
     * '@PostConstruct注解的方法将会在依赖注入完成后被自动调用。
     */
    @PostConstruct
    public void initRedisTemplate() {
        //RedisTemplate已在RedisConfig.java的redisTemplate方法中序列化
        RedisTemplate<String, Object> redisTemplate=(RedisTemplate<String, Object>) getBean("redisTemplate");
        RedisUtil.setRedisTemplate(redisTemplate);
    }

}
