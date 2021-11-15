package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class LogDataDocument{
	public String documentId;
	public String logDataId;
	
	public String getDocumentId() {return documentId;}
	public String getLogDataId() {return logDataId;}
	
	public LogDataDocument(String logDataId, String documentId) {
		this.documentId = documentId;
		this.logDataId = logDataId;
	}
	
	public LogDataDocument() {
		this.documentId = null;
		this.logDataId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof LogDataDocument) {
			LogDataDocument other = (LogDataDocument) o;
			return (documentId.equals(other.documentId) && logDataId.equals(other.logDataId));
		}
		
		return false;
	}
	
	public String toString() {
		return "LogDataDocument has logDataId:" + logDataId + " and documentId: " + documentId + ".";
	}
	
}