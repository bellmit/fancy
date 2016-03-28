var pagenum = 1;
var showflag = 0;//历史数据显示 1不显示0
$(document).ready(function(){

	var navH=$(".g_biaoToiu").offset().top;
	$(".g_biaoToiu").css({"position":"fixed","top":0});
    $(window).scroll(function(){

      var scroH = $(this).scrollTop(); 
      if(scroH>=navH){
        $(".g_biaoToiu").css({"position":"fixed","top":0});
        }
    });
    
	orderby1();
	$(window).scroll(function () {
	    if ($(document).scrollTop() + $(window).height() >= $(document).height()) {
	    	scrollSearch();
	    }
	});
	
	$(".hideclass").hide();
	$(".hidenow").hide();
	
	$("input:radio").each(function(i,el){
		$(el).click(function(){
			var a = $(el).siblings("span").html();
			$(el).parent().parent().parent().siblings("h3").children("span").html(a);
			$(el).parent().parent().parent().siblings("h3").children(".searchstr").val($(el).val());
		});
	});
	
	$(".g_pinMony h3").each(function(i,el){
		$(el).click(function(){
			if($(el).siblings("div").hasClass("g_none")){
				
				$(".g_pinMony h3").each(function(i,el){
					if($(el).siblings("div").hasClass("g_none")){
						
					}else{
						$(el).siblings("div").addClass("g_none");
					}
				});
				
				$(el).siblings("div").removeClass("g_none");
			}else{
				$(el).siblings("div").addClass("g_none");
			}
		});
	});
	cookieset();
	$(".g_souSotop input").click(function(e){
		historyshow();
        return false;
    });
	
	$("body").click(function() {
		$(".g_dianChuesf").hide();
	});
	
	$(".z_all").click(function(event) {
        $(".z_type").show().siblings().hide();
     });
	
	$(".z_cancle").click(function(){
        $(".g_mengCeng1").addClass("g_none");
        $(".g_mask").addClass("g_none");
        $(".g_weiIndex").css("height","auto");
        $("#main").show();
        $(document).ready(function(){
            gao_bottomFin();
        });
    })
    
});

function historyshow(){
	if(showflag == 1){
		$(".g_dianChuesf").show();
	}
}

function clear1(){
	$(".g_pinMony h3").each(function(i,el){
		$(el).children("span").html("");
		$(el).children(".searchstr").val("");
	});
	$("input:radio").each(function(i,el){
		$(el).removeAttr("checked");
	});
	
	$(".g_pinMony h3").each(function(i,el){
			if($(el).siblings("div").hasClass("g_none")){
				
			}else{
				$(el).siblings("div").addClass("g_none");
			}
	});
	hidemore();
	$(".shownow").show();
	$(".hidenow").hide();
}

function showmore(obj){
	$(obj).addClass("hidebtn");
	$(obj).hide();
	$(obj).siblings(".hideclass").show();
}

function hidemore(){
	$(".hideclass").hide();
	$(".hidebtn").show();
}

