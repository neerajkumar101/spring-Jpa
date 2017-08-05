
var ajaxLike = function(obj){
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

var ajaxShare = function(){
//	debugger;
	$.ajax({
		url: "/share_ajax", 
		type: "POST",
		data: $('#share').val()
	})
	.done(function(data, status){
		console.log("Data: " + data + "\nStatus: " + status);
    });
}
