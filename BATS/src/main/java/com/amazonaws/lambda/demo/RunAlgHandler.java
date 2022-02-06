package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

//import edu.wpi.cs.heineman.demo.BucketManager;

import java.io.ByteArrayInputStream;

import UpperLevel.RunAlgo;

//import edu.wpi.cs.heineman.demo.BucketManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
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
import com.amazonaws.regions.Regions;


 //this is where we create a choice

//This lambda function gets all of the data documents for the currentUser

public class RunAlgHandler implements RequestHandler<RunAlgRequest, RunAlgResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;

	void getJarFromBucket() throws Exception {
		if (logger != null) { logger.log("in getJarFromBucket"); }
		if (s3 == null) {
			// overly precise.... shouldn't have to live life this way...
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		}
		
		try {
			S3Object o = s3.getObject("batsmqp", "Algo5.jar");
		    S3ObjectInputStream s3is = o.getObjectContent();
		    FileOutputStream fos = new FileOutputStream(new File("./Algo5.jar"));
		    byte[] read_buf = new byte[1024];
		    int read_len = 0;
		    while ((read_len = s3is.read(read_buf)) > 0) {
		        fos.write(read_buf, 0, read_len);
		    }
		    s3is.close();
		    fos.close();
		    logger.log("fos closed");
		    
		    byte[] fileContent = null;
		    fileContent = FileUtils.readFileToByteArray(new File("./Algo5.jar"));
		    logger.log("'Algo5.jar': " + fileContent.toString());
		} catch (IOException e) {
	    	e.printStackTrace();
	    }
	}

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
		
//		try {
//			getJarFromBucket();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ExtensionLoader<Algo5> loader = new ExtensionLoader<Algo5>();
//		algoPlugin = loader.LoadClass("./Algo5.jar", "com.amazonaws.lambda.demo", Algo5.class);
		
		String name="test_d";
        File file = new File("./src/main/java/",name+".csv" );
        
//        byte[] decodedBytes = Base64.getDecoder().decode(data.file);
//        String decodedString = new String(decodedBytes);
        
        File resultFile = RunAlgo.run(file, req.getAlgName());
	    byte[] resultFileContent = null;
	    String resultFileEncodedString = null;
        try {
        	resultFileContent = FileUtils.readFileToByteArray(resultFile);
        	resultFileEncodedString = Base64.getEncoder().encodeToString(resultFileContent);
        	logger.log("resultFileEncodedString: " + resultFileEncodedString);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		RunAlgResponse response;
		if (fail) {
			response = new RunAlgResponse(failMessage, 400, resultFileEncodedString, "image");
		} else {
			response = new RunAlgResponse("none", 200, resultFileEncodedString, "image");
		}

		return response;
	}

}

//modify lastMod for the study
//try {
//	dao.updateLastMod(req.getStudyId(), logger);
//}
//catch(Exception e) {
//	fail = true;
//	failMessage = "Error updating lastMod for the given studyId";
//}

//byte[] fileContent = null;
//String encodedString = null;
//try {
//	fileContent = FileUtils.readFileToByteArray(new File("./img/example_graph.png"));
//	logger.log("fileContent in RunAlgHandler:");
//	logger.log(fileContent.toString());
//	encodedString = Base64.getEncoder().encodeToString(fileContent);
//	logger.log("encodedString in RunAlgHandler:");
//	logger.log(encodedString);
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

//try {
//	logger.log("getImageFromBucket(\"example_graph.png\"): " + getImageFromBucket("example_graph.png"));
//} catch (Exception e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}

//private AmazonS3 s3 = null;

//String getImageFromBucket(String imageName) throws Exception {
//	if (logger != null) { logger.log("in getImageFromBucket"); }
//	if (s3 == null) {
//		// overly precise.... shouldn't have to live life this way...
//		s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
//	}
//	
//	try {
//		S3Object o = s3.getObject("batsmqp", "img/example_graph.png");
//	    S3ObjectInputStream s3is = o.getObjectContent();
//	    FileOutputStream fos = new FileOutputStream(new File("example_graph.png"));
//	    byte[] read_buf = new byte[1024];
//	    int read_len = 0;
//	    while ((read_len = s3is.read(read_buf)) > 0) {
//	        fos.write(read_buf, 0, read_len);
//	    }
//	    s3is.close();
//	    fos.close();
//	}
//	
////	String folder = BucketManager.bucket + "/" + BucketManager.getImgFolder();
////	if (logger != null) { logger.log("Got img Folder"); }
////	S3Object obj = s3.getObject(folder, imageName);
////	if (logger != null) { logger.log("Got Object"); }
////
////	try (S3ObjectInputStream constantStream = obj.getObjectContent()) {
////		Scanner sc = new Scanner(constantStream);
////		String val = sc.nextLine();
////		sc.close();
////		if (logger != null) { logger.log("retrieved img from Bucket"); }
////		return val;
////	}
//}

//boolean createSystemImg(String name, String value) throws Exception {
//	if (logger != null) { logger.log("in createSystemImg"); }
//	
//	if (s3 == null) {
//		logger.log("attach to S3 request");
//		s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
//		logger.log("attach to S3 succeed");
//	}
//
//	String folder = BucketManager.getImgFolder() + "/";
//	
//	byte[] contents = ("" + value).getBytes();
//	ByteArrayInputStream bais = new ByteArrayInputStream(contents);
//	ObjectMetadata omd = new ObjectMetadata();
//	omd.setContentLength(contents.length);
//	
//	// makes the object publicly visible
//	PutObjectResult res = s3.putObject(new PutObjectRequest(BucketManager.bucket, folder + name, bais, omd)
//			.withCannedAcl(CannedAccessControlList.PublicRead));
//	
//	// if we ever get here, then whole thing was stored
//	return true;
//}
