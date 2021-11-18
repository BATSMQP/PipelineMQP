package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Document{
	public final String documentId;
	public String filename;
	
	public String getDocumentId() {return documentId;}
	public String getFilename() {return filename;}
	
	public Document(String documentId, String filename) {
		this.documentId = documentId;
		this.filename = filename;
	}
	
	public Document() {
		this.documentId = null;
		this.filename = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Document) {
			Document other = (Document) o;
			return documentId.equals(other.documentId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Document " + documentId + " is filename: " + filename + ".";
	}
	
}