package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetDataRequest {
	String authUserId;
	String studyId;

	public String getAuthUserId() { return authUserId; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	public String getStudyId() { return studyId; }
	public void setStudyId(String studyId) { this.studyId = studyId; }
	
	public String toString() {
		return "GetDataRequest(" + authUserId + ", " + studyId + ")";
	}
	
	public GetDataRequest (String authUserId, String studyId) {
		this.authUserId = authUserId;
		this.studyId = studyId;
	}
	
	public GetDataRequest() {
	}
}
