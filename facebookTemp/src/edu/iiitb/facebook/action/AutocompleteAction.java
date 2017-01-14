package edu.iiitb.facebook.action;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Profile;


public class AutocompleteAction  extends ActionSupport implements SessionAware{

	
	int profileId;
	String search;
	public int getProfileId() {
		return profileId;
	}


	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	public String getSearch() {
		return search;
	}


	public void setSearch(String search) {
		this.search = search;
	}


	public ArrayList<Profile> getListOfProfile() {
		return listOfProfile;
	}


	public void setListOfProfile(ArrayList<Profile> listOfProfile) {
		this.listOfProfile = listOfProfile;
	}


	ArrayList<Profile> listOfProfile;

	public String execute() throws Exception {
		
		HttpSession session=ServletActionContext.getRequest().getSession(false);  
        if(session==null || session.getAttribute("login")==null){  
        	
            return "login";  
        } 
        else{
        	
        	setProfileId((Integer)session.getAttribute("profileId"));
        	System.out.println("profile Id "+getProfileId());
        	setListOfProfile(FacebookDAO.getSearchResult(getSearch(),profileId));
        	//System.out.println("##"+u.getFirstName()+"##"+user.getProfilePicId());
        	
        	return "success";
        }

	}
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

}
