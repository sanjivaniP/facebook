package edu.iiitb.facebook.model;


import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iiitb.facebook.action.LoginAction;
import edu.iiitb.facebook.util.DB;
import edu.iiitb.facebook.util.MyLog;


public class Login {
	
	public static String checkValidity(String emailId, String password) {
		int flag=0;
		ResultSet resultSet = null;
		String status = null;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		try {
		String query = "select * from Login where emailId=?";
		stmt = connection.prepareStatement(query);
		stmt.setString(1, emailId);
	  
		resultSet = stmt.executeQuery();
        if (resultSet.next()) {
				////System.out.println("inside fineone last::::"+logAction.getLastLoggedIn());
				flag=1;
				
			}
        else
        	{status = "invalid email";}
			if(flag==1){
				String query2 = "select password from Login where emailId=?";
				stmt = connection.prepareStatement(query2);
				stmt.setString(1, emailId);
			    resultSet = stmt.executeQuery();
		        if (resultSet.next()) {
						////System.out.println("inside fineone last::::"+logAction.getLastLoggedIn());
		        	if(password.equals(resultSet.getString("password")))
		        	{
		        		status = "valid user";
					}
		        	else
		        		status = "wrong password";
		        }
		       
				
			}
		} catch (SQLException e) {
          e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return status;
		
	}
	public static String checkValidityByUsername(String username, String password) {
		int flag=0;
		ResultSet resultSet = null;
		String status = null;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		try {
		String query = "select * from Login where userName=?";
		stmt = connection.prepareStatement(query);
		stmt.setString(1, username);
	  
		resultSet = stmt.executeQuery();
        if (resultSet.next()) {
				////System.out.println("inside fineone last::::"+logAction.getLastLoggedIn());
				flag=1;
				
			}
        else
        	{status = "invalid username";}
			if(flag==1){
				String query2 = "select password from Login where userName=?";
				stmt = connection.prepareStatement(query2);
				stmt.setString(1, username);
			    resultSet = stmt.executeQuery();
		        if (resultSet.next()) {
						////System.out.println("inside fineone last::::"+logAction.getLastLoggedIn());
		        	if(password.equals(resultSet.getString("password")))
		        	{
		        		status = "valid user";
					}
		        	else
		        		status = "wrong password";
		        }
		       
				
			}
		} catch (SQLException e) {
          e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return status;
		
	}
	
	public static String checkValidity(long phoneNo, String password) {
		int flag=0;
		ResultSet resultSet = null;
	
		String status = null;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		
		try {
		String query = "select * from Login where phoneNumber=?";
		stmt = connection.prepareStatement(query);
		stmt.setLong(1, phoneNo);
	  
		resultSet = stmt.executeQuery();
        if (resultSet.next()) {
				////System.out.println("inside fineone last::::"+logAction.getLastLoggedIn());
				flag=1;
				
			}
        else
    	{status = "invalid phone number";}
	
			if(flag==1){
				String query2 = "select password from Login where phoneNumber=?";
				
				stmt = connection.prepareStatement(query2);
				stmt.setLong(1, phoneNo);
			    resultSet = stmt.executeQuery();
		        if (resultSet.next()) {
						////System.out.println("inside fineone last::::"+logAction.getLastLoggedIn());
		        	if(password.equals(resultSet.getString("password")))
		        	{
		        		status = "valid user";
		        		
					}
		        	else
		        		status = "wrong password";
		        	
			}
		        
		       //System.out.println(status);
				
			}
		} catch (SQLException e) {
          e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return status;
		
	}
	public static String getEmailIdByPhone(long phoneNo){
		PreparedStatement stmt3=null;
		ResultSet resultSet3 = null;
		LoginAction loginAction = new LoginAction();
		Connection connection = DB.getConnection();
		String query3 = "select emailId from Login where phoneNumber=?";
		try{
		stmt3 = connection.prepareStatement(query3);
		stmt3.setLong(1, phoneNo);
	    resultSet3 = stmt3.executeQuery();
        if (resultSet3.next()) {
        	
        	loginAction.setEmailId(resultSet3.getString("emailId"));
        }
        }
		catch(Exception e){
			e.printStackTrace();
		}
		
        return loginAction.getEmailId();
	}
	public static int getProfileIdByEmail(String emailId){
		PreparedStatement stmt4=null;
		ResultSet resultSet4 = null;
		LoginAction loginAction2 = new LoginAction();
		Connection connection = DB.getConnection();
		String query4 = "select profileId from Profile p, Login where p.loginId=(select loginId from Login where emailId=?)";
		try{
		stmt4 = connection.prepareStatement(query4);
		stmt4.setString(1, emailId);
	    resultSet4 = stmt4.executeQuery();
        if (resultSet4.next()) {
        	
        	loginAction2.setProfileId(resultSet4.getInt("profileId"));
        }
        }
		catch(Exception e){
			e.printStackTrace();
		}
		return loginAction2.getProfileId();
	}
	public static int getProfileIdByUsername(String username){
		PreparedStatement stmt4=null;
		ResultSet resultSet4 = null;
		LoginAction loginAction2 = new LoginAction();
		Connection connection = DB.getConnection();
		String query4 = "select profileId from Profile p, Login where p.loginId=(select loginId from Login where userName=?)";
		try{
		stmt4 = connection.prepareStatement(query4);
		stmt4.setString(1, username);
	    resultSet4 = stmt4.executeQuery();
        if (resultSet4.next()) {
        	
        	loginAction2.setProfileId(resultSet4.getInt("profileId"));
        }
        }
		catch(Exception e){
			e.printStackTrace();
		}
		return loginAction2.getProfileId();
	}
}
