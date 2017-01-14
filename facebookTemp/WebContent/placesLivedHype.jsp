<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function setHomeTown(){
	
	var flagHomeTown="true";
}
</script>
 
</head>
<body onload="place.close()">
<div >
<h3>Places Lived</h3>


<s:iterator value="placesList">
	<br/>
	 <s:property value="whereto" />
	
	 	
	 </s:iterator>
<br/>



</div>

</body>
</html>