
$(document).ready(function(){
	var tagFlag=false;
	var shiftKey=false;
	var prev=0;
	var curFriend=0;
	var count=0;
	var startPoint=new Array();
	var length=new Array();
	var hrefs=new Array();
	var listLength = 0;
	
	
	$("#status").keyup(function(event){
		console.log(event.keyCode)
		
		if(event.shiftKey && event.keyCode==50)//@ typed
			{tagFlag=true;
			$("#status").focus();
			}
		
		if(event.keyCode==32)//space
			tagFlag=false;
		
		if(event.keyCode!=40&&event.keyCode!=38&&tagFlag==true)
	{		console.log("in here!!");
		event.preventDefault();
		
		$("#dropdownTag").css("display","inline-block");
	console.log($(this).val());
	var text=$(this).val();
	var naam=text.substring(text.lastIndexOf('@'))
	console.log(naam)
	var nayanaam=naam.split('@');
	$.ajax({
	url: "getSearchForTagging",
	data:{searchText:nayanaam[1]},
	
	context: document.body
	}).done(function(data) {
		listLength = data.searchResult1.length;
		console.log(data.searchResult1[0]);
		var prenka="";
		var counter=0;
		data.searchResult1.forEach(function(entry) {
		  //  console.log(entry);
		  //  prenka+=entry+"<br>";
		    prenka+="<div id='friend2"+counter+"' class='autoFriends2'>"+entry+"</div>"+"<br>";
		    counter++;
		});
		$("#dropdownTag").html(prenka);
		$("#status").focus();
		
	});
		}
		else//remove auto suggest
			{
			if(event.keyCode!=40&&event.keyCode!=38){
			$("#dropdownTag").children().remove();
			$("#dropdownTag").css("display","none");
			listLength = 0;
			}
			}
	
});
	
	
	$("#status").keydown(function(event){
		
		if(event.keyCode==40 && listLength>curFriend )
	{
			console.log("down")
			$("#status").focus();
				curFriend++;
	}
		if(event.keyCode==38 && curFriend>0 )
		{	console.log("up")
			curFriend--;
		$("#status").focus();
		}
		
		
		
	$(".autoFriends2").css("background-color","white")
	var curDiv=curFriend-1;
	$("#friend2"+curDiv).css("background-color","blue")
	
	
	if(event.keyCode==13 && curFriend!=0 ){
		//console.log($(this).val())
		event.preventDefault();
		curFriend=0;
		startPoint.push($(this).val().lastIndexOf('@'))
		length.push($("#friend2"+curDiv).children().text().length)
		hrefs.push($("#friend2"+curDiv).children().filter('.profileId').attr('value'));
		var text=$(this).val()
		var tempVal=text.split("@")
		var prevVal=tempVal[0]
		var newVal=prevVal+$("#friend2"+curDiv).children().text();
		$(this).val(newVal);
		tagFlag=false;
		//console.log($("#friend"+curDiv).children().text())
		//window.location.href=$("#friend"+curDiv).children().attr("href");
	}
	
	   var offset=curDiv*50;
		$("#dropdownTag").animate({scrollTop:offset},10);
	
	});

	
	$("#post").click(function(){
		var text= $("#status").val();
		console.log('submitting');
		var newText="";
		var finText="";
		if(hrefs.length>0){
		newText+=text.substring(0,startPoint[0])+"<a href=''"+hrefs[0]+"\'' style=''color:#5C5CD6''>"+text.substring(startPoint[0],startPoint[0]+length[0])+"</a>";
		finText=newText;
		for(var i=1;i<startPoint.length;i++){
			//console.log(text.substring(startPoint[i],startPoint[i]+length[i]))
			finText+=text.substring(startPoint[i-1]+length[i-1],startPoint[i])+"<a href=''"+hrefs[i]+"\'' style=''color:#5C5CD6''>"+text.substring(startPoint[i],startPoint[i]+length[i])+"</a>";
		}
		finText+=text.substring(startPoint[i-1]+length[i-1],text.length);

		/*for( var i =0; i< hrefs.length;i++)
			$("#anchor1"+i).attr("href",hrefs[i])*/
			$("#actualStatus").val(finText);
		}
		else
			$("#actualStatus").val($("#status").val());
			document.getElementById("statusForm").action="updateStatus";
			document.getElementById("statusForm").submit();
					
	});
	
	
	
}) ;
 
