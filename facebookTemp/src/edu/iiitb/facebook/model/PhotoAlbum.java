package edu.iiitb.facebook.model;

import java.util.ArrayList;

public class PhotoAlbum {
	private int photoAlbumId;
	private String description;
	private String location;
	private String createdOn;
	private int photoCount;
	private ArrayList<Integer> photoIdList;
	
	public int getPhotoAlbumId() {
		return photoAlbumId;
	}
	public void setPhotoAlbumId(int photoAlbumId) {
		this.photoAlbumId = photoAlbumId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public int getPhotoCount() {
		return photoCount;
	}
	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}
	public ArrayList<Integer> getPhotoIdList() {
		return photoIdList;
	}
	public void setPhotoIdList(ArrayList<Integer> photoIdList) {
		this.photoIdList = photoIdList;
	}

}
