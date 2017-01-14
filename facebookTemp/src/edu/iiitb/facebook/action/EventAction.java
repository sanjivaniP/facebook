package edu.iiitb.facebook.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.Birthday;
import edu.iiitb.facebook.model.BirthdayDao;

import edu.iiitb.facebook.model.Event;
import edu.iiitb.facebook.model.EventDao;

public class EventAction extends ActionSupport implements SessionAware {

	private int profileId;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int eventId;
	private int eventProfileId;

	public int getEventProfileId() {
		return eventProfileId;
	}

	public void setEventProfileId(int eventProfileId) {
		this.eventProfileId = eventProfileId;
	}

	private String details;
	private String where;
	private Date datepickerDate;
	private String dateEvent; // retrieve in different format MM dd
	private String dateEvent1; // retrieve in EEEE, MMMM dd, yyyy
	private String datepicker;
	private String when;
	private String host;
	private boolean flag;
	private int eventPicId;
	private String time; // HH:mm a format

	private int going;
	private int maybe;
	private int invited;

	EventAction ep;

	private List<String> statusList; // select join status of event
	private int rsvpStatusId;

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public EventAction getEp() {
		return ep;
	}

	public void setEp(EventAction ep) {
		this.ep = ep;
	}

	public String eventCreateEnter() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			setProfileId(profileid);

			return "success";
		}
	}

	public String eventPageDisplay() {
		/* retrieve information of event */
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			ep = new EventAction();
			setProfileId(profileid);
			System.out.println(eventId);
			ep = EventDao.getEventInformation(eventId, profileId);

			eventProfileId = ep.getEventProfileId();
			name = ep.getName();
			details = ep.getDetails();
			where = ep.getWhere();
			datepicker = ep.getDatepicker();
			when = ep.getWhen();
			host = ep.getHost();
			time = ep.getTime();
			eventPicId = ep.getEventPicId();
			dateEvent = ep.getDateEvent();
			dateEvent1 = ep.getDateEvent1();

			going = ep.getGoing();
			maybe = ep.getMaybe();
			invited = ep.getInvited();

			rsvpStatusId = ep.getRsvpStatusId();

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
	}

	public String createEventDetails() {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");

			ep = new EventAction();

			setProfileId(profileid);
			
			SimpleDateFormat printFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd");
			 
			datepicker=printFormat1.format(datepickerDate);
			
			
			System.out.println("date::::" + datepicker);
			ep = EventDao.create(profileId, name, details, where,
					datepicker, when);
			System.out.println("name:       " + name);
			System.out.println("flag:       " + ep.isFlag());

			eventId = ep.getEventId();
			flag = ep.isFlag();
			host = ep.getHost();
			dateEvent = ep.getDateEvent();
			dateEvent1 = ep.getDateEvent1();
			time = ep.getTime();
			eventPicId = ep.getEventPicId();

			going = ep.getGoing();
			maybe = ep.getMaybe();
			invited = ep.getInvited();

			rsvpStatusId = 1;
			statusList = new ArrayList<String>();
			statusList.add("Going");
			statusList.add("Decline");
			statusList.add("Maybe");

			return "success";
		}
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
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

	public String getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public int getRsvpStatusId() {
		return rsvpStatusId;
	}

	public void setRsvpStatusId(int rsvpStatusId) {
		this.rsvpStatusId = rsvpStatusId;
	}

	public Date getDatepickerDate() {
		return datepickerDate;
	}

	public void setDatepickerDate(Date datepickerDate) {
		this.datepickerDate = datepickerDate;
	}

	private ArrayList<Event> event;
	private ArrayList<Birthday> birthday;
	private String dt;
	private ArrayList<EventAction> eventD;

	public String eventDisplay() {

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			//setSessionId(profileid);
			eventD = EventAction.eventListRetrieval(profileid);

			if (!eventD.isEmpty())
				System.out.println("in eventDisplay function:   "
						+ eventD.get(0).getDt());

			return "success";
		}
	}

	public ArrayList<EventAction> getEventD() {
		return eventD;
	}

	public void setEventD(ArrayList<EventAction> eventD) {
		this.eventD = eventD;
	}

	public static ArrayList<EventAction> eventListRetrieval(int profileId) {

		ArrayList<EventAction> eventDisplay = new ArrayList<EventAction>();
		EventAction evD;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		// get current date time with Date()
		Date date = new Date();
		System.out.println("date :" + dateFormat.format(date));

		Date d2 = new Date();
		String d3;//original date
		Date d5 = new Date();
		String d4;//to be displayed used for panel

		for (int i = 1; i <= 365; i++) {

			evD = new EventAction();
			if (i == 1)
				d4 = "Today";

			else if (i >= 2 && i < 7) {

				DateFormat format2 = new SimpleDateFormat("EEEE");//give monday tuesday wednesday...
				d4 = format2.format(d5);

			} else {

				DateFormat format2 = new SimpleDateFormat("EEEE, MMMM dd, yyyy");//monday month day year
				d4 = format2.format(d5);
			}
			d3 = (dateFormat.format(date)).toString();

			evD.dt = d4;

			evD.event = EventDao.getEventList(profileId, d3);
			evD.birthday = BirthdayDao.getBirthdayNotification(profileId, d3);

	//		System.out.println("Event "+evD.event);
			if (!evD.event.isEmpty() || !evD.birthday.isEmpty())
				eventDisplay.add(evD);

			d2.setTime(date.getTime() + 1 * 24 * 60 * 60 * 1000);//incrementing the date by one full day
			date = d2;
			d5 = d2;
		}

		return eventDisplay;

	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Event> getEvent() {
		return event;
	}

	public void setEvent(ArrayList<Event> event) {
		this.event = event;
	}

	public ArrayList<Birthday> getBirthday() {
		return birthday;
	}

	public void setBirthday(ArrayList<Birthday> birthday) {
		this.birthday = birthday;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

}
