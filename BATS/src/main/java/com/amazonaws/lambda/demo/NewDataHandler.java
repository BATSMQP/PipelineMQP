package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.NewDataRequest;
import com.amazonaws.lambda.demo.http.NewDataResponse;
import com.amazonaws.lambda.demo.model.AuthUserDocument;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.StudyDocument;


 //this is where we create a choice

public class NewDataHandler implements RequestHandler<NewDataRequest, NewDataResponse> {

	LambdaLogger logger;

	private Document createDocument(String file, String filename, String name, String dataType, String ext, String docType){
		if (logger != null) logger.log("in createDocument");
		String documentId = UUID.randomUUID().toString();
		
		Document document = new Document(documentId, file, filename, name, dataType, ext, docType);
		
		return document;
	}
	
	private StudyDocument createStudyDocument(String studyId, String documentId){
		StudyDocument studyDocument = new StudyDocument(studyId, documentId);
		return studyDocument;
	}
	
	private AuthUserDocument createAuthUserDocument(String authUserId, String documentId){
		AuthUserDocument authUserDocument = new AuthUserDocument(authUserId, documentId);
		return authUserDocument;
	}

	@Override
	public NewDataResponse handleRequest(NewDataRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";

		Document document = createDocument(req.getFile(), req.getFilename(), req.getName(), req.getDataType(), req.getExt(), "Data");
		try {
			dao.addDocument(document, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error adding data document to the database";
		}
		
		//add document to StudyDocument
		StudyDocument studyDocument = createStudyDocument(req.getStudyId(), document.getDocumentId());
		try {
			dao.addStudyDocument(studyDocument, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error adding StudyDocument to the database";
		}
		
		//add document to AuthUserDocument
		AuthUserDocument authUserDocument = createAuthUserDocument(req.getAuthUserId(), document.getDocumentId());
		try {
			dao.addAuthUserDocument(authUserDocument, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error adding AuthUserDocument to the database";
		}
		
		NewDataResponse response;
		if (fail) {
			response = new NewDataResponse(failMessage, 400, document);
		} else {
			response = new NewDataResponse("none", 200, document);
		}

		return response;
	}

}
