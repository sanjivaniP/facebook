<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<script src="newsFeed/newsFeed.js"></script>
<title>Insert title here</title>
<script> $(document).ready(function(){ $("#flip").click(function(){ $("#panel").slideToggle(); }); }); 
$(document).ready(function(){ $("#flip1").click(function(){ $("#panel").slideUp(); }); }); 
function deletePost(form,num){
	

	var url = 'http://localhost:8080/facebookTemp/deletePost';
	/*	var abc=form[0][0];*/
		var posting = $.post(url, {
			"index" : num
		});

	posting.done(function(response) {
		document.getElementById("myForm").submit();
		
	});

	}
	function deleteComment(form,num){
		var url = 'http://localhost:8080/facebookTemp/deleteComment';
		/*	var abc=form[0][0];*/
		/*	alert(url);
		*/	var posting = $.post(url, {
				"commentIndex" : num
			});
		posting.done(function(response) {
			document.getElementById("myForm").submit();
			
		});
		}



</script>

<style type="text/css">
#panel,#flip {
	padding: 5px;
	text-align: center;
	background-color: white;
	border: solid 1px #c3c3c3;
}

#panel {
	padding: 50px;
	display: none;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td>
				<div id="flip">Add Photo/Create Album</div>
			</td>
		</tr>
	</table>
	<div id="panel">
		<a href="uploadPic.jsp" class="nameFont">Upload Photo</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="createAlbum.jsp" class="nameFont"> Create Album </a>
	</div>

	<form id="myForm" action="home1">
		<s:iterator value="posts"><!-- iterrating through post -->
			<div class="panel panel-default"
				id="main<s:property value="postId" />">
				<div class="panel-heading">
					<table border="0" cellspacing="0" bgcolor="#ffffff" width="560">
						<tr>
							<s:iterator value="postOwner"><!-- post owner iterator -->
							<!--  displaying profile pic and name hyperlinks of post owner -->
								<td rowspan="2" width="80"><a
									href="profileTempAction_hype?profileId=<s:property value="userId" />"><img
										src="image?photoId=<s:property value="photoId" />" height="60"
										width="60"></a></td>
								<td colspan="2"><a
									href="profileTempAction_hype?profileId=<s:property value="userId" />"
									class="nameFont"><span class="hover"><s:property
												value="userName" /></span></a></td>
							</s:iterator><!-- post owner iterator closed -->
						</tr>
						<tr>
							<td class="timeFont"><s:property value="postDate" /> at <s:property
									value="postTime" />.</td>
						</tr>
						<tr>
							<s:if test="ownPost"><!-- if your own post then show delete button -->
								<td colspan="2"><s:property value="userStatus" escape="false"/></td>
								<td><input type="button" value="Delete"
									onclick="deletePost(this.form,'<s:property value="postId"/>');" /></td>
							</s:if>
							<s:else>
								<td colspan="3"><s:property value="userStatus" escape="false"/></td>
							</s:else>
						</tr>

					</table>
				</div>
				<div class="panel-body">
					<div class="row" style="height: auto">
						<s:if test="youLiked"><!-- if you liked --Show unlike -->
							<a href=""
								onclick="unlike(this.form,event,'<s:property value="postId" />')"
								id="l<s:property value="postId" />" class="likeCommentFont">Unlike</a>
						</s:if>
						<s:else><!-- You did not like--Show Like -->
							<a href=""
								onclick="like(this.form,event,'<s:property value="postId" />')"
								id="l<s:property value="postId" />" class="likeCommentFont">Like</a>
						</s:else>
						. <a href="#" class="likeCommentFont"
							onclick="focusOnCommentBox('desc<s:property value="postId" />')">Comment</a>
						<s:if test="likeCount!=0">
		
						<!-- only you liked-->

							<s:if test="%{youLiked && likeCount==1}">
								<div id="youLike<s:property value='postId'/>"
									style="display: inline;">							<hr>
									<a href=""><img src="Images/like.jpg" height="20"
										width="20"></a> You like this
								</div>
							</s:if>
							<s:else>
							<hr>
							<!-- You have not liked but someone else has liked  -->
							<s:if test="%{likeCount==1}">
										<a href=""><img src="Images/like.jpg" height="20"
										width="20"></a>
							<s:iterator value="peopleLiked" status="peopleLikedStatus">
								<span id="youLike<s:property value='postId'/>"
											style="display: none;" >You and </span><!-- HIDDEN initially -->
										<a href="<s:url action='profileTempAction_hype' > 
											<s:param name="profileId"><s:property value="userId" /></s:param></s:url>" class="likeCommentFont"> 
											<s:property value="userName" /></a>
								</s:iterator>
								 like this.
							</s:if>
							<!-- Like count is 2 and even youLiked  -->
							<s:elseif test="%{likeCount==2 && youLiked}">
										<a href=""><img src="Images/like.jpg" height="20"
										width="20"></a>
							<s:iterator value="peopleLiked" status="peopleLikedStatus">
							<s:if test="%{youLiked && (#peopleLikedStatus.index==0)}">
								<span id="youLike<s:property value='postId'/>">You and </span>
								</s:if>
								<s:else>
									<s:if test="%{youLiked && (#peopleLikedStatus.index!=0)}">
						
										<a href="<s:url action='profileTempAction_hype' > 
											<s:param name="profileId"><s:property value="userId" /></s:param></s:url>" class="likeCommentFont"> <s:property value="userName" /></a>
									</s:if>
								</s:else>
								</s:iterator>
								 like this.
							</s:elseif>

							<!-- like count>1 and <5 -->
							<s:elseif test="likeCount<5">
								<a href=""><img src="Images/like.jpg" height="20" width="20"></a>
								<s:iterator value="peopleLiked" status="peopleLikedStatus">
									<!-- You have liked -->
									<s:if test="%{youLiked && (#peopleLikedStatus.index==0)}">
										<div id="youLike<s:property value='postId'/>"
											style="display: inline;">You,</div>

									</s:if>
									<s:else>
									
										<!-- You did not like initially when page loaded-->
										<span id="youLike<s:property value='postId'/>"
											style="display: none;" >You,</span>
											<a href="<s:url action='profileTempAction_hype' > 
											<s:param name="profileId"><s:property value="userId" />
											</s:param></s:url>" class="likeCommentFont"> 
											<s:property value="userName"/></a>
									</s:else>
								
									<s:if test="#peopleLikedStatus.last==false">
										<s:if test="%{(#peopleLikedStatus.count+1)==likeCount}"> and </s:if>
										<s:elseif test="%{(#peopleLikedStatus.last==false) && (#peopleLikedStatus.index!=0)}">,</s:elseif>
									</s:if>
								</s:iterator>
								 like this.
							</s:elseif>
							
							<!-- like count greater than 5 -->
							<s:elseif test="likeCount>=5">

								<a href=""
									onclick="window.open('<s:url action='showLikes' > <s:param name="index"><s:property value="postId" /></s:param></s:url>','People Who Like This',100,'height=100,scrollbars=yes');"><img
									src="Images/like.jpg" height="20" width="20"></a>
								<s:iterator value="peopleLiked" status="peopleLikedStatus"
									begin="0" end="3">
									<!-- You liked initially -->
									<s:if test="%{youLiked && (#peopleLikedStatus.index==0)}">
										<div id="youLike<s:property value='postId'/>"
											style="display: inline;">You,</div>
									</s:if>
									<s:else>
										<!-- You did not like initially -->
										<span id="youLike<s:property value='postId'/>"
											style="display: none;">
											You,</span>
										<a href="<s:url action='profileTempAction_hype' > 
											<s:param name="profileId"><s:property value="userId" />
											</s:param></s:url>"
											class="likeCommentFont"><s:property value="userName" /></a>

									</s:else>
									<s:if test="#peopleLikedStatus.last==false && (#peopleLikedStatus.index!=0)">
															,
														</s:if>
								</s:iterator>
													
												and <a href=""
									onClick="window.open('<s:url action='showLikes' > <s:param name="index"><s:property value="postId" /></s:param></s:url>','People Who Like This',100,'height=100,scrollbars=yes');"
									class="likeCommentFont"><span class="hover"><s:property
											value="likeCount-4" /> others</span></a> like this.
							</s:elseif>
						</s:else>
						</s:if>
					
						<s:else>

							<!-- No one liked yet, not even you Ganda post hai!!-->
							<span id="youLike<s:property value='postId'/>"
								style="display: none; "><hr>
								<a href=""><img src="Images/like.jpg" height="20" width="20"></a>
								You like this
							</span>
						</s:else>
						<s:if test="commentCount!=0">
							<s:iterator value="comment"><!-- comment itterate -->
								<hr>

								<table border="0" bordercolor="#f7f7f7">
									<tr>
										<s:iterator value="commentOwner">

											<td rowspan="2"><a
												href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>"><img
													src="image?photoId=<s:property value="photoId" />"
													height="35" width="35"></a></td>
											<td><a
												href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>"
												class="commentNameFont"><span class="hover"><s:property
															value="userName" /></span></a></td>
										</s:iterator>
										<s:if test="myComment">
											<td colspan="1" width="380"><span class="commentFont">
													<s:property value="description" />
											</span></td>
											<td><input type="button" value="Delete"
												onclick="deleteComment(this.form,'<s:property value="commentId" />')" /></td>

										</s:if>
										<s:else>
											<td colspan="2" width="380"><span class="commentFont">
													<s:property value="description" />
											</span></td>

										</s:else>
									</tr>

									<tr height="18">
										<td colspan="3" class="timeFont"><s:property value="date" />
											at <s:property value="time" /> . 
											<s:if test="youLiked">
												<a href=""
													onclick="commentUnlike(this.form,event,'<s:property value="commentId" />')"
													id="cl<s:property value="commentId" />"
													class="likeTextCommentFont"><span class="hover">Unlike											
													
													</span></a></s:if>
													<s:else>
													<a href=""
													onclick="commentLike(this.form,event,'<s:property value="commentId" />')"
													id="cl<s:property value="commentId" />"
													class="likeTextCommentFont"><span class="hover">Like											
													
													</span></a>
													
													</s:else>
											 . <s:if test="%{likeCount!=0}">
												<a href=""
													onclick="window.open('<s:url action='showCommentLikes' > <s:param name="commentIndex"><s:property value="commentId" /></s:param></s:url>','People Who Like This',100,'height=100,scrollbars=yes');"
													class="likeOnCommentFont"><span class="hover"><img
														src="Images/like.jpg" height="17" width="17"> <s:property
															value="likeCount" /></span></a>
											</s:if></td>
									</tr>
								</table>
							</s:iterator><!-- comment itterate close-->
						</s:if>

						<hr>
						<!-- comment text box!!!! -->
						<a
							href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>"><img
							src="image?photoId=<s:property value="photoId1" />" height="35"
							width="35"></a> <input type="text"
							id="desc<s:property value="postId" />"
							placeholder="Write a comment..." class="resizedCommentBox"
							onkeypress="handleKeyPress(event,this.form,'<s:property value="postId"/>')" />
						<input type="hidden" name="postId"
							value="<s:property value="postId"/> " />
					</div>
				</div>
			</div>
		</s:iterator><!-- iterrator of post closed -->
		
		<hr>
		<div class="row">
		<input type="hidden" name="count" value="<s:property value="count" />" />
			<center>
				<a href="<s:url action='olderPost' ><s:param name="count"><s:property value="count" /></s:param> 
					<s:param name="olderPost" value="true" /></s:url>" class="nameFont"><span
					class="hover">See Older Posts</span></a>
			</center>
		</div>
		<hr>

	</form>
</body>
</html>