package edu.iiitb.facebook.action;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.Profile;





public class SearchAction extends ActionSupport {
	
	String searchText;
	int userId;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<Profile> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(ArrayList<Profile> searchResult) {
		this.searchResult = searchResult;
	}

	ArrayList<Profile> searchResult;
	ArrayList<String> searchResult1;
	
	public ArrayList<String> getSearchResult1() {
		return searchResult1;
	}

	public void setSearchResult1(ArrayList<String> searchResult1) {
		this.searchResult1 = searchResult1;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String temp() throws Exception 
 {
		HttpSession session = ServletActionContext.getRequest().getSession(
			false);
	if (session == null || session.getAttribute("login") == null) {
		// //System.out.println("inside if");
		return "login";
	} else {
		setUserId((Integer) session.getAttribute("profileId"));
		setSearchResult(FacebookDAO.getSearchResult(searchText,
				(Integer) session.getAttribute("profileId")));
		if (getSearchResult() == null)
			return "noResult";
		else
			return "success";
	}
}
	
	
	

	public String temp1() throws Exception //auto suggest on searchbox
 {
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			// //System.out.println("inside if");
			return "login";
		} else {
			setUserId((Integer) session.getAttribute("profileId"));
			setSearchResult1(FacebookDAO.getAutosuggest(searchText,
					(Integer) session.getAttribute("profileId")));
			
				return "success";
		}
}

	
	public String temp2() throws Exception 
 {

		
	HttpSession session = ServletActionContext.getRequest().getSession(false);
if (session == null || session.getAttribute("login") == null) {
	// //System.out.println("inside if");
	return "login";
} else {
	setUserId((Integer) session.getAttribute("profileId"));
	System.out.println("Here Search "+searchText);
	setSearchResult1(FacebookDAO.getAutosuggestMessage(searchText,(Integer) session.getAttribute("profileId")));
	
		return "success";
}
		
	
	

}

	public String temp3() throws Exception 
 {

		
	HttpSession session = ServletActionContext.getRequest().getSession(false);
if (session == null || session.getAttribute("login") == null) {
	// //System.out.println("inside if");
	return "login";
} else {
	setUserId((Integer) session.getAttribute("profileId"));
	System.out.println("Here Search "+searchText);
	setSearchResult1(FacebookDAO.getAutosuggestForTagging(searchText,(Integer) session.getAttribute("profileId")));
	
		return "success";
}
		
	
	

}
	
	
	
}
	