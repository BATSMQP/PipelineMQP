package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetStudyToolsRequest {
	String studyId;

	public String getStudyId() { return studyId; }
	public void setStudyId(String studyId) { this.studyId = studyId; }
	
	public String toString() {
		return "GetStudyToolsRequest(" + studyId + ")";
	}
	
	public GetStudyToolsRequest (String studyId) {
		this.studyId = studyId;
	}
	
	public GetStudyToolsRequest() {
	}
}
