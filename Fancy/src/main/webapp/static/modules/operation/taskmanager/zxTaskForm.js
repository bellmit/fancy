$(document).ready(function() {
	//$("#name").focus();
	$("#inputForm").validate({
		rules:{
			frequency:{
				required: true
			}
		},messages: {
			frequency:"请选择频率",
		},
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorPlacement: function(error, element) {
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.insertBefore(element.siblings(".help-inline"));
			} else {
				error.insertAfter(element);
			}
		}
	});
});

/**
验证小区是否选中 
 */
function waylineTreeselectCallBack (v)
{
	
	if($("#waylineId").val()=="")
	{
		$("#dValidate").val("");
		$("#dValidate").blur();
	}else{
		$("#dValidate").val($("#waylineId").val());
		$("#dValidate").blur();
	}
}