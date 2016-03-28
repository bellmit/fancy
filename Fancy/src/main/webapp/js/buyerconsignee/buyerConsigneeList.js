$(function(){
	
});
// 新增收货地址按钮
function addBuyerConsignee(sum){
	if(sum<20){
		window.open(contextPath+"/buyerconsignee/Add.html?from=1",'_self');		
	}else{
		alert("您添加的地址数目已经达到20,无法继续添加");
	}
}

// 修改
function edit(buyconsigneeid){
	window
			.open(
					contextPath
							+ "/buyerconsignee/queryEditBuyerInfo.html?buyconsigneeid="
							+ buyconsigneeid+"&from=1", '_self');
}

