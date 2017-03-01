	function page(n,s){
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
			return false;
	}
	/**
    *切换启用禁用样式
    *self object
    *status 飞机状态
    *id  飞机编号
    */
	function changeStatus(self,id)
	{
		if(($(self).prev().css("color")=="rgb(204, 204, 204)"))
		{
			$(self).siblings().css({"color": $("#aColor").css("color")});
			$(self).parent().siblings().parent().removeAttr("style");
			$(self).text("禁用");
			handlerStatus(id,1);
		}else{
			$(self).siblings().css({"color": "#ccc"});
			$(self).parent().siblings().parent().css({"color": "#ccc"})
			$(self).text("启用");
			handlerStatus(id,0);
		}
	}

	  /**
	   *处理状态更新
	   *id 任务编码
	   *status 状态
	   */
	   function handlerStatus(id,status)
	   {
		   $.post(ctx+"/taskmanager/zxTask/changeStatus",{id:id,status:status});
	   }
	  
	  /**
	   *处理状态更新
	   *id 任务编码
	   *status 状态
	   */
	  function editZxTask(id,self)
	  {
		  if($(self).css("color")=="rgb(204, 204, 204)"){
				 return;
			 }
		  window.location.href=ctx+"/taskmanager/zxTask/form?id="+id;
	  }
	  
	/**
	* 确认提示并跳转
	* link 跳转的链接
	* tip 提示内容
	*/
	function jump(link,tip,self)
	{
		 if($(self).css("color")=="rgb(204, 204, 204)"){
			 return;
		 }
		if(confirmx(tip,link)){
			window.location.href=link;
		}
	}
	
   /**
    *弹出选择配置人页面
    */
	function popupTip(v,self)
	{
		 if($(self).css("color")=="rgb(204, 204, 204)"){
			 return;
		 }
		$("#configTas"+v+"Button").click();
	}
	
   /**
    *配置任务人
    */
	function configTas(index,v)
	{
		if(v=="ok"){
		var userIdOld=$("#userId"+index).val();//旧的用户
		var id=$("#id"+index).val();//更新的任务id
	    var userId=$("#configTas"+index+"Id").val();//新的用户
	    if(userIdOld!=userId)
	   {
		 
		   $.post(ctx+"/taskmanager/zxTask/configTaskUser",{taskId:id,userId:userId},function(data)
		   {
	   			if(data=='ok')
	   			{
	   				$("#configTas"+index+"Id").val(userId);
	   				$("#userId"+index).val(userId)
	   				jBox.tip("配置任务人成功");
	   			}
		   });
	   }
	 }
}