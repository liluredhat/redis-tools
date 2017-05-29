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
public final class RedisUtil {

	private static JedisPool jedisPool = null;

	private static int MAX_ACTIVE = 1024;
	private static int MAX_IDLE = 200;
	private static int MAX_WAIT = 10000;
	private static int TIMEOUT = 10000;

	private static String HOST = "192.168.22.202";
	private static int PORT = 6379;

	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWait(MAX_WAIT);
			config.setMaxActive(MAX_ACTIVE);
			jedisPool = new JedisPool(config, HOST, PORT);
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

	public static void returnJedis(Jedis jedis) {
		if (null != jedis && null != jedisPool) {
			jedisPool.returnResource(jedis);
		}
	}
}
