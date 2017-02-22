package org.sfmovie.dao.redis.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@ComponentScan("org.sfmovie")
public class RedisCacheConfig {
    private  String host = "redis-16561.c8.us-east-1-2.ec2.cloud.redislabs.com";
    private int port = 16561;
    private int database = 0; 
    public static final String MOVIE_LOCATION_CACHE = "movieLocationCache";
    @Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName(host);
		redisConnectionFactory.setPort(port);
		redisConnectionFactory.setDatabase(database);
		redisConnectionFactory.setUsePool(true);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		Map<String,Long> expires = new HashMap<String,Long>();
		expires.put(MOVIE_LOCATION_CACHE,600L);
		cacheManager.setExpires(expires);
		return cacheManager;
	}
}
