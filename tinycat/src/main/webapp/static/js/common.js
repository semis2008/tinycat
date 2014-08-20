Date.prototype.format = function(format) {
	var o = {
	    "M+": this.getMonth() + 1, // month
	    "d+": this.getDate(), // day
	    "h+": this.getHours(), // hour
	    "m+": this.getMinutes(), // minute
	    "s+": this.getSeconds(), // second
	    "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
	    "S": this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

function getStringLimit(Str, Limit) {
	var returnValue = '';
	var byteValLen = 0;
	for ( var i = 0; i < Str.length; i++) {
		if (Str.substr(i, 1).match(/[^\x00-\xff]/ig) != null) {
			byteValLen += 2;
		} else {
			byteValLen += 1;
		}
		if (byteValLen > Limit) {
			returnValue += "...";
			break;
		}
		returnValue += Str.substr(i, 1);
	}
	return returnValue;
}

function check_login() {
	var email = $("#login_email").val();
	var password = $("#login_password").val();
	var correct = 1;
	if($.trim(email)==""||$.trim(password)=="") {
		correct=0;
	}
	// 验证邮箱格式
	var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		correct=0;
	}
    if (correct==1) {
    	$("#lockbtn_login").animate({ left: '260px' , duration: 'slow'});;
    	$("#loginbtn").animate({ left: '0' , duration: 'slow'});;
    }
}

function check_regist() {
	var email = $("#regist_email").val();
	var username = $("#regist_username").val();
	var password = $("#regist_password").val();
	var correct = 1;
	if($.trim(email)==""||$.trim(password)==""||$.trim(username)=="") {
		correct=0;
	}
	// 验证邮箱格式
	var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		correct=0;
	}
    if (correct==1) {
    	$("#lockbtn_regist").animate({ left: '260px' , duration: 'slow'});;
    	$("#registbtn").animate({ left: '0' , duration: 'slow'});;
    }
}


$("#loginbtn").click(function(){
	$('#loading_login').removeClass('hidden');
	var email = $.trim($("#login_email").val());
	var password = $.trim($("#login_password").val());
	var path = $("#contextPath").val();
	
	if($.trim(email)==""||$.trim(password)=="") {
		showLoginErrorTip("登陆项不能为空");
	}
	// 验证邮箱格式
	var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		showLoginErrorTip("邮箱格式不正确");
	}
	// 异步登录
	$.ajax({
	    type: "POST",
	    url: path + "/user/login",
	    data: {
	        email: email,
	        password: password
	    },
	    dataType: "json",
	    success: function(msg) {
	    	$('#loading_login').addClass('hidden');
		    if (msg.success) {
			    location.href = path + "/index";
		    }else{
		    	showLoginErrorTip(msg.list);
		    }
	    }
	});
});

$("#registbtn").click(function(){
	$('#loading_regist').removeClass('hidden');
	var email = $("#regist_email").val();
	var username = $("#regist_username").val();
	var password = $("#regist_password").val();
	var path = $("#contextPath").val();
	
	if($.trim(email)==""||$.trim(password)==""||$.trim(username)=="") {
		showRegistErrorTip("注册项不能为空");
	}
	// 验证邮箱格式
	var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		showRegistErrorTip("邮箱格式不正确");
	}
	
	$.ajax({
	    type: "POST",
	    url: path + "/user/regist",
	    data: {
	        email: email,
	        password: password,
	        name: username
	    },
	    dataType: "json",
	    success: function(msg) {
	    	$('#loading_regist').addClass('hidden');
		    if (msg.success) {
			    window.location.href = path + "/index";
		    } else {
		    	showRegistErrorTip(msg.list);
		    }
	    }
	});
});

function showLoginErrorTip(msg) {
	$('#login-error-tip').removeClass('hidden');
	$("#login-error-tip span").text(msg.list);
}

function showRegistErrorTip(msg) {
	$('#regist-error-tip').removeClass('hidden');
	$("#regist-error-tip span").text(msg.list);
}
 