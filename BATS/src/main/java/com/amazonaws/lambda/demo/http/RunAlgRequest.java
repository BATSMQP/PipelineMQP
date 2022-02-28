package com.amazonaws.lambda.demo.http;

public class RunAlgRequest {
	String dataDocumentId;
	String toolDocumentId;
	String algName;
	String studyId;
	double cutoff;
	double order;


	public String getDataDocumentId() { return dataDocumentId; }
	public String getToolDocumentId() { return toolDocumentId; }
	public String getAlgName() { return algName; }
	public String getStudyId() { return studyId; }
	public double getCutoff() {return cutoff; }
	public double getOrder() {return order; }


	public void setDataDocumentId(String dataDocumentId) { this.dataDocumentId = dataDocumentId; }
	public void setToolDocumentId(String toolDocumentId) { this.toolDocumentId = toolDocumentId; }
	public void setAlgName(String algName) { this.algName = algName; }
	public void setStudyId(String studyId) { this.studyId = studyId; }
	public void setCutoff(double cutoff) { this.cutoff = cutoff; }
	public void setOrder(double order) { this.order = order; }
	
	public String toString() {
		return "RunAlgRequest(dataDocumentId: " + dataDocumentId + ", toolDocumentId: "+ toolDocumentId + ", algName: "+ algName +", studyId: " + studyId + ", cutoff: " + cutoff + ", order: " + order +")";
	}
	
	public RunAlgRequest (String dataDocumentId, String toolDocumentId, String algName, String studyId, double cutoff, double order) {
		this.dataDocumentId = dataDocumentId;
		this.toolDocumentId = toolDocumentId;
		this.algName = algName;
		this.studyId = studyId;
		this.cutoff = cutoff;
		this.order = order;

	}
	
	public RunAlgRequest() {
	}
	
}
