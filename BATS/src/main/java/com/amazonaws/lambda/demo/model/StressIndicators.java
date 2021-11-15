package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class StressIndicators{
	public final String siId;
	public Timestamp time;
	public Timestamp timeIncr;
	public String SR;
	public String heartbeatWaveform;
	public double bloodPressure;
	public String participantId;
	
	public String getSiId() {return siId;}
	public Timestamp getTime() {return time;}
	public Timestamp getTimeIncr() {return timeIncr;}
	public String getSR() {return SR;}
	public String getHeartbeatWaveform() {return heartbeatWaveform;}
	public double getBloodPressure() {return bloodPressure;}
	public String getParticipantId() {return participantId;}
	
	public StressIndicators(String siId, Timestamp time, Timestamp timeIncr, String SR, String heartbeatWaveform, double bloodPressure, String participantId) {
		this.siId = siId;
		this.time = time;
		this.timeIncr = timeIncr;
		this.SR = SR;
		this.heartbeatWaveform = heartbeatWaveform;
		this.bloodPressure = bloodPressure;
		this.participantId = participantId;
	}
	
	public StressIndicators() {
		this.siId = null;
		this.time = null;
		this.timeIncr = null;
		this.SR = null;
		this.heartbeatWaveform = null;
		this.bloodPressure = -1;
		this.participantId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof StressIndicators) {
			StressIndicators other = (StressIndicators) o;
			return siId.equals(other.siId);
		}
		
		return false;
	}
	
	public String toString() {
		return "StressIndicators " + siId + ".";
	}
	
}