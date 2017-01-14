<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">
<style>
.boxed {
	border: 3px solid white;
	width: 60px;
}

table {
	border: 2px solid black;
}
</style>

<script type="text/javascript">
function eraseChecked() {
	
document.myForm.action = "joinStatusChange";
document.getElementById("myForm").submit();
}

function inviteFriend()
{

	document.myForm.action = "inviteFriends";
	document.getElementById("myForm").submit();
	
	}
</script>

</head>
<body>

<s:form id="myForm">
<div class="panel panel-default">

		<TABLE WIDTH="1000" HEIGHT="200">
			<TR>

				<TD WIDTH="1000" HEIGHT="200" BACKGROUND="Images/event-default.png"
					VALIGN="bottom"><FONT SIZE="+1" COLOR="black"> <b
						class="boxed"><s:property value="dateEvent" /> </b> <s:property
							value="name" />

				</FONT></TD>
			</TR>
		</TABLE>
						<div class="panel-body">
					<div class="row">
	<TABLE WIDTH="1000" HEIGHT="50">


		<TR>
			<TD WIDTH="600">Public Hosted by <s:property value="host" /></TD>
			<TD><s:select name="status" list="statusList" onchange="eraseChecked();" /></TD>
			<TD>
			<a href="javascript:inviteFriend();" >Invite Friends</a>
			
			
		
		</TR>
	</TABLE>
	</div>
	</div>
	<TABLE WIDTH="1000" HEIGHT="50">
		<TR>

			<TD><TABLE WIDTH="500" HEIGHT="50">
					<tr>
						<td><s:property value="dateEvent1" /> at <s:property
								value="time" /></td>

					</tr>
					<tr>
						<td><hr></td>
					</tr>
					<tr>
						<td><s:property value="where" /></td>
					</tr>
				</TABLE></TD>
			<TD><TABLE>
					<tr>


						<td><s:property value="going" /> <br> <a
							href="   <s:url action="calculateStatusEvent">
		  <s:param name="eventId"><s:property value="eventId"/>  </s:param>
		  <s:param name="statusEvent" value="1"/>
		    </s:url> "
							target="dynamic"> Going </a></td>

						<td><s:property value="maybe" /> <br> <a
							href="   <s:url action="calculateStatusEvent">
		  <s:param name="eventId"><s:property value="eventId"/>  </s:param>
		  <s:param name="statusEvent" value="2"/>
		    </s:url> "
							target="dynamic"> Maybe </a></td>

						<td><s:property value="invited" /><br>
						 <a href="<s:url action='calculateStatusEvent' >
							 <s:param name="eventId"><s:property value="eventId" /> </s:param>
		  <s:param name="statusEvent"><s:property value="4" /></s:param></s:url>"
							onclick="window.open('<s:url action='calculateStatusEvent' >
							 <s:param name="eventId"><s:property value="eventId" /> </s:param>
		  <s:param name="statusEvent"><s:property value="4" /></s:param></s:url>'
		  ,'Invited People',100,'height=100,scrollbars=yes');" class="likeCommentFont"><span class="hover">
							 Invited</span></a></td>
					</tr>
				</TABLE></TD>


		</TR>

	</TABLE>
	<table WIDTH="1000" HEIGHT="200">
		<tr>
			<td><s:property value="details" /></td>
		</tr>
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
	</div>
	</s:form>
	
</body>
</html>