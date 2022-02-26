package com.amazonaws.lambda.demo.http;

public class RunAlgRequest {
	String dataDocumentId;
	String toolDocumentId;
	String algName;
	String studyId;


	public String getDataDocumentId() { return dataDocumentId; }
	public String getToolDocumentId() { return toolDocumentId; }
	public String getAlgName() { return algName; }
	public String getStudyId() { return studyId; }


	public void setDataDocumentId(String dataDocumentId) { this.dataDocumentId = dataDocumentId; }
	public void setToolDocumentId(String toolDocumentId) { this.toolDocumentId = toolDocumentId; }
	public void setAlgName(String algName) { this.algName = algName; }
	public void setStudyId(String studyId) { this.studyId = studyId; }
	
	public String toString() {
		return "RunAlgRequest(dataDocumentId: " + dataDocumentId + ", toolDocumentId: "+ toolDocumentId + ", algName: "+ algName +", studyId: " + studyId + ")";
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
