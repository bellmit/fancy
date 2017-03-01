//添加标记
function addMarker(e) {
	//判断是否在限制区域内 
	if (!polygon.contains(e.lnglat)) {
		return;
	}
	var marker = new AMap.Marker({
		icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
		position : [ e.lnglat.getLng(), e.lnglat.getLat() ],
		draggable : true,
		clickable : true,
		extData : mindex
	});
	marker.setMap(map);
	markers.push(marker);
	setMarkerVal(mindex, e.lnglat.getLng(), e.lnglat.getLat(), "","3");
	mindex++;
	AMap.event.addListener(marker, 'click', clickMarker);
	AMap.event.addListener(marker, 'dragstart', dragstart);
	AMap.event.addListener(marker, 'dragend', dragend);
}

function dragstart(e) {
	$("#lastx").val(e.target.getPosition().getLng());
	$("#lasty").val(e.target.getPosition().getLat());

}

function dragend(e) {
	var nowindex = e.target.getExtData();
	var nowname = "";
	var nowheight = "3";
	var nowx = "";
	var nowy = "";
	//判断是否在限制区域内 
	if (polygon.contains(e.target.getPosition())) {
		nowx = e.target.getPosition().getLng();
		nowy = e.target.getPosition().getLat();
	} else {
		nowx = $("#lastx").val();
		nowy = $("#lasty").val();
	}
	$(".hidtr").each(function(i, el) {
		//航点信息存在 更新
		if (nowindex == $(el).attr("mindex")) {
			nowname = $(el).children().find("input[name='name']").val();
			nowheight = $(el).children().find("input[name='height']").val();
			return false;
		}
	});
	setMarkerVal(e.target.getExtData(), nowx, nowy, nowname,nowheight);
	markers[nowindex].setPosition([ nowx, nowy ]);
}
var icon_blue =  new AMap.Icon({image:'http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png'});
var icon_red =  new AMap.Icon({image:'http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png'});
//点击标记
function clickMarker(e) {
	for(var i=0;i<markers.length;i++){
		markers[i].setIcon(icon_blue);
	}
	e.target.setIcon(icon_red);
	$("#lastx").val(e.target.getPosition().getLng());
	$("#lasty").val(e.target.getPosition().getLat());
	var nowindex = e.target.getExtData();
	var nowname = "";
	var nowheight = "3";
	var nowgpslng = "";
	var nowgpslat = "";
	$(".hidtr").each(function(i, el) {
		//航点信息存在 更新
		if (nowindex == $(el).attr("mindex")) {
			nowname = $(el).children().find("input[name='name']").val();
			nowheight = $(el).children().find("input[name='height']").val();
			nowgpslng = $(el).children().find("input[name='gpslng']").val();
			nowgpslat = $(el).children().find("input[name='gpslat']").val();
			return false;
		}
	});
	setMarkerVal(e.target.getExtData(), e.target.getPosition().getLng(),
			e.target.getPosition().getLat(), nowname,nowheight,null,nowgpslng,nowgpslat);
}

//标记经纬赋值
function setMarkerVal(mindex, lng, lat, name,height,flag,gpslng,gpslat) {
	$("#hidmindex").val(mindex);
	$("#hdx").val(lng);
	$("#hdy").val(lat);
	$("#hdname").val(name);
	$("#height").val(height);
	//高德坐标转gps
	if(!flag){
		if(gpslng){
			$("#gpslng").val(gpslng);
			$("#gpslat").val(gpslat);
		}else{
			if(lng){
				var gps = gd2gps(lng,lat);
				$("#gpslng").val(gps[0].toFixed(10));
				$("#gpslat").val(gps[1].toFixed(10));
			}else{
				$("#gpslng").val("");
				$("#gpslat").val("");
			}
		}
	}else{
		//不在范围内 恢复以前的
		if(flag=="2"){
			$("#gpslng").val($("#lastgx").val());
			$("#gpslat").val($("#lastgy").val());
		}
	}
	$("#lastgx").val($("#gpslng").val());
	$("#lastgy").val($("#gpslat").val());
}

