// JavaScript Document
 jQuery(function($) {
                    $(window).resize(function(){
                        var width=$('#js-com-header-area').width();
						var height=$(window).height();
						//首页计算
                        $('.touchslider-viewport').css('height',164*(height/640));
						$('.touchslider-viewport img').css('width',640*(width/640)).css('height',164*(height/640));
						$('.pxui-bottom-tab').css('width',640*(width/640));
						$('.indexlist  dl').css('width',640*(width/640));
						$('.indexlist  dl dd').css('width',640*(width/640)-155);
						$('.myheight').css('height',height);
						$('.myheight img').css('width',640*(width/640)).css('height','auto');
						
						$('.cslist  dl').css('width',640*(width/640));
						$('.cslist  dl dd').css('width',640*(width/640)-100);
						
						$('.cclist  dl').css('width',640*(width/640));
						$('.cclist  dl dd').css('width',640*(width/640)-130);
						$('.cclist  dl dd p').css('width',640*(width/640)-140);
						
						$('.newslist  dl').css('width',640*(width/640));
						$('.newslist  dl dd').css('width',640*(width/640)-150);
						$('.newslist  dl dd p').css('width',640*(width/640)-130);
						$('.newsinfo p img').css('width',640*(width/640)-40);
						$('.bigimg img').css('width',640*(width/640)-40);
						$('.smallimg').css('width',640*(width/640)-20);
						$('.smallimg img').css('width',640*(width/640)/4);
						$('.smiao ul li').css('width',640*(width/640)/3);
						$('.smiao ul li img').css('width',640*((width/640)/3)-10);
						$('.csi ul li').css('width',640*(width/640)/3);
						$('.csi ul li img').css('width',640*((width/640)/3)-10);
						$('.cke ul li').css('width',640*(width/640)/3);
						$('.cke ul li img').css('width',640*((width/640)/3)-10);
						$('.cksearch').css('width',640*(width/640)-20);
						$('.topimg img').css('width',640*(width/640));
						$('.cstopimg img.bgimg').css('width',640*(width/640));
						$('.bslist dl').css('width',640*(width/640)-20);
						$('.afhar').css('width',640*(width/640)-140);
						$('.yw').css('width',640*(width/640)-25);
						$('.ywbut').css('width',640*(width/640)-50);
						$('.yw textarea').css('width',640*(width/640)-50);
						$('.px').css('width',640*(width/640)-20);
						$('.dz').css('width',640*(width/640));
						$('.plbox dl').css('width',640*(width/640)-20);
						$('.plbox dl dd').css('width',640*(width/640)-105);
						$('.smindex ul li').css('width',640*(width/640)/3);
						$('.smindex ul li img').css('width',640*((width/640)/3)-10);
						$('.csindex ul li').css('width',640*((width/640)/3)-6);
						$('.csindex ul li img').css('width',640*((width/640)/3)-10);
						$('.indexyl dl').css('width',640*(width/640));
						$('.ys').css('width',640*(width/640)-20);
						$('.ystxt').css('width',640*(width/640)-20);
						$('.mywidth').css('width',640*(width/640)/2.5);
						$('.mywidth2').css('width',640*(width/640)/3);
						$('.tl-tab ul li').css('width',640*(width/640)/5);
						$('.yl-img').css('width',640*(width/640));
						$('.yl-img ul li').css('width',640*(width/640)/5.5).css('height',640*(height/640)/5.5);
						$('.yl-img img').css('width',640*(width/640)/5.5);
						
						/*$('.sm_ul ul li').css('width',640*(width/640)/3);
						$('.sm_ul ul li img').css('width',640*((width/640)/3)-10);
						
						$('.sm_ul ul li').css('width',640*(width/640)/3);
						$('.sm_ul ul li img').css('width',640*((width/640)/3)-10);
						
						$('.sm_ul ul li').css('width',640*(width/640)/3);
						$('.sm_ul ul li img').css('width',640*((width/640)/3)-10);
						*/
						
                    }).resize();	
                    $(".touchslider").touchSlider({mouseTouch: true, autoplay: true});
                });
 
 
 
 Date.prototype.Format = function(fmt)   
 { //author: meizz
   var o = {   
     "M+" : this.getMonth()+1,                 //月份   
     "d+" : this.getDate(),                    //日   
     "h+" : this.getHours(),                   //小时   
     "m+" : this.getMinutes(),                 //分   
     "s+" : this.getSeconds(),                 //秒   
     "q+" : Math.floor((this.getMonth()+3)/3), //季度   
     "S"  : this.getMilliseconds()             //毫秒   
   };   
   if(/(y+)/.test(fmt))   
     fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
   for(var k in o)   
     if(new RegExp("("+ k +")").test(fmt))   
   fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
   return fmt;   
 }
 
 $(function(){ 

		$(".xb label").click(function(){
			$(this).toggleClass("nv");
			var cc = $(".xb label.nv").length;
			if(cc > 0){
				$("#sex").val("2");
			}else{
				$("#sex").val("1");
			}
		});
		if($("#sex").val() == '2'){
			$(".xb label").addClass("nv");
		}else{
			$(".xb label").removeClass("nv");
		}

});
 
