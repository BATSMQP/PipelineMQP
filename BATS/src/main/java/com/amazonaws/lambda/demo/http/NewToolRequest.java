package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Study;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewToolRequest {
	String file;
	String name;
	String dataType;

	public String getFile() { return file; }
	public String getName() { return name;}
	public String getDataType() { return dataType;}
	public void setFile(String file) { this.file = file; }
	public void setName(String name) { this.name = name; }
	public void setDataType(String dataType) { this.dataType = dataType; }
	
	public String toString() {
		return "NewToolRequest(" + file + ", "+ name + ", " + dataType + ")";
	}
	
	public NewToolRequest (String file, String name, String dataType) {
		this.file = file;
		this.name = name;
		this.dataType = dataType;
	}
	
	public NewToolRequest() {
	}
}
