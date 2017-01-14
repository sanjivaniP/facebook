package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import edu.iiitb.facebook.action.EventAction;
import edu.iiitb.facebook.util.DB;

public class EventDao {
	public static EventAction getEventInformation(int eventId, int sessionId) {
		// TODO Auto-generated method stub

		EventAction epa = new EventAction();
		Connection connection;
		connection = DB.getConnection();

		ResultSet resultSet = null;
		String query;

		query = "select profileId,eventName,description,startTime,location,host,eventPicId from Events where eventId="
				+ eventId + "; ";
		System.out.println("Display event Details    " + query);

		resultSet = DB.readFromDB(query, connection);

		try {
			if (resultSet.next()) {
				epa.setEventProfileId(resultSet.getInt("profileId"));
				epa.setName(resultSet.getString("eventName"));
				epa.setDetails(resultSet.getString("description"));
				// epa.setTime(resultSet.getString("startTime"));

				/*--------------fetching datepicker and when -----------------r*/

				SimpleDateFormat parseFormat = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				SimpleDateFormat printFormat1 = new SimpleDateFormat(
						"yyyy-MM-dd");

				SimpleDateFormat printFormat2 = new SimpleDateFormat("hh:mm:ss");
				SimpleDateFormat printFormat3 = new SimpleDateFormat("MMM dd");

				SimpleDateFormat printFormat4 = new SimpleDateFormat(
						"EEEE, MMMM dd, yyyy");

				SimpleDateFormat printFormat5 = new SimpleDateFormat("hh:mm a ");

				Date date;
				try {
					date = parseFormat.parse(resultSet.getString("startTime"));

					epa.setDatepicker(printFormat1.format(date));
					epa.setWhen(printFormat2.format(date));
					epa.setDateEvent(printFormat3.format(date));
					epa.setDateEvent1(printFormat4.format(date));
					epa.setTime(printFormat5.format(date));

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				epa.setWhere(resultSet.getString("location"));
				epa.setHost(resultSet.getString("host"));
				epa.setEventPicId(resultSet.getInt("eventPicId"));

				epa.setHost((resultSet.getString("host")));

				/* set rsvpStatus ID */
				query = "select rsvpStatusId from EventMembership where eventId="
						+ eventId + " and profileId=" + sessionId + ";";

				resultSet = DB.readFromDB(query, connection);

				try {
					if (resultSet.next()) {
						epa.setRsvpStatusId((resultSet.getInt(1)));
					}

				}

				catch (SQLException e) {
					e.printStackTrace();
				}

				/*-------------set status of event----------*/

				
				
				
				epa.setInvited(getInvited(eventId));
				epa.setGoing(getGoing(eventId));
				epa.setMaybe(getMaybe(eventId));
				
				DB.close(connection);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return epa;
	}

	public static int getGoing(int eventId) {
		// TODO Auto-generated method stub

		Connection connection;
		connection = DB.getConnection();

		ResultSet resultSet = null;
		String query;
		query = "select count(*) from EventMembership where eventId="
				+ eventId + " and rsvpStatusId =1; ";

		resultSet = DB.readFromDB(query, connection);

		try {
			if (resultSet.next()) {
				return(resultSet.getInt(1));
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int getMaybe(int eventId) {
		// TODO Auto-generated method stub

		Connection connection;
		connection = DB.getConnection();

		ResultSet resultSet = null;
		String query;
		query = "select count(*) from EventMembership where eventId="
				+ eventId + " and rsvpStatusId =2; ";

		resultSet = DB.readFromDB(query, connection);

		try {
			if (resultSet.next()) {
				return(resultSet.getInt(1));
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	

	public static int getInvited(int eventId) {
		// TODO Auto-generated method stub
		
		Connection connection;
		connection = DB.getConnection();

		ResultSet resultSet = null;
		String query;
		query = "select count(*) from EventMembership where eventId="
				+ eventId + " and rsvpStatusId =4; ";

		resultSet = DB.readFromDB(query, connection);

		try {
			if (resultSet.next()) {

				return(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return 0;
	}

	public static EventAction create(int id, String name, String details,
			String where, String datepicker, String when) {

		EventAction epa = new EventAction();

		/*------------setting host name------*/
		Connection connection;
		connection = DB.getConnection();
		Statement stmt;

		ResultSet resultSet = null;
		String query;

		query = "select firstName from Profile where profileId=" + id + "; ";
		System.out.println("host :    " + query);

		resultSet = DB.readFromDB(query, connection);
		System.out.println("create event name :   " + epa.getName());
		try {
			while (resultSet.next()) {

				epa.setHost((resultSet.getString("firstName")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*-------------checking for validity for time----------*/
		boolean validTime = isvalid(when);

	/*	System.out.println("valid time or not ?    " + validTime);
		System.out.println("when:      " + when);*/
		if (validTime == false) {
			when = "";
			epa.setFlag(false);
		} else
			epa.setFlag(true);

		System.out.println("epa flag value :  " + epa.isFlag());

		/*---------setting time -------*/

		String string1 = datepicker + " " + when;
		System.out.println("setting time in create event dao :   " + string1);
		epa.setTime(EventDao.getTime(string1));

		/*--------------changing entered when values into datetime format*/
		String dateSample = datepicker + " " + when;
		String oldFormat;
		String newFormat;

		if (epa.isFlag() == true) {
			oldFormat = "yyyy-MM-dd HH:mm:ss";
			newFormat = "yyyy-MM-dd HH:mm:ss";
		} else {
			oldFormat = "yyyy-MM-dd";
			newFormat = "yyyy-MM-dd";
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
		SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

		try {
			System.out.println(sdf2.format(sdf1.parse(dateSample)));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*-------------------entering event details--------------*/
		String query1;

		try {

			String parseDate = sdf2.format(sdf1.parse(dateSample));
			;
			stmt = connection.createStatement();
			query1 = "INSERT INTO Events (eventName, profileId, description,location, startTime, host,eventPicId) VALUES ('"
					+ name
					+ "',"
					+ id
					+ ",' "
					+ details
					+ "', '"
					+ where
					+ "','" + parseDate + "', '" + epa.getHost() + "',1);";
			System.out.println(query1);
			stmt.executeUpdate(query1);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*-------fetching eventId---------*/

		query = "SELECT eventId,eventPicId FROM Events order by eventId ASC";

		resultSet = DB.readFromDB(query, connection);

		try {

			resultSet.last();
			epa.setEventId((resultSet.getInt("eventId")));
			epa.setEventPicId(resultSet.getInt("eventPicId"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*----------entering details in event membership tables */

		query1 = "INSERT INTO EventMembership (profileId, rsvpStatusId, eventId) VALUES ("
				+ id + ", 1," + epa.getEventId() + ");";
		System.out.println(query1);
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(query1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*--------------------- fetching eventDate in different format------------*/
		String oldFormat1;
		String newFormat1, newFormat2;

		oldFormat1 = "yyyy-MM-dd";
		newFormat1 = "MMM dd";
		newFormat2 = "EEEE, MMMM dd, yyyy";

		SimpleDateFormat sdf3 = new SimpleDateFormat(oldFormat1);
		SimpleDateFormat sdf4 = new SimpleDateFormat(newFormat1);
		SimpleDateFormat sdf5 = new SimpleDateFormat(newFormat2);

		try {
			epa.setDateEvent(sdf4.format(sdf3.parse(datepicker)));

			epa.setDateEvent1(sdf5.format(sdf3.parse(datepicker)));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*-------------set status of event----------*/

		/*
		 * query =
		 * "select count(*) from EventMembership group by rsvpStatusId ASC; ";
		 */
		epa.setGoing(1);
		epa.setMaybe(0);

		epa.setInvited(0);

		return epa;
	}

	private static boolean isvalid(String dateStr) {

		DateFormat df = new SimpleDateFormat("HH:mm:ss");

		try {
			df.parse(dateStr);
			return true;
		} catch (ParseException exc) {
		}

		return false;

	}


	public static ArrayList<Event> getEventList(int id, String dt) {

		ArrayList<Event> eventPost = new ArrayList<Event>();
		Event ev;

		ResultSet resultSet = null;

		String query;

		query = "select startTime,eventPicId,eventName,location,Events.profileId,Events.eventId,rsvpStatusId,host from Events,EventMembership" +
				" where Events.eventId=EventMembership.eventId and EventMembership.profileId="
				+ id + " and startTime like'" + dt.substring(0, 10) + "%' ;";
/*
		System.out.println("query for event    " + query);*/
//System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);

		try {
			while (resultSet.next()) {

				ev = new Event();
				System.out.println("Event ID :  "
						+ resultSet.getInt("Events.eventId"));
				ev.setStartTime(resultSet.getString("startTime"));
				ev.setEventPicId(resultSet.getInt("eventPicId"));
				ev.setEventName(resultSet.getString("eventName"));
				ev.setLocation(resultSet.getString("location"));
				ev.setProfileEventID(resultSet.getInt("Events.eventId"));
				ev.setRsvpStatusId(resultSet.getInt("rsvpStatusId"));
				ev.setHost(resultSet.getString("host"));

				String st;
				st = getTime(ev.getStartTime());

				ev.setStartT(st);
				eventPost.add(ev);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return eventPost;

	}

	public static String getTime(String startTime) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt;
		String st = "";
		SimpleDateFormat printFormat5 = new SimpleDateFormat("hh:mm a ");
		try {
			dt = sdf2.parse(startTime);
			st = printFormat5.format(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;
	}

}
