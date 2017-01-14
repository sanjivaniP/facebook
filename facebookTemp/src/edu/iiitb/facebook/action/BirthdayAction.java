package edu.iiitb.facebook.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.Birthday;
import edu.iiitb.facebook.model.BirthdayDao;

public class BirthdayAction {

	private int profilePicAlbumId;
	private String firstName;
	private String lastName;
	private ArrayList<Birthday> birthday;
	
	private ArrayList<EventAction> eventD;
	
	private String displayBirthdayList;
	private boolean flag;
	private int session;
	private int profileId;
	private String wish;
	private int birthdayId;
	private boolean flagEventWished;

	public int getProfilePicAlbumId() {
		return profilePicAlbumId;
	}

	public void setProfilePicAlbumId(int profilePicAlbumId) {
		this.profilePicAlbumId = profilePicAlbumId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String birthdayNotification()
			{
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
	//		picId=NewsFeedDao.getOwnerDetails((Integer) session.getAttribute("profileId")).getPhotoId();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// get current date time with Date()
		Date date = new Date();
		
			
		String dt = (dateFormat.format(date)).toString();
		displayBirthdayList="";
		birthday=BirthdayDao.getBirthdayNotification(profileid,dt);
		
		
		if(birthday.size()==1)
			displayBirthdayList=displayBirthdayList + birthday.get(0).getFirstName()+" "+birthday.get(0).getLastName()+"'s birthday is today";
	if(birthday.size()>1)	
		displayBirthdayList=displayBirthdayList + birthday.get(0).getFirstName()+" "+birthday.get(0).getLastName()+" and "+ (birthday.size()-1) +" other ";	
	
	if(birthday.size()>0)
		setFlag(true);//for displaying the birthday image
	

		return "success";
		}
	}
	
	public String postWish()
	{
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// get current date time with Date()
		Date date = new Date();
		//System.out.println(dateFormat.format(date));

		String dt = (dateFormat.format(date)).toString();
		
		
		BirthdayDao.postBirthdayWish(profileid,profileId,wish);
		
		if(flagEventWished)
		{
			eventD=EventAction.eventListRetrieval(profileid);
		}else{
		
		
			birthday=BirthdayDao.getBirthdayNotification(profileid,dt);
		}
		
		
		
		return "success";
		}
	}

	
	public ArrayList<Birthday> getBirthday() {
		return birthday;
	}

	public void setBirthday(ArrayList<Birthday> birthday) {
		this.birthday = birthday;
	}

	public String getDisplayBirthdayList() {
		return displayBirthdayList;
	}

	public void setDisplayBirthdayList(String displayBirthdayList) {
		this.displayBirthdayList = displayBirthdayList;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}

	public int getBirthdayId() {
		return birthdayId;
	}

	public void setBirthdayId(int birthdayId) {
		this.birthdayId = birthdayId;
	}

	public ArrayList<EventAction> getEventD() {
		return eventD;
	}

	public void setEventD(ArrayList<EventAction> eventD) {
		this.eventD = eventD;
	}
}
