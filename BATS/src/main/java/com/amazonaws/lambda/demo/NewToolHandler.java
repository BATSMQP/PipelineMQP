package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.NewToolRequest;
import com.amazonaws.lambda.demo.http.NewToolResponse;
import com.amazonaws.lambda.demo.model.Document;


 //this is where we create a choice

public class NewToolHandler implements RequestHandler<NewToolRequest, NewToolResponse> {

	LambdaLogger logger;

	private Document createDocument(String file, String filename, String name, String dataType, String ext, String docType){
		if (logger != null) logger.log("in createDocument");
		String documentId = UUID.randomUUID().toString();
		
		Document document = new Document(documentId, file, filename, name, dataType, ext, docType);
		
		return document;
	}

	@Override
	public NewToolResponse handleRequest(NewToolRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";

		Document document = createDocument(req.getFile(), req.getFilename(), req.getName(), req.getDataType(), req.getExt(), "Tool");
		try {
			dao.addDocument(document, logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error adding tool document to the database";
		}
		
		NewToolResponse response;
		if (fail) {
			response = new NewToolResponse(failMessage, 400, document);
		} else {
			response = new NewToolResponse("none", 200, document);
		}

		return response;
	}

}
