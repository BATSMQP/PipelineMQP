package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetStudiesRequest {
	String authUserId;

	public String getAuthUserId() { return authUserId; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	
	public String toString() {
		return "GetStudiesRequest(" + authUserId + ")";
	}
	
	public GetStudiesRequest (String authUserId) {
		this.authUserId = authUserId;
	}
	
	public GetStudiesRequest() {
	}
}
