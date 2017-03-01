							
$(function(){
	
	$(".qfbutbox a").click(function(){
		  
		$(this).hide().next().animate({width:'toggle'}).siblings(".qfbutok").show();
		  
	});
});
					
 
