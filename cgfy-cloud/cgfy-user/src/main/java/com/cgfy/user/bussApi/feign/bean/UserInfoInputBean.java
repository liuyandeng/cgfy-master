package com.cgfy.user.bussApi.feign.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 平台用户信息插入用Bean
 */
public class UserInfoInputBean {
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
    @NotNull
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
    @NotNull
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
    @NotNull
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 移动电话
     */
    @ApiModelProperty(value = "移动电话")
    @Size(max = 16)
    private String mobilePhone;

    /**
     * 固定电话
     */
    @ApiModelProperty(value = "固定电话")
    @Size(max = 16)
    private String telephone;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    @Size(max = 256)
    private String email;

    /**
     * 家庭电话
     */
    @ApiModelProperty(value = "家庭电话")
    @Size(max = 16)
    private String homePhone;

    /**
     * 传真号码
     */
    @ApiModelProperty(value = "传真号码")
    @Size(max = 32)
    private String faxno;

    /**
     * QQ号
     */
    @ApiModelProperty(value = "QQ号")
    @Size(max = 128)
    private String qq;

    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    @Size(max = 128)
    private String wechat;

    /**
     * 微博号
     */
    @ApiModelProperty(value = "微博号")
    @Size(max = 128)
    private String microBlog;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    @Size(max = 256)
    private String address;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    @Size(max = 16)
    private String zipCode;

    /**
     * 办公地点
     */
    @ApiModelProperty(value = "办公地点")
    @Size(max = 256)
    private String office;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @Size(max = 18)
    private String cardId;

    /**
     * 参加工作日期
     */
    @ApiModelProperty(value = "参加工作日期")
    private Date workDate;

    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    private Date employedDate;

    /**
     * 工作证号码
     */
    @ApiModelProperty(value = "工作证号码")
    @Size(max = 36)
    private String employerId;

    /**
     * 政治面貌（参看码表USER_POLITICAL_STATUS）
     */
    @ApiModelProperty(value = "政治面貌（参看码表USER_POLITICAL_STATUS）")
    @Size(max = 4)
    private String politicalStatus;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @Size(max = 255)
    private String remarks;

    /**
     * 密码最后修改时间
     */
    @ApiModelProperty(value = "密码最后修改时间")
    private Date passModDate;

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
    private Date fcTime;

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
    private Date lmTime;

