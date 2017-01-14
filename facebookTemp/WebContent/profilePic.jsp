<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
.link2
{
position:absolute;
bottom:5%;
left:5px;
color: black;
  background-color: transparent;
  text-decoration: none;
  
}
  .link2:hover {
  color: black ;
  background-color: #def ;
   visibility: visible;
}
</style>
</head>
<body>
		<img  width="180" height="250" src="image?photoId=<s:property value="%{user.profilePicId}"/>" style="border: 5px solid white;"  />
		<s:if test="isHyperlink!=\"true\"">
		<a href="editProfilePic.jsp" target="_blank" class=link2>Change Picture</a>  
		</s:if>
		
</body>
</html>