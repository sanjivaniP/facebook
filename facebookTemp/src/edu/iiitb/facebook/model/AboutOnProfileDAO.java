package edu.iiitb.facebook.model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.facebook.action.AboutOnProfileAction;

import edu.iiitb.facebook.action.placeAction;
import edu.iiitb.facebook.util.DB;

public class AboutOnProfileDAO {
	
	public static AboutOnProfileAction getAboutInfo(int profileId) {
		System.out.println("isnide  getAboutInfo model");
		AboutOnProfileAction profileAbout=new AboutOnProfileAction();
		ResultSet resultSet = null;
		String query = "select homeTown,currentCity from Profile where profileId="+profileId+";";
		System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				profileAbout.setCurrentCity(resultSet.getString("currentCity"));
				System.out.println("currentcity in model:::"+profileAbout.getCurrentCity());
				profileAbout.setHomeTown(resultSet.getString("homeTown"));
				System.out.println("hometownin model::::"+profileAbout.getHomeTown());
			}
			System.out.println("before worklist");
		profileAbout.setWorkList(workAndEducationDAO.getWorkInfo(profileId));
		System.out.println("after worklist");
		profileAbout.setEducationList(workAndEducationDAO.getEducationInfo(profileId));
		System.out.println("after educationlist");
			
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		System.out.println("before Db resultset");
		DB.close(resultSet);
		System.out.println("before close connectn");
		DB.close(connection);
		System.out.println("work before return in model :::"+profileAbout.getWorkList().get(0));
		return profileAbout;
	}


}
