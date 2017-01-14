package edu.iiitb.facebook.model;

public class EventInviteFriends {

	private int profileId; // id of each friend
	private int profilePicId;
	private String firstName;
	private String lastName;
	private boolean flagF; // flag to find particular friend is already invited
							// or not

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getProfilePicId() {
		return profilePicId;
	}

	public void setProfilePicId(int profilePicId) {
		this.profilePicId = profilePicId;
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

	public boolean isFlagF() {
		return flagF;
	}

	public void setFlagF(boolean flagF) {
		this.flagF = flagF;
	}

}
