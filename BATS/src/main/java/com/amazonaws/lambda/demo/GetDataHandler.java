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
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

public class GetDataHandler implements RequestHandler<GetDataRequest, GetDataResponse> {

	LambdaLogger logger;

	@Override
	public GetDataResponse handleRequest(GetDataRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		Data data = new Data();
		

		try {
			data = dao.getDataForStudy(req.getStudyId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the data for the studyId";
		}
		
		GetDataResponse response;
		if (fail) {
			response = new GetDataResponse(failMessage, 400, data);
		} else {
			response = new GetDataResponse("none", 200, data);
		}

		return response;
	}

}

