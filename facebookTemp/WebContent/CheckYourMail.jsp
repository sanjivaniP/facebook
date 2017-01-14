<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div.checkMail{

left:320px;
position:relative;
width:650px;
 height:350px;
 border-style:solid;
border-color:#dfe3ee;
top:120px;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Images/favicon.ico">
<title>Facebook</title>
</head>
<body>
<div class="checkMail"><h3 style="position:relative;left:30px"> Check Your Email</h3>
<br>
<font style ="position:relative; top:-7px; left:30px;"  size="2"> 
Check your email - we sent you an email with a six-digit confirmation code. Enter it below to continue to reset your password.
</font>
<form action="requestForResetPassword"  method="post">
<s:hidden name="loginId" value="%{loginId}" />
<s:hidden name="emailId" value="%{emailId}" />
<input type="text" name="enteredCode"  size="28" placeholder="######" style="position:relative; height:40px;left:30px;top:15px;">
   <br><br>
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   <input type="image" src="Images/search.png" alt="Continue" style="position:relative; left:480px;top:20px;">
     <a href="Login.jsp"><img  src="Images/cancel.png" alt="Cancel" style="position:relative; left:474px;top:19px"></a><br>
  
  </form>
  </div>   
  
</body>
</html>