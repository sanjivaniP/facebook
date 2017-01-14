package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.iiitb.facebook.util.DB;

public class ForgotPasswordDao {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Date date = new Date();
	public static Profile getIdentity(String emailId){
		Profile user = new Profile();
		ResultSet resultSet = null;
		int flag = 0;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		try {
			String queryForCheck = "select * from Login where emailId = ?";
			stmt = connection.prepareStatement(queryForCheck);
			stmt.setString(1, emailId);

			resultSet = stmt.executeQuery();
	        if (resultSet.next()) {
	        	flag = 1;
	        }
	     if(flag==1){
		String query = "select profilePicId, firstName, lastName, loginId from Profile  where loginId="+
		"(select loginId from Login where emailId = ?)";
		stmt = connection.prepareStatement(query);
		stmt.setString(1, emailId);

		resultSet = stmt.executeQuery();
        if (resultSet.next()) {
        	
        	user.setFirstName(resultSet.getString("firstName"));
        	user.setLastName(resultSet.getString("lastName"));
        	user.setLoginId(resultSet.getInt("loginId"));
        	user.setProfilePicId(resultSet.getInt("profilePicId"));
        	
        }
	     }
	     else
	    	 user = null;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	public static String getUserIdentity(String username){
		ResultSet resultSet = null;
		String emailId = null;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		try {
			String queryForCheck = "select emailId from Login where username = ?";
			stmt = connection.prepareStatement(queryForCheck);
			stmt.setString(1, username);

			resultSet = stmt.executeQuery();
	        if (resultSet.next()) {
	        emailId = resultSet.getString("emailId");
	        }
	       		}
		catch(Exception e){
			e.printStackTrace();
		}
		return emailId;
	}
	public static String getIdentity(long phoneNo){
		ResultSet resultSet = null;
		String emailId = null;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		try {
			String queryForCheck = "select emailId from Login where phoneNumber = ?";
			stmt = connection.prepareStatement(queryForCheck);
			stmt.setLong(1, phoneNo);

			resultSet = stmt.executeQuery();
	        if (resultSet.next()) {
	        	emailId = resultSet.getString("emailId");
	         }
	       
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return emailId;
	}
	public String storeSecureCode(int loginId, int code){
		Statement stmt=null;
		try{
			 Connection con=DB.getConnection();
			 stmt = con.createStatement();
		/*	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date date = new Date();*/
			 System.out.println(dateFormat.format(date));
			String query = "insert into ForgotPasswordCode(loginId, code, time) values ("+loginId+","+code+",'"+dateFormat.format(date)+"')";
		 stmt.executeUpdate(query); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	// code for reset password
	
	public static String checkCode(int loginId, int enteredCode){
		int maxId = 0;
		int storedCode = 0;
		
		ArrayList<Integer> listOfCodeId = new ArrayList<Integer>();
		ResultSet resultSet = null;
		String status = null;
		Connection connection = DB.getConnection();
		PreparedStatement stmt=null;
		try {
		String query = "select * from ForgotPasswordCode where loginId=?";
		stmt = connection.prepareStatement(query);
		stmt.setInt(1, loginId);

		resultSet = stmt.executeQuery();
        while(resultSet.next()) {
        	
        	listOfCodeId.add(resultSet.getInt("forgotPassowrdCodeId"));
        	
        }
        
        	for (Integer codeId : listOfCodeId) {
            if(maxId < codeId)
            	maxId = codeId;
        	//System.out.println(item);
        	}
        
        String queryForCode = "select code from ForgotPasswordCode where forgotPassowrdCodeId=?";
		stmt = connection.prepareStatement(queryForCode);
		stmt.setInt(1, maxId);

		resultSet = stmt.executeQuery();
        if(resultSet.next()) {
        	 storedCode = resultSet.getInt("code");
        }
        if(storedCode == enteredCode)
        {	status = "code matched";
        System.out.println("status"+status);
        }
        else 
        	status = "code does not match";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}
	public String resetPassword(String newPassword, int loginId){
		Statement stmt=null;
		String status = null;
		try{
		    Connection con=DB.getConnection();
			 stmt = con.createStatement();
			 
			 System.out.println(dateFormat.format(date));
			String query = "update Login set password='"+newPassword+"' where loginId="+loginId;
		 stmt.executeUpdate(query); 
		 query = "insert into ResetPassword(loginId, password, time) values("+loginId+",'"+newPassword+"','"+dateFormat.format(date)+"')";
		 stmt.executeUpdate(query); 
		}
		catch(Exception e){
			e.printStackTrace();
		}
		status = "password reset successfully"; 
		return status;
	}

}
