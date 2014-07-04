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

// 用户相关
function userLogin() {
	var email = $("#inputEmail").val();
	var pass = $("#inputPassword").val();
	// 非空验证
	if (email == "") {
		showErrorMsg("邮箱不能为空！");
		return;
	}
	if (pass == "") {
		showErrorMsg("密码不能为空！");
		return;
	}
	// 验证邮箱格式
	var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		showErrorMsg("您输入的邮箱格式不正确！");
		return;
	}
	var path = $("#contextPath").val();
	// 异步登录
	$.ajax({
	    type: "POST",
	    url: path + "/user/login",
	    data: {
	        email: email,
	        password: pass
	    },
	    dataType: "json",
	    success: function(msg) {
		    if (msg.success) {
			    location.href = path + "/index";
		    }
	    }
	});

}

function userRegist() {
	var email = $.trim($("#inputEmail").val());
	var name = $.trim($("#inputName").val());
	var pass = $.trim($("#inputPassword").val());
	var pass_con = $.trim($("#inputPasswordCon").val());
	var randCode = $("#inputRandCode").val();
	var path = $("#contextPath").val();
	// 非空验证
	if (email == "") {
		showErrorMsg("邮箱不能为空！");
		return;
	}
	if (name == "") {
		showErrorMsg("昵称不能为空！");
		return;
	}
	if (pass == "") {
		showErrorMsg("密码不能为空！");
		return;
	}
	if (pass_con == "") {
		showErrorMsg("确认密码不能为空！");
		return;
	}
	// 验证邮箱格式
	var emailRegExp = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		showErrorMsg("您输入的邮箱格式不正确！");
		return;
	}
	// 验证密码
	if (pass != pass_con) {
		showErrorMsg("密码不一致！");
		return;
	}
	$.ajax({
	    type: "POST",
	    url: path + "/user/regist",
	    data: {
	        email: email,
	        password: pass,
	        name: name,
	        randCode: randCode
	    },
	    dataType: "json",
	    success: function(msg) {
		    if (msg.success) {
			    window.location.href = path + "/index";
		    } else {
			    showErrorMsg(msg.list);
		    }
	    }
	});
}
function showErrorMsg(msg) {
	var msgD = $.globalMessenger().post({
	    message: msg,
	    type: 'error'
	});
}
function showSuccessMsg(msg) {
	var msgD = $.globalMessenger().post({
	    message: msg,
	    type: 'success'
	});
}
