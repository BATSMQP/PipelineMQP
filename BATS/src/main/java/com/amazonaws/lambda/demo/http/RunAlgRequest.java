package com.amazonaws.lambda.demo.http;

public class RunAlgRequest {
	String dataDocumentId;
	String toolDocumentId;
	String algName;
	String studyId;


	public String getDataDocumentID() { return dataDocumentId; }
	public String getToolDocumentID() { return toolDocumentId; }
	public String getAlgName() { return algName; }
	public String getStudyId() { return studyId; }


	public void setDataDocumentID(String dataDocumentId) { this.dataDocumentId = dataDocumentId; }
	public void setToolDocumentID(String toolDocumentId) { this.toolDocumentId = toolDocumentId; }
	public void setAlgName(String algName) { this.algName = algName; }
	public void setStudyId(String studyId) { this.algName = studyId; }
	
	public String toString() {
		return "RunAlgRequest(" + dataDocumentId + ", "+ toolDocumentId + ", "+ algName +")";
	}
	
	public RunAlgRequest (String dataDocumentId, String toolDocumentId, String algName, String studyId) {
		this.dataDocumentId = dataDocumentId;
		this.toolDocumentId = toolDocumentId;
		this.algName = algName;
		this.studyId = studyId;

	}
	
	public RunAlgRequest() {
	}
	
	
}
