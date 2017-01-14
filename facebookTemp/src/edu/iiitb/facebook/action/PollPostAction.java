package edu.iiitb.facebook.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Friend;
import edu.iiitb.facebook.model.Profile;

public class PollPostAction extends ActionSupport implements SessionAware {

	String options;
	String question;
	
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			// //System.out.println("inside else");
			int profileid = (Integer) session.getAttribute("profileId");
		System.out.println(question+"    "+options+"$$$$$$$$$$$$$$$$$$$$$$");
		String[] list=options.split("#");
		
		List<String> optionList= Arrays.asList(list);
		for(String str:optionList )
			System.out.println(str);
		FacebookDAO.addPoll(getQuestion(),optionList,profileid);
		return "success";
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}