<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div.confirmIdentityBox{

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

function checkRadioButton()
{
var x = document.getElementById("emailForReset").checked;

if(x){
return true;
}
else 
	{alert( "You did not select the option, please select the option " );
document.emailForm.emailForReset.focus();
return false;
	}
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Images/favicon.ico">
<title>Facebook</title>
</head>
<body>

<div class = "confirmIdentityBox">
<h3 style="position:relative;left:40px;top:9px;">Reset Your Password</h3>
 <form action= "sendEmail" name="emailForm" onsubmit="return checkRadioButton()">
 <br>
 <img  style="position: relative;left: 550px;top: 70px;" width="50" height="50" src="image?photoId=<s:property value="%{profilePicId}"/>"  /> <b><font size='4'> <s:property value="%{user.firstName}"/></font></b>
                 
                 <br>
                 <br>
                
                 <s:hidden name="loginId" value="%{loginId}" />
                 <s:hidden name="emailId" value="%{emailId}" />
                  <input type="radio" name="emailForReset" id="emailForReset" value="emailForReset" style="position:relative; left:180px; top:20px;">
<img src="Images/mail_icon.jpg" width="25" height="13" style = "position:relative; top:20px; left:180px">

 <label for="Email me a link to reset my password"><font style ="position:relative; color:#3b5998; top:20px; left:180px;"  size="2"> Email me a link to reset my password </font></label>
                <br>
<input type="image" src="Images/search.png" alt="Continue" style="position:relative; left:470px; top:90px;" >
  <a href="Login.jsp"><img  src="Images/cancel.png" alt="Cancel" style="position:relative; left:470px; top:89px;"></a><br>
</form>
</div>
</body>
</html>