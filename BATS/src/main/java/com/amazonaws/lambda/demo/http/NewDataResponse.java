package com.amazonaws.lambda.demo.http;

import com.amazonaws.lambda.demo.model.Data;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class NewDataResponse {
	public final String error;
	public final int statusCode;
	public final Data data;
	
	public NewDataResponse (String s, int statusCode, Data data) {
		this.error = s;
		this.statusCode = statusCode;
		this.data = data;
	}
	
	// 200 means success
	public NewDataResponse (String s, Data data) {
		this.error = s;
		this.statusCode = 200;
		this.data = data;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public Data getData() {return data;}
	
	public String toString() {
		return "NewDataResponse(" + data + ": " + error + ")";
	}
}
