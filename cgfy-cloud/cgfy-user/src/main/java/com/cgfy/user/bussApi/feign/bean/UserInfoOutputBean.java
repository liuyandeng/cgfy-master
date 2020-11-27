package com.cgfy.user.bussApi.feign.bean;

import com.cgfy.user.base.bean.BaseSelectField;
import com.cgfy.user.base.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;

/**
 * MetaBean
 *
 * @author qiucw 2018.05.18
 */
public class UserInfoOutputBean extends BaseSelectField {

	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    private String orgId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名")
    private String loginName;

    /**
     * 用户名称
     */
    @ApiModelProperty(value = "用户名称")
    private String name;

    /**
     * 用户名称英文
     */
    @ApiModelProperty(value = "用户名称英文")
    private String nameEn;

    /**
     * 用户编码
     */
    @ApiModelProperty(value = "用户编码")
    private String code;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private String orderNo;

    /**
     * 用户类别（1_内部用户，2_外部用户）
     */
    @ApiModelProperty(value = "用户类别（1_内部用户，2_外部用户）")
    private String category;

    /**
     * 状态（0_活动，1_禁用，2_删除）
     */
    @ApiModelProperty(value = "状态（0_活动，1_禁用，2_删除）")
    private String status;

    /**
     * 性别（0_未知，1_男，2_女）
     */
    @ApiModelProperty(value = "性别（0_未知，1_男，2_女）")
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
    private String mobilePhone;

    /**
     * 固定电话
     */
    @ApiModelProperty(value = "固定电话")
    private String telephone;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    private String email;

    /**
     * 家庭电话
     */
    @ApiModelProperty(value = "家庭电话")
    private String homePhone;

    /**
     * 传真号码
     */
    @ApiModelProperty(value = "传真号码")
    private String faxno;

    /**
     * QQ号
     */
    @ApiModelProperty(value = "QQ号")
    private String qq;

    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wechat;

    /**
     * 微博号
     */
    @ApiModelProperty(value = "微博号")
    private String microBlog;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String address;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    /**
     * 办公地点
     */
    @ApiModelProperty(value = "办公地点")
    private String office;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
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
    private String employerId;

