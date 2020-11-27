package com.cgfy.gateway.dynamic.domain.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 网关动态路由表
 */
@Table(name = "gateway_route")
@Data
public class GatewayRoute implements Serializable {
    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 服务ID
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 转发地址
     */
    private String uri;

    /**
     * 访问路径
     */
    private String predicates;

    /**
     * 过滤
     */
    private String filters;

    /**
     * 顺序
     */
    private String orders;

    /**
     * 创建人
     */
    @Column(name = "create_id")
    private String createId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新人
     */
    @Column(name = "update_id")
    private String updateId;

    /**
     * 更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 删除标记
     */
    @Column(name = "del_flag")
    private String delFlag;

}