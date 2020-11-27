package com.cgfy.user.bussApi.domain.model;

import com.cgfy.user.base.domain.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * 员工信息
 *
 * @author scgk_hr
 */
@Table(name = "staff_info")
public class StaffInfo implements Serializable, BaseModel {
    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 是否是旧数据（0_是,1_否）
     */
    @Column(name = "is_old")
    private String isOld;

    /**
     * 旧数据id
     */
    @Column(name = "old_id")
    private String oldId;

    /**
     * 登录账号
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 曾用名
     */
    @Column(name = "used_name")
    private String usedName;

    /**
     * 用户头像路径
     */
    @Column(name = "head_path")
    private String headPath;

    /**
     * 职务id
     */
    @Column(name = "duty_id")
    private String dutyId;

    /**
     * 职务名称
     */
    @Column(name = "duty_name")
    private String dutyName;

    /**
     * 职务层级(0_领导班子,1_中层正职,2_中层副职,3_员工)
     */
    @Column(name = "duty_level")
    private String dutyLevel;

    /**
     * 岗位
     */
    private String post;

    /**
     * 所在部门
     */
    @Column(name = "org_name")
    private String orgName;

    /**
     * 所在部门id
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 单位名称
     */
    @Column(name = "unit_name")
    private String unitName;

    /**
     * 单位id
     */
    @Column(name = "unit_id")
    private String unitId;

    /**
     * 性别（0_未知,1_男,2_女）
     */
    private String gender;

    /**
     * 民族
     */
    private String folk;

    /**
     * 贯籍
     */
    @Column(name = "native_place")
    private String nativePlace;

    /**
     * 出生日期
     */
    private String birthday;

    /**
     * 出生地
     */
    @Column(name = "birthday_place")
    private String birthdayPlace;

    /**
     * 户籍性质(0_本市城镇,1_本市农村,2_外埠城镇,3_外埠农村)
     */
    @Column(name = "census_type")
    private String censusType;

    /**
     * 户籍所在地
     */
    @Column(name = "census_place")
    private String censusPlace;

    /**
     * 身份证
     */
    @Column(name = "card_id")
    private String cardId;

    /**
     * 职工类别(0_在岗职工,1_在岗职工(特),2_劳务派遣,3_其他从业人员,4.1_不在岗职工(非内退),4.2_内退,5_退休,6_离职)
     */
    @Column(name = "staff_type")
    private String staffType;

    /**
     * 年龄
     */
    private String age;

    /**
     * 退休年月
     */
    @Column(name = "retire_date")
    private String retireDate;

    /**
     * 健康状况
     */
    private String health;

    /**
     * 政治面貌(0_中共党员,1_共青团员,2_民主党派,3_群众)
     */
    @Column(name = "political_status")
    private String politicalStatus;

    /**
     * 入党(团)时间
     */
    @Column(name = "political_date")
    private String politicalDate;

    /**
     * 婚姻状况(0_未婚,1_已婚,2_离异,3_丧偶)
     */
    private String marriage;

    /**
     * 用户状态（0_活动,1_禁用,2_删除）
     */
    @Column(name = "user_status")
    private String userStatus;

    /**
     * 用户类别（0_内部用户,1_外部用户）
     */
    private String category;

    /**
     * 最高学历(0_博士研究生,1_硕士研究生,2_本科,3_大专,4_高中,5_中专,6_中专以下)
     */
    @Column(name = "high_edu")
    private String highEdu;

    /**
     * 最高学历专业
     */
    @Column(name = "high_major")
    private String highMajor;

    /**
     * 最高学位(0_博士,1_硕士,2_学士,3_无)
     */
    @Column(name = "high_degree")
    private String highDegree;

    /**
     * 联系电话
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 参加工作年月
     */
    @Column(name = "work_date")
    private String workDate;

    /**
     * 工龄
     */
    @Column(name = "work_age")
    private String workAge;

    /**
     * 到本企业年月
     */
    @Column(name = "employed_date")
    private String employedDate;

    /**
     * 到本企业的工龄
     */
    @Column(name = "employed_age")
    private String employedAge;

    /**
     * 现在的职务
     */
    private String job;

    /**
     * 任现职时间
     */
    @Column(name = "job_date")
    private String jobDate;

    /**
     * 现职业时间
     */
    @Column(name = "job_limit")
    private String jobLimit;

