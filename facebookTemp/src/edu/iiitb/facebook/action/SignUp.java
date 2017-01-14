package edu.iiitb.facebook.action;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Login;
import edu.iiitb.facebook.model.Profile;
import edu.iiitb.facebook.model.SignUpDao;

public class SignUp extends ActionSupport implements SessionAware {
	String fname;
	String lname;
	String email;
	String password;
	String reEnteredEmail;
	String day;
	String month;
	String year;
	String genderFemale;
	String genderMale;
	public String getGenderFemale() {
		return genderFemale;
	}

	public void setGenderFemale(String genderFemale) {
		this.genderFemale = genderFemale;
	}

	public String getGenderMale() {
		return genderMale;
	}

	public void setGenderMale(String genderMale) {
		this.genderMale = genderMale;
	}

	java.sql.Date birthDate;
	int dobMonth;
	String schoolName;
	String collegeName;
	String companyName;
	String currentCity;
	String hometown;
	private SessionMap<String, Object> sessionMap;
	private String myFileContentType;
	private String myFileFileName;
	private File myFile;

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnteredEmail() {
		return reEnteredEmail;
	}

	public void setReEnteredEmail(String reEnteredEmail) {
		this.reEnteredEmail = reEnteredEmail;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	

	public String signUp() {
		String status = null;
		int loginId;
		Profile newProfile = new Profile();
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		DateFormat presentMonth = new SimpleDateFormat("MM");
		DateFormat presentDay = new SimpleDateFormat("dd");
		newProfile.setFirstName(fname);
		newProfile.setLastName(lname);
		if(genderMale!=null)
		newProfile.setGender(genderMale);
		else
			newProfile.setGender(genderFemale);
		
		setDate();
	
		newProfile.setBirthDate(birthDate);
		
		Date today = new Date();
		//System.out.println("present year "+dateFormat.format(today));
		//System.out.println("present month "+Integer.parseInt(presentMonth.format(today)));
		int age = 0;
		if(Integer.parseInt(day)>Integer.parseInt(presentDay.format(today))&&dobMonth == Integer.parseInt(presentMonth.format(today))){
			System.out.println("day greater");
			for(int i=Integer.parseInt(year)+1; i<= Integer.parseInt(dateFormat.format(today))-1; i++){
				age++;
			}
		}
		else{
		if(dobMonth > Integer.parseInt(presentMonth.format(today))){
		//	System.out.println("month greater");
			for(int i=Integer.parseInt(year)+1; i<= Integer.parseInt(dateFormat.format(today))-1; i++){
				age++;
			}
		}
		else{
		for(int i=Integer.parseInt(year)+1; i<= Integer.parseInt(dateFormat.format(today)); i++){
			age++;
		}
		}
		}
		//System.out.println("age "+age);
		if(age<18){
			addActionError("Age should be more than 18 years to create an account");
			status = "failure";
		}
	
		
		else
		{loginId = SignUpDao.createAccount(email, password, fname, lname);
		newProfile.setProfileId(SignUpDao.addNewProfile(newProfile, loginId));
		if (newProfile.getProfileId() != 0) {
			status = "success";
			sessionMap.put("login", "true");
			sessionMap.put("profileId", newProfile.getProfileId());
		} else
			status = "failure";
		}
		return status;
	}

	public String fillOutInfo() {
		int profileId;
		String status = null;
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			profileId = (Integer) session.getAttribute("profileId");
			// System.out.println("pid in show"+profileId);
		}
		if (myFile != null) {
			FacebookDAO.insertProfileImage(myFile, profileId, "Profile_pic",
					"Bangalore");
			System.out.println("FILE==" + myFile);
			System.out.println("SHUBHAM12");
		}
		if (SignUpDao.fillOutInfo(profileId, currentCity, hometown,
				companyName, schoolName, collegeName).equals(
				"info added successfully")) {
			status = "success";
		} else {
			status = "failure";
		}
		return status;
	}

	public void setDate() {
		switch (month.charAt(0)) {
		case 'J':
			if (month.charAt(1) == 'a' && month.charAt(2) == 'n')
				dobMonth = 1;
			else if (month.charAt(2) == 'n' && month.charAt(1) == 'u')
				dobMonth = 6;
			else
				dobMonth = 7;
			break;
		case 'F':
			dobMonth = 2;
			break;
		case 'M':
			if (month.charAt(2) == 'r')
				dobMonth = 3;
			else
				dobMonth = 5;

			break;
		case 'A':
			if (month.charAt(2) == 'r')
				dobMonth = 4;
			else
				dobMonth = 8;
			break;
		case 'S':
			dobMonth = 9;
			break;
		case 'O':
			dobMonth = 10;
			break;
		case 'N':
			dobMonth = 11;
			break;
		case 'D':
			dobMonth = 12;
			break;
		}
		// birthDate = (java.sql.Date) new Date(Integer.parseInt(year),
		// dobMonth, Integer.parseInt(day));
		birthDate = java.sql.Date.valueOf(Integer.parseInt(year) + "-"
				+ dobMonth + "-" + Integer.parseInt(day));

	}

	public void setSession(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sessionMap = (SessionMap) map;
	}
}
