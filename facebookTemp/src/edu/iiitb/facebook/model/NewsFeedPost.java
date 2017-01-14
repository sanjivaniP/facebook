package edu.iiitb.facebook.model;

import java.util.ArrayList;

public class NewsFeedPost {
	private int postId;
	private int likeCount;
	private int commentCount;
	private boolean ownPost;
	private NewsFeedPostOwner postOwner;
	private String userStatus;
	private String type;
	private String postDate;
	private String postTime;
	private ArrayList<NewsFeedPostOwner> peopleLiked;
	private boolean youLiked;
	private ArrayList<NewsFeedComment> comment;
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public NewsFeedPostOwner getPostOwner() {
		return postOwner;
	}
	public void setPostOwner(NewsFeedPostOwner postOwner) {
		this.postOwner = postOwner;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<NewsFeedPostOwner> getPeopleLiked() {
		return peopleLiked;
	}
	public void setPeopleLiked(ArrayList<NewsFeedPostOwner> peopleLiked) {
		this.peopleLiked = peopleLiked;
	}
	public boolean isYouLiked() {
		return youLiked;
	}
	public void setYouLiked(boolean youLiked) {
		this.youLiked = youLiked;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public ArrayList<NewsFeedComment> getComment() {
		return comment;
	}
	public void setComment(ArrayList<NewsFeedComment> comment) {
		this.comment = comment;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public boolean isOwnPost() {
		return ownPost;
	}
	public void setOwnPost(boolean ownPost) {
		this.ownPost = ownPost;
	}
	
}
