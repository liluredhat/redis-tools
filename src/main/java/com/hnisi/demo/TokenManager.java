package com.hnisi.demo;

public interface TokenManager {

	 public TokenModel createToken(String username);
	 
	 public boolean checkToken(String token);
	 
	 public TokenModel getToken(String authentication);
	 
	 public void deleteToken(String token);
}
