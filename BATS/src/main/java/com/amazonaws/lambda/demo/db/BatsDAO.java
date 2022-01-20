package com.amazonaws.lambda.demo.db;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;

import com.amazonaws.lambda.demo.model.AuthUser;
import com.amazonaws.lambda.demo.model.Data;
import com.amazonaws.lambda.demo.model.Document;
import com.amazonaws.lambda.demo.model.Facial;
import com.amazonaws.lambda.demo.model.FacialStudy;
import com.amazonaws.lambda.demo.model.LogData;
import com.amazonaws.lambda.demo.model.LogDataStudy;
import com.amazonaws.lambda.demo.model.Neural;
import com.amazonaws.lambda.demo.model.NeuralStudy;
import com.amazonaws.lambda.demo.model.Speech;
import com.amazonaws.lambda.demo.model.SpeechStudy;
import com.amazonaws.lambda.demo.model.StressIndicators;
import com.amazonaws.lambda.demo.model.StressIndicatorsStudy;
import com.amazonaws.lambda.demo.model.Study;

public class BatsDAO { 

	java.sql.Connection conn;
	

//	final String tblName = "Choice";   // Exact capitalization

    public BatsDAO() {

    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
    public AuthUser getAuthUser(String username, LambdaLogger logger) throws Exception {
      
      try {
      	{ logger.log("in getAuthUser"); }
      	AuthUser authUser = null;
          { logger.log("authUser = null"); }
          { logger.log("con= " + conn); }
          PreparedStatement ps = conn.prepareStatement("SELECT * FROM AuthUser WHERE username=?;");
          ps.setString(1, username);
          ResultSet resultSet = ps.executeQuery();
          
          while (resultSet.next()) {
              authUser = generateAuthUser(resultSet, logger);
              { logger.log("authUser (in getAuthUser): " + authUser.toString()); }
          }
          resultSet.close();
          ps.close();
//          { logger.log("authUser after while loop (in getAuthUser): " + authUser.toString()); }
          return authUser;

      } catch (Exception e) {
      	e.printStackTrace();
          throw new Exception("Failed in getting authUser: " + e.getMessage());
      }
    }
    
	 public boolean addAuthUser(AuthUser authUser, LambdaLogger logger) throws Exception {
	  try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM AuthUser WHERE authUserId = ?;");
	      ps.setString(1, authUser.authUserId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in addAuthUser): " + ps); }
	      // already present?
	      while (resultSet.next()) {
	    	  AuthUser c = generateAuthUser(resultSet, logger);
	          resultSet.close();
	          return false;
	      }
//	      logger.log("choice.numMembers in addChoice before insert: " + choice.numMembers);
	      ps = conn.prepareStatement("INSERT INTO AuthUser (authUserId, firstName, lastName, username, pass, email) values(?,?,?,?,?,?);");
	      ps.setString(1, authUser.authUserId);
	      ps.setString(2, authUser.firstName);
	      ps.setString(3, authUser.lastName);
	      ps.setString(4, authUser.username);
	      ps.setString(5, authUser.pass);
	      ps.setString(6, authUser.email);
	      { logger.log("ps for insert (in addAuthUser): " + ps); }
	      ps.execute();
//	      boolean test;
//	      int index = 1;
	      return true;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to insert authUser: " + e.getMessage());
	  }
	}
	 
