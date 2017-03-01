
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}

	/**
    *切换启用禁用样式
    *self object
    */
	function changeStatus(e,id)
	{
		var status;
		if($(e).html()=="禁用"){
			status = "0";
		} else {
			status = "1";
		}
		$.ajax({
			type : 'POST',
			data:{
				id:id,
				status:status
			},
			url : ctx+'/xqdistrict/xqDistrict/enabled.ajax',
			success : function(data) {
				if(status=="0"){
					$(e).html("启用");
					$(e).siblings("a").addClass("jinyong");
				}else{
					$(e).html("禁用");
					$(e).siblings("a").removeClass("jinyong");
				}
			}
		});
	}

		  /**
		   *编辑
		   *id 任务编码
		   *status 状态
		   */
		  function editDist(id,self)
		  {
			  if ($(self).attr("class")) {
					return false;
				}
			  window.location.href=ctx+"/xqdistrict/xqDistrict/form.html?id="+id;
		  }
		  
			/**
			* 删除提示并跳转
			* link 跳转的链接
			* tip 提示内容
			*/
			function jump(link,tip,self)
			{
				if ($(self).attr("class")) {
					return false;
				}
				if(confirmx(tip,link)){
					window.location.href=link;
				}
			}
			
		//重置密码
		function resetpwd(id,self)
		{
			if ($(self).attr("class")) {
				return false;
			}
			 $.post(ctx+"/xqdistrict/xqDistrict/resetpwd.html",{userId:id}
			 ,function(data)
			 {
				 jBox.tip('重置管理密码成功，重置后的密码是'+data);
			 });
		}
		
		/**
	    *弹出选择负责人页面
	    */
		function popupTip(v,self)
		{
			if ($(self).attr("class")) {
				return false;
			}
			$("#configUser"+v+"Button").click();
		}
		
		/**
	    *配置负责人
	    */
		function configUser(index,v)
		{
			
			if(v=="ok"){
			var userIdOld=$("#userId"+index).val();//旧的用户
			var id=$("#id"+index).val();//更新的小区id
		    var userId=$("#configUser"+index+"Id").val();//新的用户
		    if(userIdOld!=userId)
			   {
				 
				  $.post(ctx+"/xqdistrict/xqDistrict/configDistriUser",{districtId:id,userId:userId},function(data)
				   {
			   			if(data=='ok')
			   			{
			   				$("#configUser"+index+"Id").val(userId);
			   				$("#userId"+index).val(userId)
			   				jBox.tip("配置小区负责人成功");
			   			}
				   }); 
			   }
		 }
	}