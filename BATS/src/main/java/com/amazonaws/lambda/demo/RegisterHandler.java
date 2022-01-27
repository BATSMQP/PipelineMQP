package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.LoginRequest;
import com.amazonaws.lambda.demo.http.LoginResponse;
import com.amazonaws.lambda.demo.http.RegisterRequest;
import com.amazonaws.lambda.demo.http.RegisterResponse;
import com.amazonaws.lambda.demo.model.AuthUser;

public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {

	LambdaLogger logger;
	
	private AuthUser createAuthUser(String firstName, String lastName, String username, String pass, String email){
		if (logger != null) logger.log("in createAuthUser");
		String authUserId = UUID.randomUUID().toString();
		
		AuthUser authUser = new AuthUser(authUserId, firstName, lastName, username, pass, email);
		
		return authUser;
	}

	@Override
	public RegisterResponse handleRequest(RegisterRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		String username = "";
		String authUserId = "";
		
		//See if there is a user with that username
			//if so, error there is an AuthUser with that usernam
			//if not, create AuthUser

		AuthUser authUser = createAuthUser("", "", req.getUsername(), req.getPassword(), req.getEmail());

		try {
			dao.addAuthUser(authUser, logger);
			username = authUser.getUsername();
			authUserId =  authUser.authUserId;
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error: AutUser with the given username already exists";
		}
		
		RegisterResponse response;
		if (fail) {
			response = new RegisterResponse(failMessage, 400, username, authUserId);
		} else {
			response = new RegisterResponse("none", 200, username, authUserId);
		}

		return response;
	}

}
