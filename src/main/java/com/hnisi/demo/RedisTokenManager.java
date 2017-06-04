package com.hnisi.demo;

import java.util.UUID;

import redis.clients.jedis.Jedis;
/**
 * Redis方案，实现token管理
 * 
 * @author lilu
 *
 */
public class RedisTokenManager implements TokenManager {
	
	@Override
	public TokenModel createToken(String username) {
		Jedis jedis = RedisUtil.getJedis();
		//生成源token
		String token = UUID.randomUUID().toString().replace("-", "");
		
        TokenModel model = new TokenModel(username, token);
        //存储到redis并设置过期时间
        jedis.set(token, username);
        jedis.expire(token, 60); //60s
        
        RedisUtil.returnJedis(jedis);
        return model;
	}

	@Override
	public boolean checkToken(String token) {
		if (token==null) {
			return false;
		}
		return false;
	}

	@Override
	public TokenModel getToken(String authentication) {
		//authentication类似userid_token
		if (authentication == null || authentication.length() == 0) {
            return null;
        }
		String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //解密拿到token
        String token = param[1];
        Jedis jedis = RedisUtil.getJedis();
        String username = jedis.get(token);
        RedisUtil.returnJedis(jedis);
        
        return new TokenModel(username, token);
	}

	@Override
	public void deleteToken(String token) {
		Jedis jedis = RedisUtil.getJedis();
		jedis.del(token);
		
		RedisUtil.returnJedis(jedis);
	}

}
