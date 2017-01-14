package edu.iiitb.facebook.action;




import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;



import edu.iiitb.facebook.model.basicInfoDAO;

public class basicInfoAction {
	java.sql.Date birthDate;
	String bDate;
	int bYear;
	basicInfoAction basicInfo; 
	String relWith;
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

/*---------------------------------SHUBHAM CODE START ---------------------------------------*/
	java.sql.Date birthDate1;
	public Date getBirthDate1() {
		return birthDate1;
	}

	
	public void setBirthDate1(java.sql.Date birthDate1) {
		this.birthDate1 = birthDate1;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	String day;
	String month;
	String year;
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
	int  editday;
	int  editmonth;
	int  edityear;
	public int getEditday() {
		return editday;
	}

	public void setEditday(int editday) {
		this.editday = editday;
	}

	public int getEditmonth() {
		return editmonth;
	}

	public void setEditmonth(int editmonth) {
		this.editmonth = editmonth;
	}

	public int getEdityear() {
		return edityear;
	}

	public void setEdityear(int edityear) {
		this.edityear = edityear;
	}

	String gender1;
	String religion1;
	String interest1;
	ArrayList<String> interestedList=new ArrayList<String>();
	ArrayList<String> notInterestedList=new ArrayList<String>();
	/*---------------------------------SHUBHAM CODE END-------------------------------------*/
	public ArrayList<String> getInterestedList() {
		return interestedList;
	}

	public void setInterestedList(ArrayList<String> interestedList) {
		this.interestedList = interestedList;
	}

	public ArrayList<String> getNotInterestedList() {
		return notInterestedList;
	}

	public void setNotInterestedList(ArrayList<String> notInterestedList) {
		this.notInterestedList = notInterestedList;
	}

	public String getInterest1() {
		return interest1;
	}

	public void setInterest1(String interest1) {
		this.interest1 = interest1;
	}

	public String getReligion1() {
		return religion1;
	}

	public void setReligion1(String religion1) {
		this.religion1 = religion1;
	}

	public String getGender1() {
		return gender1;
	}

	public void setGender1(String gender1) {
		this.gender1 = gender1;
	}


	
	ArrayList<String> relStatusList;
	ArrayList<String> genderOptions;
	ArrayList<String> menWomen=new ArrayList<String>();
	public ArrayList<String> getMenWomen() {
		return menWomen;
	}

	public void setMenWomen(ArrayList<String> menWomen) {
		this.menWomen = menWomen;
	}

	public ArrayList<String> getGenderOptions() {
		return genderOptions;
	}

	public void setGenderOptions(ArrayList<String> genderOptions) {
		this.genderOptions = genderOptions;
	}


	String editParam;
	
	public String getEditParam() {
		return editParam;
	}

	public void setEditParam(String editParam) {
		this.editParam = editParam;
	}

	public ArrayList<String> getRelStatusList() {
		return relStatusList;
	}

	public void setRelStatusList(ArrayList<String> relStatusList) {
		this.relStatusList = relStatusList;
	}


	
	
	
	
	public String getRelWith() {
		return relWith;
	}

	public void setRelWith(String relWith) {
		this.relWith = relWith;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	
	public int getbYear() {
		return bYear;
	}

	public void setbYear(int bYear) {
		this.bYear = bYear;
	}


	String interests;
	String religion;
	String gender;
	String relationship;
	
	

	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String retrieveBasicInfo(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
		
		 basicInfo=basicInfoDAO.getBasicInfo(profileid);
		if(basicInfo!=null){
			//System.out.println("inside if");
			//System.out.println("birth date:::"+basicInfo.getBirthDate());
			
		return "success";
		
		}
		}
		return "failure";
	}

	public basicInfoAction getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(basicInfoAction basicInfo) {
		this.basicInfo = basicInfo;
	}
	
	
	public String retrieveRelStatusList(){
		//System.out.println("isnide retrieveRelStatusList");
		relStatusList=basicInfoDAO.getRelStatusList();
		if(relStatusList!=null){
			//System.out.println("inside  if list not null");
			return "success";
		}
		return "failure";
	}
	
	public String saveRelStatus(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			int success=basicInfoDAO.updateRelStatus(relStatusList.get(0),relWith,profileid);
			return "success";
		
		}
	}
	public String deleteRelStatus(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			int success=basicInfoDAO.updateRelStatus(null,relWith,profileid);
			return "success";
		
		}
	}
	public String gender_temp(){
		System.out.println("inside gender_temp:::"+editParam);
		if(editParam.equals("interests")){
			menWomen.add("Men");
			menWomen.add("Women");
			
		}
		return editParam;
	}
	
	public String saveGender(){
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			
			int success=basicInfoDAO.updateGender(genderOptions.get(0),profileid);
			return "success";
		
		}
	}
		
		public String saveInterestedIn(){
			System.out.println("inside in save interested in");
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				if(menWomen.size()==1)
					basicInfoDAO.updateInterestedIn(menWomen.get(0),profileid);
				else
					basicInfoDAO.updateInterestedIn("Men and Women",profileid);
				System.out.println("before returning success inside intersets action");
				return "success";
			
			}
	}
		
		public String saveReligion(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				
					basicInfoDAO.updateReligion(religion,profileid);
				
				return "success";
			
			}
	}
		public String deleteReligion(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				religion=null;
					basicInfoDAO.updateReligion(religion,profileid);
				
				return "success";
			
			}
	}
		
		
		public String retrieveBasicInfoForHyperlink(){
			setIsHyperlink("true");
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				//int profileid = (Integer) session.getAttribute("profileId");
			
			 basicInfo=basicInfoDAO.getBasicInfo(profileId);
			if(basicInfo!=null){
				//System.out.println("inside if");
				//System.out.println("birth date:::"+basicInfo.getBirthDate());
				
			return "success";
			
			}
			}
			return "failure";
		}




