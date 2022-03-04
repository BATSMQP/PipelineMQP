package com.amazonaws.lambda.demo.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetDataDocRequest {
	String documentId;

	public String getDocumentId() { return documentId; }
	public void setDocumentId(String documentId) { this.documentId = documentId; }
	
	public String toString() {
		return "GetDataDocRequest(" + documentId + ")";
	}
	
	public GetDataDocRequest (String documentId) {
		this.documentId = documentId;
	}
	
	public GetDataDocRequest() {
	}
}
