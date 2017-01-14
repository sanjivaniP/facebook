<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UpdateStatus</title>


<script language="JavaScript" type="text/JavaScript" >

function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}
function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}
$('#status').click(function(event) {

       event.preventDefault();
    //or return false;
});
function hidePost(){
	
	document.getElementById('post').style.visibility='hidden';
}
function showPost(event){
	
	document.getElementById('post').style.visibility='visible';
	
	}
</script>

</head>
<body onload="hidePost()">
<form action="postOnWall">
<a href="#" id="status" onclick="showPost()">Post</a>
<br/>
<input type="hidden" name="profileId" value="<s:property value="profileId" />">
<input type="text" name='postOn' id='postOn' style="height:50px;width:800px" value="Write Something...." onfocus="inputFocus(this)" onblur="inputBlur(this)"/>
<br/>
 <input type="submit" value="Post" align="right" id="post"/>
</form>
</body>
</html>