package com.cgfy.user.bussApi.domain.mapper;

import com.cgfy.user.base.domain.mapper.BaseMapper;
import com.cgfy.user.bussApi.domain.model.StaffInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工信息 Mapper
 *
 * @author scgk_hr
 */
@Mapper
public interface StaffInfoMapper extends BaseMapper<StaffInfo> {
}