package edu.iiitb.facebook.action;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.ForgotPasswordDao;
import edu.iiitb.facebook.model.Profile;


public class ForgotPasswordAction extends ActionSupport{
	String userIdentity;
	String username;
	String emailId;
	String firstName;
	String lastName;
	int loginId;
	String enteredCode;
	String newPassword;
	String reEnteredPassword;
	boolean isUsername = false;
	
	int profilePicId;
	private long phoneNo;
	
	public String forgotPassword(){
		Profile user = new Profile();
		String status = null;
		if(hasChar(userIdentity)){
			
		if(userIdentity.contains("@")){
		if(userIdentity.contains("@facebook.com"))
			 isUsername = true;
		else{
			emailId = userIdentity;
			user = ForgotPasswordDao.getIdentity(emailId);
			System.out.println("in action check user profpicid");
			if(user!=null)
				 status = "success"; 
			else
			{	 
			addActionError("The email id or username you entered does not belong to any account"); 
			status = "failure";
			}
				 }
		}
		else{
			isUsername = true;
		}
		
		if(isUsername==true){
			
			username = userIdentity;
			if(userIdentity.contains("@facebook.com"))
			 emailId = ForgotPasswordDao.getUserIdentity(username);
			else
				 emailId = ForgotPasswordDao.getUserIdentity(username+"@facebook.com");
		    if(emailId != null)
			 user = ForgotPasswordDao.getIdentity(emailId);
				else
					user = null;
			 if(user!=null)
				 status = "success";
			else
			{	 
			addActionError("The username you entered does not belong to any account. Make sure that it is typed correctly"); 
			status = "failure";
			}
		
			
			}
		
		}
		else
			{phoneNo = Long.parseLong(userIdentity);
			emailId = ForgotPasswordDao.getIdentity(phoneNo);
			if(emailId!= null){
				user = ForgotPasswordDao.getIdentity(emailId);
				System.out.println("in action check user profpicid"+user.getProfilePicId());
			}
			else
				user = null;
			if(user!=null)
				 status = "success";
			else
			{	 
			addActionError("The phone number you entered does not belong to any account"); 
			status = "failure";
			}
			}
		if(user!=null){
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setProfilePicId(user.getProfilePicId());
		setLoginId(user.getLoginId());
		setEmailId(emailId);
		System.out.println("in if login id"+loginId);
		System.out.println("in if profilePicId  "+profilePicId);
		}
		System.out.println("in forgotpasswordaction loginid"+firstName);
		System.out.println("in forgotpasswordaction loginid"+lastName);
		System.out.println("in forgotpasswordaction loginid"+loginId);
		System.out.println("in forgotpasswordaction status"+status);
		System.out.println(" user in forgot password email id "+emailId);
		return status;
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getProfilePicId() {
		return profilePicId;
	}
	public void setProfilePicId(int profilePicId) {
		this.profilePicId = profilePicId;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserIdentity() {
		return userIdentity;
	}
	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}
	public String getEnteredCode() {
		return enteredCode;
	}
	public void setEnteredCode(String enteredCode) {
		this.enteredCode = enteredCode;
	}

	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getReEnteredPassword() {
		return reEnteredPassword;
	}
	public void setReEnteredPassword(String reEnteredPassword) {
		this.reEnteredPassword = reEnteredPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean hasChar(String checkField){
		int i=0; 
		while(i < checkField.length()) {
	        if (!Character.isDigit(checkField.charAt(i))) {
	            return true;
	        }
	        i++;
	    }
		return false;

	}
	public String checkCode(){
		String status;
		if(ForgotPasswordDao.checkCode(1,Integer.parseInt(enteredCode)).equals("code matched"))
		status = "success";
		else
			status = "failure";
			
			return status;
	}
	
	public String resetPassword(){
		String status = null;
		ForgotPasswordDao forgotPassword = new ForgotPasswordDao();
		System.out.println("loginid"+loginId);
		if(forgotPassword.resetPassword( newPassword, loginId).equals("password reset successfully"))
		{	status = "success";
		    addActionError("Password reset successfully");
		}
		else 
			status = "failure";
			return status;
	}

}