<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理员中心</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container-fluid">
		
		<div class="row bg-dark">
			<div class="col-md-12" style="height:64px">
				<img alt="" src="/resource/images/rose.jpg" width="64px" height="64px" class="rounded-circle">
				<font color="white">管理员中心</font>
			</div>
		
		</div>
		
		<div class="row mt-2" >
			
			<div class="col-md-2 bg-light rounded" style="height:500px" pt-5>
				<div class="list-group pt-2 text-center">
				  <a href="#" data="/admin/articles" class="list-group-item list-group-item-action active">
				    审核文章
				  </a>
				  <a href="#" data="/admin/users" class="list-group-item list-group-item-action">管理用户</a>
				  <a href="#" class="list-group-item list-group-item-action">管理栏目</a>
				  <a href="#" class="list-group-item list-group-item-action">系统设置</a>
				  <a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">nothing</a>
</div>
			</div>
			
			<div class="col-md-10" id="center">
				
				中间区域
			</div>
		
		</div>
	
	</div>
	
	<script type="text/javascript">

	
	//默认加载文章
	$("#center").load("/admin/articles");
	
	//给菜单添加绑定点击事件
	$("a").click(function(){
		//获取a标签的url
		var url = $(this).attr("data");
		//先把所有菜单选中样式去除
		$("a").removeClass("active");
		//为当前点击的菜单添加选中的样式
		$(this).addClass("active");
		//在中间区域加载url
		$("#center").load(url);
	});
	</script>
	
</body>
</html>