package edu.iiitb.facebook.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import edu.iiitb.facebook.util.DB;
public class MessageDao {

	//adding the to validity and from validity
	//when delete in a message , 	identify whether the current user is sender or receiver of the message to be deleted
	//accordingly make the validity from 1->0
	//delete conversation , do this for all the messages in the conversation to be deleted
	//view messages , retrieve for only those messages which are valid for the current user .


	public void deleteMessage (int messageId,int currentUser,int selectedUser, String currentUserStatus)
	{

		
		ArrayList<Message> conversation = new ArrayList<Message>();
		ResultSet rs;
		try
		{
			PreparedStatement stmt=null;
			Statement stmt1=null,stmt2=null;
			Connection con=DB.getConnection();

			String delete="delete from Messages where messageId = "+messageId;;
			//identify current user is sendr or reciever in the given message ID
			//instead of delete set the 1->0
			if(currentUserStatus=="sender")
			{
				delete = "update Messages  set fromValidity=0 where messageId="+messageId;
				//set sender validity to 0;
			}
			else if(currentUserStatus=="receiver")
			{
				delete = "update Messages  set toValidity=0 where messageId="+messageId;
				//set receiver validity to 0;
			}

			stmt1=con.createStatement();
			stmt1.executeUpdate(delete);
			
			String query="Select * from Conversations where lastMessageId = "+messageId+" and user1Id="+currentUser ;
			stmt=con.prepareStatement(query);
			rs=stmt.executeQuery();		
			
			if(rs.next())
			{
				//if the message to be deleted exists in the conversations table
				
				//find the latest valid message between these two ppl and update the lastmessageId accordingly
				 
				//current User and selected user cha last valid message 
				conversation=displayFullConversation(currentUser,selectedUser);
				Collections.sort(conversation);
				//sorts it in ascending order
				System.out.println("sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+conversation.size());
				
			
				if(conversation.size()==0) 
				//when last message is to be deleted remove entry from 
				//remove the entry from the conversations table for the current user
				{
					Statement s3=null;
					String s1 = "delete from Conversations where user1Id="+currentUser+" and user2Id="+selectedUser ;
					s3=con.createStatement();
					s3.executeUpdate(s1);
					
				}
				else
				{
					int newLastMessageId=conversation.get(conversation.size()-1).getMessageId();
					
					String s = "update Conversations " +
							"set lastMessageId="+newLastMessageId+
							" where user1Id="+currentUser+" and lastMessageId="+messageId;

					stmt2=con.createStatement();
					stmt2.executeUpdate(s);
				}
				
			}


			DB.close(con);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}


	}




	public void populateSender(ArrayList<Message> fullConversation)
	{
		Iterator iterator = fullConversation.iterator();
		while ( iterator.hasNext())
		{
			Message msg = (Message) iterator.next();
			try
			{
				PreparedStatement stmt=null;
				Connection con=DB.getConnection();
				String query="Select firstName,profilePicId from Profile where profileId = "+msg.getSender()+";";
				stmt=con.prepareStatement(query);
				ResultSet rs=stmt.executeQuery();		
				rs.next();
				msg.setSenderName(rs.getString("firstName"));
				msg.setPhotoId(rs.getInt("profilePicId"));
				//System.out.println(msg.getSenderName());
				DB.close(con);
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}

		}

	}
	public ArrayList<Message> getAllMessagesInDatabase()
	{
		ArrayList<Message>allMessageList = new ArrayList<Message>();

		try
		{

			PreparedStatement stmt=null;
			Connection con=DB.getConnection();
			String query="Select *from Messages;";
			stmt=con.prepareStatement(query);
			ResultSet rs=stmt.executeQuery();		
			while(rs.next())
				//here i create a new msg object for each message that i want to store
				//the arraylist will only hold only references to the objects .. 
				//the objects should first be formed ,
				// if a new message is not creadted for each object 
				// all objects stored so far in the arraylist contain a reference only to the last mesage
			{	Message msg = new Message();
			msg.setMessageId(rs.getInt("messageId"));
			msg.setSender(rs.getInt("sender"));
			msg.setReceiver(rs.getInt("receiver"));
			msg.setText(rs.getString("text"));
			msg.setFromValidity(rs.getBoolean("fromValidity"));
			msg.setToValidity(rs.getBoolean("toValidity"));
			msg.setSeenAt(rs.getTimestamp("seenAt"));
			msg.setSentAt(rs.getTimestamp("sentAt"));
			allMessageList.add(msg); 
			}
			Iterator iterator = allMessageList.iterator();
			ListIterator litr  = allMessageList.listIterator();	
			Message msg = new Message();
			//here msg object created only once everytime only what it reference to 
			//gets changed
			while(litr.hasNext())
			{			
				msg = (Message) litr.next(); 
				System.out.println(msg.getText());
			}
			DB.close(con);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return allMessageList;
	}
	public ArrayList<Message> displayFullConversation(int currentUser,int selectedUser)
	{
		ArrayList<Message>fullConversation = new ArrayList<Message>();

		try
		{
			PreparedStatement stmt=null,stmt1=null,stmt2=null;
			Connection con=DB.getConnection();
	
			String query1="select * from Messages where (sender="+currentUser+" and receiver="+selectedUser+" and fromValidity=1) " +
					"or (sender="+selectedUser+" and receiver="+currentUser+" and toValidity=1)";
		    stmt1=con.prepareStatement(query1);
		    System.out.println(query1);
		    ResultSet rs1=stmt1.executeQuery();
		    while(rs1.next())
			 {
		
				 Message msg = new Message();
				 poplulateMessageObject(rs1, msg);
				 fullConversation.add(msg); 
			 }
		    
		    
		/*    
		    String query2="select * from Messages where sender="+selectedUser+" and receiver="+currentUser+" and toValidity=1" ;
		    stmt2=con.prepareStatement(query2);
			ResultSet rs2=stmt2.executeQuery();
			 while(rs2.next()) 
			 {
				 Message msg = new Message();
				 poplulateMessageObject(rs2, msg);
				 fullConversation.add(msg);			}
			
		*/	
			/*			while(rs.next())
				//here i create a new msg object for each message that i want to store
				//the arraylist will only hold only references to the objects .. 
				//the objects should first be formed ,
				// if a new message is not creadted for each object 
				// all objects stored so far in the arraylist contain a reference only to the last mesage
			{	Message msg = new Message();
			poplulateMessageObject(rs, msg);
			fullConversation.add(msg); 
			}
			 */			

			 DB.close(con);
			 populateSender(fullConversation);		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
        Collections.sort(fullConversation);
		return fullConversation;
	}
	public ArrayList<MessageLastConversation> displayRecentConversationWithAll(int currentUser) 
	{

		
		ArrayList<MessageLastConversation> lastConversationWithAllList 
		= new ArrayList<MessageLastConversation>();
		Integer otherPersonId;
		String otherPersonName;

		try
		{
			PreparedStatement stmt=null,stmt1=null,stmt2=null,stmt3=null;
			Connection con=DB.getConnection(); 

			//first find the latest messages from the conversations table;
			//select * from Conversations where user1Id=6 or user2Id=6;
			//above query got executed in the terminal , but dint run here .
			// now populate the array list according to the message Id we get from above query

			//String query="select * from Conversations where  user2Id="+currentUser;			
			String query1 = "select * from Conversations where user1Id="+currentUser;
			stmt1=con.prepareStatement(query1);		
			ResultSet rs1=stmt1.executeQuery();
			//all the conversation entries selected ... 
			
			
			
			while(rs1.next())
			{
				
				MessageLastConversation lastConversation = new MessageLastConversation();
		        			
				Message msg = new Message();	
	            String query2 = "select * from Messages where messageId="+rs1.getInt("lastMessageId");
				stmt2=con.prepareStatement(query2);
				ResultSet rs2=stmt2.executeQuery();
				rs2.next();
				poplulateMessageObject(rs2,msg);
	            
	            
				otherPersonId=rs1.getInt("user2Id");
				
				String query3 = "select firstName,profilePicId from Profile where profileId="+otherPersonId;
				stmt3=con.prepareStatement(query3);
				ResultSet rs3=stmt3.executeQuery();
				rs3.next();
				
				lastConversation.setPhotoId(rs3.getInt("profilePicId"));
				otherPersonName=rs3.getString("firstName");
				lastConversation.setUser2Name(otherPersonName);
				lastConversation.setLastMessageText(rs2.getString("text"));
				lastConversation.setUser2Id(otherPersonId);
				lastConversation.setTime(msg.getSentAt());
				lastConversationWithAllList.add(lastConversation);

			}

			//sort last ConversationwithAll.in descending 
			//so that person you messaged latest will show up first in the list
			Collections.sort(lastConversationWithAllList);		    
			DB.close(con);

		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		return lastConversationWithAllList;
	}  
	public void poplulateMessageObject(ResultSet rs , Message msg ) throws SQLException
	{
		// given msg object is populated , 
		//here by default refernce of message object is passed 
		msg.setMessageId(rs.getInt("messageId"));
		msg.setReceiver(rs.getInt("receiver"));
		msg.setText(rs.getString("text"));
		msg.setSender(rs.getInt("sender"));
		msg.setFromValidity(rs.getBoolean("fromValidity"));
		msg.setToValidity(rs.getBoolean("toValidity"));
		msg.setSeenAt(rs.getTimestamp("seenAt"));
		msg.setSentAt(rs.getTimestamp("sentAt"));
		msg.setTimeInString(rs.getTimestamp("sentAt"));
	}

	
	
	
	
public void storeNewMessage(String reply, int selectedUser,int currentUser)
{
	int messageId;
	Boolean isCurrentUser=false,isSelectedUser=false;
	storeReply(reply, selectedUser,currentUser);
    
	try
	{
	Connection con = DB.getConnection();
	String query= "select messageId from Messages where sender=? and receiver=?";
	PreparedStatement stamt = con.prepareStatement(query);
	stamt.setInt(1,currentUser);
	stamt.setInt(2,selectedUser);
	ResultSet rs=stamt.executeQuery();
	rs.last();
	
	//messageId of the message just inserted 
	messageId=rs.getInt("messageId");
	
	String query1="select * from Conversations where user1Id=? and user2Id=?";
	PreparedStatement stamt1 = con.prepareStatement(query1);
	stamt1.setInt(1,currentUser);
	stamt1.setInt(2,selectedUser);
	ResultSet rs1=stamt1.executeQuery();
	if(rs1.next())
	{
		isCurrentUser=true;
	    //conversations tale entry exists for current user
	}
	
	String query2="select * from Conversations where user1Id=? and user2Id=?";
	PreparedStatement stamt2 = con.prepareStatement(query2);
	stamt2.setInt(1,selectedUser);
	stamt2.setInt(2,currentUser);
	ResultSet rs2=stamt2.executeQuery();
    if(rs2.next())
    {
    	isSelectedUser=true;
    	//conversations table entry exists for selected user
    }
	
    
    /*    THE 4 Conditions */ 
    
    if(isCurrentUser && isSelectedUser)
    {
    	//update entry for both
    	
    	String query3="update Conversations set lastMessageId=? where user1Id=? and user2Id=? or (user1Id=? and user2Id=?)";
    	PreparedStatement stamt3 = con.prepareStatement(query3);
    	stamt3.setInt(1,messageId);
    	stamt3.setInt(2,currentUser);
    	stamt3.setInt(3,selectedUser);
    	stamt3.setInt(4,selectedUser);
    	stamt3.setInt(5,currentUser);
        stamt3.executeUpdate();
    	
    }
    else if(isCurrentUser && !isSelectedUser)
    {
    	//update for currentUser , insert for selectedUser
    	
    	//update for currentUser
    	String query4="update Conversations set lastMessageId=? where user1Id=? and user2Id=?" ;
    	PreparedStatement stamt4 = con.prepareStatement(query4);
    	stamt4.setInt(1,messageId);
    	stamt4.setInt(2,currentUser);
    	stamt4.setInt(3,selectedUser);
        stamt4.executeUpdate();

        //insert for selectedUser
        String query3="insert into Conversations values (null,?,?,?)";
    	PreparedStatement stamt3 = con.prepareStatement(query3);
    	stamt3.setInt(1,selectedUser);
    	stamt3.setInt(2,currentUser);
    	stamt3.setInt(3,messageId);
        stamt3.executeUpdate();

    }
    else if(!isCurrentUser && isSelectedUser)
    {
    	//insert for currentUser and update for selectedUser
        
    	//insert for currentUser
    	String query3="insert into Conversations values (null,?,?,?)";
    	PreparedStatement stamt3 = con.prepareStatement(query3);
    	stamt3.setInt(1,currentUser);
    	stamt3.setInt(2,selectedUser);
    	stamt3.setInt(3,messageId);
        stamt3.executeUpdate();
        
        //update for selected user
        String query4="update Conversations set lastMessageId=? where user1Id=? and user2Id=?" ;
    	PreparedStatement stamt4 = con.prepareStatement(query4);
    	stamt4.setInt(1,messageId);
    	stamt4.setInt(2,selectedUser);
    	stamt4.setInt(3,currentUser);
        stamt4.executeUpdate();

        
    }
    else if(!isCurrentUser && !isSelectedUser)
    {
    	//insert for both
    	    	    	
    	//insert for currentUser
    	String query3="insert into Conversations values (null,?,?,?)";
    	PreparedStatement stamt3 = con.prepareStatement(query3);
    	stamt3.setInt(1,currentUser);
    	stamt3.setInt(2,selectedUser);
    	stamt3.setInt(3,messageId);
        stamt3.executeUpdate();
        
        //insert for selectedUser
        String query4="insert into Conversations values (null,?,?,?)";
    	PreparedStatement stamt4 = con.prepareStatement(query4);
    	stamt4.setInt(1,selectedUser);
    	stamt4.setInt(2,currentUser);
    	stamt4.setInt(3,messageId);
        stamt4.executeUpdate();

    }
    
    
	
	}
	catch(Exception E)
	{
		System.out.println(E.getMessage());
	}

}
		
	public void storeReply(String reply, int selectedUser, int currentUser) 
	{
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp now = new Timestamp(date.getTime());
		//insert into Messages values(null , profileId,1,selectedUser,1,reply,sentAt,null)
		try
		{
			Connection con = DB.getConnection();	 
			String sql = "INSERT INTO Messages " +
					"VALUES (null, ?, 1,?,1,?,?,null)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1,currentUser);
			preparedStatement.setInt(2,selectedUser);
			preparedStatement.setString(3,reply);
			preparedStatement.setTimestamp(4,now);
			preparedStatement.executeUpdate(); 

		}
		catch(Exception E)
		{
			System.out.println(E.getMessage());
		}		   
	}

	public void updateEntriesInConversationsTable(int currentUser,int selectedUser)
	{
		// i have selected user and current user 
		//using display full conversations get hold of last messageId between the two 
		//basically you want the message Id that you just added in the new reply sent
		
		//for other user have deleted the entry
		try{
		Connection con = DB.getConnection();
		boolean isSelectedUser=false;
		String query2="select * from Conversations where user1Id=? and user2Id=?";
		PreparedStatement stamt2 = con.prepareStatement(query2);
		stamt2.setInt(1,selectedUser);
		stamt2.setInt(2,currentUser);
		ResultSet rs2=stamt2.executeQuery();
	    if(rs2.next())
	    {
	    	isSelectedUser=true;
	    	//conversations table entry exists for selected user
	    }
		
		
		
		
		
		
	    ArrayList<Message> conversation = new ArrayList<Message>();
	    conversation = displayFullConversation(currentUser, selectedUser);
	    int newLastMessageId=conversation.get(conversation.size()-1).getMessageId();
	    System.out.println("updateEnteriesInConversationTable" +newLastMessageId);
	    
	    
	    //update the two enteries in the conversations table to this newlastmessageId
	    String s1="update Conversations set lastMessageId="+newLastMessageId+
	    		" where user1Id="+currentUser+" and user2Id="+selectedUser;
	    Statement stmt1=null,stmt2=null;
	    
	   // Connection con = DB.getConnection();
	    stmt1 = con.prepareStatement(s1);
		stmt1.executeUpdate(s1);
		
		if(isSelectedUser){
			String s2="update Conversations set lastMessageId="+newLastMessageId+
		    		" where user1Id="+selectedUser+" and user2Id="+currentUser;
			stmt2 = con.prepareStatement(s2);
			stmt2.executeUpdate(s2);
		    
		}
		else{
			 String query4="insert into Conversations values (null,?,?,?)";
		    	PreparedStatement stamt4 = con.prepareStatement(query4);
		    	stamt4.setInt(1,selectedUser);
		    	stamt4.setInt(2,currentUser);
		    	stamt4.setInt(3,newLastMessageId);
		        stamt4.executeUpdate();

			
		}
	    
		
	    }
	    catch(Exception E)
	    {System.out.println(E.getMessage());}
	    
	}
	
	
	public void refreshConversationsTable()
	{
		int latestMessageId,i=0;
		ResultSet rs;
		PreparedStatement stmt;
		ArrayList<Message>fullConversation = new ArrayList<Message>();

		try{
			Message msg;
			Connection con = DB.getConnection();
			String sql =" select * from Conversations;";
			stmt = con.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				fullConversation=displayFullConversation(rs.getInt("user1Id"), rs.getInt("user2Id"));		 
				Collections.sort(fullConversation); // ascending sort 

				//check if conversation table is up to date
				latestMessageId= fullConversation.get(fullConversation.size()-1).getMessageId();

				if (rs.getInt("lastMessageId")==latestMessageId)
				{
					//do nothing
				}
				else
				{
					String updateQuery = "update Conversations set lastMessageId="+latestMessageId+
							" where conversationId="+rs.getInt("conversationId");
					PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
					preparedStatement.executeUpdate(); 

				}

			}

		}catch(Exception E)
		{
			System.out.println(E.getMessage());
		}

	}

	
	public void deleteConversation(int currentUser,int selectedUser)
	{
		int messageId;
		String delete="";
		ArrayList<Message> conversation = new ArrayList<Message>();
				
		//delete the entry from conversations table
		Connection con = DB.getConnection();
		Statement stmt = null,stmt1=null;
		
		
		try
		{
	
		String deleteFromConversation="delete from Conversations " +
				"where user1Id="+currentUser+" and user2Id="+selectedUser;
		stmt=con.createStatement();
		stmt.executeUpdate(deleteFromConversation);
		
		conversation=displayFullConversation(currentUser, selectedUser);
        for(Message msg:conversation)
        {
          	messageId=msg.getMessageId();
          	if(msg.getSender()== currentUser)
          	{
          		//current=sender
          		delete = "update Messages  set fromValidity=0 where messageId="+messageId;
				//set sender validity to 0;
          	}
          	else if(msg.getReceiver()== currentUser)
          	{
          		//current=receiver
          		delete = "update Messages  set toValidity=0 where messageId="+messageId;
				//set receiver validity to 0;

          	}
          	stmt1=con.createStatement();
			stmt1.executeUpdate(delete);
	         	
        }	
		
				

				}
		catch(Exception E)
		{
			System.out.println(E.getMessage());
		}
		
	}
	
	
	/* old no flag ............... */
	
	/*public void deleteConversation(int currentUser,int selectedUser) 
	{
		// delete from conversations table is taken care of because 
		// lastMessageId in conversations table refers to messageId of the messages table
		//and it is onUpdate cascade  and onDelete cascade
		//so deleting from messages tble will automatically delete it from conversations table.

		Statement stmt = null,stmt1=null;
		Connection con = DB.getConnection();
		try
		{
			//delete from Messages 
			//where sender=loggedInUser and receiver=selectedUser 
			//or receiver=loggedInUser and sender=selectedUser

			String deleteQuery1="delete from Messages " +
					"where sender="+currentUser+" and receiver="+selectedUser+ 
					";";
			String deleteQuery2="delete from Messages " +
					"where sender="+selectedUser+" and receiver="+currentUser+
					";";

			stmt=con.createStatement();
			stmt.executeUpdate(deleteQuery1);
			stmt1=con.createStatement();
			stmt1.executeUpdate(deleteQuery2);
			DB.close(con);

		}
		catch(Exception E)
		{
			System.out.println(E.getMessage());
		}
	}

*/
}
