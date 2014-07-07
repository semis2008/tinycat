function sendMsg(type) {
	var msg = $("#sendMsgText").val();
	var path = $("#contextPath").val();
	var headPath = $("#headPath").val();
	var headPhoto = "";
	var userName = "";
	var userId = "";
	var timeNow = new Date().format('hh:mm:ss');
	var chatUl = $("#chat_content");
	
	if(msg=="")
		return;
	
	if (type == 0) {// guest
		userName = "Guest";
		headPhoto = headPath + "/guest.jpg";
	} else {
		userName = $("#loginUserName").val();
		userId = $("#loginUserId").val();
		var userPhoto = $("#loginUserPhoto").val();
		headPhoto = headPath + "/" + userPhoto + ".jpg";
	}
	var msgHtml = "<li><div class=\"media\"><a href=\"javascript:void(0)\" class=\"pull-right\"> <img class=\"media-object img-circle\" style=\"width: 44px; height: 44px;\" " + "src=\"" + headPhoto + "\"></a><div class=\"media-body\">	<blockquote class=\"pull-right\">" + "<p class=\"user_msg\">" + msg
	        + "</p><small>" + userName + " <em>" + timeNow + "</em></small></blockquote></div></div></li>";
	// 禁用按钮
	$("#sendBtn").attr("disabled", "disabled");
	chatUl.append(msgHtml);
	$("#sendMsgText").val("");
	$.ajax({
	    type: "POST",
	    url: path + "/chat/answer",
	    data: {
	        msg: msg,
	        userId: userId
	    },
	    dataType: "json",
	    success: function(msg) {
		    if (msg.success) {
			    var backTimeNow = new Date().format('hh:mm:ss');
			    var tinyCatPhoto = headPath + "/tinycat.jpg";
			    var backMsgHtml = "<li><div class=\"media\"><a href=\"javascript:void(0)\" class=\"pull-left\"> <img class=\"media-object img-polaroid\" style=\"width: 32px; height: 32px;\" " + "src=\"" + tinyCatPhoto + "\"></a><div class=\"media-body\">	<blockquote class=\"pull-left\">"
			            + "<p class=\"\">" + msg.list + "</p><small> TinyCat<em>" + backTimeNow + "</em></small></blockquote></div></div></li>";
			    chatUl.append(backMsgHtml);
			    $("#chat_window").scrollTop(500000); //滚动到底部，或许有更好的写法...
			    $("#sendBtn").removeAttr("disabled");
		    } else {
			    showErrorMsg(msg.list);
			    $("#sendBtn").removeAttr("disabled");
		    }
	    }
	});

}
