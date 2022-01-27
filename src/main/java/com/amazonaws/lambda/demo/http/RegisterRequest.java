package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class RegisterRequest {
	String username;
	String password;
	String email;

	public String getUsername() { return username; }
	public String getPassword() { return password;}
	public String getEmail() { return email;}
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setEmail(String email) { this.email = email; }
	
	public String toString() {
		return "RegisterRequest(" + username + ", "+ password + ", " + email + ")";
	}
	
	public RegisterRequest (String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public RegisterRequest() {
	}
}
