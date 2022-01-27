package com.amazonaws.lambda.demo.http;

public class RunAlgResponse {
	public final String error;
	public final int statusCode;
	public final String resultFile;
	public final String image;
	
	public RunAlgResponse (String e, int statusCode, String resultFile, String image) {
		this.error = e;
		this.statusCode = statusCode;
		this.resultFile = resultFile;
		this.image = image;
	}
	
	// 200 means success
	public RunAlgResponse (String e, String username, String resultFile, String image) {
		this.error = e;
		this.statusCode = 200;
		this.resultFile = resultFile;
		this.image = image;
	}
	
	public String getError() {return error;}

	public int getStatusCode() { return statusCode;}
	
	public String getResultFile() {return resultFile;}
	public String getImage() {return image;}
	
	public String toString() {
		return "RunAlgResponse(" + resultFile + ": " + image + ": " + error + ")";
	}
	
	
}
