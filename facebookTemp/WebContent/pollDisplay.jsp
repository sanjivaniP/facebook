<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="js/jquery-1.10.2.js"></script>
<script src="poll.js"></script>

<style>
#progressbar {  
    border: 1px black solid;  
    width: 200px;  
    height: 25px;  
    float:left;
    
      
}  
  
#progressbar #colorProgress {  
    border-left:solid grey 
     
} 
</style>



<script type="text/javascript">

function openJSP()
{
   
	 location.href = "createPollTemp.action";
		
}
 



</script>

</head>
<body>

<input type="button" value="Create Poll" onclick="openJSP();">


<s:iterator value="pollList">
	<br/>
	<br/>
	<div class="panel panel-default" style="height:auto">
	
	<form class="pollForm" id="pollForm" action="displayPolls">
	<input type="hidden" name="pollId" value=<s:property value="pollId" />>
	<label ><font color="blue"  size="3">Created By :</font></label>
	<a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profile.profileId" /></s:param></s:url>"><img  width="50" height="50" src="image?photoId=<s:property value="%{profile.profilePicId}"/>"  /></a>
	<label><font size="2"><s:property value="profile.firstName" /> <s:property value="profile.lastName" /></font></label>
	
	<br/><label for="qtnLabel"><font color="blue"  size="4">Question :</font></label>
	<label for="qtn"><font size="3"><s:property value="Question" /></font></label>
	
	<s:iterator  value="optionlist" status="status">
	<br/>
	<div class="optionClass">
	&nbsp;&nbsp;&nbsp;
		<s:if test="checked==\"true\"">
		<s:checkbox name="checkboxes[%{#status.index}]"  theme="simple" onclick="submitPoll(this.form,event)" checked="checked"/>
		 </s:if>
		 <s:else>
		 <s:checkbox name="checkboxes[%{#status.index}]"  theme="simple" onclick="submitPoll(this.form,event)"/>
		 </s:else>
		<s:property value="description"/>&nbsp;&nbsp;&nbsp;
		
		
		<div id="progressbar">
			<!-- <p id="colorProgress style="width:'<s:property value="voterPercentage"/>'"> <s:property value="voterPercentage"/></p> -->
			<img  width="<s:property value="voterPercentage*2"/>" height="25" src="Images/polldisp.jpg"  />
		</div>&nbsp;&nbsp;&nbsp;
		
		<s:iterator value="listOfUser">
			<a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"><img  width="50" height="50" src="image?photoId=<s:property value="profilePicId"/>"  /></a> 
		</s:iterator>
		&nbsp;&nbsp;&nbsp; <s:property value="countOfVotes" /> Votes
		
		<input type="hidden" id="optionId" name="optionId" value="<s:property value="optionId" />" />	
		<input type="hidden" id="countOfUsers" name="countOfUsers" value="<s:property value="countOfVotes" />" />
		<input type="hidden" id="listOfUser" name="listOfUser" value="<s:property value="listOfUser" />" />
		<input type="hidden" id="stringListOfUsers" name="stringListOfUsers" value="<s:property value="stringListOfUsers" />" />
		</div>			
	</s:iterator>
	 <!--<s:submit value="Vote" action= "voteForPoll"></s:submit>-->
	 </form>
	<!-- <input type="submit" value="Vote"/> -->
	
	</div>
</s:iterator>



</body>
</html>