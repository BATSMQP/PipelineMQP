package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Document;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class GetDataDocResponse {
	public final String error;
	public final int statusCode;
	public Document doc;
	
	public GetDataDocResponse (String s, int statusCode, Document doc) {
		this.error = s;
		this.statusCode = statusCode;
		this.doc = doc;
	}
	
	// 200 means success
	public GetDataDocResponse (String s, Document doc) {
		this.error = s;
		this.statusCode = 200;
		this.doc = doc;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public Document getDoc() {return doc;}
	
	public String toString() {
		return "GetDataDocResponse(" + doc + ": " + error + ")";
	}
}
