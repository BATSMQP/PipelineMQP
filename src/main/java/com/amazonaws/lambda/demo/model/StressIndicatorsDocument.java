package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class StressIndicatorsDocument{
	public String documentId;
	public String siId;
	
	public String getDocumentId() {return documentId;}
	public String getSiId() {return siId;}
	
	public StressIndicatorsDocument(String siId, String documentId) {
		this.documentId = documentId;
		this.siId = siId;
	}
	
	public StressIndicatorsDocument() {
		this.documentId = null;
		this.siId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof StressIndicatorsDocument) {
			StressIndicatorsDocument other = (StressIndicatorsDocument) o;
			return (documentId.equals(other.documentId) && siId.equals(other.siId));
		}
		
		return false;
	}
	
	public String toString() {
		return "StressIndicatorsDocument has siId:" + siId + " and documentId: " + documentId + ".";
	}
	
}