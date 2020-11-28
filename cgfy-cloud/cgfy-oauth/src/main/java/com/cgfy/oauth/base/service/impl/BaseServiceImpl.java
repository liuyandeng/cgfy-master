package com.cgfy.oauth.base.service.impl;

import com.cgfy.oauth.base.bean.SelectInputBean;
import com.cgfy.oauth.base.domain.model.BaseModel;
import com.cgfy.oauth.base.service.BaseService;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


public abstract class BaseServiceImpl implements BaseService {
	/**
     * 插入附加操作
     * 
     * @param record 插入对象
     */
    protected void insertAdditional(BaseModel record) {
    }
    
    /**
     * 更新附加操作
     * 
     * @param record 插入对象
     */
    protected void updateAdditional(BaseModel record) {
    }
    
    /**
     * 更新附加操作
     * 
     * @param record 插入对象
     */
    protected void deleteAdditional(BaseModel record) {
    }
    
    /**
     * 检索附加前操作
     * 
     * @param record 插入对象
     */
    protected void selectBeforeAdditional(SelectInputBean input) {
    }
    
    /**
     * 检索附加操作
     * 
     * @param record 插入对象
     */
    protected void selectAdditional(SelectInputBean input, Example example) {
    }
    
    /**
     * 敏感字段过滤
     * 
     * @param record 插入对象
     */
    protected void sensitiveFieldFilter(List<String> fields) {
        if(!CollectionUtils.isEmpty(fields)) {
            fields.addAll(getAllField());
        }
    }
    
    /**
     * 全字段取得
     */
    protected abstract List<String> getAllField();
}
