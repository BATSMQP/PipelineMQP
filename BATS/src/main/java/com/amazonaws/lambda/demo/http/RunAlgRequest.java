package com.amazonaws.lambda.demo.http;

public class RunAlgRequest {
	String dataDocumentId;
	String toolDocumentId;
	String algName;


	public String getDataDocumentID() { return dataDocumentId; }
	public String getToolDocumentID() { return toolDocumentId; }
	public String getAlgName() { return algName; }


	public void setDataDocumentID(String dataDocumentId) { this.dataDocumentId = dataDocumentId; }
	public void setToolDocumentID(String toolDocumentId) { this.toolDocumentId = toolDocumentId; }
	public void setAlgName(String algName) { this.algName = algName; }
	
	public String toString() {
		return "RunAlgRequest(" + dataDocumentId + ", "+ toolDocumentId + ", "+ algName +")";
	}
	
	public RunAlgRequest (String dataDocumentId, String toolDocumentId, String algName) {
		this.dataDocumentId = dataDocumentId;
		this.toolDocumentId = toolDocumentId;
		this.algName = algName;

	}
	
	public RunAlgRequest() {
	}
	
	
}
