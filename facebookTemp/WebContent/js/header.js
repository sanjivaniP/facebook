$(document).ready(function() {
					var curFriend = 0;
					var listLength = 0;

					$("#typeahead").keyup(function(event) {//keypress doesnot give input

										if ($("#typeahead").val().length > 1) {
											if (event.keyCode != 40//up
													&& event.keyCode != 38) {//down
												event.preventDefault();

												var text = $(this).val();
												$("#auto").children().remove();//remove the entire result..as new results will be loaded
												curFriend = 0;
												$.ajax(
																{
																	url : "getSearch",
																	data : {
																		searchText : text
																	},

																	context : document.body
																}).done(function(data) {

																	var list = "";
																	var counter = 0;

																	data.searchResult1.forEach(function(
																					entry) {
																				listLength = data.searchResult1.length;
																				list += "<div id='friend"
																						+ counter
																						+ "' class='autoFriends'>"
																						+ entry
																						+ "</div>"
																						+ "<br>";
																				counter++;
																			});
																	$("#auto").html(list);
																	$("#typeahead").focus();});
											}
										} else {
											$("#auto").children().remove();
											curFriend = 0;

										}

									});

					
					$("#typeahead").keydown(function(event) {

										if (event.keyCode == 40
												&& curFriend < listLength) {//down

											curFriend++;
										}
										if (event.keyCode == 38//up
												&& curFriend > 0) {
											curFriend--;
										}

										$(".autoFriends").css("background-color", "white");
										var curDiv = curFriend - 1;
										$("#friend" + curDiv).css("background-color", "#4D70B8");//to highlight the selected div

										if (event.keyCode == 13//enter button and selected a user
												&& curFriend != 0) {

											window.location.href = $("#friend" + curDiv).children().attr("href");
										} else if (event.keyCode == 13)
											$("#searchForm").submit();

										var offset = curDiv * 50;
										$("#auto").animate({
											scrollTop : offset
										}, 10);

									});

				});

function confirmRequest(form, e) {

	e.preventDefault();

	var url = form[0].action;

	var userId1 = form[0][0].value;
	var userId2 = form[0][1].value;
	var submit = 'Confirm';
	var child = form.children().filter('.b1');//('#b1')
	var child2 = form.children().filter('.accepted');

	var posting = $.post(url, {///url+?userId1=variable
		"userId1" : userId1,
		"userId2" : userId2,
		"submit" : submit
	});
	posting.done(function(response) {

		child.hide();
		child2.show();

	});

}

function declineRequest(form, e) {

	e.preventDefault();

	var url = form[0].action;

	var userId1 = form[0][0].value;
	var userId2 = form[0][1].value;
	var submit = 'Decline';
	var child = form.children().filter('.b1');
	var child2 = form.children().filter('.rejected');

	var posting = $.post(url, {
		"userId1" : userId1,
		"userId2" : userId2,
		"submit" : submit
	});
	posting.done(function(response) {

		child.hide();
		child2.show();

	});

}