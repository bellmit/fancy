/*
 
 @authors leon <kairyou@qq.com>
          you
*/
$(function () {
	// 控制首页导航条最后一个li没有右边的边框
	$(".g_indexNav ul li:last").css("border-right","none");
	$(".g_pinApacon ul li:last").css("border-bottom","none");

	// 点击首页筛选中的品牌时，显示详细内容
	var shiJian = 1;
	$(".g_pinPai h3").click(function(){
		if (shiJian == 1) {
			$(this).siblings(".g_pinApacon").removeClass("g_none");
			$(this).addClass("g_genghuan");
			shiJian = 2;
		}else{
			$(this).siblings(".g_pinApacon").addClass("g_none");
			$(this).removeClass("g_genghuan");
			shiJian = 1;
			
		};
		
	})
	// 点击筛选，蒙版层固定盖度
	var bodyHei =  $("body,html").height();
	$(".g_mengCeng1").height(bodyHei);
	// $(document).ready(function(){
 //        gao_bottomFin();
 //    });
	// 首页点击筛选
	$(".z_screening").click(function(){
		$(".g_mask").removeClass("g_none");
		$(".g_mengCeng1").removeClass("g_none");
		$(".g_weiIndex").height(bodyHei);
		$(".g_shaiXuan").height(bodyHei);
		$(".g_mask").height(bodyHei);
		$(".z_search").height(bodyHei);

	})
	// 点击“更多品牌”的时候  出现全部品牌
	// $(".g_pinApacon li:last").click(function(){
	// 	$(".g_mengCeng1").addClass("g_none");
	// 	$(".g_mengCeng1").siblings(".g_gengAllpai").removeClass("g_none");
	// })
	// 品牌浮层中点击取消，品牌浮层消失
	$(".g_quXiao").click(function(){
		$(".g_mengCeng1").addClass("g_none");
		$(".g_mask").addClass("g_none");
		$(".g_weiIndex").css("height","auto");
		$(document).ready(function(){
            gao_bottomFin();
        });
	})
	$(".g_quXiao2").click(function(){
		$(".g_gengAllpai").addClass("g_none");
		$(".g_gengAllpai").siblings(".g_mengCeng1").removeClass("g_none");
	})
	// 手机型号蒙版
	$(".quan_right").click(function(){
		$(".g_mask").removeClass("g_none");
		$(".g_shouXinagcon").removeClass("g_none");
		$(".g_konGzhiH").height(bodyHei-8);
	})
	// 手机型号页面，点击旁边省份框、蒙版消失
	$(".g_mask").click(function(){
		$(".g_mask").addClass("g_none");
		$(".g_shouXinagcon").addClass("g_none");
		$(".g_konGzhiH").css("height","auto");
		$(".g_weiIndex").css("height","auto");
		$(".z_search").css("height","auto");
		$(".g_mengCeng1").addClass("g_none");
		$(".g_gengAllpai").addClass("g_none");
		// $(document).ready(function(){
  //           gao_bottomFin();
  //       });
	})
	
	$(".g_shouXinagcon li:first").click(function(){
		$(".g_shouXinagcon li").eq(0).css({"border":"none","background-color":"#eee"});
	})
	$(".g_shouXinagcon li").click(function(){
		$(this).addClass("g_zhongXuan");
		$(this).siblings().removeClass("g_zhongXuan");
	})


	
})
