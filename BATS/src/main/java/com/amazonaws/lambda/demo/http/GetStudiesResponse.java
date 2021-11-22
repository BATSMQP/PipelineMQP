package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.LogData;
import com.amazonaws.lambda.demo.model.Study;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class GetStudiesResponse {
	public final String error;
	public final int statusCode;
	public ArrayList<Study> studies = new ArrayList<Study>();
	
	public GetStudiesResponse (String s, int statusCode, ArrayList<Study> studies) {
		this.error = s;
		this.statusCode = statusCode;
		this.studies = studies;
	}
	
	// 200 means success
	public GetStudiesResponse (String s, ArrayList<Study> studies) {
		this.error = s;
		this.statusCode = 200;
		this.studies = studies;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public ArrayList<Study> getStudies() {return studies;}
	
	public String toString() {
		return "GetStudiesResponse(" + studies + ": " + error + ")";
	}
}
