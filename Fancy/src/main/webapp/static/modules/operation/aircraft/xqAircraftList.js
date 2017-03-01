
	//解决删除不显示提示问题 
	top.$.jBox.tip.mess = null;

	/**
		验证小区选择
    */
	function  checkForm()
	{
		if(!$("#districtId").val())
		{
			jBox.tip("请先选择小区！");
			return false;
		}
		return true;
	}
		//自带分页
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		//提交飞机form >解决location跳转乱码
		function editAircraft(id,self)
		{
			 if ($(self).attr("class")) {
					return false;
				}
			$("#id").val(id);
			$("#searchNameNew").val($("#name").val());
			$("#saveForm").submit();
		}
		
		

		
   /**
    *切换启用禁用样式
    *self object
    *status 飞机状态
    *id  飞机编号
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
			url : ctx+'/aircraft/xqAircraft/changeStatus',
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
	
 
   var detectStus=false;
   var oldId=0;
   /**
   *检测状态
   *@param id 飞机编号
   */ 
   function detectStatus(id,self)
  {
	   if ($(self).attr("class")) {
			return false;
		}
	  if(detectStus==false)
	  {
		  loadStatus(id);
		  $(".detectStatus").show();
			  oldId=id;
		  detectStus=true;
	  }else{
		  if(oldId!=id)
		  {
			  loadStatus(id);
			  $(".detectStatus").hide();
			  $(".detectStatus").show();
			  oldId=id;
			  detectStus=true;
			  return;
		  }
		  $(".detectStatus").hide();
		  detectStus=false;
	  }
  }
   //给删除表单赋值
   var aircId;
   var deleteAction;
	function href()
	{
		$("#id").val(aircId);
		$("#searchNameNew").val($("#name").val());
		$("#saveForm").attr("action", deleteAction);
		$("#saveForm").submit();
	}
	
	
	/**
	* 确认提示并跳转
	* link 跳转的链接
	* tip 提示内容
	*/
	function jump(hr,id,tip,self)
	{
		 if ($(self).attr("class")) {
				return false;
			}
		 top.$.jBox.confirm(tip,'系统提示',function(v,hre,f){
				if(v=='ok'){
					if (typeof href == 'function') {//href是检测存不存在href()这个方法
						aircId=id;
						deleteAction=hr;
						href();
					}
				}
		 });
	}
	
	

   /**
   *加载无人机状态数据
   *@param id 飞机编号
   */ 
  function loadStatus(id)
  {
	  $.get(ctx+"/aircraft/xqAircraft/detectStatus?id="+id,
			  function(data)
			  {
				  var datas="";
				  datas+="<li>无人机</li><input type='button' value='x' style='float:right;margin-top:-19px;' onclick='closeStatus()'/>"+
					"<li>电量："+data.aircraftBattery+"%</li>"+
					"<li>状态："+data.aircraftstatus+"</li>"+
					"<li>网络："+data.aircraftNetstat+"</li>"+
					"<li>位置："+data.aircraftLocation+"</li>"+
					"<hr style='height:1px;border:none;border-top:1px solid #555555;'/>"+
					"<li>摄像头</li>"+
					"<li>电量："+data.cameraBattery+"%</li>"+
					"<li>网络："+data.cameraNetStat+"</li>"+
					"<li>状态："+data.cameraStatus+"</li>";
					$("#detectStatus").html(datas);
			  });
  }
   
  /**
   *关闭弹窗
   */ 
  function closeStatus()
  {  
	  $(".detectStatus").hide();
	  detectStus=false;
  }
  
   /**
   *历史摄像头
   */ 
  function historyCamera(e,id)
  {
	  if ($(e).attr("class")) {
			return false;
		}
	 jBox.open("iframe:"+ctx+"/aircraft/xqAircraft/historyCamera?aircraftId="+id, "历史摄像头", 600, 400, { buttons: { '关闭': true} });

  }
  //点击事件
  function clickcheck(e) {
		if ($(e).attr("class")) {
			return false;
		}
		return true;
	}