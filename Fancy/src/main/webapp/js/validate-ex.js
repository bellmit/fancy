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

//验证码格式验证
jQuery.validator.addMethod("validateCode", function(value, element){
	var validateCode = /^[0-9a-z]{4}$/;
	return this.optional(element) || (validateCode.test(value));
}, "验证码格式不正确！");

// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function(value, element) {    
  var zip = /^[0-9]{6}$/;    
  return this.optional(element) || (zip.test(value));    
}, "请正确填写您的邮政编码!");  

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

// 判断是否已选择市
jQuery.validator.addMethod("isCityRequired", function(value, element) { 
	if(value!='')
		return true; 
	else
		return false;
}, "请选择市!"); 

// 判断是否已选择区
jQuery.validator.addMethod("isDisRequired", function(value, element) { 
	if(value!='')
		return true; 
	else
		return false;    
}, "请选择区!"); 

// 手机号码验证
jQuery.validator.addMethod("isMobile", function(value, element) {    
  var length = value.length;    
	if(value=="ysq01"||value=="lh01"||value=="ctt01"||value=="ysq02"||value=="lh02"||value=="ctt02")
		{
		   return true;
		}
  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/.test(value));    
}, "请正确填写您的手机号码!");

// 电话号码验证
jQuery.validator.addMethod("isPhone", function(value, element) {    
  var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
  return this.optional(element) || (tel.test(value));   
  // return false;
}, "请正确填写您的电话号码!")

// 用户名字符验证
jQuery.validator.addMethod("userName", function(value, element) {    
  return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);    
}, "用户名只能包括中文字、英文字母、数字和下划线!");   

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isTel", function(value,element) {   
    var length = value.length;   
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;   
    return this.optional(element) || (tel.test(value) || mobile.test(value));   
}, "请正确填写您的联系电话!");  

// IP地址验证
jQuery.validator.addMethod("ip", function(value, element) {    
  return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
}, "请填写正确的IP地址！");

// 身份证号码的验证规则
function isIdCardNo(num) {
	var len = num.length, re;
	if (len == 15)
		re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/);
	else if (len == 18)
		re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);
	else {
/*		alert("输入的数字位数不对！");*/
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
//			alert("输入的身份证号 " + a[0] + " 里出生日期不对！");
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
jQuery.validator.addMethod("chrnum", function(value, element) {
  return this.optional(element) || (/^([a-zA-Z0-9]+)$/.test(value) && value.length >= 6 && value.length <= 20);
}, "Please enter a valid format(6 - 20 charactors, A-Z, a-z, 0-9 only).");


// 字符最小长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMinLength", function(value, element, param) {
var length = value.length;
for ( var i = 0; i < value.length; i++) {
if (value.charCodeAt(i) > 127) {
length++;
}
}
return this.optional(element) || (length >= param);
}, $.validator.format("长度不能小于{0}!"));

// 字符最大长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMaxLength", function(value, element, param) {
var length = value.length;
for ( var i = 0; i < value.length; i++) {
if (value.charCodeAt(i) > 127) {
length++;
}
}
return this.optional(element) || (length <= param);
}, $.validator.format("长度不能大于{0}!"));

// 验证数字
jQuery.validator.addMethod("isNum", function(value, element) {    
  return this.optional(element) || /^[0-9]*$/.test(value);    
}, "必须为数字格式!");  

// 验证密码
jQuery.validator.addMethod("isPassword", function(value, element) {    
	var Psw=/^(?![0-9]+$)[0-9A-Za-z]{6,20}$/;
  return this.optional(element) ||Psw .test(value);    
}, "6~20位字母+数字+下划线!");  

// 验证用户名
jQuery.validator.addMethod("isUsername", function(value, element) {    
	var Psw=/^(?![0-9]+$)[0-9A-Za-z_]{6,20}$/;
  return this.optional(element) ||Psw .test(value);    
}, "6~20位字母+数字!");  

// 验证正整数
jQuery.validator.addMethod("isPositiveInteger", function(value, element) {    
  return this.optional(element) || /^[1-9][0-9]*$/.test(value);    
}, "必须为正整数!"); 