	public boolean addDocument(Document document, LambdaLogger logger) throws Exception {
	  try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM Document WHERE documentId = ?;");
	      ps.setString(1, document.documentId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in addDocument): " + ps); }
	      // already present?
	      while (resultSet.next()) {
	    	  Document c = generateDocument(resultSet, logger);
	          resultSet.close();
	          return false;
	      }

	      ps = conn.prepareStatement("INSERT INTO Document (documentId, file, name, dataType) values(?,?,?,?);");
	      ps.setString(1, document.documentId);
	      ps.setString(2, document.file);
	      ps.setString(3, document.name);
	      ps.setString(4, document.dataType);
	      { logger.log("ps for insert (in addDocument): " + ps); }
	      ps.execute();
	      return true;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to insert document: " + e.getMessage());
	  }
	}
 
   public boolean addStudy(Study study, LambdaLogger logger) throws Exception {
	   try {
		   { logger.log("In addStudy 1 (BatsDao)"); }
	       PreparedStatement ps = conn.prepareStatement("SELECT * FROM Study WHERE studyId = ?;");
	       { logger.log("In addStudy 2 (BatsDao)"); }
	       ps.setString(1, study.studyId);
	       { logger.log("In addStudy 3 (BatsDao)"); }
	       ResultSet resultSet = ps.executeQuery();
	       { logger.log("ps for select (in addStudy): " + ps); }
	       // already present?
	       while (resultSet.next()) {
	           Study c = generateStudy(resultSet, logger);
	           resultSet.close();
	           return false;
	       }

	       ps = conn.prepareStatement("INSERT INTO Study (studyId, institutionsInvolved, studyDescription, studyName, studyShortName, studyContact, studyNotes, visibility, isIrbApproved, studyStartDate, studyEndDate, lastMod, authUserId) values(?,?,?,?,?,?,?,?,?,?,?,?,?);");
	       ps.setString(1, study.studyId);
	       ps.setString(2, study.institutionsInvolved);
	       ps.setString(3, study.studyDescription);
	       ps.setString(4, study.studyName);
	       ps.setString(5, study.studyShortName);
	       ps.setString(6, study.studyContact);
	       ps.setString(7, study.studyNotes);
	       ps.setString(8, study.visibility);
	       ps.setString(9, study.isIrbApproved);
	       ps.setTimestamp(10, study.studyStartDate);
	       if(study.studyEndDate != null) {
	       	ps.setTimestamp(11, study.studyEndDate);
	       } else {
	       	ps.setTimestamp(11, null);
	       }
	       ps.setTimestamp(12, study.lastMod);
	       ps.setString(13, study.authUserId);
	       { logger.log("In addStudy p2 (BatsDao)"); }
	       { logger.log("ps for insert (in addStudy): " + ps); }
	       ps.execute();
//	       boolean test;
//	       int index = 1;
//	       for(Alternative alt: choice.alternatives) {
//	       	{logger.log(""+alt.cid+ " " + alt.aid);}
//	       	test = addAlternative(alt, index, logger);
//	       	index++;
//	       	if(!test) {return false;}
//	       }
	       return true;
	
	   } catch (Exception e) {
	       throw new Exception("Failed to insert study: " + e.getMessage());
	   }
	}
   
	 public ArrayList<Study> getAllStudies(String authUserId, LambdaLogger logger) throws Exception {
	 
		try {
		     PreparedStatement ps = conn.prepareStatement("SELECT * FROM Study where authUserId=? order by lastMod;");
		     ps.setString(1, authUserId);
		     ResultSet resultSet = ps.executeQuery();
		     ArrayList<Study> fs = new ArrayList<Study>();
		     while (resultSet.next()) {
		     	fs.add(generateStudy(resultSet, logger));
		     	{ logger.log("studies in while (in getStudies): " + fs); }
		     }
		     resultSet.close();
		     ps.close();
		     
		     { logger.log("studies after (in getStudies): " + fs); }
		     return fs;
		
		 } catch (Exception e) {
		 	e.printStackTrace();
		     throw new Exception("Failed in getting studies for the auth user: " + e.getMessage());
		 }
	}
	 
	public Data getDataForStudy(String studyId, LambdaLogger logger) throws Exception {
		 
	 try {
		 Data data = new Data();
		 data.neural = getAllNeural(studyId, logger);
		 data.facial = getAllFacial(studyId, logger);
		 data.speech = getAllSpeech(studyId, logger);
		 data.stressIndicators = getAllStressIndicators(studyId, logger);
		 data.logData = getAllLogData(studyId, logger);
		 
		 return data;
	
	 } catch (Exception e) {
	 	e.printStackTrace();
	     throw new Exception("Failed in getting data for the study: " + e.getMessage());
	 }
	}
	 
	public ArrayList<Neural> getAllNeural(String studyId, LambdaLogger logger) throws Exception {
		try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM NeuralStudy WHERE studyId = ?;");
	      ps.setString(1, studyId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in getAllNeural): " + ps); }
	      // already present?
	      ArrayList<NeuralStudy> ns = new ArrayList<NeuralStudy>();
	      while (resultSet.next()) {
	    	  ns.add(generateNeuralStudy(resultSet, logger));
	          resultSet.close();
	      }
	      ArrayList<Neural> nStudies = new ArrayList<Neural>();
	      String neuralId = "";
	      for(int i = 0; i < ns.size(); i++) {
	    	  neuralId = ns.get(i).neuralId;
		      ps = conn.prepareStatement("SELECT * FROM Neural WHERE neuralId = ?;");
		      ps.setString(1, neuralId);
		      resultSet = ps.executeQuery();
		      { logger.log("ps for select (in getAllNeural): " + ps); }
		      while (resultSet.next()) {
		    	  nStudies.add(generateNeural(resultSet, logger));
		          resultSet.close();
		      }
	      }
	      return nStudies;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to get Neural: " + e.getMessage());
	  }
	}
	
	public ArrayList<Facial> getAllFacial(String studyId, LambdaLogger logger) throws Exception {
		try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM FacialStudy WHERE studyId = ?;");
	      ps.setString(1, studyId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in getAllFacial): " + ps); }
	      // already present?
	      ArrayList<FacialStudy> fs = new ArrayList<FacialStudy>();
	      while (resultSet.next()) {
	    	  fs.add(generateFacialStudy(resultSet, logger));
	          resultSet.close();
	      }
	      ArrayList<Facial> fStudies = new ArrayList<Facial>();
	      String facialId = "";
	      for(int i = 0; i < fs.size(); i++) {
	    	  facialId = fs.get(i).facialId;
		      ps = conn.prepareStatement("SELECT * FROM Facial WHERE facialId = ?;");
		      ps.setString(1, facialId);
		      resultSet = ps.executeQuery();
		      { logger.log("ps for select (in getAllFacial): " + ps); }
		      while (resultSet.next()) {
		    	  fStudies.add(generateFacial(resultSet, logger));
		          resultSet.close();
		      }
	      }
	      return fStudies;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to get Facial: " + e.getMessage());
	  }
	}

	public ArrayList<Speech> getAllSpeech(String studyId, LambdaLogger logger) throws Exception {
		try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM SpeechStudy WHERE studyId = ?;");
	      ps.setString(1, studyId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in getAllSpeech): " + ps); }
	      // already present?
	      ArrayList<SpeechStudy> ss = new ArrayList<SpeechStudy>();
	      while (resultSet.next()) {
	    	  ss.add(generateSpeechStudy(resultSet, logger));
	          resultSet.close();
	      }
	      ArrayList<Speech> sStudies = new ArrayList<Speech>();
	      String speechId = "";
	      for(int i = 0; i < ss.size(); i++) {
	    	  speechId = ss.get(i).speechId;
		      ps = conn.prepareStatement("SELECT * FROM Speech WHERE speechId = ?;");
		      ps.setString(1, speechId);
		      resultSet = ps.executeQuery();
		      { logger.log("ps for select (in getAllSpeech): " + ps); }
		      while (resultSet.next()) {
		    	  sStudies.add(generateSpeech(resultSet, logger));
		          resultSet.close();
		      }
	      }
	      return sStudies;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to get Speech: " + e.getMessage());
	  }
	}

	public ArrayList<StressIndicators> getAllStressIndicators(String studyId, LambdaLogger logger) throws Exception {
		try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM StressIndicatorsStudy WHERE studyId = ?;");
	      ps.setString(1, studyId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in getAllStressIndicators): " + ps); }
	      // already present?
	      ArrayList<StressIndicatorsStudy> ss = new ArrayList<StressIndicatorsStudy>();
	      while (resultSet.next()) {
	    	  ss.add(generateStressIndicatorsStudy(resultSet, logger));
	          resultSet.close();
	      }
	      ArrayList<StressIndicators> sStudies = new ArrayList<StressIndicators>();
	      String siId = "";
	      for(int i = 0; i < ss.size(); i++) {
	    	  siId = ss.get(i).siId;
		      ps = conn.prepareStatement("SELECT * FROM StressIndicators WHERE siId = ?;");
		      ps.setString(1, siId);
		      resultSet = ps.executeQuery();
		      { logger.log("ps for select (in getAllStressIndicators): " + ps); }
		      while (resultSet.next()) {
		    	  sStudies.add(generateStressIndicators(resultSet, logger));
		          resultSet.close();
		      }
	      }
	      return sStudies;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to get StressIndicators: " + e.getMessage());
	  }
	}
	
	public ArrayList<LogData> getAllLogData(String studyId, LambdaLogger logger) throws Exception {
		try {
	      PreparedStatement ps = conn.prepareStatement("SELECT * FROM LogDataStudy WHERE studyId = ?;");
	      ps.setString(1, studyId);
	      ResultSet resultSet = ps.executeQuery();
	      { logger.log("ps for select (in getAllLogData): " + ps); }
	      // already present?
	      ArrayList<LogDataStudy> ls = new ArrayList<LogDataStudy>();
	      while (resultSet.next()) {
	    	  ls.add(generateLogDataStudy(resultSet, logger));
	          resultSet.close();
	      }
	      ArrayList<LogData> lStudies = new ArrayList<LogData>();
	      String logDataId = "";
	      for(int i = 0; i < ls.size(); i++) {
	    	  logDataId = ls.get(i).logDataId;
		      ps = conn.prepareStatement("SELECT * FROM LogData WHERE logDataId = ?;");
		      ps.setString(1, logDataId);
		      resultSet = ps.executeQuery();
		      { logger.log("ps for select (in getAllLogData): " + ps); }
		      while (resultSet.next()) {
		    	  lStudies.add(generateLogData(resultSet, logger));
		          resultSet.close();
		      }
	      }
	      return lStudies;
	
	  } catch (Exception e) {
	      throw new Exception("Failed to get LogData: " + e.getMessage());
	  }
	}
	
	public String getUsername(String authUserId, LambdaLogger logger) throws Exception {
		 
		try {
		     PreparedStatement ps = conn.prepareStatement("SELECT * FROM AuthUser where authUserId=?;");
		     ps.setString(1, authUserId);
		     ResultSet resultSet = ps.executeQuery();
		     AuthUser authUser = null;
		     while (resultSet.next()) {
		     	authUser = generateAuthUser(resultSet, logger);
		     	{ logger.log("authUser in while (in getUsername): " + authUser); }
		     }
		     resultSet.close();
		     ps.close();
		     
		     
		     return authUser.getUsername();
		
		 } catch (Exception e) {
		 	e.printStackTrace();
		     throw new Exception("Failed in getting username for the auth user: " + e.getMessage());
		 }
	}
	
