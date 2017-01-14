package edu.iiitb.facebook.action;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Friend;
import edu.iiitb.facebook.model.Profile;


public class HeaderAction extends ActionSupport implements SessionAware {

	private SessionMap<String, Object> sessionMap;
	private Profile user;
	private int profileId;
	String isHyperlink;
	int avgAge;
	int numberoffriend;
	public String getIsHyperlink() {
		return isHyperlink;
	}

	public void setIsHyperlink(String isHyperlink) {
		this.isHyperlink = isHyperlink;
	}

	private ArrayList<Friend>friendsRequestRecievedList;


	public ArrayList<Friend> getFriendsRequestRecievedList() {
		return friendsRequestRecievedList;
	}

	public void setFriendsRequestRecievedList(
			ArrayList<Friend> friendsRequestRecievedList) {
		this.friendsRequestRecievedList = friendsRequestRecievedList;
	}

	public String execute() throws Exception {
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	
            return "login";  
        } 
        else{
        	
        	setProfileId((Integer)session.getAttribute("profileId"));
        	//System.out.println("profile Id "+getProfileId());
        	setUser(FacebookDAO.getProfile(getProfileId()));
        	setFriendsRequestRecievedList(FacebookDAO.getFriendsRequestRecievedListHome(getProfileId()));
        	//System.out.println("##"+user.getFirstName()+"##"+user.getProfilePicId());
        	SendFriendRequest p=new SendFriendRequest();
        	/* 
        	 * EXAM CODE 1
        	 * avgAge=p.retrieveFriends1();
        	 numberoffriend=p.retrieveFriends2();
        	*/ System.out.println("*********************SHUBHAM************************");
        	 System.out.println("AVG AGE=="+avgAge);
        	 System.out.println("number of friends=="+numberoffriend);
        	return "success";
        }

	}

	public int getAvgAge() {
		return avgAge;
	}

	public void setAvgAge(int avgAge) {
		this.avgAge = avgAge;
	}

	public int getNumberoffriend() {
		return numberoffriend;
	}

	public void setNumberoffriend(int numberoffriend) {
		this.numberoffriend = numberoffriend;
	}

//apurva changes
public String executeForHyperlink() throws Exception {
		setIsHyperlink("true");
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	
            return "login";  
        } 
        else{
        	
        	//setProfileId((Integer)session.getAttribute("profileId"));
        	setProfileId(profileId);
        	System.out.println("profile Id "+getProfileId());
        	setUser(FacebookDAO.getProfile(getProfileId()));
        	setFriendsRequestRecievedList(FacebookDAO.getFriendsRequestRecievedListHome(getProfileId()));
        	System.out.println("##"+user.getFirstName()+"##"+user.getProfilePicId());
        	
        	return "success";
        }

	}

	
	
	//apurva changes end
	
	
	@Override
	public void setSession(Map<String, Object> map) {

		sessionMap = (SessionMap) map;
	}

	public Profile getUser() {
		return user;
	}

	public void setUser(Profile user) {
		this.user = user;
	}
	

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
