
$(function(){
	$("input[type='reset']").click(function() {
		$("#searchForm")[0].reset();
		$("#search").click();
	});
});




// 将form序列化结果转为json
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push($.trim(this.value) || '');
		} else {
			o[this.name] = $.trim(this.value) || '';
		}
	});
	return o;
};
// 添加用Div
var addDiv = "\
<div class=\"modal fade\" id=\"addDiv\" style=\"display:none;\">\
 <div class=\"modal-dialog\">\
  <div class=\"modal-content\">\
   <div class=\"modal-header\">\
    <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">关闭</span></button>\
    <h4 class=\"modal-title\">ReplaceTitle</h4>\
   </div>\
   <div class=\"modal-body\">\
	<div id=\"addpage\">\
    </div>\
   </div>\
   <div class=\"modal-footer\">\
    <button type=\"button\" class=\"btn btn-primary\" onclick=\"doSave();\">保存</button>\
	<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\
   </div>\
  </div>\
 </div>\
</div>";


function closeSelect() {
	$("[id^=selectDiv]").modal('hide');
}
/** **************************弹出选择画面结束********************************************** */
// 给checkbox添加返回
$(document).on("click", "form input[type='checkbox'][name]", function() {
	addCheckBoxDefault(this);
});

function replaceSpecialChar(str) {
	str = str.replace(/(:|\.|\#|\@|\$|\%|\^|\&|\*|\!|\')/g, '\\$1');
	return str;
}

function addCheckBoxDefault(obj) {
	if ($(
			"form input[type='checkbox'][name='" + replaceSpecialChar(obj.name)
					+ "']").size() == 1) {
		if ($(obj).prop("checked")) {
			$(obj).attr("value", "1");
			$(
					"form input[type='hidden'][name='"
							+ replaceSpecialChar(obj.name) + "']").remove();
		} else {
			if ($(
					"form input[type='hidden'][name='"
							+ replaceSpecialChar(obj.name) + "']").size() == 0) {
				$(obj).after(
						'<input type="hidden" name="' + obj.name
								+ '" value="0" />');
			}
		}
	}

}

// 文件变化
function hiddenFileChange(obj) {
	if (obj.value) {
		var src = '';
		if (obj.files && obj.files[0]) {
			src = obj.files[0].name;
		} else {
			obj.select();
			obj.blur();
			src = document.selection.createRange().text;
		}
		$(obj).parent().prev().val(src);
	}
}

/**
 * 上传文件
 * 
 * @param obj
 */
function uploadFile(obj) {
	var file = $(obj).siblings().find("input[type='file']").val();
	if (file == "") {
		return;
	}

	if (file == undefined || file == "") {
		$.Ialert("请选择文件格式!");
		return;
	}
	var pos1 = file.lastIndexOf("\\");
	var pos2 = file.lastIndexOf(".");
	var pos = file.substring(pos1 + 1, pos2);
	var fileName = pos+file.substring(pos2);
	
	$.ajaxFileUpload({
		async:false,
		contentType : "application/json",
		url : ctx + "/upload/uploadImage.ajax",
		secureuri : false,
		fileElementId : $(obj).siblings().find("input[type='file']").attr("id"),
		dataType : 'text',
		success : function(data) {
			var span = "<span><a href = '" + ctx + "" + data + "' target='_blank'>" + fileName + "</a></span>";
			return $(span);
		},
		error : function(data) {
			alert("error");
		}
	});
}

/**
 * 上传
 * 
 * @param obj
 */
function uploadVideo(obj, callbackUploadVideo) {
	var file = $(obj).siblings().find("input[type='file']").val();
	if (file == "") {
		$.Ialert("请选择视频文件!");
		return;
	}
	if (!/.(flv|rvmb|mp4|avi|wmv|3gp)$/.test(file)) {
		$.Ialert("视频格式不正确!");
		return;
	}
	uploadCommon(obj, "/upload/uploadVideo.ajax", 1);

}

/**
 * 上传图片
 * 
 * @param obj
 */
function uploadImage(obj) {
	var file = $(obj).val();
	if (!checkNull(file)) {
		return;
	}
	if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/i.test(file)) {
		$.Ialert("图片格式不正确!");
		return;
	}

	var objId = $(obj).attr("id");
	$.ajaxFileUpload({
		contentType : "application/json",
		url : ctx + "/upload/uploadImage.ajax",
		secureuri : false,
		fileElementId : objId,
		dataType : 'text',
		success : function(data) {
			if(data==9){
				$.Ialert("图片大小不能超过1M!");
			}else{
//				$("#currHeadImg").attr("src",ctx+data);
				$("#" + objId).siblings("img").attr("src", ctx + data);
				$("#" + objId).siblings("span").html("");
				$("#" + objId).siblings("input[type='hidden']").val(data);
			}
		}
	});
}

/**
 * 上传图片
 * @param obj
 */
function updateImagexx(obj){ 
	alert(1);
	var file = $(obj).val();
	if (!checkNull(file)) {
		return;
	}
	if (!/.(gif|jpg|jpeg|png|gif|jpg|png)$/i.test(file)) {
		Toast("图片格式不正确!");
		return;
	}
	var objId = $(obj).attr("id");
	$.ajaxFileUpload({
		async : false,
		contentType : "application/json",
		url : ctx + "/upload/uploadImage.ajax",
		secureuri : false,
		fileElementId : objId,
		dataType : 'text',
		success : function(data) {
			$("#currHeadImg").attr("src",ctx+data);
			$("#logo").val(data);
		}
	});
}

/**
 * 删除图片
 * 
 * @param obj
 */
function deleteImage(obj) {
	var aObj = $(obj).parent().find("a").eq(0);
	aObj.find("input[type='hidden']").eq(0).val("");
	aObj.find("img").eq(0).attr("src", "");
	aObj.find("span").eq(0).html("+");
}

/**
 * 上传公共 ajax
 * 
 * @param url
 * @param type
 *            0:上传图片,1:上传视频,2:上传文件
 */
function uploadCommon(obj, url, type) {
	$.ajaxFileUpload({
		contentType : "application/json",
		url : ctx + url,
		secureuri : false,
		fileElementId : $(obj).siblings().find("input[type='file']").attr("id"),
		dataType : 'text',
		success : function(data) {
			// 针对上传图片进行的操作
			if (type == 0) {
				$(obj).siblings("img").attr("src", ctx + data);
			} else {
			}
			$(obj).siblings("input[type='hidden']").val(data);
			$.Ialert("上传成功!");
		},
		error : function(data) {
			alert("error");
		}
	});
}

// jquery的validate统一处理
var validateDeal = {
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
};

/**
 * 提示框alert弹出
 * 
 */
(function($) {
	$.extend({
		/**
		 * @parem msg 消息内容
		 * @parem title 消息标题 可以为null
		 * @parem lockFlag 是否锁屏
		 * @parem func 回调函数 可以为bool
		 */
		Ialert : function(msg, title, lockFlag, func, type) {
			$.msgTips(msg, func, type);
		}
	});
})(jQuery);

/**
 * 确认框 confirm 弹出
 * 
 */
(function($) {
	$.extend({
		/**
		 * @parem content 内容
		 * @parem title 标题 可以为null
		 * @parem yes 点击确定时的回调函数
		 * @parem no 点击取消时的回调函数
		 * @parem parent 所属window对象 可以为空 默认当前window对象
		 */
		Iconfirm : function(content, title, yes, no, parent) {
			var currObj = this;
			$.dialog({
				title : title ? title : "确认",
				id : 'confirm.gif',
				icon : '32X32/i.png',
				fixed : true,
				lock : true,
				content : '<p style="font-size:14px;">' + content + '</p>',
				resize : false,
				parent : parent || null,
				ok : function(here) {
					return yes.call(this, here);
				},
				cancel : function(here) {
					return no && no.call(this, here);
				}
			});
		}
	});
})(jQuery);

/**
 * 分页共通
 * 
 * @param url
 * @param pageNum
 *            页数
 * @param result
 *            返回div
 */
function commonChangePage(url, pageNum, result) {
	$.ajax({
		type : "POST",
		url : ctx + url,
		data : $.extend(condition, {
			pageNow : pageNum
		}),
		success : function(data) {
			var res = "#searchResult";
			if (result != undefined) {
				res = result;
			}
			$(res).html(data);
		}
	});
}

/**
 * 保存
 * 
 * @param url
 *            提交url
 * @param data
 *            数据
 * @param type
 *            提交类型
 * @param callback
 *            回调函数
 */
function commonAjaxSave(url, data, type, callback) {
	var ajaxType = "text";
	if (type == "json" || type == "JSON") {
		ajaxType = "JSON";
	}
	$.ajax({
		type : "POST",
		url : ctx + url,
		cache : false,
		data : data,
		dataType : ajaxType,
		timeout : 30000,
		success : callback
	});

}

/**
 * 
 * ajax批量处理事件
 * @param inpValue input 名称（如果是要处理单个信息，填写要处理的id）
 * @param url  
 * @param type   0:处理单个信息,1:处理多个信息
 * @param msg 提示信息
 * @param message 全文提示信息
 */
function commonBacthOperate(inpValue, url, type, msg, message) {
	// 声明数组
	var inpArr = "";
	var str = [];
	// 处理单个
	if (type == "0") {
		if (!checkNull(inpValue)) {
			$.Ialert("请选择要" + msg + "数据");
			return;
		}
		str.push(inpValue);
	} else {
		inpArr = $("input[name='" + inpValue + "']:checked");
		if (inpArr.length <= 0) {
			$.Ialert("请选择要" + msg + "的数据!");
			return;
		}

		for (var i = 0; i < inpArr.length; i++) {
			str.push(inpArr[i].value)
		}
	}

	$.Iconfirm(message, "提示", function() {
		$.ajax({
			type : "POST",
			url : ctx + url,
			data : JSON.stringify(str),
			dataType : "JSON",
			contentType : "application/json",
			success : function(data) {
				if (data >= 1) {
					$.Ialert(msg + "成功", "提示", 1, function() {
						$("#search").click();
					});
				} else {
					$.Ialert(msg + "失败");
				}
			}
		});
	});
}

/**
 * 删除共通
 * 
 * @param inpName
 *            input 名称（如果是要删除单个信息，填写要删除的id）
 * @param url
 *            ajax url
 * @param type
 *            0:删除单个信息,1:批量删除
 */
function commonBatchDel(inpName, url, type) {
	// 声明数组
	var inpArr = "";
	var str = [];
	// 删除单个

	if (type == "0") {
		if (inpName == "") {
			$.Ialert("请选择要删除的数据!", "提示", 1);
			return;
		}
		str.push(inpName)
	} else if (type == "1") {
		// 批量删除

		inpArr = $("input[name='" + inpName + "']:checked");
		if (inpArr.length <= 0) {
			$.Ialert("请选择要删除的数据!", "提示", 1);
			return;
		}

		for (var i = 0; i < inpArr.length; i++) {
			str.push(inpArr[i].value)
		}
	}

	$.Iconfirm("确定将选择的删除吗?", "提示", function() {
		$.ajax({
			type : "POST",
			url : ctx + url,
			data : JSON.stringify(str),
			dataType : "JSON",
			contentType : "application/json",
			success : function(data) {
				if (data >= 1) {
					$("#search").click();
				} else {
					$.Ialert("删除失败");
				}
			}
		});
	});
}

/**
 * 判断参数是否为空
 * 
 * @param param   
 * @returns true:有值,false:无值
 */
function checkNull(param) {
	if (param != undefined && param != "" && param != null) {
		return true;
	}
	return false;
}

String.prototype.replaceAll = function(s1,s2){
	var reg = new RegExp(s1,"g");
	return this.replace(reg,s2);
}

Array.prototype.remove = function(dx) {
	if (isNaN(dx) || dx > this.length) {
		return false;
	}
	for (var i = 0, n = 0; i < this.length; i++) {
		if (this[i] != this[dx]) {
			this[n++] = this[i]
		}
	}
	this.length -= 1
}



/**
 * 写入缓存
 * 
 * @param data
 */
function writeCookie(name, data) {
	
	$.cookie(name, data, {
		path : '/'
	});
}

/**
 * 替换html
 * 
 * @param html
 * @param len
 * @returns
 */
function replaceNameOrId(html, len) {

	html = html.replace(/\_(\d+)/g, "_" + len);
	html = html.replace(/\[(\d+)\]/g, "[" + len + "]");
	return html;
}

/**
 * 短消息提示 msgTips
 *
 * @param $
 */
(function ($) {
    $.extend({
        /**
         *
         * @param msg 消息内容
         * @param callback 回调函数
         * @param type 消息类型 0:info 1：warning
         */
        msgTips: function (msg, callback, type) {

            if (callback && typeof (callback) != "function" && !isNaN(callback)) { //判断第二个参数 是否传递的是消息类型
                type = callback;
            }

            //如果第三个参数为空 则默认为 0：info
            if (typeof (type) == "undefined" || type == null)
                type = 0;

            if ($("#msgTipsDiv", $(top.window.document.body)).length > 0) {
                return;
            }
            //**********************添加遮罩层-开始*********************************//
            var coverObj = $("<div id='cover-msgTipsDiv'></div>");
            coverObj.css({
                "display": "none",
                'z-index': 9999,
                "position": "fixed",
                "top": 0,
                "left": 0,
                "bottom": 0,
                "width": "100%",
                "height": "100%",
                "background-color": "#000000",
                "-moz-opacity": 0.3, /*for ff*/
                "opacity": 0.3, /*for ff3.5+ css3.0标准的*/
                "filter": "alpha(opacity = 30)" /*z-index:900;*/
            });
            coverObj.appendTo($(top.window.document.body));
            $("#cover-msgTipsDiv", $(top.window.document.body)).show();
            //**********************添加遮罩层-结束*********************************//

            var msgObj = $("<div id='msgTipsDiv'></div>");
            var left = ($(top.window).width() * 0.5 ) / 2;
            var scrollTop = $(top.window.document).scrollTop();

            msgObj.css({
                "display": "none",
                "border-radius": "0.25em",
                "font-size": "75%",
                "font-weight": "bold",
                "padding": "8px",
                "text-align": "center",
                "vertical-align": "middle",
                "white-space": "nowrap",
                "position": 'absolute',
                "top": 10 + scrollTop + "px",
                "left": left,
                "width": $(top.window).width() * 0.5,
                "z-index": 9999
            });

            /**
             * info 的样式
             * @type {{}}
             */
            var msgInfoStyle = {
                "background-color": "#46c01c",
                "color": "#fff"
            };

            /**
             *warning 的样式
             * @type {{}}
             */
            var msgWarnStyle = {
                "background-color": "red",
                "color": "#fff"
            }
            if (type == 0) {
                msgObj.css(msgInfoStyle);
            } else {
                msgObj.css(msgWarnStyle);
            }

            msgObj.html(msg);
            msgObj.appendTo($(top.window.document.body));
            $("#msgTipsDiv", $(top.window.document.body)).fadeIn();
            $("#msgTipsDiv", $(top.window.document.body)).fadeOut(2000, function () {
                $("#cover-msgTipsDiv", $(top.window.document.body)).remove();
                $("#msgTipsDiv", $(top.window.document.body)).remove();
                if (callback && typeof(callback) == "function") {
                    callback();
                }

            });
        }
    });
})(jQuery);

$(window).resize(function () {
	var winHeight = $(window).height();
	var simpoTopHeight = $("div.SimpoTop").height();
	var simpo_meunHeight = $("div.simpo-meun").height();
	$(".SimpoLeft").height(winHeight - simpoTopHeight - simpo_meunHeight);
	$(".SimpoLeft").css("overflow","auto");
	
}).resize();