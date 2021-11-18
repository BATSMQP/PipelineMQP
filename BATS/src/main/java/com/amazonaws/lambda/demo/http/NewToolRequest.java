package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Study;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewToolRequest {
	String filepath;
	String name;
	String dataType;

	public String getFilepath() { return filepath; }
	public String getName() { return name;}
	public String getDataType() { return dataType;}
	public void setFilepath(String filepath) { this.filepath = filepath; }
	public void setName(String name) { this.name = name; }
	public void setDataType(String dataType) { this.dataType = dataType; }
	
	public String toString() {
		return "NewToolRequest(" + filepath + ", "+ name + ", " + dataType + ")";
	}
	
	public NewToolRequest (String filepath, String name, String dataType) {
		this.filepath = filepath;
		this.name = name;
		this.dataType = dataType;
	}
	
	public NewToolRequest() {
	}
}
