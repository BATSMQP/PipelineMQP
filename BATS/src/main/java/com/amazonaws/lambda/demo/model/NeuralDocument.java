package com.amazonaws.lambda.demo.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class NeuralDocument{
	public String documentId;
	public String neuralId;
	
	public String getDocumentId() {return documentId;}
	public String getNeuralId() {return neuralId;}
	
	public NeuralDocument(String neuralId, String documentId) {
		this.documentId = documentId;
		this.neuralId = neuralId;
	}
	
	public NeuralDocument() {
		this.documentId = null;
		this.neuralId = null;
	}
	
	public boolean equals(Object o) {
		if (o == null) { return false; }
		
		if (o instanceof NeuralDocument) {
			NeuralDocument other = (NeuralDocument) o;
			return (documentId.equals(other.documentId) && neuralId.equals(other.neuralId));
		}
		
		return false;
	}
	
	public String toString() {
		return "NeuralDocument has neuralId:" + neuralId + " and documentId: " + documentId + ".";
	}
	
}