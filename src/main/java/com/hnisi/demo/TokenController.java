package com.hnisi.demo;
/**
 * 
 * @author lilu
 *
 */
public class TokenController {

	
	private TokenManager tokenManager;
	
	public String login(String username, String password){
		if(username == "" || password == "") return "-1";
		//if 登录成功
		TokenModel model = tokenManager.createToken(username);
		System.out.println(model.getToken());
		System.out.println(model.getUsername());
		
		return model.getToken();
	}
	
	//业务方法，带上token
	public void search(){
		check();
		//TODO
		String authentication = "";
		TokenModel model = tokenManager.getToken(authentication);
		System.out.println(model.getUsername());
	}
	
	private void check(){
		//判断是否包含timestamp，token，sign参数
		//验证签名值
		String sign = "";
		//拿到参数，用MD5加密，比对值是否一样，如果一样，就通过
		//
		String token ="";
		tokenManager.checkToken(token);
	}
	
	
	public void logout(){
		String token  = "";//request
		tokenManager.deleteToken(token);
	}
}
