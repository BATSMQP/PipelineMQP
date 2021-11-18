package com.amazonaws.lambda.demo.http;


/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class SaveAnalysisResponse {
	public final String error;
	public final int statusCode;
	
	public SaveAnalysisResponse (String s, int statusCode) {
		this.error = s;
		this.statusCode = statusCode;
	}
	
	// 200 means success
	public SaveAnalysisResponse (String s) {
		this.error = s;
		this.statusCode = 200;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public String toString() {
		return "SaveAnalysisResponse(" + error + ")";
	}
}
