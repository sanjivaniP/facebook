package edu.iiitb.facebook.action;

import java.lang.reflect.Array;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.AboutOnProfileDAO;

public class AboutOnProfileAction {
	String homeTown;
	String currentCity;
	ArrayList<workAndEducation> workList;
	ArrayList<workAndEducation> educationList;
	AboutOnProfileAction profileAbout;
	int profileId;
	
	
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public AboutOnProfileAction getProfileAbout() {
		return profileAbout;
	}

	public void setProfileAbout(AboutOnProfileAction profileAbout) {
		this.profileAbout = profileAbout;
	}

	public ArrayList<workAndEducation> getWorkList() {
		return workList;
	}

	public void setWorkList(ArrayList<workAndEducation> workList) {
		this.workList = workList;
	}

	public ArrayList<workAndEducation> getEducationList() {
		return educationList;
	}

	public void setEducationList(ArrayList<workAndEducation> educationList) {
		this.educationList = educationList;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String retreiveAboutInfo(){
		//System.out.println("inside about action");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			profileAbout=AboutOnProfileDAO.getAboutInfo(profileid);
			System.out.println("homeTown in action:::"+profileAbout.getHomeTown());
			System.out.println("currcity in action :::"+profileAbout.getCurrentCity());
			System.out.println("worklist at action::::"+profileAbout.getWorkList().get(0));
			return "success";
		}
	}
	
	
	public String retreiveAboutInfoForHyperlink(){
		System.out.println("inside about action:::profileid:::"+profileId);
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			System.out.println("isnide if in action");
			return "login";
		} else {
			System.out.println("ineiside else in action");
			profileAbout=AboutOnProfileDAO.getAboutInfo(profileId);
			System.out.println("worklist at action::::"+profileAbout.getWorkList().get(0));
			return "success";
		}
	}
	
	

}
