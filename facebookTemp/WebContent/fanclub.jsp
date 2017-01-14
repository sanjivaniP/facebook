<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function validateNumber() {

	var startDate = document.getElementById("number").value;
	var Numbers = /^[0-9]+$/;
	var length11=startDate.length;
	//alert(length11);
	//alert("please enter the year");
	if (!(startDate.match(Numbers))) {
		alert("please enter the number only");
		return false ;
	} 
	
	return true ;
}
</script>
</head>
<body>
<h3>ENTER THE FAN CLUB NUMBER</h3>
 <s:form action="fanclub" onsubmit="return validateNumber()">
 <INPUT TYPE="text" NAME="fanClubNumber" id="number" required="required"/>
  <br/>
 <input type="submit" value="Save Changes"  />&nbsp;&nbsp;
 <input type="button" onclick="" value="Cancel">
 </s:form>
 
 
 

 
 
</body>
</html>