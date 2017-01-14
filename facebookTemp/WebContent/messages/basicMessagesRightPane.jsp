<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/facebook.css">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">
<style>
div {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
/* 	border: 0.1px solid black; */
}

"
WebContent /basicMessagesRightPane.jsp "

html,body,.container {
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
function validateNull()
{
/* alert(document.getElementById("reply1").value); */
if(document.getElementById("reply").value==" "||document.getElementById("reply").value==null) 
	{
   return false;	 
	}
else if(document.getElementById("reply").value=="Write a reply...")	
    {
	 return false;
    }
    
else
	{
	document.getElementById("writeReplyForm").submit();

	}
}


</script>

</head>
<body>
 	<div class="panel panel-default" >

		<div class="heading"></div>

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

		<div class="conversation">
			<div class="autoscroll">
				<table cellpadding="5">
					<s:iterator value="fullConversation">
						<tr>
							<td rowspan="2"><a href=""><img
									src="image?photoId=<s:property value="photoId" />" height="40"
									width="40"></a></td>
							<td><a href="" class="nameFont"><span
									class="hover"><s:property value="senderName" /></span></a></td>

						</tr>
						<tr>
							<td><s:property value="text" /></td>  <td><s:property value="sentAt" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
<hr>
		<div class="reply" align="center">
		
			<s:form id="writeReplyForm" action="replyToSelectedConversation">
				<s:textarea name="reply" id="reply" cols="75" rows="4" value="Write a reply..."
				onblur="if (this.value == '') {this.value = 'Write a reply...';}"   
					onfocus="if (this.value == 'Write a reply...') {this.value = '';}" 
				/>
			
				<input type="button" value="Reply" onclick="validateNull()"/>
			</s:form>

		</div>
 	</div>
</body>
</html>