package com.cgfy.mybatis.base.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * Redis缓存操作类
 */
@Component("redisCacheOperator")
public class RedisCacheOperator implements CacheOperator{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/** 向缓存中增加内容 **/
	public void doPut(String key, Object value) throws Exception{
		this.doPut(key, value, -1);
	}

	/** 向缓存中增加内容 **/
	public void doPut(String key, Object value, long timeout) throws Exception{
		this.doPut(key, value, timeout, TimeUnit.SECONDS);
	}

	/** 向缓存中增加内容 **/
	public void doPut(String key, Object value, long timeout, TimeUnit unit) throws Exception{
		ValueOperations<String, Object> operations= redisTemplate.opsForValue();
		operations.set(key, value);
		if(timeout>0){
			operations.getOperations().expire(key, timeout, unit);
		}
	}

	/** 从缓存中获取内容 **/
	@SuppressWarnings("unchecked")
	public <T> T doGet(String key, Class<T> type) throws Exception{
		T obj = null;
		Object value = redisTemplate.opsForValue().get(key);
		if(value!=null){
			obj = (T) value;
		}
		return obj;
	}

	@Override
	public void doPutToServletCache(String key, Object value) throws Exception {
		this.doPut(key, value);
	}

	@Override
	public <T> T doGetfromServletCache(String key, Class<T> type)
			throws Exception {
		return this.doGet(key, type);
	}

	@Override
	public void doPutToLogonUserCache(String key, Object value)
			throws Exception {
		this.doPut(key, value, 60*3600, TimeUnit.SECONDS);
	}

	@Override
	public <T> T doGetfromLogonUserCache(String key, Class<T> type)
			throws Exception {
		T value = this.doGet(key, type);
		if(value!=null){
			this.doPutToLogonUserCache(key, value);
		}
		return value;
	}
}
