
$(document).ready( function(){
	$('form').submit(function(e){
		e.preventDefault();
	});
	$('input#postText').val('');
});

var addComment = function(obj){
	var formId = '#' + $(obj).attr('id');
	var inputFields = obj.elements;
	
	$.ajax({
		url: "/comment/add_comment",
		type: "POST",
		data: {comment: $(formId).serialize()},
		dataType: 'json',
		cache: false,
		success: function(data, status){
			$(formId).val('');
			console.log("comment object: " + data.commentData);
			/*$('.update-comment-div').addClass("well");*/
			$('.post-comment').val('');
			$('.update-comment-div_' + data.postId).append(
				'<div class="col-md-12" >'+
					'<div class="col-sm-12 well well-sm" >'+
						'<div>'+
							data.userFullName + ":- " + data.commentText +
						'</div>'+
					'</div>'+
				'</div>'
			);
			
			console.log(data.userFullName + ":- " + data.commentText)
			console.log("Data: " + JSON.stringify(data.commentText));
		},
		error: function(data, status){
			console.log("error: " +  JSON.stringify(data) + " and status code is : " + status.statusCode);
		}
	});
}

function ajaxLike(obj){
    $.ajax({
		url: "/ajax/like_ajax", 
		type: "POST",
		data: { json : $(obj).val()},
		dataType: 'json',
	    cache: false,
		success: function(data, status){
			$('#div_like_' + data.requestPostId ).html(
					' <button id="like_"' + data.requestPostId + '" class="btn btn-primary" value="'+ data.requestPostId + '"'+ 
						'onClick="ajaxLike(this)"> Like <span style="background-color: red; ">'+ data.requestStatus + '</span></button>');
			
			console.log("Data: " + JSON.stringify(data.requestStatus) + "\nStatus: " + status);
		},
		error: function(data, status){
			console.log("error: " +  JSON.stringify(data.requestStatus) + " and status code is : " + status.statusCode);
		}
    });
}
/*
 * ajax to hit share on a post
 */
function shareOnPost(postId, userId, userFirstName, userLastName, postText, obj){
	$("#share-post-user-name").text(userFirstName + ' ' + userLastName + ': ');
	$("#sharepostdata").text(postText);
	postid=postId;
	$("#sharedPostId").val(postId);
	$("#sharingUserId").val(userId);
}

function ajaxShare(obj){
	alert($('#' + $(obj).attr('id')).serialize());
	
	var formId = '#' + $(obj).attr('id');
	var inputFields = obj.elements;
	
	$.ajax({
		url: "/ajax/share_ajax",
		type: "POST",
		data: {postedData: $(formId).serialize()},
		dataType: 'json',
		cache: false,
		success: function(data, status){
			console.log("Data: " + JSON.stringify(data.shareStatus) + "\nStatus: " + status);
		},
		error: function(data, status){
			console.log("error: " +  JSON.stringify(data.requestStatus) + " and status code is : " + status.statusCode);
		}
	});
}

function ajaxDelete(obj){
	if(confirm("are you sure you want to delete this post.")){
		$.ajax({
			url: "/ajax/delete_ajax", 
			type: "POST",
			data: { postId : $(obj).val()},
			dataType: 'json',
		    cache: false,
			success: function(data, status){
				$(obj).parent().parent().parent().parent().parent().parent().parent().empty();
				$(obj).parent().parent().parent().parent().parent().parent().parent().remove();
				console.log("Data: " + JSON.stringify(data.deleteRequestStatus) + "\nStatus: " + status);
			},
			error: function(data, status){
				console.log("error: " +  JSON.stringify(data.deleteRequestStatus) + " and status code is : " + status.statusCode);
			}
	    });
	}
}


function friendRequest(obj){
	alert("id: " + $(obj).val());
	$.ajax({
		url: "/ajax/friendRequest", 
		type: "POST",
		data: { requestUserId : $(obj).val()},
		dataType: 'json',
	    cache: false,
		success: function(data, status){
			alert("Friend Request Sent");
			console.log("Data: " + JSON.stringify(data.sentFriendRequestStatus) + "\nStatus: " + status);
		},
		error: function(data, status){
			console.log("error: " +  JSON.stringify(data.sentFriendRequestStatus) + " and status code is : " + status.statusCode);
		}
    });
}
