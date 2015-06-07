package com.shashajing.benison.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 基础dao类
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class RedisBaseDao<K,V> {

	@Autowired
	private RedisTemplate<K, V> redisTemplate;
	

}
