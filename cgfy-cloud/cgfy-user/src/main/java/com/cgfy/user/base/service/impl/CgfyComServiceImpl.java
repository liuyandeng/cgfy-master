package com.cgfy.user.base.service.impl;

import com.cgfy.user.base.util.BaseCommonUtil;
import com.cgfy.user.base.service.CgfyComService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 「登录用户」Service
 *
 * @author liuyandeng
 */
@Service("CgfyComService")
public class CgfyComServiceImpl implements CgfyComService {

	/**
	 * 取得用户主键ID
	 *
	 * @return 用户主键ID
	 */
	public String getCurrentUserId(){
        return BaseCommonUtil.getCurrentUserId();
    }

	/**
	 * 取得当前时间（提出共通，以便以后扩展）
	 *
	 * @return 取得当前时间
	 */
	public Date getCurrentTime(){
        return new Date();
    }

}

