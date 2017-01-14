<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
 --><style type="text/css">
div {
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;

}

html,body,.container {
	height: 100%;
	min-height: 100%;
}

.leftPane {
	float: left;
	width: 30%;
	height: 100%;
}

.rightPane {
	float: right;
	width: 70%;
	height: 100%;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

</head>
<body>

<div class="leftPane">
	<!-- write all user and the last message of their conversation . -->
    <tiles:insertAttribute name="leftPane" />
</div>


<div class="rightPane">
	<tiles:insertAttribute name="rightPane" />
</div>
</body>
</html>