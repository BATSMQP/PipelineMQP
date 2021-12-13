package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetUsernameRequest {
	String authUserId;

	public String getAuthUserId() { return authUserId; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	
	public String toString() {
		return "GetUsernameRequest(" + authUserId + ")";
	}
	
	public GetUsernameRequest (String authUserId) {
		this.authUserId = authUserId;
	}
	
	public GetUsernameRequest() {
	}
}
