package com.cgfy.user.base.util;

import com.cgfy.user.base.cache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * 该类是为码表缓存提供的工具类，
 *
 * @version 2013-7-1
 * @author leibf
 */
public class CodeListUtil {

	private static Logger logger = LogManager.getLogger(CodeListUtil.class);

	private CodeListUtil() {

	}

	/**
	 * 取得代码列表
	 *
	 * @param dataMeta
	 *            代码类型
	 * @param status
	 *            代码状态，为空时获取该分类下所有代码列表
	 * @return 代码列表
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, List<Map<String, Object>>> getCodeAll() {
		Map<Object, List<Map<String, Object>>> map = null;
		try {
			map = CacheManager.getInstance().doGetfromServletCache(Globals.CODE_TABLE_ATTR, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (map == null || map.isEmpty()) {
			logger.error("码表缓存为空");
			return null;
		}

		return map;
	}


	/**
	 * 取得代码列表
	 *
	 * @param dataMeta
	 *            代码类型
	 * @param status
	 *            代码状态，为空时获取该分类下所有代码列表
	 * @return 代码列表
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getCodeList(String dataMeta, String status) {
		List<Map<String, Object>> codeList = new ArrayList<Map<String, Object>>();
		Map<Object, List<Map<String, Object>>> map = null;
		try {
			map = CacheManager.getInstance().doGetfromServletCache(Globals.CODE_TABLE_ATTR, Map.class);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (map == null || map.isEmpty()) {
			logger.error("码表缓存为空");
			return null;
		}
		List<Map<String, Object>> tempList = map.get(dataMeta);
		if(StringUtils.isNotEmpty(status)) {
			for(Map<String, Object> tempMap : tempList) {
				if(status.equals(tempMap.get("cstatus"))) {
					codeList.add(tempMap);
				}
			}
		}else {
			codeList = tempList;
		}
		return codeList;
	}

	/**
	 * 取得代码名称
	 *
	 * @param dataMeta
	 *            代码类型
	 * @param key
	 *            代码key
	 * @return 代码名称
	 */
	public static String code2mean(String dataMeta, String key){
		String rv = null;
		if(key!=null) {
			List<Map<String, Object>> list = getCodeList(dataMeta, null);
			if (list != null && !list.isEmpty()) {
				for(Map<String, Object> map : list) {
					if(key.equals(map.get("ckey"))) {
						rv = (String) map.get("cname");
					}
				}
			}
		}
		return rv;
	}

	public static String getValue(Map<Object, List<Map<String, Object>>> map,String dataMeta, String key){
		String rv = key==null?"":key;
		if(key!=null) {
			List<Map<String, Object>> tempList = map.get(dataMeta);
			if (tempList != null && !tempList.isEmpty()) {
				for (Map<String, Object> mapE : tempList) {
					if (key.equals(mapE.get("ckey"))) {
						rv = (String) mapE.get("cname");
						break;
					}
				}
			}
		}
		return rv;
	}

}