//高德坐标转gps
function gd2gps(lng,lat){
	var arr = [];
	$.ajax({
		type : 'GET',
		async: false, 
		data:{
			lng:lng,
			lat:lat
		},
		dataType:'json',
		
		url : ctx+'/map/xqMap/gd2gps',
		success : function(data) {
			arr.push(data.lng);
			arr.push(data.lat);
		},
		error : function(data) {
		}
	});
	return arr;
}

//gps坐标转高德
function gps2gd(lng,lat){
	var arr = [];
	AMap.convertFrom(new AMap.LngLat(lng,lat),'gps',function(a,b){
		arr.push(b.locations[0].getLng());
		arr.push(b.locations[0].getLat());
		return arr;
		});
}


//航点保存或者更新
function savem() {
	var nowindex = $("#hidmindex").val();
	var hdname = $("#hdname").val();
	var hdx = $("#hdx").val();
	var hdy = $("#hdy").val();
	var gpslng = $("#gpslng").val();
	var gpslat = $("#gpslat").val();
	var height = $("#height").val();
	if (!nowindex) {
		return;
	}
	if (!hdname) {
		jBox.tip("请输入名称");
		return;
	}

	if(!(hdx&&hdy&&gpslng&&gpslat)){
		jBox.tip("请输入完整的坐标");
		return;
	}
	
	var flag = true;
	$(".hidtr").each(
			function(i, el) {
				//航点信息存在 更新
				if (nowindex == $(el).attr("mindex")) {
					$(el).children().find("input[name='name']").val(hdname);
					$(el).children().find("input[name='longitude']").val(hdx);
					$(el).children().find("input[name='latitude']").val(hdy);
					$(el).children().find("input[name='gpslng']").val(gpslng);
					$(el).children().find("input[name='gpslat']").val(gpslat);
					$(el).children().find("input[name='height']").val(height);
					flag = false;
					return false;
				}
			});

	//航点信息不存在 新建
	if (flag) {
		var html = '<tr class="hidtr" mindex="'+nowindex+'"><td>';
		html += '<input type="text" name="wpid" ><input type="text" name="name" value="'
				+ hdname + '"></td><td>';
		html += '<input type="text" name="longitude" value="' + hdx
				+ '"></td><td>';
		html += '<input type="text" name="latitude" value="' + hdy
		+ '"></td><td>';
		html += '<input type="text" name="gpslng" value="' + gpslng
		+ '"></td><td>';
		html += '<input type="text" name="gpslat" value="' + gpslat
		+ '"></td><td>';
		html += '<input type="text" name="height" value="' + height
		+ '"></td></tr>';
		$("#resulttb").append(html);
	}
	markers[nowindex].setTitle($("#hdname").val());
	jBox.tip("航点信息保存成功");
}

//航点删除
function delm() {
	var nowindex = $("#hidmindex").val();
	if (!nowindex) {
		return;
	}
	$(".hidtr").each(function(i, el) {
		if (nowindex == $(el).attr("mindex")) {
			$(el).remove();
			return false;
		}
	});
	setMarkerVal("", "", "", "","3");
	markers[nowindex].setMap(null);
}

function changeMarker() {
	var nowindex = $("#hidmindex").val();
	var gpslng = $("#gpslng").val();
	var gpslat = $("#gpslat").val();
	
	if (!nowindex || !gpslng || !gpslat) {
		return;
	}
	//gps坐标转高德
	AMap.convertFrom(new AMap.LngLat(gpslng,gpslat),'gps',function(a,b){
		var hdx = b.locations[0].getLng();
		var hdy = b.locations[0].getLat();
		//判断是否在限制区域内 
		var flag = "1";
		if (polygon.contains([ hdx, hdy ])) {
			markers[nowindex].setPosition([ hdx, hdy ]);
		}else{
			flag = "2";
			hdx = $("#lastx").val();
			hdy = $("#lasty").val();
		}
		setMarkerVal(nowindex,hdx,hdy, $("#hdname").val(),$("#height").val(),flag);
		});
}

function saveWayPoint(){
	if($(".hidtr").length == 0){
		jBox.tip("请配置航点后保存");
		return;
	}
	
	$("#wpForm").submit();
}
