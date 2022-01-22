package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class GetStudyDataResponse {
	public final String error;
	public final int statusCode;
	public ArrayList<Document> data;
	
	public GetStudyDataResponse (String s, int statusCode, ArrayList<Document> data) {
		this.error = s;
		this.statusCode = statusCode;
		this.data = data;
	}
	
	// 200 means success
	public GetStudyDataResponse (String s, ArrayList<Document> data) {
		this.error = s;
		this.statusCode = 200;
		this.data = data;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public ArrayList<Document> getData() {return data;}
	
	public String toString() {
		return "GetStudyDataResponse(" + data + ": " + error + ")";
	}
}
