package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Neural{
	public final String neuralId;
	public Timestamp time;
	public Timestamp timeIncr;
	public String SR;
	public String logMarker;
	public String logQuestion;
	public String logAnswer;
	public String participantId;
	
	public String getNeuralId() {return neuralId;}
	public Timestamp getTime() {return time;}
	public Timestamp getTimeIncr() {return timeIncr;}
	public String getSR() {return SR;}
	public String getLogMarker() {return logMarker;}
	public String getLogQuestion() {return logQuestion;}
	public String getLogAnswer() {return logAnswer;}
	public String getParticipantId() {return participantId;}
	
	public Neural(String neuralId, Timestamp time, Timestamp timeIncr, String SR, String logMarker, String logQuestion, String logAnswer, String participantId) {
		this.neuralId = neuralId;
		this.time = time;
		this.timeIncr = timeIncr;
		this.SR = SR;
		this.logMarker = logMarker;
		this.logQuestion = logQuestion;
		this.logAnswer = logAnswer;
		this.participantId = participantId;
	}
	
	public Neural() {
		this.neuralId = null;
		this.time = null;
		this.timeIncr = null;
		this.SR = null;
		this.logMarker = null;
		this.logQuestion = null;
		this.logAnswer = null;
		this.participantId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Neural) {
			Neural other = (Neural) o;
			return neuralId.equals(other.neuralId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Neural " + neuralId + ".";
	}
	
}