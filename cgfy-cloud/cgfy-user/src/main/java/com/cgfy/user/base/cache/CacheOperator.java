package com.cgfy.user.base.cache;

/**
 * 缓存操作接口
 */
public interface CacheOperator{
	
	/**
	 * 向缓存中增加内容
	 * @param key 
	 * @param value
	 * @param timeout 超时时间 单位秒
	 * @throws Exception
	 */
	public void doPut(String key, Object value, long timeout) throws Exception;
	
	/** 从缓存中获取内容 **/
	public <T> T doGet(String key, Class<T> type) throws Exception;
	
	/** 向缓存中增加内容(服务器级别) **/
	public void doPutToServletCache(String key, Object value) throws Exception;
	
	/** 从缓存中获取内容 (服务器级别)**/
	public <T> T doGetfromServletCache(String key, Class<T> type) throws Exception;
	
	/** 向缓存中增加内容 **/
	public void doPutToLogonUserCache(String key, Object value) throws Exception;
	
	/** 从缓存中获取内容 **/
	public <T> T doGetfromLogonUserCache(String key, Class<T> type) throws Exception;
	
}
