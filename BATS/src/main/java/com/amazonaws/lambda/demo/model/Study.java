package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Study{
	public final String studyId;
	public String institutionsInvolved;
	public String studyDescription;
	public String studyName;
	public String studyShortName;
	public String studyContact;
	public String studyNotes;
	public String visibility;
	public String isIrbApproved;
	public Timestamp studyStartDate;
	public Timestamp studyEndDate;
	public String authUserId;
	
	public String getStudyId() {return studyId;}
	public String getInstitutionsInvolved() {return institutionsInvolved;}
	public String getStudyDescription() {return studyDescription;}
	public String getStudyName() {return studyName;}
	public String getStudyShortName() {return studyShortName;}
	public String getStudyContact() {return studyContact;}
	public String getStudyNotes() {return studyNotes;}
	public String getVisibility() {return visibility;}
	public String getIsIrbApproved() {return isIrbApproved;}
	public Timestamp getStudyStartDate() {return studyStartDate;}
	public Timestamp getStudyEndDate() {return studyEndDate;}
	public String getAuthUserId() {return authUserId;}
	
	public Study(String studyId, String institutionsInvolved, String studyDescription, String studyName, String studyShortName, String studyContact, String studyNotes, String visibility, String isIrbApproved, Timestamp studyStartDate, Timestamp studyEndDate, String authUserId) {
		this.studyId = studyId;
		this.institutionsInvolved = institutionsInvolved;
		this.studyDescription = studyDescription;
		this.studyName = studyName;
		this.studyShortName = studyShortName;
		this.studyContact = studyContact;
		this.studyNotes = studyNotes;
		this.visibility = visibility;
		this.isIrbApproved = isIrbApproved;
		this.studyStartDate = studyStartDate;
		this.studyEndDate = studyEndDate;
		this.authUserId = authUserId;
	}
	
	public Study(String studyId, String studyName, String studyShortName, String studyDescription, Timestamp studyStartDate, String authUserId) {
		this.studyId = studyId;
		this.institutionsInvolved = "";
		this.studyDescription = studyDescription;
		this.studyName = studyName;
		this.studyShortName = studyShortName;
		this.studyContact = "";
		this.studyNotes = "";
		this.visibility = "";
		this.isIrbApproved = "";
		this.studyStartDate = studyStartDate;
		this.studyEndDate = null;
		this.authUserId = authUserId;
	}
	
	public Study() {
		this.studyId = null;
		this.institutionsInvolved = null;
		this.studyDescription = null;
		this.studyName = null;
		this.studyShortName = null;
		this.studyContact = null;
		this.studyNotes = null;
		this.visibility = null;
		this.isIrbApproved = null;
		this.studyStartDate = null;
		this.studyEndDate = null;
		this.authUserId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Study) {
			Study other = (Study) o;
			return studyId.equals(other.studyId);
		}
		
		return false;  // not a Constant
	}
	
	public String toString() {
		return "Study " + studyId + " is: " + studyName + ".";
	}
	
}
