package edu.iiitb.facebook.model;

public class Event {

	private String startTime; // to fetch starting date
	private int eventPicId;
	private String eventName;
	private String location;
	private int profileEventID;
	private int eventId;
	private int rsvpStatusId;
	private String startT; // to fetch starting time
	private String host;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getProfileEventID() {
		return profileEventID;
	}

	public void setProfileEventID(int profileEventID) {
		this.profileEventID = profileEventID;
	}

	public int getEventPicId() {
		return eventPicId;
	}

	public void setEventPicId(int eventPicId) {
		this.eventPicId = eventPicId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getRsvpStatusId() {
		return rsvpStatusId;
	}

	public void setRsvpStatusId(int rsvpStatusId) {
		this.rsvpStatusId = rsvpStatusId;
	}

	public String getStartT() {
		return startT;
	}

	public void setStartT(String startT) {
		this.startT = startT;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