//    public Choice getChoice(String cid, LambdaLogger logger) throws Exception {
//        
//        try {
//        	{ logger.log("in getChoice"); }
//            Choice choice = null;
//            { logger.log("choice = null"); }
//            { logger.log("con= " + conn); }
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE cid=?;");
//            ps.setString(1, cid);
//            ResultSet resultSet = ps.executeQuery();
//            
//            while (resultSet.next()) {
//                choice = generateChoice(resultSet, logger);
//                { logger.log("choice (in getChoice): " + choice.toString()); }
//            }
//            resultSet.close();
//            ps.close();
//            { logger.log("choice after while loop (in getChoice): " + choice.toString()); }
//            return choice;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting choice: " + e.getMessage());
//        }
//    }
//    
//    public Alternative getAlternative(String aid, LambdaLogger logger) throws Exception {
//        
//        try {
//        	{ logger.log("in getAlternative"); }
//            Alternative alt = null;
//            { logger.log("alt = null"); }
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Alternative WHERE aid=?;");
//            ps.setString(1, aid);
//            logger.log("Before query");
//            ResultSet resultSet = ps.executeQuery();
//            logger.log("After query, before while");
//            while (resultSet.next()) {
//            	{ logger.log("(in getAlternative) before generate: "); }
//                alt = generateAlternative(resultSet);
//                { logger.log("alt (in getAlternative): " + alt.toString()); }
//            }
//            resultSet.close();
//            ps.close();
//            return alt;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//        	logger.log("In exception");
//        	//{ logger.log("e (in getAlternative): " + e.getMessage()); }
//            throw new Exception("Failed in getting alternative: " + e.getMessage());
//        }
//    }
//    
//    public Vote getVoteFromMember(String aid, String mid, LambdaLogger logger) throws Exception {
//        
//        try {
//        	{ logger.log("in getVoteFromMember"); }
//            Vote vote = null;
//            { logger.log("vote = null"); }
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vote WHERE (aid=? and mid=?);");
//            ps.setString(1, aid);
//            ps.setString(2, mid);
//            ResultSet resultSet = ps.executeQuery();
//            
//            while (resultSet.next()) {
//                vote = generateVote(resultSet);
//                { logger.log("vote (in getVoteFromMember): " + vote.toString()); }
//            }
//            resultSet.close();
//            ps.close();
//            return vote;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting vote from member: " + e.getMessage());
//        }
//    }
//    
//    public int getNumMembers(String cid) throws Exception {
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(mid) as count FROM Member where Member.cid=?;");
//            ps.setString(1, cid);
//            ResultSet resultSet = ps.executeQuery();
//            int count = -2;
//            while (resultSet.next()) {
//                count = resultSet.getInt("count");
//            }
//            resultSet.close();
//            ps.close();
//            
//            return count;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting count of members: " + e.getMessage());
//        }
//    }
//    
//    public ArrayList<Alternative> getAltsForChoice(String cid) throws Exception {
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Alternative where cid=? order by altOrder;");
//            ps.setString(1, cid);
//            ResultSet resultSet = ps.executeQuery();
//            ArrayList<Alternative> alts = new ArrayList<Alternative>();
//            while (resultSet.next()) {
//            	alts.add(generateAlternative(resultSet));
//            }
//            resultSet.close();
//            ps.close();
//            
//            return alts;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting alternatives for choice: " + e.getMessage());
//        }
//    }
//    
//    public ArrayList<Member> getMembersForChoice(String cid) throws Exception {
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Member where cid=?;");
//            ps.setString(1, cid);
//            ResultSet resultSet = ps.executeQuery();
//            ArrayList<Member> members = new ArrayList<Member>();
//            while (resultSet.next()) {
//            	members.add(generateMember(resultSet));
//            }
//            resultSet.close();
//            ps.close();
//            
//            return members;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting alternatives for choice: " + e.getMessage());
//        }
//    }
//    
//    public ArrayList<Vote> getVotesForAlt(String aid) throws Exception {
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vote where aid=?;");
//            ps.setString(1, aid);
//            ResultSet resultSet = ps.executeQuery();
//            ArrayList<Vote> votes = new ArrayList<Vote>();
//            while (resultSet.next()) {
//            	votes.add(generateVote(resultSet));
//            }
//            resultSet.close();
//            ps.close();
//            
//            return votes;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting votes for an alternative: " + e.getMessage());
//        }
//    }
//    
//    public ArrayList<Feedback> getAllFeedbackForAlt(String aid) throws Exception {
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Feedback where aid=? order by Feedback.timestamp;");
//            ps.setString(1, aid);
//            ResultSet resultSet = ps.executeQuery();
//            ArrayList<Feedback> fs = new ArrayList<Feedback>();
//            while (resultSet.next()) {
//            	fs.add(generateFeedback(resultSet));
//            }
//            resultSet.close();
//            ps.close();
//            
//            return fs;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting votes for an alternative: " + e.getMessage());
//        }
//    }
//    
//    public String getMid(String username, String cid) throws Exception {
//        
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT mid FROM Member where (cid=? and name=?) ;");
//            ps.setString(1, cid);
//            ps.setString(2, username);
//            ResultSet resultSet = ps.executeQuery();
//            String mid = "";
//            while (resultSet.next()) {
//            	mid = resultSet.getString("mid");
//            }
//            resultSet.close();
//            ps.close();
//            
//            return mid;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            throw new Exception("Failed in getting mid from username and cid: " + e.getMessage());
//        }
//    }
//    
//    public boolean updateChoice(Choice choice, String col, Object value) throws Exception {
//        try {
//        	String query = "UPDATE Choice SET ?=? WHERE cid=?;";
//        	PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, col);
//            ps.setObject(2, value);
//            ps.setString(3, choice.cid);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//            
//            return (numAffected == 1);
//        } catch (Exception e) {
//            throw new Exception("Failed to update report: " + e.getMessage());
//        }
//    }
//	
//    public boolean deleteChoices(LambdaLogger logger, double numDays, Timestamp dateNDaysAgo) throws Exception {
//        try {
//        	{ logger.log("(in deleteChoices): "); }
//        	{ logger.log("(in deleteChoices) dateNDaysAgo: "+dateNDaysAgo); }
//        	//SELECT * FROM Choice WHERE dateOfCreation >= dateNDaysAgo
//        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE dateOfCreation <= ?;");
//            ps.setTimestamp(1, dateNDaysAgo); //given int that admin puts in for how many days old deleting should start at
//            ResultSet resultSet = ps.executeQuery();
//            Choice choice = null;
//            while (resultSet.next()) {
//            	choice = generateChoice(resultSet, logger);
//            	choice.alternatives = getAltsForChoice(choice.cid);
//            	choice.members = getMembersForChoice(choice.cid);
//            	for(int i = 0; i < choice.alternatives.size();i++) {
//            		choice.alternatives.get(i).feedback = getAllFeedbackForAlt(choice.alternatives.get(i).aid);
//            		choice.alternatives.get(i).votes = getVotesForAlt(choice.alternatives.get(i).aid);
//            	}
//            	deleteChoice(choice, logger);
//            }
//            resultSet.close();
//            ps.close();
//            
//            return true;
//        	
//        	//PreparedStatement ps = conn.prepareStatement("DELETE FROM Choice WHERE cid = ?;");
////            PreparedStatement ps = conn.prepareStatement("DELETE FROM Choice WHERE (CURRENT_TIMESTAMP() - dateOfCreation) > INTERVAL ? DAYS;");
////
////            ps.setDouble(1, numDays); //given int that admin puts in for how many days old deleting should start at
////            
////            ps.executeUpdate();
////            ps.close();
////            return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete choices: " + e.getMessage());
//        }
//    }
//    
//    public boolean deleteChoice(Choice choice, LambdaLogger logger) throws Exception {
//        try {
//        	//call deleteAlternatives(Choice choice) here
//        	deleteFeedback(choice, logger);
//        	deleteVotes(choice, logger);
//        	deleteAlternatives(choice, logger);
//        	deleteMembers(choice, logger);
//            PreparedStatement ps = conn.prepareStatement("DELETE FROM Choice WHERE cid = ?;");
//            ps.setString(1, choice.cid);
//            int numAffected = ps.executeUpdate();
//            ps.close();
//            
//            return (numAffected == 1);
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete choice: " + e.getMessage());
//        }
//    }
//    
//    public boolean deleteFeedback(Choice choice, LambdaLogger logger) throws Exception {
//        try {
//        	PreparedStatement ps;
//        	int numAffected = -1;
//        	//call deleteAlternatives(Choice choice) here
//        	for(int i = 0; i < choice.alternatives.size(); i++) {
//        		for(int f = 0; f < choice.alternatives.get(i).feedback.size(); f++) {
//        			{ logger.log("(in deleteFeedback) fid: "+choice.alternatives.get(i).feedback.get(f).fid); }
//        			ps = conn.prepareStatement("DELETE FROM Feedback WHERE fid = ?;");
//                    ps.setString(1, choice.alternatives.get(i).feedback.get(f).fid);
//                    numAffected = ps.executeUpdate();
//                    ps.close();
//        		}
//        		return (numAffected == choice.alternatives.get(i).feedback.size());
//        	}
//           return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete feedback: " + e.getMessage());
//        }
//    }
//    
//    public boolean deleteVotes(Choice choice, LambdaLogger logger) throws Exception {
//        try {
//        	PreparedStatement ps;
//        	int numAffected = -1;
//        	//call deleteAlternatives(Choice choice) here
//        	for(int i = 0; i < choice.alternatives.size(); i++) {
//        		for(int f = 0; f < choice.alternatives.get(i).votes.size(); f++) {
//        			{ logger.log("(in deleteVotes) vid: "+choice.alternatives.get(i).votes.get(f).vid); }
//        			ps = conn.prepareStatement("DELETE FROM Vote WHERE vid = ?;");
//                    ps.setString(1, choice.alternatives.get(i).votes.get(f).vid);
//                    numAffected = ps.executeUpdate();
//                    ps.close();
//        		}
//        		return (numAffected == choice.alternatives.get(i).votes.size());
//        	}
//           return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete votes: " + e.getMessage());
//        }
//    }
//    
//    public boolean deleteAlternatives(Choice choice, LambdaLogger logger) throws Exception {
//        try {
//        	PreparedStatement ps;
//        	int numAffected = -1;
//        	//call deleteAlternatives(Choice choice) here
//        	for(int i = 0; i < choice.alternatives.size(); i++) {
//        		{ logger.log("(in deleteAlternatives) aid: "+choice.alternatives.get(i).aid); }
//        		ps = conn.prepareStatement("DELETE FROM Alternative WHERE aid = ?;");
//                ps.setString(1, choice.alternatives.get(i).aid);
//                numAffected = ps.executeUpdate();
//                ps.close();
//        	}
//            
//            return (numAffected == choice.alternatives.size());
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete alternatives: " + e.getMessage());
//        }
//    }
//    
//    public boolean deleteMembers(Choice choice, LambdaLogger logger) throws Exception {
//        try {
//        	PreparedStatement ps;
//        	int numAffected = -1;
//        	//call deleteAlternatives(Choice choice) here
//        	for(int i = 0; i < choice.members.size(); i++) {
//        		{ logger.log("(in deleteMembers) mid: "+choice.members.get(i).mid); }
//        		ps = conn.prepareStatement("DELETE FROM Member WHERE mid = ?;");
//                ps.setString(1, choice.members.get(i).mid);
//                numAffected = ps.executeUpdate();
//                ps.close();
//        	}
//            
//            return (numAffected == choice.members.size());
//
//        } catch (Exception e) {
//            throw new Exception("Failed to delete members: " + e.getMessage());
//        }
//    }
//
//    public boolean addChoice(Choice choice, LambdaLogger logger) throws Exception { //Create Choice
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE cid = ?;");
//            ps.setString(1, choice.cid);
//            ResultSet resultSet = ps.executeQuery();
//            { logger.log("ps for select (in addChoice): " + ps); }
//            // already present?
//            while (resultSet.next()) {
//                Choice c = generateChoice(resultSet, logger);
//                resultSet.close();
//                return false;
//            }
//            logger.log("choice.numMembers in addChoice before insert: " + choice.numMembers);
//            ps = conn.prepareStatement("INSERT INTO Choice (cid, chosenAid, description, numMembers, dateOfCreation, dateOfCompletion) values(?,?,?,?,?,?);");
//            ps.setString(1, choice.cid);
//            ps.setString(2, choice.chosenAid);
//            ps.setString(3, choice.description);
//            ps.setInt(4, choice.numMembers);
//            ps.setTimestamp(5, Timestamp.valueOf(choice.dateOfCreation));
//            if(choice.dateOfCompletion != null) {
//            	ps.setTimestamp(6, Timestamp.valueOf(choice.dateOfCompletion));
//            } else {
//            	ps.setTimestamp(6, null);
//            }
//            { logger.log("ps for insert (in addChoice): " + ps); }
//            ps.execute();
//            boolean test;
//            int index = 1;
//            for(Alternative alt: choice.alternatives) {
//            	{logger.log(""+alt.cid+ " " + alt.aid);}
//            	test = addAlternative(alt, index, logger);
//            	index++;
//            	if(!test) {return false;}
//            }
//            return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert choice: " + e.getMessage());
//        }
//    }
//    
//    public boolean addAlternative(Alternative alt, int index, LambdaLogger logger) throws Exception { //Create Alternative
//        try {
//            /*PreparedStatement ps = conn.prepareStatement("SELECT * FROM Alternative WHERE aid = ?;");
//            ps.setString(1, alt.aid);
//            ResultSet resultSet = ps.executeQuery();
//            { logger.log("ps for select (in addAlternative): " + ps); }
//            // already present?
//            while (resultSet.next()) {
//                Alternative a = generateAlternative(resultSet);
//                resultSet.close();
//                { logger.log("select (in addAlternative) result: " + ps); }
//                return false;
//            }*/
//        	{ logger.log("(in addAlternative) : "); 
//            PreparedStatement ps = conn.prepareStatement("INSERT INTO Alternative (aid, cid, description, altOrder) values (?,?,?,?);");
//            { logger.log("(in addAlternative)alt.aid: " + alt.aid); }
//            { logger.log("(in addAlternative)alt.cid: " + alt.cid); }
//            { logger.log("(in addAlternative)alt.des: " + alt.description); }
//            { logger.log("(in addAlternative)alt.index: " + index); }
//            ps.setString(1, alt.aid);
//            ps.setString(2, alt.cid);
//            ps.setString(3, alt.description);
//            { logger.log("(in addAlternative)alt.aid: " + alt.aid); }
//            ps.setInt(4, index);
//            { logger.log("(in addAlternative)alt.aid: " + alt.aid); }
//            ps.execute();
//            { logger.log("ps for insert (in addAlternative): " + ps); }
//            return true;
//
//        }} catch (Exception e) {
//        	 { logger.log("(in addAlternative) e: "+e.getMessage()); }
//            throw new Exception("Failed to insert alternative: " + e.getMessage());
//        }
//    }
//    
//    public boolean addMember(Member member, LambdaLogger logger)throws Exception { //Create Member
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Member WHERE mid = ?;");
//            ps.setString(1, member.mid);
//            ResultSet resultSet = ps.executeQuery();
//            
//            // already present?
//            while (resultSet.next()) {
//                Member m = generateMember(resultSet);
//                resultSet.close();
//                return false;
//            }
//            { logger.log("(in addMember): "); }
//            ps = conn.prepareStatement("INSERT INTO Member (mid, cid, name, password) values(?,?,?,?);");
//            ps.setString(1, member.mid);
//            ps.setString(2, member.cid);
//            ps.setString(3, member.name);
//            ps.setString(4, member.password);
//            { logger.log("ps for insert (in addMember): " + ps); }
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert member: " + e.getMessage());
//        }
//    }
//    
//    public boolean addVote(Vote vote, LambdaLogger logger)throws Exception { //Create Vote
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Vote WHERE vid = ?;");
//            ps.setString(1, vote.vid);
//            ResultSet resultSet = ps.executeQuery();
//            
//            // already present?
//            while (resultSet.next()) {
//                Vote m = generateVote(resultSet);
//                resultSet.close();
//                return false;
//            }
//            { logger.log("(in addVote): "); }
//            ps = conn.prepareStatement("INSERT INTO Vote (vid, mid, username, aid, kind) values(?,?,?,?,?);");
//            ps.setString(1, vote.vid);
//            ps.setString(2, vote.mid);
//            ps.setString(3, vote.username);
//            ps.setString(4, vote.aid);
//            ps.setString(5, vote.kind);
//            { logger.log("ps for insert (in addVote): " + ps); }
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//        	{ logger.log("e (in addVote): " + e.getMessage()); }
//            throw new Exception("Failed to insert vote: " + e.getMessage());
//        }
//    }
//    
//    public boolean addFeedback(Feedback feedback, LambdaLogger logger)throws Exception { //Create Feedback
//        try {
//            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Feedback WHERE fid = ?;");
//            ps.setString(1, feedback.fid);
//            ResultSet resultSet = ps.executeQuery();
//            
//            // already present?
//            while (resultSet.next()) {
//                Feedback m = generateFeedback(resultSet);
//                resultSet.close();
//                return false;
//            }
//            { logger.log("(in addFeedback): "); }
//            ps = conn.prepareStatement("INSERT INTO Feedback (fid, timestamp, content, mid, username, aid) values(?,?,?,?,?,?);");
//            ps.setString(1, feedback.fid);
//            ps.setTimestamp(2, feedback.timestamp);
//            ps.setString(3, feedback.content);
//            ps.setString(4, feedback.mid);
//            ps.setString(5, feedback.username);
//            ps.setString(6, feedback.aid);
//            { logger.log("ps for insert (in addFeedback): " + ps); }
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            throw new Exception("Failed to insert feedback: " + e.getMessage());
//        }
//    }
//    
//    public boolean removeVote(String aid, String mid, LambdaLogger logger)throws Exception { //Remove Vote
//        try {
//            { logger.log("(in removeVote): "); }
//            PreparedStatement ps = conn.prepareStatement("Delete from Vote where (aid=? and mid=?);");
//            ps.setString(1, aid);
//            ps.setString(2, mid);
//            { logger.log("ps for delete (in removeVote): " + ps); }
//            ps.execute();
//            return true;
//        } catch (Exception e) {
//            throw new Exception("Failed to insert vote: " + e.getMessage());
//        }
//    }
//    
//    public boolean chooseAlternative(Choice choice, String aid, LambdaLogger logger)throws Exception { //update chosenAid
//        try {
//        	{ logger.log("(in chooseAlternative): "); }
//        	PreparedStatement ps = conn.prepareStatement("Update Choice set chosenAid=? where cid=?;");
//            ps.setString(1, aid);
//            ps.setNString(2, choice.cid);
//            { logger.log("ps for update (in chooseAlternative): " + ps); }
//            ps.execute();
//            
//            ps = conn.prepareStatement("Update Choice set dateOfCompletion=? where cid=?;");
//            ps.setTimestamp(1, Timestamp.valueOf(choice.dateOfCompletion));
//            ps.setNString(2, choice.cid);
//            { logger.log("ps for update 2(in chooseAlternative): " + ps); }
//            ps.execute();
//            
//            return true;
//        } catch (Exception e) {
//            throw new Exception("Failed to update chosenAid: " + e.getMessage());
//        }
//    }
//    
//    public boolean hasChooseAlternative(String cid, String aid, LambdaLogger logger)throws Exception { //update chosenAid
//        try {
//        	{ logger.log("(in chooseAlternative1): "); }
//        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM Choice WHERE cid = ?;");
//            ps.setString(1, cid);
//            ResultSet resultSet = ps.executeQuery();
//            { logger.log("ps for select: " + ps); }
//            // already present?
//            String ca = "";
//            Choice c = null;
//            while (resultSet.next()) {
//            	ca = resultSet.getString("chosenAid");
//            	resultSet.close();
//            	{ logger.log("chosenAid from select: " + ca); }
//            	{ logger.log("ca != null: " + (ca != null)); }
//                if(ca == null) {
//                	{ logger.log("in if"); }
//                	return false;
//                }else {
//                	return true;
//                }
//            }
//            return true;
//        	
//        } catch (Exception e) {
//            throw new Exception("Failed to check chosenAid: " + e.getMessage());
//        }
//    }
//
//    public ArrayList<Choice> getAllChoices(LambdaLogger logger) throws Exception {
//        
//        ArrayList<Choice> allChoices = new ArrayList<>();
//        try {
//            Statement statement = conn.createStatement();
//            String query = "SELECT * FROM Choice;";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            while (resultSet.next()) {
//                Choice c = generateChoice(resultSet, logger);
//                { logger.log("after generateChoice"); }
//                allChoices.add(c);
//            }
//            resultSet.close();
//            statement.close();
//            return allChoices;
//
//        } catch (Exception e) {
//            throw new Exception("Failed in getting choices: " + e.getMessage());
//        }
//    }
//    
//    public ArrayList<Member> getChoiceMembers(String cid) throws Exception {
//        
//        ArrayList<Member> members = new ArrayList<>();
//        try {
//        	PreparedStatement ps = conn.prepareStatement("SELECT * FROM Member WHERE cid = ?;");
//            ps.setString(1, cid);
//            ResultSet resultSet = ps.executeQuery();
//
//            while (resultSet.next()) {
//                Member m = generateMember(resultSet);
//                members.add(m);
//            }
//            resultSet.close();
//            ps.close();
//            return members;
//
//        } catch (Exception e) {
//            throw new Exception("Failed in getting choices: " + e.getMessage());
//        }
//    }
//    
//    private Choice generateChoice(ResultSet resultSet,LambdaLogger logger) throws Exception {
//        String cid  = resultSet.getString("cid");
//        String chosenAid = resultSet.getString("chosenAid");
//        String description = resultSet.getNString("description");
//        int numMembers = resultSet.getInt("numMembers");
//        String dateOfCreation = resultSet.getTimestamp("dateOfCreation").toString();
//        { logger.log("dateOfCreation: " + dateOfCreation); }
//        String dateOfCompletion = null;
//        if(resultSet.getTimestamp("dateOfCompletion") != null) {
//        	dateOfCompletion = resultSet.getTimestamp("dateOfCompletion").toString();
//        }
//        { logger.log("dateOfCompletion: " + dateOfCompletion); }
//        return new Choice(cid, chosenAid, description, numMembers, dateOfCreation, dateOfCompletion);
//    }
//    
//    private Member generateMember(ResultSet resultSet) throws Exception {
//    	String mid = resultSet.getString("mid");
//    	String cid = resultSet.getString("cid");
//    	String name = resultSet.getString("name");
//    	String password = resultSet.getString("password");
//    	return new Member(mid, cid, name, password);
//    }
//    
//    private Alternative generateAlternative(ResultSet resultSet) throws Exception {
//    	System.out.println("in generateAlternative 1 ");
//    	String aid = resultSet.getString("aid");
//    	String cid = resultSet.getString("cid");
//    	String description = resultSet.getString("description");
//    	//int index = resultSet.getInt("index");
//    	System.out.println("in generateAlternative 2 ");
//    	return new Alternative(aid, cid, description);
//    }
//    
//    private Vote generateVote(ResultSet resultSet) throws Exception {
//    	String vid = resultSet.getString("vid");
//    	String aid = resultSet.getString("aid");
//    	String mid = resultSet.getString("mid");
//    	String kind = resultSet.getString("kind");
//    	String username = resultSet.getString("username");
//    	return new Vote(vid, mid, aid, kind, username);
//    }
//    
//    private Feedback generateFeedback(ResultSet resultSet) throws Exception {
//    	String fid = resultSet.getString("fid");
//    	String aid = resultSet.getString("aid");
//    	String mid = resultSet.getString("mid");
//    	String content = resultSet.getString("content");
//    	Timestamp timestamp = resultSet.getTimestamp("timestamp");
//    	String username = resultSet.getString("username");
//    	return new Feedback(fid, mid, aid, content, timestamp, username);
//    }
    
	private AuthUser generateAuthUser(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String authUserId  = resultSet.getString("authUserId");
	  String firstName = resultSet.getString("firstName");
	  String lastName = resultSet.getString("lastName");
	  String username = resultSet.getString("username");
	  String pass = resultSet.getString("pass");
	  String email = resultSet.getString("email");
	  return new AuthUser(authUserId, firstName, lastName, username, pass, email);
	}
	private Document generateDocument(ResultSet resultSet,LambdaLogger logger) throws Exception {
		  String documentId  = resultSet.getString("documentId");
		  String file = resultSet.getString("file");
		  String name = resultSet.getString("name");
		  String dataType = resultSet.getString("dataType");
		  return new Document(documentId, file, name, dataType);
		}
  private Study generateStudy(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String studyId  = resultSet.getString("studyId");
	  String institutionsInvolved = resultSet.getString("institutionsInvolved");
	  String studyDescription = resultSet.getNString("studyDescription");
	  String studyName = resultSet.getString("studyName");
	  String studyShortName = resultSet.getString("studyShortName");
	  String studyContact = resultSet.getString("studyContact");
	  String studyNotes = resultSet.getString("studyNotes");
	  String visibility = resultSet.getString("visibility");
	  String isIrbApproved = resultSet.getString("isIrbApproved");
	  Timestamp studyStartDate = resultSet.getTimestamp("studyStartDate");
	  { logger.log("studyStartDate: " + studyStartDate); }
	  Timestamp studyEndDate = null;
	  if(resultSet.getTimestamp("studyEndDate") != null) {
		  studyEndDate = resultSet.getTimestamp("studyEndDate");
	  }
	  { logger.log("studyEndDate: " + studyEndDate); }
	  Timestamp lastMod = resultSet.getTimestamp("lastMod");
	  { logger.log("lastMod: " + lastMod); }
	  String authUserId = resultSet.getString("authUserId");
	  return new Study(studyId, institutionsInvolved, studyDescription, studyName, studyShortName, studyContact, studyNotes, visibility, isIrbApproved, studyStartDate, studyEndDate, lastMod, authUserId); 
  }
  private Neural generateNeural(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String neuralId  = resultSet.getString("neuralId");
	  Timestamp time = resultSet.getTimestamp("time");
	  { logger.log("time: " + time); }
	  String timeIncr = resultSet.getString("timeIncr");
	  String SR = resultSet.getString("SR");
	  String logMarker = resultSet.getString("logMarker");
	  String logQuestion = resultSet.getString("logQuestion");
	  String logAnswer = resultSet.getString("logAnswer");
	  String participantId = resultSet.getString("participantId");
	  return new Neural(neuralId, time, timeIncr, SR, logMarker, logQuestion, logAnswer, participantId); 
  }
  private Facial generateFacial(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String facialId  = resultSet.getString("facialId");
	  Timestamp time = resultSet.getTimestamp("time");
	  { logger.log("time: " + time); }
	  String timeIncr = resultSet.getString("timeIncr");
	  String SR = resultSet.getString("SR");
	  String video = resultSet.getString("video");
	  double facialPositionQuantification = resultSet.getDouble("facialPositionQuantification");
	  String participantId = resultSet.getString("participantId");
	  return new Facial(facialId, time, timeIncr, SR, video, facialPositionQuantification, participantId); 
  }
  private Speech generateSpeech(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String speechId  = resultSet.getString("speechId");
	  Timestamp time = resultSet.getTimestamp("time");
	  { logger.log("time: " + time); }
	  String timeIncr = resultSet.getString("timeIncr");
	  String SR = resultSet.getString("SR");
	  String waveforms = resultSet.getString("waveforms");
	  String participantId = resultSet.getString("participantId");
	  return new Speech(speechId, time, timeIncr, SR, waveforms, participantId); 
  }
  private StressIndicators generateStressIndicators(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String siId  = resultSet.getString("siId");
	  Timestamp time = resultSet.getTimestamp("time");
	  { logger.log("time: " + time); }
	  String timeIncr = resultSet.getString("timeIncr");
	  String SR = resultSet.getString("SR");
	  String heartbeatWaveform = resultSet.getString("heartbeatWaveform");
	  double bloodPressure = resultSet.getDouble("bloodPressure");
	  String participantId = resultSet.getString("participantId");
	  return new StressIndicators(siId, time, timeIncr, SR, heartbeatWaveform, bloodPressure, participantId); 
  }
  private LogData generateLogData(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String logDataId  = resultSet.getString("logDataId");
	  double changeOfSeverityOverTime = resultSet.getDouble("changeOfSeverityOverTime");
	  String question = resultSet.getString("question");
	  String answer = resultSet.getString("answer");
	  Timestamp startOfSymptoms = resultSet.getTimestamp("startOfSymptoms");
	  { logger.log("startOfSymptoms: " + startOfSymptoms); }
	  String participantId = resultSet.getString("participantId");
	  return new LogData(logDataId, changeOfSeverityOverTime, question, answer, startOfSymptoms, participantId); 
  }
  private NeuralStudy generateNeuralStudy(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String neuralId  = resultSet.getString("neuralId");
	  String studyId = resultSet.getString("studyId");
	  return new NeuralStudy(neuralId, studyId); 
  }
  private FacialStudy generateFacialStudy(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String facialId  = resultSet.getString("facialId");
	  String studyId = resultSet.getString("studyId");
	  return new FacialStudy(facialId, studyId); 
  }
  private SpeechStudy generateSpeechStudy(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String speechId  = resultSet.getString("speechId");
	  String studyId = resultSet.getString("studyId");
	  return new SpeechStudy(speechId, studyId); 
  }
  private StressIndicatorsStudy generateStressIndicatorsStudy(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String siId  = resultSet.getString("siId");
	  String studyId = resultSet.getString("studyId");
	  return new StressIndicatorsStudy(siId, studyId); 
  }
  private LogDataStudy generateLogDataStudy(ResultSet resultSet,LambdaLogger logger) throws Exception {
	  String logDataId  = resultSet.getString("logDataId");
	  String studyId = resultSet.getString("studyId");
	  return new LogDataStudy(logDataId, studyId); 
  }

}
