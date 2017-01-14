package edu.iiitb.facebook.action;

import java.util.Map;

import javax.servlet.http.Cookie;



import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import edu.iiitb.facebook.model.Login;

public class LoginAction extends ActionSupport implements SessionAware{

	private long phoneNo;
	private String emailId;
	private String password;
	private String emailOrPhone;
	private String status;
	private int profileId;
	private String username;
	private boolean isUsername=false;
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	private SessionMap<String,Object> sessionMap; 
	public String getEmailOrPhone() {
		return emailOrPhone;
	}
	public void setEmailOrPhone(String emailOrPhone) {
		this.emailOrPhone = emailOrPhone;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long l) {
		this.phoneNo = l;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String doLogin(){
	//code to merge
		if(("").equals(emailOrPhone)||emailOrPhone==null)
			{
			addActionError("Please enter the email id or phone number");
			status = "failure";
			}
	 if(("").equals(password)||password==null)
			{
			addActionError("Please enter the password");
			status = "failure";
			}
	else{
		
		 if(hasChar(emailOrPhone))//if the string has even a single char
			 {
			 if(emailOrPhone.contains("@")){//checking if it is username
			 if(emailOrPhone.contains("@facebook.com")){
				 isUsername = true;
			 }
			 else{//if not username then it has to be email id
			 setEmailId(emailOrPhone);
		 if(Login.checkValidity(emailId, password).equals("valid user")){
			 status = "success";
			 setProfileId(Login.getProfileIdByEmail(emailId));
		 sessionMap.put("login","true");
		 sessionMap.put("profileId",getProfileId());
		
		 }
		else{
			 if(Login.checkValidity(emailId, password).equals("invalid email")) {
				 addActionError("The email id or username you entered does not belong to any account. Make sure that it is typed correctly");
         		 status = "failure";
				 }
			 
		 if(Login.checkValidity(emailId, password).equals("wrong password")) {
			 addActionError("The password you entered is incorrect"); 
			 status = "failure";
		 }
		}
			 }
			 }
			 else{
				 isUsername = true;
			 }
			if(isUsername==true){
				username = emailOrPhone;
				if(emailOrPhone.contains("@facebook.com")){
					if(Login.checkValidityByUsername(username, password).equals("valid user")){
						 status = "success";
						 setProfileId(Login.getProfileIdByUsername(username));
					 sessionMap.put("login","true");
					 sessionMap.put("profileId",getProfileId());
					
					 }
					else{
						 if(Login.checkValidityByUsername(username, password).equals("invalid username")) {
							 addActionError("The username you entered does not belong to any account. Make sure that it is typed correctly");
			         		 status = "failure";
							 }
						 
					 if(Login.checkValidityByUsername(username, password).equals("wrong password")) {
						 addActionError("The password you entered is incorrect"); 
						 status = "failure";
					 }
					}
				}
				else{
					if(Login.checkValidityByUsername(username+"@facebook.com", password).equals("valid user")){
						 status = "success";
						 setProfileId(Login.getProfileIdByUsername(username+"@facebook.com"));
					 sessionMap.put("login","true");
					 sessionMap.put("profileId",getProfileId());
					
					 }
					else{
						 if(Login.checkValidityByUsername(username+"@facebook.com", password).equals("invalid username")) {
							 addActionError("The username you entered does not belong to any account. Make sure that it is typed correctly");
			         		 status = "failure";
							 }
						 
					 if(Login.checkValidityByUsername(username+"@facebook.com", password).equals("wrong password")) {
						 addActionError("The password you entered is incorrect"); 
						 status = "failure";
					 }
					}
				}
			}
		 }
		 else
		 {		
			 //System.out.println(isEmail(emailOrPhone));
		 setPhoneNo(Long.parseLong(emailOrPhone));
		 if(Login.checkValidity(phoneNo, password).equals("valid user")){
			 status = "success";
			 setEmailId(Login.getEmailIdByPhone(phoneNo));
			 setProfileId(Login.getProfileIdByEmail(getEmailId()));
			 sessionMap.put("login","true");
			 sessionMap.put("profileId",getProfileId());

		 }
		 else{
			 if(Login.checkValidity(phoneNo, password).equals("invalid phone number")) {
				 addActionError("The phone numer you entered is incorrect");
			 status = "failure";
			 }
			 if(Login.checkValidity(phoneNo, password).equals("wrong password")) {
				 addActionError("The password you entered is incorrect"); 
				 status = "failure";
			 }
			
		 }
		 }
	}
		//System.out.println();
		return status;
	}
public boolean hasChar(String checkField){
	int i=0; 
	while(i < emailOrPhone.length()) {
        if (!Character.isDigit(checkField.charAt(i))) {
            return true;
        }
        i++;
    }
	return false;

}

public String logout(){  
	
    if(sessionMap!=null){  
        sessionMap.invalidate();  
    }  
    return "success";  
}

@Override
public void setSession(Map<String, Object> map) {
	// TODO Auto-generated method stub
	 sessionMap=(SessionMap)map;
}


public String directLogin(){
	System.out.println("inside directLogin"+emailOrPhone);
	if(hasChar(emailOrPhone))
	 {
		setEmailId(emailOrPhone);
	 setProfileId(Login.getProfileIdByEmail(emailId));
	 sessionMap.put("login","true");
	 sessionMap.put("profileId",getProfileId());
	 }
	else{
		setPhoneNo(Long.parseLong(emailOrPhone));
		setEmailId(Login.getEmailIdByPhone(phoneNo));
		 setProfileId(Login.getProfileIdByEmail(getEmailId()));
		 sessionMap.put("login","true");
		 sessionMap.put("profileId",getProfileId());
	}
	return "success";
	
}
}
