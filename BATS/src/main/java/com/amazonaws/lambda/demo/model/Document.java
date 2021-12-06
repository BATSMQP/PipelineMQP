package com.amazonaws.lambda.demo.model;

public class Document{
	public final String documentId;
	public String filename;
	public String name;
	public String dataType;
	
	public String getDocumentId() {return documentId;}
	public String getFilename() {return filename;}
	public String getName() {return name;}
	public String getDataType() {return dataType;}
	
	public Document(String documentId, String filename, String name, String dataType) {
		this.documentId = documentId;
		this.filename = filename;
		this.name = name;
		this.dataType = dataType;
	}
	
	public Document() {
		this.documentId = null;
		this.filename = null;
		this.name = null;
		this.dataType = null;
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
		return "Document " + name + " " + documentId + " is filename: " + filename + " of dataType: " + dataType + ".";
	}
	
}