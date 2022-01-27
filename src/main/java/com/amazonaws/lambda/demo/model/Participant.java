package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Participant{
	public final String participantId;
	public int participantNumber;
	public String studyGroupId;
	
	public String getParticipantId() {return participantId;}
	public int getParticipantNumber() {return participantNumber;}
	public String getStudyGroupId() {return studyGroupId;}
	
	public Participant(String participantId, int participantNumber, String studyGroupId) {
		this.participantId = participantId;
		this.participantNumber = participantNumber;
		this.studyGroupId = studyGroupId;
	}
	
	public Participant() {
		this.participantId = null;
		this.participantNumber = -1;
		this.studyGroupId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Participant) {
			Participant other = (Participant) o;
			return participantId.equals(other.participantId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Participant " + participantId + " is participant number: " + participantNumber + ".";
	}
	
}