package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class FacialStudy{
	public String studyId;
	public String facialId;
	
	public String getStudyId() {return studyId;}
	public String getFacialId() {return facialId;}
	
	public FacialStudy(String facialId, String studyId) {
		this.studyId = studyId;
		this.facialId = facialId;
	}
	
	public FacialStudy() {
		this.studyId = null;
		this.facialId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof FacialStudy) {
			FacialStudy other = (FacialStudy) o;
			return (studyId.equals(other.studyId) && facialId.equals(other.facialId));
		}
		
		return false;
	}
	
	public String toString() {
		return "FacialStudy has facialId:" + facialId + " and studyId: " + studyId + ".";
	}
	
}