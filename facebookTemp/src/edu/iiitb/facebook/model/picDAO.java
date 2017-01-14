package edu.iiitb.facebook.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.facebook.action.picAction;
import edu.iiitb.facebook.util.DB;

public class picDAO {
	
	public static picAction getCoverPic(int profileId){
		int coverPicId=0;
		picAction pic=new picAction();
		ResultSet resultSet = null;
		
		String query = "select firstName,lastName,coverPicId from Profile where profileId ="+profileId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		
			try {
				if (resultSet.next()) {
					 pic.setCoverPicId(resultSet.getInt("coverPicId"));
					 if(pic.getCoverPicId()==0){
						 /*
						 setting up the default cover pic for the user hwo does not have the cover pic*/
							//System.out.println("coverpicID before"+coverPicId);
							pic.setCoverPicId(4);
							//System.out.println("coverpicID after"+coverPicId);
						}
					 pic.setFirstName(resultSet.getString("firstName"));
					 pic.setLastName(resultSet.getString("lastName"));
						//binaryStream=FacebookDAO.getPhoto(coverPicId);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		return pic;
	}
	public static ArrayList<Integer> getPics(int profileId){
		ResultSet resultSet = null;
		ArrayList<Integer> photoIds=new ArrayList<Integer>();
		String query = "select photoId from Photo where owner ="+profileId+" and albumId IS  NULL AND caption not in ('CoverPic', 'ProfilePic');";
		System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		
			try {
				while (resultSet.next()) {
					 photoIds.add(resultSet.getInt("photoId"));
						//binaryStream=FacebookDAO.getPhoto(coverPicId);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		return photoIds;
	}

	public static ArrayList<Integer> getAlbumPics(int albumId){
		ResultSet resultSet = null;
		ArrayList<Integer> photoIds=new ArrayList<Integer>();
		String query = "select photoId from Photo where albumId ="+albumId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		
			try {
				while (resultSet.next()) {
					 photoIds.add(resultSet.getInt("photoId"));
						//binaryStream=FacebookDAO.getPhoto(coverPicId);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		return photoIds;
	}

	public static ArrayList<PhotoAlbum> getPhotoAlbum(int profileId){
		ResultSet resultSet = null;
		ArrayList<PhotoAlbum> photoAlbums=new ArrayList<PhotoAlbum>();
		String query = "select photoAlbumId,location,description,createdOn from PhotoAlbum where owner ="+profileId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		PhotoAlbum photoAlbum=new PhotoAlbum();
			try {
				while (resultSet.next()) {
					photoAlbum=new PhotoAlbum();
					photoAlbum.setPhotoIdList(getAlbumPics(resultSet.getInt("photoAlbumId")));
					photoAlbum.setPhotoCount(getAlbumPics(resultSet.getInt("photoAlbumId")).size());
					photoAlbum.setDescription(resultSet.getString("description"));
					photoAlbum.setLocation(resultSet.getString("location"));
					photoAlbum.setCreatedOn(resultSet.getDate("createdOn").toString());
					
						//binaryStream=FacebookDAO.getPhoto(coverPicId);
						photoAlbums.add(photoAlbum);
				}
			/* --------------------- Getting Cover Pics-----------------------------------*/
		
				
				PhotoAlbum photoAlbum1=new PhotoAlbum();
				//photoAlbum1=new PhotoAlbum();
				photoAlbum1.setPhotoIdList(getCoverProfilePic("CoverPic" ,profileId));
				photoAlbum1.setPhotoCount(getCoverProfilePic("CoverPic",profileId).size());
				photoAlbum1.setDescription("Cover Pics");
				photoAlbums.add(photoAlbum1);
				
			/*-----------------------Getting Profile Pics -----------------------------------*/	
				PhotoAlbum photoAlbum2=new PhotoAlbum();
				//photoAlbum1=new PhotoAlbum();
				photoAlbum2.setPhotoIdList(getCoverProfilePic("ProfilePic" ,profileId));
				photoAlbum2.setPhotoCount(getCoverProfilePic("ProfilePic",profileId).size());
				photoAlbum2.setDescription("Profile Pics");
				photoAlbums.add(photoAlbum2);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		return photoAlbums;
	}
	/*---------------------------------------------------------------------------------------*/
	public static ArrayList<Integer> getCoverProfilePic(String caption,int profileId)
	{
		ArrayList<Integer> photoIds=new ArrayList<Integer>();
		String query = "select photoId from Photo where owner ="+profileId+" AND caption ='"+caption+"';";
		System.out.println(query);
		ResultSet resultSet = null;
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
			try {
				while (resultSet.next()) {
					 photoIds.add(resultSet.getInt("photoId"));
						//binaryStream=FacebookDAO.getPhoto(coverPicId);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		
		return photoIds;
	}
	/*---------------------------------------------------------------------------------------*/
	
	public static Profile detailCoverPic(int profileId){
		//int coverPicId=0;
		ResultSet resultSet = null;
		Profile profile=new Profile();
		String query = "select coverPicAlbumID,coverPicId from Profile where profileId ="+profileId+";";
		//System.out.println(query);
		Connection connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		
			try {
				if (resultSet.next()) {
					 profile.setCoverPicAlbumId(resultSet.getInt("coverPicAlbumId"));
					profile.setCoverPicId(resultSet.getInt("coverPicId"));	
					 //binaryStream=FacebookDAO.getPhoto(coverPicId);
						
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DB.close(resultSet);
		DB.close(connection);
		return profile;
	}
	
	
	public static String areFriends(int user1, int user2)
	{
		ResultSet resultSet= null;
		String query,query1;
		query= "select areFriends from Friendship where ((userId1="+user1+" and userId2="+user2+") or " +
				" (userId2="+user1+" and userId1="+user2+"));";
		System.out.println("are frnds"+query);
		Connection connection;
		connection= DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			if (resultSet.next()) {
				if(resultSet.getString("areFriends").charAt(0)=='Y')
					return "friends";
				else{
					query1="select areFriends from Friendship where userId1="+user1+" and userId2="+user2+";";
					resultSet = DB.readFromDB(query1, connection);
					if(resultSet.next()){
						return "request";
					}
					else
						return "response";
					
				}
					
			}
			else
				return "addFriend";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return "wrong";
	}
	
	


}
