<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Add Gender</h3>
 <form action="saveGender" id="genderForm">
 <select  name="genderOptions" id="genderOptions" >
 	<option>Male</option>
 	<option>Female</option>
 </select>
 <br/>
 <input type="submit" value="Save Changes" id="savegender"/>&nbsp;&nbsp;
 <input type="button" onclick="" value="Cancel" id="cancelgender" onclick="cancelGenderForm()"/>
 </form>

 
 
</body>
</html>