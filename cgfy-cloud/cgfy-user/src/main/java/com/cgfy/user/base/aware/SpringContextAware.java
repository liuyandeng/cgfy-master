package com.cgfy.user.base.aware;


import com.cgfy.user.base.cache.CacheManager;
import com.cgfy.user.base.util.Globals;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

/**
 * 获取上下文
 * @see # https://www.jianshu.com/p/4c0723615a52
 */
@Component
public class SpringContextAware implements ApplicationContextAware {

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

        //20200902注释,初始化获取码表信息
//        ResourceService resService = applicationContext.getBean("resService", ResourceService.class);
//        resService.loadCodeTable();

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

}
