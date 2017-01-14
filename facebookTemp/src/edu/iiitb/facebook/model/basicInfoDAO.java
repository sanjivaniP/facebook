package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import edu.iiitb.facebook.action.basicInfoAction;
import edu.iiitb.facebook.action.placeAction;
import edu.iiitb.facebook.util.DB;

public class basicInfoDAO {
	public static basicInfoAction getBasicInfo(int profileId) {
		basicInfoAction basicInfo=new basicInfoAction();
		ResultSet resultSet = null;
		String query = "select birthDate,interests,gender,relationship,religion from Profile where profileId="+profileId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				
				basicInfo.setBirthDate(resultSet.getDate("birthDate"));
				int iDate=basicInfo.getBirthDate().getDate();
				//System.out.println("idate::"+iDate);
				int iMonth=basicInfo.getBirthDate().getMonth()+1;
				//System.out.println("imonth::::"+iMonth);
				String bDate=null;
				if(iMonth==1)
					bDate="January".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==2)
					bDate="February".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==3)
					bDate="March".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==4)
					bDate="April".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==5)
					bDate="May".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==6)
					bDate="June".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==7)
					bDate="July".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==8)
					bDate="August".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==9)
					bDate="September".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==10)
					bDate="October".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==11)
					bDate="November".concat(" ").concat(String.valueOf(iDate));
				if(iMonth==12)
					bDate="December".concat(" ").concat(String.valueOf(iDate));
				//System.out.println("bdate:::::"+bDate);
				basicInfo.setbDate(bDate);
				basicInfo.setbYear(basicInfo.getBirthDate().getYear());
				
				basicInfo.setGender(resultSet.getString("gender"));
				basicInfo.setReligion(resultSet.getString("religion"));
				basicInfo.setRelationship(resultSet.getString("relationship"));
				basicInfo.setInterests(resultSet.getString("interests"));
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return basicInfo;
	}
	
	
	
	public static ArrayList<String> getRelStatusList(){
		ArrayList<String> relStatusList=new ArrayList<String>();
		ResultSet resultSet = null;
		String query = "select description from RelationshipStatus;";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				
				relStatusList.add(resultSet.getString("description"));
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return relStatusList;
	}

	public static int updateRelStatus(String relationship,String relWith, int profileId) {
		//System.out.println("rel in model :::"+relationship);
		String updateSQL;
		if(relationship!=null)
		{
		if(relWith.equals(null)){
		updateSQL = 
			    "update Profile "
				+ "set relationship = '" + relationship+"' where profileId = " + profileId;
		
		}
		else{
		updateSQL = 
				    "update Profile "
					+ "set relationship = '" + relationship+"', relationshipWith='"+relWith+"' where profileId = " + profileId;
		}
		}
		else
		{
			updateSQL = 
				    "update Profile "
					+ "set relationship = NULL where profileId = " + profileId;
		}
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}
	
	public static int updateGender(String gender, int profileId) {
		
		String updateSQL;
		
		updateSQL = 
			    "update Profile "
				+ "set gender = '" +gender+"' where profileId = " + profileId;
		
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}

	public static int updateInterestedIn(String interests, int profileId) {
		
		String updateSQL;
		if(interests!=null)
		{
		updateSQL = 
			    "update Profile "
				+ "set interests = '" +interests+"' where profileId = " + profileId;
		}
		else
		{
			updateSQL = 
				    "update Profile "
					+ "set interests = NULL where profileId = " + profileId;
		}
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}
	
public static int updateReligion(String religion, int profileId) {
		
		String updateSQL;
		if(religion!=null)
		{
		updateSQL = 
			    "update Profile "
				+ "set religion = '" +religion+"' where profileId = " + profileId;
		}
		else
		{
			
			updateSQL = 
				    "update Profile "
					+ "set religion = NULL where profileId = " + profileId;	
		}
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}
/*public static int deleteReligion1(String religion, int profileId) {
	
	String updateSQL;
	
	updateSQL = 
		    "update Profile "
			+ "set religion = NULL where profileId = " + profileId;
	
	
	//System.out.println(updateSQL);
	return DB.update(updateSQL);
}
*/
	public static int updatebirthDate(Date birthDate, int profileid) {
	
	String updateSQL;
	
	updateSQL = 
		    "update Profile "
			+ "set birthDate = '" +birthDate+"' where profileId = " + profileid;
	
	
	//System.out.println(updateSQL);
	return DB.update(updateSQL);
}
	/*public static int deletebirthDate1(Date birthDate, int profileid) {
		
		String updateSQL;
		
		updateSQL = 
			    "update Profile "
				+ "set birthDate = NULL where profileId = " + profileid;
		
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}*/
}
