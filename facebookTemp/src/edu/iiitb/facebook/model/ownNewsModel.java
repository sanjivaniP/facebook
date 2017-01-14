package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.facebook.action.ownNewsAction;
import edu.iiitb.facebook.util.DB;

public class ownNewsModel {
	
	public static ArrayList<ownNewsAction> getOwnNews(int profileId){
		//System.out.println("inside Model");
		ArrayList<ownNewsAction> ownNewsList = new ArrayList<ownNewsAction>();
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		String query = "select type, time,statusId,pollId,photoId from Post where owner= " +profileId+";";
		String queryName = "select firstName,lastName from Profile where profileId= " +profileId+";";
				
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		resultSet1 = DB.readFromDB(queryName, connection);
		String firstName=null,lastName=null;
		try {
			while (resultSet.next()) {
				while(resultSet1.next()){
					 firstName=resultSet1.getString("firstName");
					 lastName=resultSet1.getString("lastName");
				}
				ownNewsAction ownNews=new ownNewsAction();
				String type=resultSet.getString("type");
				
				if(type.equalsIgnoreCase("text")){
					int statusId=resultSet.getInt("statusId");
					String query1 = "select description, updateTime from UserStatus where userStatusId=" +statusId+";";
					resultSet2 = DB.readFromDB(query1, connection);
					while(resultSet2.next()){
						ownNews.setStatus(resultSet2.getString("description"));
						ownNews.setTime(resultSet2.getString("updateTime"));
						//System.out.println("inside model :::"+ownNews.getTime());
						ownNews.setType("text");
						String displayString=firstName;
						if(lastName!=null)
							displayString=displayString.concat(" ").concat(lastName);
						//System.out.println("displayString:::::"+displayString);
						ownNews.setDisplayString(displayString);
					}
				}
				ownNewsList.add(ownNews);
			}
		} catch (SQLException e) {
            
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return ownNewsList;
	}

}