    /**
     * 允许访问开始时间
     */
    @ApiModelProperty(value = "允许访问开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date accessStartDate;

    /**
     * 允许访问结束时间
     */
    @ApiModelProperty(value = "允许访问结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date accessEndDate;

    /**
     * 个人签名
     */
    @ApiModelProperty(value = "个人签名")
    private String motto;

    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String postStatus;

    /**
     * 头像附件ID
     */
    @ApiModelProperty(value = "头像附件ID")
    private Long attachmentId;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nick;

    /**
     * 是否同步
     */
    @ApiModelProperty(value = "是否同步")
    private String isTrans;

    /**
     * 是否借调人员
     */
    @ApiModelProperty(value = "是否借调人员")
    private String isBorrow;

    /**
     * 账户类型
     */
    @ApiModelProperty(value = "账户类型")
    private String accountType;

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
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
     * 获取固定电话
     *
     * @return 固定电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置固定电话
     *
     * @param telephone 固定电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * 获取家庭电话
     *
     * @return 家庭电话
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * 设置家庭电话
     *
     * @param homePhone 家庭电话
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * 获取传真号码
     *
     * @return 传真号码
     */
    public String getFaxno() {
        return faxno;
    }

    /**
     * 设置传真号码
     *
     * @param faxno 传真号码
     */
    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }

    /**
     * 获取QQ号
     *
     * @return QQ号
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ号
     *
     * @param qq QQ号
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取微信号
     *
     * @return 微信号
     */
    public String getWechat() {
        return wechat;
    }

    /**
     * 设置微信号
     *
     * @param wechat 微信号
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    /**
     * 获取微博号
     *
     * @return 微博号
     */
    public String getMicroBlog() {
        return microBlog;
    }

    /**
     * 设置微博号
     *
     * @param microBlog 微博号
     */
    public void setMicroBlog(String microBlog) {
        this.microBlog = microBlog;
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
     * 获取邮政编码
     *
     * @return 邮政编码
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * 设置邮政编码
     *
     * @param zipCode 邮政编码
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取办公地点
     *
     * @return 办公地点
     */
    public String getOffice() {
        return office;
    }

    /**
     * 设置办公地点
     *
     * @param office 办公地点
     */
    public void setOffice(String office) {
        this.office = office;
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
     * 获取参加工作日期
     *
     * @return 参加工作日期
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * 设置参加工作日期
     *
     * @param workDate 参加工作日期
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取入职日期
     *
     * @return 入职日期
     */
    public Date getEmployedDate() {
        return employedDate;
    }

    /**
     * 设置入职日期
     *
     * @param employedDate 入职日期
     */
    public void setEmployedDate(Date employedDate) {
        this.employedDate = employedDate;
    }

    /**
     * 获取工作证号码
     *
     * @return 工作证号码
     */
    public String getEmployerId() {
        return employerId;
    }

    /**
     * 设置工作证号码
     *
     * @param employerId 工作证号码
     */
    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    /**
     * 获取政治面貌（参看码表USER_POLITICAL_STATUS）
     *
     * @return 政治面貌（参看码表USER_POLITICAL_STATUS）
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置政治面貌（参看码表USER_POLITICAL_STATUS）
     *
     * @param politicalStatus 政治面貌（参看码表USER_POLITICAL_STATUS）
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取备注信息
     *
     * @return 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取密码最后修改时间
     *
     * @return 密码最后修改时间
     */
    public Date getPassModDate() {
        return passModDate;
    }

    /**
     * 设置密码最后修改时间
     *
     * @param passModDate 密码最后修改时间
     */
    public void setPassModDate(Date passModDate) {
        this.passModDate = passModDate;
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
    public Date getFcTime() {
        return fcTime;
    }

    /**
     * 设置创建时间
     *
     * @param fcTime 创建时间
     */
    public void setFcTime(Date fcTime) {
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
    public Date getLmTime() {
        return lmTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lmTime 最后修改时间
     */
    public void setLmTime(Date lmTime) {
        this.lmTime = lmTime;
    }

    /**
     * 允许访问开始时间
     *
     * @return 允许访问开始时间
     */
    public Date getAccessStartDate() {
        return accessStartDate;
    }

    /**
     * 允许访问开始时间
     *
     * @param accessStartDate 允许访问开始时间
     */
    public void setAccessStartDate(Date accessStartDate) {
        this.accessStartDate = accessStartDate;
    }

    /**
     * 允许访问结束时间
     *
     * @return 允许访问结束时间
     */
    public Date getAccessEndDate() {
        return accessEndDate;
    }

    /**
     * 允许访问结束时间
     *
     * @param accessEndDate 允许访问结束时间
     */
    public void setAccessEndDate(Date accessEndDate) {
        this.accessEndDate = accessEndDate;
    }


    /**
     * 个人签名
     */
    public String getMotto() { return motto; }

    /**
     * 个人签名
     */
    public void setMotto(String motto) { this.motto = motto; }


    /**
     * 职务
     *
     * @return 职务
     */
    public String getPostStatus() {
        return postStatus;
    }

    /**
     * 职务
     *
     * @param postStatus 职务
     */
    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    /**
     * 头像附件ID
     *
     * @return 头像附件ID
     */
    public Long getAttachmentId() {return attachmentId;}

    /**
     * 头像附件ID
     *
     * @param attachmentId 头像附件ID
     */
    public void setAttachmentId(Long attachmentId) {this.attachmentId = attachmentId;}

    /**
     * 获取用户nick名
     *
     * @return 用户nick名
     */
    public String getNick() {
        return nick;
    }

    /**
     * 设置用户nick名
     *
     * @param nick 用户nick名
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 获取是否同步
     *
     * @return 是否同步
     */
    public String getIsTrans() {
        return isTrans;
    }

    /**
     * 设置是否同步
     *
     * @param isTrans 是否同步
     */
    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans;
    }

    /**
     * 获取是否借调人员
     *
     * @return 是否借调人员
     */
    public String getIsBorrow() {
        return isBorrow;
    }

    /**
     * 设置是否借调人员
     *
     * @param isBorrow 是否借调人员
     */
    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow;
    }


    /**
     * 账户类型
     *
     * @return 账户类型
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 账户类型
     *
     * @param accountType 账户类型
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}