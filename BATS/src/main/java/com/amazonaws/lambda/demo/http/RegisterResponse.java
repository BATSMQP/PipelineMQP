package com.amazonaws.lambda.demo.http;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class RegisterResponse {
	public final String error;
	public final int statusCode;
	public final String username;
	
	public RegisterResponse (String s, int statusCode, String username) {
		this.error = s;
		this.statusCode = statusCode;
		this.username = username;
	}
	
	// 200 means success
	public RegisterResponse (String s, String username) {
		this.error = s;
		this.statusCode = 200;
		this.username = username;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public String getUsername() {return username;}
	
	public String toString() {
		return "RegisterResponse(" + username + ": " + error + ")";
	}
}
