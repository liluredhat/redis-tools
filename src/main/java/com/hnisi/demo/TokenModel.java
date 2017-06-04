package com.hnisi.demo;

public class TokenModel {

	private String username;
	//随机生成的uuid
    private String token;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public TokenModel(String username, String token) {
		this.username = username;
		this.token = token;
	}
    
    
}
