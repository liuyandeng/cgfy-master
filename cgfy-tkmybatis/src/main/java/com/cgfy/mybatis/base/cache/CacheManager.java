package com.cgfy.mybatis.base.cache;

import org.springframework.context.ApplicationContext;

/**
 * 
 * 该类是系统缓存管理类
 */
public class CacheManager {

	private static CacheManager singleton = null;
	
	private CacheOperator cacheOperator = null;
	
	public static CacheManager getInstance() {
		return singleton;
	}
	
	public static CacheManager creatInstance(ApplicationContext applicationContext) {
		if (singleton != null) {
			return singleton;
		}
		synchronized (CacheManager.class) {
			if (singleton == null) {
				singleton = new CacheManager(applicationContext);
			}
			return singleton;
		}
	}
	//获取Redis缓存操作类
	private CacheManager (ApplicationContext applicationContext){
		cacheOperator = applicationContext.getBean("redisCacheOperator", CacheOperator.class);
	}
	
	/** 向缓存中增加内容 **/
	public void doPut(String key, Object value) throws Exception{
		cacheOperator.doPut(key, value, -1);
	}
	
	/** 向缓存中增加内容 **/
	public void doPut(String key, Object value, long timeout) throws Exception{
		cacheOperator.doPut(key, value, timeout);
	}
	
	/** 从缓存中获取内容**/
	public <T> T doGet(String key, Class<T> type) throws Exception{
		return cacheOperator.doGet(key, type);
	}
	
	/** 向缓存中增加内容(服务器级别) **/
	public void doPutToServletCache(String key, Object value) throws Exception{
		cacheOperator.doPutToServletCache(key, value);
	}
	
	/** 从缓存中获取内容 (服务器级别)**/
	public <T> T doGetfromServletCache(String key, Class<T> type) throws Exception{
		return cacheOperator.doGetfromServletCache(key, type);
	}
	
	/** 向缓存中增加内容 **/
	public void doPutToLogonUserCache(String key, Object value) throws Exception{
		cacheOperator.doPutToLogonUserCache(key, value);
	}
	
	/** 从缓存中获取内容 **/
	public <T> T doGetfromLogonUserCache(String key, Class<T> type) throws Exception{
		return cacheOperator.doGetfromLogonUserCache(key, type);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
