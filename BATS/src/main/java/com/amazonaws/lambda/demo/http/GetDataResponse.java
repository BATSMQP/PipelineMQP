package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.Study;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class GetDataResponse {
	public final String error;
	public final int statusCode;
	public ArrayList<Document> data;
	public ArrayList<Study> studies;
	
	public GetDataResponse (String s, int statusCode, ArrayList<Document> data, ArrayList<Study> studies) {
		this.error = s;
		this.statusCode = statusCode;
		this.data = data;
		this.studies = studies;
	}
	
	// 200 means success
	public GetDataResponse (String s, ArrayList<Document> data, ArrayList<Study> studies) {
		this.error = s;
		this.statusCode = 200;
		this.data = data;
		this.studies = studies;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public ArrayList<Document> getData() {return data;}
	
	public ArrayList<Study> getStudies() {return studies;}
	
	public String toString() {
		return "GetDataResponse(" + data + ": " + error + ")";
	}
}
