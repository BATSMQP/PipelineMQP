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
import com.amazonaws.lambda.demo.http.GetStudyDataRequest;
import com.amazonaws.lambda.demo.http.GetStudyDataResponse;
//import com.amazonaws.lambda.demo.http.NewStudyRequest;
//import com.amazonaws.lambda.demo.http.NewStudyResponse;
//import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
//import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class GetStudyDataHandler implements RequestHandler<GetStudyDataRequest, GetStudyDataResponse> {

	LambdaLogger logger;

	@Override
	public GetStudyDataResponse handleRequest(GetStudyDataRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		ArrayList<Document> data = new ArrayList<Document>();
		

		try {
			data = dao.getDataForStudy(req.getStudyId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the data for the studyId";
		}
		
		GetStudyDataResponse response;
		if (fail) {
			response = new GetStudyDataResponse(failMessage, 400, data);
		} else {
			response = new GetStudyDataResponse("none", 200, data);
		}

		return response;
	}

}

