package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class FacialDocument{
	public String documentId;
	public String facialId;
	
	public String getDocumentId() {return documentId;}
	public String getFacialId() {return facialId;}
	
	public FacialDocument(String facialId, String documentId) {
		this.documentId = documentId;
		this.facialId = facialId;
	}
	
	public FacialDocument() {
		this.documentId = null;
		this.facialId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof FacialDocument) {
			FacialDocument other = (FacialDocument) o;
			return (documentId.equals(other.documentId) && facialId.equals(other.facialId));
		}
		
		return false;
	}
	
	public String toString() {
		return "FacialDocument has facialId:" + facialId + " and documentId: " + documentId + ".";
	}
	
}