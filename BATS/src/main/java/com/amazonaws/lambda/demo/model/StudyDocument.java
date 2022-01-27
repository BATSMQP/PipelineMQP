package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class StudyDocument{
	public String studyId;
	public String documentId;
	
	public String getStudyId() {return studyId;}
	public String getDocumentId() {return documentId;}
	
	public StudyDocument(String studyId, String documentId) {
		this.studyId = studyId;
		this.documentId = documentId;
	}
	
	public StudyDocument() {
		this.studyId = null;
		this.documentId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof StudyDocument) {
			StudyDocument other = (StudyDocument) o;
			return (studyId.equals(other.studyId) && documentId.equals(other.documentId));
		}
		
		return false;
	}
	
	public String toString() {
		return "StudyDocument has studyId:" + studyId + " and documentId: " + documentId + ".";
	}
	
}