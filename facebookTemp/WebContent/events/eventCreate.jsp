<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page import="java.io.*,java.util.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<sx:head />

<script language="javascript" type="text/javascript"
	src="js/datetimepicker.js">
	
</script>

<script type="text/javascript">
	function validateAndSubmit() {
		
		//alert((dojo.widget.byId("datepicker").getValue()).substr(0,10) );
		var str=dojo.widget.byId("datepicker").getValue().substr(0,10);
		


		var d1 = Date.parse(str);
		var d2 = Date.parse(new Date());
		


		if (document.getElementById("Name").value == "")
			{
			alert("Name is empty.");			
			}
		else if (document.getElementById("Details").value == "")
			{alert("Enter Details.");}			
				else if (document.getElementById("Where").value == "")
			{alert("Place is empty.");}
				else if(str== "")
				{					alert("Enter Date");}
				else if (d1 < d2) {
				    alert ("Error !Date must be later than today's date.");
				}				else  document.getElementById("form1").submit();
		
	}
	
	
	function gotoevent()
	{
		document.getElementById("form1").action="eventTemp";
			document.getElementById("form1").submit();
		
	}
</script>

</head>
<body>
	<div class="panel panel-default">

		<h3>
			<b><font color="#000000">Create New Event</font></b>
		</h3>
		<hr>
		<form action="createEvent" id="form1">
			<table>
				<tr>
					<td><font size=2><b>Name</b></font></td>
					<td><input type="text" name="name" id="Name"
						placeholder="ex: Birthday Party" /></td>
				<tr>
					<td>Details</td>
					<td><input type="text" name="details" id="Details"
						placeholder="Add more info" /></td>
				</tr>
				<tr>
					<td><font size=2><b>Where</b></font></td>
					<td><input type="text" name="where" id="Where"
						placeholder="Add a place" /></td>
				</tr>
				<tr>
					<td>When</td>
					<td>
				
					
					<sx:datetimepicker name="datepickerDate" id='datepicker'
							displayFormat="dd-MMM-yyyy" required="true"  />
						<input type="text" name="when" id="When" placeholder="Add a time?"
						width="50px" /></td>
				<tr>
					<td><input style="background-color: #45619d; color: white;"
						type="button" value="Create" onclick='validateAndSubmit()' /> </td>
					<td><input type="button" value="Cancel" onclick="gotoevent()"  /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>