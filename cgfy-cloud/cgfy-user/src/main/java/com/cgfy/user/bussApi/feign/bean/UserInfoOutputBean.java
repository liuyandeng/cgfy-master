package com.cgfy.user.bussApi.feign.bean;

import com.cgfy.user.base.bean.BaseSelectField;
import com.cgfy.user.base.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 平台用户信息输出用Bean
 *
 * @author liuyandeng
 */
public class UserInfoOutputBean extends BaseSelectField {
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
    private String birthday;

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
    private String workDate;

    /**
     * 入职日期
     */
    @ApiModelProperty(value = "入职日期")
    private String employedDate;

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
    private String passModDate;

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
    private String fcTime;

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
    private String lmTime;

    /**
     * 默认构造函数
     */
    public UserInfoOutputBean() {

    }

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
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(String birthday) {
        this.birthday =  DateUtil.getFormatDate(birthday,"yyyy-MM-dd");
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
    public String getWorkDate() {
        return workDate;
    }

    /**
     * 设置参加工作日期
     *
     * @param workDate 参加工作日期
     */
    public void setWorkDate(String workDate) {
        this.workDate = DateUtil.getFormatDate(workDate,"yyyy-MM-dd");
    }

    /**
     * 获取入职日期
     *
     * @return 入职日期
     */
    public String getEmployedDate() {
        return employedDate;
    }

    /**
     * 设置入职日期
     *
     * @param employedDate 入职日期
     */
    public void setEmployedDate(String employedDate) {
        this.employedDate = DateUtil.getFormatDate(employedDate,"yyyy-MM-dd");
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
    public String getPassModDate() {
        return passModDate;
    }

    /**
     * 设置密码最后修改时间
     *
     * @param passModDate 密码最后修改时间
     */
    public void setPassModDate(String passModDate) {
        this.passModDate = DateUtil.getFormatDate(passModDate,"yyyy-MM-dd HH:mm:ss");
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
    public String getFcTime() {
        return fcTime;
    }

    /**
     * 设置创建时间
     *
     * @param fcTime 创建时间
     */
    public void setFcTime(String fcTime) {
        this.fcTime = DateUtil.getFormatDate(fcTime,"yyyy-MM-dd HH:mm:ss");
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
    public String getLmTime() {
        return lmTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lmTime 最后修改时间
     */
    public void setLmTime(String lmTime) {
        this.lmTime = DateUtil.getFormatDate(lmTime,"yyyy-MM-dd HH:mm:ss");
    }
}
