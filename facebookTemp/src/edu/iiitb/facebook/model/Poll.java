package edu.iiitb.facebook.model;

import java.util.ArrayList;

public class Poll {
	int pollId;
	
	String question;
	ArrayList<Options> optionlist; 
	
	public int getPollId() {
		return pollId;
	}
	public void setPollId(int pollId) {
		this.pollId = pollId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public ArrayList<Options> getOptionlist() {
		return optionlist;
	}
	public void setOptionlist(ArrayList<Options> optionlist) {
		this.optionlist = optionlist;
	}
	

}
