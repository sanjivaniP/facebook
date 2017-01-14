package edu.iiitb.facebook.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.iiitb.facebook.model.*;

public class MessageAction {
	private String reply, deleteChoice, To;
	String messageId;
	private int user2Id, currentUser;
	private static int selectedUser = 0;
	private int selectedUser1;
	private int selectedUser2;

	private static ArrayList<Message> fullConversation;
	private static ArrayList<MessageLastConversation> recentConversationWithAll;
	private Map<Integer, Boolean> checkboxes;
	private Boolean firstLoginDone = false;

	public String checkbox() throws Exception {
		int messageId;
		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			currentUser = (Integer) session.getAttribute("profileId");
			System.out.println("inside checkbx");
			System.out.println(checkboxes.toString());
			System.out.println(checkboxes.get(0));
			// map->list of messageids to be deleted
			System.out.println(checkboxes.size());
			for (int i = 0; i < checkboxes.size(); i++) {
				if (checkboxes.get(i)) {
					// this message is marked to be deleted
					messageId = fullConversation.get(i).getMessageId();
					// pass this one message to dao
					MessageDao msgDao = new MessageDao();
			//		msgDao.deleteMessage(messageId, currentUser, selectedUser);
				}
			}
			loadBasicMessagePage();
			return "success";
		}

	}

	public String getAllMessagesInDatabase() throws Exception {

		MessageDao messageDao = new MessageDao();
		messageDao.getAllMessagesInDatabase();
		return "success";
	}


	public String displayRecentConversationWithAll() throws Exception {

		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			currentUser = (Integer) session.getAttribute("profileId");
			MessageDao messageDao = new MessageDao();
			messageDao.displayRecentConversationWithAll(currentUser);
			return "success";
		}
	}

	public String loadBasicMessagePage() throws Exception {//called everytime the message page is reloaded

		// 6 is the userId of logged in user .
		int user2Id;
		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			recentConversationWithAll=null;//left pane list of conversation
			fullConversation=null;
			currentUser = (Integer) session.getAttribute("profileId");
			MessageDao messageDao = new MessageDao();
			recentConversationWithAll = messageDao
					.displayRecentConversationWithAll(currentUser);//last messages with everyone
			if(recentConversationWithAll.size()!=0)
			{
				user2Id = recentConversationWithAll.get(0).getUser2Id();
				if (selectedUser == 0 || selectedUser == currentUser)//making the 2nd user of the most recent conversation as the selected user
					selectedUser = user2Id;
				// by default the first person in the list will be the selected user
				fullConversation = messageDao.displayFullConversation(currentUser,
						user2Id);
				/*System.out.println("selected user in load basic message page "
						+ selectedUser);*/
			}
			return "success";
		}
	}

	public String loadConversationWithSelectedUser() throws Exception {
		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			currentUser = (Integer) session.getAttribute("profileId");
			MessageDao messageDao = new MessageDao();
			recentConversationWithAll = messageDao
					.displayRecentConversationWithAll(currentUser);
			fullConversation = messageDao.displayFullConversation(currentUser,
					selectedUser);
			// selectedUser1=selectedUser;
			return "success";
		}
	}

	public String storeReplyToSelectedConversation() throws Exception {
		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			currentUser = (Integer) session.getAttribute("profileId");
			// currentUser = loggedInUser
			if (To == null) // this is not new message , it is reply to an
				// existing conversaton
			{
				MessageDao messageDao = new MessageDao();
				System.out
				.println("selected user in store replt to selected conversation is : "
						+ selectedUser);
				messageDao.storeReply(reply, selectedUser, currentUser);
				messageDao.updateEntriesInConversationsTable(currentUser, selectedUser);
				//messageDao.refreshConversationsTable();
				selectedUser = 0;
				loadBasicMessagePage();
				return "success";
			}
			return "success";

		}
	}

	public String storeNewMessage() throws Exception {
		//check for if conversation with this user already exists

		int receiver =0;
		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			if(To!=null)
			{

				try
				{
					receiver= Integer.parseInt(To);
					currentUser = (Integer) session.getAttribute("profileId");
					MessageDao messageDao = new MessageDao();
					messageDao.storeNewMessage(reply, receiver, currentUser);
					selectedUser=0;
					loadBasicMessagePage();

					// insert it into database by calling a method in dao
					// insert into the conversations table also
				}

				catch(NumberFormatException ne)
				{

				}

			}

			return "success";
		}
	}

	public String deleteMessage() throws Exception {
		// selectedUser visible
		int currentUser;

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			currentUser = (Integer) session.getAttribute("profileId");
			MessageDao msgDao = new MessageDao();
			if (deleteChoice.equals("Delete Conversation")) {
				System.out.println("shubham=="+selectedUser);
				System.out.println("shubham=="+currentUser);
				msgDao.deleteConversation(currentUser, selectedUser);
				selectedUser=0;
				loadBasicMessagePage();
				return "conversation";
			} else if (deleteChoice.equals("Delete Messages")) {
				return "message";
			}

			return "";
		}

	}

	public String deleteSelectedMessage() throws Exception {
		int messageId;
		int currentUser,deleter;
		String currentUserStatus="";

		System.out.println("inside checkbx");
		System.out.println(checkboxes.toString());
		System.out.println(checkboxes.get(0));
		// map->list of messageids to be deleted
		System.out.println(checkboxes.size());


		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			currentUser = (Integer) session.getAttribute("profileId");

			// map->list of messageids to be deleted

			for (int i = 0; i < checkboxes.size(); i++) {
				if (checkboxes.get(i)) {
					// this message is marked to be deleted
					messageId = fullConversation.get(i).getMessageId();
					// pass this one message to dao
					//identify deleter as sender or receiver
					if(currentUser==fullConversation.get(i).getSender())
					{
						currentUserStatus="sender";
					}
					else if(currentUser==fullConversation.get(i).getReceiver())
					{
						currentUserStatus="receiver";	
					}
					MessageDao msgDao = new MessageDao();
					msgDao.deleteMessage(messageId, currentUser, selectedUser,currentUserStatus);
				}
			}
			selectedUser=0;
			loadBasicMessagePage();
			return "success";
		}
	}

	public String redirectToCreateMessage() throws Exception {
		//System.out.println("In redirect to create message");
		return "success";
	}

	public final int getUser2Id() {
		return user2Id;
	}

	public final void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	public final void setFullConversation(ArrayList<Message> fullConversation) {
		this.fullConversation = fullConversation;
	}

	public final ArrayList<Message> getFullConversation() {
		return fullConversation;
	}

	public final ArrayList<MessageLastConversation> getRecentConversationWithAll() {
		return recentConversationWithAll;
	}

	public final void setRecentConversationWithAll(
			ArrayList<MessageLastConversation> recentConversationWithAll) {
		this.recentConversationWithAll = recentConversationWithAll;
	}

	public final int getSelectedUser() {
		return selectedUser;
	}

	public final void setSelectedUser(int selectedUser) {
		this.selectedUser = selectedUser;
	}

	public final String getReply() {
		return reply;
	}

	public final void setReply(String reply) {
		this.reply = reply;
	}

	public final String getDeleteChoice() {
		return deleteChoice;
	}

	public final void setDeleteChoice(String deleteChoice) {
		this.deleteChoice = deleteChoice;
	}

	public final String getTo() {
		return To;
	}

	public final void setTo(String to) {
		this.To = to;
	}

	public final String getMessageId() {
		return messageId;
	}

	public final void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public final int getCurrentUser() {
		return currentUser;
	}

	public final void setCurrentUser(int currentUser) {
		this.currentUser = currentUser;
	}

	public final Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}

	public final void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}

	public final int getSelectedUser1() {
		return selectedUser1;
	}

	public final void setSelectedUser1(int selectedUser1) {
		this.selectedUser1 = selectedUser1;
	}

	public final int getSelectedUser2() {
		return selectedUser2;
	}

	public final void setSelectedUser2(int selectedUser2) {
		this.selectedUser2 = selectedUser2;
	}

}
