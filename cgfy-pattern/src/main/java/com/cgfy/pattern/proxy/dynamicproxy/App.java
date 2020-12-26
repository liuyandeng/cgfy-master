package com.cgfy.pattern.proxy.dynamicproxy;


/**
 * 测试类
 */
public class App {
    public static void main(String[] args) {
        // 目标对象
        UserDao target = new UserDaoImpl();
        // 【原始的类型 class cn.itcast.b_dynamic.UserDao】
        System.out.println("目标对象:"+target.getClass());

        // 给目标对象，创建代理对象
        UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0   内存中动态生成的代理对象
        System.out.println("代理对象:"+proxy.getClass());

        // 执行方法   【代理对象】
        proxy.save();
    }
}