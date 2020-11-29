package com.cgfy.user.bussApi.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.cgfy.user.bussApi.feign.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.cgfy.user.base.exception.BusinessException;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import com.cgfy.user.base.service.impl.BaseServiceImpl;
import com.cgfy.user.base.bean.SelectInputBean;
import com.cgfy.user.base.bean.CgfyListResponse;
import com.cgfy.user.base.bean.CgfySelectInputBean;
import com.cgfy.user.base.bean.BeanTrans;
import com.cgfy.user.base.bean.select.Condition;
import com.cgfy.user.base.bean.select.Direction;
import com.cgfy.user.base.bean.select.Order;
import com.cgfy.user.base.bean.BaseSelectField;
import com.cgfy.user.base.domain.mapper.BaseMapper;
import com.cgfy.user.base.domain.model.BaseModel;
import com.cgfy.user.bussApi.bean.UserInfoOutputBean;
import com.cgfy.user.bussApi.service.UserInfoService;
import com.cgfy.user.bussApi.bean.UserInfoInputBean;
import com.cgfy.user.bussApi.domain.model.UserInfo;

/**
 * 「平台用户信息」基础Service
 *
 * @author cgfy_web
 */
@Service("UserInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo,UserInfoOutputBean> implements UserInfoService{
    @Autowired
    private AuthFeignClient authFeignClient;
    /**
     * Mapper
     */
    @Resource
    private com.cgfy.user.bussApi.domain.mapper.UserInfoMapper mapper;



    /**
    * 检索
    * @param cgfyInput 输入参数
    * @return 输出对象
    */
    public CgfyListResponse<UserInfoOutputBean> select(CgfySelectInputBean cgfyInput) {
         SelectInputBean input = BeanTrans.zgcfzBeanToNormal(cgfyInput);
         Example example = new Example(UserInfo.class);
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
                item.addCondition(example, criteria, UserInfo.class);
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

        CgfyListResponse<UserInfoOutputBean> result = new CgfyListResponse<UserInfoOutputBean>();
        result.setTotalCount(mapper.selectCountByExample(example));
        List<UserInfo> resultData = null;
        if(input.getRowBounds() == null) {
            resultData = mapper.selectByExample(example);
        } else {
            resultData = mapper.selectByExampleAndRowBounds(example, input.getRowBounds());
            result.setPage(input.getRowBounds().getOffset());
            result.setPageSize(input.getRowBounds().getLimit());
            result.setPageCount((int) Math.ceil(result.getTotalCount()/result.getPageSize()));
        }
        List<UserInfoOutputBean> resultRecords = new ArrayList<UserInfoOutputBean>();
        for(UserInfo item : resultData){
            UserInfoOutputBean out = new UserInfoOutputBean();
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
    public UserInfoOutputBean save(UserInfoInputBean input,String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("主键不能为空");
        }
        UserInfo record = mapper.selectByPrimaryKey(id);
        if (record==null) {//新增
            UserInfo entity = new UserInfo();
            BeanUtils.copyProperties(input,entity);
            entity.setId(id);// 主键
            if(StringUtils.isNoneBlank(entity.getPassword())){
                entity.setPassword(authFeignClient.encodedPassword(entity.getPassword()));
            }else{
                entity.setPassword(authFeignClient.encodedPassword("123456"));
            }

            int count = mapper.insert(entity);
            if (count != 1) {
                throw new BusinessException("UserInfo插入失败");
            }
        } else {//更新
            BeanUtils.copyProperties(input,record);
            record.setId(id);// 主键
            record.setPassword(authFeignClient.encodedPassword(record.getPassword()));
            int count = mapper.updateByPrimaryKeySelective(record);
            if (count != 1) {
                throw new BusinessException("UserInfo更新失败");
            }

        }

        return getDetail(id);
    }

    /**
    * 获取详情
    * @param id 主键id
    * @return 输出对象
    */
    public UserInfoOutputBean getDetail(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("主键不能为空");
        }
        UserInfo record =mapper.selectByPrimaryKey(id);
        UserInfoOutputBean out=new UserInfoOutputBean(record);
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
            throw new BusinessException("UserInfo删除失败");
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

