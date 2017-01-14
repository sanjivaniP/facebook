package edu.iiitb.facebook.model;

import java.util.ArrayList;
import java.sql.*;
public class Message implements Comparable<Message>
{
//Boolean sets initially to null boolean doesnt	
private int messageId ;
private int sender ;
private Boolean fromValidity;
private int receiver ;
private Boolean toValidity;
private String text;
private Timestamp sentAt;
private Timestamp seenAt;
private String senderName;
private int photoId;
private String sentAtTime;


public void setTimeInString(Timestamp givenTime)
{
  	sentAtTime=givenTime.toString();
}


@Override
public int compareTo(Message o) {
// TODO Auto-generated method stub
 
//return (int)(o.getSentAt().getTime()-this.getSentAt().getTime());// descending
return (int)(this.getSentAt().getTime()-o.getSentAt().getTime());//ascending
}


public final int getMessageId() {
	return messageId;
}
public final void setMessageId(int messageId) {
	this.messageId = messageId;
}

public final Boolean getFromValidity() {
	return fromValidity;
}
public final void setFromValidity(Boolean fromValidity) {
	this.fromValidity = fromValidity;
}

public final Boolean getToValidity() {
	return toValidity;
}
public final void setToValidity(Boolean toValidity) {
	this.toValidity = toValidity;
}
public final String getText() {
	return text;
}
public final void setText(String text) {
	this.text = text;
}
public final Timestamp getSentAt() {
	return sentAt;
}
public final void setSentAt(Timestamp sentAt) {
	this.sentAt = sentAt;
}
public final Timestamp getSeenAt() {
	return seenAt;
}
public final void setSeenAt(Timestamp seenAt) {
	this.seenAt = seenAt;
}
public final int getSender() {
	return sender;
}
public final void setSender(int sender) {
	this.sender = sender;
}
public final int getReceiver() {
	return receiver;
}
public final void setReceiver(int receiver) {
	this.receiver = receiver;
}
public final String getSenderName() {
	return senderName;
}
public final void setSenderName(String senderName) {
	this.senderName = senderName;
}


public int getPhotoId() {
	return photoId;
}


public void setPhotoId(int photoId) {
	this.photoId = photoId;
}


public final String getSentAtTime() {
	return sentAtTime;
}


public final void setSentAtTime(String sentAtTime) {
	this.sentAtTime = sentAtTime;
}




}
