package edu.iiitb.facebook.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import edu.iiitb.facebook.model.NewsFeedComment;
import edu.iiitb.facebook.model.NewsFeedDao;
import edu.iiitb.facebook.model.NewsFeedPost;
import edu.iiitb.facebook.model.NewsFeedPostOwner;

public class NewsFeedAction extends ActionSupport implements SessionAware {

	private ArrayList<String> postId1;
	private boolean deleteOwnNews;
	private boolean olderPost;
	public String deleteComment() {
		setProfileId(profileId);
		System.out.println("submit comment" + profileId);

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			System.out.println("delete comment");
			NewsFeedDao.deleteComment(commentIndex);
			return "success";
		}
	}

	public String deletePost() {

		setProfileId(profileId);
		System.out.println("submit comment" + profileId);
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			System.out.println("Delete" + index);
			NewsFeedDao.deletePost(index);
			return "success";
		}
	}

	int profileId;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public ArrayList<String> getPostId1() {
		return postId1;
	}

	public void setPostId1(ArrayList<String> postId1) {
		this.postId1 = postId1;
	}

	private ArrayList<NewsFeedPost> posts;
	private ArrayList<String> description;
	private ArrayList<String> postId;
	private int photoId1;
	private int index;
	private NewsFeedComment currentComment;

	public NewsFeedComment getCurrentComment() {
		return currentComment;
	}

	public void setCurrentComment(NewsFeedComment currentComment) {
		this.currentComment = currentComment;
	}

	private int commentIndex;

	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<String> getPostId() {
		return postId;
	}

	public void setPostId(ArrayList<String> postId) {
		this.postId = postId;
	}

	public int getPhotoId1() {
		return photoId1;
	}

	public void setPhotoId1(int photoId1) {
		this.photoId1 = photoId1;
	}

	public ArrayList<String> getDescription() {
		return description;
	}

	public void setDescription(ArrayList<String> description) {
		this.description = description;
	}

	private ArrayList<NewsFeedPostOwner> peopleLiked;

	public int getCommentIndex() {
		return commentIndex;
	}

	public void setCommentIndex(int commentIndex) {
		this.commentIndex = commentIndex;
	}

	public ArrayList<NewsFeedPost> getPosts() {
		return posts;
	}

	public void setPosts(ArrayList<NewsFeedPost> posts) {
		this.posts = posts;
	}

	public String likeAction() {

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			NewsFeedDao.insertPostLike(index,
					(Integer) session.getAttribute("profileId"));
			return "success";
		}
	}

	public String unlikeAction() {

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			NewsFeedDao.deletePostLike(index,
					(Integer) session.getAttribute("profileId"));
			return "success";
		}
	}

	public String likeCommentAction() {

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			System.out.println(commentIndex);
			NewsFeedDao.insertCommentLike(commentIndex,
					(Integer) session.getAttribute("profileId"));
			return "success";
		}
	}

	public String unlikeCommentAction() {

		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
		
			NewsFeedDao.deleteCommentLike(commentIndex,
					(Integer) session.getAttribute("profileId"));
			return "success";
		}
	}

	public String showLikesAction() {

		peopleLiked = NewsFeedDao.showPostLikes(index);
		return "success";
	}

	public String showCommentLikesAction() {

		peopleLiked = NewsFeedDao.showCommentLikes(commentIndex);
		return "success";
	}

	public String getPost() {
		if(olderPost || count==0)
		count++;
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {
			return "login";
		} else {
			System.out.println("count is "+count);
			posts = NewsFeedDao.getPosts(
					(Integer) session.getAttribute("profileId"), count);
			
			photoId1 = NewsFeedDao.getOwnerDetails(
					(Integer) session.getAttribute("profileId")).getPhotoId();
			return "success";
		}
	}

	public String submitComment() {
		//System.out.println("YAHA PE!!!!");
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {

			setProfileId(profileId);
			System.out.println("submit comment" + profileId);
			int cnt = 0;
			for (String des : description) {
				if (!des.equals("")) {
					System.out
							.println("In submit comment "
									+ des
									+ " "
									+ index
									+ " "
									+ (Integer) session
											.getAttribute("profileId"));

					setCurrentComment(NewsFeedDao.insertComment(
							(Integer) session.getAttribute("profileId"), des,
							index));

				}
				cnt++;
			}

			return "success";
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<NewsFeedPostOwner> getPeopleLiked() {
		return peopleLiked;
	}

	public void setPeopleLiked(ArrayList<NewsFeedPostOwner> peopleLiked) {
		this.peopleLiked = peopleLiked;
	}

	public String retrieveOwnNews() {
		if(olderPost || count==0)
		count++;
		deleteOwnNews = true;
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			int profileid = (Integer) session.getAttribute("profileId");
			posts = NewsFeedDao.getOwnPosts(profileid, profileid, count);
			photoId1 = NewsFeedDao.getOwnerDetails(
					(Integer) session.getAttribute("profileId")).getPhotoId();

			return "success";
		}
	}

	public String retrieveOwnNewsForHypelink() {
	System.out.println(olderPost+" "+profileId);
		if(olderPost || count==0)
		count++;
		deleteOwnNews = false;
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		if (session == null || session.getAttribute("login") == null) {

			return "login";
		} else {
			System.out.println(profileId);
			posts = NewsFeedDao.getOwnPosts(profileId,
					(Integer) session.getAttribute("profileId"), count);
			photoId1 = NewsFeedDao.getOwnerDetails(
					(Integer) session.getAttribute("profileId")).getPhotoId();
			return "success";
		}
	}

	public boolean isDeleteOwnNews() {
		return deleteOwnNews;
	}

	public void setDeleteOwnNews(boolean deleteOwnNews) {
		this.deleteOwnNews = deleteOwnNews;
	}

	public boolean isOlderPost() {
		return olderPost;
	}

	public void setOlderPost(boolean olderPost) {
		this.olderPost = olderPost;
	}

	// apurva work ends

}
