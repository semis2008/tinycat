(function() {

	var $pages = $('.page-set'), index = 0, loading = false;

	var firstTime = "0";// 第一次请求的时间

	function addPage() {
		var path = $("#contextPath").val();
		$.ajax({
		    url: path + "/blog/ajax/list",
			type : "POST",
		    data: {
		        index: index,
		        firstTime: firstTime
		    },
		    cache:false,  
		    dataType: "json",
		    success: function(date) {
			    var diaryList = date.list;
			    if (date.time)
				    firstTime = date.time;
			    var diaryHtml = "";
			    $.each(diaryList, function(i, val) {
			    	diaryHtml += "<section id=\"diary_" + val.id + "\" class=\"diarySec\"><div><img width=\"60px\" height=\"60px\" alt=\"\" class=\"img-polaroid left\" src=\"" + path + "/static" + val.author_photo + "\" />" + "<blockquote><h3 class=\"muted\"> " + val.title
				            + " <em title=\"回复/阅读数\">[" + val.reply_num + "/" + val.read_num + "]</em>" + "</h3><small>";
				    var tags = val.tags.split("_");
				    for ( var i = 0; i < tags.length; i++) {
					    diaryHtml += "<label class=\"label\"><i class=\"icon-tag\"></i> " + tags[i] + "</label> ";
				    }
				    var content = getStringLimit(val.content, 250);
				    diaryHtml += "<em><a class=\"text-info\" title=\"查看他发布的所有博文\" href=\"#\">" + val.author_name + "</a>" + "发布于 " + val.publish_time + "</em> </small> </blockquote></div><div> <p>" + content
				            + "</p></div><div><a class=\"button\" href=\"#\">阅读全文 <i class=\"icon-chevron-down\"></i> </a>" + "</div></section>";
				    index++;
			    });
			    $pages.append(diaryHtml);
		    }
		});

	}
	;

	addPage();

	$(window).scroll(function() {
		if (loading) {
			$("#loadingDIV").show();
		}else if ($(window).scrollTop() > $(document).height() - $(window).height() - 100) {
			loading = true;
			setTimeout(function() {
				addPage();
				loading = false;
				$("#loadingDIV").hide();
			}, 250);
		}
		
		var t = $(document).scrollTop();
		if (t >= 300) {
			showGoToTop();
		} else {
			hideGoToTop();
		}
	});

	/*
	 * 
	 * This is where the plugin is initialized.
	 * 
	 */

	$(window).sausage();
	 

}());


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

