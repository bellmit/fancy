var pagenum = 1;
/*判断产品名称列的高度 一行垂直居中显示*/
$(function() {
    serachQuotation();
    /*页面向下滚动*/
	$(window).scroll(function () {
	    if ($(document).scrollTop() + $(window).height() >= $(document).height()) {
	    	scrollSearch();
	    }
	});
});

/*显示:当前日期加时间(如:2009-06-12 12:00:00)*/
function CurentTime()
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();          //秒
    var clock = year + "/";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "/";
   
    if(day < 10)
        clock += "0";
       
    clock += day + "/";
   
    if(hh < 10)
        clock += "0";
    clock += hh + ":";
    
    if (mm < 10) 
    	clock += '0'; 
    clock += mm+ ":";
    
    if (ss < 10) 
    	clock += '0'; 
    clock += ss; 
    //修改时间处
    $('#time').html(clock);
    //修改内容处
    serachQuotation();
} 


function serachQuotation(){
		pagenum = 1;
		$("#scroll").val("1");
		$.ajax({
			type : 'GET',
			url : contextPath+'/weproductsearch/getquotation.html?pagenum='+pagenum,
			success : function(data) {
				if(data.indexOf("second") > 0 ){
					$(".l_prilist").html(data);
					pagenum++;
					$("#scroll").val("0");
					$(".l_bjdlist .l_prilist .second p").each(function(index, el) {
				        if($(this).height()>18)
				        {
				            $(this).css("line-height","18px");
				        }
				        else
				        {
				            $(this).css("line-height","33px");
				        }
				    });
				}
			},
			error : function(data) {
				$("#scroll").val("0");
			}
		});
}

function scrollSearch(){
	if($("#scroll").val()=="1"){
		return;
	}
	if($("#pageflag").val()=="0"){
		return;
	}
	$("#scroll").val("1");
	$.ajax({
		type : 'GET',
		url : contextPath+'/weproductsearch/getquotation.html?pagenum='+pagenum,
		success : function(data) {
			if(data.indexOf("second") > 0 ){
				$(".l_prilist").append(data);
				pagenum++;
				$("#scroll").val("0");
			}
		},
		error : function(data) {
			$("#scroll").val("0");
		}
	});
}