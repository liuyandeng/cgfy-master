package com.cgfy.user.bussApi.feign.bean;

import com.cgfy.user.base.bean.BaseSelectField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 系统码表信息输出用Bean

 */
@Data
public class CodeOutputBean extends BaseSelectField {
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
    public CodeOutputBean() {

    }




}
