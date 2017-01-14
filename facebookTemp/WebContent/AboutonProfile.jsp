<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

                                 
  <div class="panel panel-default">  
  <div class="panel-heading">                          

<h3>About</h3>
</div>
<div class="panel-body">
<s:if test="profileAbout.workList!=null">  
 <label for="workedLabel" style="color:#888;">Worked at : </label>&nbsp;
 <label for="work">
 <s:iterator value="profileAbout.workList" status="workPlace">
		<s:if test="#workPlace.first == true ">
    		<s:property value="work" />
  		</s:if>
</s:iterator>
 
 </label>
                    
 </s:if>  
 <s:else>
 	 <label for="workLabel" style="color:#888;">Work </label>
 	
 </s:else>
 <br/>
 <s:if test="profileAbout.educationList!=null"> 
<label for="StudiedLabel" style="color:#888;">Studied at : </label>&nbsp;
 <label for="education">
 <s:iterator value="profileAbout.educationList" status="edu">
		<s:if test="#edu.first == true ">
    		<s:property value="education" />
  		</s:if>
</s:iterator>
 
 </label>
                    
 </s:if>  
 <s:else>
 	 <label for="studyLabel" style="color:#888;">Study </label>
 	
 </s:else>
 <br/>
 
 <s:if test="profileAbout.homeTown!=null">  
 <label for="homeLabel" style="color:#888;">From : </label>&nbsp;
 <label for="homeTown"><s:property value="profileAbout.homeTown"/></label>
                    
 </s:if>  
 <s:else>
 	<label for="noHomeLabel" style="color:#888;">Home Town Not Updated </label>
 	
 </s:else>
 <br/>
 
 <s:if test="profileAbout.currentCity!=null">  
 <label for="cityLabel" style="color:#888;">Lives in : </label>&nbsp;
 <label for="currentCity"><s:property value="profileAbout.currentCity"/></label>
                    
 </s:if>  
 <s:else>
 	<label for="noCityLabel" style="color:#888;">Current City Not Updated </label>
 	
 </s:else>
 </div>
</div>
</body>
</html>