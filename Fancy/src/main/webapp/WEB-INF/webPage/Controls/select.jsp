<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%=request.getAttribute("selectHtml")%>
<input type="hidden" id="oldProvinceId"
	value="${areaCondition.provinceId }" />
<input type="hidden" id="oldCityId" value="${areaCondition.cityId }" />
<input type="hidden" id="oldDistrictId"
	value="${areaCondition.districtId }" />
<input type="hidden" id="oldTownId" value="${areaCondition.townId }" />
<input type="hidden" id="oldVillageId"
	value="${areaCondition.villageId }" />
<script type="text/javascript">
	$(function() {
		//页面初始化时默认只显示省 start
		if ($("#oldCityId").val() == "") {
			$("#cityId").hide();
		}
		if ($("#oldDistrictId").val() == "") {
			$("#districtId").hide();
		}
		if ($("#oldTownId").val() == "") {
			$("#townId").hide();
		}
		if ($("#oldVillageId").val() == "") {
			$("#villageId").hide();
		}
		//页面初始化时默认只显示省 end
		$("#provinceId").change(loadAreaCity);
		$("#cityId").change(loadAreaDistrict);
		$("#districtId").change(loadAreaTown);
		$("#townId").change(loadAreaVillage);
		
		if ($("#oldProvinceId").val() != "") {
			$("#provinceId").val($("#oldProvinceId").val());
			$("#provinceId").change();
		}
	});

	// 市
	function loadAreaCity() {
		$("#cityId option").remove();
		var from=$("#from").val();
		$("#cityId").append(
				"<option value='' selected='selected'>---全部---</option>");
		$
				.ajax({
					type : 'GET',
					contentType : 'application/json',
					data : {
						areaId : $("#provinceId").val(),
						from :from
					},
					url : contextPath + '/QueryAreaById.html?timeStamp=' + new Date().getTime(),
					dataType : 'json',
					success : function(data) {
						if (data.length == 0) {//当查询结果为空时，隐藏当前字段
							$("#cityId").hide();
						} else {
							$("#cityId").show();
						}

						/* $("#districtId").hide();
						$("#townId").hide();
						$("#villageId").hide(); */

						$("#districtId option").remove();
						$("#districtId")
								.append(
										"<option value='' selected='selected'>---全部---</option>");
						$("#townId option").remove();
						$("#townId")
								.append(
										"<option value='' selected='selected'>---全部---</option>");
						$("#villageId option").remove();
						$("#villageId")
								.append(
										"<option value='' selected='selected'>---全部---</option>");
						for ( var i = 0; i < data.length; i++) {
							$(
									"<option value='" + data[i].areaId + "'>"
											+ data[i].areaName + "</option>")
									.appendTo("#cityId");
						}
						$("#cityId").val($("#oldCityId").val());
						$("#cityId").change();
					}
				});
	}

	// 县/区
	function loadAreaDistrict() {
		$("#districtId option").remove();
		var from=$("#from").val();
		$("#districtId").append(
				"<option value='' selected='selected'>---全部---</option>");
		$
				.ajax({
					type : 'GET',
					contentType : 'application/json',
					data : {
						areaId : $("#cityId").val(),
						from :from
					},
					url : contextPath + '/QueryAreaById.html?timeStamp=' + new Date().getTime(),
					dataType : 'json',
					success : function(data) {
						if (data.length == 0) {
							$("#districtId").hide();
						} else {
							$("#districtId").show();
						}
						/* 	$("#townId").hide();
							$("#villageId").hide(); */
						$("#townId option").remove();
						$("#townId")
								.append(
										"<option value='' selected='selected'>---全部---</option>");
						$("#villageId option").remove();
						$("#villageId")
								.append(
										"<option value='' selected='selected'>---全部---</option>");
						for ( var i = 0; i < data.length; i++) {
							$(
									"<option value='" + data[i].areaId + "'>"
											+ data[i].areaName + "</option>")
									.appendTo("#districtId");
						}
						$("#districtId").val($("#oldDistrictId").val());
						$("#districtId").change();
					}
				});
	}

	//乡镇
	function loadAreaTown() {
		$("#townId option").remove();
		var from=$("#from").val();
		$("#townId").append(
				"<option value='' selected='selected'>---全部---</option>");
		$
				.ajax({
					type : 'GET',
					contentType : 'application/json',
					data : {
						areaId : $("#districtId").val(),
						from :from
					},
					url : contextPath + '/QueryAreaById.html?timeStamp=' + new Date().getTime(),
					dataType : 'json',
					success : function(data) {
						if (data.length == 0) {
							$("#townId").hide();
						} else {
							$("#townId").show();
						}
						/* $("#villageId").hide(); */

						$("#villageId option").remove();
						$("#villageId")
								.append(
										"<option value='' selected='selected'>---全部---</option>");
						for ( var i = 0; i < data.length; i++) {
							$(
									"<option value='" + data[i].areaId + "'>"
											+ data[i].areaName + "</option>")
									.appendTo("#townId");
						}
						$("#townId").val($("#oldTownId").val());
						$("#townId").change();
					}
				});
	}

	//乡镇下一级
	function loadAreaVillage() {
		$("#villageId option").remove();
		var from=$("#from").val();
		$("#villageId").append(
				"<option value='' selected='selected'>---全部---</option>");
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			data : {
				areaId : $("#townId").val(),
				from :from
			},
			url : contextPath + '/QueryAreaById.html?timeStamp=' + new Date().getTime(),
			dataType : 'json',
			success : function(data) {
				for ( var i = 0; i < data.length; i++) {
					$(
							"<option value='" + data[i].areaId + "'>"
									+ data[i].areaName + "</option>").appendTo(
							"#villageId");
				}
			}
		});
	}
</script>
