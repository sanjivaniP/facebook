

$(document).ready(function(){
	
	$(".optionClass").click(function(){
		console.log($(this).children().filter("#optionId").attr("value"));
		console.log($(this).children().filter("#countOfUsers").attr("value"));
		console.log($(this).children().filter("#stringListOfUsers").attr("value"));
		
		var url = 'http://localhost:8080/facebookTemp/voteForPoll';
		/*	var abc=form[0][0];*/
		/*	alert(url);
		*/	var posting = $.post(url, {
				"listOfUsers1" : $(this).children().filter("#stringListOfUsers").attr("value"),
				"optionId1": $(this).children().filter("#optionId").attr("value"),
				"count1":$(this).children().filter("#countOfUsers").attr("value")
			});
			posting.done(function(response) {
				//console.log($(this).parent());
				document.getElementById("pollForm").submit();
			});

		
		
	});
	
});