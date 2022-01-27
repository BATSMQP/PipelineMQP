package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class StressIndicatorsStudy{
	public String studyId;
	public String siId;
	
	public String getStudyId() {return studyId;}
	public String getSiId() {return siId;}
	
	public StressIndicatorsStudy(String siId, String studyId) {
		this.studyId = studyId;
		this.siId = siId;
	}
	
	public StressIndicatorsStudy() {
		this.studyId = null;
		this.siId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof StressIndicatorsStudy) {
			StressIndicatorsStudy other = (StressIndicatorsStudy) o;
			return (studyId.equals(other.studyId) && siId.equals(other.siId));
		}
		
		return false;
	}
	
	public String toString() {
		return "StressIndicatorsStudy has siId:" + siId + " and studyId: " + studyId + ".";
	}
	
}