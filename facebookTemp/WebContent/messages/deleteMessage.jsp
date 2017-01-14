<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<style>
div {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	/* 	  border: 0.1px solid black; */
}

"
WebContent /basicMessagesRightPane.jsp  " html,body,.container {
	height: 100%;
	min-height: 100%;
}

.autoscroll {
	width: 100%;
	height: 100%;
	overflow: auto;
}

.heading {
	width: 100%;
	height: 8%;
}

.conversation {
	width: 100%;
	height: 54%;
}

.reply {
	width: 100%;
	height: 30%;
}
</style>

<script type="text/javascript">
function checkInput() {

	var form=$("#messageCheckbox");
	var cboxes = form.children().filter('.checkboxDeleteMessage');
	var flag = false;
	for ( var i = 0; i < cboxes.length; i++) 
	{
		//alert("11111"+flag);
		console.log(cboxes.length);
		console.log(cboxes[i].checked);
		if (cboxes[i].checked) 
		{
			//alert("22222"+flag);
			flag = true;
			break;			
		}
	}
	//alert("33333"+flag);
 if (!flag) {
		alert("You must select some message to delete .Click on a message to select it");
	    return false;
	}
}
</script>

</head>
<body>
	<div class="panel panel-default" style="width: 100%; height:100% ">

		<div class="heading">
			<s:form action="deleteMessage">
				<s:submit value="+ New Message" theme="simple"
					action="redirectToCreateMessage"></s:submit>
				<s:select name="deleteChoice"
					list="{'  ','Delete Messages','Delete Conversation'}" value=" "
					property="choose" onchange="this.form.submit()" theme="simple">
				</s:select>
			</s:form>
		</div>
		</div>
	<form id="messageCheckbox" action="deleteSelectedMessage">
	<div class="panel panel-default" style="width: 100%; height: 100%">

<!-- 		<div class="conversation">
 -->			<div class="autoscroll">
					<table>


						<s:iterator value="fullConversation" status="stat">
							<input type="hidden" name="messageId"
								value="<s:property value="messageId" />" />
							<tr>
								<td rowspan="2"><a href=""><img
										src="image?photoId=<s:property value="photoId" />" height="40"
										width="40"></a></td>
								<td colspan="2"><a
									href="<s:url action='showConversationWithSelectedUser' >
						<s:param name="selectedUser"><s:property value="senderName"/>
						</s:param></s:url>" class="nameFont"><span
									class="hover"><s:property
											value="senderName" /></span></a></td>
							</tr>
							<tr>
						
								<td><s:checkbox name="checkboxes[%{#stat.index}]" class="checkboxDeleteMessage"
										theme="simple" /></td>
								<td colspan="1"><s:property value="text" /></td>
							</tr>
						</s:iterator>

					</table>
						</div>
					
			</div>
				<br/>
					<center><s:submit value="Delete" ></s:submit></center>
	</form>
</body>
</html>