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
public class GetStudyToolsResponse {
	public final String error;
	public final int statusCode;
	public ArrayList<Document> tools;
	
	public GetStudyToolsResponse (String s, int statusCode, ArrayList<Document> tools) {
		this.error = s;
		this.statusCode = statusCode;
		this.tools = tools;
	}
	
	// 200 means success
	public GetStudyToolsResponse (String s, ArrayList<Document> tools) {
		this.error = s;
		this.statusCode = 200;
		this.tools = tools;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public ArrayList<Document> getTools() {return tools;}
	
	public String toString() {
		return "GetStudyToolsResponse(" + tools + ": " + error + ")";
	}
}
