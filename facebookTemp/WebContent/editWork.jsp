<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Work</title>

<script type="text/javascript">
	function validateDate() {
		var d=new Date();
		var year=d.getFullYear();
		//alert(year);
		var startDate = document.getElementById("startDate").value;
		var endDate = document.getElementById("endDate").value;
		var numbers = /^[0-9]+$/;
		//alert("please enter the year");
		if (!(startDate.match(numbers)) || !(endDate.match(numbers))) {
			alert("please enter the year");
			return false ;
		} else {
			if(startDate<=year && endDate <=year)
				{
			startDate = parseInt(startDate);
			endDate = parseInt(endDate);
			if (startDate > endDate) {
				alert("start date cannot be greater than end date");
				return false ;
			}
				}
			else
				{
				alert("Please enter the valid year");
				return false ;
				}
		}
		return true ;
	}
</script>
</head>
<body>

	<form action="editWork2" onsubmit="return validateDate()">

		<input type="hidden" name="workPlaceId"
			value="<s:property value="workDetail.workPlaceId"/>" />
		<table border="0" cellpadding="5" width="300">
			<tr>
				<td><label for="companynamelabel">Company Name</label>&nbsp;</td>
				<td><INPUT TYPE="text" NAME="companyName" required="required"
					value="<s:property value="workDetail.companyName"/>" /></td>
			</tr>
			<tr>
				<td><label for="jobprofilelabel">Job Profile</label>&nbsp;</td>
				<td><INPUT TYPE="text" NAME="jobProfile"
					value="<s:property value="workDetail.jobProfile"/>" /></td>
			</tr>
			<tr>
				<td><label for="sdatelabel">Start DATE</label>&nbsp;</td>
				<td><INPUT TYPE="text" NAME="startDate" id="startDate"
					value="<s:property value="workDetail.startDate"/>" /></td>
			</tr>
			<tr>
				<td><label for="edatelabel">End DATE</label>&nbsp;</td>
				<td><INPUT TYPE="text" NAME="endDate" id="endDate"
					value="<s:property value="workDetail.endDate"/>" /></td>
			</tr>
		</table>
		<input type="submit" value="Save" id="save"  />&nbsp;&nbsp;

</form></body>
</html>