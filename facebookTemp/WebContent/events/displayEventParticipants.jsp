<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<s:iterator value="joinStatus">
		<s:property value="profilePicAlbumId" />

		<a
			href="<s:url action='profileTempAction_hype' ><s:param name="profileId"> <s:property value="profileId"/> </s:param>
    </s:url>"
			target="dynamic"> <s:property value="firstName" /> <s:property
				value="lastName" />
		</a>
		<br>
	</s:iterator>


</body>
</html>