    /**
     * 任现职级时间
     */
    @Column(name = "rank_date")
    private String rankDate;

    /**
     * 常住地址(家庭情况)
     */
    private String address;

    /**
     * 户口所在地址(家庭情况)
     */
    @Column(name = "registered_place")
    private String registeredPlace;

    /**
     * 紧急联系人姓名(家庭情况)
     */
    @Column(name = "emerg_name")
    private String emergName;

    /**
     * 与本人关系(家庭情况)
     */
    @Column(name = "emerg_relate")
    private String emergRelate;

    /**
     * 紧急联系电话(家庭情况)
     */
    @Column(name = "emerg_tel")
    private String emergTel;

    /**
     * 紧急联系地址(家庭情况)
     */
    @Column(name = "emerg_address")
    private String emergAddress;

    /**
     * 填表人id
     */
    @Column(name = "fill_id")
    private String fillId;

    /**
     * 填表人姓名
     */
    @Column(name = "fill_name")
    private String fillName;

    /**
     * 填表时间
     */
    @Column(name = "fill_date")
    private Date fillDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 附件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 附件url
     */
    @Column(name = "file_url")
    private String fileUrl;

    /**
     * 附件类型
     */
    @Column(name = "file_type")
    private String fileType;

    /**
     * 是否工人身份(0_是,1_否)
     */
    @Column(name = "is_worker")
    private String isWorker;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建人姓名
     */
    @Column(name = "creator_name")
    private String creatorName;

    /**
     * 创建人部门id
     */
    @Column(name = "organ_id")
    private String organId;

    /**
     * 创建人部门名称
     */
    @Column(name = "organ_name")
    private String organName;

    /**
     * 流程id
     */
    @Column(name = "process_id")
    private String processId;

    /**
     * 提交时间
     */
    private Date committed;

    /**
     * 流程结束时间
     */
    private Date done;

    /**
     * 表单状态(0_已保存,1_审核中,3_ 已驳回,2_已完成)
     */
    private String status;

    /**
     * 当前节点名称
     */
    @Column(name = "last_task")
    private String lastTask;

    /**
     * 流程分支
     */
    @Column(name = "branchline_to")
    private String branchlineTo;

    /**
     * 是否可以编辑(0_是,1_否)
     */
    @Column(name = "is_edit")
    private String isEdit;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取是否是旧数据（0_是,1_否）
     *
     * @return is_old - 是否是旧数据（0_是,1_否）
     */
    public String getIsOld() {
        return isOld;
    }

