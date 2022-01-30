package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetStudyInfoRequest {
	String studyId;

	public String getStudyId() { return studyId; }
	public void setStudyId(String studyId) { this.studyId = authUserIdstudyId; }
	
	public String toString() {
		return "GetStudyInfoRequest(" + studyId + ")";
	}
	
	public GetStudyInfoRequest (String studyId) {
		this.studyId = studyId;
	}
	
	public GetStudyInfoRequest() {
	}
}
