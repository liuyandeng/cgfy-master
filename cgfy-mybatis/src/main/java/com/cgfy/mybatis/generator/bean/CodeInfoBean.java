package com.cgfy.mybatis.generator.bean;

import com.cgfy.mybatis.base.bean.BaseSelectField;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 系统码表信息输出用Bean
 *
 * @author liuyandeng
 */
public class CodeInfoBean extends BaseSelectField {
    /**
     * 代码类别
     */
    @ApiModelProperty(value = "代码类别")
    @Size(max = 64)
    @NotNull
    private String codeType;

    /**
     * 代码值
     */
    @ApiModelProperty(value = "代码值")
    @Size(max = 128)
    @NotNull
    private String codeValue;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    @Size(max = 64)
    private String codeTypeName;

    /**
     * 代码名称
     */
    @ApiModelProperty(value = "代码名称")
    @Size(max = 64)
    private String codeName;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @Size(max = 16)
    private String orderNo;

    /**
     * 状态（0_未禁用，1_已禁用）
     */
    @ApiModelProperty(value = "状态（0_未禁用，1_已禁用）")
    @Size(max = 1)
    private String status;

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
    public CodeInfoBean() {

    }



    /**
     * 获取代码类别
     *
     * @return 代码类别
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * 设置代码类别
     *
     * @param codeType 代码类别
     */
    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    /**
     * 获取代码值
     *
     * @return 代码值
     */
    public String getCodeValue() {
        return codeValue;
    }

    /**
     * 设置代码值
     *
     * @param codeValue 代码值
     */
    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * 获取类别名称
     *
     * @return 类别名称
     */
    public String getCodeTypeName() {
        return codeTypeName;
    }

    /**
     * 设置类别名称
     *
     * @param codeTypeName 类别名称
     */
    public void setCodeTypeName(String codeTypeName) {
        this.codeTypeName = codeTypeName;
    }

    /**
     * 获取代码名称
     *
     * @return 代码名称
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * 设置代码名称
     *
     * @param codeName 代码名称
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
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
     * 获取状态（0_未禁用，1_已禁用）
     *
     * @return 状态（0_未禁用，1_已禁用）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态（0_未禁用，1_已禁用）
     *
     * @param status 状态（0_未禁用，1_已禁用）
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
    public String getFcTime() {
        return fcTime;
    }

    /**
     * 设置创建时间
     *
     * @param fcTime 创建时间
     */
    public void setFcTime(String fcTime) {
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
    public String getLmTime() {
        return lmTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lmTime 最后修改时间
     */
    public void setLmTime(String lmTime) {
        this.lmTime = lmTime;
    }
}
