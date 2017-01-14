package edu.iiitb.facebook.model;

import java.util.ArrayList;

public class Options {

	String description;
	ArrayList<Profile> listOfUser;
	String stringListOfUsers;
	public String getStringListOfUsers() {
		return stringListOfUsers;
	}
	public void setStringListOfUsers(String stringListOfUsers) {
		this.stringListOfUsers = stringListOfUsers;
	}
	String checked;//checked by current user
	int countOfVotes;
	float voterPercentage;
	public float getVoterPercentage() {
		return voterPercentage;
	}
	public void setVoterPercentage(float voterPercentage) {
		this.voterPercentage = voterPercentage;
	}
	int optionId;
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Profile> getListOfUser() {
		return listOfUser;
	}
	public void setListOfUser(ArrayList<Profile> listOfUser) {
		this.listOfUser = listOfUser;
	}
	public int getCountOfVotes() {
		return countOfVotes;
	}
	public void setCountOfVotes(int countOfVotes) {
		this.countOfVotes = countOfVotes;
	}
	public int getPollId() {
		return optionId;
	}
	public void setPollId(int pollId) {
		this.optionId = pollId;
	}
}
