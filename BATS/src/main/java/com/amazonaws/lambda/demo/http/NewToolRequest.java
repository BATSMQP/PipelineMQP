package com.amazonaws.lambda.demo.http;

import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.Study;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewToolRequest {
	String file;
	String filename;
	String name;
	String dataType;
	String ext;

	public String getFile() { return file; }
	public String getFilename() { return filename; }
	public String getName() { return name;}
	public String getDataType() { return dataType;}
	public String getExt() { return ext;}
	public void setFile(String file) { this.file = file; }
	public void setFilename(String filename) { this.filename = filename; }
	public void setName(String name) { this.name = name; }
	public void setDataType(String dataType) { this.dataType = dataType; }
	public void setExt(String ext) { this.ext = ext; }
	
	public String toString() {
		return "NewToolRequest(" + file + ", "+ name + ", " + dataType + ", " +  ext + ")";
	}
	
	public NewToolRequest (String file, String filename, String name, String dataType, String ext) {
		this.file = file;
		this.filename = filename;
		this.name = name;
		this.dataType = dataType;
		this.ext = ext;
	}
	
	public NewToolRequest() {
	}
}
