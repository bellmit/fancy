/**
 * caosheng 会员注册页验证
 */
function regist(){
	if(checkForm()){
		$.ajax({
			type : 'POST',
			async : false,
			data : $("#regform").serialize(),
			url : contextPath + '/member/registerajax.html',
			success : function(data) {
				$("#reg").attr("onclick","regist()");
				if (data == '1') {
					$(".l_zcOn").removeClass("g_none");
					$(".l_zcCg").removeClass("g_none");
					setTimeout(function()
						{
							window.location.href = contextPath + "/member/login.html";
						}, 2000);
			
				} else if(data == '2'){
					alert("手机格式错误");
				}else if(data == '3'){
					alert("该用户名已经存在");
				}else if(data == '4'){
					alert("手机号被注册");
				}else if(data == '5'){
					alert("注册失败");
				}else if(data == '6'){
					window.location.href = contextPath + "/member/no_open.html";
				}else if(data == '7'){
					alert("请输入正确的邀请码");
				}else if(data == '8'){
					alert("邀请码已失效，请重新获取");
				}else{
					alert("注册失败");
				}
			},
			error : function(data) {
				$("#reg").attr("onclick","regist()");
				alert("注册失败");
			}
		});
	}
}


function checkForm() {
	var userFlag = true;
	var phoneFlag = true;
	var userName = $.trim($("#userName").val());
	var reg = /^[\u4e00-\u9fa5_a-zA-Z0-9-]+$/;
	if (userName == '') {
		alert("用户名不能为空");
		return false;
	}
	if (!reg.test(userName) || !(userName.length > 5 && userName.length < 17)) {
		alert("用户名只能是6-16位汉字、字母、数字及“-”、“_”组合");
		return false;
	} else {
		$.ajax({
			type : 'POST',
			async : false,
			data : {
				userName : userName
			},
			url : contextPath + '/member/checkUserNameIsExists.html',
			dataType : 'json',
			success : function(data) {
				if (data.flag == 'exists') {
					alert("用户名已存在");
					userFlag = false;
				} else {
					userFlag = true;
				}
			},
			error : function(data) {
				alert("验证用户名失败");
				userFlag = false;
			}
		});
	}
	if (userFlag == false) {
		return false;
	}

	var passWord = $.trim($("#passWord").val());
	if (passWord == '') {
		alert("密码不能为空");
		return false;
	}

	var reg = /^[a-zA-Z0-9]+$/;
	if (!reg.test(passWord) || !(passWord.length > 5 && passWord.length < 21)) {
		alert("密码需是6-20位的字母或数字!");
		return false;
	}
	var phone = $.trim($("#phone").val());
	var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
	if (phone == '') {
		alert("手机号不能为空");
		return false;
	}
	if (!reg.test(phone)) {
		alert("请正确填写您的手机号码格式!");
		return false;
	}

	var validate = $.trim($("#validate").val());
	var reg = /^\S+$/;
	if (!reg.test(validate)) {
		alert("验证码不能为空");
		return false;
	}

	var area = $.trim($("#area").val());
	var reg = /^\S+$/;
	if (!reg.test(area)) {
		alert("所在区域不能为空");
		return false;
	}
	var accede = $("#accede").is(":checked");
	if (!accede) {
		alert("请勾选用户服务协议");
		return false;
	}
	var phoneFlag = true;
	$.ajax({
		type : 'POST',
		async : false,
		data : {
			phone : phone
		},
		url : contextPath + '/member/checkPhoneIsExists.html',
		dataType : 'json',
		success : function(data) {
			if (data.flag == 'exists') {
				alert("手机号已存在");
				phoneFlag = false;
			} else {
				phoneFlag = true;
			}
		},
		error : function(data) {
			alert("验证手机号出错");
			phoneFlag = false;
		}
	});
	if(phoneFlag==false)
	{
		return false;
	}
	var flag = true;
	var validate = $("#validate").val();
	$.ajax({
		type : 'POST',
		async : false,
		data : {
			validate : validate
		},
		url : contextPath + '/member/validatePhone.html?timeStamp=' + new Date().getTime(),
		dataType : 'json',
		success : function(data) {
			if (data == null) {
				alert("验证码错误");
				flag = false;
			} else if (data.result == 'isnotnull') {
				alert("验证码不能为空");
				flag = false;
			} else if (data.result == 'isnotequal') {
				alert("验证码错误");
				flag = false;
			}
		},
		error : function(data) {
			alert("error");
			flag = false;
		}
	});
	if(flag==false)
	{
		return false;	
	}
	var invite = $.trim($("#invite").val());
	if (invite == '') {
		alert("请填写注册邀请码");
		return false;
	}
	$("#reg").removeAttr("onclick");//移除登录事件
	return true;
}

