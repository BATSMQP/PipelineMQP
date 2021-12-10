package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewStudyRequest {
	String name;
	String shortName;
	String studyAbstract;
	String institutionsInvolved;
	String studyContact;
	String studyNotes;
	String isIrbApproved;
	String visibility;
	String authUserId;

	public String getName() { return name; }
	public String getShortName() { return shortName;}
	public String getStudyAstract() { return studyAbstract;}
	public String getInstitutionsInvolved() { return institutionsInvolved;}
	public String getStudyContact() { return studyContact;}
	public String getStudyNotes() { return studyNotes;}
	public String getisIrbApproved() { return isIrbApproved;}
	public String getVisibility() { return visibility;}
	public String getAuthUserId() { return authUserId;}
	public void setName(String name) { this.name = name; }
	public void setShortName(String shortName) { this.shortName = shortName; }
	public void setStudyAbstract(String studyAbstract) { this.studyAbstract = studyAbstract; }
	public void setInstitutionsInvolved(String institutionsInvolved) { this.institutionsInvolved = institutionsInvolved; }
	public void setStudyContact(String studyContact) { this.studyContact = studyContact; }
	public void setStudyNotes(String studyNotes) { this.studyNotes = studyNotes; }
	public void setIsIrbApproved(String isIrbApproved) { this.isIrbApproved = isIrbApproved; }
	public void setVisibility(String visibility) { this.visibility = visibility; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	
	public String toString() {
		return "NewStudyRequest(" + name + ", "+ shortName + ", " + studyAbstract + ", " + institutionsInvolved + ", " + studyContact + ", " + studyNotes + ", " + isIrbApproved + ", " + visibility + ", " + authUserId + ")";
	}
	
	public NewStudyRequest (String name, String shortName, String studyAbstract, String institutionsInvolved, String studyContact, String studyNotes, String isIrbApproved, String visibility, String authUserId) {
		this.name = name;
		this.shortName = shortName;
		this.studyAbstract = studyAbstract;
		this.institutionsInvolved = institutionsInvolved;
		this.studyContact = studyContact;
		this.studyNotes = studyNotes;
		this.isIrbApproved = isIrbApproved;
		this.visibility = visibility;
		this.authUserId = authUserId;
	}
	
	public NewStudyRequest() {
	}
}
