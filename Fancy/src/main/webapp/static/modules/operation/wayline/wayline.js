$(document).ready(function() {
	$("#sel").select2({
		minimumResultsForSearch : -1
	});
});

// 点击航点时处理
function clickMarker(e) {
	// 航点对应信息 及 对应checkbox变化处理
	var nowindex = e.target.getExtData();
	$("#nowmarker").val(nowindex);
	$("#gpslng").html($("#gpslng_" + nowindex).val());
	$("#gpslat").html($("#gpslat_" + nowindex).val());
	$("#height").html($("#height_" + nowindex).val() + "米");
	$("#hdname").html($("#name_" + nowindex).html());

	$(".wpcheck").attr("checked", false);
	$(".spname").removeAttr("style");
	$(".wpcheck").attr("disabled", "disabled");
	$("#name_" + nowindex).attr("style", "color:red");

	// 航点关系处理
	$(".hidsp").each(function(i, el) {
		// 航点关系存在 选中关系checkbox
		if (nowindex == $(el).attr("hidval")) {
			$(el).children(".wpbid").each(function(j, wpel) {
				$("#" + $(wpel).val()).removeAttr("disabled");
			});
			return false;
		}
	});

	var exitflag = true;
	// 航线已保存
	$(".hidfp").each(
					function(i, el) {
						if (nowindex == $(el).attr("hidval")) {
							$("#minHeight").val($(el).children("input[name='minHeight']").val());
							$("#maxHeight").val($(el).children("input[name='maxHeight']").val());
							var cf = $(el).children("input[name='circleFlag']").val();
							if (cf == "1") {
								$("#circleFlag").attr("checked", true);
								$("#circleLongtime").val($(el).children("input[name='circleLongtime']").val());
								$("#circleRadius").val($(el).children("input[name='circleRadius']").val());
								$("#sel").val($(el).children("input[name='circleLongtimeUnit']").val());
								$("#sel").select2();
							} else {
								$("#circleFlag").attr("checked", false);
								$("#circleLongtime").val("");
								$("#circleRadius").val("");
								$("#sel").val("c");
								$("#sel").select2();
							}

							if ($(el).children("input[name='pauseFlag']").val() == "1") {
								$("#pauseFlag").attr("checked", true);
								$("#pauseLongtime").val($(el).children("input[name='pauseLongtime']").val());
							} else {
								$("#pauseFlag").attr("checked", false);
								$("#pauseLongtime").val("");
							}

							if ($(el).next().children("input[name='waypointId']").val()) {
								$("#"+ $(el).next().children("input[name='waypointId']").val()).attr("checked",true);
							}
							exitflag = false;
							return false;
						}
					});

	if (exitflag) {
		$("#minHeight").val("");
		$("#maxHeight").val("");
		$("#circleFlag").attr("checked", true);
		$("#circleLongtime").val("2");
		$("#circleRadius").val("100");
		$("#sel").val("c");
		$("#sel").select2();
		$("#pauseFlag").attr("checked", false);
		$("#pauseLongtime").val("");
	}

	if ($("#lastpoint").val()) {
		if ($("#lastpoint").val() != nowindex) {
			$(".wpcheck").attr("disabled", "disabled");
		}
	}
	
	$(".wpcheck").each(function(i, el) {
		if($.inArray($(el).val(),pointarr)!=-1)
			$(el).attr("disabled","disabled");
	});
	
}

function checkHandle(e) {
	if (!$(e).attr("checked")) {
		polyline.setPath(lineArr);
		return;
	}

	$(".wpcheck").attr("checked", false);
	$(e).attr("checked", true);
	var nowindex = $("#nowmarker").val();
	var lr = lineArr.slice();
	lr.push(new AMap.LngLat($("#longitude_" + nowindex).val(), $(
			"#latitude_" + nowindex).val()));
	lr.push(new AMap.LngLat($("#longitude_" + $(e).val()).val(), $(
			"#latitude_" + $(e).val()).val()));
	polyline.setPath(lr);
}

