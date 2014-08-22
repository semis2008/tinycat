var path = $("#contextPath").val();
$(window).scroll(function() {
	if ($(".navbar").offset().top > 50) {
		$(".navbar-fixed-top").addClass("top-nav-collapse");
		$('a.page-scroll').removeClass("menu");
	} else {
		$(".navbar-fixed-top").removeClass("top-nav-collapse");
		$('a.page-scroll').addClass("menu");
	}
});

// jQuery for page scrolling feature - requires jQuery Easing plugin
$(function() {
	$('a.page-scroll').bind('click', function(event) {
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop: $($anchor.attr('href')).offset().top
		}, 1500, 'easeInOutExpo');
		event.preventDefault();
	});
	//随机背景
	randomBG();
	//注册按钮事件
	registBtnAction();
	
	$('.tip').tooltip('hide');
});


function registBtnAction() {
	$("#login-row").hide();
	$("#regist-row").hide();
	$("#show-edit-name").hide();
	$(".edit-name").hide();
	$("#new-room-div").hide();
	//登陆页展示
	$("#show-login-btn").click(function(){
		$("#login-info-row").fadeOut(500,function(){
			$("#login-row").fadeIn(500);	
		});
	});
	//登陆页展示
	$("#show-regist-btn").click(function(){
		$("#login-info-row").fadeOut(500,function(){
			$("#regist-row").fadeIn(500);	
		});
	});
	//信息页展示
	$("#show-info-btn-from-login").click(function(){
		$("#login-row").fadeOut(500,function(){
			$("#login-info-row").fadeIn(500);	
		});
	});
	//信息页展示
	$("#show-info-btn-from-regist").click(function(){
		$("#regist-row").fadeOut(500,function(){
			$("#login-info-row").fadeIn(500);	
		});
	});
	//修改头像
	$("#change_photo").click(function(){
		changePhoto();
	});
	//显示修改昵称
	$("#change_name").click(function(){
		toggleChangeName();
	});
	//修改昵称
	$("#new-name-btn").click(function(){
		changeName();
	});
	//显示添加房间
	$(".addRoom").click(function(){
		 $("#new-room-div").fadeToggle("slow");
	});
	//添加房间
	$("#add-room-btn").click(function(){
		addRoom();
	});
	
	
}

// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
	$('.navbar-toggle:visible').click();
});

function randomBG() {
	var introIndex = getRandomInt(0, 4);
	$(".intro").addClass("bg-intro-0"+introIndex);
	var midIndex = getRandomInt(0, 4);
	$(".mid-section").addClass("mid-bg-0"+midIndex);
	var midIndex = getRandomInt(0, 2);
	$("#login").addClass("login-bg-0"+midIndex);
	
}

function changePhoto() {
	var userId = $("#loginUserId").val();
	if(userId==-1) 
		return;
	
	
	$.ajax({
	    type: "POST",
	    url: path + "/user/changephoto",
	    data: {
	        userId: userId
	    },
	    dataType: "json",
	    success: function(msg) {
		    if (msg.success) {
		    	var newPhoto = msg.list;
		    	var headPath = $("#headPath").val();
		    	$(".head_photo_20").attr("src",headPath+"/"+newPhoto+".jpg");
		    	$(".head_photo_100").attr("src",headPath+"/"+newPhoto+".jpg");
		    }
	    }
	});
}

function addRoom() {
	var userId = $("#loginUserId").val();
	if(userId==-1) {
		location.href = path+"/index#login";
		return;
	}
	var roomType = $("#add-room-type").val();
	var roomName = $("#add-room-name").val();
	var password = $("#add-room-password").val();
	$.ajax({
	    type: "POST",
	    url: path + "/addRoom",
	    data: {
	        userId: userId,
	        roomType:roomType,
	        roomName:roomName,
	        password:password
	    },
	    dataType: "json",
	    success: function(msg) {
		    if (msg.success) {
		    	//更新聊天窗口信息
		    	
		    }else {
		    	alert(msg.list);
		    }
	    }
	});
}

function getRandomInt(start,end) {
	var rn=Math.random()*(end-start+1)-1;
	var num=start + Math.ceil(rn);
	return num;
}

function toggleChangeName() {
	$("#hide-edit-name").toggle();
	$("#show-edit-name").toggle();
	 $(".edit-name").slideToggle("slow");
}

function changeName() {
	var userId = $("#loginUserId").val();
	if(userId==-1) 
		return;
	var newName = $.trim($("#new-name-text").val());
	if(newName=="")
		return;
	$.ajax({
	    type: "POST",
	    url: path + "/user/changename",
	    data: {
	        userId: userId,
	        name: newName
	    },
	    dataType: "json",
	    success: function(msg) {
		    if (msg.success) {
		    	$(".InfoUserName").text(newName);
		    	$("#head-user-name").text(newName);
		    	$("#new-name-text").val("");
		    }
	    }
	});
}
