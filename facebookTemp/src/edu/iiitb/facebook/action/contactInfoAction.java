package edu.iiitb.facebook.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.basicInfoDAO;
import edu.iiitb.facebook.model.contactInfoDAO;

public class contactInfoAction {
	String emailId;
	String phoneNumber;
	String userName;
	contactInfoAction contactInfo;
	int profileId;
	String isHyperlink;
	
	public String getIsHyperlink() {
		return isHyperlink;
	}

	public void setIsHyperlink(String isHyperlink) {
		this.isHyperlink = isHyperlink;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	String phoneNumber1;
	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public contactInfoAction getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(contactInfoAction contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String retrieveContactInfo(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
		
		 contactInfo=contactInfoDAO.getContactInfo(profileid);
		
		 if(contactInfo!=null)
		return "success";
		
		}
		
		return "failure";
	}
	
	
	public String savePhoneNumber(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			int success=contactInfoDAO.updatePhoneNumber(phoneNumber,profileid);
			//System.out.println("success:::"+success);
			return "success";
		
		}
		 
}
	public String deletePhoneNumber(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			phoneNumber=null;
			int success=contactInfoDAO.updatePhoneNumber(phoneNumber,profileid);
			//System.out.println("success:::"+success);
			return "success";
		
		}
		 
}
	public String retrieveContactInfoForHyperlink(){
		setIsHyperlink("true");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			//int profileid = (Integer) session.getAttribute("profileId");
		
		 contactInfo=contactInfoDAO.getContactInfo(profileId);
		
		 if(contactInfo!=null)
		return "success";
		
		}
		
		return "failure";
	}
	
public String editPhone1(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			//int success=contactInfoDAO.updatePhoneNumber(phoneNumber,profileid);
			System.out.println("success:::"+phoneNumber1);
			return "success";
		
		}
		 
}
	

}
