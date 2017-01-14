package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import edu.iiitb.facebook.util.DB;
import edu.iiitb.facebook.util.MyLog;
import edu.iiitb.facebook.util.RuntimeSettings;

public class SignUpDao {
	public static int addNewProfile(Profile newProfileInfo, int loginId) {
		int profileId = 0;
		Statement stmt = null;
		PreparedStatement stmt2 = null;
		Connection con = DB.getConnection();
		try {

			stmt = con.createStatement();
			String insertQuery = "insert into Profile (firstName, lastName, birthDate, gender, loginId) values('"
					+ newProfileInfo.getFirstName()
					+ "','"
					+ newProfileInfo.getLastName()
					+ "','"
					+ newProfileInfo.getBirthDate()
					+ "','"
					+ newProfileInfo.getGender() + "'," + loginId + ")";

			stmt.executeUpdate(insertQuery);
			String retrieveQuery = "select profileId from Profile where loginId=?";
			stmt2 = con.prepareStatement(retrieveQuery);
			stmt2.setInt(1, loginId);
			ResultSet rs = stmt2.executeQuery();
			if (rs.next()) {
				profileId = rs.getInt("profileId");
			}
			stmt.close();
			close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profileId;
	}

	public static int createAccount(String emailId, String password,
			String fname, String lname) {
		Statement stmt = null;
		String username = null;
		int loginId = 0;
		Connection con = DB.getConnection();
		PreparedStatement stmtRetrieve = null;
		try {

			stmt = con.createStatement();
			String query = "insert into Login(emailId, password) values ('"
					+ emailId + "','" + password + "')";
			stmt.executeUpdate(query);
			String retrieveQuery = "select loginId from Login where emailId = ?";
			stmtRetrieve = con.prepareStatement(retrieveQuery);
			stmtRetrieve.setString(1, emailId);
			ResultSet rs = stmtRetrieve.executeQuery();
			if (rs.next()) {
				loginId = rs.getInt("loginId");
			}

			username = fname + "." + lname + loginId+"@facebook.com";
			String insertQuery = "update Login set userName='" + username
					+ "' where loginId=" + loginId + ";";
			stmt.executeUpdate(insertQuery);

			// //System.out.println("after query2");
			stmt.close();
			close(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginId;

	}

	public static String fillOutInfo(int profileId, String currentCity,
			String hometown, String companyName, String schoolName,
			String collegeName) {
		Statement stmt = null;
		int schoolCount = 0, workPlaceCount = 0;

		Connection con = DB.getConnection();

		if (companyName != null && !companyName.isEmpty()) {
			workPlaceCount = 1;
		}

		if ((collegeName != null && !collegeName.isEmpty())
				&& (schoolName != null && !schoolName.isEmpty())) {
			schoolCount = 2;

		} else if ((collegeName != null && !collegeName.isEmpty())
				|| (schoolName != null && !schoolName.isEmpty())) {
			schoolCount = 1;

		}
		try {
			stmt = con.createStatement();
			String insertQuery5 = "insert into placeMovement(WhereTo, story,profileId) values ('"
					+ hometown + "','HomeTown'," + profileId + ") ";
			stmt.executeUpdate(insertQuery5);
			String insertQuery6 = "insert into placeMovement(WhereTo, story,profileId) values ('"
					+ currentCity + "','CurrentCity'," + profileId + ") ";
			stmt.executeUpdate(insertQuery6);
			String insertQuery1 = "update Profile set schoolCount ="
					+ schoolCount + " , homeTown='" + hometown
					+ "' , currentCity='" + currentCity
					+ "' , workPlaceCount =" + workPlaceCount
					+ " where profileId=" + profileId;
			stmt.executeUpdate(insertQuery1);
			String insertQuery2 = "insert into School(schoolName, profileId) values('"
					+ schoolName + "'," + profileId + ")";
			stmt.executeUpdate(insertQuery2);
			String insertQuery3 = "insert into School(schoolName, profileId) values('"
					+ collegeName + "'," + profileId + ")";
			stmt.executeUpdate(insertQuery3);
			String insertQuery4 = "insert into WorkPlace(companyName, profileId) values('"
					+ companyName + "'," + profileId + ")";
			stmt.executeUpdate(insertQuery4);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "info added successfully";
	}

	public static void close(Connection connection) {
		// return;
		if (connection == null) {
			return;
		}
		try {
			if (connection.isClosed()) {
				connection = null;
			} else {
				try {
					connection.close();

					connection = null;
				} catch (SQLException ex) {
					MyLog.myCatch("/java", 106, ex);
				}
			}
		} catch (SQLException ex) {
			MyLog.myCatch("/java", 110, ex);
		}
	}

}
