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
<script src="js/ownNews.js"></script>
<title>Insert title here</title>


<style type="text/css"> #panel,#flip { padding:5px; text-align:center; background-color:white; border:solid 1px #c3c3c3; } #panel { padding:50px; display:none; } </style>
</head>
<body>
	<s:property value="profileId" />
	<form id="myForm" action="profileTempAction">
		<s:iterator value="posts">
			<div class="panel panel-default"  id="main<s:property value="postId" />">
				<div class="panel-heading">
					<table border="0" cellspacing="0" bgcolor="#ffffff" width="560">
						<tr>
							<s:iterator value="postOwner">
								<td rowspan="2" width="80"><a href=""><img
										src="image?photoId=<s:property value="photoId" />" height="60"
										width="60"></a></td>
								<td colspan="2">
										<s:if test="type==\"text\"">  
															<a href="profileTempAction_hype?profileId=<s:property value="userId" />" class="nameFont"><span
															class="hover">
 																<s:property value="userName" />
 																</span></a>
                    										</s:if>
                    										<s:if test="type==\"postOnWall\"">  
 																	<a href="profileTempAction" class="nameFont"><span
															class="hover">
 																<s:property value="userName" />
 																</span></a>
 																posted on Your wall
                    										</s:if>
										
								</td>
							</s:iterator>
						</tr>
						<tr>
							<td class="timeFont"><s:property value="postDate" /> at <s:property
									value="postTime" />.</td>
						</tr>
						<tr>
							<td colspan="3"><s:property value="userStatus" escape="false"/></td>
						</tr>
					</table>
				</div>
				<div class="panel-body">
					<div class="row" style="height:auto">
						<s:if test="youLiked"><a href=""
							onclick="unlike(this.form,event,'<s:property value="postId" />')"
							id="l<s:property value="postId" />" class="likeCommentFont">Unlike</a></s:if><s:else> <a href=""
							onclick="like(this.form,event,'<s:property value="postId" />')"
							id="l<s:property value="postId" />">Like</a></s:else> . <a
							href="#" class="likeCommentFont" onclick="focusOnCommentBox('desc<s:property value="postId" />')">Comment</a>
						<s:if test="likeCount!=0">
							<hr>

							<a href=""><img src="Images/like.jpg" height="20" width="20"></a>
							<s:if test="likeCount<5">
								<s:iterator value="peopleLiked" status="peopleLikedStatus">
									<s:if test="%{youLiked && (#peopleLikedStatus.index==0)}">
										<s:property />
									</s:if>
									<s:else>
										<a href="" class="likeCommentFont"> <s:property /></a>
									</s:else>
									<s:if test="#peopleLikedStatus.last==false">
										<s:if test="%{(#peopleLikedStatus.count+1)==likeCount}"> and </s:if>
										<s:else>,</s:else>
									</s:if>
								</s:iterator>
							</s:if>
							<s:else>
								<s:iterator value="peopleLiked" status="peopleLikedStatus"
									begin="0" end="3">
									<s:if test="%{youLiked && (#peopleLikedStatus.index==0)}">
										<s:property />
									</s:if>
									<s:else>
										<a
											onclick="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>"
											class="likeCommentFont"><s:property /></a>
									</s:else>
									<s:if test="#peopleLikedStatus.last==false">
															,
														</s:if>
								</s:iterator>
													
												and <a href=""
									onClick="window.open('<s:url action='showLikes' > <s:param name="index"><s:property value="postId" /></s:param></s:url>','People Who Like This',100,'height=100,scrollbars=yes');"
									class="likeCommentFont"><span class="hover"><s:property
											value="likeCount-4" /> others</span></a>
							</s:else> like this.
							
						</s:if>
						<s:if test="commentCount!=0">
							<s:iterator value="comment">
								<hr>

								<table border="0" bordercolor="#f7f7f7">
									<tr>
										<s:iterator value="commentOwner">

											<td rowspan="2"><a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>"><img
													src="image?photoId=<s:property value="photoId" />"
													height="35" width="35"></a></td>
											<td><a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>" class="commentNameFont"><span
													class="hover"><s:property value="userName" /></span></a></td>
										</s:iterator>

										<td colspan="2" width="380"><span class="commentFont">
												<s:property value="description" />
										</span></td>
									</tr>

									<tr height="18">
										<td colspan="3" class="timeFont"><s:property value="date" />
											at <s:property value="time" /> . <s:if
														test="youLiked">
														<a href=""
											onclick="commentUnlike(this.form,event,'<s:property value="commentId" />')"
											id="cl<s:property value="commentId" />" 
											class="likeTextCommentFont"><span class="hover" ">Unlike</span></a></s:if>
														<s:else><a href=""
											onclick="commentLike(this.form,event,'<s:property value="commentId" />')"
											id="cl<s:property value="commentId" />"
											class="likeTextCommentFont"><span class="hover">Like</span></a></s:else>
											. <s:if test="%{likeCount!=0}">
												<a href=""
													onclick="window.open('<s:url action='showCommentLikes' > <s:param name="commentIndex"><s:property value="commentId" /></s:param></s:url>','People Who Like This',100,'height=100,scrollbars=yes');"
													class="likeOnCommentFont"><span class="hover"><img
														src="Images/like.jpg" height="17" width="17"> <s:property
															value="likeCount" /></span></a>
											</s:if></td>
									</tr>
								</table>
							</s:iterator>
						</s:if>

						<hr>
						<a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="userId" /></s:param></s:url>"><img
							src="image?photoId=<s:property value="photoId" />" height="35"
							width="35"></a> <input type="text"
							id="desc<s:property value="postId" />"
							placeholder="Write a comment..." class="resizedCommentBox"
							onkeypress="handleKeyPress(event,this.form,'<s:property value="postId"/>','<s:property value="userId"/>')" />
						<input type="hidden" name="postId"
							value="<s:property value="postId"/> " />
					</div>
				</div>
			</div>
		</s:iterator>
		<s:hidden value="count" />
		<hr>
		<div class="row">
					
		<center><a href="<s:url action='olderPost' > </s:url>" class="nameFont"><span
										class="hover">See Older Posts</span></a></center>
		</div>
		<hr>
	</form>
</body>
</html>