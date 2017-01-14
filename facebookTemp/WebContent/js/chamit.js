
$(document).on("submit", ".chamit", function(event) {
	event.preventDefault();
	
	var form = $(this);
	var url = form[0].action;
	var userId1 = form[0][0].value;
	var userId2 = form[0][1].value;
	var submit = form[0][3].value;
	var child = $(this).children().filter('.dibba');

	/**
	 * Post reply
	 */
	var posting = $.post(url, {
		"userId1" : userId1,
		"userId2" : userId2,
		"submit" : submit
	});
	posting.done(function(response) {
		//console.log($(this).parent());
		child.attr('value','Sent Request');
	});

});