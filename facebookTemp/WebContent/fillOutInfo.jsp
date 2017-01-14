<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fill Out Info</title>
</head>
<body>

<div style="position:absolute;width:850px;height:500px;left:230px;top:120px;background-color:#eeeff4">
<form action="fillOutInfo" method="post" enctype="multipart/form-data">
<h3>Fill in your Profile info</h3>
<br>
<br>
<table><tr><td>
<label>Secondary School</label></td><td>
   <input type="text" name="schoolName" placeholder="High School Name" size="30" style="height:20px">
</td></tr>
<br></br>
<tr><td>
<label>College/University &nbsp; </label></td><td>
   <input type="text" name="collegeName" placeholder="College or University Name" size="30" style="height:20px">
 </td></tr>
 
 <tr><td>
 <label>  &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp;Employer</label></td><td>
   <input type="text" name="companyName" placeholder="Company Name" size="30" style="height:20px">
 </td></tr>
 
 <tr><td>
  <label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;Current City</label></td><td>
  <input type="text" name="currentCity" placeholder="Current City" size="30" style="height:20px">
</td></tr>
 
 <tr><td>

   <label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;Hometown</label></td><td>
  <input type="text" name="hometown" placeholder="Hometown" size="30" style="height:20px">
</td></tr>
 <tr><td>

   <label>&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;Upload a photo</label></td><td>
  </label>
  <input type= "file"  name="myFile" size="30" style="position:relative;height:20px;left:8px;top:9px; "><br><br>
</td></tr>
 </table><br>
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
 <a href="<s:url action="fillOutInfo"/>" style="text-decoration:none; position:relative; left:-207px; ">
<font style ="color:#0000A0;"  size="2"> Skip</font></a>
 <!-- <a href="<s:url action="fillOutInfo"/>"> Skip</a> -->&nbsp;
 <input type="image" src="Images/saveBtn.png" alt="Save & Continue" width="130" height="35" style="position:relative; left:660px; top:-26px">

</form>


</div>
</body>
</html>