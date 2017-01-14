<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div.resetPassword{

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
<script type="text/javascript">
function validate(){
	if( document.resetPasswordForm.newPassword.value == "" )
	{
	  alert( "Please provide new password!" );
	  document.resetPasswordForm.newPassword.focus() ;
	  return false;
	}
	if( document.resetPasswordForm.reEnteredPassword.value == "" )
	{
	  alert( "Please re-enter the password!" );
	  document.resetPasswordForm.reEnteredPassword.focus() ;
	  return false;
	}
}
function matchPassword(){
	 var password = document.getElementById("newPassword").value;
	    var reEnteredpassword = document.getElementById("reEnteredPassword").value;
	    
	   if (password != reEnteredpassword) {
           document.getElementById("passwordMatchError").innerHTML = "password does not match";
           return false;
       }
       else {
           document.getElementById("passwordMatchError").innerHTML = "password matches";
           return true;
       }
	    
}
var pass_strength;

function IsEnoughLength(str,length)
{
	if ((str == null) || isNaN(length))
		return false;
	else if (str.length < length)
		return false;
	return true;
	
}

function HasMixedCase(passwd)
{
	if(passwd.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))
		return true;
	else
		return false;
}

function HasNumeral(passwd)
{
	if(passwd.match(/[0-9]/))
		return true;
	else
		return false;
}

function HasSpecialChars(passwd)
{
	if(passwd.match(/.[!,@,#,$,%,^,&,*,?,_,~]/))
		return true;
	else
		return false;
}


function CheckPasswordStrength(pwd)
{
	if (IsEnoughLength(pwd,8) && HasMixedCase(pwd) && HasNumeral(pwd) && HasSpecialChars(pwd))
		pass_strength = "<b><font style='color:olive'>Very strong</font></b>";
	else if (IsEnoughLength(pwd,6) && HasMixedCase(pwd) && (HasNumeral(pwd) || HasSpecialChars(pwd)))
		pass_strength = "<b><font style='color:Blue'>Strong</font></b>";
	else if (IsEnoughLength(pwd,6) && HasNumeral(pwd))
		pass_strength = "<b><font style='color:Green'>Good</font></b>";
	else
		pass_strength = "<b><font style='color:red'>Weak</font></b>";

	document.getElementById('pwd_strength').innerHTML = pass_strength;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Images/favicon.ico">
<title>Facebook</title>
</head>
<body>
<div class = "resetPassword">
<h3 style="position:relative;left:30px"> Choose a new password</h3>
<br>
<font style ="position:relative; top:-7px; left:30px;"  size="2">
A strong password is a combination of letters and punctuation marks </font>
<form action="resetPassword"  method="post" name = "resetPasswordForm" onsubmit="return validate()">
<label style="position:relative;left:155px; top:60px"> New password</label>
<input type="password" name="newPassword" id = "newPassword" onkeyup='CheckPasswordStrength(this.value);' size="20" style="position:relative; height:20px; left:160px; top:60px;"><br><br>
<div id='pwd_strength' style = "position:absolute; left:365px; top:240px"></div>

<label style="position:relative;left:127px; top:55px"> Confirm password</label>
<input type="password" name="reEnteredPassword" id = "reEnteredPassword" onkeyup='matchPassword();' size="20" style="position:relative; height:20px; left:133px; top:55px;">
<br>
<span style = "position:relative; left:133px; top:75px " id="passwordMatchError"></span>
<s:hidden name="loginId" value="%{loginId}" />
<s:hidden name="emailId" value="%{emailId}" />
   <br><br>
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
   &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;
      <input type="image" src="Images/search.png" alt="Continue" style = "position:relative; left:80px; top:95px;">
    <a href="Login.jsp"><img  src="Images/cancel.png" alt="Cancel" style = "position:relative; left:80px; top:95px;"></a><br>
  </form>
    
</div>
    </body>
</html>