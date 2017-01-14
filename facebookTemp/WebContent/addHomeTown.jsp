<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>

</script>
</head>
<body>
<h3>Add Home Town</h3>
 <s:form action="saveHomeTown">
 <INPUT TYPE="text" NAME="homeTown"/>
  <br/>
 <input type="submit" value="Save Changes"  />&nbsp;&nbsp;
 <input type="button" onclick="" value="Cancel">
 </s:form>
 
 
 

 
 
</body>
</html>