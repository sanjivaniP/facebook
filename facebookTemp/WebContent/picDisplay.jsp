<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/facebook.css">
</head>


<body>
	<div class="panel panel-default" align="center">
<h3>
						<b>&nbsp;&nbsp;&nbsp;Photos</b>
					</h3>
		<table >
		<% int count=0; %>
		
			<s:iterator value="photoIdList">	<!-- photos which r not the part of any album or profile pic or cover pic -->
			<% if(count%5==0) { %>
				<tr>
				<%} %>
					<td><img src="image?photoId=<s:property/>" height="120"
						width="120"></td>
									<% count++;%>
							<% if(count%5==0) { %>
						
				</tr>
								<%} %>
				

			</s:iterator>
		</table>
		<hr>	
		<h3>
						<b>&nbsp;&nbsp;&nbsp;Photo Albums</b>
					</h3>
<hr>	
			<s:iterator value="photoAlbum">
Description: <s:property value="description" /><br/>
Location: <s:property value="location"/><br/>
Created On:<s:property value="createdOn"/><br/>
		<table >
		<% int count1=0; %>
		
			<s:iterator value="photoIdList">
			<% if(count1%5==0) { %>
				<tr>
				<%} %>
					<td><img src="image?photoId=<s:property/>" height="120"
						width="120"></td>
									<% count1++;%>
							<% if(count1%5==0) { %>
						
				</tr>
								<%} %>
				

			</s:iterator>
		</table>
			<hr>
	<br/>
		</s:iterator>
	</div>

</body>
</html>
