package com.amazonaws.lambda.demo.http;

//import java.util.ArrayList;

//import com.amazonaws.lambda.demo.model.Study;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class NewDataRequest {
	String file;
	String filename;
	String name;
	String dataType;
	String ext;
	String studyId;
	String authUserId;

	public String getFile() { return file; }
	public String getFilename() { return filename; }
	public String getName() { return name;}
	public String getDataType() { return dataType;}
	public String getExt() { return ext;}
	public String getStudyId() { return studyId; }
	public String getAuthUserId() { return authUserId; }
	public void setFile(String file) { this.file = file; }
	public void setFilename(String filename) { this.filename = filename; }
	public void setName(String name) { this.name = name; }
	public void setDataType(String dataType) { this.dataType = dataType; }
	public void setExt(String ext) { this.ext = ext; }
	public void setStudyId(String studyId) { this.studyId = studyId; }
	public void setAuthUserId(String authUserId) { this.authUserId = authUserId; }
	
	public String toString() {
		return "NewDataRequest(" + name + ", " + dataType + ", " + ext + ")";
	}
	
	public NewDataRequest (String file, String filename, String name, String dataType, String ext, String studyId, String authUserId) {
		this.file = file;
		this.filename = filename;
		this.name = name;
		this.dataType = dataType;
		this.ext = ext;
		this.studyId = studyId;
		this.authUserId = authUserId;
	}
	
	public NewDataRequest() {
	}
}
