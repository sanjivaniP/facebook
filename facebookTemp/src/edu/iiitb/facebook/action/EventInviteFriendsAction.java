package edu.iiitb.facebook.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.EventDao;
import edu.iiitb.facebook.model.EventInviteFriends;
import edu.iiitb.facebook.model.EventInviteFriendsDao;

public class EventInviteFriendsAction {

	private ArrayList<EventInviteFriends> ifa;
	private int profileId;
	private int eventId;
	private String name;
	private String details;
	private String where;
	private String datepicker;
	private String when;

	private String host;
	private boolean flag;
	private int eventPicId;
	private String time; // HH:mm a format

	private String dateEvent; // retrieve in different format MM dd
	private String dateEvent1; // retrieve in EEEE, MMMM dd, yyyy

	private int going;
	private int maybe;
	private int invited;

	private int rsvpStatusId;

	private List<String> friendId;

	/* variables for calculating list of people's joining status of events */
	private int statusEvent;

	private String status;

	public int getStatusEvent() {
		return statusEvent;
	}

	public void setStatusEvent(int statusEvent) {
		this.statusEvent = statusEvent;
	}

	private ArrayList<EventInviteFriends> joinStatus;

	private List<String> statusList; // select join status of event

	public List<String> getFriendId() {
		return friendId;
	}

	public void setFriendId(List<String> friendId) {
		this.friendId = friendId;
	}

	public String inviteFriends() {

		ifa = new ArrayList<EventInviteFriends>();

		ifa = (EventInviteFriendsDao.invite(profileId, eventId));

		return "success";
	}

	public String inviteFriendsSelect() {

		/*
		 * for (int tempFriendList : friendId) {
		 * System.out.println("Friend id: "+ tempFriendList
		 * +"EventId:  "+eventId); }
		 */
		EventInviteFriendsDao.sendInvite(friendId, eventId);

		invited = EventDao.getInvited(eventId);

		statusList = new ArrayList<String>();

		if (rsvpStatusId == 1) {
			statusList.add("Going");
			statusList.add("Not going");
			statusList.add("Maybe");

		} else if (rsvpStatusId == 2) {
			statusList.add("Maybe");
			statusList.add("Not going");

			statusList.add("Going");

		} else if (rsvpStatusId == 3) {
			statusList.add("Not going");
			statusList.add("Going");

			statusList.add("Maybe");

		} else {
			statusList.add("Join?");
			statusList.add("Not going");

			statusList.add("Going");

			statusList.add("Maybe");

		}

		return "success";
	}

	public String calculateStatusEvent() {
		joinStatus = new ArrayList<EventInviteFriends>();
		System.out.println("Calc Status Event");
		joinStatus = (EventInviteFriendsDao.calculateJoinStatus(eventId,
				statusEvent));
		return "success";

	}

	public String joinStatusChange() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			setProfileId(profileid);
			/*
			 * System.out.println("in joinStatusChange function");
			 * System.out.println("Going/maybe/not going "+status);
			 */

			int statusid;
			if (status.equals("Going"))
				statusid = 1;
			else if (status.equals("Maybe"))
				statusid = 2;
			else
				statusid = 3;

			EventInviteFriendsDao.updateStatusChange(profileId, eventId,
					statusid);
			statusList = new ArrayList<String>();

			going = EventDao.getGoing(eventId);
			maybe = EventDao.getMaybe(eventId);
			invited = EventDao.getInvited(eventId);

			if (status.equals("Going")) {
				statusList.add("Going");
				statusList.add("Not going");
				statusList.add("Maybe");

			} else if (status.equals("Maybe")) {
				statusList.add("Maybe");
				statusList.add("Not going");
				statusList.add("Going");

			} else {
				statusList.add("Not Going");
				statusList.add("Going");
				statusList.add("Maybe");

			}

			return "success";
		}
	}

	public String statusUpdate() {

		return "success";

	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getDatepicker() {
		return datepicker;
	}

	public void setDatepicker(String datepicker) {
		this.datepicker = datepicker;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public ArrayList<EventInviteFriends> getIfa() {
		return ifa;
	}

	public void setIfa(ArrayList<EventInviteFriends> ifa) {
		this.ifa = ifa;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getEventPicId() {
		return eventPicId;
	}

	public void setEventPicId(int eventPicId) {
		this.eventPicId = eventPicId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getDateEvent1() {
		return dateEvent1;
	}

	public void setDateEvent1(String dateEvent1) {
		this.dateEvent1 = dateEvent1;
	}

	public int getGoing() {
		return going;
	}

	public void setGoing(int going) {
		this.going = going;
	}

	public int getMaybe() {
		return maybe;
	}

	public void setMaybe(int maybe) {
		this.maybe = maybe;
	}

	public int getInvited() {
		return invited;
	}

	public void setInvited(int invited) {
		this.invited = invited;
	}

	public ArrayList<EventInviteFriends> getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(ArrayList<EventInviteFriends> joinStatus) {
		this.joinStatus = joinStatus;
	}

	public int getRsvpStatusId() {
		return rsvpStatusId;
	}

	public void setRsvpStatusId(int rsvpStatusId) {
		this.rsvpStatusId = rsvpStatusId;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
