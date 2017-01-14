package edu.iiitb.facebook.model;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.iiitb.facebook.util.DB;

public class NewsFeedDao {
	public static void deletePost(int postId/* , int userId */) {
		try {
			Statement stmt = null;
			Connection con = DB.getConnection();
			stmt = con.createStatement();

			String sql = "DELETE FROM Post WHERE postId=" + postId + ";";
			// System.out.println(sql);
			stmt.executeUpdate(sql);

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void deleteComment(int commentId) {
		try {
			Statement stmt = null;
			Connection con = DB.getConnection();
			stmt = con.createStatement();

			String sql = "DELETE FROM Comment WHERE commentId=" + commentId
					+ ";";
			stmt.executeUpdate(sql);

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<NewsFeedComment> getComments(int postId, int userId) {
		ArrayList<NewsFeedComment> comments = new ArrayList<NewsFeedComment>();
		NewsFeedComment comment;
		String query;
		query = "select commentId,profileId, description, commentTime from Comment where postId="
				+ postId;
		Connection connection;
		connection = DB.getConnection();
		ResultSet resultSet = null;
		resultSet = DB.readFromDB(query, connection);
		NewsFeedPostOwner postOwner;
		try {
			while (resultSet.next()) {
				comment = new NewsFeedComment();
				
				comment.setTime(resultSet.getTime("commentTime") + "");
				
				if (userId == resultSet.getInt("profileId"))
					comment.setMyComment(true);//your own comment..will be used to check for delete button
				comment.setTime(convertTime(comment.getTime()));
				comment.setDate(convertDate(resultSet.getDate("commentTime")
						+ ""));
				comment.setDescription(resultSet.getString("description"));
				postOwner = new NewsFeedPostOwner();
				postOwner = getOwnerDetails(resultSet.getInt("profileId"));
				comment.setCommentOwner(postOwner);
				comment.setCommentId(resultSet.getInt("commentId"));
				comment.setLikeCount(getCommentLikes(
						resultSet.getInt("commentId")).size());
				comment.setPeopleLiked(getCommentLikes(resultSet
						.getInt("commentId")));
				if (getCommentLikes(resultSet.getInt("commentId")).contains(
						getOwnerDetails(userId).getUserName()))
					comment.setYouLiked(true);//for liked unlike button

				comments.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return comments;
	}

	public static String convertDate(String oldDate) {
		String newDate = " ";
		String ddMM[] = oldDate.split("-");
		switch (Integer.parseInt(ddMM[1])) {
		case 1:
			newDate += "January";
			break;
		case 2:
			newDate += "February";
			break;
		case 3:
			newDate += "March";
			break;
		case 4:
			newDate += "April";
			break;
		case 5:
			newDate += "May";
			break;
		case 6:
			newDate += "June";
			break;
		case 7:
			newDate += "July";
			break;
		case 8:
			newDate += "August";
			break;
		case 9:
			newDate += "September";
			break;
		case 10:
			newDate += "October";
			break;
		case 11:
			newDate += "November";
			break;
		case 12:
			newDate += "December";
			break;

		}
		if (ddMM[2].charAt(0) != '0')//agar 01 aaya toh 1 chahiye
			newDate += " " + ddMM[2];
		else
			newDate += " " + ddMM[2].charAt(1);

		return newDate;
	}

	public static String convertTime(String time) {
		String newTime = " ";
		String hhMM[] = time.split(":");
		if (Integer.parseInt(hhMM[0]) > 12) {
			newTime += (Integer.parseInt(hhMM[0]) - 12) + ":" + hhMM[1] + "pm";
		} else
			newTime += hhMM[0] + ":" + hhMM[1] + "am";

		return newTime;
	}

	public static NewsFeedComment insertComment(int userId, String description,
			int postId) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		NewsFeedComment comm = new NewsFeedComment();
		try {
			Statement stmt = null;
			Connection con = DB.getConnection();
			stmt = con.createStatement();

			String sql = "INSERT INTO Comment (profileId, postId, commentTime, description) "
					+ "VALUES ("
					+ userId
					+ ", "
					+ postId
					+ ", '"
					+ dateFormat.format(date).toString()
					+ "', '"
					+ description
					+ "');";
			// System.out.println(sql);

			stmt.executeUpdate(sql);
			sql = "SELECT commentId from Comment order by commentID desc LIMIT 1 ";
			ResultSet rs = stmt.executeQuery(sql);
			int commentId = 0;
			while (rs.next()) {
				commentId = rs.getInt("commentId");
			}

			comm.setCommentId(commentId);
			comm.setDate(dateFormat.format(date).toString());

			sql = "SELECT firstName, lastName,profilePicId from Profile where profileId='"
					+ userId + "'";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				NewsFeedPostOwner owner = new NewsFeedPostOwner();
				owner.setUserName((rs.getString("firstName") + " " + rs
						.getString("lastName")));
				owner.setPhotoId(rs.getInt("profilePicId"));
				owner.setUserId(userId);
				comm.setCommentOwner(owner);
			}
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return comm;
	}

	public static void insertPostLike(int postId, int userId) {

		if (getPostLikes(postId, userId).size()==0 || !getPostLikes(postId, userId).get(0).getUserName().equals("You")) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			try {
				Statement stmt = null;
				Connection con = DB.getConnection();
				stmt = con.createStatement();

				String sql = "INSERT INTO Likes (profileId,type, postId, likeTime) "
						+ "VALUES ("
						+ userId
						+ ", 'post', "
						+ postId
						+ ", '"
						+ dateFormat.format(date).toString() + "');";
				stmt.executeUpdate(sql);
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void deletePostLike(int postId, int userId) {
		try {
			Statement stmt = null;
			Connection con = DB.getConnection();
			stmt = con.createStatement();

			String sql = "DELETE FROM Likes WHERE postId=" + postId
					+ " and profileId=" + userId + ";";
			stmt.executeUpdate(sql);

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void insertCommentLike(int commentId, int userId) {
		if (!getCommentLikes(commentId).contains(
				getOwnerDetails(userId).getUserName())) {

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			try {
				Statement stmt = null;
				Connection con = DB.getConnection();
				stmt = con.createStatement();

				String sql = "INSERT INTO Likes (profileId,type, commentId, likeTime) "
						+ "VALUES ("
						+ userId
						+ ", 'comment', "
						+ commentId
						+ ", '" + dateFormat.format(date).toString() + "');";
				stmt.executeUpdate(sql);
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteCommentLike(int commentId, int userId) {
		try {
			Statement stmt = null;
			Connection con = DB.getConnection();
			stmt = con.createStatement();

			String sql = "DELETE FROM Likes WHERE commentId=" + commentId
					+ " and profileId=" + userId + ";";
			stmt.executeUpdate(sql);

			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<NewsFeedPostOwner> showCommentLikes(int commentId) {
		ArrayList<NewsFeedPostOwner> peopleLiked = new ArrayList<NewsFeedPostOwner>();
		ResultSet resultSet = null;
		String query;
		query = "select profileId from Likes where type='comment' and commentId="
				+ commentId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		NewsFeedPostOwner postOwner = new NewsFeedPostOwner();
		try {
			while (resultSet.next()) {
				postOwner = getOwnerDetails(resultSet.getInt("profileId"));
				peopleLiked.add(postOwner);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		// System.out.println(peopleLiked);
		return peopleLiked;
	}

	public static ArrayList<NewsFeedPostOwner> showPostLikes(int postId) {
		ArrayList<NewsFeedPostOwner> peopleLiked = new ArrayList<NewsFeedPostOwner>();
		ResultSet resultSet = null;
		String query;
		query = "select profileId from Likes where type='post' and postId="
				+ postId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		NewsFeedPostOwner postOwner = new NewsFeedPostOwner();
		try {
			while (resultSet.next()) {
				postOwner = getOwnerDetails(resultSet.getInt("profileId"));
				peopleLiked.add(postOwner);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		// System.out.println(peopleLiked);
		return peopleLiked;
	}

	public static ArrayList<NewsFeedPostOwner> getPostLikes(int postId, int userId) {
		ArrayList<NewsFeedPostOwner> peopleLiked = new ArrayList<NewsFeedPostOwner>();
		ResultSet resultSet = null;
		String query;
		query = "select profileId from Likes where postId=" + postId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		NewsFeedPostOwner postOwner = new NewsFeedPostOwner();
		try {
			while (resultSet.next()) {
				postOwner = new NewsFeedPostOwner();
				/*checking if it is the users own id(the user has liked the post..then we will add 'You' at the beginning of the peopleliked array list)*/
				if (resultSet.getInt("profileId") == userId) {
					if (peopleLiked.size() != 0
							&& !peopleLiked.get(0).getUserName().equals("You"))
					{
						postOwner.setUserName("You");
						peopleLiked.add(0, postOwner);
					}
					else if (peopleLiked.size() == 0)//the 1st id retrieved is your own id
					{
						postOwner.setUserName("You");
						peopleLiked.add(0, postOwner);
					}
				} else {
					postOwner = getOwnerDetails(resultSet.getInt("profileId"));//getting details of the rest of users who liked the post
					peopleLiked.add(postOwner);
				}
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		// System.out.println(peopleLiked);
		return peopleLiked;
	}

	public static ArrayList<String> getCommentLikes(int commentId) {
		ArrayList<String> peopleLiked = new ArrayList<String>();
		ResultSet resultSet = null;
		String query;
		query = "select profileId from Likes where commentId=" + commentId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		NewsFeedPostOwner postOwner = new NewsFeedPostOwner();
		try {
			while (resultSet.next()) {
				postOwner = getOwnerDetails(resultSet.getInt("profileId"));
				peopleLiked.add(postOwner.getUserName());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		// System.out.println("Comment "+peopleLiked);
		return peopleLiked;
	}

	public static ArrayList<NewsFeedPost> getPosts(int userId, int count) {
		ArrayList<NewsFeedPost> posts = new ArrayList<NewsFeedPost>();
		NewsFeedPost post;
		ResultSet resultSet = null;
		String query;
		query = "SELECT postId,owner,type,time,statusId,photoId,postOnWallId FROM Post "
				+ "where owner in (select userId2 as friend from Friendship where areFriends='Y' and userId1="
				+ userId
				+ ")"
				+ " or owner="
				+ userId
				+ " or owner in (select userId1 as friend from Friendship where areFriends='Y' and userId2="
				+ userId
				+ ")"
				+ " order by postId Desc limit "
				+ 10
				* count//retrieves 10 at a time
				+ ";";
		System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				// System.out.println("here");
				post = new NewsFeedPost();
				if (userId == resultSet.getInt("owner"))
					post.setOwnPost(true);//if it is your own post
				post.setPostId(resultSet.getInt("postId"));
				
				//get the list of people who liked this post
				post.setPeopleLiked(getPostLikes(resultSet.getInt("postId"),
						userId));
				if (post.getPeopleLiked().size()!=0 && post.getPeopleLiked().get(0).getUserName().equals("You"))
					post.setYouLiked(true);//if you liked the post
				post.setLikeCount(post.getPeopleLiked().size());
				
				post.setPostOwner(getOwnerDetails(resultSet.getInt("owner")));
				post.setPostTime(convertTime(resultSet.getTime("time") + ""));
				post.setPostDate(convertDate(resultSet.getDate("time") + ""));
				
				post.setType(resultSet.getString("type"));
				
				if (post.getType().equals("text")) {
					post.setUserStatus(getUserStatus(resultSet
							.getInt("statusId")));
					// System.out.println("text");
				} else if (post.getType().equals("postOnWall")) {
					// System.out.println("text");
//					System.out.println("postOnWall");
					if (resultSet.getInt("owner") != userId)

						post.setUserStatus(getPostMessage(//agar dono dost hai toh hi display hoga
								resultSet.getInt("postOnWallId"), userId));
					else
						continue;
					if (post.getUserStatus().equals("")//if both are not your friends just skip this post
							|| post.getUserStatus() == null)
						continue;
				}

				
				post.setComment(getComments(resultSet.getInt("postId"), userId));//ab comment lao
				post.setCommentCount(post.getComment().size());
				posts.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return posts;
	}

	public static String getUserStatus(int userStatusId) {
		String userStatus = new String();
		// get userStatus details
		ResultSet resultSet = null;
		String query;
		query = "select description from UserStatus where userStatusId="
				+ userStatusId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				userStatus = resultSet.getString("description");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return userStatus;

	}

	public static NewsFeedPostOwner getOwnerDetails(int ownerId) {
		// get postOwner Name and pic
		NewsFeedPostOwner postOwner = new NewsFeedPostOwner();
		ResultSet resultSet = null;
		String query;
		query = "select profileId,firstName,lastName,profilePicId from Profile where profileId="
				+ ownerId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				if (resultSet.getString("lastName") != null)
					postOwner.setUserName(resultSet.getString("firstName")
							+ " " + resultSet.getString("lastName"));
				else
					postOwner.setUserName(resultSet.getString("firstName"));
				postOwner.setPhotoId(resultSet.getInt("profilePicId"));
				postOwner.setUserId(resultSet.getInt("profileId"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return postOwner;
	}

	public static String getPostMessage(int postOnWallId, int userId) {
		String msg = "";
		// get userStatus details
		ResultSet resultSet = null;
		String query;
		query = "select fromId,toId,description from PostOnWall where postOnWallId="
				+ postOnWallId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				if (areFriends(userId, resultSet.getInt("toId"))
						&& areFriends(userId, resultSet.getInt("fromId"))) {
					msg = getOwnerDetails(resultSet.getInt("fromId"))
							.getUserName()
							+ " wrote '"
							+ resultSet.getString("description")
							+ "' on "
							+ ""
							+ getOwnerDetails(resultSet.getInt("toId"))
									.getUserName() + "'s wall";

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return msg;

	}

	public static int getFromPostOnWall(int postOnWallId) {
		int fromId = 0;
		ResultSet resultSet = null;
		String query;
		query = "select fromId from PostOnWall where postOnWallId="
				+ postOnWallId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				fromId = resultSet.getInt("fromId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return fromId;

	}

	public static ArrayList<NewsFeedPost> getOwnPosts(int otherProfileId,
			int yourProfileId, int count) {
		ArrayList<NewsFeedPost> posts = new ArrayList<NewsFeedPost>();
		NewsFeedPost post;
		ResultSet resultSet = null;
		String query;
		query = "SELECT postId,owner,type,time,statusId,photoId,postOnWallId FROM Post "
				+ "where owner="
				+ otherProfileId
				+ " order by postId Desc limit " + (5 * count) + ";";
		// System.out.println(query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				// System.out.println("here");
				post = new NewsFeedPost();
				post.setPostId(resultSet.getInt("postId"));
				// System.out.println("Post ID"+resultSet.getInt("postId"));
				post.setPeopleLiked(getPostLikes(resultSet.getInt("postId"),
						yourProfileId));
				if (post.getPeopleLiked().size()!=0 && post.getPeopleLiked().get(0).getUserName().equals("You"))
					post.setYouLiked(true);
				post.setLikeCount(post.getPeopleLiked().size());
				// System.out.println("Like count "+post.getPeopleLiked().size());
				post.setPostOwner(getOwnerDetails(resultSet.getInt("owner")));
				post.setPostTime(convertTime(resultSet.getTime("time") + ""));
				post.setPostDate(convertDate(resultSet.getDate("time") + ""));
				// System.out.println(post.getPostDate());
				post.setType(resultSet.getString("type"));
				// System.out.println(resultSet.getString("type"));
				if (post.getType().equals("text")) {
					post.setUserStatus(getUserStatus(resultSet
							.getInt("statusId")));
					if (yourProfileId == resultSet.getInt("owner")) {
						// System.out.println("I can, I am owner");
						post.setOwnPost(true);
					}

					// System.out.println("text");
				} else if (post.getType().equals("postOnWall")) {
					post.setPostOwner(getOwnerDetails(getFromPostOnWall(resultSet
							.getInt("postOnWallId"))));
					post.setUserStatus(getPostonWall(resultSet
							.getInt("postOnWallId")));
					if (post.getPostOwner().getUserId() == yourProfileId) {
						// System.out.println("I can, I am owner");
						post.setOwnPost(true);
					}

					// System.out.println("text");
				}

				post.setComment(getComments(resultSet.getInt("postId"),
						yourProfileId));
				post.setCommentCount(post.getComment().size());
				posts.add(post);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return posts;
	}

	public static boolean areFriends(int user1, int user2) {
		ResultSet resultSet = null;
		String query;
		query = "select areFriends from Friendship where ((userId1=" + user1
				+ " and userId2=" + user2 + ") or " + " (userId2=" + user1
				+ " and userId1=" + user2 + "));";
		//System.out.println("are frnds" + query);
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				if (resultSet.getString("areFriends").charAt(0) == 'Y')
					return true;
				else
					return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);

		return false;
	}

	public static String getPostonWall(int postOnWallId) {
		String userStatus = new String();
		// get userStatus details
		ResultSet resultSet = null;
		String query;
		query = "select description from PostOnWall where postOnWallId="
				+ postOnWallId;
		Connection connection;
		connection = DB.getConnection();
		resultSet = DB.readFromDB(query, connection);
		try {
			while (resultSet.next()) {
				userStatus = resultSet.getString("description");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(resultSet);
		DB.close(connection);
		return userStatus;

	}

}
