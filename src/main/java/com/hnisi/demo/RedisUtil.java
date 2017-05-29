package com.hnisi.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 工具类，底层封装
 * 
 * @author lilu
 * 
 */
public class RedisUtil {

	private static JedisPool jedisPool = null;

	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// config.setMaxTotal(500);
			//
			jedisPool = new JedisPool(config, "192.168.22.202", 6379);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void returnJedis(Jedis jedis) {
		if (null != jedis && null != jedisPool) {
			jedisPool.returnResource(jedis);
		}
	}
}
