package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class NeuralStudy{
	public String studyId;
	public String neuralId;
	
	public String getStudyId() {return studyId;}
	public String getNeuralId() {return neuralId;}
	
	public NeuralStudy(String neuralId, String studyId) {
		this.studyId = studyId;
		this.neuralId = neuralId;
	}
	
	public NeuralStudy() {
		this.studyId = null;
		this.neuralId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof NeuralStudy) {
			NeuralStudy other = (NeuralStudy) o;
			return (studyId.equals(other.studyId) && neuralId.equals(other.neuralId));
		}
		
		return false;
	}
	
	public String toString() {
		return "NeuralStudy has neuralId:" + neuralId + " and studyId: " + studyId + ".";
	}
	
}