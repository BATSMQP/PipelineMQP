package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewStudyRequest {
	String name;
	String shortName;
	String studyAbstract;
	String authUserId;

	public String getName() { return name; }
	public String getShortName() { return shortName;}
	public String getStudyAstract() { return studyAbstract;}
	public String getAuthUserId() { return authUserId;}
	public void setName(String name) { this.name = name; }
	public void setShortName(String shortName) { this.shortName = shortName; }
	public void setStudyAbstract(String studyAbstract) { this.studyAbstract = studyAbstract; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	
	public String toString() {
		return "NewStudyRequest(" + name + ", "+ shortName + ", " + studyAbstract + ", " + authUserId + ")";
	}
	
	public NewStudyRequest (String name, String shortName, String studyAbstract, String authUserId) {
		this.name = name;
		this.shortName = shortName;
		this.studyAbstract = studyAbstract;
		this.authUserId = authUserId;
	}
	
	public NewStudyRequest() {
	}
}
