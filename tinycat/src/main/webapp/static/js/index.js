/*
 * ! Start Bootstrap - Grayscale Bootstrap Theme (http://startbootstrap.com)
 * Code licensed under the Apache License v2.0. For details, see
 * http://www.apache.org/licenses/LICENSE-2.0.
 */

// jQuery to collapse the navbar on scroll
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
	
});

// Closes the Responsive Menu on Menu Item Click
$('.navbar-collapse ul li a').click(function() {
	$('.navbar-toggle:visible').click();
});

function randomBG() {
	var introIndex = getRandomInt(0, 4);
	$(".intro").addClass("bg-intro-0"+introIndex);
	var midIndex = getRandomInt(0, 4);
	$(".mid-section").addClass("mid-bg-0"+midIndex);
	
}

function getRandomInt(start,end) {
	var rn=Math.random()*(end-start+1)-1;
	var num=start + Math.ceil(rn);
	return num;
}