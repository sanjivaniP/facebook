package edu.iiitb.facebook.model;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import edu.iiitb.facebook.util.DB;

public class FacebookDAO {

	public static ArrayList<Profile> getSearchResult(String searchText,int userId) {
		ArrayList<Profile> searchResult = new ArrayList<Profile>();
		PreparedStatement stmt = null;
		ArrayList<Integer> users=new ArrayList<Integer>();
		Connection con;
		if(searchText==null || searchText=="")
		{
			return null;
		}
		String[] inputTextList=searchText.split(" ");
		if(inputTextList.length>2)
		{
			return null;
		}
		else
		{
			String firstName=inputTextList[0];
			String lastName="";
			if(inputTextList.length==2)
				lastName=inputTextList[1];
			
		try {
			con = DB.getConnection();
			
			
		
			
			 String query = "select p.loginId loginId, p.profileId profileId, " +
					"p.firstName firstName, p.lastName lastName, profilePicId profilePicId from Profile p " +
					"where p.firstName like ? and  IF(p.lastName IS NULL,'',p.lastName) like ?";
			
			stmt = con.prepareStatement(query);

			stmt.setString(1, firstName+"%");
			stmt.setString(2, lastName+"%");
			
			ResultSet rs = stmt.executeQuery();
			System.out.println(stmt+"&&&&&");
			while (rs.next()) {
				
				Profile p=new Profile();
				p.setLoginId(rs.getInt("loginId"));
				p.setProfileId(rs.getInt("profileId"));
				
				p.setInfo("");
				if(p.getProfileId()!=userId)
				{
					users.add(p.getProfileId());
				p.setFirstName(rs.getString("firstName"));
				p.setLastName(rs.getString("lastName"));
				p.setProfilePicId(rs.getInt("profilePicId"));
				searchResult.add(p);
				}
			}
			
			
			/*******************************************************************************************************/
			
			query="Select p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName,IF(p.homeTown IS NULL,'',p.homeTown) homeTown," +
			 		"p.profileId profileId,p.profilePicId profilePicId from Profile p where p.homeTown like ? ";
			 stmt = con.prepareStatement(query);
				stmt.setString(1, searchText+"%");
				
				rs=stmt.executeQuery();
				while(rs.next())
				{
					int profileId=rs.getInt("profileId");
					if(!users.contains(profileId) && profileId!=userId)
					{
						
						Profile p=new Profile();
						//p.setLoginId(rs.getInt("loginId"));
						p.setProfileId(rs.getInt("profileId"));
						
						String homeTown=rs.getString("homeTown");
						p.setInfo("Hometown:"+homeTown);
						users.add(p.getProfileId());
						p.setFirstName(rs.getString("firstName"));
						p.setLastName(rs.getString("lastName"));
						p.setProfilePicId(rs.getInt("profilePicId"));
						searchResult.add(p);
						
					}
				}
				
				//searching for workplace
				query="SELECT w.profileId profileId,w.companyName companyName, p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName," +
						"p.profilePicId profilePicId FROM WorkPlace w, Profile p where w.companyName like ? and w.profileId=p.profileId";
				stmt = con.prepareStatement(query);
				stmt.setString(1, searchText+"%");
				
				rs=stmt.executeQuery();
				while(rs.next())
				{
					int profileId=rs.getInt("profileId");
					if(!users.contains(profileId) && profileId!=userId)
					{
						
						Profile p=new Profile();
						//p.setLoginId(rs.getInt("loginId"));
						p.setProfileId(rs.getInt("profileId"));
						
						String companyName=rs.getString("companyName");
						p.setInfo("Worked in:"+companyName);
						users.add(p.getProfileId());
						p.setFirstName(rs.getString("firstName"));
						p.setLastName(rs.getString("lastName"));
						p.setProfilePicId(rs.getInt("profilePicId"));
						searchResult.add(p);
						
					}
				}
				
				//searching school
				query="SELECT s.profileId profileId,s.schoolName schoolName, p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName, " +
						"p.profilePicId profilePicId FROM School s, Profile p where s.schoolName like ? and s.profileId=p.profileId"; 
				stmt = con.prepareStatement(query);
				stmt.setString(1, searchText+"%");
				
				rs=stmt.executeQuery();
				while(rs.next())
				{
					int profileId=rs.getInt("profileId");
					if(!users.contains(profileId) && profileId!=userId)
					{
						
						Profile p=new Profile();
						//p.setLoginId(rs.getInt("loginId"));
						p.setProfileId(rs.getInt("profileId"));
						
						String schoolName=rs.getString("schoolName");
						p.setInfo("Studied in:"+schoolName);
						users.add(p.getProfileId());
						p.setFirstName(rs.getString("firstName"));
						p.setLastName(rs.getString("lastName"));
						p.setProfilePicId(rs.getInt("profilePicId"));
						searchResult.add(p);
						
					}
				}
				
				/***************************************************************************************************************************/
			
			ArrayList<Integer> friendsList =getFriendList(userId);
			ArrayList<Integer> friendsRequestSentList =getFriendRequestSentList(userId);
			ArrayList<Integer> friendsRequestRecievedList =getFriendsRequestRecievedList(userId);
			
			
			
			for(Profile p:searchResult)
			{
				if(friendsList.contains(p.getProfileId()))
				{
				  p.setIsFriend("Y");
				}
				else if(friendsRequestSentList.contains(p.getProfileId()))
				{
					p.setIsFriend("S");
				}
				else if(friendsRequestRecievedList.contains(p.getProfileId()))
				{
					p.setIsFriend("R");
				}
				else
					p.setIsFriend("N");
				
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 if(searchResult.size()==0)
		 return null;
	 else
	 {
		 
	 //for(Profile p:searchResult)
		 //System.out.println("Name "+p.getFirstName());
		 return searchResult;

	 }	
	}
	}
	
	private static ArrayList<Integer> getFriendsRequestRecievedList(int userId) {

		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<Integer> friendsRequestRecievedList =new ArrayList<Integer>();
		try {
			con = DB.getConnection();

			String query="SELECT  f.userId1 userId FROM Friendship f where f.userId2=? and f.areFriends='N'";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Integer temp=rs.getInt("userId");
				friendsRequestRecievedList.add(temp);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendsRequestRecievedList;

	}

	private static ArrayList<Integer> getFriendRequestSentList(int userId) {

		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<Integer> friendsRequestSentList =new ArrayList<Integer>();
		try {
			con = DB.getConnection();

			String query="SELECT  f.userId2 userId FROM Friendship f where f.userId1=? and f.areFriends='N'";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Integer temp=rs.getInt("userId");
				friendsRequestSentList.add(temp);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendsRequestSentList;

	}

	public static ArrayList<Integer> getFriendList(int userId) {

		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<Integer> friendsList = new ArrayList<Integer>();
		try {
			con = DB.getConnection();

			String query = "(SELECT  f.userId2 userId FROM Friendship f where f.userId1=? and f.areFriends='Y')"
					+ " UNION "
					+ "(SELECT  f.userId1 userId FROM Friendship f where f.userId2=? and f.areFriends='Y')";
			stmt = con.prepareStatement(query);

			stmt.setInt(1, userId);
			stmt.setInt(2, userId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Integer temp = rs.getInt("userId");
				friendsList.add(temp);

			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendsList;

	}

	public static void insertImage()
 {
		try {
			File file = new File("/home/apurva/Desktop/jj.jpg");

			FileInputStream inputStream = new FileInputStream(file);
			PreparedStatement stmt = null;
			// Get a Statement object
			Connection con;
			con = DB.getConnection();
			// //System.out.println("Semester in getSubjList " + semester);

			// ////System.out.println("Priyanka!!!!!!!!");
			String query = "insert into Photo(albumId,caption,owner,photo) VALUES ('3','profile pic','19',?)";
			// //System.out.println(query);

			stmt = con.prepareStatement(query);

			stmt.setBlob(1, inputStream,file.length());
			stmt.executeUpdate();
			con.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
			//System.out.println(e);
		}

	}
	
	public static void main(String arg[])
	{
		insertImage();
	}

	public static InputStream getPhoto(int photoId) {
		
		InputStream binaryStream=null;
		
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		// TODO Auto-generated method st
		try{
			con=DB.getConnection();
		String query = "SELECT photo photo FROM Photo where photoId=?";
		
		stmt = con.prepareStatement(query);

		stmt.setInt(1, photoId);
		
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			binaryStream = rs.getBinaryStream("photo");
			
		}
		
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}		
		return binaryStream;
	}

	public static Profile getProfile(int profileId) {
/*		EXAM CODE 1
		int totalAge = 0;
		int totalFriend=0;
		int avgAge;
*/		
		PreparedStatement stmt = null;
		Profile user=new Profile();
	
		Connection con;
		
		try{
			/*
			String query = "SELECT profileId,p.firstName firstName,lastName, p.profilePicId profilePicId FROM Profile p where p.profileId=?";
			*/
			con=DB.getConnection();
		String query = "SELECT * FROM Profile p where p.profileId=?";
		
		stmt = con.prepareStatement(query);

		stmt.setInt(1, profileId);
		ResultSet rs = stmt.executeQuery();
		
		/*------------------------------------EXAM CODE-----------------------------------*/
	/*	
	 * EXAM CODE 2
	 * Date currentDate=new Date();
		int currentyear=currentDate.getYear();
		int age;
	*/	
		
		while (rs.next()) {
			user.setFirstName(rs.getString("firstName"));
			//System.out.println(user.getFirstName());
			user.setProfilePicId(rs.getInt("profilePicId"));
			user.setLastName(rs.getString("lastName"));
			user.setProfileId(rs.getInt("profileId"));
			
			
			user.setBirthDate(rs.getDate("birthDate"));
			System.out.println(user.getBirthDate());

		/*	
		 * EXAM CODE 3
		 * Date d=rs.getDate("birthDate");
			@SuppressWarnings("deprecation")
			int friend_year=d.getYear();
			currentyear=currentyear+1900;
			friend_year=friend_year+1900;
			age=currentyear-friend_year;
			user.setAgeMT2013148(age);
			
			totalAge=age+totalAge;
			totalFriend=totalFriend+1;
		*/	/*System.out.println("Total Friend=="+totalFriend);
			System.out.println("total AGE=="+totalAge);
			System.out.println("YEAR=="+friend_year+"AGE=="+age);
		*/}
		
	
		con.close();
	} catch (Exception e) {
		e.printStackTrace();
	}		
	/*
	 * EXAM CODE 4
	 * avgAge=totalAge/totalFriend;
	*/	
	/*	System.out.println("AVEGRAGE AGE="+avgAge);
	*/	return user;
	}
	
	/**
	 * sends a friend request from user1 to user2
	 * @param userId1
	 * @param userId2
	 */
	public static void sendFriendRequest(int userId1, int userId2) {



		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		
		try {
			con = DB.getConnection();

			String query = "INSERT into Friendship (userId1,userId2) values(?,?)";
			stmt = con.prepareStatement(query);

			stmt.setInt(1, userId1);
			stmt.setInt(2, userId2);
			System.out.println(stmt+"^^^^^^^^^^^^^^^^^");
			 stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	
	
	}

	public static void unFriend(int userId1, int userId2) {



		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		
		try {
			con = DB.getConnection();

			String query = "SELECT friendshipId FROM Friendship where (userId1=? AND userId2=?) OR (userId1=? AND userId2=?)";
			stmt = con.prepareStatement(query);

			stmt.setInt(1, userId1);
			stmt.setInt(2, userId2);
			stmt.setInt(3, userId2);
			stmt.setInt(4, userId1);
			//System.out.println(stmt);
			int friendshipId=0;
			 ResultSet rs=stmt.executeQuery();
			 
				 while(rs.next())
				  {
					  friendshipId=rs.getInt("friendshipId");
				  }
				 
			
			 
			 
			 query="DELETE  FROM Friendship where friendshipId=?";
			 stmt = con.prepareStatement(query);
			 stmt.setInt(1, friendshipId);
			 //System.out.println(stmt);
			 stmt.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	
	
	}
	
public static void confirmFriendRequest(int userId1, int userId2) {

		




		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		
		try {
			con = DB.getConnection();

			String query = "update Friendship set areFriends='Y' where (userId1=? and userId2=?) or (userId1=? and userId2=?) ";
			stmt = con.prepareStatement(query);

			stmt.setInt(1, userId1);
			stmt.setInt(2, userId2);
			stmt.setInt(3, userId2);
			stmt.setInt(4, userId1);
			
			stmt.executeUpdate();
			 
					
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	
	
	
		
		
	}


	//apurva changes
	public static ArrayList<Profile> getFriends(int profileId){
		System.out.println("inside getFriends:::"+profileId);
		ArrayList<Integer> friendList=getFriendList(profileId);
		ArrayList<Profile> friendProfileList=new ArrayList<Profile>();
		
		for(int userId:friendList){
			System.out.println("userID in getfriends");
			Profile profile=getProfile(userId);
			
			friendProfileList.add(profile);
		}
		System.out.println(friendList.size());
		//friendProfileList.get(0).getAgeMT2013148();
		return friendProfileList;
	}
	/*----------------------------EXAM CODE---------------------------------------------------------*/
	/*
	 * EXAM CODE 5
	public static ArrayList<Profile> getFriends1(int profileId){
		System.out.println("inside getFriends:::"+profileId);
		ArrayList<Integer> friendList=getFriendList(profileId);
		ArrayList<Profile> friendProfileList=new ArrayList<Profile>();
		
		for(int userId:friendList){
			System.out.println("userID in getfriends");
			Profile profile=getProfile(userId);
			
			friendProfileList.add(profile);
		}
		System.out.println(friendList.size());
		int totalfriend=0;
		int totalage=0;
		totalfriend=friendProfileList.size();
		for(int i=0;i<friendProfileList.size();i++)
		{
			totalage=totalage+friendProfileList.get(i).getAgeMT2013148();
		}
		int avgAge;
		if(totalfriend!=0)
		{
		avgAge=totalage/totalfriend;
		
		}
		else
		{
			avgAge=0;
		}//friendProfileList.get(0).getAgeMT2013148();
		return friendProfileList;
	}
	
	
*/	/*----------------------------EXAM CODE---------------------------------------------------------*/
	
	
	//apurva changes end
	
	
	//shefalie changes
	
	public static ArrayList<Friend> getFriendsRequestRecievedListHome(int userId) {

		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<Friend> friendsRequestRecievedList =new ArrayList<Friend>();
		try {
			con = DB.getConnection();

			//String query="SELECT  f.userId1 userId FROM facebook.Friendship f where f.userId2=? and f.areFriends='N'";
			//userId2 is the is of the logged in user
	String query = "SELECT  f.userId1 userId,p.profilePicId profilePicId,p.firstName Name,IF(p.lastName IS NULL,'',p.lastName) AS lastName FROM Friendship f, Profile p where f.userId2=? and f.userId1=p.profileId and f.areFriends='N'";
			
			stmt = con.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				//Integer temp=rs.getInt("userId");
				Friend frnd = new Friend();
				frnd.setUserId(rs.getInt("userId"));
				frnd.setName(rs.getString("Name")+" "+rs.getString("lastName"));
				frnd.setProfilePicId(rs.getInt("profilePicId"));
				//System.out.println(frnd.getName()+"shefu!!");
				/*frnd.setlastName(rs.getString("lastName"));*/
			
				friendsRequestRecievedList.add(frnd);
				
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return friendsRequestRecievedList;

	}
	
	
	public static void acceptRequest(int userId1, int userId2){
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		try{
			con = DB.getConnection();

			String query = "UPDATE Friendship set areFriends='Y' where userId1=? and userId2=?";
			System.out.println(query);
			stmt = con.prepareStatement(query);

			stmt.setInt(1, userId1);
			stmt.setInt(2, userId2);

			 stmt.executeUpdate();
			
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		}
	public static void rejectRequest(int userId1,int userId2){
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		try{
			con = DB.getConnection();

			String query = "DELETE from Friendship where userId1=? and userId2=?";
			System.out.println(query);
			stmt = con.prepareStatement(query);

			stmt.setInt(1, userId1);
			stmt.setInt(2, userId2);

			 stmt.executeUpdate();
			
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Profile> getProfileList(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 	public static ArrayList<String> getNames(String searchText) {
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<String> list=new ArrayList<String>();
		try{
			con = DB.getConnection();
			System.out.println(searchText+"5%%%%%%");
			String query = "Select p.firstName firstName from Profile p where p.firstName like ?";
			
			stmt = con.prepareStatement(query);
			stmt.setString(1, searchText+"%");
			System.out.println(stmt);
			 ResultSet rs=stmt.executeQuery();
			 
			 while(rs.next())
			 { String str=rs.getString("firstName");
			 	String temp="<a href=\"https://www.google.co.in/\"><img  width=\"25\" height=\"25\" src=\"image?photoId=1\"><b>"+str+"</b>" +
			 			"<br/>something something</a>";
				 list.add(temp);
			 }
			
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
 
	 back up code*/
	
	
	public static ArrayList<String> getAutosuggest(String searchText,int userId) {
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<String> list=new ArrayList<String>();
		try{
			
			String[] inputTextList=searchText.split(" ");
			if(inputTextList.length>2)
			{
				return null;
			}
			else
			{
				String firstName=inputTextList[0];
				String lastName="";
				if(inputTextList.length==2)
					lastName=inputTextList[1];
				
			con = DB.getConnection();
			
			String query = "Select p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName,IF(p.homeTown IS NULL,'',p.homeTown) homeTown," +
					"p.profileId profileId,p.profilePicId profilePicId from Profile p where p.firstName like ? and IF(p.lastName IS NULL,'',p.lastName) like ?";
			
			stmt = con.prepareStatement(query);
			stmt.setString(1, firstName+"%");
			stmt.setString(2, lastName+"%");
			//System.out.println(stmt);
			 ResultSet rs=stmt.executeQuery();
			ArrayList<Integer> users=new ArrayList<Integer>();
			 
			 while(rs.next())
			 { String fname=rs.getString("firstName");
			 	String lname=rs.getString("lastName");
			 	//String homeTown=rs.getString("homeTown");
			 	int profileId=rs.getInt("profileId");
			 	users.add(profileId);
			 	int profilePicId=rs.getInt("profilePicId");
			 	String finalName="";
			 	//adding highlight
			 	if(((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())>=0)
			 	{	int pos=((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase());
			 		int l=(fname+" "+lname).length();
			 		int l1=searchText.length();
			 		System.out.println("$$$$$$"+((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())+"$$$$$"+(searchText).indexOf(fname+" "+lname)+" "+searchText+" "+fname+" "+lname);
				 	finalName=(fname+" "+lname).substring(0,pos);
				 	finalName=finalName+"<mark>";
				 	finalName=finalName+(fname+" "+lname).substring(pos,pos+l1)+"</mark>";
				 	finalName=finalName+(fname+" "+lname).substring(pos+l1,l);
			 		//="<mark>"+fname+" "+lname+"</mark>";
			 	}
			 	if(profileId!=userId)
			 	{
			 	String temp="<a href=\"profileTempAction_hype?profileId="+profileId+"\">" +
			 			"<img  width=\"25\" height=\"25\" src=\"image?photoId="+profilePicId+"\"><b>"+finalName+"</b>" +
			 			"<br/><br/></a>";
				 list.add(temp);
			 	}
			 }
			 //checking for hometown
			 query="Select p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName,IF(p.homeTown IS NULL,'',p.homeTown) homeTown," +
			 		"p.profileId profileId,p.profilePicId profilePicId from Profile p where p.homeTown like ? ";
			 stmt = con.prepareStatement(query);
				stmt.setString(1, searchText+"%");
				
				System.out.println(stmt);
				rs=stmt.executeQuery();
				while(rs.next())
				{
					int profileId=rs.getInt("profileId");
					if(!users.contains(profileId) && profileId!=userId)
					{ String fname=rs.getString("firstName");
				 	String lname=rs.getString("lastName");
				 	String homeTown=rs.getString("homeTown");
				 	//int profileId=rs.getInt("profileId");
				 	users.add(profileId);
				 	int profilePicId=rs.getInt("profilePicId");
				 	String str="";
				 	if(((homeTown).toLowerCase()).indexOf(searchText.toLowerCase())>=0)
				 	{	int pos=((homeTown).toLowerCase()).indexOf(searchText.toLowerCase());
				 		int l=(homeTown).length();
				 		int l1=searchText.length();
				 		//System.out.println("$$$$$$"+((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())+"$$$$$"+(searchText).indexOf(fname+" "+lname)+" "+searchText+" "+fname+" "+lname);
					 	str=(homeTown).substring(0,pos);
					 	str=str+"<mark>";
					 	str=str+(homeTown).substring(pos,pos+l1)+"</mark>";
					 	str=str+(homeTown).substring(pos+l1,l);
				 		//="<mark>"+fname+" "+lname+"</mark>";
				 	}
				 	
				 	String temp="<a href=\"profileTempAction_hype?profileId="+profileId+"\">" +
				 			"<img  width=\"25\" height=\"25\" src=\"image?photoId="+profilePicId+"\"><b>"+fname+" "+lname+"</b>" +
				 			"<br/>Hometown:"+str+"</a>";
					 list.add(temp);
				 
				 }
				}
				
				//if company name matches 
				query="SELECT w.profileId profileId,w.companyName companyName, p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName," +
						"p.profilePicId profilePicId FROM WorkPlace w, Profile p where w.companyName like ? and w.profileId=p.profileId";
				stmt = con.prepareStatement(query);
				stmt.setString(1, searchText+"%");
				
				rs=stmt.executeQuery();
				while(rs.next())
				{
					int profileId=rs.getInt("profileId");
					if(!users.contains(profileId) && profileId!=userId)
					{ String fname=rs.getString("firstName");
				 	String lname=rs.getString("lastName");
				 	String companyName=rs.getString("companyName");
				 	//int profileId=rs.getInt("profileId");
				 	users.add(profileId);
				 	int profilePicId=rs.getInt("profilePicId");
				 	String str="";
				 	if(((companyName).toLowerCase()).indexOf(searchText.toLowerCase())>=0)
				 	{	int pos=((companyName).toLowerCase()).indexOf(searchText.toLowerCase());
				 		int l=(companyName).length();
				 		int l1=searchText.length();
				 		//System.out.println("$$$$$$"+((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())+"$$$$$"+(searchText).indexOf(fname+" "+lname)+" "+searchText+" "+fname+" "+lname);
					 	str=(companyName).substring(0,pos);
					 	str=str+"<mark>";
					 	str=str+(companyName).substring(pos,pos+l1)+"</mark>";
					 	str=str+(companyName).substring(pos+l1,l);
				 		//="<mark>"+fname+" "+lname+"</mark>";
				 	}
				 	
				 	String temp="<a href=\"profileTempAction_hype?profileId="+profileId+"\">" +
				 			"<img  width=\"25\" height=\"25\" src=\"image?photoId="+profilePicId+"\"><b>"+fname+" "+lname+"</b>" +
				 			"<br/>Worked in:"+str+"</a>";
					 list.add(temp);
				 
				 }
				}
				
				//school name
				query="SELECT s.profileId profileId,s.schoolName schoolName, p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName, " +
						"p.profilePicId profilePicId FROM School s, Profile p where s.schoolName like ? and s.profileId=p.profileId"; 
				stmt = con.prepareStatement(query);
				stmt.setString(1, searchText+"%");
				
				rs=stmt.executeQuery();
				while(rs.next())
				{
					int profileId=rs.getInt("profileId");
					if(!users.contains(profileId) && profileId!=userId)//if already in the search list
					{ String fname=rs.getString("firstName");
				 	String lname=rs.getString("lastName");
				 	String schoolName=rs.getString("schoolName");
				 	//int profileId=rs.getInt("profileId");
				 	users.add(profileId);
				 	int profilePicId=rs.getInt("profilePicId");
				 	String str="";
				 	if(((schoolName).toLowerCase()).indexOf(searchText.toLowerCase())>=0)
				 	{	int pos=((schoolName).toLowerCase()).indexOf(searchText.toLowerCase());
				 		int l=(schoolName).length();
				 		int l1=searchText.length();
				 		//System.out.println("$$$$$$"+((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())+"$$$$$"+(searchText).indexOf(fname+" "+lname)+" "+searchText+" "+fname+" "+lname);
					 	str=(schoolName).substring(0,pos);
					 	str=str+"<mark>";
					 	str=str+(schoolName).substring(pos,pos+l1)+"</mark>";
					 	str=str+(schoolName).substring(pos+l1,l);
				 		//="<mark>"+fname+" "+lname+"</mark>";
				 	}
				 	
				 	String temp="<a href=\"profileTempAction_hype?profileId="+profileId+"\">" +
				 			"<img  width=\"25\" height=\"25\" src=\"image?photoId="+profilePicId+"\"><b>"+fname+" "+lname+"</b>" +
				 			"<br/>Studied in:"+str+"</a>";
					 list.add(temp);
				 
				 }
				}
				
				
				
			con.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

public static void addPoll(String question, List<String> optionList , int profileId) {
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		ArrayList<String> list=new ArrayList<String>();
		try{
			con = DB.getConnection();
			
			String query = "INSERT INTO Poll(question,owner) VALUES (?,?)";
			
			stmt = con.prepareStatement(query);
			stmt.setString(1, question);
			stmt.setInt(2, profileId);
			//System.out.println(stmt);
			stmt.executeUpdate();
			
			query="SELECT p.pollId pollId, p.question question FROM Poll p order by  p.pollId  desc LIMIT 1" ;
			stmt = con.prepareStatement(query);
			ResultSet rs=stmt.executeQuery();
			int qId=0;
			 while(rs.next())
			 {
				 qId=rs.getInt("pollId");
				 
			 }
			
			 for(String str:optionList)
			 {
				 query="INSERT INTO PollOption(description, countOfUsersVoted, pollId,listOfUsersVoted) VALUES (?, 0, ?,';')";
				 stmt = con.prepareStatement(query);
					stmt.setString(1, str);
					stmt.setInt(2, qId);
					//System.out.println(stmt);
					stmt.executeUpdate();
				 
				 
			 }
			 
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static Poll retrievePoll(int pollId,int userId) {
		

		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		Poll poll=new Poll();
		ArrayList<String> list=new ArrayList<String>();
		try{
			con = DB.getConnection();
			
			String query = "SELECT question from Poll where pollId=?";
			
			stmt = con.prepareStatement(query);
			stmt.setInt(1, pollId);
			//System.out.println(stmt);
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				poll.setQuestion(rs.getString("question"));
			}
			
			
			query="SELECT optionId, description,listOfUsersVoted, countOfUsersVoted where pollId=?" ;
			stmt = con.prepareStatement(query);
			stmt.setInt(1, pollId);
			//System.out.println(stmt);
			rs=stmt.executeQuery();
			int qId=0;
			
			 while(rs.next())
			 { Options op=new Options();
			 	op.setOptionId(rs.getInt("optionId"));
			 	op.setDescription(rs.getString("description"));
			 	op.setCountOfVotes(rs.getInt("countOfUsersVoted"));
			 	String listOfVoters=rs.getString("listOfUsersVoted");
			 	if(listOfVoters.contains(";"+userId+";"))
			 		op.setChecked("true");
			 		else
			 			op.setChecked("false");
			 	String[] voters=listOfVoters.split(";");
			 	int max=4;
			 	op.listOfUser=new ArrayList<Profile>();
			 	if(op.getCountOfVotes()>0 )
			 	{	if(op.getCountOfVotes()<4)
			 			max=op.getCountOfVotes();
			 		for(int i=0;i<max;i++)
			 		{
			 			op.getListOfUser().add(FacebookDAO.getProfile(Integer.parseInt(voters[i])));
			 		}
			 	}
			 	//poll.getListOfOptions();
			 	
			 }
			
			 
			 
			con.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		
		return null;
		
	}
	
	//shefali changes end


/*-----------------------------------UPLOAD COVER PIC START------------------------------------------*/

	public static void insertCoverImage(File file, int profileid,
			String caption, String location) {
		try {
			// File file = new File(picPath);

			FileInputStream inputStream = new FileInputStream(file);
			PreparedStatement stmt = null;
			// Get a Statement object
			Connection con;
			con = DB.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			System.out.println("INSIDE COVER PIC EDIT SECTION DB CLASS");
			/*
			 * Profile profile = picDAO.detailCoverPic(profileid); // GETTING
			 * THE // PROFILE TABLE // DETAIL FORM // THE PROFILE // TABLE LIKE
			 * // COVERPICID // AND // COVERPICALBUL // ID
			 */
			String query = "insert into Photo(caption,owner,photo,location,createdOn) VALUES (?,?,?,?,?)";

			// int coverepicid=picDAO.getCoverPic(profileid);

			// String
			// query="update Photo set photo = ? ,caption='h123'  where photoId="+coverepicid+" ;";
			System.out.println(query);
			// Stirng query="update Photo set photo=? where "
			stmt = con.prepareStatement(query);
			// UPDATE `facebook`.`Photo` SET `caption`='profile pic11',
			// `owner`=191 WHERE `photoId`='8';
			// stmt.setInt(1, profile.getCoverPicAlbumId());
			stmt.setString(1, "CoverPic");
			stmt.setInt(2, profileid);
			stmt.setBlob(3, inputStream, file.length());
			stmt.setString(4, location);
			stmt.setString(5, dateFormat.format(date).toString());
			System.out.println(query);

			stmt.executeUpdate();

			int maxPhotoId = findMaxPhotoId(); // GET THE MAXIMUM PHOTOID
												// PRESENT IN THE PHOTO TABLE

			updatecoverImage(profileid, maxPhotoId); // UPDATE THE COVERPICID IN
														// THE PROFILE TABLE
			con.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
			// System.out.println(e);
		}

	}

	public static int updatecoverImage(int profileid, int maxPhotoId) {
		// int maxPhotoId = 0;
		PreparedStatement stmt = null;

		try {// Get a Statement object
			Connection con;
			con = DB.getConnection();
			// String query =
			// "select photoId from Photo order by Desc photoId limit 1;";//insert
			// into Photo(albumId,caption,owner,photo,location) VALUES
			// (?,?,?,?,?)";

			String query = "update Profile set coverPicId=" + maxPhotoId
					+ " where profileId=" + profileid + ";";
			// UPDATE `facebook`.`Profile` SET `coverPicId`=11 WHERE
			// `profileId`='7';
			System.out.println(query);
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxPhotoId;
	}

	/*-----------------------------------UPLOAD COVER PIC END------------------------------------------*/

	/*------------------------------------------------PHOTO UPLOAD START-----------------------------------*/

	public static void insertPhotoUpload(File file, int profileid,
			String caption, String location) {
		try {
			// File file = new File(picPath);

			FileInputStream inputStream = new FileInputStream(file);
			PreparedStatement stmt = null;
			// Get a Statement object
			Connection con;
			con = DB.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			/*
			 * Profile profile = picDAO.detailCoverPic(profileid); // GETTING
			 * THE // PROFILE TABLE // DETAIL FORM // THE PROFILE // TABLE LIKE
			 * // COVERPICID // AND // COVERPICALBUL // ID
			 */
			String query = "insert into Photo(caption,owner,photo,location,createdOn) VALUES (?,?,?,?,?)";
			System.out.println(query);
			stmt = con.prepareStatement(query);
			stmt.setString(1, caption);
			stmt.setInt(2, profileid);
			stmt.setBlob(3, inputStream, file.length());
			stmt.setString(4, location);
			stmt.setString(5, dateFormat.format(date).toString());
			System.out.println(query);
			stmt.executeUpdate();
			con.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
			// System.out.println(e);
		}

	}

	/*------------------------------------------------PHOTO UPLOAD END-----------------------------------*/
	/*---------------------------------------------CREATE ALBUM START----------------------------*/
	public static void insertAlbumUpload(File file, int profileid,
			String photoCaption, String photoLocation, int albumId) {
		try {
			// File file = new File(picPath);

			FileInputStream inputStream = new FileInputStream(file);
			PreparedStatement stmt = null;
			// Get a Statement object
			Connection con;
			con = DB.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			/*
			 * updateAlbum(albumCaption,albumLocation,profileid);
			 * 
			 * int albumId=findMaxAlbumID();
			 */
			String query = "insert into Photo(caption,owner,photo,location,createdOn,albumId) VALUES (?,?,?,?,?,?)";
			System.out.println(query);
			stmt = con.prepareStatement(query);

			stmt.setString(1, photoCaption);
			stmt.setInt(2, profileid);
			stmt.setBlob(3, inputStream, file.length());
			stmt.setString(4, photoLocation);
			stmt.setString(5, dateFormat.format(date).toString());
			stmt.setInt(6, albumId);
			System.out.println(query);
			stmt.executeUpdate();
			con.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
			// System.out.println(e);
		}

	}

	public static void updateAlbum(String albumCaption, String albumLocation,
			int profileID) {
		try {
			Connection con;
			con = DB.getConnection();
			PreparedStatement stmt = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			// Get a Statement object
			String query = "insert into PhotoAlbum(createdOn,description,location,owner) VALUES (?,?,?,?)";
			System.out.println(query);
			stmt = con.prepareStatement(query);
			stmt.setString(1, dateFormat.format(date).toString());
			stmt.setString(2, albumCaption);
			stmt.setString(3, albumLocation);
			stmt.setInt(4, profileID);
			System.out.println(query);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int findMaxAlbumID() {
		int maxPhotoId = 0;
		PreparedStatement stmt = null;

		try {// Get a Statement object
			Connection con;
			con = DB.getConnection();
			String query = "select photoAlbumId from PhotoAlbum order by photoAlbumId Desc  limit 1;";// insert
																										// into
																										// Photo(albumId,caption,owner,photo,location)
																										// VALUES
																										// (?,?,?,?,?)";

			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				maxPhotoId = rs.getInt("photoAlbumId");
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxPhotoId;
	}

	/*-----------------------------------CREATE ALBUM END------------------------------------------*/

	/*-----------------------------------UPLOAD PROFILE PIC START------------------------------------------*/

	public static void insertProfileImage(File file, int profileid,
			String caption, String location) {
		try {
			// File file = new File(picPath);

			FileInputStream inputStream = new FileInputStream(file);
			PreparedStatement stmt = null;
			// Get a Statement object
			Connection con;
			con = DB.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			// //System.out.println("Semester in getSubjList " + semester);

			// ////System.out.println("Priyanka!!!!!!!!");

			/*
			 * Profile profile = picDAO.detailCoverPic(profileid); // GETTING
			 * THE // PROFILE TABLE // DETAIL FORM // THE PROFILE // TABLE LIKE
			 * // COVERPICID // AND // COVERPICALBUL // ID
			 */
			String query = "insert into Photo(caption,owner,photo,location,createdOn) VALUES (?,?,?,?,?)";

			// int coverepicid=picDAO.getCoverPic(profileid);

			// String
			// query="update Photo set photo = ? ,caption='h123'  where photoId="+coverepicid+" ;";
			System.out.println(query);
			// Stirng query="update Photo set photo=? where "
			stmt = con.prepareStatement(query);
			// UPDATE `facebook`.`Photo` SET `caption`='profile pic11',
			// `owner`=191 WHERE `photoId`='8';
			// stmt.setInt(1, profile.getCoverPicAlbumId());
			stmt.setString(1, "ProfilePic");
			stmt.setInt(2, profileid);
			stmt.setBlob(3, inputStream, file.length());
			stmt.setString(4, location);
			stmt.setString(5, dateFormat.format(date).toString());
			System.out.println(query);

			stmt.executeUpdate();

			int maxPhotoId = findMaxPhotoId(); // GET THE MAXIMUM PHOTOID
												// PRESENT IN THE PHOTO TABLE

			updateProfileImage(profileid, maxPhotoId); // UPDATE THE COVERPICID
														// IN THE PROFILE TABLE
			con.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
			// System.out.println(e);
		}

	}

	public static int findMaxPhotoId() {
		int maxPhotoId = 0;
		PreparedStatement stmt = null;

		try {// Get a Statement object
			Connection con;
			con = DB.getConnection();
			String query = "select photoId from Photo order by photoId Desc  limit 1;";// insert
																						// into
																						// Photo(albumId,caption,owner,photo,location)
																						// VALUES
																						// (?,?,?,?,?)";

			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				maxPhotoId = rs.getInt("photoId");
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxPhotoId;
	}

	public static int updateProfileImage(int profileid, int maxPhotoId) {
		// int maxPhotoId = 0;
		PreparedStatement stmt = null;

		try {// Get a Statement object

			Connection con;
			con = DB.getConnection();
			// String query =
			// "select photoId from Photo order by Desc photoId limit 1;";//insert
			// into Photo(albumId,caption,owner,photo,location) VALUES
			// (?,?,?,?,?)";

			String query = "update Profile set profilePicId=" + maxPhotoId
					+ " where profileId=" + profileid + ";";
			// UPDATE `facebook`.`Profile` SET `coverPicId`=11 WHERE
			// `profileId`='7';
			System.out.println(query);
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return maxPhotoId;
	}

	public static ArrayList<String> getAutosuggestMessage(String searchText,int userId) {
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		System.out.println(searchText+"$$$$$");
		ArrayList<String> list=new ArrayList<String>();
		try{
			
			String[] inputTextList=searchText.split(" ");
			if(inputTextList.length>2)
			{
				return null;
			}
			else
			{
				String firstName=inputTextList[0];
				String lastName="";
				if(inputTextList.length==2)
					lastName=inputTextList[1];
				
			con = DB.getConnection();
			
			String query = "Select p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName,IF(p.homeTown IS NULL,'',p.homeTown) homeTown," +
					"p.profileId profileId,p.profilePicId profilePicId from Profile p where p.firstName like ? and IF(p.lastName IS NULL,'',p.lastName) like ?";
			
			stmt = con.prepareStatement(query);
			stmt.setString(1, firstName+"%");
			stmt.setString(2, lastName+"%");
			System.out.println(stmt);
			 ResultSet rs=stmt.executeQuery();
			ArrayList<Integer> users=new ArrayList<Integer>();
			 
			 while(rs.next())
			 { String fname=rs.getString("firstName");
			 	String lname=rs.getString("lastName");
			 	String homeTown=rs.getString("homeTown");
			 	int profileId=rs.getInt("profileId");
			 	users.add(profileId);
			 	int profilePicId=rs.getInt("profilePicId");
			 	String finalName="";
			 	if(((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())>=0)
			 	{	int pos=((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase());
			 		int l=(fname+" "+lname).length();
			 		int l1=searchText.length();
			 		System.out.println("$$$$$$"+((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())+"$$$$$"+(searchText).indexOf(fname+" "+lname)+" "+searchText+" "+fname+" "+lname);
				 	finalName=(fname+" "+lname).substring(0,pos);
				 	finalName=finalName+"<mark>";
				 	finalName=finalName+(fname+" "+lname).substring(pos,pos+l1)+"</mark>";
				 	finalName=finalName+(fname+" "+lname).substring(pos+l1,l);
			 		//="<mark>"+fname+" "+lname+"</mark>";
			 	}
			 	if(profileId!=userId)
			 	{
			 	String temp="<input type=\"hidden\" value=\""+profileId+"\" name=\"profileId\" class=\"profileId\">" +
			 			"<img  width=\"25\" height=\"25\" src=\"image?photoId="+profilePicId+"\"><b>"+finalName+"</b>" +
			 			"<br/><br/>";
				 list.add(temp);
			 	}
			 }
			 //checking for hometown
				
				
				
			con.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public static ArrayList<String> getAutosuggestForTagging(String searchText,int userId) {
		PreparedStatement stmt = null;
		// Get a Statement object
		Connection con;
		System.out.println(searchText+"$$$$$");
		ArrayList<String> list=new ArrayList<String>();
		try{
			
			String[] inputTextList=searchText.split(" ");
			if(inputTextList.length>2)
			{
				return null;
			}
			else
			{
				String firstName=inputTextList[0];
				String lastName="";
				if(inputTextList.length==2)
					lastName=inputTextList[1];
				
			con = DB.getConnection();
			
			String query = "Select p.firstName firstName,IF(p.lastName IS NULL,'',p.lastName) lastName,IF(p.homeTown IS NULL,'',p.homeTown) homeTown," +
					"p.profileId profileId,p.profilePicId profilePicId from Profile p where p.firstName like ? and IF(p.lastName IS NULL,'',p.lastName) like ?";
			
			stmt = con.prepareStatement(query);
			stmt.setString(1, firstName+"%");
			stmt.setString(2, lastName+"%");
			System.out.println(stmt);
			 ResultSet rs=stmt.executeQuery();
			ArrayList<Integer> users=new ArrayList<Integer>();
			 
			 while(rs.next())
			 { String fname=rs.getString("firstName");
			 	String lname=rs.getString("lastName");
			 	String homeTown=rs.getString("homeTown");
			 	int profileId=rs.getInt("profileId");
			 	users.add(profileId);
			 	int profilePicId=rs.getInt("profilePicId");
			 	String finalName="";
			 	if(((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())>=0)
			 	{	int pos=((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase());
			 		int l=(fname+" "+lname).length();
			 		int l1=searchText.length();
			 		System.out.println("$$$$$$"+((fname+" "+lname).toLowerCase()).indexOf(searchText.toLowerCase())+"$$$$$"+(searchText).indexOf(fname+" "+lname)+" "+searchText+" "+fname+" "+lname);
				 	finalName=(fname+" "+lname).substring(0,pos);
				 	finalName=finalName+"<mark>";
				 	finalName=finalName+(fname+" "+lname).substring(pos,pos+l1)+"</mark>";
				 	finalName=finalName+(fname+" "+lname).substring(pos+l1,l);
			 		//="<mark>"+fname+" "+lname+"</mark>";
			 	}
			 	if(profileId!=userId)
			 	{
			 	String temp="<input type=\"hidden\" value=\"profileTempAction_hype?profileId="+profileId+"\" name=\"profileId\" class=\"profileId\">" +
			 			"<img  width=\"25\" height=\"25\" src=\"image?photoId="+profilePicId+"\"><b>"+finalName+"</b>" +
			 			"<br/><br/>";
				 list.add(temp);
			 	}
			 }
			 //checking for hometown
				
				
				
			con.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
}
