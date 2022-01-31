package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class EditStudyInfoRequest {
	String studyId;
	String studyName;
	String studyShortName;
	String studyDescription;
	String institutionsInvolved;
	String studyContact;
	String studyNotes;
	String isIrbApproved;
	String visibility;

	public String getStudyId() { return studyId; }
	public String getStudyName() { return studyName; }
	public String getStudyShortName() { return studyShortName;}
	public String getStudyDescription() { return studyDescription;}
	public String getInstitutionsInvolved() { return institutionsInvolved;}
	public String getStudyContact() { return studyContact;}
	public String getStudyNotes() { return studyNotes;}
	public String getisIrbApproved() { return isIrbApproved;}
	public String getVisibility() { return visibility;}
	public void setStudyId(String studyId) { this.studyId = studyId; }
	public void setStudyName(String studyName) { this.studyName = studyName; }
	public void setStudyShortName(String studyShortName) { this.studyShortName = studyShortName; }
	public void setStudyDescription(String studyDescription) { this.studyDescription = studyDescription; }
	public void setInstitutionsInvolved(String institutionsInvolved) { this.institutionsInvolved = institutionsInvolved; }
	public void setStudyContact(String studyContact) { this.studyContact = studyContact; }
	public void setStudyNotes(String studyNotes) { this.studyNotes = studyNotes; }
	public void setIsIrbApproved(String isIrbApproved) { this.isIrbApproved = isIrbApproved; }
	public void setVisibility(String visibility) { this.visibility = visibility; }
	
	public String toString() {
		return "EditStudyInfoRequest(" + studyId + ")";
	}
	
	public EditStudyInfoRequest (String studyId, String studyName, String studyShortName, String studyDescription, String institutionsInvolved, String studyContact, String studyNotes, String isIrbApproved, String visibility) {
		this.studyId = studyId;
		this.studyName = studyName;
		this.studyShortName = studyShortName;
		this.studyDescription = studyDescription;
		this.institutionsInvolved = institutionsInvolved;
		this.studyContact = studyContact;
		this.studyNotes = studyNotes;
		this.isIrbApproved = isIrbApproved;
		this.visibility = visibility;
	}
	
	public EditStudyInfoRequest() {
	}
}
