package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class AuthUserDocument{
	public String authUserId;
	public String documentId;
	
	public String getAuthUserId() {return authUserId;}
	public String getDocumentId() {return documentId;}
	
	public AuthUserDocument(String authUserId, String documentId) {
		this.authUserId = authUserId;
		this.documentId = documentId;
	}
	
	public AuthUserDocument() {
		this.authUserId = null;
		this.documentId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof AuthUserDocument) {
			AuthUserDocument other = (AuthUserDocument) o;
			return (authUserId.equals(other.authUserId) && documentId.equals(other.documentId));
		}
		
		return false;
	}
	
	public String toString() {
		return "AuthUserDocument has authUserId:" + authUserId + " and documentId: " + documentId + ".";
	}
	
}