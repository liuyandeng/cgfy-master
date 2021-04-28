package com.cgfy.mybatis.base.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {



    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName().trim());
                for (Object obj : params) {
                    sb.append(obj.getClass().getName());
                }
                return sb.toString();
            }
        };

    }
    /**
	 * 	<p>SpringBoot配置redis作为默认缓存工具,SpringBoot 2.0 以上版本的配置</p>
	 *	基于注解的缓存,开启Spring的缓存注解功能,使用Redis缓存数据库数据
	 *  Redis有很多使用场景，其中一点就是缓存数据库的数据。Redis作为一个内存数据库，存取数据的速度比传统的数据库快得多。使用Redis缓存数据库数据，可以减轻系统对数据库的访问压力，及加快查询效率等好处。
	 *  Spring支持多种缓存技术：RedisCacheManager、EhCacheCacheManager、GuavaCacheManager等，
	 *  基于注解的缓存能够在现有的代码基础上只需要加入少量的缓存注解即能够达到缓存方法的返回结果的效果。
	 *   步骤:
	 *   1.配置CacheManager类型的bean
	 *   2.配置好之后使用注解来缓存数据(详细查看CacheTestServiceImpl):
	 *   	@EnableCaching:写在配置类上,开启Spring的缓存注解功能
	 *  	@CacheConfig:写在类上
	 *  	@Cacheable:有则用,无则新增,多用于查询
	 *  	@CachePut:将返回值保存到缓存中,多用于新增和修改
	 *  	@CacheEvict:清除缓存,多用于删除
	 */
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
		// 设置序列化
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
	    om.enableDefaultTyping(DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		StringRedisSerializer stringSerializer = new StringRedisSerializer();

		// 设置缓存默认过期时间, 10分钟
		RedisCacheConfiguration config =
				RedisCacheConfiguration.defaultCacheConfig()
				// 缓存数据保存10分钟
                .entryTtl(Duration.ofMinutes(10))
				// 设置key为String
                .serializeKeysWith(SerializationPair.fromSerializer(stringSerializer))
				// 设置value 为自动转Json的Object
                .serializeValuesWith(SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				// 不缓存null
                .disableCachingNullValues();

        return RedisCacheManager
				// Redis 连接工厂
				.builder(factory)
				// 缓存配置
				.cacheDefaults(config)
				// 配置同步修改或删除 put/evict
				.transactionAware()
				.build();
    }



	/**
	 * RedisTemplate<K,V>类的配置
	 * Spring在 org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration类下配置的两个RedisTemplate的Bean。
	 * (1) RedisTemplate<Object, Object>
	 * 这个Bean使用JdkSerializationRedisSerializer进行序列化，即key, value需要实现Serializable接口，redis数据格式为二进制比较难懂
	 * (2) StringRedisTemplate，即RedisTemplate<String, String>
	 * key和value都是String。当需要存储实体类时，需要先转为String，再存入Redis。一般转为Json格式的字符串，所以使用StringRedisTemplate，需要手动将实体类转为Json格式
	 * Spring配置的两个RedisTemplate都不太方便使用，所以可以配置一个RedisTemplate<String,Object> 的Bean，key使用String即可(包括Redis Hash 的key)，value存取Redis时默认使用Json格式转换。
	 * 配置一个RedisTemplate<String,Object>的Bean
	 * Springboot 2.1.0.RELEASE 默认的Redis客户端为 Lettuce，默认的连接工厂为LettuceConnectionFactory另外还有JedisConnectionFactory
	 * @param factory
	 * @link -- https://www.cnblogs.com/caizhaokai/p/11037610.html
	 * @link -- https://www.cnblogs.com/yangyongjie/p/11759563.html
	 * @return
	 */
	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

    	// 设置序列化
		// 定义Jackson2JsonRedisSerializer序列化对象
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
		om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会报异常
	    om.enableDefaultTyping(DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		StringRedisSerializer stringSerializer = new StringRedisSerializer();

		// 配置redisTemplate
		// 创建RedisTemplate<String, Object>对象
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		// 配置连接工厂
		redisTemplate.setConnectionFactory(factory);
		// redis key 序列化方式使用stringSerial
		redisTemplate.setKeySerializer(stringSerializer);// key序列化
		// redis value 序列化方式使用jackson
		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);// value序列化
		// redis hash key 序列化方式使用stringSerial
		redisTemplate.setHashKeySerializer(stringSerializer);// Hash key序列化
		// redis hash value 序列化方式使用jackson
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);// Hash value序列化
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
    }

}
