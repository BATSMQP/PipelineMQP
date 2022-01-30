package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Study;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class EditStudyInfoResponse {
	public final String error;
	public final int statusCode;
	
	public EditStudyInfoResponse (String s, int statusCode) {
		this.error = s;
		this.statusCode = statusCode;
	}
	
	// 200 means success
	public EditStudyInfoResponse (String s) {
		this.error = s;
		this.statusCode = 200;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public String toString() {
		return "EditStudyInfoResponse(" +  error + ")";
	}
}
