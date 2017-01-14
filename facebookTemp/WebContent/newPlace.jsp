<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Place</title>

</head>
<body>
<center>
<h3> MOVED </h3>
<form action="addPlace">
<table border="0" cellpadding="5" width="300">
<tr>
	<td><label for="wherelabel">WhereTo</label>&nbsp;</td>
	<td><INPUT TYPE="text" NAME="whereto"/></td>
</tr>
<tr>
	<td><label for="addresslabel">Address</label>&nbsp;</td>
	<td><INPUT TYPE="text" NAME="address"/></td>
</tr>
<tr>
	<td><label for="fromlabel">From</label>&nbsp;</td>
	<td><INPUT TYPE="text" NAME="from"/></td>
</tr>
<tr>
	<td><label for="whenlabel">When</label>&nbsp;</td>
	<td><INPUT TYPE="text" NAME="when"/></td>
</tr>
<tr>
	<td><label for="storylabel">Story</label>&nbsp;</td>
	<td><INPUT TYPE="text" NAME="story"/></td>
	
</tr>
</table>
<input type="submit" value="Save" id="save"/>&nbsp;&nbsp;
<input type="button" onclick="self.close()" value="Cancel" id="cancel"/>

</form>

</center>


</body>
</html>