package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class LogDataStudy{
	public String studyId;
	public String logDataId;
	
	public String getStudyId() {return studyId;}
	public String getLogDataId() {return logDataId;}
	
	public LogDataStudy(String logDataId, String studyId) {
		this.studyId = studyId;
		this.logDataId = logDataId;
	}
	
	public LogDataStudy() {
		this.studyId = null;
		this.logDataId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof LogDataStudy) {
			LogDataStudy other = (LogDataStudy) o;
			return (studyId.equals(other.studyId) && logDataId.equals(other.logDataId));
		}
		
		return false;
	}
	
	public String toString() {
		return "LogDataStudy has logDataId:" + logDataId + " and studyId: " + studyId + ".";
	}
	
}