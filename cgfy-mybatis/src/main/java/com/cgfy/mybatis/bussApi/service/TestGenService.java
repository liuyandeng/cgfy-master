package com.cgfy.mybatis.bussApi.service;


import com.cgfy.mybatis.base.bean.CgfyListResponse;
import com.cgfy.mybatis.base.bean.CgfySelectInputBean;
import com.cgfy.mybatis.base.service.BaseService;
import com.cgfy.mybatis.bussApi.bean.TestGenInputBean;
import com.cgfy.mybatis.bussApi.bean.TestGenInternalOutputBean;

/**
 * 「cgfy」基础Service
 *
 * @author generator
 */
public interface TestGenService extends BaseService {


	/**
	* 检索
	* @param scgkInput 输入参数
	* @return 输出对象
	*/
	public CgfyListResponse<TestGenInternalOutputBean> select(CgfySelectInputBean scgkInput);

	/**
	* 保存
	* @param input 输入参数
	* @param id 主键id
	* @return 输出对象
	*/
	public TestGenInternalOutputBean save(TestGenInputBean input, String id);

	/**
	* 获取详情
	* @param id 主键id
	* @return 输出对象
	*/
	public TestGenInternalOutputBean getDetail(String id);

	/**
	* 物理删除
	* @param id 主键id
	* @return 输出对象
	*/
	public void deleteForce(String id);



}

