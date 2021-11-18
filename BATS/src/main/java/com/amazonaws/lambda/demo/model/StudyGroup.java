package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class StudyGroup{
	public final String studyGroupId;
	public String studyGroupName;
	public String studyGroupDescription;
	public String studyId;
	
	public String getStudyGroupId() {return studyGroupId;}
	public String getStudyGroupName() {return studyGroupName;}
	public String getStudyGroupDescription() {return studyGroupDescription;}
	public String getStudyId() {return studyId;}
	
	public StudyGroup(String studyGroupId, String studyGroupName, String studyGroupDescription, String studyId) {
		this.studyGroupId = studyGroupId;
		this.studyGroupName = studyGroupName;
		this.studyGroupDescription = studyGroupDescription;
		this.studyId = studyId;
	}
	
	public StudyGroup() {
		this.studyGroupId = null;
		this.studyGroupName = null;
		this.studyGroupDescription = null;
		this.studyId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof StudyGroup) {
			StudyGroup other = (StudyGroup) o;
			return studyGroupId.equals(other.studyGroupId);
		}
		
		return false;  // not a Constant
	}
	
	public String toString() {
		return "StudyGroup " + studyGroupId + " is: " + studyGroupName + ".";
	}
	
}