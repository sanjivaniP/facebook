<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>
<body>
<div >
<h3>Contact Info</h3>
<label for="emailLabel"  style="color:#888;">Email :</label>&nbsp;
<s:if test="contactInfo.userName!=null">  
         <label for="userName"><s:property value="contactInfo.userName"/>@facebook.com</label>            
 </s:if>  

<label for="loginEmail"><s:property value="contactInfo.emailId"/></label><br/>
  <s:if test="contactInfo.phoneNumber!=null">  
 <label for="phoneLabel" style="color:#888;">Phone Number : </label>&nbsp;<label for="phone"><s:property value="contactInfo.phoneNumber"/></label>
   
   <s:if test="isHyperlink!=\"true\"">
   <a href="<s:url action='editPhone1'><s:param name="phoneNumber1"><s:property value="contactInfo.phoneNumber" /></s:param> 
	   </s:url>">edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     <a href="<s:url action='deletePhone'><s:param name="phoneNumber1"><s:property value="contactInfo.phoneNumber" /></s:param> 
	   </s:url>">delete</a>
	   </s:if>
       
 </s:if>  
 <s:else>
 <s:if test="isHyperlink!=\"true\"">
 	 <a href="<s:url action='gender_temp'><s:param name="editParam">phoneNumber</s:param></s:url>">Add Mobile Phone</a>
 	</s:if>
 </s:else>
 <br/>
 
 
 
  <s:if test="contactInfo.userName!=null">  
       <label for="facebookLabel" style="color:#888;">Facebook</label>&nbsp; 
       <a href="profileTempAction">http://facebook.com/<s:property value="contactInfo.userName"/> </a>           
 </s:if>  
 
 <br/>
 
 
 
 
  </div>
</body>
</html>