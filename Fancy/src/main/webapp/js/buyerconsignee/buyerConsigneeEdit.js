$(function(){
	// 保存新增收货地址信息
	$("#saveaddress").click(function(){
		if($("#consigneer").val()==''||$("#consigneer").val()=='请填写您的称呼'){
			alert("请填写正确的收货人！");
			return;
		}
		if($("#telephone").val()==''||$("#telephone").val()=='请填写您的联系电话'){
			alert("请填写正确的手机号！");
			return;
		}
		var mb = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
		if (!mb.test($("#telephone").val())) {
			alert("输入的手机号码不正确！请重新输入！");
			$("#telephone").focus();
			return;
		}
		if($("#consigneeaddress").val()==''||$("#consigneeaddress").val()=='请填写您的详细地址'){
			alert("请填写正确的详细地址！");
			return;
		}
		if($("#consigneeaddress").length>100){
			alert("输入字符超过最大限制100字符！请重新输入");
			return;
		}
		if(confirm("确定修改收货地址？")){
			if ($("#checkbox").is(":checked")){
				$("#isdefault").val("1"); 
			}
			$("#Form").attr("action",contextPath+"/buyerconsignee/SaveEditConsignee.html");
			$("#Form").attr("method", "post");
			$("#Form").submit();
		}
	});
	
	if ($("#operate").val() != null && $("#operate").val() != "") {
		if ($("#operate").val() == 'success') {
			alert("保存成功！");
			window.open(contextPath + "/buyerconsignee/View.html", '_self');
		} else if ($("#operate").val() == 'fail') {
			alert("保存失败！请联系系统管理员！");
			window.open(contextPath + "/buyerconsignee/Edit.html", '_self');
		}
	}
}
)	