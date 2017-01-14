<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">
<script src="js/jquery-1.10.2.js"></script>
<title>Insert title here</title>
<script src="js/header.js"></script>
<script>
function WriteCookie(name)
{
	
	
   //cookievalue= escape(document.loginForm.emailOrPhone.value) + ";";
   document.cookie="name=" + "";
   
	
}


function deleteCookie()
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
			    if(value!=""){
			    	WriteCookie(name);
			    }
			      
			    
			  }
		}
		var allcookies = document.cookie;
		
}
</script>
<style>
#auto{
    position: absolute;
    display: inline-block;
    max-height: 250px;
    top: 33px;
    width: 640px;
    left: 0px;
    background-color: white;
    border-color: black;
    border-width: 0px;
    border-style: solid;
    overflow-y:auto;
}
.autoFriends{
width:640px;
}
.autoFriends:hover{
background-color:#4D70B8
}

.autoFriends a{
display: inline-block;      
 width:640px;
}
</style>
</head>
<body>
<div class="navbar-header">
                      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle</span>
                        <span class="icon-bar"></span>
          				<span class="icon-bar"></span>
          				<span class="icon-bar"></span>
                      </button>
                      
                      <a href="<s:url action='home1'/>" class="navbar-brand logo"><font size='6'>f</font></a><!-- logo -->
                  	</div>
                  	<nav class="collapse navbar-collapse" role="navigation">
                    <form class="navbar-form navbar-left" action="Search" id="searchForm" >
                        <div class="input-group input-group-sm6" ><!-- search box -->
                          <input type="text" class="form-control" placeholder="Search for people, places, and things.." name="searchText" id="typeahead" style="width:600px"  autocomplete="off">
                          <div id="auto"></div>
                          
                          <div class="input-group-btn">
                          
                            <button class="btn btn-default" disabled="disabled" style="background-color:white"><i class="glyphicon glyphicon-search"></i></button>
                          </div>
                        </div>
                    </form>
                    <ul class="nav navbar-nav">
                      <li><!-- Name of the person with image -->
                        <a href="<s:url action='profileTempAction'/>" role="button" data-toggle="modal"><img  width="25" height="25" src="image?photoId=<s:property value="%{user.profilePicId}"/>"  /> <b><font size='4'> <s:property value="%{user.firstName}"/></font></b></a>
                      </li>
                      <li><!-- home -->
                        <a href="<s:url action='home1'/>" role="button" data-toggle="modal"><b><font size='4'>Home</font></b></a>
                      </li>
                      <li>
                      <div class="dropdown" style="top:15px">
                      <!-- Friendship Request button-->
                        <a href="#" class="dropdown-toggle"  data-toggle="dropdown"><span class="badge"><img src="Images/friend_icon.jpg" width="25" height="25"/></span></a>
                        
                         <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width:500px;left-margin:10px">
                         <s:iterator value="friendsRequestRecievedList">
                         <li>
    							<form action="sendFriendRequest">
    									&nbsp;&nbsp;&nbsp;<a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"><img  width="30" height="30" src="image?photoId=<s:property value="profilePicId"/>" /> </a>
    									&nbsp;&nbsp;&nbsp;<a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"><font color="#3b5998"><b><s:property	value="name" />:</b></font></a><br/><br/>
    									<input type="hidden" name="userId1"	value="<s:property value="userId" />" />
    									<input type="hidden" name="userId2"	value="<s:property value="profileId" />" /> 
    									 
    									<div class="b1" >
    									
    									&nbsp;&nbsp;&nbsp;<a  class="btn btn-default active" style="background-color:#3b5998" onclick="confirmRequest($(this).closest('form'),event)" name="submit" ><span class="glyphicon glyphicon-ok" ></span><font color="white">Confirm</font></a>	
    									<a  class="btn btn-default active"  onclick="declineRequest($(this).closest('form'),event)" name="submit" ><span class="glyphicon glyphicon-remove" ></span>Decline</a>	
    									</div>
    									<div class="accepted" style="display:none"><!-- accept div hidden initially -->
    									<font color="#3b5998">&nbsp;&nbsp;&nbsp;Friend request Accepted</font>
    									</div>
    									<div class="rejected" style="display:none"><!-- accept div hidden initially -->
    									<font color="red">&nbsp;&nbsp;&nbsp;Friend request Declined</font>
    									</div>
    									<hr>
    							</form>
    						</li>
    						
    					</s:iterator>
    					
  						</ul>
                        
                        
                        </div>
                      </li>
                      <li>
                        <a href="<s:url action='basicMessages'/>"><span class="badge"><img src="Images/message_icon.jpg" width="25" height="25"/></span></a>
                      </li>
                      <li>
                        <a href="<s:url action='birthday'/>"><span class="badge"><img src="Images/notification_icon.jpg" width="25" height="25"/></span></a>
                      </li>
                      
                    </ul>
                    <ul class="nav navbar-nav">
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i style="height:20px;width:20px" class="glyphicon glyphicon-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                          <li><a href="<s:url action='logout'/>" onclick="deleteCookie();" target="_top">Logout</a></li>
                         
                        </ul>
                      </li>
                    </ul>
                  	</nav>
</body>
</html>