package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Speech{
	public final String speechId;
	public Timestamp time;
	public Timestamp timeIncr;
	public String SR;
	public String waveforms;
	public String participantId;
	
	public String getSpeechId() {return speechId;}
	public Timestamp getTime() {return time;}
	public Timestamp getTimeIncr() {return timeIncr;}
	public String getSR() {return SR;}
	public String getWaveforms() {return waveforms;}
	public String getParticipantId() {return participantId;}
	
	public Speech(String speechId, Timestamp time, Timestamp timeIncr, String SR, String waveforms, String participantId) {
		this.speechId = speechId;
		this.time = time;
		this.timeIncr = timeIncr;
		this.SR = SR;
		this.waveforms = waveforms;
		this.participantId = participantId;
	}
	
	public Speech() {
		this.speechId = null;
		this.time = null;
		this.timeIncr = null;
		this.SR = null;
		this.waveforms = null;
		this.participantId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Speech) {
			Speech other = (Speech) o;
			return speechId.equals(other.speechId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Speech " + speechId + ".";
	}
	
}