package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class SpeechDocument{
	public String documentId;
	public String speechId;
	
	public String getDocumentId() {return documentId;}
	public String getSpeechId() {return speechId;}
	
	public SpeechDocument(String speechId, String documentId) {
		this.documentId = documentId;
		this.speechId = speechId;
	}
	
	public SpeechDocument() {
		this.documentId = null;
		this.speechId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof SpeechDocument) {
			SpeechDocument other = (SpeechDocument) o;
			return (documentId.equals(other.documentId) && speechId.equals(other.speechId));
		}
		
		return false;
	}
	
	public String toString() {
		return "SpeechDocument has speechId:" + speechId + " and documentId: " + documentId + ".";
	}
	
}