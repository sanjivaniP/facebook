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
<title>Poll</title>

<script type="text/javascript">

/* $(document).ready(function(){
	var counter = 2;
    
 	
    $("#option"+(counter-1)).focus(function () {
 	console.log("#option"+(counter-1));
	if(counter>10){
            alert("Only 10 textboxes allow");
            return false;
	}   
 
	var newTextBoxDiv = $(document.createElement('div'))
	     .attr("id", 'TextBoxDiv' + counter);
 
	newTextBoxDiv.after().html('<input type="text" class="span6" id="option'+counter+'" name="searchText" autocomplete="off" placeholder="Add options" onfocu>'');
 
	newTextBoxDiv.appendTo("#TextBoxesGroup");
 
 
	counter++;
     }); */
     
     
     
     
 
    /*  $("#removeButton").click(function () {
	if(counter==1){
          alert("No more textbox to remove");
          return false;
       }   
 
	counter--;
 
        $("#TextBoxDiv" + counter).remove();
 
     }); */
    
     
var count=0;
   
/*      function displayMsg() {
 console.log("Here");
	var msg = '';
	var i=1;
	for(i=1; i<count; i++){
   	  msg += "\n Textbox #" + i + " : " + $('#' + i).val();
	}
    	  
    	  console.log(msg);
     }  */
   
    
     
       
     function nothing(){
	 }
     
     
     function addTextBox(curr){
    	 
 		var counter=0;
 		counter=parseInt(curr.id)+1;
 		
 		console.log(counter);
 		$("#"+curr.id).attr('onfocus','nothing()');
 		if(counter>10){
 	            alert("Only 10 textboxes allow");
 	            return false;
 		}   
 	 
 		var newTextBoxDiv = $(document.createElement('div'))
 		     .attr("id", 'TextBoxDiv' + counter);
 	 
 		newTextBoxDiv.after().html('<input type="text" class="span6" id="'+counter+'" name="searchText" autocomplete="off" placeholder="Add options" onfocus="addTextBox(this)">');
 	 
 		newTextBoxDiv.appendTo("#TextBoxesGroup");
 	 
 	 	count=counter;
 		    	    	 
  
  }
     
     $(document).on("submit", ".poll", function(event) {
    	 
    	
    	 console.log("Here");
    	 
    	 if(count>2 && $('#' + 2).val()!=""){
    		var msg = '';
    		var i=1;
    		for(i=1; i<count; i++){
    	   	  msg += $('#' + i).val()+"#";
    		}
    	    	  
    	    	  console.log(msg);	
    	    	  $("#options").attr('value',msg);
    	 }
    	 else{
    		 alert("At least 2 options should be entered");
    		 return false;
    		 
    	 }
     
     
     });
     
  
</script>

</head>
<body>

 <form action="submitPoll" class="poll">
  <div class="well" id="TextBoxesGroup">
 			
  			<input type="text" class="span6" id="Question" name="question"  required="required" autocomplete="off" placeholder="Add question">
  			<div id="TextBoxDiv1">
            <input type="text" class="span6" id="1" autocomplete="off" required="required" placeholder="Add options" onfocus="addTextBox(this)">
            </div>
           </div>
            <input type="submit" onclick="displayMsg()" value="Post" ></input>
            <input type="hidden" name="options" id="options"/>
            
            
</form>
    
</body>
</html>