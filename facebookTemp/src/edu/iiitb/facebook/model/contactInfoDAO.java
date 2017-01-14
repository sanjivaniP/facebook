package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iiitb.facebook.action.basicInfoAction;
import edu.iiitb.facebook.action.contactInfoAction;
import edu.iiitb.facebook.util.DB;

public class contactInfoDAO {
	
	
	public static contactInfoAction getContactInfo(int profileId) {
		contactInfoAction contactInfo=new contactInfoAction();
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		
		String query = "select loginId from Profile where profileId="+profileId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				String query1 = "select emailId,phoneNumber,userName from Login where loginId="+resultSet.getInt("loginId")+";";
				resultSet1 = DB.readFromDB(query1, connection);
				while (resultSet1.next()) {
					contactInfo.setEmailId(resultSet1.getString("emailId"));
					contactInfo.setPhoneNumber(resultSet1.getString("phoneNumber"));
					contactInfo.setUserName(resultSet1.getString("userName"));
					
				}
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return contactInfo;
	}
	
	
public static int updatePhoneNumber(String phoneNumber, int profileId) {
		
		String updateSQL;
		if(phoneNumber!=null)
		{
		updateSQL = 
			    "update Login "
				+ "set phoneNumber = '" +phoneNumber+"' where loginId = (select loginId from Profile where profileId= "+profileId+");"; 
		}
		else
		{
			updateSQL = 
				    "update Login "
					+ "set phoneNumber = NULL where loginId = (select loginId from Profile where profileId= "+profileId+");"; 
				
		}
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}

	
	

}
