package com.amazonaws.lambda.demo.model;

import java.util.ArrayList;

public class Data{
	public ArrayList<Neural> neural = new ArrayList<Neural>();
	public ArrayList<Facial> facial = new ArrayList<Facial>();
	public ArrayList<Speech> speech = new ArrayList<Speech>();
	public ArrayList<StressIndicators> stressIndicators = new ArrayList<StressIndicators>();
	public ArrayList<LogData> logData = new ArrayList<LogData>();
	
	public ArrayList<Neural> getNeural() {return neural;}
	public ArrayList<Facial> getFacial() {return facial;}
	public ArrayList<Speech> getSpeech() {return speech;}
	public ArrayList<StressIndicators> getStressIndicators() {return stressIndicators;}
	public ArrayList<LogData> getLogData() {return logData;}
	
	public Data(ArrayList<Neural> neural, ArrayList<Facial> facial, ArrayList<Speech> speech, ArrayList<StressIndicators> stressIndicators, ArrayList<LogData> logData) {
		this.neural = neural;
		this.facial = facial;
		this.speech = speech;
		this.stressIndicators = stressIndicators;
		this.logData = logData;
	}
	
	public Data() {
		this.neural = null;
		this.facial = null;
		this.speech = null;
		this.stressIndicators = null;
		this.logData = null;
	}
	
//	public boolean equals(Object o) {
//		if (o == null) { return false; }
//		
//		if (o instanceof LogData) {
//			LogData other = (LogData) o;
//			return logDataId.equals(other.logDataId);
//		}
//		
//		return false;
//	}
	
	public String toString() {
		return "Data " + neural + "\n" + facial + "\n" + speech + "\n" + stressIndicators + "\n" + logData + ".";
	}
	
}