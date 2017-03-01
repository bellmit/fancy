$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorPlacement: function(error, element) {
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});


	

	
	
/**
	判断小区是否选中 验证
*/
function districtTreeselectCallBack(v)
{
	
		if($("#districtId").val()=="")
		{
			$("#dValidate").val("");
			$("#dValidate").blur();
		}else{
			$("#dValidate").val($("#districtId").val());
			$("#dValidate").blur();
		}
}
