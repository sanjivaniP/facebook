<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ArrayList" %>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type=text/javascript>
function validate(){
if (document.getElementById('option1').checked){
          alert("checked") ;
}else{
alert("You didn't check it! Let me check it for you.")
}
}
</script>

</head>
<body>
<s:form action="options">

   <h2>Question:<s:property value="Question" /><br>
   <s:checkbox id="option1chk" name="option1chk" value="option1chk"/> <s:property value="option1" /><br><br>
   <s:checkbox name="option2chk" value="option2chk"/> <s:property value="option2" /><br><br>
   <s:checkbox name="option3chk" value="option3chk"/> <s:property value="option3" /><br><br>
   <s:checkbox name="option4chk" value="option4chk"/> <s:property value="option4" /><br><br>
   <s:submit value="submit" name="submit" />
  </s:form>
   
</body>
</html>