package com.cgfy.user.bussApi.feign.bean;

import com.cgfy.user.base.bean.BaseSelectField;
import com.cgfy.user.base.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;

/**
 * 平台角色信息输出用Bean
 *
 * @author qiucw
 */
public class SysRoleInfoExtOutputBean extends BaseSelectField {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色代码")
    private String code;

    /**
     * 所属子系统
     */
    @ApiModelProperty(value = "所属子系统")
    private String subsysid;

    /**
     * 角色状态（0_活动，1_禁用，2_删除）
     */
    @ApiModelProperty(value = "角色状态（0_活动，1_禁用，2_删除）")
    private String status;

    /**
     * 角色类型（1平台角色，2用户创建角色）
     */
    @ApiModelProperty(value = "角色类型（1平台角色，2用户创建角色）")
    private String type;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID")
    private String orgId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String fcUser;

    /**
     * 创建时间
     */

    private String fcTime;

    /**
     * 最后修改人
     */
    @ApiModelProperty(value = "最后修改人")
    private String lmUser;

    /**
     * 最后修改时间
     */

    private String lmTime;

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
     * 获取角色名称
     *
     * @return 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色代码
     *
     * @return 角色代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色代码
     *
     * @param code 角色代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取所属子系统
     *
     * @return 所属子系统
     */
    public String getSubsysid() {
        return subsysid;
    }

    /**
     * 设置所属子系统
     *
     * @param subsysid 所属子系统
     */
    public void setSubsysid(String subsysid) {
        this.subsysid = subsysid;
    }

    /**
     * 获取角色状态（0_活动，1_禁用，2_删除）
     *
     * @return 角色状态（0_活动，1_禁用，2_删除）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置角色状态（0_活动，1_禁用，2_删除）
     *
     * @param status 角色状态（0_活动，1_禁用，2_删除）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取角色类型（1平台角色，2用户创建角色）
     *
     * @return 角色类型（1平台角色，2用户创建角色）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置角色类型（1平台角色，2用户创建角色）
     *
     * @param type 角色类型（1平台角色，2用户创建角色）
     */
    public void setType(String type) {
        this.type = type;
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
