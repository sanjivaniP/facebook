package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.facebook.action.placeAction;
import edu.iiitb.facebook.util.DB;

public class addPlaceDAO {

	public static int insertPlace(String whereto,String address,String from,String when,String story,int profileId) {
		String insertSQL = "insert into  placeMovement"
				+ "(WhereTo, address, story, date, fromPlace, profileId) " + "values('" + whereto
				+ "', '" + address + "', '" + story + "', '" + when + 
				"', '" + from + "',"+profileId+");";
		//System.out.println(insertSQL);
		return DB.update(insertSQL);
	}
	
	
	public static ArrayList<placeAction> getPlaces(int profileId) {
		ArrayList<placeAction> placesList = new ArrayList<placeAction>();
		ResultSet resultSet = null;
		String query = "select WhereTo, story from placeMovement where profileId="+profileId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				placeAction place=new placeAction();
			
				place.setWhereto(resultSet.getString("WhereTo"));
				place.setStory(resultSet.getString("story"));
				if(resultSet.getString("story").equalsIgnoreCase("HomeTown")){
					place.setFlagHomeTown("true");
					place.setHomeTown(resultSet.getString("WhereTo"));
				}
				if(resultSet.getString("story").equalsIgnoreCase("CurrentCity")){
					place.setFlagCurrentCity("true");
					place.setCurrentCity(resultSet.getString("WhereTo"));
				}
				placesList.add(place);
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return placesList;
	}

	
/* ********************************Shubham Code Start *************************************** */
	
	/*UPDATE `facebook`.`placeMovement` SET `WhereTo`='Mumbai' WHERE `place_id`='1';*/
	public static int editPlace(String whereto,String address,int profileId) {
		
	/*	String updateSQL="UPDATE  placeMovement "
				+"SET WhereTo='"+whereto+"' WHERE  profileId='"+profileId+"';";
		*/
	String updateSQL="UPDATE  placeMovement "
				+"SET WhereTo='"+whereto+"' WHERE story='"+address+"' AND profileId='"+profileId+"';";
	DB.update(updateSQL);
	
	if(address.equalsIgnoreCase("HomeTown"))
	{
		updateSQL = 
			    "update Profile "
				+ "set  homeTown= '" +whereto+"' where profileId = " + profileId;
		DB.update(updateSQL);
		
	}
	if(address.equalsIgnoreCase("CurrentCity"))
	{
		updateSQL = 
			    "update Profile "
				+ "set  currentCity= '" +whereto+"' where profileId = " + profileId;
		DB.update(updateSQL);
		
	}
	/*	String insertSQL = "insert into  placeMovement"
				+ "(whereTo, address, story, date, fromPlace, profileId) " + "values('" + whereto
				+ "', '" + address + "', '" + story + "', '" + when + 
				"', '" + from + "',"+profileId+");";
		*/
		System.out.println(updateSQL);
		return DB.update(updateSQL);
	}
	
	
	public static int deletePlace(String whereto,String address,int profileId) {
		
		/*	String updateSQL="UPDATE  placeMovement "
					+"SET WhereTo='"+whereto+"' WHERE  profileId='"+profileId+"';";
			*/
			
			/*String query = "delete from WorkPlace where profileId=" + profileId
					+ " AND companyName='" + companyName + "';";
			*/
			String updateSQL="delete  from placeMovement where profileId=" + profileId
					+" AND WhereTo='"+whereto+"';";
		/*String updateSQL="UPDATE  placeMovement "
					+"SET WhereTo='"+whereto+"' WHERE story='"+address+"' AND profileId='"+profileId+"';";
		*/
			DB.update(updateSQL);
		
		if(address.equalsIgnoreCase("HomeTown"))
		{
			updateSQL = 
				    "update Profile "
					+ "set  homeTown= NULL where profileId = " + profileId;
			DB.update(updateSQL);
			
		}
		if(address.equalsIgnoreCase("CurrentCity"))
		{
			updateSQL = 
				    "update Profile "
					+ "set  currentCity= NULL where profileId = " + profileId;
			DB.update(updateSQL);
			
		}
		/*	String insertSQL = "insert into  placeMovement"
					+ "(whereTo, address, story, date, fromPlace, profileId) " + "values('" + whereto
					+ "', '" + address + "', '" + story + "', '" + when + 
					"', '" + from + "',"+profileId+");";
			*/
			System.out.println(updateSQL);
			return DB.update(updateSQL);
		}
	
	/* ********************************Shubham Code End *************************************** */
	
public static int updateHomeTown(String homeTown, int profileId) {
		
		String updateSQL;
		insertPlace(homeTown, "", "", "", "HomeTown", profileId);
		
		updateSQL = 
			    "update Profile "
				+ "set  homeTown= '" +homeTown+"' where profileId = " + profileId;
		
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}

public static int updateCurrentCity(String currentCity, int profileId) {
	
	String updateSQL;
	insertPlace(currentCity, "", "", "", "CurrentCity", profileId);
	
	updateSQL = 
		    "update Profile "
			+ "set  currentCity= '" +currentCity+"' where profileId = " + profileId;
	
	
	//System.out.println(updateSQL);
	return DB.update(updateSQL);
}


	



}
