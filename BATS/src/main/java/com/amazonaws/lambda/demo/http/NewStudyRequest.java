package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewStudyRequest {
	String name;
	String shortName;
	String studyAbstract;

	public String getName() { return name; }
	public String getShortName() { return shortName;}
	public String getStudyAstract() { return studyAbstract;}
	public void setName(String name) { this.name = name; }
	public void setShortName(String shortName) { this.shortName = shortName; }
	public void setStudyAbstract(String studyAbstract) { this.studyAbstract = studyAbstract; }
	
	public String toString() {
		return "NewStudyRequest(" + name + ", "+ shortName + ", " + studyAbstract + ")";
	}
	
	public NewStudyRequest (String name, String shortName, String studyAbstract) {
		this.name = name;
		this.shortName = shortName;
		this.studyAbstract = studyAbstract;
	}
	
	public NewStudyRequest() {
	}
}
