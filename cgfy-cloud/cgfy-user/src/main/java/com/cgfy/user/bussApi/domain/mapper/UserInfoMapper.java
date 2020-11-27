package com.cgfy.user.bussApi.domain.mapper;

import com.cgfy.user.base.domain.mapper.BaseMapper;
import com.cgfy.user.bussApi.domain.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台用户信息 Mapper
 *
 * @author cgfy_web
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}