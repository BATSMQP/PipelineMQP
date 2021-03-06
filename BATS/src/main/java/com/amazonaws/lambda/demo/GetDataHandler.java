package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.GetDataRequest;
import com.amazonaws.lambda.demo.http.GetDataResponse;
import com.amazonaws.lambda.demo.http.GetStudiesRequest;
import com.amazonaws.lambda.demo.http.GetStudiesResponse;
import com.amazonaws.lambda.demo.http.NewStudyRequest;
import com.amazonaws.lambda.demo.http.NewStudyResponse;
import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class GetDataHandler implements RequestHandler<GetDataRequest, GetDataResponse> {

	LambdaLogger logger;

	@Override
	public GetDataResponse handleRequest(GetDataRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		ArrayList<Document> data = new ArrayList<Document>();
		ArrayList<Study> studies = new ArrayList<Study>();
		

		try {
			data = dao.getDataForAuthUser(req.getAuthUserId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the data for the authUserId";
		}
		
		try {
			studies = dao.getStudiesForDocuments(data, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the studies for the data documents";
		}
		
		GetDataResponse response;
		if (fail) {
			response = new GetDataResponse(failMessage, 400, data, studies);
		} else {
			response = new GetDataResponse("none", 200, data, studies);
		}

		return response;
	}

}

