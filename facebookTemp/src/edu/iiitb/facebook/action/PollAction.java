package edu.iiitb.facebook.action;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.FacebookDAO;
import edu.iiitb.facebook.model.MessageDao;
import edu.iiitb.facebook.model.Options;
import edu.iiitb.facebook.model.Poll;
import edu.iiitb.facebook.model.PollDAO;
import edu.iiitb.facebook.model.Profile;

public class PollAction {
	PollAction poll;
	int optionId1;
	Profile profile;
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public int getOptionId1() {
		return optionId1;
	}
	public static int getOptionId() {
		return optionId;
	}
	public static void setOptionId(int optionId) {
		PollAction.optionId = optionId;
	}
	public void setOptionId1(int optionId1) {
		this.optionId1 = optionId1;
	}
	public int getCount1() {
		return count1;
	}
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	
	public PollAction getPoll() {
		return poll;
	}
	int count1;
	String listOfUsers1;
	
	public String getListOfUsers1() {
		return listOfUsers1;
	}
	public void setListOfUsers1(String listOfUsers1) {
		this.listOfUsers1 = listOfUsers1;
	}
	public ArrayList<PollAction> getPollList() {
		return pollList;
	}
	public void setPollList(ArrayList<PollAction> pollList) {
		this.pollList = pollList;
	}
	public void setPoll(PollAction poll) {
		this.poll = poll;
	}


	ArrayList<PollAction> pollList;
	private Map<Integer, Boolean> checkboxes;
	
	public Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}
	public void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}
	
	



	ArrayList<Options> optionlist;
	
	public ArrayList<Options> getOptionlist() {
		return optionlist;
	}
	public void setOptionlist(ArrayList<Options> optionlist) {
		this.optionlist = optionlist;
	}

	public static int pollId,optionId,countOfUsersVoted=0;
	String Question,CaptiononVoteButton,option1,option2,option3,option4,CaptiononVoteButton1,option1chk,option2chk,option3chk,option4chk;
     	   public String execute() throws Exception {
		   pollId++;
		   System.out.println(option1chk+ "\n");
		   System.out.println(option2chk+ "\n");
		   System.out.println(option3chk+ "\n");
		   System.out.println(option4chk+ "\n");
		  // System.out.println(Question);
		  // System.out.println(CaptiononVoteButton1);
		  // System.out.println("print"+CaptiononVoteButton);
		//PollDAO.addPoll(pollId,Question,optionId1,option1,option2,option3,option4,countOfUsersVoted);
	      return "success";
	   }
     	  public String execute1() throws Exception {
   	      return "success";
   	   }

		public int getpollId() {
			return pollId;
		}

		public void setPollId(int pollId) {
			this.pollId = pollId;
		}
		
		public String getQuestion() {
			return Question;
		}

		public void setQuestion(String Question) {
			this.Question = Question;
		}

		public String getCaptiononVoteButton() {
			return CaptiononVoteButton;
		}
	 
		public void setCaptiononVoteButton(String CaptiononVoteButton)
		{
			this.CaptiononVoteButton=CaptiononVoteButton;
		}


		public String getOption1() {
			return option1;
		}


		public void setOption1(String option1) {
			this.option1 = option1;
		}


		public String getOption2() {
			return option2;
		}


		public void setOption2(String option2) {
			this.option2 = option2;
		}


		public String getOption3() {
			return option3;
		}


		public void setOption3(String option3) {
			this.option3 = option3;
		}


		public String getOption4() {
			return option4;
		}


		public void setOption4(String option4) {
			this.option4 = option4;
		}



		public static int getCountOfUsersVoted() {
			return countOfUsersVoted;
		}


		public static void setCountOfUsersVoted(int countOfUsersVoted) {
			PollAction.countOfUsersVoted = countOfUsersVoted;
		}


		public String getCaptiononVoteButton1() {
			return CaptiononVoteButton1;
		}


		public void setCaptiononVoteButton1(String captiononVoteButton1) {
			CaptiononVoteButton1 = captiononVoteButton1;
		}


		public static int getPollId() {
			return pollId;
		}


		public String getOption1chk() {
			return option1chk;
		}


		public void setOption1chk(String option1chk) {
			this.option1chk = option1chk;
		}


		public String getOption2chk() {
			return option2chk;
		}


		public void setOption2chk(String option2chk) {
			this.option2chk = option2chk;
		}


		public String getOption3chk() {
			return option3chk;
		}


		public void setOption3chk(String option3chk) {
			this.option3chk = option3chk;
		}


		public String getOption4chk() {
			return option4chk;
		}


		public void setOption4chk(String option4chk) {
			this.option4chk = option4chk;
		}
		
		public String retreivePolls(){
			HttpSession session = ServletActionContext.getRequest().getSession(
					false);
			if (session == null || session.getAttribute("login") == null) {
				return "login";
			} else {
				// //System.out.println("inside else");
				int profileid = (Integer) session.getAttribute("profileId");
				pollList=PollDAO.retreivePolls(profileid);
				for(PollAction poll:pollList){
					System.out.println("qtn in action::::"+poll.getQuestion());
				}
				return "success";
			}
			
		}
		
		public String voteForPoll(){
			System.out.println("inside voteforpoll");
			HttpSession session=ServletActionContext.getRequest().getSession(false);  
	        if(session==null || session.getAttribute("login")==null){  
	        	
	            return "login";  
	        } 
	        else{    	
	        	int profileid=(Integer)session.getAttribute("profileId");
		 
			
	        	System.out.println("before::::"+optionId1+" "+count1+ " "+listOfUsers1+"%%%%%%");
	        	if(listOfUsers1.contains(profileid+",")){
	        		System.out.println("in comma");
	        		int indexOf=listOfUsers1.indexOf(profileid+",");
	        		count1--;
	        		String users=listOfUsers1.substring(0,indexOf);
	        		users=users.concat(listOfUsers1.substring(indexOf+2,listOfUsers1.length()));
//	        		String toDelete=profileid+",";
//	        		listOfUsers1.replace(toDelete,"");
	        		listOfUsers1=users;
	        	}
	        	else if(listOfUsers1.contains(profileid+";")){
	        		System.out.println("in semi");
	        		count1--;
//	        		String toDelete=profileid+";";
//	        		listOfUsers1.replace(toDelete,";");
	        		int indexOf=listOfUsers1.indexOf(profileid+";");
	        		
	        		String users=null;
	        		if(indexOf!=0){
	        		 users=listOfUsers1.substring(0,indexOf-1);
	        		users=users.concat(";");
	        		}
	        		else{
	        			users=";";
	        		}
//	        		String toDelete=profileid+",";
//	        		listOfUsers1.replace(toDelete,"");
	        		listOfUsers1=users;
	        	}
	        	
	        	else{
	        	if(!(listOfUsers1.equals(";"))){
	        	listOfUsers1=listOfUsers1.replace(";",",");
	        	listOfUsers1=listOfUsers1.concat(profileid+";");
	        	}
	        	else
	        	{
	        		listOfUsers1=profileid+";";
	        	}
	        	count1++;
	        	}
	        	System.out.println("before::::"+optionId1+" "+count1+ " "+listOfUsers1+"%%%%%%");
	        		
	        		       	    
	        	    PollDAO.updateVotersForPoll(optionId1,profileid,count1,listOfUsers1);
	        	
	        
	        
		
	        return "success";
		}
	        }

	    
		


		
}
