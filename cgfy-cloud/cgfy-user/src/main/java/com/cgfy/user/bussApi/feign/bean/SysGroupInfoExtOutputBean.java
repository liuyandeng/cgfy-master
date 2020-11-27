package com.cgfy.user.bussApi.feign.bean;

import com.cgfy.user.base.bean.BaseSelectField;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台岗位信息输出用Bean
 *
 * @author qiucw
 */
public class SysGroupInfoExtOutputBean extends BaseSelectField {
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
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String name;

    /**
     * 岗位名称英文
     */
    @ApiModelProperty(value = "岗位名称英文")
    private String nameEn;

    /**
     * 岗位简称
     */
    @ApiModelProperty(value = "岗位简称")
    private String shortName;

    /**
     * 岗位编码
     */
    @ApiModelProperty(value = "岗位编码")
    private String code;

    /**
     * 岗位类型（参看码表GROUP_TYPE_CODE）
     */
    @ApiModelProperty(value = "岗位类型（参看码表GROUP_TYPE_CODE）")
    private String type;

    /**
     * 岗位等级（参看码表GROUP_GRADE_CODE）
     */
    @ApiModelProperty(value = "岗位等级（参看码表GROUP_GRADE_CODE）")
    private String grade;

    /**
     * 岗位描述
     */
    @ApiModelProperty(value = "岗位描述")
    private String remarks;

    /**
     * 是否内建岗位（0_否，1_是）
     */
    @ApiModelProperty(value = "是否内建岗位（0_否，1_是）")
    private String innerFlag;

    /**
     * 岗位状态（0_活动，1_禁用，2_删除）
     */
    @ApiModelProperty(value = "岗位状态（0_活动，1_禁用，2_删除）")
    private String status;

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
     * 获取岗位名称
     *
     * @return 岗位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置岗位名称
     *
     * @param name 岗位名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取岗位名称英文
     *
     * @return 岗位名称英文
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置岗位名称英文
     *
     * @param nameEn 岗位名称英文
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * 获取岗位简称
     *
     * @return 岗位简称
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置岗位简称
     *
     * @param shortName 岗位简称
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取岗位编码
     *
     * @return 岗位编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置岗位编码
     *
     * @param code 岗位编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取岗位类型（参看码表GROUP_TYPE_CODE）
     *
     * @return 岗位类型（参看码表GROUP_TYPE_CODE）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置岗位类型（参看码表GROUP_TYPE_CODE）
     *
     * @param type 岗位类型（参看码表GROUP_TYPE_CODE）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取岗位等级（参看码表GROUP_GRADE_CODE）
     *
     * @return 岗位等级（参看码表GROUP_GRADE_CODE）
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置岗位等级（参看码表GROUP_GRADE_CODE）
     *
     * @param grade 岗位等级（参看码表GROUP_GRADE_CODE）
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取岗位描述
     *
     * @return 岗位描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置岗位描述
     *
     * @param remarks 岗位描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取是否内建岗位（0_否，1_是）
     *
     * @return 是否内建岗位（0_否，1_是）
     */
    public String getInnerFlag() {
        return innerFlag;
    }

    /**
     * 设置是否内建岗位（0_否，1_是）
     *
     * @param innerFlag 是否内建岗位（0_否，1_是）
     */
    public void setInnerFlag(String innerFlag) {
        this.innerFlag = innerFlag;
    }

    /**
     * 获取岗位状态（0_活动，1_禁用，2_删除）
     *
     * @return 岗位状态（0_活动，1_禁用，2_删除）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置岗位状态（0_活动，1_禁用，2_删除）
     *
     * @param status 岗位状态（0_活动，1_禁用，2_删除）
     */
    public void setStatus(String status) {
        this.status = status;
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
