package edu.iiitb.facebook.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.workAndEducationDAO;

public class workAndEducation {
	String work;
	String education;
	String isHyperlink;
	public String getIsHyperlink() {
		return isHyperlink;
	}


	public void setIsHyperlink(String isHyperlink) {
		this.isHyperlink = isHyperlink;
	}


	ArrayList<workAndEducation> workList;
	ArrayList<workAndEducation> educationList;
	int profileId;
	public int getProfileId() {
		return profileId;
	}


	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}


	/*----------------------------------------START SHUBHAM CODE-------------------------------*/
	int workPlaceId;
	String companyName;
	String jobProfile;
	String StartDate;
	String endDate;
	//ArrayList<workAndEducation> workDetail;
	workAndEducation workDetail;
	
	
	
	int schoolId;
	String schoolName;
	String graduationYear;
	String concentration;
	
	workAndEducation educationDetail;
	/*----------------------------------------END SHUBHAM CODE-------------------------------*/
	
	


	
	public int getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getGraduationYear() {
		return graduationYear;
	}


	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}


	public String getConcentration() {
		return concentration;
	}


	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}


	public workAndEducation getEducationDetail() {
		return educationDetail;
	}


	public void setEducationDetail(workAndEducation educationDetail) {
		this.educationDetail = educationDetail;
	}


	public ArrayList<workAndEducation> getWorkList() {
		return workList;
	}


	public int getWorkPlaceId() {
		return workPlaceId;
	}


	public void setWorkPlaceId(int workPlaceId) {
		this.workPlaceId = workPlaceId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getJobProfile() {
		return jobProfile;
	}


	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}


	public String getStartDate() {
		return StartDate;
	}


	public void setStartDate(String startDate) {
		StartDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public void setWorkList(ArrayList<workAndEducation> workList) {
		this.workList = workList;
	}


	public ArrayList<workAndEducation> getEducationList() {
		return educationList;
	}


	public void setEducationList(ArrayList<workAndEducation> educationList) {
		this.educationList = educationList;
	}


	public String getWork() {
		return work;
	}


	public void setWork(String work) {
		this.work = work;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String storeWork(){
		//System.out.println("inside storeWork");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			if(work!=null && !(work.equals("")))
			{
				System.out.println("work in actionnnn:::"+work);
			int profileid = (Integer) session.getAttribute("profileId");
			int success=workAndEducationDAO.addWork(work,profileid);
			work=null;
			return "success";
			}
			else
				return "failure";
		
		}
	}
	
	
	public String storeEducation(){
		//System.out.println("inside storeEducation");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			if(education!=null && !(education.equals(""))){
			int profileid = (Integer) session.getAttribute("profileId");
			int success=workAndEducationDAO.addEducation(education,profileid);
		return "success";
			}
			else
				return "failure";
		}
	}
	
	
	public String retreiveWorkAndEducation(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			workList=workAndEducationDAO.getWorkInfo(profileid);
			educationList=workAndEducationDAO.getEducationInfo(profileid);
			
			
			return "success";
		}
		
	}
	
	public String retreiveWorkAndEducationForHyperlink(){
		//System.out.println("inside retreuve");
		setIsHyperlink("true");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			//int profileid = (Integer) session.getAttribute("profileId");
			workList=workAndEducationDAO.getWorkInfo(profileId);
			educationList=workAndEducationDAO.getEducationInfo(profileId);
			
			
			return "success";
		}
		
	}
	/*--------------------------------------START SHUBHAM CODE------------------------------*/
	public String editWork1(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			workDetail=workAndEducationDAO.getWorkInfo1(profileid,companyName);
			//System.out.println(workDetail.companyName);
			//System.out.println(workDetail.jobProfile);	
			//System.out.println(workDetail.StartDate);	
			//System.out.println(workDetail.endDate);	
	/*		//System.out.println(workDetail.get(0).companyName);
			//System.out.println(workDetail.get(0).jobProfile);
			//System.out.println(workDetail.get(0).StartDate);
			//System.out.println(workDetail.get(0).endDate);
			
		*/	//System.out.println("inside editwork1"+workPlaceId);
			return "success";
		}
		
	}

	public String editWork2(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			
			//System.out.println("inside editwork2"+workPlaceId);
			int profileid = (Integer) session.getAttribute("profileId");
			int success=workAndEducationDAO.editWorkDetail(workPlaceId,companyName,jobProfile,StartDate,endDate);
		/*	workDetail=workAndEducationDAO.getWorkInfo1(profileid,companyName);
			//System.out.println(workDetail.companyName);
			//System.out.println(workDetail.jobProfile);	
			//System.out.println(workDetail.StartDate);	
			//System.out.println(workDetail.endDate);	
			//System.out.println(workDetail.get(0).companyName);
			//System.out.println(workDetail.get(0).jobProfile);
			//System.out.println(workDetail.get(0).StartDate);
			//System.out.println(workDetail.get(0).endDate);
			
		*/	
			return "success";
		}
		
	}
	public String deleteWork(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			workAndEducationDAO.deleteWork(profileid,companyName);
		
			return "success";
		}
		
	}
	
	public String editEducation1(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			educationDetail=workAndEducationDAO.getEducationInfo1(profileid,schoolName);
	/*		//System.out.println(workDetail.companyName);
			//System.out.println(workDetail.jobProfile);	
			//System.out.println(workDetail.StartDate);	
			//System.out.println(workDetail.endDate);	
			//System.out.println(workDetail.get(0).companyName);
			//System.out.println(workDetail.get(0).jobProfile);
			//System.out.println(workDetail.get(0).StartDate);
			//System.out.println(workDetail.get(0).endDate);
			
		*/	//System.out.println("inside editschool1"+schoolId);
			return "success";
		}
		
	}
	public String deleteEducation(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			System.out.println("**********SHUBHAM 1 ********************");
			workAndEducationDAO.deleteEducation(profileid,schoolName);
	
			return "success";
		}
		
	}
	public String editEducation2(){
		//System.out.println("inside retreuve");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			
			//System.out.println("inside editschool2"+schoolId);
			int profileid = (Integer) session.getAttribute("profileId");
			int success=workAndEducationDAO.editEducationDetail(schoolId,schoolName,graduationYear,concentration);
		/*	workDetail=workAndEducationDAO.getWorkInfo1(profileid,companyName);
			//System.out.println(workDetail.companyName);
			//System.out.println(workDetail.jobProfile);	
			//System.out.println(workDetail.StartDate);	
			//System.out.println(workDetail.endDate);	
			//System.out.println(workDetail.get(0).companyName);
			//System.out.println(workDetail.get(0).jobProfile);
			//System.out.println(workDetail.get(0).StartDate);
			//System.out.println(workDetail.get(0).endDate);
			
		*/	
			return "success";
		}
		
	}
	
	/*------------------------------END SHUBHAM CODE--------------------------------------------------*/
	
	
	public workAndEducation getWorkDetail() {
		return workDetail;
	}


	public void setWorkDetail(workAndEducation workDetail) {
		this.workDetail = workDetail;
	}


}
