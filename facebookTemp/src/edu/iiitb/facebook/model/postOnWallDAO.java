package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iiitb.facebook.util.DB;

public class postOnWallDAO {
	
	public static int addPostOnWall(int userId1, int userId2,String timestamp, String description) {
		//insert into userStatus Table
		String insertSQL = "insert into PostOnWall "
				+ "(time,description,fromId,toId) " + "values('" + timestamp
				+ "', '" + description + "', " + userId1 +","+userId2+");";
		DB.update(insertSQL);
		//System.out.println(insertSQL);
		//find out the status id of the added record
		int postonwallid=findPostOnWallId(userId1,userId2,timestamp,description);
		//System.out.println("statusid:::"+statusid);
		//insert into post Table
		String insertSQL1 = "insert into Post "
				+ "(owner,type,time,postOnWallId) " + "values("+userId2+",'postOnWall','"+timestamp+"',"+postonwallid+");";
		//System.out.println(insertSQL1);
		return DB.update(insertSQL1);
	}
	
	public static int findPostOnWallId(int userId1, int userId2,String timestamp, String description){
		ResultSet resultSet = null;
		int statusid=0;;
		//System.out.println("status in findstatusid:::"+status);
		//System.out.println("profile id in findid::::"+profileId);
		//System.out.println("timestamp in find id ::::"+timestamp);
		String query = "select postOnWallId from PostOnWall where description ='"+description+"' and fromId ="+userId1+" and time='"+timestamp+"';";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		
			try {
				if (resultSet.next()) {
						statusid=resultSet.getInt("postOnWallId");
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
