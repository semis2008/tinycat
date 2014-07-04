$(window).scroll(function() {
	var t = $(document).scrollTop();
	if (t >= 300) {
		showGoToTop();
	} else {
		hideGoToTop();
	}
});
function goToTop() {
	$('body,html').animate({
		scrollTop: 0
	}, 1000);
}
function showGoToTop() {
	$(".goToTopDiv").show();
}
function hideGoToTop() {
	$(".goToTopDiv").hide();
}
