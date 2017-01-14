<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.scroll {
	width: 100%;
	height: 100%;
	overflow: auto;
}

.heading {
	width: 100%;
	height: 10%;
}

.latestConverstions {
	width: 100%;
	height: 90%;
}

html,body,.container {
	height: 100%;
	min-height: 100%;
}
</style>
</head>
<body>
	<div class="panel panel-default" style="width: 240px; height: 100%">
<br/>
		<div class="heading"></div>
		<div class="heading"></div>


		<div class="scroll">
		<div class="latestConversations">
				<s:iterator value="recentConversationWithAll">

			<table>
					<tr>
						<td rowspan="2"><a href=""><img
								src="image?photoId=<s:property value="photoId" />" height="40"
								width="40"></a></td>
						<td><a
							href="<s:url action='showConversationWithSelectedUser' >
						<s:param name="selectedUser"><s:property value="user2Id"/>
						</s:param></s:url>" class="nameFont"><span
									class="hover"><s:property
									value="user2Name" /></span></a></td>
					</tr>

					<tr>
						<td colspan='2'><s:property value="lastMessageText" /></td>
					</tr>


			</table>
								<hr>
												</s:iterator>
			</div>
		</div>
	</div>
</body>
</html>