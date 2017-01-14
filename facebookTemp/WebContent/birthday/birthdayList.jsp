<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>
		function handle(e) {
			if (e.keyCode === 13) {
				$(this).submit();
			}

			return false;
		}
	</script>
	<table>
		<tr>
			<td width="85%"><b><font size=3>Today's Birthdays</font></b></td>
			<td><a href="eventTemp" class="likeCommentFont"><span
					class="hover">See All</span></a></td>
		</tr>
	</table>

	<s:iterator value="birthday">
		<hr>

		<table>

			<tr>
				<td rowspan="2"><img
						src="image?photoId=<s:property value="picId" />" height="60"
						width="60"></td>
				<td><a
					href="<s:url action='profileTempAction_hype' > <s:param name="profileId"> <s:property value="profileId"/> </s:param>
    </s:url>"
					target="dynamic" class="commentNameFont"><font size=2.5><span
							class="hover"> <s:property value="firstName" /> <s:property
									value="lastName" /></span></font></a><span class="timeFont"> . <s:property
							value="age" /> years old
				</span></td>

			</tr>
			<tr>
				<td><s:if test="%{flagB==false }">

						<form action="postWish">
							<input type="hidden" name="profileId"
								value="<s:property value="profileId"/>" /> <input type="hidden"
								name="flagEventWished" value="false" /><!-- to indicate that this is not from event page -->

							<s:if
								test="%{gender=='Male' || gender=='male' || gender=='MALE'}">

								<input type="text" name="wish" style="width: 120%;"
									placeholder="Write a birthday wish on his timeline..."
									onkeypress="handle" />
							</s:if>
							<s:elseif
								test="%{gender=='Female' || gender=='female' || gender=='FEMALE'}">

								<input type="text" name="wish" style="width: 120%;"
									placeholder="Write a birthday wish on her timeline..."
									onkeypress="handle" />
							</s:elseif>

						</form>

					</s:if> <s:else>
						<font size='2'> You wrote on <s:property value="firstName" />'s
							timeline.

						</font>
					</s:else></td>
			</tr>
		</table>

	</s:iterator>

</body>
</html>