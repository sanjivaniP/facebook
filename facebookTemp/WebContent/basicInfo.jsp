<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function loadAddGender()
{
	alert('loadAddGender');
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("genderDiv").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","addGender.jsp",true);
xmlhttp.send();
}
</script>



</head>
<body onload="onLoadHide()">
<div  id="genderDiv">
<h3>Basic Info</h3>
<label for="bwholeDateLabel" style="color:#888;">Birth  whole Date :</label>&nbsp;<s:property value="basicInfo.birthDate"/>
<!-- <label for="bDateLabel" style="color:#888;">Birth Date :</label>&nbsp;<s:property value="basicInfo.bDate"/><br/> -->
<!-- <label for="bYearLabel" style="color:#888;">Birth Year :</label>&nbsp;<label for="bYear"><s:property value="basicInfo.bYear"/></label> -->
 <s:if test="isHyperlink!=\"true\"">
 <a href="<s:url action='editDOB1'><s:param name="birthDate1"><s:property value="basicInfo.birthDate" /></s:param> 
	   </s:url>">edit</a>
	    <%--  <a href="<s:url action='deleteDOB'><s:param name="birthDate1"><s:property value="basicInfo.birthDate" /></s:param> 
	   </s:url>">delete</a><br/>
	  --%>  </s:if>
 
 <br/>

 <s:if test="basicInfo.gender!=null">  
 <label for="genderLabel" style="color:#888;">Gender : </label>&nbsp;<label for="gender"><s:property value="basicInfo.gender"/></label>
 	<s:if test="isHyperlink!=\"true\"">
 	 <a href="<s:url action='gender_temp'><s:param name="editParam">gender</s:param></s:url>">edit</a>
 	</s:if>
  <%-- <a href="<s:url action='gender_temp'><s:param name="gender1"><s:property value="basicInfo.gender" /></s:param> 
	  </s:url>">edit</a><br/>
	 --%>                    
 </s:if>  
 <s:else>
 	<s:if test="isHyperlink!=\"true\"">
 	 <a href="#" onclick="loadAddGender();">Add Gender</a>
 	</s:if>
 </s:else>
 <br/>
 
 
 
  <s:if test="basicInfo.interests==null">  
  <s:if test="isHyperlink!=\"true\"">
              <a href="<s:url action='gender_temp'><s:param name="editParam">interests</s:param></s:url>">Add Interested In</a>
              </s:if>       
 </s:if>  
 <s:else>
 	<label for="interestsLabel" id="interestsLabel" style="color:#888;">Interested in</label>&nbsp;<label for="interests"><s:property value="basicInfo.interests"/></label>
 	<s:if test="isHyperlink!=\"true\"">
 <a href="<s:url action='editIntrestedIn1'><s:param name="interest1"><s:property value="basicInfo.interests" /></s:param> 
	   </s:url>">edit</a>
	   
	   </s:if>
	
 
 </s:else>
 <br/>
 
 
 
  <s:if test="basicInfo.relationship==null">  
              <label for="relationship1Label" style="color:#888;">Relationship Not Updated</label>       
 </s:if>  
 <s:else>
 	<label for="relationshipLabel" style="color:#888;">Relationship</label>&nbsp;<label for="relationship"><s:property value="basicInfo.relationship"/></label>
 <s:if test="isHyperlink!=\"true\"">
  <a href="<s:url action='relStatusList'/>">edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
   <a href="<s:url action='deleteReligion'> </s:url>">delete</a>
  
 </s:if>
 </s:else>
 <br/>
 
 
 
  <s:if test="basicInfo.religion==null">  
  <s:if test="isHyperlink!=\"true\"">
              <a href="<s:url action='gender_temp'><s:param name="editParam">religion</s:param></s:url>">Add Religion</a>       
 </s:if>
 </s:if>  
 <s:else>
 	<label for="religionLabel" style="color:#888;">Religion</label>&nbsp;<label for="religion"><s:property value="basicInfo.religion"/></label>
  <s:if test="isHyperlink!=\"true\"">
  <a href="<s:url action='editReligion1'><s:param name="religion1"><s:property value="basicInfo.religion" /></s:param> 
	   </s:url>">edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   <a href="<s:url action='deleteReligion'><s:param name="religion1"><s:property value="basicInfo.religion" /></s:param> 
	   </s:url>">delete</a><br/>
	   </s:if>
	  
 </s:else>
 <br/>
 
 
  </div>
</body>
</html>