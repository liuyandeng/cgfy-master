package com.cgfy.mybatis.base.service;

import java.util.Date;

/**
 * 「公共」Service
 */
public interface CgfyComService {

	/**
	 * 取得当前时间（提出共通，以便以后扩展）
	 * 
	 * @return 取得当前时间
	 */
	public Date getCurrentTime();
	
}

