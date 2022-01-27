package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class LogData{
	public final String logDataId;
	public double changeOfSeverityOverTime;
	public String question;
	public String answer;
	public Timestamp startOfSymptoms;
	public String participantId;
	
	public String getLogDataId() {return logDataId;}
	public double getChangeOfSeverityOverTime() {return changeOfSeverityOverTime;}
	public String getQuestion() {return question;}
	public String getAnswer() {return answer;}
	public Timestamp getStartOfSymptoms() {return startOfSymptoms;}
	public String getParticipantId() {return participantId;}
	
	public LogData(String logDataId, double changeOfSeverityOverTime, String question, String answer, Timestamp startOfSymptoms, String participantId) {
		this.logDataId = logDataId;
		this.changeOfSeverityOverTime = changeOfSeverityOverTime;
		this.question = question;
		this.answer = answer;
		this.startOfSymptoms = startOfSymptoms;
		this.participantId = participantId;
	}
	
	public LogData() {
		this.logDataId = null;
		this.changeOfSeverityOverTime = -1;
		this.question = null;
		this.answer = null;
		this.startOfSymptoms = null;
		this.participantId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof LogData) {
			LogData other = (LogData) o;
			return logDataId.equals(other.logDataId);
		}
		
		return false;
	}
	
	public String toString() {
		return "LogData " + logDataId + ".";
	}
	
}