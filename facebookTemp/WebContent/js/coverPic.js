
$(document).on("submit", ".coverPic", function(event) {
	
	event.preventDefault();
	
	var form = $(this);
	var url = form[0].action;
	var userId1 = form[0][0].value;
	var userId2 = form[0][1].value;
	var submit = form[0][2].value;
	var child = $(this).children().filter('.dibba1');
	var child2= $(this).children().filter('.dibba1').children().filter('.glyphicon');

	var posting = $.post(url, {
		"userId1" : userId1,
		"userId2" : userId2,
		"submit" : submit
	});
	posting.done(function(response) {
		
		if(submit==="Add Friends"){
			
			console.log('in add friend');
			child.attr('value','Sent Request');
			child.html('<span class="glyphicon glyphicon-user" ></span>Friend Request Sent');
			
			child.attr('disabled','disabled');
			
			
		}
		else if(submit==="Friends")
			{ 
					
					child.attr('value','Add Friends');
					
					child.html('<span class="glyphicon glyphicon-plus"></span>Add Friends');
			}
		
		
		
		
	});

});


function confirmRequest(form,e){

	e.preventDefault();
	
	
		
		
		var url = form[0].action;
	
		var userId1 = form[0][0].value;
		var userId2 = form[0][1].value;
		var submit = 'Confirm';	
		var child = form.children().filter('.dibba1');
		var child2 = form.children().filter('.response');
		
		
		var posting = $.post(url, {
			"userId1" : userId1,
			"userId2" : userId2,
			"submit" : submit
		});
		posting.done(function(response) {
			//console.log($(this).parent());
				child2.hide();
				child.removeClass("btn btn-default active dropdown-toggle dibba1");
				child.addClass("btn btn-default active dibba1");
				child.attr('value','Friends');
				child.removeAttr("data-toggle");
				child.attr('type','submit');
				child.html('<span class="glyphicon glyphicon-ok"></span>Friends');
				
				
				
		
		});
		

}




function declineRequest(form,e){

e.preventDefault();
	

	var url = form[0].action;
	
	var userId1 = form[0][0].value;
	var userId2 = form[0][1].value;
	var submit = 'Decline';	
	var child = form.children().filter('.dibba1');
	var child2 = form.children().filter('.response');
	
	
	var posting = $.post(url, {
		"userId1" : userId1,
		"userId2" : userId2,
		"submit" : submit
	});
	posting.done(function(response) {
		//console.log($(this).parent());
			child2.hide();
			child.removeClass("btn btn-default active dropdown-toggle dibba1");
			child.addClass("btn btn-default active dibba1");
			child.attr('value','Add Friends');
			child.removeAttr("data-toggle");
			child.attr('type','submit');
			child.html('<span class="glyphicon glyphicon-plus"></span>Add Friends');
			
			
			
	
	});
	

}