    /**
     * 政治面貌（参看码表USER_POLITICAL_STATUS）
     */
    @ApiModelProperty(value = "政治面貌（参看码表USER_POLITICAL_STATUS）")
    private String politicalStatus;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 密码最后修改时间
     */
    @ApiModelProperty(value = "密码最后修改时间")
    private String passModDate;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "机构_主键ID")
    private String org_id;

    /**
     * 上级ID
     */
    @ApiModelProperty(value = "机构_上级ID")
    private String org_parentId;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构_机构名称")
    private String org_name;

    /**
     * 机构名称英文
     */
    @ApiModelProperty(value = "机构_机构名称英文")
    private String org_nameEn;

    /**
     * 机构简称
     */
    @ApiModelProperty(value = "机构_机构简称")
    private String org_shortName;

    /**
     * 机构别名
     */
    @ApiModelProperty(value = "机构_机构别名")
    private String org_anotherName;

    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构_机构代码")
    private String org_code;

    /**
     * 机构等级,起始为0
     */
    @ApiModelProperty(value = "机构_机构等级,起始为0")
    private Integer org_grade;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "机构_排序号")
    private String org_orderNo;

    /**
     * 机构类别（1_公司/企业，2_部门/科室）
     */
    @ApiModelProperty(value = "机构_机构类别（1_公司/企业，2_部门/科室）")
    private String org_category;

    /**
     * 归属机构（如某个部门归属在其某个上级机构）
     */
    @ApiModelProperty(value = "机构_归属机构（如某个部门归属在其某个上级机构）")
    private String org_ascriptionOrg;

    /**
     * 机构类型（码表中的ORG_TYPE_CODE）
     */
    @ApiModelProperty(value = "机构_机构类型（码表中的ORG_TYPE_CODE）")
    private String org_type;

    /**
     * 机构性质（码表中的ORG_NATURE_CODE）
     */
    @ApiModelProperty(value = "机构_机构性质（码表中的ORG_NATURE_CODE）")
    private String org_nature;

    /**
     * 机构状态（0_活动，1_禁用，2_删除）
     */
    @ApiModelProperty(value = "机构_机构状态（0_活动，1_禁用，2_删除）")
    private String org_status;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "机构_邮政编码")
    private String org_zipCode;

    /**
     * 机构地址
     */
    @ApiModelProperty(value = "机构_机构地址")
    private String org_address;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "机构_电子邮件")
    private String org_email;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "机构_联系电话")
    private String org_telephone;

    /**
     * 是否是临时
     */
    @ApiModelProperty(value = "机构_是否是临时")
    private String org_isTemp;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "机构_是否隐藏")
    private String org_isHidden;



	@ApiModelProperty(value = "头像路径")
	private String headPath;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {

		this.birthday = DateUtil.getFormatDate(birthday,"yyyy-MM-dd");
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getFaxno() {
		return faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getMicroBlog() {
		return microBlog;
	}

	public void setMicroBlog(String microBlog) {
		this.microBlog = microBlog;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {

		this.workDate = DateUtil.getFormatDate(workDate,"yyyy-MM-dd");
	}

	public String getEmployedDate() {
		return employedDate;
	}

	public void setEmployedDate(String employedDate) {

		this.employedDate = DateUtil.getFormatDate(employedDate,"yyyy-MM-dd");
	}

	public String getEmployerId() {
		return employerId;
	}

	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPassModDate() {
		return passModDate;
	}

	public void setPassModDate(String passModDate) {

		this.passModDate = DateUtil.getFormatDate(passModDate,"yyyy-MM-dd HH:mm:ss");;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_parentId() {
		return org_parentId;
	}

	public void setOrg_parentId(String org_parentId) {
		this.org_parentId = org_parentId;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getOrg_nameEn() {
		return org_nameEn;
	}

	public void setOrg_nameEn(String org_nameEn) {
		this.org_nameEn = org_nameEn;
	}

	public String getOrg_shortName() {
		return org_shortName;
	}

	public void setOrg_shortName(String org_shortName) {
		this.org_shortName = org_shortName;
	}

	public String getOrg_anotherName() {
		return org_anotherName;
	}

	public void setOrg_anotherName(String org_anotherName) {
		this.org_anotherName = org_anotherName;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public Integer getOrg_grade() {
		return org_grade;
	}

	public void setOrg_grade(Integer org_grade) {
		this.org_grade = org_grade;
	}

	public String getOrg_orderNo() {
		return org_orderNo;
	}

	public void setOrg_orderNo(String org_orderNo) {
		this.org_orderNo = org_orderNo;
	}

	public String getOrg_category() {
		return org_category;
	}

	public void setOrg_category(String org_category) {
		this.org_category = org_category;
	}

	public String getOrg_ascriptionOrg() {
		return org_ascriptionOrg;
	}

	public void setOrg_ascriptionOrg(String org_ascriptionOrg) {
		this.org_ascriptionOrg = org_ascriptionOrg;
	}

	public String getOrg_type() {
		return org_type;
	}

	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}

	public String getOrg_nature() {
		return org_nature;
	}

	public void setOrg_nature(String org_nature) {
		this.org_nature = org_nature;
	}

	public String getOrg_status() {
		return org_status;
	}

	public void setOrg_status(String org_status) {
		this.org_status = org_status;
	}

	public String getOrg_zipCode() {
		return org_zipCode;
	}

	public void setOrg_zipCode(String org_zipCode) {
		this.org_zipCode = org_zipCode;
	}

	public String getOrg_address() {
		return org_address;
	}

	public void setOrg_address(String org_address) {
		this.org_address = org_address;
	}

	public String getOrg_email() {
		return org_email;
	}

	public void setOrg_email(String org_email) {
		this.org_email = org_email;
	}

	public String getOrg_telephone() {
		return org_telephone;
	}

	public void setOrg_telephone(String org_telephone) {
		this.org_telephone = org_telephone;
	}

	public String getOrg_isTemp() {
		return org_isTemp;
	}

	public void setOrg_isTemp(String org_isTemp) {
		this.org_isTemp = org_isTemp;
	}

	public String getOrg_isHidden() {
		return org_isHidden;
	}

	public void setOrg_isHidden(String org_isHidden) {
		this.org_isHidden = org_isHidden;
	}
	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
 }
