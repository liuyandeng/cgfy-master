package com.cgfy.mybatis.base.service;

import com.cgfy.mybatis.base.bean.BaseSelectField;

/**
 * 工作流审批动作接口
 *
 * @author xiayongshou
 */

public interface WorkflowService<T> {
    BaseSelectField selectDetail(String id);
}
