package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Study;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class GetStudyInfoResponse {
	public final String error;
	public final int statusCode;
	public Study study;
	
	public GetStudyInfoResponse (String s, int statusCode, Study study) {
		this.error = s;
		this.statusCode = statusCode;
		this.study = study;
	}
	
	// 200 means success
	public GetStudyInfoResponse (String s, Study study) {
		this.error = s;
		this.statusCode = 200;
		this.study = study;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public Study getStudy() {return study;}
	
	public String toString() {
		return "GetStudyInfoResponse(" + study + ": " + error + ")";
	}
}
