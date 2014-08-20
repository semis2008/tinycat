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
	
});


function registBtnAction() {
	$("#login-row").hide();
	$("#regist-row").hide();
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
	var midIndex = getRandomInt(0, 1);
	$("#login").addClass("login-bg-0"+midIndex);
	
}

function getRandomInt(start,end) {
	var rn=Math.random()*(end-start+1)-1;
	var num=start + Math.ceil(rn);
	return num;
}

