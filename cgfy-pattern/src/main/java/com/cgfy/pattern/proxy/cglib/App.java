package com.cgfy.pattern.proxy.cglib;

import org.junit.Test;

/**
 * 测试类
 */
public class App {

    @Test
    public void test(){
        //目标对象
        UserDaoImpl target = new UserDaoImpl();

        //代理对象
        UserDaoImpl proxy = (UserDaoImpl)new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}