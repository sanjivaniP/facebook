<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<a class="btn btn-default active dropdown-toggle dibba" data-toggle="dropdown" name="submit"><span class="glyphicon glyphicon-user" ></span>Respond to friend request</a>
        							 <ul class="dropdown-menu dropdown-inverse response">
										<li><a onclick="confirmRequest($(this).closest('form'),event)" name="submit" value="Confirm">Confirm</a></li>
										<li><a onclick="declineRequest($(this).closest('form'),event)">Decline</a></li>	
										
									</ul>


</body>
</html>