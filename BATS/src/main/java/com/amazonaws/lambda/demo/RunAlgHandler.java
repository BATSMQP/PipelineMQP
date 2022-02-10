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

import java.io.BufferedWriter;

//import edu.wpi.cs.heineman.demo.BucketManager;

import java.io.ByteArrayInputStream;

import UpperLevel.RunAlgo;

//import edu.wpi.cs.heineman.demo.BucketManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
	
	ArrayList<File> getTestResults() {
		ArrayList<File> results = new ArrayList<File>();
		results.add(new File("./test_d.csv"));
//		results.add(new File("./example_graph.png"));
		return results;
	}
	
	boolean addFileToS3(String name, String value) throws Exception {
		if (logger != null) { logger.log("in createSystemImg"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
	
		String folder = BucketManager.getDataFolder() + "/";
		
		byte[] contents = ("" + value).getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest(BucketManager.bucket, folder + name, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	
	File getDataFile(Document data) {
		//create data file
//		File file = new File("./",data.filename+"." + data.ext);
		
		//write data to file
//		try {
//			new FileOutputStream("./"+data.filename+"." + data.ext).close();
//			FileOutputStream fos = new FileOutputStream(("./"+data.filename+"." + data.ext), true);
//		    fos.write(data.file.getBytes());
//		    fos.close();
//		} catch (IOException e) {
//	    	e.printStackTrace();
//	    }
		
		//add data file to s3 bucket
		try {
			addFileToS3((data.filename+"." + data.ext), data.file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//return that file
		return new File("./",data.filename+"." + data.ext);
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
		
		//create new java file from data.file text string with same data.filename + ".csv"
		
		//The following is commented out for AWS
//		File file = new File("./",data.filename+".csv");
		
//		byte[] fileContent = null;
//		try {
//			fileContent = FileUtils.readFileToByteArray(new File("./src/main/java/",data.filename+".csv"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		logger.log("fileContent of data file before in RunAlgHandler:");
//		logger.log(fileContent.toString());
		
		//The following is commented out for AWS
//		try {
//			new FileOutputStream("./"+data.filename+".csv").close();
//			FileOutputStream fos = new FileOutputStream(("./"+data.filename+".csv"), true);
//		    fos.write(data.file.getBytes());
//		    fos.close();
//		} catch (IOException e) {
//	    	e.printStackTrace();
//	    }
		
//		fileContent = null;
//		try {
//			fileContent = FileUtils.readFileToByteArray(new File("./src/main/java/",data.filename+".csv"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		logger.log("fileContent of data file after in RunAlgHandler:");
//		logger.log(fileContent.toString());
	    
        File[] results = RunAlgo.run(getDataFile(data), req.getAlgName());
        
	    byte[] resultFileContent = null;
	    String resultFileEncodedString = null;
	    String resultFileString = null;
        try {
        	resultFileContent = FileUtils.readFileToByteArray(results[0]);
        	resultFileEncodedString = Base64.getEncoder().encodeToString(resultFileContent);
        	logger.log("resultFileEncodedString: " + resultFileEncodedString);
        	resultFileString = new String(resultFileContent, StandardCharsets.UTF_8);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
        
      byte[] imageFileContent = null;
      String imageEncodedString = null;
      if(results[1] != null) {
          try {
        	  imageFileContent = FileUtils.readFileToByteArray(results[1]);
          	logger.log("fileContent in RunAlgHandler:");
          	logger.log(imageFileContent.toString());
          	imageEncodedString = Base64.getEncoder().encodeToString(imageFileContent);
          	logger.log("encodedString in RunAlgHandler:");
          	logger.log(imageEncodedString);
          } catch (IOException e) {
          	// TODO Auto-generated catch block
          	e.printStackTrace();
          }
      }
      
//      String testBase64Image = "iVBORw0KGgoAAAANSUhEUgAAAlgAAAE7CAYAAAAB7v+1AAAACXBIWXMAAA7EAAAOxAGVKw4bAAAAB3RJTUUH4QYDDwI4nVGl6AAAAAd0RVh0QXV0aG9yAKmuzEgAAAAMdEVYdERlc2NyaXB0aW9uABMJISMAAAAKdEVYdENvcHlyaWdodACsD8w6AAAADnRFWHRDcmVhdGlvbiB0aW1lADX3DwkAAAAJdEVYdFNvZnR3YXJlAF1w/zoAAAALdEVYdERpc2NsYWltZXIAt8C0jwAAAAh0RVh0V2FybmluZwDAG+aHAAAAB3RFWHRTb3VyY2UA9f+D6wAAAAh0RVh0Q29tbWVudAD2zJa/AAAABnRFWHRUaXRsZQCo7tInAAAgAElEQVR4nOzdfVRj530v+u+WhAABAiFAM8wLcgbPeLAdBuSXNFMn8TDT2PGdpDjmuqOTk9M7kzY5OU17bmGSZW57b9ZpQ1YNk6anOV1NV6A9baqpi2M1cRPbNcSOXZI6HqHBsRkPZjxiXsQIpBEIEKC3ff+QtUGIFwk2SILvZy3WSI+2th4JRvrq9zz72YIoiiKIiIiISDaKdHeAiIiIaLtRAYDD4UAgEEh3X4iIiIiymlqthtFojAasQCCAgwcPprtPRERERFltaGgIAIcIiYiIiGTHgEVEREQkMwYsIiIiIpkxYBERERHJjAGLiIiISGYMWEREREQyY8AiIiIikhkDFhEREZHMGLCIiIiIZMaARURERCQzBiwiIiIimTFgEREREcmMAYuIiIhIZgxYRNtMe3s7zGazdN1ms8FsNqO3t3fV+/X29sJsNsNms614P7PZjPb29rj7xbaL/Ww3DocDZrMZVqtVlv0t9xoS0fajSncHiGiB2WzG8ePHcfr06bh2h8OB1tZW1NXV4ezZs0nfbyucO3dOlsfu6upCT09PQntbWxuMRuOG9k1EtNUYsIgySF1dHXp6ehLCypUrVwAAdrs94T6xilNVVRUALBvA5GKxWOKuOxyOuMeW+zHa29vR2tqK5uZmmEwm2R5jK7S3t8PlcqGjoyOufelrSETbE4cIiTJIdXU1gIXQFDMyMiJdXnrbtWvXAAAHDhzY5N4l8ng8m7r/WFgcGBjY1MchIpIbK1hEGaSurg7d3d24du1aXMVmcHAQTU1Ny942PDyMuro6aRgtNtS2VqWkvb1dqojV1dWhvr5+zf61tLTAYDDg7Nmzcffv7OxEZ2cnmpqa0NjYKG3rdDql+653qK+ysjIuyMWGSxc7c+YMGhoa4p6bXq/HsWPH4rZd3D8gOrS6tK23txednZ1rVs2WPr9YH2w2G86dOxf3GItvX/wapvqcAODYsWNx+8/G6h7RTsCARZRBjEYjKisrMTw8LLU5HA44nU7s378fdXV16OvriwsEdrsdTU1NKT1OLBzFQpjVakVnZ2dK+zh79qwUJpaGAbPZjLq6Oml4zGq1orW1NeWQFXvuNTU1ACA93uJQFAtEAOL60NPTg56eHukxY9tptdq47dajpaUFp06dkoJN7PUrKSmByWSCxWJZcYhwqVSek91uj/u9dXV14dy5c5ynRpSBOERIlGFqamri5lrF5l+ZTCZUV1fD6XRKc59iw4X79+9Pev82mw12ux1nzpyR2hobG3H8+HEZeg/paLvFFZrGxkZUVlbipz/9aUr7+s53vgMgWrUBgPPnz6Ouri4uYDY0NKCurg4vvPBCwv0XB4+GhgZUVlaiv78/pT4sp6OjI65qVFdXB2BhuDYVqT6nxZXJ2OsS+xshoszBgEWUYWprawEshKeRkRHpAzz2b+wDNfaBnsoQ0cTEBIDEOVtyTVSPDVkuZTAYkpqztXTJB4vFAqPRKFWzYvPUFlsaPIHo0OLSqs7S8CqX9VaP1vOclntcn8+3rscnos3DIUKiDBMLSwMDAzCZTBgcHMTRo0cBLAwhxia9Dw8Pp1x52uwPY5fLBafTueyaWMsFr6XWmjum1WqTattscq75lSnPiYjkw4BFlIHq6uowODgIm80mzb+KqampQU9PD44dO5Yw1JcpVlqvSw7LBcRUQuPSKlCqYhPSl679tZHAtdHnRESZh0OERBmovr4eTqcTAwMDqKysjBsCjA3lxeYzpbo8QyysLZ23s3gpiI2oqamBy+WSZV+LLXcAQMzw8HDCkODS4TUgejSmwWCQrldWVsLr9cZts9brEHvdYkO5K9Hr9aveDqT+nIgoezBgEWWgWGjq6emRjqCLiR1V1tPTE7c8Q7JMJhMqKyvjJlCvtIr6ehw7dgxOpxNdXV1x7e3t7Wuermctjz76KOx2e9x+rFYr7HY7Tp06lbD94qUPrFYrnE6nNDEcWKgGxoKY1Wpd83UoKSkBED+hvaWlJWE7nU63bMjb6HMiouzAIUKiDBSrbDidzmUnn9fV1cFuty87OToZHR0daGlpkYa1jh8/jubm5rj1ldbLaDTCYrHAbDbHhZWlSzmsR+z+sXW3YpZbpqCyshKnTp2KG7pbumbU6dOn4fF4pCCWzOtgMplw5swZdHZ2oru7W3puS4/4a2xsRF9fn7TvldarSuU5EVH2EERRFIeGhnDw4MF094WISBbJrkFFRCS3WKbiECERERGRzBiwiIiIiGTGOVhEtO1s1hIRRETJYgWLiIiISGYMWEREREQyY8AiIiIikhkDFhEREZHMGLCIiIiIZMaARURERCQzBiwiIiIimTFgEREREcmMAYuIiIhIZgxYRERERDJjwCIiIiKSGQMWERERkcxSOtmz2WyWLre1tcFoNAIAWlpa4HQ6AQDNzc0wmUyrthMRERFtZ0lXsKxWK5qbm2GxWNDU1ITu7m6pvaamBhaLBc3NzTh//vyq7URERETbXdIBq7GxUapA7d+/X2rv6+tDbW0tAEi3OxyOFduJiIiItrt1zcEaGBhAdXW1dF2v10uXDQYDPB7Pqu1ERERE21nKActms2FwcBCNjY2b0R8iIiKirJfSJHeHw4Fz587BYrHEtXs8HmnCu8vlkipXK7Uny+PxsOpFREREW0qv16ecWZZKOmA5HA60traira0trr2mpgYDAwMwmUyw2WwAAKPRuGJ7KuR4gkRERERbTRBFURwaGsLBgwdX3bC9vR12uz2uLbZUw0rLN6zUTkRERLQdxTJV0gGLiIiIiFYXy1RcyZ2IiIhIZgxYRERERDJjwCIiIiKSGQMWERERkcwYsIiIiIhkxoBFREREJDMGLCIiIiKZMWARERERyYwBi4iIiEhmKZ3smYiIiCgZl0ZnYHljDO7pAEoLc/C5Bw04vLsg3d3aMqxgERERkawujc6g46XruOqexdRcGCPuOfz5y9dhvzaV7q5tmZQClsPhgNlshtVqjWs3m83Sz+LbWlpapHabzSZPj4mIiCijWd4Yw3woEtfmD0RgeWMsTT3aekkHLJvNhu985ztoamqKa+/q6kJTUxMsFgva2trQ3d0NALBaraipqYHFYkFzczPOnz8vb8+JiIgo47h8Adzwzq1w2/wW9yZ9kg5YJpMJHR0dCe06nS7uemVlJQCgr68PtbW10n2BaAWMiIiItp/J2RC+/x+38NVnryAUEZfdprxIvcW9Sp8NT3JvbGxES0uLVLmyWCzSbXq9XrpsMBjg8XhgNBo3+pBERESUISZnQ3h+wI3eS14EwwvBShAAcVHOylcr8OT9FWnoYXpseJK7w+GA0+lEc3MzgOiQIREREW1v/kAYz/WPo/mfh/Hi27fjwlWVPg+fri3D7uJc5CgVMGjV+J2HKvHAHdo09nhrbbiC1d3djebmZphMJlgsFrS0tEgT2hdXrFwuV1xFKxkejwcej2ejXSQiIiKZzIVEvHEz+jMXih8KLNcAx+5Q4pA+BGAC0ZlCCgARIHgLQ0O30tDj1On1+pQzy1IbDlgulwsTExPSdafTCb1ej5qaGgwMDMBkMkmBK9XhQTmeIBEREW1cMCyi95IXP7w4jqm5+CMEDVo1PnOkDEeri6FUCGnqYWYRRFEUh4aGcPDgwVU37O3tRWdnZ1ybxWKBzWbDuXPnpLampiY0NjYCiC7fENPW1sb5V0RERFkmFqyeH3BjcjYUd1tpQQ6eMJUzWC0Sy1RJBywiIiLaOcIREX3Dk/jhRTdcvkDcbcX5KpysLUPDYR1ylAxWi8UyFU+VQ0RERJLVgpVGrcTj9eUMVklgwCIiIiIAgG1kCs/1j2PEE79QqEatxCP3lOKRe0qhUSvT1LvswoBFRES0w60UrHKUAk7WljFYrQMDFhER0Q51aXQGz/W7cWl0Jq49Rymg4bAOJ2vLUJzPqLAefNWIiIh2mMu3/HjWNp4QrJQKAZ84VILH68sZrDaIrx4REdEOMeKZw3P947CNTMW1KxUCjlYX4zNHymDQ7pzzBW4mBiwiIqJt7oZ3Ht0XxhKCFQD82gEtnjBVMFjJjAGLiIhom3L5AvjhRTf6hicRjsSf1sZUVYTH68tRpc9LU++2NwYsIiKibWZ8KgirfXzZYHXPngKcesDAYLXJGLCIiIi2icnZEJ4fcKP3khfBcHywOry7AI/Xl+Hw7oI09W5nYcAiIiLKcv5AGM/1jy8brKr0efjcRwwMVlsspYDlcDjQ2toad0JnIP5E0JWVlejo6AAAtLS0wOl0AgCam5thMpnk6jcREdGO5w+E8eLbt/Hi27fhD4TjbqvS5+Hx+nKYqorS1LudLemAZbPZcP78eTQ1NSW0d3Z2wmKxxLVbrVbU1NSgo6NDui8DFhER0cYFwyKeH3AvG6wMWjWevL8CD9yhTVPvCAAUyW5oMpmkytRiAwMDOHPmTEJ7X18famtrpfsC0QoYERERrU8wLOLFt2/jv//Te3iufzwuXBm0avzuxyrx9BMHGK4ywIbnYHk8HvT09EhDhMePH8fp06cBAHq9XtrOYDDA4/HAaDRu9CGJiIh2lHBExE/f9eJf7G5MzobibivOV6Hpvgo8dGcxlAohTT2kpWSZ5L54fpXZbMaxY8fk2C0REdGOFo6I6BuexA8vuuHyBeJuK85X4WRtGRoO65CjZLDKNBsOWHq9HhMTE9L1uro6eDweAIirWLlcrriKVjI8Ho+0LyIiop3k7TERr45EcHs2/qjAPJWAj+wR8NF9gErhxtUr7jT1cPvS6/UpZ5alNhywdDodRkZGAETnWNntdpw9exYDAwMYGBiAyWSCzWYDgJSHB+V4gkRERNnENjKF5/rHMeKZi2vXqJV45J5SPHJPKTRqZZp6R8kSRFEUh4aGcPDgwVU3XLwUQ0zsyMGVlmMwm83Stm1tbZx/RUREtIK3b87g/C9dCcEqRyngN2pK8Zm6MgarLBDLVEkHLCIiIpLfpdEZPNfvxqXRmbj2HKWAhsM6nKwtQ3E+1wXPFrFMxd8YERFRGlx1z8LyxlhCsFIqBBytLsaT91cwWGUx/uaIiIi20IhnDs/1j8M2MhXXHgtWnzlSBoNWnabekVwYsIiIiLaAyxfAM2+O4ZdXfQm3maqK8OT9FagsyU1Dz2gzMGDJJHj1Amb/7dsIe51QFFdA80gLcu64L93dIiKiNHP5AvjhRTf6hicRjsQvuWCqKsLj9eWo0uelqXe0WRiwZBC8egHT3/8DiMFZAEDYP4Fpyx+i8IlvIOfQQ2nuHRERpcPkbAjdF8bw+nuJwerw7gI8eX8Fqivy09Q72mwMWDKYfenbUriKEeen4X/pWyhmwCIi2lEmZ0N4fsCN3kteBMOJwerx+jIc3l2Qpt7RVmHAkkF4wrl8++0bW9wTIiJKF38gjOcHPHjxbU9CsKrS5+HJ+yvw4b2FaeodbTUGLBkotBUI+ycSbxBFhK7/Cqp99259p4iIaEv4A2G8+PZtvPj2bfgD4bjbqvR5eLy+HKaqojT1jtKFAUsGmob/hulnWyHOx69lAjECX+dpFDz2NeTe/0R6OkdERJsiGBbxb4O38UO7OyFYGbRqfOZIGT52sCRNvaN0Y8CSQc6hh1D4RBv8L30LkYlRIL8ECM1DnJ0EImHMPN+G0I23oTnZCkHFtU2IiLJZMCyi95IXzw+4MTkbirstFqyOVhdDqRDS1EPKBAxYMsk59FDchHbRP4Hp7qcQvPIGAGDe/iOEx4ZReOocFFpDurpJRETrFI6I6BuexDNvjiUEq+J8FU7WluFEjY7BigAAilQ2djgcMJvNsFqtCbdZrVaYzWbYbDapraWlBWazOaF9JxA0JSj6z99B3tHPS22hm4OY/KtTUugiIqLMF46IeG1oAl999gr+5jVnXLgqzlfhcx/ZhW//1p145J5ShiuSJF3BstlsOH/+PJqampa9va+vD3V1ddJ1q9WKmpoadHR0SPc1mUwb73E2USih+eR/h6ryMGZ++CcQA36I/glM/cPvQXPiK3Hhi4iIMo9tZAqWN1xw+QJx7Rq1Eo/cU4pP3atHXk5KtQraIZL+qzCZTOjo6Fj2tq6uLpw6dSqura+vD7W1tdJ9gWgFbCdS3/tJaH/376As3RdtiIThf+nbmO5+CmLAn97OERFRAtvIFP4f6/v485evx4UrjVqJx+vL8e3fqsbj9eUMV7SiDf9l2Gw2eDyeZatTer1eumwwGODxeDb6cFlLWVEN7Ze+H7eye+BXL8H3N7+N8O3raewZERHFXBqdwdd/dBV//vJ1jHjmpPYcpYBH7ilFe9MBPF5fDo1amcZeUjbY8CT38+fP4/d+7/fk6Mu2J+QVoeg//QVmX/kuZl/5LgAgPDYM319/DoWf/VOeVoeIKE0ujc7guX43Lo3GL7eToxTQcFiHk7VlKM7ncWGUvA39tTgcDjidTrS2tkptdrsdZ86cAQB4PB4YjUYAgMvliqtoJcPj8WzPqteeh6H+RDGKfv6/IARmIM5NYeof/wD+Dz8B/4e5XhYR0WYZmRTxb1cimJwXoc0TcP9uAYPjIq5441deVwjAhw0K/Po+AaX5Priu++BKU59p6+n1+pQzy1IbClhGoxEWi0W63t7ejmPHjsFkMmFkZAQDAwMwmUzSEYSxsJUsOZ5gxjp4EOEjRzFtaUZ4bBgAoHnrWRTPu1D42T+FkMdVf4mI5HRpdAb/9PPrmA9Fw5Q/KOJfp0WIi7KVUiHgaHUxPnOkDAYt1y2k9Us6YPX29qKzs1O63t3dHReuljp9+jTMZjN6enoAAG1tbRvo5vakLN0H7e/+HWasX0fgnejrFLz8enTI0HwOyorqNPeQiGh7mJwN4Xuvj2I+FIlrj4YrAYAIU1URHq8vR5U+Lx1dpG1GEEVRHBoawsGDB9Pdlx1tru/v4X/5L4FI9HQLglqDgsavQ3338TT3jIgo+9zwzuPyLT+ujM/i8i1/wjILS32j8UMMViSLWKbijL0MkXf081DuOhRdusE/ATHgx/QzX0Xe0c9Dc+IrgIJHrBARLScYFjE85sfw2CyGXNFAtfTcgAAgCIgbDowxaNUMVyQ7BqwMknPgQRR/6fuYfuarCN0cBBCtbIVvXUZh0zchaHjSUCKiydkQhsdmcWl0BsNjs7jqnkM4skxyWiRHKaCsMAfj00GEwgvb5qsVePL+is3uMu1AHCLMQGIoAP/zbZi3/0hqU5RUovDJp6HaU5PGnhERbb1Uh/uA6IKgh3drUF2Rj4MGDT5Uno8cpQD7tSlY3hiDezqI0gIVnry/Ag/cod2CZ0E7RSxTMWBlsPk3n8XMj/9sYV6WSg3NyVbk1n06zT0jItocyQ73LVVZkiuFqYOGfFSW5G5Bb4kScQ5WFsi9/4novKzzf4jItAdiKIAZ69cRuvE2Ch77GudlEVHWW+9wX5U+D4cMGhzcpcFBgwZFeXw/pMzCgJXhVPvuhfbL/4TpZ84iNHIRQLSyFb51GYWnvgVF4TZdJ4yItiU5h/uIMhmHCLNFJAz/Cx2Ye+MZqUlRqEfhk+1QVR1JY8eIiJbH4T7aiThEmG0USmge+xqUlYfhf74NYiiAyLQHvr/9HWgebUHeg0+mu4dEtMNxuI9oAQNWlsmt+zRUuw9hytKMyIQzWtn68Z8h7LwEzclWCCqe2oGItgaH+4hWxoCVhZS7DkXXy+p+CsErbwAA5u0/Qmj0MorM56AoqUxzD4lou+FwH1FqOAcrm0XC8Pf+FeZe/1upSdCUoLDpm8g58GAaO0ZE2Y7DfUTrs645WA6HA62trWhqakJjY6PUbjabpcttbW0wGo0AgJaWFjidTgBAc3MzTCaTDF0niUIJzYmvQFV5GDPWr0MM+CH6JzD1D78HTcOXkffQ/5XuHhJRluBwH5G8kg5YNpsN58+fR1NTU1y71WqVwpPVakV3dzfOnj0Lq9WKmpoadHR0SPdlwNoc6ruPQ1luxPQ/fRVhtyNa2Xr5LxFyXkJB49chqDXp7iIRZRAO9xFtvqQDlslkkkLUYosrWfv378fw8DAAoK+vD6dOnZLue/78eTgcDqm6RfJSVlRDG5uXdfl1AEDgnR6Exx0o/K2noSwzpreDRJQ2HO4j2nqyTnIfGBhAdXW1dF2vX1gE02AwwOPxMGBtIkGtQdF/+gvMvvJdzP7se0AkjPDYMHx//bnovKxDD6W7i0S0BTjcR5R+sgUsm82GwcFBdHR0yLVLWqf8h78I1d57Md39FMS5KYgBP6b+8Q+Q//AXkf/xL/AUO0TbCIf7iDKTLAHL4XDg3LlzsFgsce2LK1YulyuuopUMj8cDj8cjRxd3oDIoPvkn0L56DqrJ6wCA2Ve+i8nL/4GpX/99iOqCNPePiNZjJgjc8IlwTERwcwpwTolYY7QPKgWwq1DAfq2AfcUC9mkFaHLCAKYBTGN6DBga24reE2UHvV6fcmZZasMBK3ZkYVtbW1x7TU0NBgYGYDKZYLPZACDl4UE5nuDOdhDivfdh5vk2BAZ+AgBQOwdQ0fN1FJrPQVlRvcb9iWgrXBqdgeWNMbinAygtzMHnHjTg8O7olyAO9xFlp6TXwert7UVnZ2dcm8ViQXt7O+x2e1x7bKmGlZZvoK031/f38L/8l0AkOnQgqDUoONkKde2n0twzop3t0ugMOl66jvlQRGpTKwXsLc3DrckAh/uIskwsU3Gh0R0keOUNzPzgjxCZXhh2zTv6eWhOfIXzsojSIBgW0frc+xidnF/mVgFA4tgfj+4jymw82fMOlHPgwehSDuebEbo5CCBa2QrfuoyCz/4pFIUcjiXaTLEJ6ZdGoz/DY34Ew8tPoBLFCARB4HAfUZZiBWsHEkMB+H/8Z5i3LaxpptAaUHjqHFR7atLYM6LtJZlAJQiAuEzG0uar8EePVXG4jyjLsIK1gwkqNQo+88dQ7b0HM8+3AZEwIj4XpjpPQ/PY15Bralx7J0SUIBwRcdU9h0ujM7g06seQy4+5YGTV++g0KvjmwggtCl75agV++6O7GK6IshgD1g6Wa2qEsqIa0898FRGfC2IogJkf/glCN95GwclWzssiWsN6AlV5UQ4O7y7AvXsKUVOpQXG+CvZrUx8cRRhEaYEKT95fgQfu0G7RsyCizcAhQkJk2oOZ7qcQvHpBalPtuxeFTz4NhdaQxp4RZZ7YKWdSDVR37dLg8O4ClBflbFFPiSgdeBQhxYuE4X+hA3NvPCM1KQr1KGj6JnLuuC+NHSNKrxHP3AdzqKKhaq1lExioiHY2zsGieAolNI99Daq992Lm+TaIAT8i0x5M/e//Cs2jLch78Ml095BoS6QaqIrzVaip1ODuykLcXclARURRDFgUR137KSgNBzBlaUZkwhmtbP34zxC68SsUnGyFoNaku4tEsnJOzOOtGzMpB6q7dhXg8G4NJ6IT0bIYsCiBctchFH/p+5j+wR8h+N7PAQCBgZ8g7LqCIvM5KEoq09xDovVzTszj0qgf796awaDTj8nZ0KrbM1AR0XpwDhatLBLG7CvfxezPvic1CZoSFH72T5Fz50fT2DGi5KUaqGILex7eXYAP7y1goCKilHAOFq1NoUR+w5ehrDyMmR/8McSAH6J/AlP/+AfIf/iLyP/4F9LdQ6IE41NBvOOcwTvO6ZQD1eHdGlTp87aop0S0naUUsBwOB1pbW9HU1ITGxoXFKFtaWuB0OgEAzc3NMJlMq7ZTdlEffhjKL30f05Y/RNjtiFa2ev8KYeclFHz2Tzgvi9JqfCqIS6MzePdWdGL6+FRw1e0ZqIhoKyQdsGw2G86fP4+mpqa4dqvVipqaGnR0dEjbmEymFdspOynLjNB+6fuY+cEfI3DpFQBA4NIrCP/151Bo/haUZcb0dpB2jFQDVV6OAgcNGilUVVfkb1FPiWgnSzpgmUwmKTgt1tfXh1OnTknbnD9/Hg6HY8V2o9EoX+9pSwlqDQpPncPsz76H2Ve+C0TCCLsd8P3151Dw2T+B+vDD6e4iycjlcuHixYuYnp6GRqNBfX09DIatX3h2cjaEQacfv7o5va5AdUdZHpQKnhyZiLaWLHOw9Hq9dNlgMMDj8azYzoCV/fI//gWoKmsw3f0UxLkpiAE/ps83I//jX0D+w1/kKXa2AZfLhVdffRXhcHTJgvn5ebz22mv46Ec/ij179mzqY8cC1bu3ossmOCfmV92egYqIMhEnudO65Nz5URR/+TymLM0I37oMAJj92fcQcg6isOmbEPKK0txD2oiLFy9K4SomGAzil7/8JU6ePAmVSr63jlQDVY5SQHVFLFBpUF2hQY6SgYqIMoss75KLK1Mul0uqXK3Unsp+Y9UwykzCJ55CwRvfQ97VfwcABN/7Odx/8QSmPtGMkM6Y3s7RuszNzcHr9S572+zsLP75n/8ZhYWF0Gq1KCoqQkFBARQKRfL7D4kYmQTe94q4OiHC7RdX3V6lAPYUCTCWRH/2FAlQKeYBzANTXlydSuXZERGtTa/Xp5xZltpwwKqpqcHAwABMJhNsNhsAwGg0rtieCjmeIG2Bmv+JuV9Y4H/pz4FIGMqZcehe/h8oONkKde2n0t07StLU1BTeeecdXL16FZFIBIKwclVoenoa09PTAAClUomysjJUVFSgoqICZWVlUCoXhon9gXDcqWdGPHOr9kOpEBYN+bFCRUTZKemFRnt7e9HZ2RnXZrFYAABms1lqa2trk4LUSu20PQWvXsBM91OITC9UHfN+zQzNJ/9vzsvKYIuDlSiuXE0SBAF5eXmYnZ1ddX9KpRK5hTrMKIrgmMnH5QkVIli5wqVUCLijLE9aNuGgQYO8nOQrYkREmSSWqbiSO8kq4nNh+pmvInT9V1Jbzh33oaDpm1AUshqZSVYLVnv37oXBYMDQ0BD8fj/y8/Nx5MgR7N+/H3Nzc3C5XBgbG4PL5YLP51v1cSJQYCKiwXhYC0+4ADNCIar0DFREtD1xJXfaFAqtAbU+K2gAACAASURBVNozXZh5vg3ztuiSHsGrF+D768+h8Mmnodp3b5p7SGsFq3vvvRc6nQ6XRmfwy7Aa7mAApbk5uDPng4CsVGNSqcf7yMelSBmc89MoEXwoVcygTDkFjRA/SV2BCEoV0yhVTAM5gEqlQnlROQwFBlTkVSBXVbBVT50oY5Yfoe2PFSzaNPM2K/w//jOIoUC0QaFEwclW5JoaV78jbYpkgxUAXBqdQcdL1zEfikjb5CgFlBXmYGwqiHBk5aFEjSKAO4vmUZnnhzowgeD86kOKKpUK5eXlMBgMqKiogF6vX3X+F9F6LV1+BABycnK2ZPkR2jk4REhbInRzENPnmxHxuaS2XFMjNI99DYJKncae7RypBKuYP/6Xq7jqXi4YCQASw1V0yG/h9DMa9cKcu5mZmbghxZmZmVX7q1KpYDAYpJ+lfSNKVezI2F/+8pfL/v3l5eXhxIkTKCri8jK0cQxYtGUi0x7MdD+F4NULUptqTw0KT52DQsvS/GZZT7CaC0bw+nsT+Idf3MJyRSpRFCEIAipLcnF3ZQHu2ZMYqNayOHA5nU7Mza1+VKFarZaOUGTgotWEQiFMTEzA6/VicnISk5OT8Hq9CAQCSd0/NzcX5eXlKC0tRVlZGcrKymRd8412BgYs2lqRMPwv/yXm+v5ealIU6lHQ9E3k3HFfGju2/awnWDkn5vHyoBevvzeBuWAEggAsd0Bhcb4KbY9/CMX58n3o+Hy+uAoXAxetRRRFTE5Owufz4fbt2/D5fPB6vWtWR2NfEFKh0+lQVlaG0tJS6PV6lJSUbKTrtAMwYFFaBAZ+gpnn2yAG/NEGhRKaE19B3tHPp7dj20CqwSocEXHx+jReHryNt28mfjAtDVn5agV+56FKPHCHdtOeA5B64MrNzcWuXbukwKXVbm7/aGv5/X54vd64MDUxMbHqkiJLqdVqFBcXIycnBy6XK24OliAIKC4uht/vT6rSpVarodfrpQqXXq+HWs3pDrSAAYvSJnzrMqb/6asI374utalrP4WCk60Q1Jo09iw7pRqspubCePWyFz9917vsiZPLi3Jw7C4d9AU5sNrdcE8HUVqgwpP3V2x6uFpOLHC5XC7cunVrzQ/BvLw8acI8A1f2CAQCCRWpycnJpIf3gOgabFqtFsXFxdDpdNBqtdDr9cjLy5O2uXnzJvr7+xOWHwGAiYkJeDweuN1ueDweTExMJPW4Wq1WCltlZWWsqu5wDFiUVuLcFKa7n0LwvZ9Lbcpdh1D4W09DWbovjT3LHqkGq6vuWbw86MUvrkwiGE789n/PngKcqCnFkX2FGX2yZK/XK1W4xsbGGLiyjCiKmJiYSAhTfr8/pf0UFBRIIaq0tFQKVnIegRoKheB2u6XANT4+nlTgU6lU0plIysrKUF5ejtzcXNn6RZmNAYvSLxLG7M++h9lXvis1CXlFKGz6JnLu/GgaO5bZUglWwbCIC44pvPSOB8NjiUcF5uUo8NCdJThRo0NlSXZ+AKQauAoKCuICV0EB1+HaLDMzM5iYmJAmm8cmnqcyvJebm4uSkhLodDoUFRVBr9dDq9WmbfL51NSUVOUaHx9Pergy1vfS0lJUVFRAp9NxOZJtigGLMkbw8uuY7n4qbl5W/se/gPyHv5jejmWYVILV7ZkgfvquF6+8O4HJ2VDCvipLcnGiRoeH7izZdquoe71e3Lp1C7du3cL4+DhCocTnvxgD18YFAoGEI/cmJibWfO0Xiw3v6XQ6FBcXS6Fq8fBeJgqFQvB4PLh9+zbGx8fhdrvXnDcIRJ9v7GjFWKVLo+EUie2AAYsyStjtiM7LGhuW2nIOPYTCpm/u+HlZqQSry7f8eOmd27CNTCUsBqpUCDiyrxANh3X48N7CLet/OomiCI/HI02YTzVw7dq1ix96i4TDYfh8PilE+Xw+eDyepALFYlqtNiFMbaeh25mZGWlY0e12w+v1xk2sX4lGo5Emz8fmci0+cTplB1kDVktLC5xOJwDg+PHjOH36NACgvb0ddrsdAHDmzBk0NDRsvOe0bYkBP2asX0fgnR6pTVlmjM7LqqhOY8/SI9lgFQyLeG1oAi8P3sYN73zCforylPjEIR2O3aVDeVHOVnU/I60ncGm12rgKV6ZXVOQyNTWFyclJTExMSHOmkp30HZObmwudToeSkpK4iec7bW2pcDgMr9crzedyu91JzTkTBAE6nU46w0FZWRkrrFlAtoBltVrh9XqlUGU2m9HW1oYrV66gv78fZ8+ehcPhQGtrKywWi5zPIaPw/FbykeZlRaLf+AS1BgWNX4f67uNp7tnWSDZYuXwBvDx4G68NTcIfSPx2fEdZPhoO63C0uhg5Ss71WE4scI2OjmJsbAxut3vNSsN2C1zz8/PS4pyx+UU+ny+l4T2VSrXs8B4ndq9sbm5Omsfldrtx+/btpKpcscVQY0OLer1+xwXWTCfbyZ61Wi28Xm9cm9FoRHd3N+rr66XrdXV1sNlsMJlMG33IjLP0/Fbz8/N47bXXeH6rdcr/+Beg2ntvdF6WfwJiwI/pZ766MC9LsT1L5skGK/u1KXS9eR32a1MJ+8hRCrjPWIRP3q1HdUX+VnU9awmCIA3HANFKg9vtlibMLxe4fD4ffD4f3nvvPQDxgWv37t0ZuyZSOByOq0hNTk7i9u3bmJ9PrHquRqvVJlSkttPw3lbJy8vD3r17sXfvXgALR1fGApfH48HUVOL/8fn5edy4cQM3btwAEP0bLikpiVuBnr+PzLDhgNXQ0ID29naYzWYAQFtbm3Tb4hVv9Xp9yuXlbHHx4sWEN+FgMIg333yTAWudcg48iOIvfR9TlmaEb10GEK1shW78KjovS7N9VlNOJljlFmjxyuUJ/PTlYbh8iUfJFeercKKmFA/fVSLrKus7jVKplM6BCKwvcOl0OilwVVRUpCVwxfoUm3geG+JLRV5e3rLDe5wTtDliw4E6nU4aUZqfn8f4+Dhu374tDS0urSyKogiv1xtX6FCr1SgvL5cqXFwMNT1kmYNlNptx5swZvPDCCzAYDDh79iza29tx7NgxqWLV1dWFqqqqlOZheTweeDyedfdrq1y8eHHFcrpKpZImcO7EuQcbJYTmUPjm3yH3yqtSWzivGMgrgjA7iUhBKWZM/wVBQ036OrlOc3NzuHXrFtxud8JtJSUlqKysxAw0eNMZwcVbEYQiifvYXyzgwT0KHNILyOClq7aNSCSC6elp+Hw+TE9PY3p6es37aDQaFBUVoaioCIWFhbK+B4RCIfj9fszOzmJ2dhZ+vx9zc3OIRJb5Y1mBQqFAXl4eCgsLkZubC41Gg/z8fL5XZSi/34/p6Wn4/X7MzMxgdna5k7Inys/PR0FBAQoLC1FQUID8fFa4VxMLpush2xBhV1eXNIE9Vs3q7e0FgLiKlcfjQW1tbUr73sgT3ErDw8MrVucWL1QXG47YvXs3KisrUVpausU9zVI138LcG8/A/0IHEAlDOTsJzE0CABTzUyh57VsofOIbyDn0UJo7mpy1KlZ3330PrvhUeP6d27h8K3EibI5SwMcOluBETSn26jjHJZ1CoRDGx8eldbg8Hk/C79Tv98Pv98PlcgFYqHDt3r0b5eXlUKlUa87hDIVCCRUpr9eb0vBe7JQwixfm1Ol0nDSd5QKBgHS0Ymxocbm14GIhPPaFTqVSxZ3up7y8nFUumW04YHk8nrjVol0uF0pKSlBdXY3+/n40NDTA4XDAbrfj7NmzG324jFRbW4uf//znCAYXTjsiCAIEQYj7JimKIsbHxzE+Po633noLeXl5qKysxO7duzN67kYmyHvwSah2HcLU334RYiT+9C7i/DT8L30LxRkesNYKVnccrIFtFPh/X3Qvu3aVQavGscM6PHyoBBo1h2kygUqlkv7/AskFrthwzrvvvgtBEKDVauHz+aTt5ufn8eqrr2Lv3r3SvKnl5uKsJi8vT1qQMzbxnMN725NarY77GwSiQ8RLl4lYKhQKSevFxcQWQ42tPl9SUsLFUDdgw0OEsSMEYxYv07B4+Ybm5uZtOcE9ZrnzW+3btw9jY2MYHR2F0+lcdQ4aq1vJ8X7zYYizk4k3CAKK/+BfMvI0O2sFq5I9B/H6tRAuOKaWPYVN3f4iHLurBHX7i7aqyyST2IdYbFmI5T7oBEFYdiVwURTX/HBTq9UJFani4mJ+WaM4scVQF69An0z1M3bKn9LSUunIxWw/anYrcKHRNPD7/RgdHZUC12qHQbO6tbzJv/othG8NJd4gAlAqkVv7GPI+fiYjgtZqwapyz14EtUa8NhLGVXfiHAqNWomPHSzGiZpSGLT83W8XgUBAmjC/UuBaTmx4LzbZPLYUAof3aL1iS3Lcvn0bY2Nj8Hq9SZ3yp6CgIOHE1qyMxmPASjNRFFndWofg5dcx/WwrxPmZlTdSpDdorRasyndVwqPeh585QpiaS1zzZq8uFydqSvGxgyVcu2oHCAQCeOGFFzAzk/j3nJOTgwceeEAKVhyqoc0UDoeloxVjla5kFkNVKpXQ6XRxK9DHzn6wU9eHZMDKMKxuJS94+XX4X/oWIhOjEIoqkPfAEwhdG0Dg0ivxG25x0FotWGn1u3A1shsXRrHsKWxMVUX45N2lOLSLp2XZaW7evJkwh1OlUuEjH/kI9u/fn8ae0U7n9/vjVp9P9pQ/eXl5KCoqgtvtjnsvzMnJ2RHrQzJgZTBWt9YnfOsyZl/5my0PWqsFK3VxBX7lN+DKZGIJvThfhYfvKsGxu3QoLdjZp7DZ6Zabw8lwRZkmtuZW7AAOt9u9bPUVWHluYVFREU6ePLnZXU0rBqwswupWarYqaK0WrCKaMlzwlcMdSFxGoboiH5+8W4/7jEUcBiSirBZbDDU2tOjxeFb9jBIEAadOndrCHm49BqwsxepW8jYraK0WrPw5peifKocvEr+IX45SwK8dKMaJGh3uKOMCf0S0PcVO+fPqq68uuwhqYWEhPv3pT6ehZ1uHAWubYHVrbXIFrdWClVcowTuzhoRgVV6Ug2N36fCJQzoU5fFIGyLaGXby3EIGrG2I1a3VrTdorRasxiLFGArsSghW9+wpwImaUhzZVwglz2FDRDvQTp1byIC1A7C6tbxkg9ZqwcoVLsZ7wfhglZejwEN3luBEjQ6VJTyFDRHRTsSAtcOwupVopaDlz9XDcedncT1cnFSwqizJxYkaHR66swR5OYot6TsREWUmBqwdjtWtBbGgNTFsx5Wyj8CprYEoxAelpcFKqRBwZF8hGg7r8OG9henoNhERZaBYptrwyZ4pO2k0Ghw4cAAHDhxYs7o1NzeH999/H++///62rG75Cyrxzh1NuAoTli7bUjH1Hj7k/g9cVh3CSPFnUFRYiE8c0uHYXTqUF3HtKiIiWp4sFSybzYZz585J1y0WCwCgvb0ddrsdAHDmzBk0NDRsrLe0JXZKdWu1OVZ5fjfqXT+Bdn5MahMFJdS1j0Hzicw41yEREWUe2YYIHQ4HWltb0dbWBqPRKLX39vaiv78fZ8+elbaJBS/KHttx7layk9eN4Wv4PwM/wt7x/4jfQZrPdUhERJlLtiFCu92OpqamuHAFAP39/aivrwcAGI1G1NXVwWazwWQybfQhaQsJggCDwQCDwYAjR46sWt0SRRHj4+MYHx/HW2+9lXHVrWSDVXG+Ck/UlOLhuw6iOP+RxMnwkTDm7T/C/MCPGbSIiGhZGw5YXq8XPT096O7uBgDU1dXh7NmzAICSkhJpO71ev2r1g7JDNs7dSjZYHdqlwW/fXQpTVVHc2lXKXYdQeOocgxYRESVNlknui+dXtbS0wGazybFb6bxGlNk0Gg2qq6sRCATg8/kwOTmJyclJRCIRaZul1S2VSoWSkhJotVpotVqoVPIfbzE3N4fR0VvweNwJt8WClR/5OLJLgfsrFSjXBIDgLVwZvrXCHgXA9EWoPvRJaN76AdTX34w2fxC05i7+KwIf+hhm7vlNRIp2yf58iIhoa+j1euj1+g3tY8OfajqdDj6fT7peU1MjVTIWVzQ8Hg9qa2tT2rccT5DSY63qVigUgtvthtvtlr26NTU1Bftbv8KNkREAy1es8guL8dgRHR4+VAKNOtVT2BwEHvyNhIqWIEaQe+VV5F59nRUtIqIdbsMBS6vVor+/X7re09ODtrY2+Hw+9Pf3o6GhAQ6HA3a7XRo6pO0vHXO3pqam8AvbWxh3XoOwQrA6sLcCv3NXCer2F234OXLokIiIViLLMg0rLcfQ0tICp9MJAGhubuYEdwKw8SMTfzk4grd/NQBleA5hRS6qDx7C7fExzHhuYOlZ/1zhYlxHJerv3I0TNaUwaDdvor1cJ5UmIqLsxZXcKWOksu6WUpWDYDCItc6f7AoXw5e/H79+91587GAJcpRbd8JlBi0iop2LAYsy0lrVLUEQEo4EjN1PEAS4wsXIrTiAhtr9OLRLs1XdXhaDFhHRzsOARVlhcXXr5k0nwuHlq1siALXxQTTU7kdpQWadwoZBi4ho54hlKsXamxKlj0ajQWH5PgxGPoQX/fdiJrL8HKoActH00QMZF66AhcnwxV8+D/Xhhxdu+GAy/OT/fBwz1q8jfPt6+jpJRESy4smeKWNNzobw/IAbLw96EY5EhwUvYS/qckegRFjaLigqUHXonnR1M2k86pCIaOfgECFlnFiw6r3kRTAcP9/q8O4CHNbOYOL6u8hFAAHkoOrgPTh236E09Xb9OHRIRLT9cA4WZZy1gtXj9WU4vLsgTb3bPAxaRETbBwMWZYydGqyWYtAiIsp+DFiUdgxWy2PQIiLKXgxYlDZTc2H88OL4ssHqjrJ8mB+s2JHBaikGLSKi7MOARVvOHwjjxbdv48W3b8MfCMfdVqXPw+P15TBVbfwcgdsNgxYRUfZgwKItw2AlDwYtIqLMJ3vA6u3tRWdnZ9zJnlc6CTTtDAxWm4NBi4goc8UylWwLjb7wwgs4fvy4dL23txcAYLFY4HA40NrayoC1QzBYbS4uWEpElPlkCVhWqxWPPvooRkZGpLb+/n7U19cDAIxGI+rq6mCz2WAymeR4SMpADFZbi0GLiChzbThgORwO9PX1oaOjA11dXXG3lZSUSJf1ej0mJiY2+nCUgYJhET9+y4Of/MqTEKwMWjWeMFXg1w5o09S77Y9Bi4go82w4YHV3d+PUqVNy9CWBx+OBx+PZlH3TxoUigG1UxL9fC2MmGH9bab6AX98n4MOGCBThWxgaupWeTu4oAmD6IlQf+iQ0b/0A6utvRps/CFpzF/8VgQ99DDP3/CYiRbvS21Uiogym1+uh1+s3tI8NByy73S5NZI/x+XwAEFex8ng8qK2tTWnfcjxBkl8wLKL3khfPD7gxOZtYsfrMkTIcrS6GUiGkqYc73UHgwd9IqGgJYgS5V15F7tXXWdEiItpksi7T0NXVhaqqKjQ0NMBqtWJ4eBhnz56VJrlbLBYZukzpEh+sQnG3MVhlLh51SES0dWQ/inCpxsZGtLS0wGw2AwCam5s366FokzFYZTfO0SIi2npcaJRWxGC1PbGiRUS0ebiSO60oHBHRNzyJZ94cSwhWxfkqnKwtw4kaHYNVllsraKkOPID5X1gQ9jqhKK6A5pEW5NxxX3o6S0SUJRiwKEEsWP3wohsuXyDutliwajisQ46SwWo7WTFoiQAW/aqF3EIUPvEN5Bx6aEv7R0SUTRiwSMJgRcDSoCUgmrDiKYrKof2vFigKeXQvEdFyGLCIwYqWFb51GZPf/TwQDq64jaKkEqqqI8jZfwSqffdCuevQFvaQiChzbfpRhJS5GKxoNcpdh6AsvwPhW0OJN34wbBiZcCIw4URg4CcAACGvCKp990K1/0j0Z08NBLVmaztORJRBGLB2kNWCVVGeEp85Us5gRQAATcN/w/SzrRDnZxYalTlQlu5BxOuEGIr/+xHnphB87+cIvvfzaINCCdXuQwuBq6qOw4pEtKNwiHCHsI1MofvCGG545+PaNWolHrmnFI/cUwqNWpmm3lEmCl5+Hf6XvoXIxCiEogpofuP3ob77OMRQAOHRywhdsyN0bQDBETtE/9rnGeWwIhHtBJyDtUPYRqbwXP84Rjxzce0MViSnsNuB0PW3ELo2gNCIHWG3Y837cFiRiLYjBqxtjsGK0kmcm0Lw6gWEbryN0DU7wjcHE4YVE3BYkYi2AQasbYrBijIRhxWJaKfgUYTbDIMVZTJBpY4OB+67FzgabUtmWJFHKxJRtpKlgtXS0gKn0wkgelJnk8kEAGhvb4fdbgcAnDlzBg0NDRvvMcWxX5vCs7bEYJWjFPDYh/X41L16BivKChxWJKLtQLYKVm9vLx599FE0NDSgt7cX58+fh8lkQm9vLwDAYrHA4XCgtbWVAUtGl0Zn8Fy/G5dGZ+Lac5QCGg7rcLK2DMX5LFBS9hDyiqA+/DDUhx8GkOSwYiSM0M1BhG4OAr+wAOCwIhFlhg1/Ai8OTQcOHJAu9/f3o76+HgBgNBpRV1cHm80mVbdofRisaKfgsCIRZTNZP4ntdjtqamqk6yUlJdJlvV6PiYm1J7XS8hisiABlmRHKMiNy6z4NILlhRS6CSkTpINsnssPhQHd3NywWi1y7hMfjgcfjkW1/2WhkUsSrjghGJuNPvKtSAKbdAn59vxIFOT64rvvgSlMfidJKuQeo2gNUfRIIB6G67UDO+LvIGR+CauxdKOan4rdfZlgxXFCOYMUhhMoPIVR+J0I649Y/DyLKGHq9Hnr9xr54yRawWltb0dbWFte2uGLl8XhQW1ub0j7leILZihUrovW6G8Bj0rVkhhWVM+NQXh0Hrv47AA4rEtHGyfIJbTab0dzcDKPRKLVVV1ejv78fDQ0NcDgcsNvtOHv2rBwPt61ddc/C8sZYQrBSKgQcrS7Gk/dXMFgRpYDDikSUDhtepqGrqws9PT1xbbGlGlZavoESjXjm8Fz/OGwj8cMZsWD1mSNlMGjVaeod0fbFRVCJSE5cyT1DMFgRZR6eW5GI1osBK80YrIiyBxdBJaJkMWClCYMVUfaTe1gxePUCZv/t2wh7nVAUV0DzSAty7rhvs58GEW0CBqwtxmBFtL2td1hRod+PyOgQxEhwoT23EIVPfAM5hx7axB4T0WZgwNoiLl8Az9rG8IsrvoTbTFVFaLqvAnt1uWnoGRFtpuSHFQUAYmJrTi5Ue++FoK2AoFBByCuEkFcEQaWG8MFQo6KkMvpvUVm0Pa8IQl7RJj4rIlqLbOcipKhLozOwvDEG93QApYU5eOxePd6+OYO+4UmEI/FvnqaqIjxeX44qfV6aektEmy35cysmhisAEIPzCF69sL4HVyih0BqiF4s/CGiaEghqDQR1PgRNyQfbVES3KSwDVGoo8j8IaKpczhcj2iBWsGRwaXQGHS9dx3woIrUJAiAued9ksCKixcJuB6b+95cRmbyVeKOIaHErzRaqZHoIqlxAnQ+FRhcf0LQV0esaHaDOh6DWQFFQEnd/op2CFSwZWd4YiwtXQCxcRUv/DFZEtBxlmREF/8dTmH62FeL8wuLCglqD/BNfgbLiAABA9I1BjIQg+icgBmajPx9Mqo9MRNcajEy5IYYCEOemIM5NJT7YOkn7/+Df9VIU6gFVbnTeWX5RtGJWWBa9rWQ3AHxQZYsGNEFTAkGhgqI4FuIMgEK5oT4APKCAtg4Dlgzc0ysdri3iG40fYrAiohXlHHoIhU+0wf/StxCZGIVQVAHNb/w+1Hcf39B+xVAA4rQbABD2RsNRNKD5FwJaJIyIbwwAEPG5otdnotsgNI/ItHzngl28r/AG9iMNdarUUBTFAlq0SiYU6hfNRSuEoMpdmK9WbED45iBm/uV/QAzORvvhn8C05Q95QAFtCgYsGZQW5GBqLvEtw6BVM1wR0ZpyDj2EYpk/4AWVGkJseG+Dw3QLVTIPxND8QpUsFJCCU8Q3Fg1ofi8QmIUY8CMyEwtx8p2KXvRPSNW7ZI7UjJd4QIE4P43pf/4qDygg2TFgyeAJUzn+6tWbmA0sDBPmqxV48v6KNPaKiEgeCrmC2gdVMnFuCpHZWECLVtkiE6PRf6c9wKKhTjESQmQyvsq2fjyggLYOA5YM6vYX4cuf2PPBUYRBlBao8OT9FXjgDm26u0ZElDFiQQQANjKbamGoM7FKJk57Fs1Fm45W3D6osgWvDQDh4DI7xPoPKIiE5ZunxgMKtpVNDViLTwTd1NSExsbGzXy4tKrbX4S6/SwXExFtNkFTEq0MIbWgFrz8Og8o4AEFW2bTApbNZsPg4CAsFgsAwGw2o66uDkajcbMekoiIaEU8oCA1PKBgYzYtYA0MDODo0aPS9ePHj+PKlSsMWERElDY8oCB5m3FAgf+lb8n++meqTR0i1GoX5iDpdDr4fImniyEiIqKo7X5AQfj2jQ3sM7tk9CR3j8cDj0e+sikREdHOIgD4oNhRUPbBv3clfW/F/BSE0Fz0Z24KiISh8Ec/l5Vzk0A4CCEwA0VwFggHoJiNVrxU40MQIqGE/YU1ZRgaGtrQM9oKer0eev3Gju7c1IC1uGLl9XpRVVWV0v3leIJERES0tZY9oCC3AMWPNaN8h5yaT7FZO66qqkJfX590vaenBwcOHNishyMiIqIMETugQFlWFZ0kr9uLgt/8/zZ8QEE22bQKVkNDA/r7+2E2mwEAZ86c4QR3IiKiHWIzDijIJps6RHj27NnN3D0RERFRRtq0IUIiIiKinYoBi4iIiEhmDFhEREREMmPAIiIiIpIZAxYRERGRzBiwiIiIiGSW0afKyRS9vb3o7OwEANTV1UnLT9hsNpw7dw4AUFlZiY6ODuk+VqsV3d3daGtrg9FojNs2ZvG+KKqrqws9PT0AgKamJjQ2NgJYeD2B6InDT58+Ld2nvb0dLpcr7vVfvJ+lvxuKamlpgdMZPbFsc3MzTCYTgOjrabfbAUTXr2toaJDuYzabE17/lfZDUBvKxAAADDZJREFUUQ6HA62trdJ1i8UiXY6tEwhAeq9IdXtakOp79UrbA4nv4RRvpfeJld7DV9p+tfebrCeKonj58mWRVvb0009Ll5ubm8Wenh7p8oULF0RRFMXOzk7xueeei7vc3NwsXr16ddl9Pvfcc9L2FHX16lWxs7NTunzq1Cnx6tWr0uWYxa977PfR3Nws3X7hwoW4608//bT0O6Oonp4e6TVZ/Pr19PRIf++LX/fY5Z6eHul3JIrRv+PY9QsXLsT9niiqs7NTeh/o7OyUXq/F7xmLX/dUt6cFqb5Xr7R9Mu/hO9mFCxek13Dx//ul772x9/DVtl+uPdvFMhWHCJOw+FtNTU0NgOg3IgDSt/Xa2loMDw8DAE6fPi2l9pV0d3ejrq5uM7qbtYxGo1QZMRqNqKyMnk3ebrfj+PGF0yscPXoU165dAwB0dHQknIJpufNXlpSUbFa3s1JDQ4P0TXHx69ff34/6+noA0d9BXV0dbDYbjEZjXCUlZnh4GLW1tQCi/xcqKyvhcDg2/wlkkdOnT0sVkMXnY+3p6ZHeAxoaGqRv8aluTwtSfa9ebnsguffwncxkMkmvz+KK9cDAAI4ePSpdP378OK5cubLi9iu1bxcMWClafE5Fg8Egtev1erhcrqT20dvbi+PHj7PsvIrYm2LsNdLpdNJtWq0WXq93xfsajUYcPXoUZrMZZrMZ9fX12/I/r1zsdnvch8viMKrX6zExMbHiffV6vRR2gej/CY/Hszkd3Qb6+/vjQtPi94Dlwmmq29OCVN+reb7c9ent7Y0rFmi1WumyTqeDz+dbdfu12rMZA1YKurq60NTUtOFg9MILL0jf+ml558+fx6lTp9Z9/+HhYRw/fhzHjx+X5lhQIofDge7u7rg5Vak4duwYuru7pTDLqsrKent7ASDpOSapbk8LUn2vluu9fSfq7OxEU1PThrdPdT/ZgJPck2S1WgEgrmy8+FuQx+OJ+5a0kqXlakrU3t6ORx99NO41Wlyx8vl8cRWtpXp7e6HX66XQoNPp0NXVte4QsZ21traira0trm1xxcrj8az6ZWDp0GFLSwv/tpdhs9nwwgsvJBxs4XA4pA91p9MpXU51e1qQ6nv1cttTclpaWtDc3Bz3d7i4YuX1euMqsMttv1p7tmMFKwlWqxXDw8NxH9AmkwlOp1MKTAMDA6iurl5zXz/96U/x6KOPblpfs117ezuqq6vjvrXv379fOioFAPr6+rB///4V9+Hz+eKGqbxe76qBbKcym80Jb2rV1dXo7+8HEP0wt9vtSQcmq9UaN9RIUbEj2JaGpbq6Oqnit3h4JNXtaUGq79XLbU/JaWlpSfgiXFVVhb6+Pun64mHX5bZfrX07EERRFIeGhnDw4MF09yUjLT1kGlg4nHelQ3wXH3YKLCwrENvXcpOFKf6Q6ZjYYb4rHfq7+LB1YOEw38VLB3CZhkSLX8+Y2BILyy27sNwyI21tbbDb7dLyGVx2ZHmLX88Yi8Wy4nIMqW5PUam+V6+2/Urv4RS1eNmcmNh773LLLqy0vc/nW3E/2SyWqRiwiIiIiGQSy1QcIiQiIiKSGQMWERERkcwYsIiIiIhkxoBFREREJDMGLCIiIiKZMWARERERyYwBi4iIiEhmDFhEREREMmPAIiIiIpIZAxYRERGRzBiwiIiIiGTGgEVEREQkMwYsIiIiIpkxYBERERHJjAGLiIiISGYMWEREREQyY8AiIiIikhkDFhEREZHMGLCItpjD4YDZbJZ+Wlpa0t2ljNDS0hL3uvT29iZ1P7PZvK5tYr+H7cpqtaKrq0u6brPZYDab4XA4pLauri7pdU71tdjOrx2RHBiwiFJweyaI14Ym8OrlCdyeCa57P5WVlbBYLLBYLDAYDEmHiUwU8bkwb/8R5m1WRHyuDe2rra0NFosFbW1t6Ozs3NTXxWg0wmKxbNr+N8Lv9+P999/HlStX4Pf717WPuro6DA4OStcnJiYAAFeuXJHaBgcHceDAgY11loiWpUp3B4iyRd/wJDr/3QkBgtT220d34aE7Sza875KShX1YrVZ0d3cDAJqamtDY2AibzYb/v337CW2zjOMA/k2Trt1iy7ox44I2mU1HyIYsZKvMHIS+VbYpyHRFycFD40kGHtox3GEXoZe0J4ewQ3JRMiG6IAyDkESGh7rDm9e1ELAYyFtZcA5ptevWduteDyXP8qZp06Rv/38/pzfv+zxv3vdJ4P3m+T0ZHh4Wbfr7+wEAd+/eRTKZhN1ux9DQkJhVKL5eb/OjCTz84YvnY2ICrO9+jj0n3l3TeZ1OJ4LBIDKZDCRJWnL/lYLRwMAAPB4PkskkgMWw5nQ6xfFIJCKOFfsHAgGxXd6/uD8UCkFRlIrnXA/5fB537tyBybQ4ppqmoaurC0eOHKnpPMXrzOfzcDqdUFUVPT09UFVV7C9tByw/RkXF72Op0s/G6/Xi0qVLADZ+3Ii2GgYs2rVGcv/hm1//wr+Pn9bYUxNb128XcP12oWqPlmYz3jtxCGeOHwAAFAoFXRjy+XwAFh9Wk5OTuod7Pp+Hz+cT+2RZRjqdRnd3N5LJpHh4xePxig/AWsyP/YRHiSE8e/hPTf20ku2HN68CN69W7WPatx973/wEzacrl5o6OjqQSCQAAMPDw7r7DIVC4kFeVCgUcPbsWUSjUcTjcaTTafT19YnjbW1tiEajiEQiiMfjS8apUCjA7/fr2rS3twOoHOhWS1VVyLKM2dnZus8xMjKCkZGRqu2amppw7NgxuN1uAIDH40Eul4PT6UQ2m8XFixdx7do1AIszWX6/X9e/0hiV3nsgEFgybjdu3BBt4vE4UqmU+MGwVWcIiTYCS4S0a8WVB3WEq/pMzy7gO/lv8bq0ROj3+xEKhQAAExMTSCaTYh2SoiiipFNco1Q6k+P1esXMQHt7O2Kx2JrKao9/vl5zuKqX9mgKj1NfVW0ny7LuPr1eL+7fr1yKlCRJtCktjwEQwcDhcGBycrJi//I2Pp8PiqLo1jLVamxsbE3hqhZzc3MYHR0Vrx0OB1RVRT6fh81m081qqaoqAmRRpTGKRCLi+1hOlmXxYyEQCCAWi0FVVUPGjWi7Y8CiXavb3YZGs6l6QwOYG0yQ3G0Vj5UHht7eXhG+otEoJElCPB6Hx+NBNBoV5cFyxVkuVVXrXjjfdOoCTJY9dfWtWYMZTacuLHs4l8vBZrNtzLWsIBqNwuFwLFkgvloulwtms9n4C6vAZDKhs7NTvO7o6EA2m0Uul4PL5QLwfFYrm82KmdPlyLKMbDYrvouVeL1e3fe1OGu41nEj2u5YIqRd68zxA6JkV82D6Se4/P0fmH+q6fY3WRow+P6rsLXWH0oURRFBorW1FYlEomKZr61tMaBNTEyseL6+vj4MDAyItTe1aD4dWLZkV+7ZVAFTX34APJnT7Tft2YvWT7+F+cArNb13uXA4jGAwiIMHD0JRFHE/iqLA4/FU7JNKpSBJ0opt6iFJElRVFeW2WrjdblGyq2ZmZga3bt3CwsKCbr/ZbMa5c+fQ0tJS03s7nU7YbDZkMhl0d3cDWJydymQyqw6vxXayLC85Vv7ZlFvLuBFtdwxYRKtwqKURH59+CV+P6EtTH3W9WFe4Kl2DBTxfq1J8IJUeGxwcxPnz50UJxm63V3w4li5Q7unpWfcHWsN+O6zvXMajH0O6/fve/qzucHXlyhWx3d/fL2ZYgsGgOLbSAv7SsTNi/U8qlUI4HBbvW7qmaz1YrVacPHlSF2Y0TYPX6605XBW5XC7EYjGxZk2SJITDYfT29lbt6/P5kE6ndesFSxX/jFD6uQWDQQDY0HEj2opMmqZp4+PjOHr06GZfC9GW92D6CX77cxoA8NrLL6xp5mqneDZVwPzvvwAAGjvfWPPMVb1K/xG43c3MzODevXsAgMOHD9cdroho4xUzFWewiGpwqKURb3lWV1bcLRr229H8+oebfRk7itVq5Y9eom2Oi9yJaEfYKbNXRLQzMGARERERGYwBi4iIiMhgDFhEREREBmPAIiIiIjIYAxYRERGRwRiwiIiIiAzGgEVERERkMAYsIiIiIoMxYBEREREZjAGLiIiIyGAMWEREREQGY8AiIiIiMhgDFhEREZHBLABgsVgwPj6+2ddCREREtK1ZLBYAgEnTNG2Tr4WIiIhoR2GJkIiIiMhg/wOGtva4qhHejQAAAABJRU5ErkJggg==";
//      imageEncodedString = testBase64Image;
      
		RunAlgResponse response;
		if (fail) {
			response = new RunAlgResponse(failMessage, 400, resultFileString, imageEncodedString);
		} else {
			response = new RunAlgResponse("none", 200, resultFileString, imageEncodedString);
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

//try {
//getJarFromBucket();
//} catch (Exception e) {
//// TODO Auto-generated catch block
//e.printStackTrace();
//}

//ExtensionLoader<Algo5> loader = new ExtensionLoader<Algo5>();
//algoPlugin = loader.LoadClass("./Algo5.jar", "com.amazonaws.lambda.demo", Algo5.class);

//String name="test_d";
//File file = new File("./src/main/java/",name+".csv" );

//byte[] decodedBytes = Base64.getDecoder().decode(data.file);
//String decodedString = new String(decodedBytes);