function sure() {
	$(".g_neiHUane").addClass("g_none");
	$(".g_mask").addClass("g_none");
	$("#L_login").css("height", "auto");

	$("#shengidsure").val($("#shengid").val());
	$("#shiidsure").val($("#shiid").val());
	$("#xianidsure").val($("#xianid").val());
	$("#area").val($("#countryname").text());
}
function clearCity() {
	$("#city").html('');
}
function clearCoutry() {
	$("#country").html('');
}
function getCity(self) {
	var areaId = $(self).attr("id");
	var areanme = $("#" + areaId).html();
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : contextPath + "/member/getCity.html",
		dataType : 'json',
		data : {
			areaId : areaId
		},
		success : function(data) {
			var html = "";
			for ( var i = 0; i < data.length; i++) {
				html += "<li onclick='getCountry(this)' id='" + data[i].areaId + "'>" + data[i].areaName + "</li>";
			}
			$("#city").html(html);
			$("#cityname").html(areanme);
			$("#shengid").val(areaId);
			$("#shengname").val(areanme);
		},
		error : function(data) {
			alert("error");
		}
	});
}
function getCountry(self) {
	var areaId = $(self).attr("id");
	var areanme = $("#" + areaId).html();
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : contextPath + "/member/getCity.html",
		dataType : 'json',
		data : {
			areaId : areaId
		},
		success : function(data) {
			var html = "";
			for ( var i = 0; i < data.length; i++) {
				html += "<li class='countryli' >" + data[i].areaName + "<input id='" + data[i].areaId
						+ "' onclick='radioClk(this)' type='radio' name='radio' value='" + data[i].areaName + "' /></li>";
			}
			$("#country").html(html);
			$("#countryname").html($("#shengname").val() + areanme);
			$("#shiid").val(areaId);
			$("#shiname").val(areanme);
			$(".g_tureQued").addClass("g_none");
		}
	});
}
function radioClk(self) {
	var countryid = $(self).attr("id");
	var countryname = $("#" + countryid).val();
	$("#xianid").val(countryid);
	$("#xianname").val(countryname);
	$("#countryname").html($("#shengname").val() + $("#shiname").val() + countryname);
	$(".g_tureQued").removeClass("g_none");
}

$("#btnCode").click(function() {
	var o = this;
	var phone = $("#phone").val();
	var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
	if (!reg.test(phone)) {
		alert("请正确填写您的手机号码!");
	} else {
		var phoneFlag = true;
		$.ajax({
			type : 'POST',
			async : false,
			data : {
				phone : phone
			},
			url : contextPath + '/member/checkPhoneIsExists.html',
			dataType : 'json',
			success : function(data) {
				if (data.flag == 'exists') {
					alert("手机号已存在");
					phoneFlag = false;
				} else {
					phoneFlag = true;
				}
			},
			error : function(data) {
				alert("验证手机号出错");
				phoneFlag = false;
			}
		});
		if (phoneFlag == true) {
			// 异步发送短信
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				async : false,
				data : {
					phone : phone
				},
				url : contextPath + '/member/PhoneSendMsg.html?timeStamp=' + new Date().getTime(),
				dataType : 'json',
				success : function(data) {
					alert("短信已发送，请注意查收！");
					time2(o);
				},
				error : function(data) {
					alert("短信发送失败");
				}
			});
		}
	}

});

var wait2 = 60;
function time2(o) {

	if (wait2 == 0) {
		$(o).prop("disabled", false);
		$(o).val("获取验证码");
		wait2 = 60;
	} else {
		$(o).attr("disabled", "true");
		$(o).val("重新发送(" + wait2 + ")");
		wait2--;
		setTimeout(function() {
			time2(o);
		}, 1000);
	}
}

$(document).ready(function() {
	var bodyHei1 =  $("body,html").height();
	// 点击“会员服务协议”
            $(".l_hyXieyi").click(function(){
                $(".l_mask").removeClass("g_none");
                $(".g_zhuCexie").removeClass("g_none");
                $("#L_login").height(bodyHei1);
                $(".g_mask").height(bodyHei1);
            });

            // 点击会员X号
            $(".g_dianJIewQwu").click(function(){
                $(".l_mask").addClass("g_none");
                $(".g_zhuCexie").addClass("g_none");
                $("#L_login").css("height", "auto");
            });
            
            
	$(".widht").height($(".widht_G").height());
	gao_bottomFin();

	// 点击筛选，蒙版层固定盖度
	var bodyHei1 = $("body,html").height();
	// 点击"请选择所在地址"
	$(".g_tureXuanadree").click(function() {
		$(".g_mask").removeClass("g_none");
		$(".gao_xuanBeiqu").removeClass("g_none");
		$("#L_login").height(bodyHei1);
		$(".g_mask").height(bodyHei1);
	});
	// 点击取消1
	$(".g_siewneuQu").click(function() {
		$(".gao_xuanBeiqu").addClass("g_none");
		$(".g_mask").addClass("g_none");
		$("#L_login").css("height", "auto");
	});

	// 点击北京 区域出现
	$(".gao_xuanBeiqu li").live('click', function() {
		$(".gao_xuanBeiqu").addClass("g_none");
		$(".g_haiDanqu").removeClass("g_none");
	});

	$(".g_BeijingQuy li").live('click', function() {
		if (!$(this).attr("class")) {
			$(".gao_xuanBeiqu").addClass("g_none");
			$(".g_haiDanqu").removeClass("g_none");
		}

	});

	// 点击取消2
	$(".g_haiDanqu .g_tiopUXne img").click(function() {
		$(".g_haiDanqu").addClass("g_none");
		$(".gao_xuanBeiqu").removeClass("g_none");
	});

	// 点击北京西城区 区域出现
	$(".g_haiDanqu li").live('click', function() {
		$(".g_haiDanqu").addClass("g_none");
		$(".g_neiHUane").removeClass("g_none");
	});
	// 点击取消3
	$(".g_neiHUane .g_tiopUXne img").click(function() {
		$(".g_neiHUane").addClass("g_none");
		$(".g_haiDanqu").removeClass("g_none");
	});
	// 点击“会员服务协议”
//	$(".g_zunXunxieyi").click(function() {
//		$(".g_mask").removeClass("g_none");
//		$(".g_zhuCexie").removeClass("g_none");
//		$("#L_login").height(bodyHei1);
//		$(".g_mask").height(bodyHei1);
//	});
	

            

	// 点击会员X号
//	$(".g_dianJIewQwu").click(function() {
//		$(".g_mask").addClass("g_none");
//		$(".g_zhuCexie").addClass("g_none");
//		$("#L_login").css("height", "auto");
//	});
            
});


