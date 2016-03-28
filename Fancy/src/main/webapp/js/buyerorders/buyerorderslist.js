//页面初始化设置
$(function(){
	$(".L_home").click(function() {
		window.history.back();
	});
	
	var ordertype = document.getElementById('ordertype').value;
	
	if(ordertype == ""){
		$("#allorders").click();
	}
	else if(ordertype == "1"){
		$("#unpayorders").click();
	}
	else if(ordertype == "2"){
		$("#unsendorders").click();
	}
	else if(ordertype == "3"){
		$("#sendorders").click();
	}
	
	//initShopLevel();
	gao_bottomFin();
})




function initShopLevel(){
	$(".hidlevel").each(function(i,el){
		var sellerlevelVal = $(el).val();
		var bhtml = getSellerLevelHtml(sellerlevelVal);
		$(el).siblings(".levelspan").after(bhtml);
	});
}



//全部订单CSS标签样式改变
function changeAllClass(obj) {
	$(obj).attr("class", "dingdan_con_show");
	$("#unpayorders").attr("class", "");
	$("#unsendorders").attr("class", "");
	$("#sendorders").attr("class", "");
}

//代付款订单CSS标签样式改变
function changeUnpayClass(obj) {
	$(obj).attr("class", "dingdan_con_show");
	$("#allorders").attr("class", "");
	$("#unsendorders").attr("class", "");
	$("#sendorders").attr("class", "");
}

//代发货订单CSS标签样式改变
function changeUnsendClass(obj) {
	$(obj).attr("class", "dingdan_con_show");
	$("#allorders").attr("class", "");
	$("#unpayorders").attr("class", "");
	$("#sendorders").attr("class", "");
}

//配送中订单CSS标签样式改变
function changeSendClass(obj) {
	$(obj).attr("class", "dingdan_con_show");
	$("#allorders").attr("class", "");
	$("#unpayorders").attr("class", "");
	$("#unsendorders").attr("class", "");
}

//全部订单加载
function searchAllorders(obj){
	changeAllClass(obj);
	
	$.ajax({
		url : contextPath + '/buyerOrders/allOrders.html',
		type : 'GET',
		data : {
			"orderstatus" : '0'
		},
		success : function(data) {
			gao_bottomFin();
			$("#result").html(data);
			//initShopLevel();
			gao_bottomFin();
		},
		error : function(data) {
			
		}
	});

}

//待付款订单加载
function searchUnpayorders(obj){
	changeUnpayClass(obj);
	
	$.ajax({
		url : contextPath + '/buyerOrders/allOrders.html',
		type : 'GET',
		data : {
			"orderstatus" : '1'
		},
		success : function(data) {
			gao_bottomFin();
			$("#result").html(data);
			//initShopLevel();
			gao_bottomFin();
		},
		error : function(data) {
			
		}
	});
}

//待发货订单加载
function searchUnsendorders(obj){
	changeUnsendClass(obj);
	
	$.ajax({
		url : contextPath + '/buyerOrders/allOrders.html',
		type : 'GET',
		data : {
			"orderstatus" : '2'
		},
		success : function(data) {			
			$("#result").html(data);
			gao_bottomFin();
			//initShopLevel();
			
		},
		error : function(data) {
			
		}
	});
}

//配送中订单加载
function searchSendorders(obj){
	changeSendClass(obj);
	
	$.ajax({
		url : contextPath + '/buyerOrders/allOrders.html',
		type : 'GET',
		data : {
			"orderstatus" : '3'
		},
		success : function(data) {
			$("#result").html(data);
			//initShopLevel();
			gao_bottomFin();
		},
		error : function(data) {
			
		}
	});
}