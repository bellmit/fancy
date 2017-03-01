/**
 * 地图管理列表js
 */
function searchMap() {
	if ($("input[name='districtId']").val()) {
		$("#searchForm").submit();
	} else {
		 jBox.tip("请选择小区后查询");
	}
}

function statushandle(e,mapid){
	var status;
	if($(e).html()=="禁用"){
		status = "0";
	} else {
		status = "1";
	}
	$.ajax({
		type : 'POST',
		data:{
			id:mapid,
			status:status
		},
		url : ctx+'/map/xqMap/statushandle',
		success : function(data) {
			
			if(status=="0"){
				$(e).html("启用");
				$(e).siblings("a").addClass("jinyong");
			}else{
				$(e).html("禁用");
				$(e).siblings("a").removeClass("jinyong");
			}
		},
		error : function(data) {
		}
	});

}

function clickcheck(e) {
	if ($(e).attr("class")) {
		return false;
	}
	return true;
}

function delMap(e,url){
	if ($(e).attr("class")) {
		return false;
	}
	if(confirmx("确认要删除该地图吗？",url)){
		return true;
	}
	return false;
}