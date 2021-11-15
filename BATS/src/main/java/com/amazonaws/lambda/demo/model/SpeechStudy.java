package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class SpeechStudy{
	public String studyId;
	public String speechId;
	
	public String getStudyId() {return studyId;}
	public String getSpeechId() {return speechId;}
	
	public SpeechStudy(String speechId, String studyId) {
		this.studyId = studyId;
		this.speechId = speechId;
	}
	
	public SpeechStudy() {
		this.studyId = null;
		this.speechId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof SpeechStudy) {
			SpeechStudy other = (SpeechStudy) o;
			return (studyId.equals(other.studyId) && speechId.equals(other.speechId));
		}
		
		return false;
	}
	
	public String toString() {
		return "SpeechStudy has speechId:" + speechId + " and studyId: " + studyId + ".";
	}
	
}