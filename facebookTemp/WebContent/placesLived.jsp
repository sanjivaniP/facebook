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
<%String flagHomeTown="false"; String flagCurrentCity="false";String hyperLink="false";%>

<s:iterator value="placesList">
	<br/>
	 <s:property value="whereto" />
	
	 <s:if test="flagHomeTown==\"true\"">

				<%flagHomeTown="true"; %>
	</s:if>
	
	<s:if test="flagCurrentCity==\"true\"">

				<%flagCurrentCity="true"; %>
	</s:if>
	
	
	 
	  
    
    
	  <!-- ********************************Shubham Code Start ***************************************-->
	   <a href="<s:url action='editLocation1'><s:param name="location"><s:property value="whereto" /></s:param> 
	   <s:param name="citytype"><s:property value="story" /></s:param></s:url>">edit</a><br/>
	  <!--   <a href="javascript:window.open('editPlace.jsp',EPLACE);">EDIT</a>-->
	  <a href="<s:url action='deleteLocation'><s:param name="location"><s:property value="whereto" /></s:param> 
	   <s:param name="citytype"><s:property value="story" /></s:param></s:url>">delete</a><br/>
	  	<!-- ********************************Shubham Code End ***************************************-->
	  	
	  	
	  	 
	  	
	  	 
	
	 </s:iterator>
<br/>

<%  

	
if(flagHomeTown.equalsIgnoreCase("false"))

{%>
   
 
        <a href="<s:url action='gender_temp'><s:param name="editParam">homeTown</s:param></s:url>">Add Home Town</a>             
 <%} 
 if(flagCurrentCity.equalsIgnoreCase("false")) {%>
 <br/>
   
        <a href="<s:url action='gender_temp'><s:param name="editParam">currentCity</s:param></s:url>">Add Current City</a>             
 <%} %>
 <br/>
<a href="javascript:window.open('newPlace.jsp', 'place', 'width=1000,height=500');">Add Place</a>




</div>

</body>
</html>