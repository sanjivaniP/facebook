package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.iiitb.facebook.util.DB;

public class EventInviteFriendsDao {

	public static ArrayList<EventInviteFriends> invite(int id,int eventId) {

		ArrayList<EventInviteFriends> inviteF = new ArrayList<EventInviteFriends>();

		EventInviteFriends ifs;
		ResultSet resultSet = null;
		String query;
		query = "select profileId,profilePicId,firstName,lastName from Profile " +
				"where profileId IN (SELECT  f.userId2 userId FROM Friendship f where f.userId1="+ id +" and f.areFriends='Y')"
					+ " or profileId in "	+ "(SELECT  f.userId1 userId FROM Friendship f where f.userId2="+id+" and f.areFriends='Y');";
		System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);

		boolean flagF;

		try {
			while (resultSet.next()) {
				ifs = new EventInviteFriends();
				ifs.setProfilePicId((resultSet.getInt("profilePicId")));
				ifs.setProfileId(resultSet.getInt("profileId"));
				ifs.setFirstName(resultSet.getString("firstName"));
				ifs.setLastName(resultSet.getString("lastName"));

				flagF = isInvited(ifs.getProfileId(),eventId);
				ifs.setFlagF(flagF);
System.out.println("friend or not :"+flagF);
				inviteF.add(ifs);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return inviteF;

	}

	private static boolean isInvited(int id,int eventId) {
		// TODO Auto-generated method stub

		String query;
		query = " select * from EventMembership where profileId=" + id + " and eventid="+ eventId +";";
		System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		ResultSet resultSet = null;
		resultSet = DB.readFromDB(query, connection);

		try {
			if (resultSet.next())
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DB.close(resultSet);
		DB.close(connection);
		return false;
	}

	public static void sendInvite(List<String> friendId, int eventId) {

		String query;
		Connection connection;
		connection = DB.getConnection();
		
		for (String tempFriendList : friendId) {
			query = "INSERT INTO EventMembership"
					+ " (profileId, rsvpStatusId, eventId) VALUES ("
					+ tempFriendList + ",4 ," + eventId + ");";
			System.out.println(query);

			Statement stmt;
			try {
				stmt = connection.createStatement();

				stmt.executeUpdate(query);

				

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		DB.close(connection);
		

	}

	public static ArrayList<EventInviteFriends> calculateJoinStatus(int eventId,
			int id) {
		// TODO Auto-generated method stub
		ArrayList<EventInviteFriends> joinStatus = new ArrayList<EventInviteFriends>();

		EventInviteFriends status;
		ResultSet resultSet = null;
		String query;
		query = "select Profile.profileId,profilePicId,firstName,lastName from Profile,EventMembership where eventId= "
				+ eventId
				+ " and rsvpStatusId="
				+ id
				+ " and Profile.profileId=EventMembership.profileId; ";
		System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);

		try {
			while (resultSet.next()) {
				status = new EventInviteFriends();
				status.setProfilePicId((resultSet
						.getInt("profilePicId")));
				status.setProfileId(resultSet.getInt("profileId"));
				status.setFirstName(resultSet.getString("firstName"));
				status.setLastName(resultSet.getString("lastName"));

				joinStatus.add(status);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return joinStatus;

	}

	public static void updateStatusChange(int profileId, int eventId,int statusid) {
		// TODO Auto-generated method stub
		String query;
		Connection connection;
		connection = DB.getConnection();
		//System.out.println("statusid"+statusid);
		
			query = "UPDATE EventMembership SET rsvpStatusId ="+ statusid +" WHERE eventId="+ eventId +" and profileId="+ profileId +";";
			System.out.println(query);

			Statement stmt;
			try {
				stmt = connection.createStatement();

				stmt.executeUpdate(query);

		

			} catch (SQLException e) {

				e.printStackTrace();
			}
		
		DB.close(connection);
		
		
		
	}
}
