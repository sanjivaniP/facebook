<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/test.css" rel="stylesheet">

 
 <script src="js/jquery-1.10.2.js"></script>
 <script src="search/searchResult.js"></script>
<title>Search</title>
</head>
<body>
<s:iterator value="searchResult">
 <div class="panel panel-default">
 								
                                <div class="panel-thumbnail"><img src="/assets/example/bg_4.jpg" class="img-responsive"></div>
                                <div class="panel-body">
                                  <div class="row">
       								<div class="col-md-4">
         								<a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"><img src="image?photoId=<s:property value="profilePicId" />"  style="width: 200px; height: 200px"></a>
       								</div>
      							 	<div class="col-md-6">
        							 <a href="<s:url action='profileTempAction_hype' > <s:param name="profileId"><s:property value="profileId" /></s:param></s:url>"><font color="#00006B" size='4'><b><s:property	value="firstName" /> &nbsp;<s:property value="lastName" /></b></font></a>
        							 <br/><s:property value="info" />
        							 
        							 <br/>
        							 <br/>
        							 <form action="sendFriendRequest" class="friend">
        							 		<input type="hidden" name="userId1"	value="<s:property value="userId" />" /> 
											<input type="hidden" name="userId2" value="<s:property value="profileId" />" />
											<input type="hidden" name="searchText" value="<s:property value="searchText" />" />
        							<s:if test="isFriend==\"Y\"">
	        							 	<button name="submit" class="btn btn-default active dibba" type="submit" value="Friends"><span class="glyphicon glyphicon-ok"></span>Friends</button>
        						  			<!-- <a href="#" class="btn btn-default active" >Message</a> -->
        						   </s:if>
        						   
        						   <s:elseif test="isFriend==\"N\"">
        							 <button name="submit" class="btn btn-default active dibba" type="submit" value="Add Friends" ><span class="glyphicon glyphicon-plus"></span>Add Friends</button>
        						   	<!-- <a href="#" class="btn btn-default active" >Message</a> -->
        						   </s:elseif>
        						   
        						   <s:elseif test="isFriend==\"S\"">
        							 <button name="submit" class="btn btn-default active dibba" disabled="disabled" type="submit" value="Friend Request Sent" ><span class="glyphicon glyphicon-user" ></span> Friend Request Sent</button>	
        						   	<!-- <a href="#" class="btn btn-default active" >Message</a> -->
        						   </s:elseif>
        							 
        						   <s:elseif test="isFriend==\"R\""> 
        							<%-- <button class="btn btn-default active" type="submit" value="Confirm" type="submit" id="dibba"><span class="glyphicon glyphicon-ok" ></span>Confirm</button>
        							<button class="btn btn-default active" type="submit" value="Decline"><span class="glyphicon glyphicon-remove" ></span>Decline</button>
        							<a href="#" class="btn btn-default active" >Message</a>
        							 --%>
        							 <button class="btn btn-default active dropdown-toggle dibba" data-toggle="dropdown" name="submit"><span class="glyphicon glyphicon-user" ></span>Respond to friend request</button>
        							 <ul class="dropdown-menu dropdown-inverse response">
										<li><a onclick="confirmRequest($(this).closest('form'),event)" name="submit" >Confirm</a></li>
										<li><a onclick="declineRequest($(this).closest('form'),event)">Decline</a></li>	
										
									</ul>
									<!-- <a href="#" class="btn btn-default active" >Message</a> -->
        							 
        							</s:elseif>
        							 </form>
        							
        							 <!-- 
        							 	<button class="btn btn-default active dropdown-toggle" data-toggle="dropdown" ><span class="glyphicon glyphicon-plus"></span> Add Friend</button>
        							 	<ul class="dropdown-menu" role="menu">
    										<li><a href="#">Action</a></li>
    										<li><a href="#">Another action</a></li>
    										<li><a href="#">Something else here</a></li>
    										<li class="divider"></li>
    										<li><a href="#">Separated link</a></li>
  										</ul>
        							 	 -->
        							 	
        							 	
        							 
        							 
       								</div>
                                </div>
                              </div>
                             
	
</div>

</s:iterator>

</body>
</html>