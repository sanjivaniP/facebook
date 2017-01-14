<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
function checkInput() {
	
	var cboxes = document.getElementsByName('menWomen');
	var flag = false;
	for ( var i = 0; i < cboxes.length; i++) {
		if (cboxes[i].checked) {
			flag = true;
			document.myFormInterest.action = "saveInterestedIn";
			document.getElementById("myFormInterest").submit();

		}
	}
	if (!flag) {
		alert("You have not made any selection, can't proceed further");
	}
}
</script>
</head>
<body>
<h3>Add Interested In</h3>
 <s:form id="myFormInterest">
 <s:iterator value="menWomen">
						<input type="checkbox" name="menWomen" id="menWomen"
							value='<s:property/>' />
						<s:property />
					</s:iterator>							
 <br/>
 <input type="button" value="Save Changes" onclick="checkInput();" />&nbsp;&nbsp;
 <input type="button" onclick="" value="Cancel" id="cancelgender"/>
 </s:form>
 
 
 

 
 
</body>
</html>