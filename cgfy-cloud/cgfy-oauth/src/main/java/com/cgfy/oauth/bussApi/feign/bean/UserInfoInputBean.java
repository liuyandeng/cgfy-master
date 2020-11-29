package com.cgfy.oauth.bussApi.feign.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 平台用户信息新增更新输入用Bean
 *
 * @author cgfy_web
 */
public class UserInfoInputBean {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @Size(max = 32)
    @NotNull
    private String id;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    @Size(max = 32)
    private String orgId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    @Size(max = 128)
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @Size(max = 128)
    private String password;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    @Size(max = 128)
    private String name;

    /**
     * 用户名称英文
     */
    @ApiModelProperty(value = "用户名称英文")
    @Size(max = 128)
    private String nameEn;

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    @Size(max = 128)
    private String code;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @Size(max = 32)
    private String orderNo;

    /**
     * 用户类别（1_内部用户，2_外部用户）
     */
    @ApiModelProperty(value = "用户类别（1_内部用户，2_外部用户）")
    @Size(max = 1)
    private String category;

    /**
     * 状态（0_活动，1_禁用，2_删除）
     */
    @ApiModelProperty(value = "状态（0_活动，1_禁用，2_删除）")
    @Size(max = 1)
    private String status;

    /**
     * 性别（0_未知，1_男，2_女）
     */
    @ApiModelProperty(value = "性别（0_未知，1_男，2_女）")
    @Size(max = 2)
    private String gender;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private java.util.Date birthday;

    /**
     * 允许访问开始时间
     */
    @ApiModelProperty(value = "允许访问开始时间")
    private java.util.Date accessStartDate;

    /**
     * 允许访问结束时间
     */
    @ApiModelProperty(value = "允许访问结束时间")
    private java.util.Date accessEndDate;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @Size(max = 50)
    private String nike;

    /**
     * 移动电话
     */
    @ApiModelProperty(value = "移动电话")
    @Size(max = 16)
    private String mobilePhone;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    @Size(max = 256)
    private String email;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    @Size(max = 256)
    private String address;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @Size(max = 18)
    private String cardId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @Size(max = 32)
    private String fcUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private java.util.Date fcTime;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value = "最后修改人")
    @Size(max = 32)
    private String lmUser;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private java.util.Date lmTime;

    /**
     * 用户头像路径
     */
    @ApiModelProperty(value = "用户头像路径")
    @Size(max = 500)
    private String headPath;

    /**
     * 账户类型 (0_真实用户 1_内置用户 2_虚拟用户)
     */
    @ApiModelProperty(value = "账户类型 (0_真实用户 1_内置用户 2_虚拟用户)")
    @Size(max = 1)
    private String accountType;

    /**
     * 获取主键ID
     *
     * @return 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取机构ID
     *
     * @return 机构ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置机构ID
     *
     * @param orgId 机构ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取登录名
     *
     * @return 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * 获取密码
     *
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户名称
     *
     * @return 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称
     *
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户名称英文
     *
     * @return 用户名称英文
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置用户名称英文
     *
     * @param nameEn 用户名称英文
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * 获取用户编码
     *
     * @return 用户编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置用户编码
     *
     * @param code 用户编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取排序号
     *
     * @return 排序号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置排序号
     *
     * @param orderNo 排序号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取用户类别（1_内部用户，2_外部用户）
     *
     * @return 用户类别（1_内部用户，2_外部用户）
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置用户类别（1_内部用户，2_外部用户）
     *
     * @param category 用户类别（1_内部用户，2_外部用户）
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取状态（0_活动，1_禁用，2_删除）
     *
     * @return 状态（0_活动，1_禁用，2_删除）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（0_活动，1_禁用，2_删除）
     *
     * @param status 状态（0_活动，1_禁用，2_删除）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取性别（0_未知，1_男，2_女）
     *
     * @return 性别（0_未知，1_男，2_女）
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别（0_未知，1_男，2_女）
     *
     * @param gender 性别（0_未知，1_男，2_女）
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     *
     * @return 出生日期
     */
    public java.util.Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取允许访问开始时间
     *
     * @return 允许访问开始时间
     */
    public java.util.Date getAccessStartDate() {
        return accessStartDate;
    }

    /**
     * 设置允许访问开始时间
     *
     * @param accessStartDate 允许访问开始时间
     */
    public void setAccessStartDate(java.util.Date accessStartDate) {
        this.accessStartDate = accessStartDate;
    }

    /**
     * 获取允许访问结束时间
     *
     * @return 允许访问结束时间
     */
    public java.util.Date getAccessEndDate() {
        return accessEndDate;
    }

    /**
     * 设置允许访问结束时间
     *
     * @param accessEndDate 允许访问结束时间
     */
    public void setAccessEndDate(java.util.Date accessEndDate) {
        this.accessEndDate = accessEndDate;
    }

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    public String getNike() {
        return nike;
    }

    /**
     * 设置昵称
     *
     * @param nike 昵称
     */
    public void setNike(String nike) {
        this.nike = nike;
    }

    /**
     * 获取移动电话
     *
     * @return 移动电话
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置移动电话
     *
     * @param mobilePhone 移动电话
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取电子邮件
     *
     * @return 电子邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮件
     *
     * @param email 电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取联系地址
     *
     * @return 联系地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置联系地址
     *
     * @param address 联系地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取身份证号
     *
     * @return 身份证号
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置身份证号
     *
     * @param cardId 身份证号
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取创建人
     *
     * @return 创建人
     */
    public String getFcUser() {
        return fcUser;
    }

    /**
     * 设置创建人
     *
     * @param fcUser 创建人
     */
    public void setFcUser(String fcUser) {
        this.fcUser = fcUser;
    }

    /**
     * 获取创建时间
     *
     * @return 创建时间
     */
    public java.util.Date getFcTime() {
        return fcTime;
    }

    /**
     * 设置创建时间
     *
     * @param fcTime 创建时间
     */
    public void setFcTime(java.util.Date fcTime) {
        this.fcTime = fcTime;
    }

    /**
     * 获取最后修改人
     *
     * @return 最后修改人
     */
    public String getLmUser() {
        return lmUser;
    }

    /**
     * 设置最后修改人
     *
     * @param lmUser 最后修改人
     */
    public void setLmUser(String lmUser) {
        this.lmUser = lmUser;
    }

    /**
     * 获取最后修改时间
     *
     * @return 最后修改时间
     */
    public java.util.Date getLmTime() {
        return lmTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lmTime 最后修改时间
     */
    public void setLmTime(java.util.Date lmTime) {
        this.lmTime = lmTime;
    }

    /**
     * 获取用户头像路径
     *
     * @return 用户头像路径
     */
    public String getHeadPath() {
        return headPath;
    }

    /**
     * 设置用户头像路径
     *
     * @param headPath 用户头像路径
     */
    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    /**
     * 获取账户类型 (0_真实用户 1_内置用户 2_虚拟用户)
     *
     * @return 账户类型 (0_真实用户 1_内置用户 2_虚拟用户)
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 设置账户类型 (0_真实用户 1_内置用户 2_虚拟用户)
     *
     * @param accountType 账户类型 (0_真实用户 1_内置用户 2_虚拟用户)
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}