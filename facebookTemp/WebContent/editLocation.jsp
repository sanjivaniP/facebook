<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="editLocation2">
<label for="address"  ><s:property value="citytype" /></label>
<input type="text" name="City" required="required" value="<s:property value="location"/>" />
<input type="hidden" name="address1" value="<s:property value="citytype"/>"/>
</br>
<input type="submit" value="Save" id="save"/>
</form>
</body>
</html>