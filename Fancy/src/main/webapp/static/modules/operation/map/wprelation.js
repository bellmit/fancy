var icon_blue =  new AMap.Icon({image:'http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png'});
var icon_red =  new AMap.Icon({image:'http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png'});
//点击航点时处理
function clickMarker(e) {
	for(var i=0;i<markers.length;i++){
		markers[i].setIcon(icon_blue);
	}
	e.target.setIcon(icon_red);
	// 航点对应信息 及 对应checkbox变化处理
	var nowindex = e.target.getExtData();
	$("#nowmarker").val(nowindex);
	$("#gpslng").html($("#gpslng_" + nowindex).val());
	$("#gpslat").html($("#gpslat_" + nowindex).val());
	$("#height").html($("#height_" + nowindex).val() + "米");
	$("#hdname").html($("#name_" + nowindex).html());

	$("input[name='wpcheck']").attr("checked", false);
	$(".spname").removeAttr("style");
	$(".wpcheck").removeAttr("disabled");
	$("#" + nowindex).attr("disabled", "disabled");
	$("#name_" + nowindex).attr("style", "color:red");
	
	// 航点关系处理
	$(".hidsp").each(function(i, el) {
		// 航点关系存在 选中关系checkbox
		if (nowindex == $(el).attr("hidval")) {
			$(el).children(".wpbid").each(function(j, wpel) {
				$("#" + $(wpel).val()).attr("checked", true);
			});
			return false;
		}
	});
}

// 保存关系

function saver() {

	var nowid = $("#nowmarker").val();
	if (!nowid) {
		jBox.tip("请选择航点可去的位置后保存");
		return;
	}

	var flag = true;
	var html = '<span class="hidsp" hidval="' + nowid
			+ '"><input type="text" class="wpaid" name="wpaid" value="' + nowid
			+ '">';
	$(".wpcheck").each(
			function(i, el) {
				if ($(el).attr("checked")) {
					flag = false;
					html += '<input type="text" class="wpbid" value="'
							+ $(el).val() + '">'
				}
			});
	if (flag) {
		jBox.tip("请选择航点可去的位置后保存");
		return;
	}
	html += '</span>';

	$(".hidsp").each(function(i, el) {
		if (nowid == $(el).attr("hidval")) {
			$(el).remove();
			return false;
		}
	});
	$("#resultdiv").append(html);
	jBox.tip("航点关系保存成功");
}

// 配置完毕
function saveWpr() {
	if ($(".hidsp").length == 0) {
		jBox.tip("请配置航点关系后保存");
		return;
	}

	$(".hidsp").each(function(i, el) {
		var ids = "";
		$(el).children().siblings(".wpbid").each(function(j, wpel) {
			if (ids) {
				ids = ids + "&" + $(wpel).val();
			} else {
				ids = $(wpel).val();
			}
		});
		var html = '<input type="hidden" name="wpbid" value="' + ids + '">';
		$("#resultdiv").append(html);
	});

	$("#inputForm").submit();
}
