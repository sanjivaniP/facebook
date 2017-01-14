<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Hello World</title>
</head>
<body>
   <center> 
<H1>Create a Poll</H1>

<FORM action="createpoll" >  
<table>
<tr>
<td>Question:</td>
<td><input type="text" name="Question"></td>
</tr>
<tr>
<td>
Option 1:</td><td><input type="text" name="option1" width=100px height=100px><br><br></td></tr>
<tr><td>Option 2:</td><td><input type="text" name="option2" style="width: 300px height=200px;"><br><br></td></tr>
<tr><td>Option 3:</td><td><input type="text" name="option3" width=100px height=100px><br><br></td></tr>
<tr><td>Option 4:</td><td><input type="text" name="option4" width=100px height=100px><br><br></td>
</tr>
<tr>
<td colspan="2"><INPUT TYPE="SUBMIT" value="Create Poll">
</td>
</tr>
</table>
</FORM>
</center>
</body>
</html>