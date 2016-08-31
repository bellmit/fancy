/*
 * jQuery validation 验证类型扩展
 *
 * 扩展的验证类型：用户名，邮政编码，大陆身份证号码，大陆手机号码,电话号码
 * 
 * 2010-06-17 by zhsd
 */
//3.验证9.99
jQuery.validator.addMethod("price", function(value) {
	return /^[1-9]*[.]*[0-9][0-9]?$/.test(value);
});

// 验证码格式验证
jQuery.validator.addMethod("validateCode", function(value, element) {
	var validateCode = /^[0-9a-z]{4}$/;
	return this.optional(element) || (validateCode.test(value));
}, "验证码格式不正确！");

// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {
	var zip = /^[0-9]{6}$/;
	return this.optional(element) || (zip.test(value));
}, "请正确填写您的邮政编码!");






// 邮箱验证
jQuery.validator.addMethod("isEmail", function(value, element) {
	var email = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	return this.optional(element) || email.test(true);
}, "邮箱格式不正确");

// 身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
	var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;
	return this.optional(element) || (idCard.test(value));
}, "请输入正确的身份证号码!");

// 判断注册名是否合法 英文+数字
jQuery.validator.addMethod("isValidUserName", function(value, element) {
	var idCard = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,30}$/;
	return this.optional(element) || (idCard.test(value));
}, "请输入合法的用户名(字母+数字)!");

// 验证登录用户名 数字或者英文字母组合
jQuery.validator.addMethod("isLoginName", function(value, element) {
	var Psw = /^[a-zA-Z]{1}[a-zA-Z\d]*$/;
	return this.optional(element) || Psw.test(value);
}, "请输入合法的用户名(英文字母开头)!");

// 用户名字符验证
jQuery.validator.addMethod("userName", function(value, element) {
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "用户名只能包括中文字、英文字母、数字和下划线!");

// 手机号码验证
jQuery.validator
		.addMethod(
				"isMobile",
				function(value, element) {
					var length = value.length;
					if (value == "ysq01" || value == "lh01" || value == "ctt01"
							|| value == "ysq02" || value == "lh02"
							|| value == "ctt02") {
						return true;
					}
					return this.optional(element)
							|| (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/
									.test(value));
				}, "请正确填写您的手机号码!");

// 电话号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
	return this.optional(element) || (tel.test(value));
	// return false;
}, "请正确填写您的电话号码!")

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isTel", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
	var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
	return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "请正确填写您的联系电话!");

// IP地址验证
jQuery.validator
		.addMethod(
				"ip",
				function(value, element) {
					return this.optional(element)
							|| /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/
									.test(value);
				}, "请填写正确的IP地址！");

// 身份证号码的验证规则
function isIdCardNo(num) {
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);
	else {
		alert("输入的数字位数不对！");
		return false;
	}
	var a = num.match(re);
	if (a != null) {
		if (len == 15) {
			var D = new Date("19" + a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		} else {
			var D = new Date(a[3] + "/" + a[4] + "/" + a[5]);
			var B = D.getFullYear() == a[3] && (D.getMonth() + 1) == a[4]
					&& D.getDate() == a[5];
		}
		if (!B) {
			alert("输入的身份证号 " + a[0] + " 里出生日期不对！");
			return false;
		}
	}
	if (!re.test(num)) {
		alert("身份证最后一位只能是数字和字母!");
		return false;
	}
	return true;
}

// 增加只能是字母和数字的验证
jQuery.validator
		.addMethod(
				"chrnum",
				function(value, element) {
					return this.optional(element)
							|| (/^([a-zA-Z0-9]+)$/.test(value)
									&& value.length >= 6 && value.length <= 20);
				},
				"Please enter a valid format(6 - 20 charactors, A-Z, a-z, 0-9 only).");

// 字符最小长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMinLength", function(value, element, param) {
	var length = value.length;
	for (var i = 0; i < value.length; i++) {
		if (value.charCodeAt(i) > 127) {
			length++;
		}
	}
	return this.optional(element) || (length >= param);
}, $.validator.format("长度不能小于{0}!"));

// 验证数字
jQuery.validator.addMethod("isNum", function(value, element) {
	return this.optional(element) || /^[0-9]*$/.test(value);
}, "必须为数字格式!");

// 验证密码
jQuery.validator.addMethod("isPassword", function(value, element) {
	var Psw = /^(?![0-9]+$)[0-9A-Za-z]{6,20}$/;
	return this.optional(element) || Psw.test(value);
}, "6~20位字母/数字/下划线");

// 验证正整数
jQuery.validator.addMethod("isPositiveInteger", function(value, element) {
	return this.optional(element) || /^[1-9][0-9]*$/.test(value);
}, "必须为正整数!");

// 验证价格的格式
jQuery.validator.addMethod("isPrice", function(value, element) {
	return this.optional(element)
			|| /^(([0-9]|[1-9][0-9]+)\.[0-9]{1,3}|[1-9][0-9]*)$/.test(value);
}, "请输入正确的金额!");

