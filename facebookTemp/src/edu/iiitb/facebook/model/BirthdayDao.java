package edu.iiitb.facebook.model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import edu.iiitb.facebook.util.DB;

public class BirthdayDao {

	public static ArrayList<Birthday> getBirthdayNotification(int id, String dt) {
		
		String bd;

		int a;
		ArrayList<Birthday> birthdayPost = new ArrayList<Birthday>();

		Birthday ba;

		ResultSet resultSet = null;
		String query;
		query = " select profileId,profilePicId,firstName,lastName,birthDate,gender from Profile" +
				" where birthDate like '%"
				+ dt.substring(5, 10) //only month and day
				+ "' and (profileId IN (SELECT  f.userId2 userId FROM Friendship f where f.userId1="+id+" and f.areFriends='Y')"
					+ " or profileId IN (SELECT  f.userId1 userId FROM Friendship f where f.userId2="+id+" and f.areFriends='Y'));";

		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);

		try {
			while (resultSet.next()) {
				ba = new Birthday();
				ba.setPicId((resultSet.getInt("profilePicId")));
				ba.setFirstName(resultSet.getString("firstName"));
				ba.setLastName(resultSet.getString("lastName"));
				ba.setProfileId(resultSet.getInt("profileId"));
				ba.setGender(resultSet.getString("gender"));

				bd = resultSet.getString("birthDate");

				a = Integer.parseInt(dt.substring(0, 4))
						- Integer.parseInt(bd.substring(0, 4));//a=diff of years from today(age)
					//calculating the age
				if (Integer.parseInt(dt.substring(5, 7)) < Integer.parseInt(bd
						.substring(5, 7))
						|| (Integer.parseInt(dt.substring(5, 7)) == Integer
								.parseInt(dt.substring(5, 7)) && Integer
								.parseInt(dt.substring(8, 10)) < Integer
								.parseInt(bd.substring(8, 10)))) {
					--a;
				}


				ba.setAge(a);
				
				int bid = getBirthdayId(id, resultSet.getInt("profileId"));

				if (bid != 0)
					ba.setFlagB(true);
				else
					ba.setFlagB(false);

				birthdayPost.add(ba);
				// System.out.println("Iterating birthday list");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return birthdayPost;

	}

	public static void postBirthdayWish(int session, int profileId, String wish) {
			//session =logged in user
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// get current date time with Date()
		Date date = new Date();

		String dt = (dateFormat.format(date)).toString();

		String query;

		query = "INSERT INTO `Birthday` (`time`, `message`, `fromId`, `toId`) VALUES ('"
				+ dt + "', '" + wish + "'," + session + "," + profileId + ");";
		// System.out.println(query);

		Connection connection;
		connection = DB.getConnection();
		Statement stmt;
		try {
			stmt = connection.createStatement();

			stmt.executeUpdate(query);

			int bid = getBirthdayId(session, profileId);
			stmt.executeUpdate(query);
			
			query = "INSERT INTO `PostOnWall` (`time`, `description`, `fromId`, `toId`) VALUES ('"
					+ dt + "', '" + wish + "'," + session + "," + profileId + ");";
			System.out.println(query);
			stmt.executeUpdate(query);
			int postonwallid=postOnWallDAO.findPostOnWallId(session,profileId,dt,wish);
			String insertSQL1 = "insert into Post "
					+ "(owner,type,time,postOnWallId) " + "values("+profileId+",'postOnWall','"+dt+"',"+postonwallid+");";
			
		DB.update(insertSQL1);
		

		} catch (SQLException e) {

			e.printStackTrace();
		}
		DB.close(connection);

	}
/**
 * checks if there is an entry of the 'to' user wishing the 'from' user in table Birthday
 * @param from
 * @param to
 * @return
 */
	public static int getBirthdayId(int from, int to) {

		ResultSet resultSet = null;
		String query;
		int bId = 0;
		//just add time like '2014%'; for current year check
		query = " select birthdayId from Birthday where fromId=" + from
				+ " and toId=" + to + ";";
		// System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			if (resultSet.next())
				bId = resultSet.getInt("birthdayId");
			// System.out.println(bId);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return bId;
	}

}