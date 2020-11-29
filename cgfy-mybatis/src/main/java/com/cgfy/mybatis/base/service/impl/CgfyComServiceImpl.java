package com.cgfy.mybatis.base.service.impl;

import com.cgfy.mybatis.base.service.CgfyComService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 「公共」Service
 */
@Service("CgfyComService")
public class CgfyComServiceImpl implements CgfyComService {

	/**
	 * 取得当前时间（提出共通，以便以后扩展）
	 *
	 * @return 取得当前时间
	 */
	public Date getCurrentTime(){
        return new Date();
    }

}

