package com.cgfy.user.bussApi.service;

import com.cgfy.user.base.service.BaseService;
import com.cgfy.user.base.bean.CgfyListResponse;
import com.cgfy.user.base.bean.CgfySelectInputBean;
import com.cgfy.user.bussApi.bean.UserInfoOutputBean;
import com.cgfy.user.bussApi.bean.UserInfoInputBean;

/**
 * 「平台用户信息」基础Service
 *
 * @author cgfy_web
 */
public interface UserInfoService extends BaseService{


	/**
	* 检索
	* @param cgfyInput 输入参数
	* @return 输出对象
	*/
	public CgfyListResponse<UserInfoOutputBean> select(CgfySelectInputBean CgfyInput);

	/**
	* 保存
	* @param input 输入参数
	* @param id 主键id
	* @return 输出对象
	*/
	public UserInfoOutputBean save(UserInfoInputBean input,String id);

	/**
	* 获取详情
	* @param id 主键id
	* @return 输出对象
	*/
	public UserInfoOutputBean getDetail(String id);

	/**
	* 物理删除
	* @param id 主键id
	* @return 输出对象
	*/
	public void deleteForce(String id);



}

