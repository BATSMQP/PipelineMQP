package com.amazonaws.lambda.demo.model;

public class Tag{
	public final String tagId;
	public String documentId;
	public String name;
	public String value;
	
	public String getTagId() {return tagId;}
	public String getDocumentId() {return documentId;}
	public String getName() {return name;}
	public String getValue() {return value;}
	
	public Tag(String tagId, String documentId, String name, String value) {
		this.tagId = tagId;
		this.documentId = documentId;
		this.name = name;
		this.value = value;
	}
	
	public Tag() {
		this.tagId = null;
		this.documentId = null;
		this.name = null;
		this.value = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Tag) {
			Tag other = (Tag) o;
			return tagId.equals(other.tagId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Tag " + tagId + " " + name + " has value: " + value + ".";
	}
	
}