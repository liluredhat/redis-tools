package com.hnisi.demo;

import redis.clients.jedis.Jedis;

/**
 * 业务类
 * 
 * @author lilu
 * 
 */
public class SmsService {

	public void sendSmsMessage(String phone, String message) {
		Jedis jedis = null;
		try {
			String key = "sms.limit." + phone;
			jedis = RedisUtil.getJedis();
			Long count = jedis.incrBy(key, 1);
			if (count.intValue() == 1) {
				jedis.expire(key, 60);
			} else if(count.intValue() > 1){
				throw new RuntimeException("每分钟只能发送一次短信!");
			}
			//send sms message by invoking sms api
			System.out.println(message + " send.");
		} finally{
			RedisUtil.returnJedis(jedis);
		}
	}

	public static void main(String[] args) {
		new SmsService().sendSmsMessage("15018766333", "测试短信");
		new SmsService().sendSmsMessage("15018766333", "测试短信");
		new SmsService().sendSmsMessage("15018766333", "测试短信");
	}
}
