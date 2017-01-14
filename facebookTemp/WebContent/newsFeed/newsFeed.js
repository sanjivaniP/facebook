
function handleKeyPress(e,form,num){

	var key=e.keyCode || e.which;
if (key==13){//enter is pressed
	submitComment(form,e,num);
 
}
}

function like(form,e,num){
	
	e.preventDefault();
/*	var form = $("#myForm");
*/
	var url = 'http://localhost:8080/facebookTemp/insertLike';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var posting = $.post(url, {
		"index" : num
	});
	posting.done(function(response) {
	//	alert($("#youLike"+num).text());

		//console.log($(this).parent());
		$("#l"+num).html('');
		$("#l"+num).html('Unlike');
		$("#l"+num).attr('onclick','unlike(this.form,event,\''+num+'\')');
		$("#youLike"+num).show();
	});

}
function unlike(form,e,num){
	
	e.preventDefault();
/*	var form = $("#myForm");
*/
	var url = 'http://localhost:8080/facebookTemp/deleteLike';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var posting = $.post(url, {
		"index" : num
	});
	posting.done(function(response) {
		//console.log($(this).parent());
		$("#l"+num).html('');
		$("#l"+num).html('Like');
		$("#l"+num).attr('onclick','like(this.form,event,\''+num+'\')');
		$("#youLike"+num).hide();

	});

}

function focusOnCommentBox(commentBox){
	console.log("focus pe!!");
	$("#"+commentBox).focus();
	
	
}
function commentLike(form,e,num){
	
	e.preventDefault();
/*	var form = $("#myForm");
*/
	var url = 'http://localhost:8080/facebookTemp/insertLikeComment';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var posting = $.post(url, {
		"commentIndex" : num
	});
	posting.done(function(response) {
		//console.log($(this).parent());
/*		alert("success");
 * 
*/	//	$("#commentYouDontLike"+num).hide();
//$("#commentYouLike"+num).show();
		$("#cl"+num).html('Unlike');
		$("#cl"+num).attr('onclick','commentUnlike(this.form,event,\''+num+'\')');
	});

}
function submitComment(form,e,num){
	
	var url = 'http://localhost:8080/facebookTemp/submitComment';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var desc=$("#desc"+num).val();
	var posting = $.post(url, {
		"index" : num,
		"description":desc
	});
	posting.done(function(response) {
		
		document.getElementById("myForm").submit();
	});

}
function commentUnlike(form,e,num){
	
	e.preventDefault();
/*	var form = $("#myForm");
*/
	var url = 'http://localhost:8080/facebookTemp/deleteLikeComment';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var posting = $.post(url, {
		"commentIndex" : num
	});
	posting.done(function(response) {
		//console.log($(this).parent());
/*		alert("success");
*/
		$("#cl"+num).html('Like');
		$("#cl"+num).attr('onclick','commentLike(this.form,event,\''+num+'\')');
	});

}
