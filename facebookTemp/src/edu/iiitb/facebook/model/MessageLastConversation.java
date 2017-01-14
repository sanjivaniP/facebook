package edu.iiitb.facebook.model;

import java.sql.Timestamp;

public class MessageLastConversation implements Comparable<MessageLastConversation>{
private int user2Id;
private String user2Name;
private String lastMessageText;
private Timestamp time;
private int photoId;

public final String getUser2Name() {
	return user2Name;
}
public final void setUser2Name(String user2Name) {
	this.user2Name = user2Name;
}
public final String getLastMessageText() {
	return lastMessageText;
}
public final void setLastMessageText(String lastMessageText) {
	this.lastMessageText = lastMessageText;
}
public final int getUser2Id() {
	return user2Id;
}
public final void setUser2Id(int user2Id) {
	this.user2Id = user2Id;
}
public final Timestamp getTime() {
	return time;
}
public final void setTime(Timestamp time) {
	this.time = time;
}
@Override
public int compareTo(MessageLastConversation compare) {//for sort function to return values sort in order of time
	// TODO Auto-generated method stub
	 Timestamp compareTime = compare.getTime(); 
	 //getTime() will return only timestamp...but getTime().getTime() returns a long value that is in seconds..and hence can be compared
	 return (int)(compare.getTime().getTime()-this.getTime().getTime());//descending 
	//return (int)(this.getTime().getTime()-compare.getTime().getTime());//ascending
    //second getTIme call is a method of timestamp to get the tim in long.
}
public int getPhotoId() {
	return photoId;
}
public void setPhotoId(int photoId) {
	this.photoId = photoId;
}

}
