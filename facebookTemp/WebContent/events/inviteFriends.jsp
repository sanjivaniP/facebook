<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<script type="text/javascript">
	function checkInput() {

		var cboxes = document.getElementsByName('friendId');
		var flag = false;
		for ( var i = 0; i < cboxes.length; i++) {
			if (cboxes[i].checked) {
				flag = true;
				document.myForm.action = "friendsInviteSelect";
				document.getElementById("myForm").submit();
			}
		}
		if (!flag) {
			alert("You have not made any selection, can't proceed further");
		}
	}
</script>


<title>Insert title here</title>
</head>
<body>

	Invite Friends
	<s:form id="myForm">

		<table>
			<s:iterator value="ifa">
				<s:if test="%{flagF  == false}">

					<tr>
						<td><input type="checkbox" name="friendId" id="friendsList"
							value="<s:property value="profileId"/>" /></td>

						<td ><img
								src="image?photoId=<s:property value="profilePicId" />"
								height="40" width="40"></td>
						<td><a href="<s:url action='profileTempAction_hype' >  
    <s:param name="profileId"> <s:property value="profileId"/> </s:param>
    </s:url>"class="nameFont"><span class="hover"><s:property
										value="firstName" /> <s:property value="lastName" /></span></a></td>
					</tr>
				</s:if>
				<s:else>
					<tr>

						<td><input type="checkbox" name="friendId" id="friendsList"
							disabled="disabled" /></td>
						<td ><img
								src="image?photoId=<s:property value="profilePicId" />"
								height="40" width="40"></td>
						<td><a href="<s:url action='profileTempAction_hype' >  
    <s:param name="profileId"> <s:property value="profileId"/> </s:param>
    </s:url>" class="nameFont"><span class="hover"><s:property
										value="firstName" /> <s:property value="lastName" /></span></a></td>
					</tr>
				</s:else>
				<%-- <input type="hidden" name="friendId"
				value="<s:property value="profileId"/>" /> --%>
				</tr>

			</s:iterator>
		</table>
		<input type="hidden" name="profileId"
			value="<s:property value="profileId"/>" />
		<input type="hidden" name="eventId"
			value="<s:property value="eventId" />" />
		<input type="hidden" name="name" value="<s:property value="name" />" />
		<input type="hidden" name="details"
			value="<s:property value="details" />" />
		<input type="hidden" name="where" value="<s:property value="where" />" />
		<input type="hidden" name="datepicker"
			value="<s:property value="datepicker" />" />
		<input type="hidden" name="when" value="<s:property value="when" />" />
		<input type="hidden" name="flag" value="<s:property value="flag" />" />
		<input type="hidden" name="host" value="<s:property value="host" />" />
		<input type="hidden" name="eventPicId"
			value="<s:property value="eventPicId" />" />
		<input type="hidden" name="time" value="<s:property value="time" />" />
		<input type="hidden" name="dateEvent"
			value="<s:property value="dateEvent" />" />
		<input type="hidden" name="dateEvent1"
			value="<s:property value="dateEvent1" />" />
		<input type="hidden" name="going" value="<s:property value="going" />" />
		<input type="hidden" name="maybe" value="<s:property value="maybe" />" />
		<input type="hidden" name="invited"
			value="<s:property value="invited" />" />
		<input type="hidden" name="rsvpStatusId"
			value="<s:property value="rsvpStatusId" />" />
		<br>
		<br>
		<input name="btn" type="button" id="btn" value="invite"
			onclick="checkInput();" />

	</s:form>

</body>
</html>