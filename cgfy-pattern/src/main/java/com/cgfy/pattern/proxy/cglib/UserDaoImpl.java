package com.cgfy.pattern.proxy.cglib;

/**
 * 接口实现
 * 目标对象
 */
public class UserDaoImpl{
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
