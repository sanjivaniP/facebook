<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="editCoverPic" method="post" enctype="multipart/form-data">
  <br/>
   <br/>
 
  Select the Cover PHOTO to upload  <input type= "file"  name="myFile"><br>
 Location of Photo <input type="text" id="location" name="location"><br/>
 Say Something About The Photo <input type="text" id="caption" name="caption"><br/>
 <input type="submit" name="submit" value="UPDATE">
 </form>
</body>
</html>