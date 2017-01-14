$(document).ready(function() {
					var curFriend = 0;
					var listLength = 0;

					$("#typeaheadMessage").keyup(function(event) {

										if ($("#typeaheadMessage").val().length > 1) {
											if (event.keyCode != 40
													&& event.keyCode != 38) {
												event.preventDefault();

												var text = $(this).val();
												$("#autoMessage").children().remove();
												curFriend = 0;
												$.ajax(
																{
																	url : "getSearchForMessage",
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
																				list += "<div id='friend1"
																						+ counter
																						+ "' class='autoFriends1'>"
																						+ entry
																						+ "</div>"
																						+ "<br>";
																				counter++;
																			});
																	$("#autoMessage").html(list);
																	$(
																			"#autoMessage, #typeaheadMessage").focus();

																});
											}
										} else {
											$("#autoMessage").children().remove();
											curFriend = 0;

										}

									});

					
					$("#typeaheadMessage").keydown(function(event) {

										if (event.keyCode == 40
												&& curFriend < listLength) {

											curFriend++;
										}
										if (event.keyCode == 38
												&& curFriend > 0) {
											curFriend--;
										}

										$(".autoFriends1").css("background-color", "white");
										var curDiv = curFriend - 1;
										$("#friend1" + curDiv).css("background-color", "#4D70B8");

										if (event.keyCode == 13
												&& curFriend != 0) {
											event.preventDefault();
												console.log("enter Mara");
											var id = $("#friend1"+curDiv).children().filter('.profileId').attr('value');
											var name=$("#friend1" + curDiv).children().text();
											console.log(id+" "+name+" lalalala "+curFriend);
											$(this).val(name);
											$(this).attr('disabled','disabled');
											$("#receiver").val(id);
											$("#autoMessage").children().remove();
											curFriend = 0;
										} 
										
										var offset = curDiv * 50;
										$("#autoMessage").animate({
											scrollTop : offset
										}, 10);

									});

				});
