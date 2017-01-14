<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<script type="text/javascript">

function ReadCookie()
{
   var allcookies = document.cookie;
   
	if(allcookies==""){
		window.location.href ='Login.jsp';
	}
	else{
		  // Get all the cookies pairs in an array
		  cookiearray  = allcookies.split(';');

		  // Now take key value pair out of this array
		  for(var i=0; i<cookiearray.length; i++){
		     name = cookiearray[i].split('=')[0];
		     value = cookiearray[i].split('=')[1];
		    if(value!="")
		      location.href = "directLogin.action?emailOrPhone="+value;
		    else
		    	window.location.href ='Login.jsp';
		    
		  }
	}
	
	
}

</script>
</head>
<body>
<form id="checkCookieForm">
<script>
 var isCookie=ReadCookie();
 </script>
 <!-- <input type="hidden" name="emailOrPhone" value="return ReadCookie();"/> -->
</form>

</body>
</html>