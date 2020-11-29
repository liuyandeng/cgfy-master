package com.cgfy.user.base.service;

import java.util.Date;

/**
 * 「登录用户」Service
 *
 * @author liuyandeng
 */
public interface CgfyComService {
	
	/**
	 * 取得用户主键ID
	 * 
	 * @return 用户主键ID
	 */
	public String getCurrentUserId();
	

	/**
	 * 取得当前时间（提出共通，以便以后扩展）
	 * 
	 * @return 取得当前时间
	 */
	public Date getCurrentTime();
	
}

