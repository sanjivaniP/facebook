package edu.iiitb.facebook.model;

import java.sql.Connection;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iiitb.facebook.util.DB;

public class statusModel {
	public static int addStatus(String status,String timestamp, int profileId) {
		//insert into userStatus Table
		String insertSQL = "insert into UserStatus "
				+ "(description, updateTime, profileId) " + "values('" + status
				+ "', '" + timestamp + "', " + profileId +");";
		DB.update(insertSQL);
		//System.out.println(insertSQL);
		//find out the status id of the added record
		int statusid=findStatusId(status,profileId,timestamp);
		//System.out.println("statusid:::"+statusid);
		//insert into post Table
		String insertSQL1 = "insert into Post "
				+ "(owner,type,time,statusId) " + "values("+profileId+",'text','"+timestamp+"',"+statusid+");";
		//System.out.println(insertSQL1);
		return DB.update(insertSQL1);
	}
	
	public static int findStatusId(String status,int profileId,String timestamp){
		ResultSet resultSet = null;
		int statusid=0;;
		//System.out.println("status in findstatusid:::"+status);
		//System.out.println("profile id in findid::::"+profileId);
		//System.out.println("timestamp in find id ::::"+timestamp);
		String query = "select userStatusId from UserStatus where description ='"+status+"' and profileId ="+profileId+" and updateTime='"+timestamp+"';";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		
			try {
				if (resultSet.next()) {
						statusid=resultSet.getInt("userStatusId");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		return statusid;
	}

}
