package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.sql.Timestamp;
import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.GetDataDocRequest;
import com.amazonaws.lambda.demo.http.GetDataDocResponse;
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



public class GetDataDocHandler implements RequestHandler<GetDataDocRequest, GetDataDocResponse> {

	LambdaLogger logger;

	@Override
	public GetDataDocResponse handleRequest(GetDataDocRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		Document doc = null;
		

		try {
			doc = dao.getDataDocument(req.getDocumentId(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the data for the documentId";
		}
		
		GetDataDocResponse response;
		if (fail) {
			response = new GetDataDocResponse(failMessage, 400, doc);
		} else {
			response = new GetDataDocResponse("none", 200, doc);
		}

		return response;
	}

}

