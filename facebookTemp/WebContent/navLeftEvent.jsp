<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">
  
<title>Facebook</title>


</head>
<body style= "background-color:#f7f7f7;">	
<div class="well sidebar-nav sidebar-offcanvas" >   

<ul class="nav nav-tabs">
<li>
<table border="0">
<tr>
<td rowspan="2">
<a href="<s:url action='profileTempAction'/>"><img src="image?photoId=<s:property value="%{user.profilePicId}"/>" alt="profile_pic" height="40" width="40"/></a></td>
<td><a href="<s:url action='profileTempAction'/>">&nbsp;&nbsp;<font color="black" face="Lucida Grande" size="2" ><b> <s:property value="%{user.firstName}"/> <s:property value="%{user.lastName}"/></b></font></a></td>
 </tr>
 <tr>
 <td>
 <a><font color="black" face="Lucida Grande" size="2" >&nbsp;&nbsp;&nbsp;Edit Profile</font></a>
 <td>
 </tr>
 </table>

 
				
				<li ><a href="<s:url action='home1'/>"><img src="Images/newsFeed.png"/><font color="#000000" size='3'> News Feed</font></a></li>
				<li><a href="<s:url action='eventTemp'/>"><img src="Images/event.png"/><font color="#000000" size='3'><b>My Events</b></font></a></li>
				<li><a href="<s:url action='eventCreateTemp'/>"><img src="Images/createEvent.png"/><font color="#000000" size='3'><b>Create Events</b></font></a></li>
				
	
			</ul>
			

		
</div>
 </body>
</html>