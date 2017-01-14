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
<script src="js/message.js"></script>
<style>


#autoMessage{
    position: relative;
    display: inline-block;
    max-height: 250px;
    
    width: 600px;
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
div {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
/* 	  border: 0.1px solid black; */
}

html,body,.container 
{
	height: 100%;
	min-height: 100%;
}

.autoscroll
{
width:100%;
height:100%;
overflow:auto; 
}

.heading
{
    width: 100%;
	height: 8%;
}

.conversation
{
    width: 100%;
	height: 54%;
}
.reply
{
    width: 100%;
	height: 30%;
}
</style>

</head>
<body>
<form action="createMessage" id="createMessage">
	
    <div class="heading">
	&nbsp;&nbsp;New Message
	</div>

	<div class="heading">
			&nbsp;&nbsp;
      To:
      <input type="text" class="form-control"  name="searchText" id="typeaheadMessage" style="width:600px"  autocomplete="off">
      <input type="hidden" name="To" id="receiver"/>
         <div id="autoMessage"></div>                
   </div>

    <div class="conversation">
    <div class="autoscroll">
	</div>
	</div>    

    <div class="reply">

			<input type="text" name="reply" id="reply" placeholder="Write a reply..." style="height: 100px;width: 600px;"/>
			
			<input type="submit" value="Reply"  />
	</div>

</form>	
</body>
</html>