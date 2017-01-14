package edu.iiitb.facebook.action;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.statusModel;

public class statusAction {
	int profileId;
	private String status;
	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String updateStatus(){
		//session changes
				HttpSession session = ServletActionContext.getRequest().getSession(
						false);
				if (session == null || session.getAttribute("login") == null) {
					return "login";
				} else {
					// //System.out.println("inside else");
					if(status!=null && !(status.equals(""))){
						System.out.println("status in action::::"+status);
					int profileid = (Integer) session.getAttribute("profileId");
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timeStamp =formatter.format( Calendar.getInstance().getTime());
		
		statusModel.addStatus(status,timeStamp,profileid);
		
					}
					return "success";
				}
	}
	
	public String tempReturnSuccess1(){
		System.out.println("inside temp return:::::"+profileId);
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
			System.out.println(profileId);
			if(profileId!=0){
				if(profileid==profileId)
					return "profile";
				else
					return "profileHype";
			}
			else
				return "success";
		}
	}

	public String tempReturnSuccess(){
		return "success";
	}
	
	
	public int getProfileId() {
		return profileId;
	}


	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

}