    /**
     * 设置是否是旧数据（0_是,1_否）
     *
     * @param isOld 是否是旧数据（0_是,1_否）
     */
    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }

    /**
     * 获取旧数据id
     *
     * @return old_id - 旧数据id
     */
    public String getOldId() {
        return oldId;
    }

    /**
     * 设置旧数据id
     *
     * @param oldId 旧数据id
     */
    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    /**
     * 获取登录账号
     *
     * @return login_name - 登录账号
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录账号
     *
     * @param loginName 登录账号
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
     * 获取曾用名
     *
     * @return used_name - 曾用名
     */
    public String getUsedName() {
        return usedName;
    }

    /**
     * 设置曾用名
     *
     * @param usedName 曾用名
     */
    public void setUsedName(String usedName) {
        this.usedName = usedName;
    }

    /**
     * 获取用户头像路径
     *
     * @return head_path - 用户头像路径
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
     * 获取职务id
     *
     * @return duty_id - 职务id
     */
    public String getDutyId() {
        return dutyId;
    }

    /**
     * 设置职务id
     *
     * @param dutyId 职务id
     */
    public void setDutyId(String dutyId) {
        this.dutyId = dutyId;
    }

    /**
     * 获取职务名称
     *
     * @return duty_name - 职务名称
     */
    public String getDutyName() {
        return dutyName;
    }

    /**
     * 设置职务名称
     *
     * @param dutyName 职务名称
     */
    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    /**
     * 获取职务层级(0_领导班子,1_中层正职,2_中层副职,3_员工)
     *
     * @return duty_level - 职务层级(0_领导班子,1_中层正职,2_中层副职,3_员工)
     */
    public String getDutyLevel() {
        return dutyLevel;
    }

    /**
     * 设置职务层级(0_领导班子,1_中层正职,2_中层副职,3_员工)
     *
     * @param dutyLevel 职务层级(0_领导班子,1_中层正职,2_中层副职,3_员工)
     */
    public void setDutyLevel(String dutyLevel) {
        this.dutyLevel = dutyLevel;
    }

    /**
     * 获取岗位
     *
     * @return post - 岗位
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置岗位
     *
     * @param post 岗位
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取所在部门
     *
     * @return org_name - 所在部门
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置所在部门
     *
     * @param orgName 所在部门
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取所在部门id
     *
     * @return org_id - 所在部门id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置所在部门id
     *
     * @param orgId 所在部门id
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取单位名称
     *
     * @return unit_name - 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 设置单位名称
     *
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    /**
     * 获取单位id
     *
     * @return unit_id - 单位id
     */
    public String getUnitId() {
        return unitId;
    }

    /**
     * 设置单位id
     *
     * @param unitId 单位id
     */
    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    /**
     * 获取性别（0_未知,1_男,2_女）
     *
     * @return gender - 性别（0_未知,1_男,2_女）
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置性别（0_未知,1_男,2_女）
     *
     * @param gender 性别（0_未知,1_男,2_女）
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取民族
     *
     * @return folk - 民族
     */
    public String getFolk() {
        return folk;
    }

    /**
     * 设置民族
     *
     * @param folk 民族
     */
    public void setFolk(String folk) {
        this.folk = folk;
    }

    /**
     * 获取贯籍
     *
     * @return native_place - 贯籍
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置贯籍
     *
     * @param nativePlace 贯籍
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
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
        this.birthday = birthday;
    }

    /**
     * 获取出生地
     *
     * @return birthday_place - 出生地
     */
    public String getBirthdayPlace() {
        return birthdayPlace;
    }

    /**
     * 设置出生地
     *
     * @param birthdayPlace 出生地
     */
    public void setBirthdayPlace(String birthdayPlace) {
        this.birthdayPlace = birthdayPlace;
    }

    /**
     * 获取户籍性质(0_本市城镇,1_本市农村,2_外埠城镇,3_外埠农村)
     *
     * @return census_type - 户籍性质(0_本市城镇,1_本市农村,2_外埠城镇,3_外埠农村)
     */
    public String getCensusType() {
        return censusType;
    }

    /**
     * 设置户籍性质(0_本市城镇,1_本市农村,2_外埠城镇,3_外埠农村)
     *
     * @param censusType 户籍性质(0_本市城镇,1_本市农村,2_外埠城镇,3_外埠农村)
     */
    public void setCensusType(String censusType) {
        this.censusType = censusType;
    }

    /**
     * 获取户籍所在地
     *
     * @return census_place - 户籍所在地
     */
    public String getCensusPlace() {
        return censusPlace;
    }

    /**
     * 设置户籍所在地
     *
     * @param censusPlace 户籍所在地
     */
    public void setCensusPlace(String censusPlace) {
        this.censusPlace = censusPlace;
    }

    /**
     * 获取身份证
     *
     * @return card_id - 身份证
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置身份证
     *
     * @param cardId 身份证
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取职工类别(0_在岗职工,1_在岗职工(特),2_劳务派遣,3_其他从业人员,4.1_不在岗职工(非内退),4.2_内退,5_退休,6_离职)
     *
     * @return staff_type - 职工类别(0_在岗职工,1_在岗职工(特),2_劳务派遣,3_其他从业人员,4.1_不在岗职工(非内退),4.2_内退,5_退休,6_离职)
     */
    public String getStaffType() {
        return staffType;
    }

    /**
     * 设置职工类别(0_在岗职工,1_在岗职工(特),2_劳务派遣,3_其他从业人员,4.1_不在岗职工(非内退),4.2_内退,5_退休,6_离职)
     *
     * @param staffType 职工类别(0_在岗职工,1_在岗职工(特),2_劳务派遣,3_其他从业人员,4.1_不在岗职工(非内退),4.2_内退,5_退休,6_离职)
     */
    public void setStaffType(String staffType) {
        this.staffType = staffType;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 获取退休年月
     *
     * @return retire_date - 退休年月
     */
    public String getRetireDate() {
        return retireDate;
    }

    /**
     * 设置退休年月
     *
     * @param retireDate 退休年月
     */
    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }

    /**
     * 获取健康状况
     *
     * @return health - 健康状况
     */
    public String getHealth() {
        return health;
    }

    /**
     * 设置健康状况
     *
     * @param health 健康状况
     */
    public void setHealth(String health) {
        this.health = health;
    }

    /**
     * 获取政治面貌(0_中共党员,1_共青团员,2_民主党派,3_群众)
     *
     * @return political_status - 政治面貌(0_中共党员,1_共青团员,2_民主党派,3_群众)
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 设置政治面貌(0_中共党员,1_共青团员,2_民主党派,3_群众)
     *
     * @param politicalStatus 政治面貌(0_中共党员,1_共青团员,2_民主党派,3_群众)
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    /**
     * 获取入党(团)时间
     *
     * @return political_date - 入党(团)时间
     */
    public String getPoliticalDate() {
        return politicalDate;
    }

    /**
     * 设置入党(团)时间
     *
     * @param politicalDate 入党(团)时间
     */
    public void setPoliticalDate(String politicalDate) {
        this.politicalDate = politicalDate;
    }

    /**
     * 获取婚姻状况(0_未婚,1_已婚,2_离异,3_丧偶)
     *
     * @return marriage - 婚姻状况(0_未婚,1_已婚,2_离异,3_丧偶)
     */
    public String getMarriage() {
        return marriage;
    }

    /**
     * 设置婚姻状况(0_未婚,1_已婚,2_离异,3_丧偶)
     *
     * @param marriage 婚姻状况(0_未婚,1_已婚,2_离异,3_丧偶)
     */
    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    /**
     * 获取用户状态（0_活动,1_禁用,2_删除）
     *
     * @return user_status - 用户状态（0_活动,1_禁用,2_删除）
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态（0_活动,1_禁用,2_删除）
     *
     * @param userStatus 用户状态（0_活动,1_禁用,2_删除）
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取用户类别（0_内部用户,1_外部用户）
     *
     * @return category - 用户类别（0_内部用户,1_外部用户）
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置用户类别（0_内部用户,1_外部用户）
     *
     * @param category 用户类别（0_内部用户,1_外部用户）
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取最高学历(0_博士研究生,1_硕士研究生,2_本科,3_大专,4_高中,5_中专,6_中专以下)
     *
     * @return high_edu - 最高学历(0_博士研究生,1_硕士研究生,2_本科,3_大专,4_高中,5_中专,6_中专以下)
     */
    public String getHighEdu() {
        return highEdu;
    }

    /**
     * 设置最高学历(0_博士研究生,1_硕士研究生,2_本科,3_大专,4_高中,5_中专,6_中专以下)
     *
     * @param highEdu 最高学历(0_博士研究生,1_硕士研究生,2_本科,3_大专,4_高中,5_中专,6_中专以下)
     */
    public void setHighEdu(String highEdu) {
        this.highEdu = highEdu;
    }

    /**
     * 获取最高学历专业
     *
     * @return high_major - 最高学历专业
     */
    public String getHighMajor() {
        return highMajor;
    }

    /**
     * 设置最高学历专业
     *
     * @param highMajor 最高学历专业
     */
    public void setHighMajor(String highMajor) {
        this.highMajor = highMajor;
    }

    /**
     * 获取最高学位(0_博士,1_硕士,2_学士,3_无)
     *
     * @return high_degree - 最高学位(0_博士,1_硕士,2_学士,3_无)
     */
    public String getHighDegree() {
        return highDegree;
    }

    /**
     * 设置最高学位(0_博士,1_硕士,2_学士,3_无)
     *
     * @param highDegree 最高学位(0_博士,1_硕士,2_学士,3_无)
     */
    public void setHighDegree(String highDegree) {
        this.highDegree = highDegree;
    }

    /**
     * 获取联系电话
     *
     * @return mobile_phone - 联系电话
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置联系电话
     *
     * @param mobilePhone 联系电话
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取参加工作年月
     *
     * @return work_date - 参加工作年月
     */
    public String getWorkDate() {
        return workDate;
    }

    /**
     * 设置参加工作年月
     *
     * @param workDate 参加工作年月
     */
    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取工龄
     *
     * @return work_age - 工龄
     */
    public String getWorkAge() {
        return workAge;
    }

    /**
     * 设置工龄
     *
     * @param workAge 工龄
     */
    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    /**
     * 获取到本企业年月
     *
     * @return employed_date - 到本企业年月
     */
    public String getEmployedDate() {
        return employedDate;
    }

    /**
     * 设置到本企业年月
     *
     * @param employedDate 到本企业年月
     */
    public void setEmployedDate(String employedDate) {
        this.employedDate = employedDate;
    }

    /**
     * 获取到本企业的工龄
     *
     * @return employed_age - 到本企业的工龄
     */
    public String getEmployedAge() {
        return employedAge;
    }

    /**
     * 设置到本企业的工龄
     *
     * @param employedAge 到本企业的工龄
     */
    public void setEmployedAge(String employedAge) {
        this.employedAge = employedAge;
    }

    /**
     * 获取现在的职务
     *
     * @return job - 现在的职务
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置现在的职务
     *
     * @param job 现在的职务
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取任现职时间
     *
     * @return job_date - 任现职时间
     */
    public String getJobDate() {
        return jobDate;
    }

    /**
     * 设置任现职时间
     *
     * @param jobDate 任现职时间
     */
    public void setJobDate(String jobDate) {
        this.jobDate = jobDate;
    }

    /**
     * 获取现职业时间
     *
     * @return job_limit - 现职业时间
     */
    public String getJobLimit() {
        return jobLimit;
    }

    /**
     * 设置现职业时间
     *
     * @param jobLimit 现职业时间
     */
    public void setJobLimit(String jobLimit) {
        this.jobLimit = jobLimit;
    }

    /**
     * 获取任现职级时间
     *
     * @return rank_date - 任现职级时间
     */
    public String getRankDate() {
        return rankDate;
    }

    /**
     * 设置任现职级时间
     *
     * @param rankDate 任现职级时间
     */
    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    /**
     * 获取常住地址(家庭情况)
     *
     * @return address - 常住地址(家庭情况)
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置常住地址(家庭情况)
     *
     * @param address 常住地址(家庭情况)
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取户口所在地址(家庭情况)
     *
     * @return registered_place - 户口所在地址(家庭情况)
     */
    public String getRegisteredPlace() {
        return registeredPlace;
    }

    /**
     * 设置户口所在地址(家庭情况)
     *
     * @param registeredPlace 户口所在地址(家庭情况)
     */
    public void setRegisteredPlace(String registeredPlace) {
        this.registeredPlace = registeredPlace;
    }

    /**
     * 获取紧急联系人姓名(家庭情况)
     *
     * @return emerg_name - 紧急联系人姓名(家庭情况)
     */
    public String getEmergName() {
        return emergName;
    }

    /**
     * 设置紧急联系人姓名(家庭情况)
     *
     * @param emergName 紧急联系人姓名(家庭情况)
     */
    public void setEmergName(String emergName) {
        this.emergName = emergName;
    }

    /**
     * 获取与本人关系(家庭情况)
     *
     * @return emerg_relate - 与本人关系(家庭情况)
     */
    public String getEmergRelate() {
        return emergRelate;
    }

    /**
     * 设置与本人关系(家庭情况)
     *
     * @param emergRelate 与本人关系(家庭情况)
     */
    public void setEmergRelate(String emergRelate) {
        this.emergRelate = emergRelate;
    }

    /**
     * 获取紧急联系电话(家庭情况)
     *
     * @return emerg_tel - 紧急联系电话(家庭情况)
     */
    public String getEmergTel() {
        return emergTel;
    }

    /**
     * 设置紧急联系电话(家庭情况)
     *
     * @param emergTel 紧急联系电话(家庭情况)
     */
    public void setEmergTel(String emergTel) {
        this.emergTel = emergTel;
    }

    /**
     * 获取紧急联系地址(家庭情况)
     *
     * @return emerg_address - 紧急联系地址(家庭情况)
     */
    public String getEmergAddress() {
        return emergAddress;
    }

    /**
     * 设置紧急联系地址(家庭情况)
     *
     * @param emergAddress 紧急联系地址(家庭情况)
     */
    public void setEmergAddress(String emergAddress) {
        this.emergAddress = emergAddress;
    }

    /**
     * 获取填表人id
     *
     * @return fill_id - 填表人id
     */
    public String getFillId() {
        return fillId;
    }

    /**
     * 设置填表人id
     *
     * @param fillId 填表人id
     */
    public void setFillId(String fillId) {
        this.fillId = fillId;
    }

    /**
     * 获取填表人姓名
     *
     * @return fill_name - 填表人姓名
     */
    public String getFillName() {
        return fillName;
    }

    /**
     * 设置填表人姓名
     *
     * @param fillName 填表人姓名
     */
    public void setFillName(String fillName) {
        this.fillName = fillName;
    }

    /**
     * 获取填表时间
     *
     * @return fill_date - 填表时间
     */
    public Date getFillDate() {
        return fillDate;
    }

    /**
     * 设置填表时间
     *
     * @param fillDate 填表时间
     */
    public void setFillDate(Date fillDate) {
        this.fillDate = fillDate;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取附件名称
     *
     * @return file_name - 附件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置附件名称
     *
     * @param fileName 附件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取附件url
     *
     * @return file_url - 附件url
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 设置附件url
     *
     * @param fileUrl 附件url
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * 获取附件类型
     *
     * @return file_type - 附件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置附件类型
     *
     * @param fileType 附件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取是否工人身份(0_是,1_否)
     *
     * @return is_worker - 是否工人身份(0_是,1_否)
     */
    public String getIsWorker() {
        return isWorker;
    }

    /**
     * 设置是否工人身份(0_是,1_否)
     *
     * @param isWorker 是否工人身份(0_是,1_否)
     */
    public void setIsWorker(String isWorker) {
        this.isWorker = isWorker;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取创建人ID
     *
     * @return creator_id - 创建人ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人ID
     *
     * @param creatorId 创建人ID
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取创建人姓名
     *
     * @return creator_name - 创建人姓名
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置创建人姓名
     *
     * @param creatorName 创建人姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 获取创建人部门id
     *
     * @return organ_id - 创建人部门id
     */
    public String getOrganId() {
        return organId;
    }

    /**
     * 设置创建人部门id
     *
     * @param organId 创建人部门id
     */
    public void setOrganId(String organId) {
        this.organId = organId;
    }

    /**
     * 获取创建人部门名称
     *
     * @return organ_name - 创建人部门名称
     */
    public String getOrganName() {
        return organName;
    }

    /**
     * 设置创建人部门名称
     *
     * @param organName 创建人部门名称
     */
    public void setOrganName(String organName) {
        this.organName = organName;
    }

    /**
     * 获取流程id
     *
     * @return process_id - 流程id
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * 设置流程id
     *
     * @param processId 流程id
     */
    public void setProcessId(String processId) {
        this.processId = processId;
    }

    /**
     * 获取提交时间
     *
     * @return committed - 提交时间
     */
    public Date getCommitted() {
        return committed;
    }

    /**
     * 设置提交时间
     *
     * @param committed 提交时间
     */
    public void setCommitted(Date committed) {
        this.committed = committed;
    }

    /**
     * 获取流程结束时间
     *
     * @return done - 流程结束时间
     */
    public Date getDone() {
        return done;
    }

    /**
     * 设置流程结束时间
     *
     * @param done 流程结束时间
     */
    public void setDone(Date done) {
        this.done = done;
    }

    /**
     * 获取表单状态(0_已保存,1_审核中,3_ 已驳回,2_已完成)
     *
     * @return status - 表单状态(0_已保存,1_审核中,3_ 已驳回,2_已完成)
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置表单状态(0_已保存,1_审核中,3_ 已驳回,2_已完成)
     *
     * @param status 表单状态(0_已保存,1_审核中,3_ 已驳回,2_已完成)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取当前节点名称
     *
     * @return last_task - 当前节点名称
     */
    public String getLastTask() {
        return lastTask;
    }

    /**
     * 设置当前节点名称
     *
     * @param lastTask 当前节点名称
     */
    public void setLastTask(String lastTask) {
        this.lastTask = lastTask;
    }

    /**
     * 获取流程分支
     *
     * @return branchline_to - 流程分支
     */
    public String getBranchlineTo() {
        return branchlineTo;
    }

    /**
     * 设置流程分支
     *
     * @param branchlineTo 流程分支
     */
    public void setBranchlineTo(String branchlineTo) {
        this.branchlineTo = branchlineTo;
    }

    /**
     * 获取是否可以编辑(0_是,1_否)
     *
     * @return is_edit - 是否可以编辑(0_是,1_否)
     */
    public String getIsEdit() {
        return isEdit;
    }

    /**
     * 设置是否可以编辑(0_是,1_否)
     *
     * @param isEdit 是否可以编辑(0_是,1_否)
     */
    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }
}