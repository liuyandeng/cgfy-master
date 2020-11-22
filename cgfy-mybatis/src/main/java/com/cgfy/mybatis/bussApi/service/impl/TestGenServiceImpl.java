package com.cgfy.mybatis.bussApi.service.impl;

import com.cgfy.mybatis.base.bean.BeanTrans;
import com.cgfy.mybatis.base.bean.CgfyListResponse;
import com.cgfy.mybatis.base.bean.CgfySelectInputBean;
import com.cgfy.mybatis.base.bean.SelectInputBean;
import com.cgfy.mybatis.base.bean.select.Condition;
import com.cgfy.mybatis.base.bean.select.Direction;
import com.cgfy.mybatis.base.bean.select.Order;
import com.cgfy.mybatis.base.domain.mapper.BaseMapper;
import com.cgfy.mybatis.base.exception.BusinessException;
import com.cgfy.mybatis.base.service.impl.BaseServiceImpl;
import com.cgfy.mybatis.bussApi.bean.TestGenInputBean;
import com.cgfy.mybatis.bussApi.bean.TestGenInternalOutputBean;
import com.cgfy.mybatis.bussApi.domain.mapper.TestGenMapper;
import com.cgfy.mybatis.bussApi.domain.model.TestGen;
import com.cgfy.mybatis.bussApi.service.TestGenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 「cgfy」基础Service
 *
 * @author generator
 */
@Service("TestGenService")
public class TestGenServiceImpl extends BaseServiceImpl<TestGen, TestGenInternalOutputBean> implements TestGenService {

    /**
     * Mapper
     */
    @Resource
    private TestGenMapper mapper;


    /**
    * 检索
    * @param scgkInput 输入参数
    * @return 输出对象
    */
    public CgfyListResponse<TestGenInternalOutputBean> select(CgfySelectInputBean scgkInput) {
         SelectInputBean input = BeanTrans.zgcfzBeanToNormal(scgkInput);
         Example example = new Example(TestGen.class);
        // 检索项目设定
        if (input.getFields() != null && input.getFields().size() != 0) {
            for (String field : input.getFields()) {
                example = example.selectProperties(field);
            }
       }

        // 检索条件变换
        if (input.getCondition() != null && input.getCondition().size() != 0) {
            Criteria criteria = example.createCriteria();
            for (Condition item : input.getCondition()) {
                item.addCondition(example, criteria, TestGen.class);
            }
        }

        // 排序设定
        if (input.getSort() != null && input.getSort().size() != 0) {
            for (Order order : input.getSort()) {
                if (order.getDirection() == Direction.DESC) {
                    example.orderBy(order.getProperty()).desc();
                } else {
                    example.orderBy(order.getProperty()).asc();
                }
            }
        }

        CgfyListResponse<TestGenInternalOutputBean> result = new CgfyListResponse<TestGenInternalOutputBean>();
        result.setTotalCount(mapper.selectCountByExample(example));
        List<TestGen> resultData = null;
        if(input.getRowBounds() == null) {
            resultData = mapper.selectByExample(example);
        } else {
            resultData = mapper.selectByExampleAndRowBounds(example, input.getRowBounds());
            result.setPage(input.getRowBounds().getOffset());
            result.setPageSize(input.getRowBounds().getLimit());
            result.setPageCount((int) Math.ceil(result.getTotalCount()/result.getPageSize()));
        }
        List<TestGenInternalOutputBean> resultRecords = new ArrayList<TestGenInternalOutputBean>();
        for(TestGen item : resultData){
            TestGenInternalOutputBean out = new TestGenInternalOutputBean();
            BeanUtils.copyProperties(item,out);
            resultRecords.add(out);
        }
        result.setForms(resultRecords);
        return result;
    }

    /**
    * 保存
    * @param input 输入参数
    * @param id 主键id
    * @return 输出对象
    */
    @Transactional(rollbackFor = Exception.class)
    public TestGenInternalOutputBean save(TestGenInputBean input, String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("主键不能为空");
        }
        TestGen record = mapper.selectByPrimaryKey(id);
        if (record==null) {//新增
            TestGen entity = new TestGen();
            BeanUtils.copyProperties(input,entity);
            entity.setId(id);// 主键
            int count = mapper.insert(entity);
            if (count != 1) {
                throw new BusinessException("TestGen插入失败");
            }
        } else {//更新
            BeanUtils.copyProperties(input,record);
            record.setId(id);// 主键
            int count = mapper.updateByPrimaryKeySelective(record);
            if (count != 1) {
                throw new BusinessException("TestGen更新失败");
            }

        }

        return getDetail(id);
    }

    /**
    * 获取详情
    * @param id 主键id
    * @return 输出对象
    */
    public TestGenInternalOutputBean getDetail(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("主键不能为空");
        }
        TestGen record =mapper.selectByPrimaryKey(id);
        TestGenInternalOutputBean out=new TestGenInternalOutputBean(record);
        return out;
    }

    /**
    * 删除
    * @param id 主键id
    * @return 输出对象
    */
    @Transactional(rollbackFor = Exception.class)
    public void deleteForce(String id) {
        int count = mapper.deleteByPrimaryKey(id);
        if (count != 1) {
            throw new BusinessException("TestGen删除失败");
        }

    }


    /**
    * 获取mapper
    */
    @Override
    public BaseMapper getBusinessMapper() {
        return this.mapper;
    }




}

