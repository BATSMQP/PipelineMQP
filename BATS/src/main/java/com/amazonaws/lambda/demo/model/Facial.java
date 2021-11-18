package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Facial{
	public final String facialId;
	public Timestamp time;
	public Timestamp timeIncr;
	public String SR;
	public String video;
	public double facialPositionQuantification;
	public String participantId;
	
	public String getFacialId() {return facialId;}
	public Timestamp getTime() {return time;}
	public Timestamp getTimeIncr() {return timeIncr;}
	public String getSR() {return SR;}
	public String getVideo() {return video;}
	public double getFacialPositionQuantification() {return facialPositionQuantification;}
	public String getParticipantId() {return participantId;}
	
	public Facial(String facialId, Timestamp time, Timestamp timeIncr, String SR, String video, double facialPositionQuantification, String participantId) {
		this.facialId = facialId;
		this.time = time;
		this.timeIncr = timeIncr;
		this.SR = SR;
		this.video = video;
		this.facialPositionQuantification = facialPositionQuantification;
		this.participantId = participantId;
	}
	
	public Facial() {
		this.facialId = null;
		this.time = null;
		this.timeIncr = null;
		this.SR = null;
		this.video = null;
		this.facialPositionQuantification = -1;
		this.participantId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Facial) {
			Facial other = (Facial) o;
			return facialId.equals(other.facialId);
		}
		
		return false;
	}
	
	public String toString() {
		return "Facial " + facialId + ".";
	}
	
}