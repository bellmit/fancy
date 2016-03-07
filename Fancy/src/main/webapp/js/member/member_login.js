/**操圣登录js***/


function login(){
	var userName = $.trim($("#userName").val());
	var passWord = $.trim($("#passWord").val());
	if (userName =='') {
		hint("请输入用户名");
		return;
	} 
	if(passWord=='')
	{	
		hint("请输入密码");
		return;
	}
	var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
	if (!reg.test(passWord) || !(passWord.length > 5 && passWord.length < 21)) {
		hint("密码格式必须是6-20位的字母或数字!");
		return;
	}

	$.ajax({
		type : 'POST',
		url:contextPath + '/member/userLogin.html',
		data:$("#loginForm").serialize(),
		success:function(msg)
		{
			if(msg=="1")
			{
				window.location.href=contextPath+"/weproductsearch/productview.html";
			}else if(msg=="2")
			{
				hint("用户名或密码错误");
			}else if(msg=="3")
			{
				hint("账号不存在");
			}
			else if(msg=="4")
			{
				window.location.href = contextPath + "/member/no_open.html";
			}else if(msg.indexOf("/")==0){
				window.location.href=contextPath+msg;
			}
		}
	});
	}


	$("#register").click(function() {
		location.href = 'register.html';
	});


	
	/*检查中文和英文个数***/
	function WidthCheck(str, maxLen) {
		var w = 0;
		for (var i = 0; i < str.value.length; i++) {
			var c = str.value.charCodeAt(i);
			if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
				w++;
			} else {
				w += 2;
			}
			if (w > maxLen) {
				str.value = str.value.substr(0, i);
				break;
			}
		}
	}
	
	/**登录错误提示**/
	function hint(val)
	{
		$(".l_zhbcz").text(val);
		$(".l_dltc").removeClass("g_none");
		$(".l_zhbcz").removeClass("g_none");
		
		setTimeout(function()
		{
			$(".l_dltc").addClass("g_none");
			$(".l_zhbcz").addClass("g_none");
		}, 2000);
	}