/*------------------------------SHUBHAM CODE START----------------------*/

		
		
		public String editReligion1(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				
					//basicInfoDAO.updateReligion(religion,profileid);
				
				return "success";
			
			}
	}
		
	
		public String editBirthDate1(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				System.out.println("DOB=="+birthDate1);
				int  editday=birthDate1.getDate();
				int editmonth=birthDate1.getMonth();
				int edityear=birthDate1.getYear();
				
				System.out.println("DATE=="+editday);

				System.out.println("Month=="+editmonth);

				System.out.println("Year=="+edityear);
				
				return "success";
			
			}
	}
		public String editBirthDate2(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				System.out.println("DOB in editBirthDate2=="+birthDate1);
				System.out.println("DOB in editBirthDate2=="+day);
				System.out.println("DOB in editBirthDate2=="+month);
				System.out.println("DOB in editBirthDate2=="+year);
				//year=year+1900;
				//SignUp s=new SignUp();
				//Date birthDate1=s.setDate(day,month,year);
				setDate();
				basicInfoDAO.updatebirthDate(birthDate,profileid);
				
				
				//newProfile.setBirthDate(birthDate);
				/*int  editday=birthDate1.getDate();
				int editmonth=birthDate1.getMonth();
				int edityear=birthDate1.getYear();
				
				System.out.println("DATE=="+editday);

				System.out.println("Month=="+editmonth);

				System.out.println("Year=="+edityear);
				*/
				return "success";
			
			}
			
	}
	/*	public String deleteBirthDate(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				System.out.println("DOB in editBirthDate2=="+birthDate1);
				System.out.println("DOB in editBirthDate2=="+day);
				System.out.println("DOB in editBirthDate2=="+month);
				System.out.println("DOB in editBirthDate2=="+year);
				//year=year+1900;
				//SignUp s=new SignUp();
				//Date birthDate1=s.setDate(day,month,year);
				setDate();
				//birthDate1=null;
				basicInfoDAO.deletebirthDate1(birthDate1,profileid);
				
				
				//newProfile.setBirthDate(birthDate);
				int  editday=birthDate1.getDate();
				int editmonth=birthDate1.getMonth();
				int edityear=birthDate1.getYear();
				
				System.out.println("DATE=="+editday);

				System.out.println("Month=="+editmonth);

				System.out.println("Year=="+edityear);
				
				return "success";
			
			}
			
	}
*/		public int getDobMonth() {
			return dobMonth;
		}

		public void setDobMonth(int dobMonth) {
			this.dobMonth = dobMonth;
		}
		int dobMonth;
		public void setDate(){
			try{
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
				//birthDate = (java.sql.Date) new Date(Integer.parseInt(year), dobMonth, Integer.parseInt(day));
			birthDate = java.sql.Date.valueOf(Integer.parseInt(year)+"-"+dobMonth+"-"+Integer.parseInt(day));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			//return birthDate;
			
		}

		public String editInterests1(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				System.out.println("INTERESTED IN=="+interest1);
				/*interestedList.clear();
				notInterestedList.clear();*/
				if(interest1.equalsIgnoreCase("Men and Women"));
				{
					interestedList.add("Men");
					interestedList.add("Women");
					
				}
				if(interest1.equalsIgnoreCase("Men"))
				{
					interestedList.clear();
					notInterestedList.clear();
					interestedList.add("Men");
					notInterestedList.add("Women");
				}
				if(interest1.equalsIgnoreCase("Women"))
				{
					interestedList.clear();
					notInterestedList.clear();
					interestedList.add("Women");
					notInterestedList.add("Men");
				}
				
				return "success";
			
			}
	}
		
		public String editInterests2(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				int profileid = (Integer) session.getAttribute("profileId");
				System.out.println("INTERESTED IN=="+interest1);
				menWomen.clear();
				menWomen.addAll(interestedList);
				menWomen.addAll(notInterestedList);
				System.out.println("INTERESTED ***************************************************************************************************IN=="+interest1);
				System.out.println("Size of list=="+menWomen.size());
				
				
				if(menWomen.size()==0)
				{
					basicInfoDAO.updateInterestedIn(null,profileid);
				}
				else if(menWomen.size()==1)
					basicInfoDAO.updateInterestedIn(menWomen.get(0),profileid);
				else
					basicInfoDAO.updateInterestedIn("Men and Women",profileid);
				
				
			return "success";
			
			}
	}
		/*------------------------------SHUBHAM CODE END----------------------*/
}