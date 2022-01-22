package com.amazonaws.lambda.demo.model;

//import java.util.HashMap;

public class Document{
	public final String documentId;
	public String file;
	public String filename;
	public String name; //user given name
	public String dataType; //"Neural", "Facial", "Speech", "Stress Indicators", or "Log Data"
	public String ext;
	public String docType; //"Data" or "Tool"
//	public HashMap<String, String> tags = new HashMap<String, String>();
	
	public String getDocumentId() {return documentId;}
	public String getFile() {return file;}
	public String getFilename() {return filename;}
	public String getName() {return name;}
	public String getDataType() {return dataType;}
	public String getExt() {return ext;}
	public String getDocType() {return docType;}
//	public HashMap<String, String> getTags() { return tags;}
	
	public Document(String documentId, String file, String filename, String name, String dataType, String ext, String docType) {
		this.documentId = documentId;
		this.file = file;
		this.filename = filename;
		this.name = name;
		this.dataType = dataType;
		this.ext = ext;
		this.docType = docType;
//		this.tags = tags;
	}
	
	public Document() {
		this.documentId = null;
		this.file = null;
		this.filename = null;
		this.name = null;
		this.dataType = null;
		this.ext = null;
		this.docType = null;
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
		return docType + " Document " + name + " " + documentId + " " + filename + "." + ext + " is of dataType: " + dataType + ".";
	}
	
}