package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class LoginRequest {
	String username;
	String password;

	public String getUsername() { return username; }
	public String getPassword() { return password;}
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	
	public String toString() {
		return "LoginRequest(" + username + ", "+ password + ")";
	}
	
	public LoginRequest (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginRequest() {
	}
}
