package edu.iiitb.facebook.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Profile;

public class SendFriendRequest extends ActionSupport{
	int userId1;
	int userId2;
	String searchText;
	String submit;
	//apurva changes 
	int profileId;
	Profile profile;
	/*
	 * EXAM CODE 1
	 * int fanClubNumber;
	int fanclub;
	int numberoffriends;
*/
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	ArrayList<Profile> frindList;
	
	
	public ArrayList<Profile> getFrindList() {
		return frindList;
	}

	public void setFrindList(ArrayList<Profile> frindList) {
		this.frindList = frindList;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	//apurva changes end

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		
		this.submit = submit;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getUserId1() {
		return userId1;
	}

	public void setUserId1(int userId1) {
		this.userId1 = userId1;
	}

	public int getUserId2() {
		return userId2;
	}

	public void setUserId2(int userId2) {
		this.userId2 = userId2;
	}
	/**
	 * Will check whether the request is to add as friend or to respond to request already sent or to delete a friend
	 * 
	 */

	public String execute() throws Exception {
		System.out.println(getSubmit()+" %%%%%%%%%%%%%"+getUserId1()+"#####"+getUserId2());
		if(getSubmit().equals("Friends"))
		{
			FacebookDAO.unFriend(getUserId1(), getUserId2());
			return "success";
		}else if(getSubmit().equals("Add Friends")){
			FacebookDAO.sendFriendRequest(getUserId1(), getUserId2());
			return "success";
			
		}
		else if(getSubmit().equals("Confirm"))
		{
			FacebookDAO.confirmFriendRequest(getUserId1(), getUserId2());
			return "success";
			
		}
		else if(getSubmit().equals("Decline"))
		{
			FacebookDAO.unFriend(getUserId1(), getUserId2());
			return "success";
		}
			
		return "login";
	}
	
	
	public String retrieveFriends(){
		//System.out.println("inside retrieve Friends");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			frindList=FacebookDAO.getFriends(profileid);
		/*
		 * EXAM CODE 2
		 * 	numberoffriends=frindList.size();
		*/	return "success";
		}
	}
/*	
 * EXAM CODE 3
 * public int getNumberoffriends() {
		return numberoffriends;
	}

	public void setNumberoffriends(int numberoffriends) {
		this.numberoffriends = numberoffriends;
	}
*/
	/*--------------------------------------------EXAM CODE-----------------------------------------------*/
/*	
 * EXAM CODE 4
 * public int retrieveFriends1(){
		//System.out.println("inside retrieve Friends");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			frindList=FacebookDAO.getFriends1(profileid);
			int totalfriend=0;
			int totalage=0;
			
			totalfriend=frindList.size();
			if(frindList.size()!=0)
			{
			for(int i=0;i<frindList.size();i++)
			{
				totalage=totalage+frindList.get(i).getAgeMT2013148();
			}
			}
			int avgAge;
			if(totalfriend!=0)
			{	
			avgAge=totalage/totalfriend;
			
			}
			else
				{
				avgAge=0;
				}return avgAge;
		}
	
	public int retrieveFriends2(){
		//System.out.println("inside retrieve Friends");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			frindList=FacebookDAO.getFriends1(profileid);
		int totalfriend;
			
			totalfriend=frindList.size();
			
			
			return totalfriend;
		}
	
	public String retrieveFriends3(){
		//System.out.println("inside retrieve Friends");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			frindList=FacebookDAO.getFriends(profileid);
			numberoffriends=frindList.size();
			if(numberoffriends!=0|| fanClubNumber !=0)
			{
			if(numberoffriends>fanClubNumber)
			{
				fanclub=numberoffriends/fanClubNumber;
			}
			}else
			{
				fanclub=0;
			}
			return "success";
		}
	}
*/	/*----------------------------EXAM CODE-------------------------------------------------------------------*/
	
/*	
 * EXAM CODE 5
 * public int getFanClubNumber() {
		return fanClubNumber;
	}

	public void setFanClubNumber(int fanClubNumber) {
		this.fanClubNumber = fanClubNumber;
	}

	public int getFanclub() {
		return fanclub;
	}

	public void setFanclub(int fanclub) {
		this.fanclub = fanclub;
	}
*/
	public String retrieveFriendsForHyperlink(){
		//System.out.println("inside retrieve Friends");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			
			System.out.println("profileId in action::::"+profileId);
			frindList=FacebookDAO.getFriends(profileId);
			for(Profile prof:frindList){
				System.out.println("in view friends action:::"+prof.getFirstName());
			}
			return "success";
		}
	}
}
