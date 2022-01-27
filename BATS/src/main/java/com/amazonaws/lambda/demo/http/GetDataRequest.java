package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetDataRequest {
	String authUserId;

	public String getAuthUserId() { return authUserId; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	
	public String toString() {
		return "GetDataRequest(" + authUserId + ")";
	}
	
	public GetDataRequest (String authUserId) {
		this.authUserId = authUserId;
	}
	
	public GetDataRequest() {
	}
}
