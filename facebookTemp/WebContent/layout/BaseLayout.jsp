<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>

div{
  box-sizing:border-box;
  -moz-box-sizing:border-box;
  -webkit-box-sizing:border-box;
  border:1px solid black;
}

html, body
{
    height: 100%; 
    min-height: 100%;
    border: 0px;
    
}

.first{
	
    float: left;
     width: 100%;
     height: 10%;
    border: 0px;
    background-color:#4e69a2;
	
	
  
}

.left{


    float: left;
     width: 50%;
     height:10%;
    border: 0px;
    background-color:#4e69a2;
	
	
  
}
.right{

    float: left;
    width: 50%;
	 height:10%;  
    border: 0px;
    background-color:#4e69a2;
	
	
}


.second{
    float: left;
    width: 100%;
    
    border:0px;
}





</style>

<link type="text/css" rel="stylesheet" href="css/bootstrap.css">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Facebook<%-- <tiles:insertAttribute name="title" ignore="true" /> --%></title>
<link rel="shortcut icon" href="Images\favicon.ico">
</head>
<body>

<div class="container-fluid" >
<div class="first" style="position:fixed">
<div class="left" style="background:#4e69a2"><tiles:insertAttribute name="header1" /></div>
<div class="right" style="background:#4e69a2"><tiles:insertAttribute name="header2" /></div>
</div>
<div  class="second" ><tiles:insertAttribute name="body" /></div>


</div>


</div>


</body>
</html>