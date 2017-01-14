<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>


function hideTextBoxes(){
	
	document.getElementById('work').style.visibility='hidden';
	document.getElementById('edu').style.visibility='hidden';
}
function showTextBoxes(){
	
	document.getElementById('work').style.visibility='visible';
	document.getElementById('edu').style.visibility='visible';

	
	}
	
	
function handle(e){
    if(e.keyCode === 13){
        alert("Enter was pressed was presses");
    }

    return false;
}


function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}
function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#888"; }
}

</script>
<style> 
 

 </style> 

</head>
<body onload="hideTextBoxes()">

<div >
<b>Work and Education</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<s:if test="isHyperlink!=\"true\"">
<input type="submit" value="Edit" id="Edit" onclick="showTextBoxes()"/>
</s:if>

<br/>
<form action="saveWork">
<INPUT TYPE="text" NAME="work" value="where have you worked?" title="workandEducation" id="work" onfocus="inputFocus(this)" onblur="inputBlur(this)" onkeypress="submitOnEnter(this, event)"/><br/><br/>
</form>
<s:if test="isHyperlink!=\"true\"">
<s:iterator value="workList">
<br/>
	   <s:property value="work" />
	
	    <a href="<s:url action='editWork1'><s:param name="companyName"><s:property value="work" /></s:param> 
	    
	   </s:url>">edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    <a href="<s:url action='deleteWork'><s:param name="companyName"><s:property value="work" /></s:param> 
	   </s:url>">delete</a><br/>
	   
</s:iterator>
</s:if>
<s:else>
<s:iterator value="workList">
<br/>
	   <s:property value="work" />
	   
</s:iterator>
</s:else>

<form action="saveEducation">
<INPUT TYPE="text" NAME="education" value="where did you go to College?" id="edu" title="workandEducation" onfocus="inputFocus(this)" onblur="inputBlur(this)" onkeypress="submitOnEnter(this, event)"/>
</form>
<s:if test="isHyperlink!=\"true\"">
<s:iterator value="educationList">
<br/>
	   <s:property value="education" />
	   
<a href="<s:url action='editEducation1'><s:param name="schoolName"><s:property value="education" /></s:param> 
	   </s:url>">edit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     <a href="<s:url action='deleteEducation'><s:param name="schoolName"><s:property value="education" /></s:param> 
	   </s:url>">delete</a><br/>
	   
</s:iterator>
</s:if>
<s:else>
<s:iterator value="educationList">
<br/>
	   <s:property value="education" />
	   
</s:iterator>

</s:else>

</div>

</body>
</html>