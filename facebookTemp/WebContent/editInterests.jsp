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
<h3>Add Interested In</h3>
 <s:form action="editIntrestedIn2">
 <s:iterator value="interestedList">
						<input type="checkbox" name="interestedList" id="interestedList"
							value='<s:property/>' checked="checked" />
						<s:property />
					</s:iterator>							
 <s:iterator value="notInterestedList">
						<input type="checkbox" name="notInterestedList" id="notInterestedList"
							value='<s:property/>'  />
						<s:property />
					</s:iterator>		
 <input type="submit" value="Save Changes"  />&nbsp;&nbsp;
 <input type="button" onclick="" value="Cancel" id="cancelgender"/>
 </s:form>
 
 
 

 
 
</body>
</html>