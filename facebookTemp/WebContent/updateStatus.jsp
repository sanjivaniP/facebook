<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UpdateStatus</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/profile.css" rel="stylesheet">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/updateStatus.js"></script>

        

<script language="JavaScript" type="text/JavaScript" >

function checkIfPressed(e){
	
	//var code = (e.keyCode ? e.keyCode : e.which);
	// alert('codee:::'+code);

}


function hidePost(){
	
	document.getElementById('post').style.visibility='hidden';
}
function showPost(event){
	
	document.getElementById('post').style.visibility='visible';
	
	}
</script>


<style>
#dropdownTag {
	position: relative;
	display: inline-block;
	max-height: 250px;
	width: 900px;
	background-color: white;
	top: -15px;
	border-color: black;
	border-width: 0px;
	border-style: solid;
	overflow-y: auto;
	display: inline-block;




}

.autoFriends2 {
	hieght: 50px;
}
</style>


</head>
<body onload="hidePost()">

 
                                 


 <div class="well">
<form  id="statusForm">
 <div class="input-group">
<a href="#" id="statusLink" onclick="showPost()"><font size='3'>Status</font></a>
<br/>
<input type="text" name='statusBar' id='status' style="height:50px;width:900px" placeholder="What's on your mind?" autocomplete="off"/>
<input type="hidden" id="actualStatus" name="status"/>
<div id="dropdownTag"></div>
<br/><br/>

 <input type="submit"  style="background-color:#3b5998;color:white" value="Post" align="right" id="post"/>
 </div>

</form>
</div>
</body>
</html>