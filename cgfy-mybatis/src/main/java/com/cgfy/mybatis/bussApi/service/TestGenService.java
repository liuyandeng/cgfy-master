package com.cgfy.mybatis.bussApi.service;

import com.cgfy.mybatis.base.service.BaseService;
import com.cgfy.mybatis.base.bean.CgfyListResponse;
import com.cgfy.mybatis.base.bean.CgfySelectInputBean;
import com.cgfy.mybatis.bussApi.bean.TestGenOutputBean;
import com.cgfy.mybatis.bussApi.bean.TestGenInputBean;

/**
 * 「cgfy」基础Service
 *
 * @author cgfy_web
 */
public interface TestGenService extends BaseService{


	/**
	* 检索
	* @param cgfyInput 输入参数
	* @return 输出对象
	*/
	public CgfyListResponse<TestGenOutputBean> select(CgfySelectInputBean CgfyInput);

	/**
	* 保存
	* @param input 输入参数
	* @param id 主键id
	* @return 输出对象
	*/
	public TestGenOutputBean save(TestGenInputBean input,String id);

	/**
	* 获取详情
	* @param id 主键id
	* @return 输出对象
	*/
	public TestGenOutputBean getDetail(String id);

	/**
	* 物理删除
	* @param id 主键id
	* @return 输出对象
	*/
	public void deleteForce(String id);



}

