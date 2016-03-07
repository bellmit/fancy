

$(document).ready(function() {
	if($(".l_slslist").html().trim()!=""){
		$(".l_sbtn").removeClass("g_none");
	}
    //搜索
    $(".l_hunt").click(function() {
	     $.ajax({
		 		type : 'POST',
		 		contentType : 'application/html',
		 		url : contextPath+'/historicalsearch/toserachbyshangpin.html?keyword='+$("#keyword").val(),
		 		success : function(data) {
		 			if(data=="false"){
		 				window.location.href=contextPath+'/historicalsearch/noproductview.html?keyword='+$("#keyword").val(); 
		 				//去除与当前查询记录相同的结果记录
		 				$(".l_slslist li").each(function(index){
		 					if($(this).text().trim()==$("#keyword").val()){
		 						$(".l_slslist li").eq(index).remove();
		 					}
		 				});
		 				//追加当前搜索记录
		 				if($(".l_slslist").html().trim()!=""){
		 					$(".l_slslist").prepend("<li onclick='setkeyword(this);'><span>"+$("#keyword").val()+"</span></li>");
		 				}else{
		 					$(".l_slslist").prepend("<li onclick='setkeyword(this);'><span>"+$("#keyword").val()+"</span></li>");
		 					$(".l_sbtn").removeClass("g_none");
		 				}
		 			}else{
		 				window.location.href=contextPath+'/weproductsearch/productview.html?keyword='+$("#keyword").val(); 
		 			}
		 		},
		 		error:function(request,text,error){
		 			alert(text);
		 		}
		 	});
   });
}); 
  //清除搜索历史
  function clearkeys() {
	   var name = "spkeywords";
		var exp = new Date();
	    exp.setTime(exp.getTime() - 99999);
	    var cval=getCookie(name);
	    if(cval!=null){
	        document.cookie= name + "='';expires="+exp.toUTCString()+"; path=/";
	          $(".l_slslist").html("");
	          $(".l_sbtn").addClass("g_none");
	    }
	}
	
	function getCookie(name) 
	{ 
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	    if(arr=document.cookie.match(reg))
	        return unescape(arr[2]); 
	    else 
	        return null; 
	} 
	
	//重新查询历史记录中值
	function setkeyword(obj){
   	 $("#keyword").val($(obj).children("span").html());
   	$(".l_hunt").click();
    }
	//查询热门搜索中值
	function setkeyworda(obj){
   	 $("#keyword").val($(obj).children("a").html());
   	$(".l_hunt").click();
    }
   