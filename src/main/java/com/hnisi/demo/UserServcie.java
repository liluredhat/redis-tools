package com.hnisi.demo;

import java.util.BitSet;

import redis.clients.jedis.Jedis;
/**
 * 
 * @author lilu
 *
 */
public class UserServcie {

	public boolean login(String username, String password) {
		//
		if (username == "lilu" && password == "lilu") {
			//
			Jedis jedis = RedisUtil.getJedis();
			long userid = 0l;
			jedis.setbit("login:" + "2017-05-29", userid, true);
			//
			RedisUtil.returnJedis(jedis);
			return true;
		}
		return false;
	}

	public int count() {
		Jedis jedis = RedisUtil.getJedis();
		BitSet b = BitSet.valueOf(jedis.get("login:2017-05-29".getBytes()));
		int num = b.cardinality();
		System.out.println("2017-05-29 login user number: " + num);
		return num;
	}

	public static void main(String[] args) {
		new UserServcie().login("xx", "xx");
		new UserServcie().login("lilu", "lilu");
		new UserServcie().count();
	}
}
