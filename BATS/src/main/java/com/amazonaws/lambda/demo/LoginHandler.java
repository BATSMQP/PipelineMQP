package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.UUID;

import com.amazonaws.lambda.demo.db.BatsDAO;
import com.amazonaws.lambda.demo.http.LoginRequest;
import com.amazonaws.lambda.demo.http.LoginResponse;
import com.amazonaws.lambda.demo.model.AuthUser;

public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {

	LambdaLogger logger;
	
	private AuthUser createAuthUser(String firstName, String lastName, String username, String pass, String email){
		if (logger != null) logger.log("in createAuthUser");
		String authUserId = UUID.randomUUID().toString();
		
		AuthUser authUser = new AuthUser(authUserId, firstName, lastName, username, pass, email);
		
		return authUser;
	}

	@Override
	public LoginResponse handleRequest(LoginRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		BatsDAO dao = new BatsDAO();
		boolean fail = false;
		String failMessage = "";
		
		AuthUser authUser = null;
		String username = "";
		
		//See if there is a user with that username
			//if so, if password is wrong error - wrong password
			//if so, if password is correct - login
		
			//if not, error - no user with that username
		

		try {
			authUser = dao.getAuthUser(req.getUsername(), logger);
			username = authUser.username;
		}
		catch(Exception e) {
			fail = true;
			failMessage = "Error: No AutUser in the system with the given username";
		}
		
		if(authUser != null) {
			if(!authUser.pass.equals(req.getPassword())) {
				fail = true;
				failMessage = "Error: Password is incorrect";
			}
		}
		
//		if(!fail) {
//			username = authUser.username;
//		}
		
		LoginResponse response;
		if (fail) {
			response = new LoginResponse(failMessage, 400, username);
		} else {
			response = new LoginResponse("none", 200, username);
		}

		return response;
	}

}
