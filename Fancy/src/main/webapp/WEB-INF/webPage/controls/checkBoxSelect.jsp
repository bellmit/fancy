<%@page import="com.ordermanager.util.OrderConstantValues"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="com.Common.UrlCommon"%>
<%=request.getAttribute("selectHtml")%>
<link href="${pageContext.request.contextPath}/css/managerCss/main.css" type="text/css" rel="stylesheet"/>
<%-- <link  href="<%=UrlCommon.ContextPath(request)%>/js/jqueryui/css/ui-lightness/jquery-ui-1.10.1.custom.css" rel="stylesheet"/> --%>
 <link  href="<%=UrlCommon.ContextPath(request)%>/js/jqueryui/css/jquery-ui-timepicker-addon.css" type="text/css" rel="stylesheet" /> 
<input type="hidden" id="provinceId" name="provinceId" /> 
<input type="hidden" id="cityId" name="cityId" />
<input type="hidden" id="countyId" name="countyId" />
<script type="text/javascript">
	$(function() {
		//页面初始化时默认只显示省 start
		
		/* if ($("#oldProvinceId").val() != "") { 
			var proarr=$("#oldProvinceId").val().split(",");
			$("input:checkbox[name=multiselect_provinceId]").each(function(index){
				for ( var i = 0; i < proarr.length; i++) {
					if(this.value ==proarr[i]){
						this.checked = true ;
					}
				}
			}); 
			$("#selectId").val($("#oldProvinceId").val()); 
			$("#provinceId").val($("#oldProvinceId").val()); 
		}
		if ($("#oldCityId").val() != "") {
			loadAreaCity(1);
			var cityarr=$("#oldCityId").val().split(",");
			
			$("input:checkbox[name=multiselect_cityId]").each(function(index){
				for ( var i = 0; i < cityarr.length; i++) {
					if(this.value ==cityarr[i]){
						this.checked = true ;
					}
				}
			}); 
		}
		$("#provinceId").multiselect('update');
		$("#cityId").multiselect('update');
		if($("input:checkbox[name=multiselect_provinceId]:checked").length>0){
			$("#cities").show();
		} */
		//var checkProvinces=$("#oldProvinceId").val();
		//var checkCitys=$("#oldCityId").val();
		//console.log("你好!!! 市："+checkCitys);
		 //if(null!=checkProvinces&&""!=checkProvinces){//如果省的值不为空
		//}
		//console.log("你好!!! "+4);
		
		//页面初始化时默认只显示省 start
/* 		if ($("#oldCityId").val() == "") {
			//$("#cities").hide();
		}
		//页面初始化时默认只显示省 end
		if ($("#oldProvinceId").val() != "") {
			
		} */
		
		var cvalue=$("#city").val();
		var pvalue = $("#province").val();
		var old=$("#oldCityId").val();
		var county_val=$("#county").val();
		if(null!=pvalue&&pvalue.length==1&&null==cvalue){//省关闭的时候
			$("#cityId").val(cvalue);//给市赋值
			loadAreaCity(pvalue.length);
		}
		
		
		if(null!=cvalue&&cvalue.length==1&&null==county_val){//市关闭的时候
			$("#countyId").val(county_val);//给县赋值
			loadAreaCounty(cvalue.length);
		}
	}); 

	// 市
	function loadAreaCity(pvalueLenth) {
		//console.log("选中的值： "+$("#provinceId").val());
		if(pvalueLenth!=1){
			return false;
		}
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				data : {
					areaId : $("#provinceId").val()
				},
				url : contextPath + '/QueryAreaById.html',
				dataType : 'json',
				success : function(data) {
					var checkCitys=$("#oldCityId").val().split(",");
					for ( var i = 0; i < data.length; i++) {
						var flag=false;
						var areaId=data[i].areaId;
						 if((checkCitys.length!=0)&&(""!=checkCitys)){
							for(var j=0;j<checkCitys.length;j++){
								if(checkCitys[j]==areaId){
									flag=true;
								}
							}
						}
						if(flag==true){
							//console.log("相等的："+data[i].areaName);
							$("<option value='" + areaId + "' selected=\"selected\">"+ data[i].areaName + "</option>").appendTo("#city");
						}else{
							$("<option value='" + areaId + "'>"+ data[i].areaName + "</option>").appendTo("#city");
						}  
					}
					$("#city").multiselect('refresh');
					$("#cities").show();
					isShowCity = true;
				}
			});
		
	}
	// 县
	function loadAreaCounty(pvalueLenth) {
		console.log("选中的值： "+$("#cityId").val());
		if(pvalueLenth!=1){
			return false;
		}
			$.ajax({
				type : 'GET',
				contentType : 'application/json',
				data : {
					areaId : $("#cityId").val()
				},
				url : contextPath + '/QueryAreaById.html',
				dataType : 'json',
				success : function(data) {
					var checkCitys=$("#oldCountyId").val().split(",");
					for ( var i = 0; i < data.length; i++) {
						var flag=false;
						var areaId=data[i].areaId;
						 if((checkCitys.length!=0)&&(""!=checkCitys)){
							for(var j=0;j<checkCitys.length;j++){
								if(checkCitys[j]==areaId){
									flag=true;
								}
							}
						}
						if(flag==true){
							//console.log("相等的："+data[i].areaName);
							$("<option value='" + areaId + "' selected=\"selected\">"+ data[i].areaName + "</option>").appendTo("#county");
						}else{
							$("<option value='" + areaId + "'>"+ data[i].areaName + "</option>").appendTo("#county");
						}  
					}
					$("#county").multiselect('refresh');
					$("#counties").show();
					isShowCity = true;
				}
			});
		
	}
</script>
