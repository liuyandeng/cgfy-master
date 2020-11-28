package com.cgfy.oauth.bussApi.domain.mapper;

import com.cgfy.oauth.base.domain.mapper.BaseMapper;
import com.cgfy.oauth.bussApi.domain.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台用户信息 Mapper
 *
 * @author cgfy_web
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}