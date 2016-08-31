<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width; initial-scale=1.0">
<title>演示：jQuery数字加减插件</title>
<link rel="stylesheet" href="${ctx}/Plug/spinner/jquery.spinner.css" />
<script type="text/javascript" src="http://libs.useso.com/js/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/Plug/spinner/jquery.spinner.js"></script>

</head>

<body>

		<h4>示例1：左右加减。</h4>
		<p>最小0，默认1，每次加减1</p>
        <input type="text" class="spinner"/>
   
<script type="text/javascript">
$('.spinner').spinner();
</script>
</body>
</html>