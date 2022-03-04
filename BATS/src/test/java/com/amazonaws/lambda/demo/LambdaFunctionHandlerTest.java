package com.amazonaws.lambda.demo;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.lambda.demo.http.EditStudyInfoRequest;
import com.amazonaws.lambda.demo.http.EditStudyInfoResponse;
import com.amazonaws.lambda.demo.http.GetDataDocRequest;
import com.amazonaws.lambda.demo.http.GetDataDocResponse;
import com.amazonaws.lambda.demo.http.GetDataRequest;
import com.amazonaws.lambda.demo.http.GetDataResponse;
import com.amazonaws.lambda.demo.http.GetStudiesRequest;
import com.amazonaws.lambda.demo.http.GetStudiesResponse;
import com.amazonaws.lambda.demo.http.GetStudyDataRequest;
import com.amazonaws.lambda.demo.http.GetStudyDataResponse;
import com.amazonaws.lambda.demo.http.GetStudyInfoRequest;
import com.amazonaws.lambda.demo.http.GetStudyInfoResponse;
import com.amazonaws.lambda.demo.http.GetStudyToolsRequest;
import com.amazonaws.lambda.demo.http.GetStudyToolsResponse;
import com.amazonaws.lambda.demo.http.GetUsernameRequest;
import com.amazonaws.lambda.demo.http.GetUsernameResponse;
import com.amazonaws.lambda.demo.http.LoginRequest;
import com.amazonaws.lambda.demo.http.LoginResponse;
import com.amazonaws.lambda.demo.http.NewDataRequest;
import com.amazonaws.lambda.demo.http.NewDataResponse;
import com.amazonaws.lambda.demo.http.NewStudyRequest;
import com.amazonaws.lambda.demo.http.NewStudyResponse;
import com.amazonaws.lambda.demo.http.NewToolRequest;
import com.amazonaws.lambda.demo.http.NewToolResponse;
import com.amazonaws.lambda.demo.http.RegisterRequest;
import com.amazonaws.lambda.demo.http.RegisterResponse;
import com.amazonaws.lambda.demo.http.RunAlgRequest;
import com.amazonaws.lambda.demo.http.RunAlgResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdaFunctionHandlerTest {
	
	Context context = createContext();
	LambdaLogger logger = context.getLogger();
	boolean fail = false;
	String failMessage = "";

    private final String CONTENT_TYPE = "image/jpeg";
    private S3Event event;
    
//    Choice getUpdatedChoice(String cid) {
//		ChoiceDAO dao = new ChoiceDAO();
//		Choice choice = null;
//		try {
//			choice = dao.getChoice(cid, logger);
//		} catch(Exception e){
//			fail = true;
//			failMessage = "Error getting choice.";
//		}
//		try {
//			choice.alternatives = dao.getAltsForChoice(choice.cid);
//		} catch(Exception e){
//			fail = true;
//			failMessage = "Error getting alternatives for choice";
//		}
//
//		try {
//			choice.members = dao.getChoiceMembers(choice.cid);
//		} catch (Exception e) {
//			fail = true;
//			failMessage = "Error getting the choice members.";
//		}
//		
//		for(int i = 0; i < choice.alternatives.size(); i++) {
//			try {
//				choice.alternatives.get(i).votes = dao.getVotesForAlt(choice.alternatives.get(i).aid);
//				System.out.println("choice in getUpdatedChoice right after votes size: "+ choice.alternatives.get(i).votes.size());
//			} catch (Exception e) {
//				fail = true;
//				failMessage = "Error getting the votes for an alternative.";
//			}
//			try {
//				choice.alternatives.get(i).feedback = dao.getAllFeedbackForAlt(choice.alternatives.get(i).aid);
//			} catch (Exception e) {
//				fail = true;
//				failMessage = "Error getting the feedback for an alternative.";
//			}
//		}
//		System.out.println("choice in getUpdatedChoice votes: "+ choice.votesToString(cid));
//		System.out.println("choice in getUpdatedChoice @ end votes size: "+ choice.alternatives.get(1).votes.size());
//		return choice;
//	}

    @Mock
    private AmazonS3 s3Client;
    @Mock
    private S3Object s3Object;

    @Captor
    private ArgumentCaptor<GetObjectRequest> getObjectRequest;

    @Before
    public void setUp() throws IOException {
        event = TestUtils.parse("/s3-event.put.json", S3Event.class);

        // TODO: customize your mock logic for s3 client
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(CONTENT_TYPE);
//        when(s3Object.getObjectMetadata()).thenReturn(objectMetadata);
//        when(s3Client.getObject(getObjectRequest.capture())).thenReturn(s3Object);
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }
    
//    @Test
//    public void testCreateStudyHandler() {
//    	CreateStudyHandler csh = new CreateStudyHandler();
//    	NewStudyRequest r = new NewStudyRequest("39cd1de6-6bb4-43ae-9348-c83e40f4d57d", "Eclipse", "Ecps", "Description", "institutionsInvolved", "studyContact", "studyNotes", "isIrbApproved", "visibility");
//    	NewStudyResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.study: " + response.study);
//    }
//    
//    @Test
//    public void testEditStudyInfoHandler() {
//    	EditStudyInfoHandler csh = new EditStudyInfoHandler();
//    	EditStudyInfoRequest r = new EditStudyInfoRequest("39cd1de6-6bb4-43ae-9348-c83e40f4d57d", "testStudyInEclipse", "ts", "abstract", "institutionsInvolved", "studyContact", "studyNotes", "yes", "no");
//    	EditStudyInfoResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    }
//    
//    @Test
//    public void testGetStudiesHandler() {
//    	GetStudiesHandler csh = new GetStudiesHandler();
//    	GetStudiesRequest r = new GetStudiesRequest("91c9465f-9a0d-4c6f-9f19-b5a813d2ce34", "test");
//    	GetStudiesResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.studies: " + response.studies);
//    }
//    
//    @Test
//    public void testGetDataHandler() {
//    	GetDataHandler csh = new GetDataHandler();
//    	GetDataRequest r = new GetDataRequest("testAuthUser");
//    	GetDataResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.data: " + response.data);
//    }
//    
//    @Test
//    public void testGetStudyInfoHandler() {
//    	GetStudyInfoHandler csh = new GetStudyInfoHandler();
//    	GetStudyInfoRequest r = new GetStudyInfoRequest("a9c30695-28ce-4481-81e5-b240af28e9fd");
//    	GetStudyInfoResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.study: " + response.study);
//    }
//    
//    @Test
//    public void testGetStudyDataHandler() {
//    	GetStudyDataHandler csh = new GetStudyDataHandler();
//    	GetStudyDataRequest r = new GetStudyDataRequest("ff9aeab7-b842-48ce-8e21-d7516292662c");
//    	GetStudyDataResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.data: " + response.data);
//    }
//    
//    @Test
//    public void testGetStudyToolsHandler() {
//    	GetStudyToolsHandler csh = new GetStudyToolsHandler();
//    	GetStudyToolsRequest r = new GetStudyToolsRequest("ff9aeab7-b842-48ce-8e21-d7516292662c");
//    	GetStudyToolsResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.tools: " + response.tools);
//    }
//    
//    @Test
//    public void testLoginHandler1() {
//    	LoginHandler csh = new LoginHandler();
//    	LoginRequest r = new LoginRequest("exampleUser", "password");
//    	LoginResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.username: " + response.username);
//    	System.out.println("response.authUserId: " + response.authUserId);
//    }
//    
//    @Test
//    public void testLoginHandler2() {
//    	LoginHandler csh = new LoginHandler();
//    	LoginRequest r = new LoginRequest("exampleUser", "wrong");
//    	LoginResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.username: " + response.username);
//    	System.out.println("response.authUserId: " + response.authUserId);
//    }
//    
//    @Test
//    public void testLoginHandler3() {
//    	LoginHandler csh = new LoginHandler();
//    	LoginRequest r = new LoginRequest("wrong", "password");
//    	LoginResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.username: " + response.username);
//    }
//    
//    @Test
//    public void testRegisterHandler1() {
//    	RegisterHandler csh = new RegisterHandler();
//    	RegisterRequest r = new RegisterRequest("test2", "pass", "email@gmail.com");
//    	RegisterResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.username: " + response.username);
//    }
//    
//    @Test
//    public void testRegisterHandler2() {
//    	RegisterHandler csh = new RegisterHandler();
//    	RegisterRequest r = new RegisterRequest("exampleUser", "pass", "email@gmail.com");
//    	RegisterResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.username: " + response.username);
//    }
//    
//    @Test
//    public void testNewToolHandler() {
//    	NewToolHandler csh = new NewToolHandler();
//    	NewToolRequest r = new NewToolRequest("TOOL", "TOOLNAME", "TOOL NAME", "Speech", "txt", "ff9aeab7-b842-48ce-8e21-d7516292662c", "testAuthUser");
//    	NewToolResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.tool: " + response.tool);
//    }
//  
//    @Test
//    public void testNewDataHandler() {
//    	NewDataHandler csh = new NewDataHandler();
//    	NewDataRequest r = new NewDataRequest("data", "dataname", "Data Name", "Facial", "csv", "ff9aeab7-b842-48ce-8e21-d7516292662c", "testAuthUser");
//    	NewDataResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.data: " + response.data);
//    }
//    
//    @Test
//    public void testGetUsernameHandler() {
//    	GetUsernameHandler csh = new GetUsernameHandler();
//    	GetUsernameRequest r = new GetUsernameRequest("testAuthUser");
//    	GetUsernameResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.username: " + response.username);
//    }
    
    @Test
    public void testRunAlgHandler4() {
    	RunAlgHandler csh = new RunAlgHandler();
    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0.4, 1.0);
    	RunAlgResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.resultFile: " + response.resultFile);
    	System.out.println("response.image: " + response.image);

    }
    
    @Test
    public void testRunAlgHandler3() {
    	RunAlgHandler csh = new RunAlgHandler();
    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0.3, 1.0);
    	RunAlgResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.resultFile: " + response.resultFile);
    	System.out.println("response.image: " + response.image);

    }
    
    @Test
    public void testRunAlgHandler2() {
    	RunAlgHandler csh = new RunAlgHandler();
    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0.2, 1.0);
    	RunAlgResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.resultFile: " + response.resultFile);
    	System.out.println("response.image: " + response.image);

    }
    
    @Test
    public void testRunAlgHandler1() {
    	RunAlgHandler csh = new RunAlgHandler();
    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0.1, 1.0);
    	RunAlgResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.resultFile: " + response.resultFile);
    	System.out.println("response.image: " + response.image);

    }
    
    @Test
    public void testRunAlgHandler0() {
    	RunAlgHandler csh = new RunAlgHandler();
    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0, 1.0);
    	RunAlgResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.resultFile: " + response.resultFile);
    	System.out.println("response.image: " + response.image);

    }
    
    @Test
    public void testRunAlgHandler49() {
    	RunAlgHandler csh = new RunAlgHandler();
    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0.49, 1.0);
    	RunAlgResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.resultFile: " + response.resultFile);
    	System.out.println("response.image: " + response.image);

    }
    
    @Test
    public void testGetDataDocHandler() {
    	GetDataDocHandler csh = new GetDataDocHandler();
    	GetDataDocRequest r = new GetDataDocRequest("38c9e5b8-33eb-4465-ad3b-aa910541ebcd");
    	GetDataDocResponse response= csh.handleRequest(r, createContext());
    	System.out.println("response.statusCode: " + response.statusCode);
    	System.out.println("response.error: " + response.error);
    	System.out.println("response.doc: " + response.doc);

    }
    
//    @Test
//    public void testRunAlgHandler5() {
//    	RunAlgHandler csh = new RunAlgHandler();
//    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", 0.5, 1.0);
//    	RunAlgResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.resultFile: " + response.resultFile);
//    	System.out.println("response.image: " + response.image);
//
//    }
//    
//    @Test
//    public void testRunAlgHandlern4() {
//    	RunAlgHandler csh = new RunAlgHandler();
//    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", -0.4, 1.0);
//    	RunAlgResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.resultFile: " + response.resultFile);
//    	System.out.println("response.image: " + response.image);
//
//    }
//    
//    @Test
//    public void testRunAlgHandlern5() {
//    	RunAlgHandler csh = new RunAlgHandler();
//    	RunAlgRequest r = new RunAlgRequest("59fb5051-8e5f-4710-b884-372b553911b1", "", "lowpass", "ff9aeab7-b842-48ce-8e21-d7516292662c", -0.5, 1.0);
//    	RunAlgResponse response= csh.handleRequest(r, createContext());
//    	System.out.println("response.statusCode: " + response.statusCode);
//    	System.out.println("response.error: " + response.error);
//    	System.out.println("response.resultFile: " + response.resultFile);
//    	System.out.println("response.image: " + response.image);
//
//    }

}
