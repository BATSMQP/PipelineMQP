package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
//import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.NewStudyRequest;
import com.amazonaws.lambda.demo.http.NewStudyResponse;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

public class CreateStudyHandler implements RequestHandler<NewStudyRequest, NewStudyResponse> {

	LambdaLogger logger;

	private Study createStudy(String studyName, String studyShortName, String studyDescription, String authUserId){
		if (logger != null) logger.log("in createStudy");
		String studyId = UUID.randomUUID().toString();
		
		long millis=System.currentTimeMillis();  
		Timestamp currentDate =new Timestamp(millis); 
//		String currentDate = date.toString();
		
//		String ii = "";
//		String sn = "";
//		if(institutionsInvolved != null) { 
//			ii = institutionsInvolved;
//		}
//		if(studyNotes != null) { 
//			sn = studyNotes;
//		}
		
		Study study = new Study(studyId, studyName, studyShortName, studyDescription, currentDate, authUserId);
		
		return study;
	}

	@Override
	public NewStudyResponse handleRequest(NewStudyRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";

		Study study = createStudy(req.getName(), req.getShortName(), req.getStudyAstract(), req.getAuthUserId());
		try {
			dao.addStudy(study, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Study has already been created";
		}
		
		NewStudyResponse response;
		if (fail) {
			response = new NewStudyResponse(failMessage, 400, study);
		} else {
			response = new NewStudyResponse("none", 200, study);
		}

		return response;
	}

}