// 验证价格的格式
jQuery.validator.addMethod("isPrice", function(value, element) {    
	  return this.optional(element) || /^(([0-9]|[1-9][0-9]+)\.[0-9]{1,3}|[1-9][0-9]*)$/.test(value);    
}, "价格的数据格式!"); 

// 验证比例的格式
jQuery.validator.addMethod("isPercent", function(value, element) {    
	  return this.optional(element) || /^(0\.(0[1-9]|[1-9][0-9]{0,1})|1|1\.0|1\.00)$/.test(value);    
}, "比例的数据格式!"); 

// 验证区域
var customError = ""; 
jQuery.validator.addMethod("isEmpty", function(value, element, param) {  
	var returnVal = true; 
	if($("#"+param[0]).val()==""){
		customError = "请选择省份！";
		returnVal =  false;
	}else if($("#"+param[1]).val()==""){
		customError = "请选择城市！";
		returnVal =  false;
	} else if($("#"+param[2]).val()==""){
		customError = "请选择区县！";
		returnVal =  false;
	}
	
	jQuery.validator.messages.isEmpty = customError; 
	return returnVal; 
}, customError);  

// 开始日期小于结束日期 要求格式：yyyy-MM-dd
$.validator.addMethod("startLessEndDate",function(value,element,params){

    var startDate = jQuery(param).val(); 
    var arr = startDate.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    var arrs = value.split("-");
    var endtime = new Date(arrs[0], arrs[1], arrs[2]);
    var endtimes = endtime.getTime();
    if(starttimes < endtimes){
    	return true;
    }else{
    	return false;
    }

},"起始日期 小于终止日期！");

// 开始日期小于结束日期 要求格式：yyyy-MM-dd hh:mm:ss
$.validator.addMethod("startLessEndDateTime",function(value,element,params){ 

    var startDate = jQuery(param).val(); 
    var arr = startDate.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();
    var arrs = value.split("-");
    var endtime = new Date(arrs[0], arrs[1], arrs[2]);
    var endtimes = endtime.getTime();
    if (startdate >= enddate) {
        return false;
    }
    else
        return true;
	},"开始时间 小于结束时间！");

/**
 * 校验最大值不能超过100000000
 */
jQuery.validator.addMethod("maxvalue",function(value, element){
	if(value >= 100000000){
		return false;
	}
	return true;
});

/**
 * 校验必须为正数且小数点后最多2位
 */
jQuery.validator.addMethod("positive",function(value, element){
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,2}))$/;
    var isMoneyFormatRight;
    if(value == ""){
    	isMoneyFormatRight = true;
	}else{
		isMoneyFormatRight = reg.test(value);
	}
	return isMoneyFormatRight;
});
/**
 * 校验必须为正数且小数点后最多5位
 */
jQuery.validator.addMethod("positiveFive",function(value, element){
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,5}))$/;
    var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});
/**
 * 提报批次格式MM/YYYY
 */
jQuery.validator.addMethod("batchReg",function(value, element){
	var reg = /^(([1-9]{2})\/([0-9]{4}))$/;
    var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});
/**
 * 校验必须为正数且小数点后最多3位
 */
jQuery.validator.addMethod("positiveFive",function(value, element){
	var reg = /^(([1-9]\d*)|([0-9]+\.[0-9]{1,3}))$/;
    var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});
/**
 * 校验必须为字母或数字
 */
jQuery.validator.addMethod("isNumAndZimu",function(value, element){
	var reg = /^([A-Za-z0-9]+)$/;
    var isMoneyFormatRight = reg.test(value);
	return isMoneyFormatRight;
});


//验证正整数或者2位小数
jQuery.validator.addMethod("isPositiveIntegerOr2Dec", function(value, element) {    
	  return this.optional(element) || /^(([0-9]|[1-9][0-9]+)\.[0-9]{1,2}|[1-9][0-9]*)$/.test(value);    
}, "验证正整数或者2位小数!"); 

/**
 * 验证是否是钱
 * 
 * @param money
 * @return true:不是金额,是金钱
 */
function checkNotMoney(money) {
	var isNum = /^\d+(\.\d+)?$/;
	if (!isNum.test(money)) {
		return true;
	} else {
		return false;
	}
}