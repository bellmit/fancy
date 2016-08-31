$.validator.setDefaults({
	errorElement : "span",
	// 错误事实添加的CSS 类
	highlight : function(input) {
		$(input).addClass("inpError");
	},
	// 完成时候移出的类
	unhighlight : function(input) {
		$(input).removeClass("inpError");
	},
	success : function(element) {
		element.parent().remove();
	},
	errorPlacement : function(error, element) {
		var div = $("<div class='error'></div>");
		error.appendTo(div);
		element.parent().append(div);
	}
});