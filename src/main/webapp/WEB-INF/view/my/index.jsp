<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>个人中心</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container-fluid">
		<!-- 头 -->
		<div class="row" style="height:64px">
			<div class="col-md-12"  style="background-color:#24292e;height:65px">
				<img alt="" src="/resource/images/love.jpeg" width="65px" height="65px" class="rounded-circle">
				<span></span>
				<font color="white">个人中心</font>
			</div>
		</div>
		
		<!-- body -->
		<div class="row" style="height:500px;padding-top:8px">
			<div class="col-md-2" style="background-color:white" pt-5 >
				<div class="list-group text-center"  >
				  <a href="#" data="/my/articles" class="list-group-item list-group-item-action active">我的文章</a>
				  <a href="#" data="/my/publish" class="list-group-item list-group-item-action list-group-item-light">发布文章</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-light">我的收藏</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-light">我的评论</a>
				  <a href="#" class="list-group-item list-group-item-action list-group-item-light">个人设置</a>
				</div>
				
			</div>
			<div class="col-md-6" id="center" style="background-color:white;margin-top:10px;height:400px">
				<div style="display:none">
				<!-- 包含kindeditor的内容 -->
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"/>
				</div>
				<div id="index">
				
				</div>
			</div>
		</div>
		
		
		
	</div>
	
	
	<script type="text/javascript">
		//默认加载我的文章
		$("#center").load("/my/articles");
	
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
		
		/* 页面一加载完就执行的函数 */
		/* window.onload = function(){ 
			var url = "/my/articles";
			$("#index").load(url);	　　
		}  */
		
	
	
	</script>
</body>
</html>