// 验证比例的格式
jQuery.validator.addMethod("isPercent", function(value, element) {
	return this.optional(element)
			|| /^(0\.(0[1-9]|[1-9][0-9]{0,1})|1|1\.0|1\.00)$/.test(value);
}, "比例的数据格式!");

// 开始时间不可以大于结束日期 要求格式：yyyy-MM-dd hh:mm:ss
$.validator.addMethod("startTimeLessEndTime", function(value, element, param) {
	if (param == "" || param == undefined)
		return true;
	return new Date(Date.parse(value.replaceall("-", "/"))) <= new Date(Date
			.parse(param.replaceall("-", "/")));
}, "开始时间必须小于结束时间！");

// 结束时间不可以小于开始时间 要求格式：yyyy-MM-dd hh:mm:ss
$.validator
		.addMethod(
				"endTimeGreaterStartTime",
				function(value, element, param) {
					if (param == "" || param == undefined)
						return true;
					return new Date(Date.parse(value.replaceall("-", "/"))) >= new Date(
							Date.parse(param.replaceall("-", "/")));
				}, "结束时间必须大于开始时间！");

/**
 * 校验最大值不能超过100000000
 */
jQuery.validator.addMethod("maxvalue", function(value, element) {
	if (value >= 100000000) {
		return false;
	}
	return true;
});

/**
 * 校验必须为正数且小数点后最多2位
 */
jQuery.validator.addMethod("positive", function(value, element) {
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
	var isMoneyFormatRight;
	if (value == "") {
		isMoneyFormatRight = true;
	} else {
		isMoneyFormatRight = reg.test(value);
	}
	return isMoneyFormatRight;
});

/**
 * 校验必须为正数且小数点后最多3位
 */
jQuery.validator.addMethod("positiveThree", function(value, element) {
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,3}))$/;
	var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});

jQuery.validator.addMethod("positiveSix", function(value, element) {
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,6}))$/;
	var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});

/**
 * 校验必须为正数且小数点后最多2位
 */
jQuery.validator.addMethod("positiveTwo", function(value, element) {
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
	var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});

/**
 * 校验必须为正数且小数点后最多5位
 */
jQuery.validator.addMethod("positiveFive", function(value, element) {
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,5}))$/;
	var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});

/**
 * 校验必须为字母或数字
 */
jQuery.validator.addMethod("isNumAndZimu", function(value, element) {
	var reg = /^([A-Za-z0-9]+)$/;
	var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});

/**
 * 校验两个值是否相等
 */
jQuery.validator.addMethod("compareValue", function(value, element, param) {
	return (value == param) ? true : false;
}, "两次输入的值必须相等！");

/**
 * 验证可以为纯英文、以英文开头的英文数字空格的组合、空值
 */
jQuery.validator.addMethod("checkEname", function(value, element, param) {
	if (/^[a-zA-Z][a-zA-Z 0-9]*$/.test(value) || !value) {// 允许以字母开头的纯英文、英文+数字+空格组合、空值
		return true;
	} else {
		return false;
	}
}, "请输入正确的英文名称");

/**
 * 只能为英文、空值
 */
jQuery.validator.addMethod("checkFirstLetter", function(value, element, param) {
	if (/^[A-Za-z]+$/.test(value) || !value) {
		return true;
	} else {
		return false;
	}
}, "只能输入英文字母");

/**
 * 最大值必须大于最小值
 */
jQuery.validator.addMethod("checkMaxValue", function(value, element, param) {
	if (parseInt(value) >= parseInt($(param).val()) || (!($(param).val()))) {
		return true;
	} else {
		return false;
	}
}, "必须大于等于最小值");

/**
 * 最小值必须小于最大值
 */
jQuery.validator.addMethod("checkMinValue", function(value, element, param) {
	if (parseInt(value) <= parseInt($(param).val()) || (!($(param).val()))) {
		return true;
	} else {
		return false;
	}
}, "必须小于等于最大值");

/**
 * 参数不为空
 * 
 * @param param
 */
function isNotNull(param) {
	if (param == "" || param == undefined || param == null) {
		return false;
	}
	return true;
}

/**
 * 邮编验证
 */
jQuery.validator.addMethod("isZipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");

/**
 * 验证提现金额不得大于余额
 */
jQuery.validator.addMethod("checkCash", function(value, element, param) {
	if (parseInt(value) <= parseInt($(param).val()) || (!($(param).val()))) {
		return true;
	} else {
		return false;
	}
}, "金额必须小于可提现金额");

//判断中文字符 
jQuery.validator.addMethod("isChinese", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
}, "请输入中文名");  

// 判断英文字符 
jQuery.validator.addMethod("isEnglish", function(value, element) {       
     return this.optional(element) || /^[A-Za-z]+$/.test(value);       
}, "只能包含英文字符。");