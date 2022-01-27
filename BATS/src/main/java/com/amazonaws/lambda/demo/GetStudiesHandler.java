package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.GetStudiesRequest;
import com.amazonaws.lambda.demo.http.GetStudiesResponse;
import com.amazonaws.lambda.demo.http.NewStudyRequest;
import com.amazonaws.lambda.demo.http.NewStudyResponse;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

public class GetStudiesHandler implements RequestHandler<GetStudiesRequest, GetStudiesResponse> {

	LambdaLogger logger;

	@Override
	public GetStudiesResponse handleRequest(GetStudiesRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		ArrayList<Study> studies = new ArrayList<Study>();
		

		try {
			studies = dao.getAllStudies(req.getAuthUserId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error: Failed to get the studies for the auth user";
		}
		
		GetStudiesResponse response;
		if (fail) {
			response = new GetStudiesResponse(failMessage, 400, studies);
		} else {
			response = new GetStudiesResponse("none", 200, studies);
		}

		return response;
	}

}

