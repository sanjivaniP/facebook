<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>

div{
  box-sizing:border-box;
  -moz-box-sizing:border-box;
  -webkit-box-sizing:border-box;
  border:1px solid black;
}

html, body, .container
{
    position:relative;
    
}

.left{
    float: left;
    width: 50%;
    
    border: 0px;
    background-color:#4e69a2;
    
	
  
}
.right{
    float: right;
    width: 50%;
    
    border: 0px;
    background-color:#4e69a2;
    
	
  
}


.second{
    float: left;
    width: 100%;
   
    background-image:url('Images/index2.jpeg')
}


.third{
    float: left;
    width: 100%;
    
   background-image:url('Images/index2.jpeg')
    
}

.fourth {
    float: left;
    width: 50%;
   
    position:relative;
   background-image:url('Images/Images.jpeg')   
    
}
.fifth {
    float: left;
    width: 50%;
    position:relative;
   background-image:url('Images/Images.jpeg')   
}

.sixth {
    float: right;
    width: 50%;
    position:relative;
   background-image:url('Images/Images.jpeg')   
}

.seventh {
    float: right;
    width: 50%;
   position:relative;
   background-image:url('Images/Images.jpeg')   
}
.eighth {
    float: right;
    width: 50%;
    position:relative;
   background-image:url('Images/Images.jpeg')   
}

.ninth {
    float: left;
    width: 100%;
    postion:relative;
   background-image:url('Images/Images.jpeg')   
}


a:link {color: black; text-decoration: underline; }
a:active {color: black; text-decoration: underline; }
a:visited {color: black; text-decoration: underline; }
a:hover {color: black; text-decoration: none; }
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>

<div class="container">
<div class="left" style="background:#4e69a2"><tiles:insertAttribute name="header1" /></div>
<div class="right" style="background:#4e69a2"><tiles:insertAttribute name="header2" /></div>
 
<div class="second"><tiles:insertAttribute name="coverPic" /></div>
<div class="third"><tiles:insertAttribute name="profileHeader" /></div>
<div class="ninth"><tiles:insertAttribute name="aboutword" /></div>
<div class="fourth"><tiles:insertAttribute name="workandEducation" /></div>
<div class="sixth"><tiles:insertAttribute name="places" /></div>
<div class="seventh"><tiles:insertAttribute name="basic" /></div>
<div class="fifth"><tiles:insertAttribute name="relationship" /></div>
<div class="eighth"><tiles:insertAttribute name="contact" /></div>


</div>


</body>
</html>