function saveactive() {
	var nowid = $("#nowmarker").val();
	if (!nowid) {
		jBox.tip("请选择正确绘制航线或选中航点后保存");
		return;
	}
	var nextid = "";
	var purpose = "";
	$(".wpcheck").each(function(i, el) {
		if ($(el).attr("checked")) {
			if (!$("#lastpoint").val() || $("#lastpoint").val() == nowid) {
				nextid = $(el).val();
				purpose = $("#name_" + $(el).val()).val();
			}
			return false;
		}
	});
	
	// 航点信息
	var latitude = $("#latitude_" + nowid).val();
	var longitude = $("#longitude_" + nowid).val();
	var gpslat = $("#gpslat_" + nowid).val();
	var gpslng = $("#gpslng_" + nowid).val();
	var height = $("#height_" + nowid).val();

	var minHeight = $("#minHeight").val();
	var maxHeight = $("#maxHeight").val();
	var circleFlag = "0";
	if ($("#circleFlag").attr("checked")) {
		circleFlag = "1";
		if ("s" == $("#sel").val()) {
			if (!$("#circleLongtime").val()) {
				jBox.tip("请填写盘旋时间后保存");
				return;
			}
			if (!$("#circleRadius").val()) {
				jBox.tip("请填写盘旋半径后保存");
				return;
			}
		} else {
			if (!$("#circleLongtime").val()) {
				jBox.tip("请填写盘旋圈数后保存");
				return;
			}
			if (!$("#circleRadius").val()) {
				jBox.tip("请填写盘旋半径后保存");
				return;
			}
		}
	}
	var circleLongtime = $("#circleLongtime").val()
	var circleLongtimeUnit = $("#sel").val();
	var circleRadius = $("#circleRadius").val()

	var pauseFlag = "0";
	if ($("#pauseFlag").attr("checked")) {
		pauseFlag = "1";
		if (!$("#pauseLongtime").val()) {
			jBox.tip("请填写悬停时间后保存");
			return;
		}
	}
	var pauseLongtime = $("#pauseLongtime").val();

	var flag = true;
	$(".hidfp")
			.each(
					function(i, el) {
						// 航点信息存在 更新
						if (nowid == $(el).attr("hidval")) {
							$(el).children("input[name='purpose']").val(purpose);
							$(el).children("input[name='minHeight']").val(minHeight);
							$(el).children("input[name='maxHeight']").val(maxHeight);
							$(el).children("input[name='circleFlag']").val(circleFlag);
							$(el).children("input[name='circleLongtimeUnit']").val(circleLongtimeUnit);
							$(el).children("input[name='circleRadius']").val(circleRadius);
							$(el).children("input[name='circleLongtime']").val(circleLongtime);
							$(el).children("input[name='pauseFlag']").val(pauseFlag);
							$(el).children("input[name='pauseLongtime']").val(pauseLongtime);
							flag = false;
							return false;
						}
					});

	if (flag) {
		var html = '<span class="hidfp" hidval="' + nowid + '">';
		html += '<input type="text" name="districtId" value="'
				+ $("#districtId").val() + '">';
		html += '<input type="text" name="purpose" value="' + purpose + '">';
		html += '<input type="text" name="waypointId" value="' + nowid + '">';
		html += '<input type="text" name="latitude" value="' + latitude + '">';
		html += '<input type="text" name="longitude" value="' + longitude
				+ '">';
		html += '<input type="text" name="gpslat" value="' + gpslat + '">';
		html += '<input type="text" name="gpslng" value="' + gpslng + '">';
		html += '<input type="text" name="height" value="' + height + '">';
		html += '<input type="text" name="minHeight" value="' + minHeight
				+ '">';
		html += '<input type="text" name="maxHeight" value="' + maxHeight
				+ '">';
		html += '<input type="text" name="circleFlag" value="' + circleFlag
				+ '">';
		html += '<input type="text" name="circleLongtimeUnit" value="'
				+ circleLongtimeUnit + '">';
		html += '<input type="text" name="circleRadius" value="' + circleRadius
				+ '">';
		html += '<input type="text" name="circleLongtime" value="'
				+ circleLongtime + '">';
		html += '<input type="text" name="pauseFlag" value="' + pauseFlag
				+ '">';
		html += '<input type="text" name="pauseLongtime" value="'
				+ pauseLongtime + '">';
		html += '</span>'
		$("#resultdiv").append(html);
	}

	// 如果选取了下一个航点
	if (nextid) {
		var html = '<span class="hidfp" hidval="' + nextid + '">';
		html += '<input type="text" name="districtId" value="'
				+ $("#districtId").val() + '">';
		html += '<input type="text" name="purpose" value="">';
		html += '<input type="text" name="waypointId" value="' + nextid + '">';
		html += '<input type="text" name="latitude" value="'
				+ $("#latitude_" + nextid).val() + '">';
		html += '<input type="text" name="longitude" value="'
				+ $("#longitude_" + nextid).val() + '">';
		html += '<input type="text" name="gpslat" value="'
				+ $("#gpslat_" + nextid).val() + '">';
		html += '<input type="text" name="gpslng" value="'
				+ $("#gpslng_" + nextid).val() + '">';
		html += '<input type="text" name="height" value="'
				+ $("#height_" + nextid).val() + '">';
		html += '<input type="text" name="minHeight" value="">';
		html += '<input type="text" name="maxHeight" value="">';
		html += '<input type="text" name="circleFlag" value="0">';
		html += '<input type="text" name="circleLongtimeUnit" value="">';
		html += '<input type="text" name="circleRadius" value="">';
		html += '<input type="text" name="circleLongtime" value="">';
		html += '<input type="text" name="pauseFlag" value="0">';
		html += '<input type="text" name="pauseLongtime" value="">';
		html += '</span>'
		$("#resultdiv").append(html);
		$("#lastpoint").val(nextid);
	} else {
		// 如果当前没有最后点
		if (!$("#lastpoint").val()) {
			$("#lastpoint").val(nowid);
		}
	}

	jBox.tip("保存成功");
	if ($("#lastpoint").val() != nowid) {
		$(".wpcheck").attr("disabled", "disabled");
	}
	
	var position1 = new AMap.LngLat($("#longitude_"+nowid).val(),$("#latitude_"+nowid).val());
	if ($.inArray(nowid, pointarr) == -1) {
		lineArr.push(position1);
		pointarr.push(nowid);
	}
	
	if(nextid){
		var position2 = new AMap.LngLat($("#longitude_"+nextid).val(),$("#latitude_"+nextid).val());
		if ($.inArray(nextid, pointarr) == -1) {
			lineArr.push(position2);
			pointarr.push(nextid);
		}
	}
	
}


//重新绘制
function clearwl(){
	lineArr = [];
	pointarr = [];
	polyline.setPath(lineArr);
	$("#lastpoint").val("");
	$(".hidfp").remove();
	$("#minHeight").val("");
	$("#maxHeight").val("");
	$("#circleFlag").attr("checked", false);
	$("#circleLongtime").val("");
	$("#circleRadius").val("");
	$("#sel").val("c");
	$("#sel").select2();
	$("#pauseFlag").attr("checked", false);
	$("#pauseLongtime").val("");
}

//保存航线
function saveWl(){
	
	if(!$("#name").val()){
		jBox.tip("请输入航线名称后保存");
		return;
	}
	
	if($(".hidfp").length == 0){
		jBox.tip("请绘制航线后保存");
		return;
	}
	if(!$("#cameraAngle").val())
	{
		jBox.tip("请输入摄像头角度后保存");
		return;
	}
	if(!$("#flySpeed").val())
	{
		jBox.tip("请输入飞行速度后保存");
		return;
	}
	$("#inputForm").submit();
}
