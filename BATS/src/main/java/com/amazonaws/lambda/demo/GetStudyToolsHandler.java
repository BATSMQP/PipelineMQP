package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

//import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
//import com.amazonaws.lambda.demo.http.GetDataRequest;
//import com.amazonaws.lambda.demo.http.GetDataResponse;
//import com.amazonaws.lambda.demo.http.GetStudiesRequest;
//import com.amazonaws.lambda.demo.http.GetStudiesResponse;
import com.amazonaws.lambda.demo.http.GetStudyToolsRequest;
import com.amazonaws.lambda.demo.http.GetStudyToolsResponse;
//import com.amazonaws.lambda.demo.http.NewStudyRequest;
//import com.amazonaws.lambda.demo.http.NewStudyResponse;
//import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
//import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class GetStudyToolsHandler implements RequestHandler<GetStudyToolsRequest, GetStudyToolsResponse> {

	LambdaLogger logger;

	@Override
	public GetStudyToolsResponse handleRequest(GetStudyToolsRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		ArrayList<Document> tools = new ArrayList<Document>();
		

		try {
			tools = dao.getToolsForStudy(req.getStudyId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the tools for the studyId";
		}
		
		GetStudyToolsResponse response;
		if (fail) {
			response = new GetStudyToolsResponse(failMessage, 400, tools);
		} else {
			response = new GetStudyToolsResponse("none", 200, tools);
		}

		return response;
	}

}