function refreshWindowSize(){
	
	var width=$('#js-com-header-area').width();
	var height=$(window).height();
	//首页计算
    $('.touchslider-viewport').css('height',164*(height/640));
	$('.touchslider-viewport img').css('width',640*(width/640)).css('height',164*(height/640));
	$('.pxui-bottom-tab').css('width',640*(width/640));
	$('.indexlist  dl').css('width',640*(width/640));
	$('.indexlist  dl dd').css('width',640*(width/640)-155);
	$('.myheight').css('height',height);
	$('.myheight img').css('width',640*(width/640)).css('height','auto');
	
	$('.cslist  dl').css('width',640*(width/640));
	$('.cslist  dl dd').css('width',640*(width/640)-100);
	
	$('.cclist  dl').css('width',640*(width/640));
	$('.cclist  dl dd').css('width',640*(width/640)-130);
	$('.cclist  dl dd p').css('width',640*(width/640)-140);
	
	$('.newslist  dl').css('width',640*(width/640));
	$('.newslist  dl dd').css('width',640*(width/640)-150);
	$('.newslist  dl dd p').css('width',640*(width/640)-130);
	$('.newsinfo p img').css('width',640*(width/640)-40);
	$('.bigimg img').css('width',640*(width/640)-40);
	$('.smallimg').css('width',640*(width/640)-20);
	$('.smallimg img').css('width',640*(width/640)/4);
	$('.smiao ul li').css('width',640*(width/640)/3);
	$('.smiao ul li img').css('width',640*((width/640)/3)-10);
	$('.csi ul li').css('width',640*(width/640)/3);
	$('.csi ul li img').css('width',640*((width/640)/3)-10);
	$('.cke ul li').css('width',640*(width/640)/3);
	$('.cke ul li img').css('width',640*((width/640)/3)-10);
	$('.cksearch').css('width',640*(width/640)-20);
	$('.topimg img').css('width',640*(width/640));
	$('.cstopimg img.bgimg').css('width',640*(width/640));
	$('.bslist dl').css('width',640*(width/640)-20);
	$('.afhar').css('width',640*(width/640)-140);
	$('.yw').css('width',640*(width/640)-25);
	$('.ywbut').css('width',640*(width/640)-50);
	$('.yw textarea').css('width',640*(width/640)-50);
	$('.px').css('width',640*(width/640)-20);
	$('.dz').css('width',640*(width/640));
	$('.plbox dl').css('width',640*(width/640)-20);
	$('.plbox dl dd').css('width',640*(width/640)-105);
	$('.smindex ul li').css('width',640*(width/640)/3);
	$('.smindex ul li img').css('width',640*((width/640)/3)-10);
	$('.csindex ul li').css('width',640*((width/640)/3)-6);
	$('.csindex ul li img').css('width',640*((width/640)/3)-10);
	$('.indexyl dl').css('width',640*(width/640));
	$('.ys').css('width',640*(width/640)-20);
	$('.ystxt').css('width',640*(width/640)-20);
	$('.mywidth').css('width',640*(width/640)/2.5);
	$('.mywidth2').css('width',640*(width/640)/3);
	$('.tl-tab ul li').css('width',640*(width/640)/5);
	$('.yl-img').css('width',640*(width/640));
	$('.yl-img ul li').css('width',640*(width/640)/5.5).css('height',640*(height/640)/5.5);
	$('.yl-img img').css('width',640*(width/640)/5.5);
}

function return_back(){
	if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){ // IE  
        if(history.length > 0){  
            window.history.go( -1 );  
        }else{  
            window.opener=null;window.close();  
        }  
    }else{ //非IE浏览器  
        if (navigator.userAgent.indexOf('Firefox') >= 0 ||  
            navigator.userAgent.indexOf('Opera') >= 0 ||  
            navigator.userAgent.indexOf('Safari') >= 0 ||  
            navigator.userAgent.indexOf('Chrome') >= 0 ||  
            navigator.userAgent.indexOf('WebKit') >= 0){  
  
            if(window.history.length > 1){  
                window.history.go( -1 );  
            }else{  
                window.opener=null;window.close();  
            }  
        }else{ //未知的浏览器  
            window.history.go( -1 );  
        }  
    }  
}
