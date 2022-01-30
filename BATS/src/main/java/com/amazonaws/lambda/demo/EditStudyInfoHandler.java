package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.EditStudyInfoRequest;
import com.amazonaws.lambda.demo.http.EditStudyInfoResponse;
import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class EditStudyInfoHandler implements RequestHandler<EditStudyInfoRequest, EditStudyInfoResponse> {

	LambdaLogger logger;
	
	public Study generateStudy(String studyId, String institutionsInvolved, String studyDescription, String studyName, String studyShortName, String studyContact, String studyNotes, String visibility, String isIrbApproved) {
		long millis=System.currentTimeMillis();  
		Timestamp lastMod =new Timestamp(millis);
		Study study = new Study(studyId, institutionsInvolved, studyDescription, studyName, studyShortName,studyContact, studyNotes, visibility, isIrbApproved, lastMod);
		return study;
	}

	@Override
	public EditStudyInfoResponse handleRequest(EditStudyInfoRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		Study study = generateStudy(req.getStudyId(), req.getInstitutionsInvolved(), req.getStudyDescription(), req.getStudyName(), req.getStudyShortName(), req.getStudyContact(), req.getStudyNotes(), req.getVisibility(), req.getisIrbApproved());

		try {
			dao.EditStudy(study, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to edit the study for the given study";
		}
		
		EditStudyInfoResponse response;
		if (fail) {
			response = new EditStudyInfoResponse(failMessage, 400);
		} else {
			response = new EditStudyInfoResponse("none", 200);
		}

		return response;
	}

}

