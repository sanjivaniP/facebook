
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.alert-box {
    color:#555;
    font-family:Tahoma,Geneva,Arial,sans-serif;font-size:18px;
    background:#ffecec url('images/error.png') no-repeat 10px 50%;
    position:relative;
}
div.enterEmailBox{

left:320px;
position:relative;
width:650px;
 height:350px;
 border-style:solid;
border-color:#dfe3ee;
top:120px;
}
img
{

}

</style>
<head>
<script  type="text/javascript">
function validateEmail()
{
	if( document.forgotPasswordForm.userIdentity.value == "" )
	{
	  alert( "Please provide your email id, phone number or username!" );
	  document.forgotPasswordForm.userIdentity.focus() ;
	  return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Images/favicon.ico">
<title>Facebook</title>
</head><!-- #f7f7f7 #3b5998-->
<body bgcolor="#f7f7f7">
<div class = "enterEmailBox">

<h3 style="position:relative;left:30px">Find Your Account</h3>
<label style="position:relative;left:210px; top:20px"> Email, Phone or Username</label>
<br><br>

  <form action="forgotPassword"  method="post" name="forgotPasswordForm" onsubmit="return validateEmail()">
  <s:hidden name="emailId" value="%{emailId}" />
  <img src="Images/mail_icon.jpg" width="50" height="25" style = "position:relative; top:20px; left:140px">
<input type="text" name="userIdentity"  size="28" style="position:relative;height:23px; left:150px; top:13px">
   <br><br>
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   <input type="image" src="Images/search.png" alt="Continue" style = "position:relative; left:470px" >
     <a href="Login.jsp"><img  src="Images/cancel.png" alt="Cancel" style="position:relative; left:470px"></a><br>
  <s:if test="hasActionErrors()"> <div class="alert-box"> <s:actionerror/> </div> </s:if>
  </form>
    <!--  <input type="image" src="Images/cancel.png" alt="cancel" style="left:600px"><br> -->
  
</div>
</body>
</html>