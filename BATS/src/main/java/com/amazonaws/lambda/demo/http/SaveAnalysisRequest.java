package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class SaveAnalysisRequest {
	String name;
	Data data;
	ArrayList<Document> analysis = new ArrayList<Document>();

	public String getName() { return name;}
	public Data getData() { return data; }
	public ArrayList<Document> getAnalysis() { return analysis;}
	public void setName(String name) { this.name = name; }
	public void setData(Data data) { this.data = data; }
	public void setAnalysis(ArrayList<Document> analysis) {this.analysis = analysis;}
	
	public String toString() {
		return "SaveAnalysisRequest(" + name + ", "+ data + ", " + analysis + ")";
	}
	
	public SaveAnalysisRequest (String name, Data data, ArrayList<Document> analysis) {
		this.name = name;
		this.data = data;
		this.analysis = analysis;
	}
	
	public SaveAnalysisRequest() {
	}
}
