package edu.iiitb.facebook.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.facebook.action.placeAction;
import edu.iiitb.facebook.action.workAndEducation;
import edu.iiitb.facebook.util.DB;

public class workAndEducationDAO {
	
	public static int addWork(String work, int profileId) {
		//insert into userStatus Table
		String insertSQL = "insert into WorkPlace "
				+ "(companyName, profileId) " + "values('" + work
				+ "', "+ profileId +");";
		//System.out.println(insertSQL);
		return DB.update(insertSQL);
		
	}
	
	public static void deleteWork(int profileId, String companyName) {

		String query = "delete from WorkPlace where profileId=" + profileId
				+ " AND companyName='" + companyName + "';";
		Connection connection = DB.getConnection();
		DB.update(query);
		DB.close(connection);

	}

	public static void deleteEducation(int profileId, String schoolName) {
		String query = "delete from School where profileId=" + profileId
				+ " AND schoolName='" + schoolName + "';";
		Connection connection = DB.getConnection();
		DB.update(query);
		DB.close(connection);
	}

	/*----------------------------SHUBHAM CODE START-------------------------------------------*/

	public static int editWorkDetail(int workPlaceId,String companyName,String jobProfile,String startDate,String endDate) {
		
		String updateSQL="UPDATE WorkPlace SET companyName='"+companyName+"', " +
				"jobProfile='"+jobProfile+"', startDate='"+startDate+"', endDate='"+endDate+"'" +
						" WHERE workPlaceId='"+workPlaceId+"';";
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
		
	}
	
public static int editEducationDetail(int schoolId,String schoolName,String graduationYear,String concentration) {
		
		String updateSQL="UPDATE School SET schoolName='"+schoolName+"', " +
				"graduationYear='"+graduationYear+"', concentration='"+concentration+"'" +
						" WHERE schoolId='"+schoolId+"';";
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
		
	}
	
	/*----------------------------SHUBHAM CODE END-------------------------------------------*/
	
	
	public static int addEducation(String education, int profileId) {
		//insert into userStatus Table
		String insertSQL = "insert into School "
				+ "(schoolName, profileId) " + "values('" + education
				+ "', "+ profileId +");";
		//System.out.println(insertSQL);
		return DB.update(insertSQL);
		
	}
	
	public static ArrayList<workAndEducation> getWorkInfo(int profileId) {
		System.out.println("inside worklist");
		ArrayList<workAndEducation> workList = new ArrayList<workAndEducation>();
		ResultSet resultSet = null;
		String query = "select companyName from WorkPlace where profileId="+profileId+" order by workPlaceId Desc;";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				workAndEducation work=new workAndEducation();
				work.setWork(resultSet.getString("companyName"));
				workList.add(work);
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
	if(workList==null)
		System.out.println("workilist is null");
		return workList;
	}
	
	/*----------------------------SHUBHAM CODE START-------------------------------------------*/
	public static workAndEducation getWorkInfo1(int profileId,String companyName) {
		workAndEducation work = new workAndEducation();
		
		ResultSet resultSet = null;
		String query = "select * from WorkPlace where profileId="+profileId+" AND companyName ='"+companyName+"';";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				//workAndEducation work=new workAndEducation();
				work.setWorkPlaceId(resultSet.getInt("workPlaceId"));
				work.setCompanyName(resultSet.getString("companyName"));
				work.setJobProfile(resultSet.getString("jobProfile"));
				work.setStartDate(resultSet.getString("startDate"));
				work.setEndDate(resultSet.getString("endDate"));
			//workList.add(work);
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return work;
	}
	
	public static workAndEducation getEducationInfo1(int profileId,String schoolName) {
		workAndEducation education = new workAndEducation();
		ResultSet resultSet = null;
		String query = "select * from School where profileId="+profileId+" AND schoolName ='"+schoolName+"';";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				//workAndEducation education=new workAndEducation();
				education.setSchoolId(resultSet.getInt("schoolId"));
				education.setSchoolName(resultSet.getString("schoolName"));
				education.setGraduationYear(resultSet.getString("graduationYear"));
				education.setConcentration(resultSet.getString("concentration"));
				//educationList.add(education);
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return education;
	}

	/*--------------------------------SHUBHAM CODE END-----------------------------------------------*/
	public static ArrayList<workAndEducation> getEducationInfo(int profileId) {
		System.out.println("inside edu list");
		ArrayList<workAndEducation> educationList = new ArrayList<workAndEducation>();
		ResultSet resultSet = null;
		String query = "select schoolName from School where profileId="+profileId+" order by schoolId Desc;";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				workAndEducation education=new workAndEducation();
				education.setEducation(resultSet.getString("schoolName"));
				educationList.add(education);
			}
		} catch (SQLException e) {
           
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		if(educationList==null)
			System.out.println("educationlist is null");
		return educationList;
	}

}
