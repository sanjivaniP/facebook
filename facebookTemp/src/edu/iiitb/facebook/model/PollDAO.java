package edu.iiitb.facebook.model;

import java.lang.reflect.Array;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.iiitb.facebook.action.PollAction;
import edu.iiitb.facebook.util.DB;


//import edu.iiitb.studentinfo.util.RuntimeSettings;

public class PollDAO {

    /**
     *
     */
   
	public static ArrayList<PollAction> retreivePolls(int profileId){
		ArrayList<Integer> friendsList =FacebookDAO.getFriendList(profileId);
		
		ArrayList<PollAction> pollList=new ArrayList<PollAction>();
		// Get a Statement object
		ResultSet resultSet = null;
		ResultSet resultSet1 = null;
		ResultSet resultSet2 = null;
		ResultSet resultSet3 = null;
		int totalCount=0;
		//for user who is logged in
		try {
			
			
			String query="SELECT pollId,question FROM Poll where owner="+profileId+";";
			
			Connection connection = DB.getConnection();
			resultSet = DB.readFromDB(query, connection);
			
			while(resultSet.next())
			{
				PollAction poll=new PollAction();
				totalCount=0;
				poll.setQuestion(resultSet.getString("question"));
				String query1="SELECT optionId,countOfUsersVoted, listOfUsersVoted,description FROM PollOption where pollId="+resultSet.getInt("pollId")+";";
				resultSet1 = DB.readFromDB(query1, connection);
				ArrayList<Options> optionList=new ArrayList<Options>();
				while(resultSet1.next()){
					Options option=new Options();
					option.setOptionId(resultSet1.getInt("optionId"));
					option.setDescription(resultSet1.getString("description"));
					option.setCountOfVotes(resultSet1.getInt("countOfUsersVoted"));
					option.setStringListOfUsers(resultSet1.getString("listOfUsersVoted"));
					String users=resultSet1.getString("listOfUsersVoted");
					System.out.println("111:::"+users);
					String splitusers=null;
					ArrayList<Profile> voterList=new ArrayList<Profile>();
					while(!(users.equalsIgnoreCase(";"))){
						//System.out.println("inside loop");
						int count=0;
						
						for(String str:users.split(",",2)){
							count++;
							if(count==1)
							 splitusers=str;
							else
								users=str;
						}
						
						
						
						if(splitusers.contains(";")){
							count=0;
							for(String str:users.split(";",2)){//will conatian a list of length of 2
								System.out.println("str:::"+str);
								count++;
								if(count==1)
								 splitusers=str;
								else
									users=";";
							}
						}
							
						System.out.println("333:::"+splitusers+users);
						int id=Integer.parseInt(splitusers);
						
						Profile prof=new Profile();
						if(id==profileId){
							option.checked="true";
						}
						prof=FacebookDAO.getProfile(id);
						prof.setProfileId(id);
						voterList.add(prof);
						
					}
					option.setListOfUser(voterList);
					optionList.add(option);
					totalCount=totalCount+resultSet1.getInt("countOfUsersVoted");
				}
				for(Options opt:optionList){//for progress bar
					if(totalCount!=0)
					opt.setVoterPercentage(opt.getCountOfVotes()*100/totalCount);//calculate how wide the blue image in the progress div should be
					else
						opt.setVoterPercentage(0);
				}
				Profile prof1=new Profile();
				prof1=FacebookDAO.getProfile(profileId);
				poll.setProfile(prof1);
				poll.setOptionlist(optionList);
				
				pollList.add(poll);
				
				
			}
			DB.close(resultSet);
			DB.close(resultSet1);
			DB.close(connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// for friends
		for(int friendId :friendsList){
			System.out.println("friendID in model::"+friendId);
		try {
			
			
			String query="SELECT pollId,question FROM Poll where owner="+friendId+";";
			
			Connection connection = DB.getConnection();
			resultSet2 = DB.readFromDB(query, connection);
			
			while(resultSet2.next())
			{
				PollAction poll=new PollAction();
				totalCount=0;
				poll.setQuestion(resultSet2.getString("question"));
				String query1="SELECT optionId,countOfUsersVoted, listOfUsersVoted,description FROM PollOption where pollId="+resultSet2.getInt("pollId")+";";
				resultSet3 = DB.readFromDB(query1, connection);
				
				ArrayList<Options> optionList=new ArrayList<Options>();
				while(resultSet3.next()){
					Options option=new Options();
					option.setOptionId(resultSet3.getInt("optionId"));
					option.setDescription(resultSet3.getString("description"));
					option.setCountOfVotes(resultSet3.getInt("countOfUsersVoted"));
					option.setStringListOfUsers(resultSet3.getString("listOfUsersVoted"));
					String users=resultSet3.getString("listOfUsersVoted");
					String splitusers=null;
					ArrayList<Profile> voterList=new ArrayList<Profile>();
					
					while(!(users.equalsIgnoreCase(";"))){
						System.out.println("inside loop");
						int count=0;
						
						for(String str:users.split(",",2)){
							count++;
							if(count==1)
							 splitusers=str;
							else
								users=str;
						}
						
						
						
						if(splitusers.contains(";")){
							count=0;
							for(String str:users.split(";",2)){
								System.out.println("str:::"+str);
								count++;
								if(count==1)
								 splitusers=str;
								else
									users=";";
							}
						}
							
						System.out.println("333:::"+splitusers+users);
						int id=Integer.parseInt(splitusers);
						
						Profile prof=new Profile();
						if(id==profileId){
							option.checked="true";
						}
						prof=FacebookDAO.getProfile(id);
						prof.setProfileId(id);
						voterList.add(prof);
						
					}
					option.setListOfUser(voterList);
					optionList.add(option);
					totalCount=totalCount+resultSet3.getInt("countOfUsersVoted");
				}
				for(Options opt:optionList){
					if(totalCount!=0)
					opt.setVoterPercentage(opt.getCountOfVotes()*100/totalCount);
					else
						opt.setVoterPercentage(0);
				}
				System.out.println("before seetting options::::poll qtn:::"+poll.getQuestion());
				Profile prof1=new Profile();
				prof1=FacebookDAO.getProfile(friendId);
				poll.setProfile(prof1);
				poll.setOptionlist(optionList);
				for(PollAction poll1:pollList){
					System.out.println("poll qtn::::"+poll1.getQuestion());
					for(Options opt:poll1.getOptionlist()){
						System.out.println("options::::"+opt.getDescription());
					}
				}
				pollList.add(poll);
				
			}
			DB.close(resultSet2);
			DB.close(resultSet3);
			DB.close(connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		return pollList;

	}
	
	
public static int updateVotersForPoll(int optionId, int profileId,int countOfVoters,String users) {
		
		String updateSQL;
		
		updateSQL = 
			    "update PollOption "
				+ "set listOfUsersVoted = '" +users+"',countOfUsersVoted="+countOfVoters+" where optionId = " +optionId+";";
		
		
		//System.out.println(updateSQL);
		return DB.update(updateSQL);
	}
	

}
