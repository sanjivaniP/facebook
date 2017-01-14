package edu.iiitb.facebook.action;

import java.util.ArrayList;


import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.ownNewsModel;

public class ownNewsAction extends ActionSupport{
	//int profileId=1;
	String displayString;
	String time;
	String status;
	String type;
	ArrayList<ownNewsAction> ownNewsList;
	
	
	public ArrayList<ownNewsAction> getOwnNewsList() {
		return ownNewsList;
	}


	public void setOwnNewsList(ArrayList<ownNewsAction> ownNewsList) {
		this.ownNewsList = ownNewsList;
	}


	public String getDisplayString() {
		return displayString;
	}


	public void setDisplayString(String displayString) {
		this.displayString = displayString;
	}


	

	

	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


//	public int getProfileId() {
//		return profileId;
//	}
//
//
//	public void setProfileId(int profileId) {
//		this.profileId = profileId;
//	}


	public String retrieveOwnNews(){
		//System.out.println("inside ownNewsAction");
		//session changes
				HttpSession session = ServletActionContext.getRequest().getSession(
						false);
				if (session == null || session.getAttribute("login") == null) {
					return "login";
				} else {
					// //System.out.println("inside else");
					int profileid = (Integer) session.getAttribute("profileId");
		ownNewsList=ownNewsModel.getOwnNews(profileid);
		for(ownNewsAction ownNews:ownNewsList){
			//System.out.println("in action :::"+ownNews.getTime());
		}
		
				
		if(ownNewsList!=null){
			
			return "success";
		}
		else{
			addActionError("There are no posts from you yet");
			return "failure";
		}
				}
		
		
	}

}
