package com.cgfy.pattern.proxy.dynamicproxy;

/**
 * 接口实现
 * 目标对象
 */
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
