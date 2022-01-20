package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.NewDataRequest;
import com.amazonaws.lambda.demo.http.NewDataResponse;
import com.amazonaws.lambda.demo.model.Document;


 //this is where we create a choice

public class NewDataHandler implements RequestHandler<NewDataRequest, NewDataResponse> {

	LambdaLogger logger;

	private Document createDocument(String file, String name, String dataType, String ext){
		if (logger != null) logger.log("in createDocument");
		String documentId = UUID.randomUUID().toString();
		
		Document document = new Document(documentId, file, name, dataType, ext);
		
		return document;
	}

	@Override
	public NewDataResponse handleRequest(NewDataRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";

		Document document = createDocument(req.getFile(), req.getName(), req.getDataType(), req.getExt());
		try {
			dao.addDocument(document, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error adding data document to the database";
		}
		
		NewDataResponse response;
		if (fail) {
			response = new NewDataResponse(failMessage, 400, document);
		} else {
			response = new NewDataResponse("none", 200, document);
		}

		return response;
	}

}
