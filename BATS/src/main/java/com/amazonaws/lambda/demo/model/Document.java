package com.amazonaws.lambda.demo.model;

import java.util.HashMap;

public class Document{
	public final String documentId;
	public String file;
	public String name;
	public String dataType;
	public String ext;
//	public HashMap<String, String> tags = new HashMap<String, String>();
	
	public String getDocumentId() {return documentId;}
	public String getFile() {return file;}
	public String getName() {return name;}
	public String getDataType() {return dataType;}
	public String getExt() {return ext;}
//	public HashMap<String, String> getTags() { return tags;}
	
	public Document(String documentId, String file, String name, String dataType, String ext) {
		this.documentId = documentId;
		this.file = file;
		this.name = name;
		this.dataType = dataType;
		this.ext = ext;
//		this.tags = tags;
	}
	
	public Document() {
		this.documentId = null;
		this.file = null;
		this.name = null;
		this.dataType = null;
		this.ext = null;
//		this.tags = null;
	}
	
//	public void addTag(String name, String value) {
//		tags.put(name, value);
//	}
	
//	public void addTags(HashMap<String, String> tags) {
//		this.tags = tags;
//	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Document) {
			Document other = (Document) o;
			return documentId.equals(other.documentId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Document " + name + " " + documentId + " with ext of " + ext + " is of dataType: " + dataType + ".";
	}
	
}