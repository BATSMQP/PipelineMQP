package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetStudiesRequest {
	String authUserId;
	String username;

	public String getAuthUserId() { return authUserId; }
	public String getUsername() { return username; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	public void setUsername(String username) { this.username = username; }
	
	public String toString() {
		return "GetStudiesRequest(" + authUserId + ", " + username + ")";
	}
	
	public GetStudiesRequest (String authUserId, String username) {
		this.authUserId = authUserId;
		this.username = username;
	}
	
	public GetStudiesRequest() {
	}
}
