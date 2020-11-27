package com.cgfy.gateway.dynamic.domain.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 网关动态路由表
 *
 * @author suny
 */
@Table(name = "sys_gateway_route")
public class SysGatewayRoute implements Serializable {
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

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取服务ID
     *
     * @return service_id - 服务ID
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置服务ID
     *
     * @param serviceId 服务ID
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 获取转发地址
     *
     * @return uri - 转发地址
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置转发地址
     *
     * @param uri 转发地址
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * 获取访问路径
     *
     * @return predicates - 访问路径
     */
    public String getPredicates() {
        return predicates;
    }

    /**
     * 设置访问路径
     *
     * @param predicates 访问路径
     */
    public void setPredicates(String predicates) {
        this.predicates = predicates;
    }

    /**
     * 获取过滤
     *
     * @return filters - 过滤
     */
    public String getFilters() {
        return filters;
    }

    /**
     * 设置过滤
     *
     * @param filters 过滤
     */
    public void setFilters(String filters) {
        this.filters = filters;
    }

    /**
     * 获取顺序
     *
     * @return order - 顺序
     */
    public String getOrders() {
        return orders;
    }

    /**
     * 设置顺序
     *
     * @param orders 顺序
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * 获取创建人
     *
     * @return create_id - 创建人
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 设置创建人
     *
     * @param createId 创建人
     */
    public void setCreateId(String createId) {
        this.createId = createId;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新人
     *
     * @return update_id - 更新人
     */
    public String getUpdateId() {
        return updateId;
    }

    /**
     * 设置更新人
     *
     * @param updateId 更新人
     */
    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
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
     * 获取删除标记
     *
     * @return del_flag - 删除标记
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除标记
     *
     * @param delFlag 删除标记
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}