
$(function(){
	
	//初始化菩萨列表数据
	listBodhisattva(1);
	
	//初始化祈福列表数据
	listPray(0);
	
});


/**
 * 换一批菩萨列表数据
 */
function nextPage(){
	
	var nextPageNo = $.trim($("#nextPageNo").val());
	if(nextPageNo==null || nextPageNo==""){
		
		nextPageNo=1;
	}
	listBodhisattva(nextPageNo);
}


/**
 * 分页查询菩萨列表数据
 * 
 * @param pageNo
 */
function listBodhisattva(pageNo){
	
	$.ajax({
		
		type: "GET",
		async: false,
		url: ctx + "/pray/listbodhisattva?pageNo=" + pageNo,
		success: function(data){
			
			$("#bodhisattvaListDiv").html(data);
		}
	});
}


/**
 * 加载更多的祈福列表数据
 */
function nextPagePray(nextPageNo){
	
	if(nextPageNo==null || nextPageNo==""){
		
		nextPageNo=1;
	}
	$("#nextPagePrayBtnDiv").remove();
	listPray(nextPageNo);
}


/**
 * 分页查询祈福列表数据
 * 
 * @param pageNo
 */
function listPray(startRecordNo){
	
	$.ajax({
		
		type: "GET",
		async: false,
		url: ctx + "/pray/listpray?startRecordNo=" + startRecordNo,
		success: function(data){
			
			$("#js-com-header-area").append(data);
		}
	});
}



/**
 * 添加祈福
 * 
 */
function addPray(){
	
	var params={};
	params["qifuContent"] = $.trim($("#qifuContent").val());
	$("#bodhisattvaListDiv ul li del").each(function(){
		
		if($(this).hasClass("selectps")){
			
			params["posaId"] = $.trim($(this).find("input[type='hidden']").val());
		}
		
	});
	
	if(params["posaId"]==null || params["posaId"]==""){
		
		alert("请选择祈福的菩萨");
		return;
	}
	
	$.ajax({
		
		type: "POST",
		async: false,
		data: params,
		url: ctx + "/pray/addpray",
		success: function(data){
			
			if(data["resultCode"]=="2"){
				
				window.location.href = ctx + "/wx/wx_login";
			}
			if(data["resultCode"]=="0"){
				
				alert("网络忙，请稍后再试");
			}
			if(data["resultCode"]=="1"){
				
				getPray(data["prayId"]);
			}
		}
	});
}


/**
 * 根据祈福id查询祈福信息
 * 
 * @param id
 */
function getPray(id){
	
	$.ajax({
		type: "GET",
		url: ctx + "/pray/getpray?id=" + id,
		cache:false,
		async: false,
		success: function(data){
			$("#bodhisattvaDiv").after(data);
		}
	});
}


/**
 * 添加还愿
 * 
 */
function addVotive(obj,qifuId){
	
	var params={};
	params["qifuId"] = qifuId;
	params["content"] = $.trim($(obj).siblings("input[type='text']").val());
	
	$.ajax({
		
		type: "POST",
		async: false,
		data: params,
		url: ctx + "/pray/addvotive",
		success: function(data){
			
			if(data["resultCode"]=="2"){
				
				window.location.href = ctx + "/wx/wx_login";
			}
			if(data["resultCode"]=="0"){
				
				alert("网络忙，请稍后再试");
			}
			if(data["resultCode"]=="1"){
				
				alert("还愿成功");
			}
		}
	});
}


/**
 * 显示还愿文本输入框
 * 
 * @param obj
 */
function showVotiveInput(obj){
	
	$(obj).hide().next().animate({width:'toggle'}).siblings(".qfbutok").show();
}