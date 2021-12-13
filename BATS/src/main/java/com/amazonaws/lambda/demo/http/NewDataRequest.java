package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Study;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewDataRequest {
	String file;
	String name;
	String dataType;
	ArrayList<Study> studies = new ArrayList<Study>();

	public String getFile() { return file; }
	public String getName() { return name;}
	public String getDataType() { return dataType;}
	public ArrayList<Study> getStudies() { return studies;}
	public void setFile(String file) { this.file = file; }
	public void setName(String name) { this.name = name; }
	public void setDataType(String dataType) { this.dataType = dataType; }
	public void setStudies(ArrayList<Study> studies) {this.studies = studies;}
	
	public String toString() {
		return "NewDataRequest(" + name + ", " + dataType + ", " + studies + ")";
	}
	
	public NewDataRequest (String file, String name, String dataType, ArrayList<Study> studies) {
		this.file = file;
		this.name = name;
		this.dataType = dataType;
		this.studies = studies;
	}
	
	public NewDataRequest() {
	}
}
