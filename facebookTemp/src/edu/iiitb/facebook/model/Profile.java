package edu.iiitb.facebook.model;

import java.sql.Blob;
import java.sql.Date;

public class Profile {
	int ageMT2013148;
	int profileId;
	int loginId;
	String firstName;
	String lastName;
	String intrests;
	Date birthDate;
	String aboutMe;
	int profilePicAlbumId;
	int profilePicId;
	String info;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getProfilePicId() {
		return profilePicId;
	}
	public void setProfilePicId(int profilePicId) {
		this.profilePicId = profilePicId;
	}
	String religion;
	int coverPicAlbumId;
	int schoolCount;
	String gender;
	String homeTown;
	String currentCity;
	int workplaceCount;
	String isFriend;
	int coverPicId;
	
	
	public int getCoverPicId() {
		return coverPicId;
	}
	public void setCoverPicId(int coverPicId) {
		this.coverPicId = coverPicId;
	}
	public String getIsFriend() {
		return isFriend;
	}
	public void setIsFriend(String isFriend) {
		this.isFriend = isFriend;
	}
	String relationshipStatus;
	Blob profileImage;
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
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
	public String getIntrests() {
		return intrests;
	}
	public void setIntrests(String intrests) {
		this.intrests = intrests;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public int getProfilePicAlbumId() {
		return profilePicAlbumId;
	}
	public void setProfilePicAlbumId(int profilePicAlbumId) {
		this.profilePicAlbumId = profilePicAlbumId;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public int getCoverPicAlbumId() {
		return coverPicAlbumId;
	}
	public void setCoverPicAlbumId(int coverPicAlbumId) {
		this.coverPicAlbumId = coverPicAlbumId;
	}
	public int getSchoolCount() {
		return schoolCount;
	}
	public void setSchoolCount(int schoolCount) {
		this.schoolCount = schoolCount;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHomeTown() {
		return homeTown;
	}
	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public int getWorkplaceCount() {
		return workplaceCount;
	}
	public void setWorkplaceCount(int workplaceCount) {
		this.workplaceCount = workplaceCount;
	}
	public String getRelationshipStatus() {
		return relationshipStatus;
	}
	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	
	}
	public int getAgeMT2013148() {
		return ageMT2013148;
	}
	public void setAgeMT2013148(int ageMT2013148) {
		this.ageMT2013148 = ageMT2013148;
	}
	
	
}
