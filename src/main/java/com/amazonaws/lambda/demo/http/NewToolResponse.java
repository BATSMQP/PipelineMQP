package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Document;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class NewToolResponse {
	public final String error;
	public final int statusCode;
	public final Document tool;
	
	public NewToolResponse (String s, int statusCode, Document tool) {
		this.error = s;
		this.statusCode = statusCode;
		this.tool = tool;
	}
	
	// 200 means success
	public NewToolResponse (String s, Document tool) {
		this.error = s;
		this.statusCode = 200;
		this.tool = tool;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public Document getTool() {return tool;}
	
	public String toString() {
		return "NewToolResponse(" + tool + ": " + error + ")";
	}
}
