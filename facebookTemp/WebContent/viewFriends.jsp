<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.leftOne{
float: left;
    width:50%;
}

.rightOne{
float: right;
    width: 50%;
}
</style>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<%-- 
EXAM CODE 1
<li>NUMBER OF FRIENDS <s:property value="numberoffriends"/></li>
<li>YOU HAVE  <s:property value="fanclub"/> Fan Club</li>
 --%>
<% int count=0; %>
<s:iterator value="frindList">

<%count++; 

if((count%2)!=0){%>
<div class="row">
<div class="col-sm-5">
<div class="panel panel-default" style="height:150px">
<a href="profileTempAction_hype" ><img width="150" height="150" src="image?photoId=<s:property value="profilePicId" />" /></a>
 &nbsp;&nbsp; &nbsp;
 <a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"> 
<b><s:property	value="firstName" /> &nbsp;<s:property value="lastName" /></b></a>
<%-- 
EXAM CODE 2
<b><s:property	value="birthDate" /> &nbsp;<s:property value="ageMT2013148" /></b>
 --%>
</div>
</div>

<%}
else{%>
<div class="col-sm-5" >
<div class="panel panel-default" style="height:150px">
<a href="profileTempAction_hype" ><img width="150" height="150" src="image?photoId=<s:property value="profilePicId" />" /></a>
 &nbsp;&nbsp; &nbsp;
 <a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"> 
<b><s:property	value="firstName" /> &nbsp;<s:property value="lastName" /></b></a>

<%-- 
EXAM CODE 3
<b><s:property	value="birthDate" /> &nbsp;<s:property value="ageMT2013148" /></b>
 --%>
</div>
</div>
</div>

<%} %>

</s:iterator>



</body>
</html>