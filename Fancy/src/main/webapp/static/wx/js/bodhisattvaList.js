
$(function(){
		
	$(".p-list ul li del").click(function(){
		
		$(this).toggleClass("selectps");
		$(this).parent().siblings().children().removeClass("selectps")
	});
});
			
 
 
