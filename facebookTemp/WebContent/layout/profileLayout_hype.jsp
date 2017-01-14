<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <<meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="Images/favicon.ico" type="image/x-icon"> 
        <title>Facebook</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/profile.css" rel="stylesheet">
        <!--[if lt IE 9]>
          <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->










        <!-- CSS code from Bootply.com editor -->
        
        <style type="text/css">

        </style>
    </head>
    
    <!-- HTML code from Bootply.com editor -->
    
    <body  >
       
        <div class="wrapper">
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left row-fluid">
                      
          
           
          
            <!-- main right col -->
            <div class="column col-sm-12 col-xs-12" id="main">
                
                <!-- top nav -->
              	<div class="navbar navbar-blue navbar-static-top">  
                    <tiles:insertAttribute name="header" />
                </div>
                <!-- /top nav -->
              
              <!--profile nav and cover  -->
				<div class="row" style="width:100%;height:400px">
                      	 <div class="col-sm-12" style="height:300px" id="cover-pic">
                      	 
                      	 <tiles:insertAttribute name="coverPic" />
                      	 
                      	 </div>
                      	
                      	
                      	<div id="profile-pic" ><tiles:insertAttribute name="profilePic" /></div>
                      	
                      <div class="col-sm-12 " style="height:20px">
                      	 <div class=" navbar navbar-default navbar-collapse collapse" id="profile-nav">
          						<ul class="nav navbar-nav">
          							<li><a href="#about" style="color: rgb(102, 102, 102); padding: 10px 10px 11px; font-size: 14px; width:250px;" ></a></li>
          							
           							 <li><a href="<s:url action="profileTempAction_hype" > <s:param name="profileId"><s:property value="profileId"/></s:param></s:url>"  style="color: rgb(102, 102, 102); padding: 10px 10px 11px; font-size: 14px;" >Timeline</a></li>
           							 <li><a href="<s:url action="About_hype" > <s:param name="profileId"><s:property value="profileId"/></s:param></s:url>" style="color: rgb(102, 102, 102); padding: 10px 10px 11px; font-size: 14px;" >About</a></li>
          							  <li><a href="<s:url action='viewPhotosTemp'/>" style="color: rgb(102, 102, 102); padding: 10px 10px 11px; font-size: 14px;" >Photos</a></li>
          							  <li><a href="<s:url action="getFriends_hype" > <s:param name="profileId"><s:property value="profileId"/></s:param></s:url>" style="color: rgb(102, 102, 102); padding: 10px 10px 11px; font-size: 14px;" >Friends</a></li>
          							  
          						</ul>
                      	 
                      	 </div>
                      </div>
                      </div>  <!--profile nav and cover end -->      
              
               
                    <div class="column col-sm-12">
                      
                        <!-- content -->                      
                      	<div class="row">
                          
                         <!-- main col left --> 
                         <div class="col-sm-4">
                     
                         <tiles:insertAttribute name="about" />
                          
                          </div>
                          
                          <!-- main col right -->
                          <div class="col-sm-8">
                               
                               <tiles:insertAttribute name="updateStatus" />
                      			<tiles:insertAttribute name="ownNews" />
                      
                            
                               
                              
                                                        
                       </div><!--/col 7-->
                      
                        
                       
                      
                      
                        
                      
                    </div><!-- ROW-->
                </div><!-- /col 12 -->
            </div>
            <!-- /main -->
          
        </div><!-- row fluid  -->
    </div><!--box  -->
</div><!--wrapper  -->

        
      <script type='text/javascript'
		src="js/jquery.min.js"></script>


	<script type='text/javascript'
		src="js/bootstrap.min.js"></script>


        
        <!-- JavaScript jQuery code from Bootply.com editor -->
        
        <script type='text/javascript'>
        
        $(document).ready(function() {
        
            /* off-canvas sidebar toggle */

$('[data-toggle=offcanvas]').click(function() {
  	$(this).toggleClass('visible-xs text-center');
    $(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
    $('.row-offcanvas').toggleClass('active');
    $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
    $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
    $('#btnShow').toggle();
});
        
        });
        
        </script>
        
    </body>
</html>