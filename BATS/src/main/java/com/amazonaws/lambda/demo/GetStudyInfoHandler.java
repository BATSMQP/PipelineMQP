package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.GetStudyInfoRequest;
import com.amazonaws.lambda.demo.http.GetStudyInfoResponse;
import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class GetStudyInfoHandler implements RequestHandler<GetStudyInfoRequest, GetStudyInfoResponse> {

	LambdaLogger logger;

	@Override
	public GetStudyInfoResponse handleRequest(GetStudyInfoRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		Study study = new Study();
		

		try {
			study = dao.getStudy(req.getStudyId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the study for the studyId";
		}
		
		GetStudyInfoResponse response;
		if (fail) {
			response = new GetStudyInfoResponse(failMessage, 400, study);
		} else {
			response = new GetStudyInfoResponse("none", 200, study);
		}

		return response;
	}

}

