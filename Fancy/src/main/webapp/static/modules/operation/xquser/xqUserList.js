	

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
				loginFlag:status
			},
			url : ctx+'/xquser/sysUser/enabled.ajax',
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
		* 删除提示并跳转
		* link 跳转的链接
		* tip 提示内容
		*/
		function jump(self,url)
		{
			if ($(self).attr("class")) {
				return false;
			}
			if(confirmx('确认要删除该小区用户吗',url)){
				return true;
			}
			return false;
		}
		//重置密码
		function resetpwd(id,self)
		{
			if ($(self).attr("class")) {
				return false;
			}
			 $.post(ctx+"/xquser/sysUser/resetpwd.html",{id:id}
			 ,function(data)
			 {
				 jBox.tip('重置管理密码成功，重置后的密码是'+data);
			 });
		}
		
		  //点击事件
		  function clickcheck(e) {
				if ($(e).attr("class")) {
					return false;
				}
				return true;
			}