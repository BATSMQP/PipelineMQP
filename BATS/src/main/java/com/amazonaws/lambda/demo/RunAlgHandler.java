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
		
		//199 to 227 commented out temporarily
	    
//        File[] results = RunAlgo.run(getDataFile(data), req.getAlgName());
//        
//	    byte[] resultFileContent = null;
//	    String resultFileEncodedString = null;
//	    String resultFileString = null;
//        try {
//        	resultFileContent = FileUtils.readFileToByteArray(results[0]);
//        	resultFileEncodedString = Base64.getEncoder().encodeToString(resultFileContent);
//        	logger.log("resultFileEncodedString: " + resultFileEncodedString);
//        	resultFileString = new String(resultFileContent, StandardCharsets.UTF_8);
//	    } catch (IOException e) {
//	    	e.printStackTrace();
//	    }
//        
//      byte[] imageFileContent = null;
//      String imageEncodedString = null;
//      if(results[1] != null) {
//          try {
//        	  imageFileContent = FileUtils.readFileToByteArray(results[1]);
//          	logger.log("fileContent in RunAlgHandler:");
//          	logger.log(imageFileContent.toString());
//          	imageEncodedString = Base64.getEncoder().encodeToString(imageFileContent);
//          	logger.log("encodedString in RunAlgHandler:");
//          	logger.log(imageEncodedString);
//          } catch (IOException e) {
//          	// TODO Auto-generated catch block
//          	e.printStackTrace();
//          }
//      }
      
      String testBase64Image = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAGQAZADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD26iiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKAPhGiiigD7uooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD4RooooA+7qKKKACiiigArzrS9WttT+J8kdh4gkjhsnuILmzuL/AHNeTc/LHAT8qR4PzADOO4BNei0UAeS/ELxc9/o2h6noOq3UWjXcV+xurdpIC08cLeQpPDcyqRtP3iO9U/FHijxPpl/4blmExYaTb3P2PdJH9svWljWSL5GUlgrFtpyvBypr2aigAooooAKKKKACiiigArKvL7Sb/Up/C9zKHu57Fp5LYo3MBbyy27GOvGM5rVooA818b6jri/CnVVsryaLV9MlSG6lhDCV1V1O9WUgruQq5PIwWGO4z9Q+JutWeqtZ2n9i30MaRvHeeZHbwXwZjv8t5LgBdv3ePM+ZTnbXqaWcEd7LeJHieZFjkYE/MFJK8dMjceev5Cp6APPrPxrq1749vPDhbT4gGnW2aCIXYARThpmScGM7uqMgzjAbJyF+EWp6hf+D449V1aO8voGdZoJNxurc+bJxMzOScgDb8q4Axz1r0CqOo6Rbar5X2iW9Ty87fst7Nb5zjr5bru6d845x1NAHCfEzxNd2Y1Dw+rWlpaXOhXU5ubpWPnvgr5EWGUB8c5OevSnW3jKHw/wDDXTILKE3utW3h21vksgj/ADRYSMuSBjAJJIznAP1rvNP06DTLdoYHunRm3k3N1LO2cAcNIzEDjpnH51boA8113x/f6Rodjf2uq+H9TjmlkFxcWu392qqp2xwtcAyvluQHBAI+Xmm6l498Q2Op22lRWVrNeatDayaRI9tJGp3Y87zk3krtGTgHgEZzXplFAHDfErVJ9ItvC866g9nE3iC0S6lWUxK0WHLhzn7nGSDxxzWTrnxOu7bVr1NIGm3dpb/ZvskSkySap5jbX8h1bHyHIPDcg5xXp9FAHk2rfFDXdGu9ZguNOtGGkTzJcOsbj92yA2h+91djhvbpUd38S/Ethe6nZ3EWkG/sf7PC6esMnnXbzorSpH+86qSecHjrXrtULXRtPstW1DVLe32XuoeX9ql3sfM8tdqcE4GAccAe9AHBar8S7m08bW2k2awTWr6rFps8csHlyxl+CwPnbmGeQfKCkcbs9a1j8StburbWyY9HN5ZW/mxRrKn2XmUIu67ExXODnaVQk46V6rRQB5RJ8T9WGgadew20Mgurya3lvJLVYoYNiqQPmuNjBiTh/NAODgEjBqeIvHmq6h4YawvI9L08X+gXlxNN5ouI5pF3x+TA6Pt3EDd1bGcYJFexUUAYPgj/AJEHw5/2C7b/ANFLW9RRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfCNFFFAH3dRRRQAUUUUAFFFFAGbpt1NcX+sRSvuS3vFiiGANqmCJ8e/zOx59a0qyNG/5CniD/ALCCf+k0Fa9ABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAVia34Xsde1PSL+6luUl0ufz4BE4Cs2VPzAg5HyjpjvW3RWlKrOlLng7P8Az0E1fcKKKKzGFFFFABRRRQB8I0UUUAfd1FFFABRRRQAUUUUAZGjf8hTxB/2EE/8ASaCtesjRv+Qp4g/7CCf+k0Fa9ABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfCNFFFAH3dRRRQAUUUUAFFFFAGRo3/IU8Qf9hBP/SaCtesjRv8AkKeIP+wgn/pNBWvQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/wAhTxB/2EE/9JoK16yNG/5CniD/ALCCf+k0Fa9ABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfCNFFFAH3dRRRQAUUUUAFFFFAGRo3/IU8Qf8AYQT/ANJoK16yNG/5CniD/sIJ/wCk0Fa9ABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfCNFFFAH3dRRRQAUUUUAFFFFAGRo3/IU8Qf9hBP/AEmgrXrI0b/kKeIP+wgn/pNBWvQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/yFPEH/YQT/0mgrXrI0b/AJCniD/sIJ/6TQVr0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQB8I0UUUAfd1FFFABRRRQAUUUUAZGjf8AIU8Qf9hBP/SaCtesjRv+Qp4g/wCwgn/pNBWvQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/yFPEH/AGEE/wDSaCtesjRv+Qp4g/7CCf8ApNBWvQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/yFPEH/YQT/wBJoK16yNG/5CniD/sIJ/6TQVr0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAYvh658QXJv/AO3rC2tNlwVtfIfd5kXZj8x5/L6CtqiitKs1OTkkl5LYSVgooorMYUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQB8I0UUUAfd1FFFABRRRQAUUUUAZGjf8AIU8Qf9hBP/SaCtesjRv+Qp4g/wCwgn/pNBWvQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/yFPEH/AGEE/wDSaCtesjRv+Qp4g/7CCf8ApNBWvQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/yFPEH/YQT/wBJoK16yNG/5CniD/sIJ/6TQVr0AFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQB8I0UUUAfd1FFFABRRRQAUUUUAZGjf8hTxB/2EE/9JoK16yNG/wCQp4g/7CCf+k0Fa9ABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfCNFFFAH3dRRRQAUUUUAFFFFAGRo3/ACFPEH/YQT/0mgrXrI0b/kKeIP8AsIJ/6TQVr0AFFFFABRVe91Cy02ETX13BaxFggeeQIpY9BknrVinytK9tACiiikAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAHwjRRRQB93UUUUAFFFFABRRRQBkaN/yFPEH/YQT/wBJoK16yNG/5CniD/sIJ/6TQVr0AFFFFAGbregaX4jsks9WtFubdJBKqFmXDAEA5Ug9CfzrSooqnOTiot6LZeu4WCiiipAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD4RooooA+7qKKKACiiigAoory3RvGuuXnxLGmTzBrCbUtQsfsvlKPKS3jjZJA2N2WLHOSRzxQB1lprek6brWvw3+qWVrK18jBJ7hEYr9mgGcE9OD+VX/wDhK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xo/4Svw5/0H9K/wDAyP8AxrXooAyP+Er8Of8AQf0r/wADI/8AGj/hK/Dn/Qf0r/wMj/xrXooAyP8AhK/Dn/Qf0r/wMj/xp8XibQLiZIYdb02SWRgqIl3GWZjwAADya1Kr38og066ma5S1EcLsbiQArFgE7yD2HX8KALFFcb4X1m9Wy1TV9b1QDQTKn9nXWoLHbyGPGC74CqFZiNuQDjr1FVvFOu6rpXi3Q4bW/dbO71KG0mVo4mgAZSTGcZlEpwCDgIAwzQB3dFFFABRRRQB8I0UUUAfd1FFFABRRRQAVUj0vT4tQk1COxtkvZV2yXKwqJHHoWxkjgVbooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAqOeCG6t5Le4iSWGVSkkcihldSMEEHggjtUlFAGTF4W8PQWlxaQ6Dpcdtc7fPhSzjCS7TldygYbB5GelWn0jTZNSTUX060a+QYW5aFTKo9mxn9auUUAFFFFABRRRQB8I0UUUAfd1FFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAfCNFFFAH3dRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAH2hRXxf/wm/iz/AKGjWv8AwYS//FUf8Jv4s/6GjWv/AAYS/wDxVAGDRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFABRRRQAUUUUAFFFFAH/9k=";
      String imageEncodedString = testBase64Image;
      
      String testResultFileString = "0.3,0.17375766604868043\r\n" + 
      		"1.3,0.8991870415704111\r\n" + 
      		"2.3,2.0585132002904136";
      String resultFileString = testResultFileString;
      
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
