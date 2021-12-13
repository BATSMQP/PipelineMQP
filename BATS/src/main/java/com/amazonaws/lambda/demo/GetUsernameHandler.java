package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.GetStudiesRequest;
import com.amazonaws.lambda.demo.http.GetStudiesResponse;
import com.amazonaws.lambda.demo.http.GetUsernameRequest;
import com.amazonaws.lambda.demo.http.GetUsernameResponse;
import com.amazonaws.lambda.demo.http.NewStudyRequest;
import com.amazonaws.lambda.demo.http.NewStudyResponse;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

public class GetUsernameHandler implements RequestHandler<GetUsernameRequest, GetUsernameResponse> {

	LambdaLogger logger;

	@Override
	public GetUsernameResponse handleRequest(GetUsernameRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		String username = "";
		

		try {
			username = dao.getUsername(req.getAuthUserId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error: Failed to get the username for the auth user";
		}
		
		GetUsernameResponse response;
		if (fail) {
			response = new GetUsernameResponse(failMessage, 400, username);
		} else {
			response = new GetUsernameResponse("none", 200, username);
		}

		return response;
	}

}

