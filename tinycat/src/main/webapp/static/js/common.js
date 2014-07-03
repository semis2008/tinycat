function getStringLimit(Str,Limit) {
	var returnValue = ''; 
	var byteValLen = 0; 
	for (var i = 0; i < Str.length; i++) { 
    	if (Str.substr(i , 1).match(/[^\x00-\xff]/ig) != null){ 
    		byteValLen += 2; 
    	}else{ 
    		byteValLen += 1;
    	} 
    	if (byteValLen > Limit){
    		returnValue += "...";
    		break; 
    	}	
    	returnValue += Str.substr(i , 1); 
	} 
	return returnValue; 
} 

//用户相关
function userLogin(){
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
	var emailRegExp = new RegExp(
			"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		showErrorMsg("您输入的邮箱格式不正确！");
		return;
	}
	var path = $("#contextPath").val();
	// 异步登录
	$.ajax({
		type : "POST",
		url : path + "/user/login",
		data : {
			email : email,
			password : pass
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success) {
				location.href = path + "/index";  
			}  
		}
	});
	
}

function userRegist() {
	var email = $.trim($("#txtEmailAddressRegist").val());
	var name = $.trim($("#txtNameRegist").val());
	var pass = $.trim($("#txtPasswordRegist").val());
	var pass_con = $.trim($("#txtPasswordCon").val());
	var randImg = $("#txtRandImg").val();
	
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
	var emailRegExp = new RegExp(
			"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	if (!emailRegExp.test(email) || email.indexOf('.') == -1) {
		showErrorMsg("您输入的邮箱格式不正确！");
		return;
	}
	// 验证密码
	if (pass != pass_con) {
		showErrorMsg("密码不一致！");
		return;
	}
	$
			.ajax({
				type : "POST",
				url : "/action/user/regist",
				data : {
					email : email,
					pass : pass,
					name : name,
					randImg : randImg
				},
				dataType : "text",
				success : function(msg) {
					if("randCode_error" == msg) {
						showErrorMsg("验证码错误！");
						return;
					}else if ("email_error" == msg) {
						showErrorMsg("邮箱已经被注册！");
						$('#registModal').modal('hide');
					} else {
						if ("success" == msg) {
							showSuccessMsg("恭喜您，注册成功!");
							$('#registModal').modal('hide');
						} else {
							showErrorMsg("对不起，注册失败:系统正在维护中~");
							$('#registModal').modal('hide');
						}
					}
				}
			});
}

function showErrorMsg(msg) {
	 alert(msg);
}
function showSuccessMsg(msg) {
	alert(msg);
}
