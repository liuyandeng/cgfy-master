package com.cgfy.mybatis.base.service;

import java.util.List;
import java.util.Map;

/**
 * 系统资源service
 *
 * @author liuyandeng 2018.05.21
 */
public interface ResourceService {

	/**
	 * 加载码表到缓存中
	 */
	public void loadCodeTable();
	public List<Map<String,Object>> getKeyValueByCode(String codeType);
	public String getValueByCodeAndKey(String codeType, String codeValue);

}
