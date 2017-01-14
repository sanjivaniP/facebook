<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/facebook.css">

<style>
a:hover {
	background-color: #E3E4FA;
}

a {
	text-decoration: none;
}
</style>


</head>
<body>

	<div class="panel panel-default" style="width: 240px; height: 270px">
<br>
		<s:if test="flag">
			<a href="#"
			onClick="window.open('<s:url action='birthdayList' ></s:url>','Birthdays',100,'height=50,scrollbars=yes');"
				style="color: #00008B"; >&nbsp;&nbsp;<img src="Images/birthdayIcon.jpg" />
				&nbsp;<s:property value="displayBirthdayList" /></a>
		</s:if>
		<s:else>
		</s:else>
	<hr>
	</div>

</body>
</html>