function searchProduct(){
	$(".g_dianChuesf").hide();
	cookiehandle();
	cookieset();
	pagenum = 1;
	
	var sortid = $("#z_all_hidden").val();
	var selectedid = "";
	$(".sortvalli").each(function(i,el){
		if($(el).attr("sortval")){
			if(selectedid){
				selectedid = selectedid + "@" + $(el).attr("sortval");
			}else{
				selectedid = $(el).attr("sortval");
			}
		}
	});
	$.ajax({
		type : 'GET',
		data:{
			conStr:encodeURI($("#keyword").val()),
			pagenum:pagenum,
			orderby:$("#orderby").val(),
			sortid:sortid,
			selectedid:selectedid
		},
		url : contextPath+'/weproductsearch/getProductList.html',
		success : function(data) {
			$("#resultul").html(data);
			pagenum++;
			$(".g_weiIndex").css("height","auto");
			gao_bottomFin();
			$("#scroll").val("0");
		},
		error : function(data) {
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
	cookiehandle();
	cookieset();
	var sortid = $("#z_all_hidden").val();
	var selectedid = "";
	$(".sortvalli").each(function(i,el){
		if($(el).attr("sortval")){
			if(selectedid){
				selectedid = selectedid + "@" + $(el).attr("sortval");
			}else{
				selectedid = $(el).attr("sortval");
			}
		}
	});
	
	$.ajax({
		type : 'GET',
		data:{
			conStr:encodeURI($("#keyword").val()),
			pagenum:pagenum,
			orderby:$("#orderby").val(),
			sortid:sortid,
			selectedid:selectedid
		},
		url : contextPath+'/weproductsearch/getProductList.html',
		success : function(data) {
			$("#resultul").append(data);
			pagenum++;
			$(".g_weiIndex").css("height","auto");
			gao_bottomFin();
			$("#scroll").val("0"); 
		},
		error : function(data) {
			$("#scroll").val("0");
		}
	});
}
var uppath = contextPath+'/images/g_up.png';
var downpath = contextPath+'/images/g_xiaHua.png';
var midpath = contextPath+'/images/g_topBtm.png';
var picflag = "m";
function orderby1(){
	$("#orderby").val("32");
	$("#by1").addClass("gao_xiaoLiang");
	$("#by1").removeClass("gao_money");
	
	$("#by2").addClass("gao_money");
	$("#by2").removeClass("gao_xiaoLiang");
	$("#by2").children("img").attr('src',midpath);
	picflag = "m";
	
	$("#by3").addClass("gao_money");
	$("#by3").removeClass("gao_xiaoLiang");
	$("#by3").children("img").hide();
	
	$("#by4").addClass("gao_money");
	$("#by4").removeClass("gao_xiaoLiang");
	$("#by4").children("img").hide();
	searchProduct();
}

function orderby2(){
	$("#by1").removeClass("gao_xiaoLiang");
	$("#by1").addClass("gao_money");
	
	$("#by2").addClass("gao_money");
	$("#by2").removeClass("gao_xiaoLiang");
	
	if(picflag=="m"){
		$("#by2").children("img").attr('src',downpath);
		$("#orderby").val("22");
		picflag = "d"
	}else{
		if(picflag=="d"){
			$("#by2").children("img").attr('src',uppath);
			$("#orderby").val("21");
			picflag = "u"
		}else{
			$("#by2").children("img").attr('src',downpath);
			$("#orderby").val("22");
			picflag = "d"
		}
	}
	
	$("#by3").addClass("gao_money");
	$("#by3").removeClass("gao_xiaoLiang");
	$("#by3").children("img").hide();
	
	$("#by4").addClass("gao_money");
	$("#by4").removeClass("gao_xiaoLiang");
	$("#by4").children("img").hide();
	searchProduct();
}

function orderby3(){
	
	$("#by1").addClass("gao_money");
	$("#by1").removeClass("gao_xiaoLiang");
	
	$("#by2").addClass("gao_money");
	$("#by2").removeClass("gao_xiaoLiang");
	$("#by2").children("img").attr('src',midpath);
	
	$("#by3").addClass("gao_xiaoLiang");
	$("#by3").removeClass("gao_money");
	$("#by3").children("img").show();
	$("#orderby").val("12");
	
	$("#by4").addClass("gao_money");
	$("#by4").removeClass("gao_xiaoLiang");
	$("#by4").children("img").hide();
	searchProduct();
}

function orderby4(){
	$("#by1").addClass("gao_money");
	$("#by1").removeClass("gao_xiaoLiang");
	
	$("#by2").addClass("gao_money");
	$("#by2").removeClass("gao_xiaoLiang");
	$("#by2").children("img").attr('src',midpath);
	
	$("#by3").addClass("gao_money");
	$("#by3").removeClass("gao_xiaoLiang");
	$("#by3").children("img").hide();
	
	$("#by4").addClass("gao_xiaoLiang");
	$("#by4").removeClass("gao_money");
	$("#by4").children("img").show();
	$("#orderby").val("32");
	searchProduct();
}


function surebtn(){
	$(".g_mengCeng1").addClass("g_none");
	$(".g_mask").addClass("g_none");
	$(".g_weiIndex").css("height","auto");
	$("#main").show();
	searchProduct();
}

function jumpto(urlbefore){
	window.location.href = urlbefore;
}

function hidediv(){
	$(".g_mengCeng1").addClass("g_none");
	$(".g_mask").addClass("g_none");
	$(".g_weiIndex").css("height","auto");
	$("#main").show();
	clear1();
	$("#scroll").val("0");
}

//设置cookie
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
//清除cookie  
function clearCookie(name) {  
    setCookie(name, "", -1);  
}

function cookiehandle(){
	var name = 'keyword';
	var value = encodeURI($.trim($("#keyword").val()));
	if(value){
		var nowcookie = getCookie(name);
		if(nowcookie){
			var str= new Array();   
		    str = nowcookie.split(","); 
		    var newvalue = value;
		    var j = 5;
		    if(str.length<5){
		    	j = str.length;
		    }
		    for (i=0;i<j;i++)   
		    {
		        if(str[i]!=value){
		        	newvalue = newvalue + ',' + str[i];
		        }
		    }
		    setCookie(name,newvalue,7);
		}else{
			setCookie(name,value,7);
		}
	}
}

function cookieset(){
	var name = 'keyword';
	var cookies = getCookie(name);
	if(cookies){
		var str= new Array();
	    str = cookies.split(",");
	    var html = "";
	    for (i=0;i<str.length;i++)
	    {
	    	var s = decodeURI(str[i]);
	    	html = html + '<li onclick="cooksearch(\''+s+'\');"><a href="javascript:void(0);">'+s+'</a></li>';
	    }
	    $("#cookieul").html(html);
	    if(html){
	    	showflag = 1;
	    }else{
	    	showflag = 0;
	    }
	}
}

function cooksearch(value){
	$("#keyword").val($.trim(value));
	searchProduct();
}

function leimu0(){
	$("#leimu0div").html("");
	$(".z_all").html("全部");
	$("#z_all_hidden").val("");
	$.ajax({
		type : 'GET',
		data:{
			productname:encodeURI($.trim($("#keyword").val()))
		},
		url : contextPath+'/weproductsearch/getleimu0.html',
		success : function(data) {
			$("#leimu0div").html(data);
		},
		error : function(data) {
		}
	});
}

function touchli0(obj){
        $(".z_all").html($(obj).children('span').html());
        $(obj).children('img').show();
        $(obj).siblings().children('img').hide();
        $(obj).parent().hide();
        $("#z_all_hidden").val($(obj).attr("id"));
        $.ajax({
    		type : 'GET',
    		data:{
    			sortid:$(obj).attr("id")
    		},
    		url : contextPath+'/weproductsearch/getleimu1.html',
    		success : function(data) {
    			$(".z_phone").remove();
    			$("#leimu0div").append(data);
    			$(obj).parent().siblings(".z_category").show();
    			$(".hideclass").hide();
    		},
    		error : function(data) {
    		}
    	});
}

function touchli1(obj){
	$(".z_details").hide();
	$(obj).siblings('.z_details').show();
	$(obj).children('p').removeClass('z_state1');
	$(obj).children('p').removeClass('z_state2');
	$(obj).children('p').addClass('z_state2');
	$(obj).children('p').show();
}

function touchli2(obj){
	 $(obj).children('img').show();
	 $(obj).parent().parent().attr("sortval",$(obj).attr("id"));
     $(obj).siblings().children('img').hide();
     $(obj).parent().siblings().children('.z_entire').html($(obj).children('span').html()).css('color', '#ff3101');
     $(obj).parent().siblings().children('p').removeClass('z_state1');
     $(obj).parent().siblings().children('p').removeClass('z_state2');
     $(obj).parent().siblings().children('p').addClass('z_state1');
     $(obj).parent().hide();
}