package edu.iiitb.facebook.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.postOnWallDAO;
import edu.iiitb.facebook.model.statusModel;

public class postOnWallAction {
	String postOn;
	int profileId;

	
	
	public String getPostOn() {
		return postOn;
	}



	public void setPostOn(String postOn) {
		this.postOn = postOn;
	}



	public int getProfileId() {
		return profileId;
	}



	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}



	public String postOnWall(){
		//session changes
				HttpSession session = ServletActionContext.getRequest().getSession(
						false);
				if (session == null || session.getAttribute("login") == null) {
					return "login";
				} else {
					// //System.out.println("inside else");
					int profileid = (Integer) session.getAttribute("profileId");
					SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String timeStamp =formatter.format( Calendar.getInstance().getTime());
		
		postOnWallDAO.addPostOnWall(profileid,profileId,timeStamp,postOn);
		return "success";
				}
	}
	

}
