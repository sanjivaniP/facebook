package edu.iiitb.facebook.action;

import java.util.ArrayList;
import java.util.Map;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;





public class TempAction extends ActionSupport implements SessionAware{
	
	
	
	public String temp() throws Exception 
	{
		return "success";
        
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}
}
	