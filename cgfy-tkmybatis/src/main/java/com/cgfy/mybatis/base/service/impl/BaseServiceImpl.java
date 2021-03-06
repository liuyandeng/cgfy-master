package com.cgfy.mybatis.base.service.impl;

import com.cgfy.mybatis.base.bean.BaseSelectField;
import com.cgfy.mybatis.base.domain.mapper.BaseMapper;
import com.cgfy.mybatis.base.domain.model.BaseModel;
import com.cgfy.mybatis.base.service.BaseService;
import com.cgfy.mybatis.base.service.CgfyComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.ParameterizedType;

@Slf4j
public abstract class BaseServiceImpl<T extends BaseModel, M extends BaseSelectField> implements BaseService {

    @Autowired
    @Qualifier("CgfyComService")
    private CgfyComService cgfyComService;

    private BaseMapper businessMapper = getBusinessMapper();

    /**
     * 获取mapper
     */
    protected abstract BaseMapper getBusinessMapper();


    /**
     * 单据详细信息检索
     *
     * @param id
     * @return
     */
    public BaseSelectField selectDetail(String id) {
        BaseModel record = (BaseModel) this.getBusinessMapper().selectByPrimaryKey(id);
        BaseSelectField outputBeanEx = getFormsOutInstance();
        BeanUtils.copyProperties(record, outputBeanEx);
        return outputBeanEx;
    }

    /**
     * 获取表单输出实例对象,OutputBean继承BaseSelectField
     *
     * @param
     * @return
     */
    public BaseSelectField getFormsOutInstance() {
        BaseSelectField field = null;
        try {
            Class<M> classM = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            field = (BaseSelectField) classM.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return field;
    }

    /**
     * 获取表单实例对象,model继承BaseModel
     *
     * @param
     * @return
     */
    public BaseModel getFormsInstance() {
        BaseModel model = null;
        try {
            Class<T> classT = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            model = (BaseModel) classT.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

}