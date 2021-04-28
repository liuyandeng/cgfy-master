package com.cgfy.mybatis.bussApi.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import com.cgfy.mybatis.base.exception.BusinessException;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import com.cgfy.mybatis.base.service.impl.BaseServiceImpl;
import com.cgfy.mybatis.base.bean.SelectInputBean;
import com.cgfy.mybatis.base.bean.CgfyListResponse;
import com.cgfy.mybatis.base.bean.CgfySelectInputBean;
import com.cgfy.mybatis.base.bean.BeanTrans;
import com.cgfy.mybatis.base.bean.select.Condition;
import com.cgfy.mybatis.base.bean.select.Direction;
import com.cgfy.mybatis.base.bean.select.Order;
import com.cgfy.mybatis.base.bean.BaseSelectField;
import com.cgfy.mybatis.base.domain.mapper.BaseMapper;
import com.cgfy.mybatis.base.domain.model.BaseModel;
import com.cgfy.mybatis.bussApi.bean.TestGenOutputBean;
import com.cgfy.mybatis.bussApi.service.TestGenService;
import com.cgfy.mybatis.bussApi.bean.TestGenInputBean;
import com.cgfy.mybatis.bussApi.domain.model.TestGen;

/**
 * 「cgfy」基础Service
 *
 * @author cgfy_web
 */
@Service("TestGenService")
public class TestGenServiceImpl extends BaseServiceImpl<TestGen,TestGenOutputBean> implements TestGenService{

    /**
     * Mapper
     */
    @Resource
    private com.cgfy.mybatis.bussApi.domain.mapper.TestGenMapper mapper;



    /**
    * 检索
    * @param cgfyInput 输入参数
    * @return 输出对象
    */
    public CgfyListResponse<TestGenOutputBean> select(CgfySelectInputBean cgfyInput) {
         SelectInputBean input = BeanTrans.zgcfzBeanToNormal(cgfyInput);
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

        CgfyListResponse<TestGenOutputBean> result = new CgfyListResponse<TestGenOutputBean>();
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
        List<TestGenOutputBean> resultRecords = new ArrayList<TestGenOutputBean>();
        for(TestGen item : resultData){
            TestGenOutputBean out = new TestGenOutputBean();
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
    public TestGenOutputBean save(TestGenInputBean input,String id) {
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
    public TestGenOutputBean getDetail(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("主键不能为空");
        }
        TestGen record =mapper.selectByPrimaryKey(id);
        TestGenOutputBean out=new TestGenOutputBean(record);
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

