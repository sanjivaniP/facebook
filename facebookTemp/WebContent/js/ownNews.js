function handleKeyPress(e,form,num){

	var key=e.keyCode || e.which;
if (key==13){
	submitComment(form,e,num);
/* document.getElementById("myForm").submit();
 */ 
}
}
function handleKeyPressOthersWall(e,form,num, id){
	var key=e.keyCode || e.which;
if (key==13){
	submitOthersComment(form,e,num,id);
/* document.getElementById("myForm").submit();
 */ 
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
		//console.log($(this).parent());
		$("#l"+num).html('');
		$("#l"+num).html('Unlike');
		$("#l"+num).attr('onclick','unlike(this.form,event,\''+num+'\')');
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
*/		$("#cl"+num).html('');
		$("#cl"+num).html('Unlike');
		$("#cl"+num).attr('onclick','commentUnlike(this.form,event,\''+num+'\')');
	});

}
function submitComment(form,e,num){
	
	//e.preventDefault();
	console.log('submit pe!!');
/*	var form = $("#myForm");
*/
	var url = 'http://localhost:8080/facebookTemp/submitOwnComment';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var desc=$("#desc"+num).val();
	console.log(desc);
	var posting = $.post(url, {
		"index" : num,
		"description":desc
	});
	posting.done(function(response) {
		document.getElementById("myForm").submit();
		
	});

}
function deletePost(form,num){
	
	var url = 'http://localhost:8080/facebookTemp/deletePost';
	/*	var abc=form[0][0];*/
		var posting = $.post(url, {
			"index" : num
		});

	posting.done(function(response) {
		document.getElementById("myForm").submit();
		
	});

	}
function deleteCommentOnOther(form,num,id){
	
	var url = 'http://localhost:8080/facebookTemp/deleteComment';
	/*	var abc=form[0][0];*/
		var posting = $.post(url, {
			"commentIndex" : num,
			"profileId":id
		});

	posting.done(function(response) {
		document.getElementById("myForm").action="profileTempAction_hype";	
		document.getElementById("myForm").submit();
		
	});

	}

function deletePostOthersWall(form,num,id){
	
	var url = 'http://localhost:8080/facebookTemp/deletePost';
	/*	var abc=form[0][0];*/
		var posting = $.post(url, {
			"index" : num,
			"profileId":id
		});

	posting.done(function(response) {
		document.getElementById("myForm").action="profileTempAction_hype";		
		document.getElementById("myForm").submit();
		
	});

	}


	function deleteComment(form,num){
		var url = 'http://localhost:8080/facebookTemp/deleteComment';
		/*	var abc=form[0][0];*/
		/*	alert(url);
		*/	var posting = $.post(url, {
				"commentIndex" : num
			});
		posting.done(function(response) {
			document.getElementById("myForm").submit();
			
		});
		}



function submitOthersComment(form,e,num,id){
	
	//e.preventDefault();
/*	alert(id);*/
/*	var form = $("#myForm");
*/
	var url = 'http://localhost:8080/facebookTemp/submitOwnCommentOnHyperLink';
/*	var abc=form[0][0];*/
/*	alert(url);
*/	var desc=$("#desc"+num).val();
	console.log(desc);
	var posting = $.post(url, {
		"index" : num,
		"description":desc,
		"profileId":id
	});
	posting.done(function(response) {
		
		document.getElementById("myForm").action="profileTempAction_hype";
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
		$("#cl"+num).html('');
		$("#cl"+num).html('Like');
		$("#cl"+num).attr('onclick','commentLike(this.form,event,\''+num+'\')');
	});

}
