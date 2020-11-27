package com.cgfy.mybatis.bussApi.domain.model;

import com.cgfy.mybatis.base.domain.model.BaseModel;
import java.io.Serializable;
import javax.persistence.*;

/**
 * cgfy
 *
 * @author cgfy_web
 */
@Table(name = "test_gen")
public class TestGen implements BaseModel, Serializable {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 家庭住址
     */
    @Column(name = "home_add_test")
    private String homeAddTest;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取电话
     *
     * @return mobile_phone - 电话
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置电话
     *
     * @param mobilePhone 电话
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取家庭住址
     *
     * @return home_add_test - 家庭住址
     */
    public String getHomeAddTest() {
        return homeAddTest;
    }

    /**
     * 设置家庭住址
     *
     * @param homeAddTest 家庭住址
     */
    public void setHomeAddTest(String homeAddTest) {
        this.homeAddTest = homeAddTest;
    }
}