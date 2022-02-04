package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
//import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.RunAlgRequest;
import com.amazonaws.lambda.demo.http.RunAlgResponse;
import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.Study;
//import com.amazonaws.lambda.demo.model.AuthUser;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class RunAlgHandler implements RequestHandler<RunAlgRequest, RunAlgResponse> {

	LambdaLogger logger;

	@Override
	public RunAlgResponse handleRequest(RunAlgRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		Document data = null;
		String algorithm = req.getAlgName();
		

		try {
			data = dao.getDocument(req.getDataDocumentID(), logger);
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Failed to get the Document for the documentId";
		}
		
		//modify lastMod for the study
//		try {
//			dao.updateLastMod(req.getStudyId(), logger);
//		}
//		catch(Exception e) {
//			fail = true;
//			failMessage = "Error updating lastMod for the given studyId";
//		}
		
//		byte[] fileContent = null;
//		String encodedString = null;
//		try {
//			fileContent = FileUtils.readFileToByteArray(new File("./img/example_graph.png"));
//			logger.log("fileContent in RunAlgHandler:");
//			logger.log(fileContent.toString());
//			encodedString = Base64.getEncoder().encodeToString(fileContent);
//			logger.log("encodedString in RunAlgHandler:");
//			logger.log(encodedString);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		RunAlgResponse response;
		if (fail) {
			response = new RunAlgResponse(failMessage, 400, "filex", "image");
		} else {
			response = new RunAlgResponse("none", 200, "filex", "image");
		}

		return response;
	}

}
