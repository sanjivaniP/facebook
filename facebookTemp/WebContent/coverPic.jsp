<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script src="js/jquery-1.10.2.js"></script>
 <script src="js/coverPic.js"></script>

<style>
.link1
{
position:absolute;
bottom:5%;
right:100px;
color: black;
  background-color: transparent;
  text-decoration: none;
  
}
  .link1:hover {
  color: black ;
  background-color: #def ;
   visibility: visible;
}

.linkName
{
position:absolute;
bottom:5%;
right:400px;
color:navi blue;
  background-color:white;
  text-decoration: none;
  
}
 
 /* 
 a:link span, a:visited span {
    visibility: hidden;
    position:absolute;
top:20%;
right:100px;
color: blue;
}
a:link:hover span, a:visited:hover span {
    visibility: visible;
    position:absolute;
top:20%;
right:100px;
color: blue;
} */

/* 
a {
  zoom: 1; filter: alpha(opacity = 0); 
  opacity: 0.0;
}
a:hover {
  zoom: 1; filter: alpha(opacity = 100);
  opacity: 1.0;
}
 */ 
.dibba1
{


z-index: 1000;
background-color: white;

}
 </style>
</head>
<body>


											
<img width="100%" height="300px" src="image?photoId=<s:property value="%{pic.coverPicId}" />"/>

<s:if test="isHyperlink!=\"true\"">
<label class="linkName"><b><s:property value="pic.firstName" /> <s:property value="pic.lastName" /></b></label>

 <a href="editCoverPic.jsp" target="_blank" class=link1>CHANGE COVER PIC</a> 
 </s:if> 
   <s:if test="isHyperlink==\"true\"">
   
   <label class="linkName"><b><s:property value="pic.firstName" /> <s:property value="pic.lastName" /></b></label>
   <form action="sendFriendRequest" class="coverPic" style="max-width: 100px;position: relative;left: 900px">
<input type="hidden" name="userId1"	value="<s:property value="profileid" />" /> 
<input type="hidden" name="userId2" value="<s:property value="profileId" />" />
 		<s:if test="areFriends==\"addFriend\"">
 		<button name="submit" class="btn btn-default active dibba1" type="submit"  value="Add Friends" ><span class="glyphicon glyphicon-plus"></span>Add Friends</button>
 		</s:if>
 		
 		
 		<s:if test="areFriends==\"request\"">
 		<button name="submit" class="btn btn-default active dibba1" disabled="disabled" type="submit" value="Friend Request Sent" ><span class="glyphicon glyphicon-user" ></span> Friend Request Sent</button>
 		</s:if>
 		
 		
 		<s:if test="areFriends==\"response\"">
 		<button class="btn btn-default active dropdown-toggle dibba1" data-toggle="dropdown" name="submit"><span class="glyphicon glyphicon-user" ></span>Respond to friend request</button>
        							 <ul class="dropdown-menu dropdown-inverse response">
										<li><a onclick="confirmRequest($(this).closest('form'),event)" name="submit" >Confirm</a></li>
										<li><a onclick="declineRequest($(this).closest('form'),event)">Decline</a></li>	
										
									</ul>
									
 		</s:if>
 		
 		
 		<s:if test="areFriends==\"friends\"">
 		<button name="submit" class="btn btn-default active dibba1" type="submit" value="Friends"><span class="glyphicon glyphicon-ok"></span>Friends</button>
       					  			
 		</s:if>
 		</form>													 
</s:if>

<!-- <a href="#" onclick="window.open('http://localhost:8080/facebookTemp/editCoverPic.jsp', 'tp', 'height=500,width=500')" target="_blank" class=link1>CHANGE COVER PIC</a>
 -->
</body>
</html>