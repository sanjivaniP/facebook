package edu.iiitb.facebook.model;


public class Friend {
	private int userId;
	private String name;
	private int profilePicId;
	public int getProfilePicId() {
		return profilePicId;
	}
	public void setProfilePicId(int profilePicId) {
		this.profilePicId = profilePicId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
