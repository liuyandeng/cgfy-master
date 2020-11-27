package com.cgfy.mybatis.bussApi.bean;

import com.cgfy.mybatis.base.bean.BaseSelectField;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * cgfy输出用Bean
 *
 * @author cgfy_web
 */
public class TestGenOutputBean extends BaseSelectField {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Size(max = 100)
    @NotNull
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Size(max = 100)
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @Size(max = 100)
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @Size(max = 100)
    private String mobilePhone;

    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址")
    @Size(max = 100)
    private String homeAddTest;

    /**
     * 默认构造函数
     */
    public TestGenOutputBean() {
        
    }

    /**
     * 默认构造函数
     */
    public TestGenOutputBean(com.cgfy.mybatis.bussApi.domain.model.TestGen input) {
        
        // 主键
        this.id = input.getId();
        // 姓名
        this.name = input.getName();
        // 性别
        this.sex = input.getSex();
        // 年龄
        this.age = input.getAge();
        // 电话
        this.mobilePhone = input.getMobilePhone();
        // 家庭住址
        this.homeAddTest = input.getHomeAddTest();
    }

    /**
     * 获取主键
     *
     * @return 主键
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
     * @return 姓名
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
     * @return 性别
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
     * @return 年龄
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
     * @return 电话
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
     * @return 家庭住址
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