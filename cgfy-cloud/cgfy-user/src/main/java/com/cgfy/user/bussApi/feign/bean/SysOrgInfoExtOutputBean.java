package com.cgfy.user.bussApi.feign.bean;

import com.cgfy.user.base.bean.BaseSelectField;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台机构信息输出用Bean
 *
 * @author qiucw
 */
public class SysOrgInfoExtOutputBean extends BaseSelectField {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 上级ID
     */
    @ApiModelProperty(value = "上级ID")
    private String parentId;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;

    /**
     * 机构名称英文
     */
    @ApiModelProperty(value = "机构名称英文")
    private String nameEn;

    /**
     * 机构简称
     */
    @ApiModelProperty(value = "机构简称")
    private String shortName;

    /**
     * 机构别名
     */
    @ApiModelProperty(value = "机构别名")
    private String anotherName;

    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构代码")
    private String code;

    /**
     * 机构等级,起始为0
     */
    @ApiModelProperty(value = "机构等级,起始为0")
    private Integer grade;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private String orderNo;

    /**
     * 机构类别（1_公司/企业，2_部门/科室）
     */
    @ApiModelProperty(value = "机构类别（1_公司/企业，2_部门/科室）")
    private String category;

    /**
     * 归属机构（如某个部门归属在其某个上级机构）
     */
    @ApiModelProperty(value = "归属机构（如某个部门归属在其某个上级机构）")
    private String ascriptionOrg;

    /**
     * 机构类型（码表中的ORG_TYPE_CODE）
     */
    @ApiModelProperty(value = "机构类型（码表中的ORG_TYPE_CODE）")
    private String type;

    /**
     * 机构性质（码表中的ORG_NATURE_CODE）
     */
    @ApiModelProperty(value = "机构性质（码表中的ORG_NATURE_CODE）")
    private String nature;

    /**
     * 机构状态（0_活动，1_禁用，2_删除）
     */
    @ApiModelProperty(value = "机构状态（0_活动，1_禁用，2_删除）")
    private String status;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    /**
     * 机构地址
     */
    @ApiModelProperty(value = "机构地址")
    private String address;

    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    private String email;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String telephone;

    /**
     * 是否是临时
     */
    @ApiModelProperty(value = "是否是临时")
    private String isTemp;

    /**
     * 是否隐藏
     */
    @ApiModelProperty(value = "是否隐藏")
    private String isHidden;

    /**
     * 起始有效日期
     */
    @ApiModelProperty(value = "起始有效日期")
    private java.util.Date vsDate;

    /**
     * 有效结束日期
     */
    @ApiModelProperty(value = "有效结束日期")
    private java.util.Date veDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
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
    private String lmUser;

    /**
     * 最后修改时间
     */
    @ApiModelProperty(value = "最后修改时间")
    private java.util.Date lmTime;

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
     * 获取上级ID
     *
     * @return 上级ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级ID
     *
     * @param parentId 上级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取机构名称
     *
     * @return 机构名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置机构名称
     *
     * @param name 机构名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取机构名称英文
     *
     * @return 机构名称英文
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置机构名称英文
     *
     * @param nameEn 机构名称英文
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * 获取机构简称
     *
     * @return 机构简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置机构简称
     *
     * @param shortName 机构简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取机构别名
     *
     * @return 机构别名
     */
    public String getAnotherName() {
        return anotherName;
    }

    /**
     * 设置机构别名
     *
     * @param anotherName 机构别名
     */
    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    /**
     * 获取机构代码
     *
     * @return 机构代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置机构代码
     *
     * @param code 机构代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取机构等级,起始为0
     *
     * @return 机构等级,起始为0
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置机构等级,起始为0
     *
     * @param grade 机构等级,起始为0
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
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
     * 获取机构类别（1_公司/企业，2_部门/科室）
     *
     * @return 机构类别（1_公司/企业，2_部门/科室）
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置机构类别（1_公司/企业，2_部门/科室）
     *
     * @param category 机构类别（1_公司/企业，2_部门/科室）
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取归属机构（如某个部门归属在其某个上级机构）
     *
     * @return 归属机构（如某个部门归属在其某个上级机构）
     */
    public String getAscriptionOrg() {
        return ascriptionOrg;
    }

    /**
     * 设置归属机构（如某个部门归属在其某个上级机构）
     *
     * @param ascriptionOrg 归属机构（如某个部门归属在其某个上级机构）
     */
    public void setAscriptionOrg(String ascriptionOrg) {
        this.ascriptionOrg = ascriptionOrg;
    }

    /**
     * 获取机构类型（码表中的ORG_TYPE_CODE）
     *
     * @return 机构类型（码表中的ORG_TYPE_CODE）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置机构类型（码表中的ORG_TYPE_CODE）
     *
     * @param type 机构类型（码表中的ORG_TYPE_CODE）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取机构性质（码表中的ORG_NATURE_CODE）
     *
     * @return 机构性质（码表中的ORG_NATURE_CODE）
     */
    public String getNature() {
        return nature;
    }

    /**
     * 设置机构性质（码表中的ORG_NATURE_CODE）
     *
     * @param nature 机构性质（码表中的ORG_NATURE_CODE）
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * 获取机构状态（0_活动，1_禁用，2_删除）
     *
     * @return 机构状态（0_活动，1_禁用，2_删除）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置机构状态（0_活动，1_禁用，2_删除）
     *
     * @param status 机构状态（0_活动，1_禁用，2_删除）
     */
    public void setStatus(String status) {
        this.status = status;
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
     * 获取机构地址
     *
     * @return 机构地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置机构地址
     *
     * @param address 机构地址
     */
    public void setAddress(String address) {
        this.address = address;
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
     * 获取联系电话
     *
     * @return 联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置联系电话
     *
     * @param telephone 联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取是否是临时
     *
     * @return 是否是临时
     */
    public String getIsTemp() {
        return isTemp;
    }

    /**
     * 设置是否是临时
     *
     * @param isTemp 是否是临时
     */
    public void setIsTemp(String isTemp) {
        this.isTemp = isTemp;
    }

    /**
     * 获取是否隐藏
     *
     * @return 是否隐藏
     */
    public String getIsHidden() {
        return isHidden;
    }

    /**
     * 设置是否隐藏
     *
     * @param isHidden 是否隐藏
     */
    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * 获取起始有效日期
     *
     * @return 起始有效日期
     */
    public java.util.Date getVsDate() {
        return vsDate;
    }

    /**
     * 设置起始有效日期
     *
     * @param vsDate 起始有效日期
     */
    public void setVsDate(java.util.Date vsDate) {
        this.vsDate = vsDate;
    }

    /**
     * 获取有效结束日期
     *
     * @return 有效结束日期
     */
    public java.util.Date getVeDate() {
        return veDate;
    }

    /**
     * 设置有效结束日期
     *
     * @param veDate 有效结束日期
     */
    public void setVeDate(java.util.Date veDate) {
        this.veDate = veDate;
    }

    /**
     * 获取备注
     *
     * @return 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
