package edu.iiitb.facebook.action;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Friend;
public class FriendRequestResponse extends ActionSupport{
	private int userId1;
	private int userId2;

	//private int user1;
	private ArrayList<Friend>friendsRequestRecievedList =new ArrayList<Friend>();
	String submit;
	//arraylist<friendsRequestRecievedList> receivedRequests;
	
	public String showRequests(){
		userId2=16;
		//friendsRequestRecievedList = FacebookDAO.getFriendsRequestRecievedList(getUserId2());
		
		return "success";
	}
	public String execute(){
		System.out.println("check1"+userId2);
	/*	HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			userId2 = profileid;
		}*/
		userId2 = 16;
		System.out.println("check2"+userId1);
		
		
		if(getSubmit().equals("Confirm"))
		{
			FacebookDAO.acceptRequest(getUserId1(), getUserId2());
			
			System.out.println("check"+userId2);
			return "accepted";
		}else if(getSubmit().equals("Not Now")){
			FacebookDAO.rejectRequest(getUserId1(), getUserId2());
			return "rejected";
			
		}
		//System.out.println(submit+"@@@@@@@");
		//FacebookDAO.acceptRequest(getUserId1(), getUserId2());
		return "success";
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
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public ArrayList<Friend> getFriendsRequestRecievedList() {
		return friendsRequestRecievedList;
	}
	public void setFriendsRequestRecievedList(
			ArrayList<Friend> friendsRequestRecievedList) {
		this.friendsRequestRecievedList = friendsRequestRecievedList;
	}
	
	
}
