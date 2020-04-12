<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理员登录</title>
<link href="/resource/css/jquery/screen.css" rel="stylesheet">
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fluid w-50">
	<h2>管理员登录</h2>
	<hr>
	<span id="msg" class="text-danger">${msg}</span>
	<form id="form1">
		<div class="form-group">
			<label for="username">用户名</label> <input
				type="text" class="form-control" id="username"
				name="username"> 
		</div>
		
		<div class="form-group">
			<label for="password">密码</label> <input
				type="password" class="form-control" id="password"
				name="password"> 
		</div>
		
		
		<div class="form-group">
			<button class="btn btn-info" type="submit">登录</button>
			<button class="btn btn-warning" type="reset">重置</button>
		</div>
		
	</form>
	</div>
	
	<script type="text/javascript">
		$("#form1").validate({
			//定义校验规则
			rules:{
				username:{
					required:true,//用户名非空
				},
				password:{
					required:true,//密码非空
				},
				
			},
			//错误消息提示
			messages:{
				username:{
					required:"用户名不能为空",
				},
				password:{
					required:"密码不能为空",
				},
			},
			submitHandler:function(){
				$.post("/passport/admin/login",$("#form1").serialize(),function(result){
					if(result.code==200){
						//如果登录成功，刷新页面
						location = "/admin/";
					}else{
						//登录失败
						$("#msg").text(result.msg);
					}
				});
			}
			
			
		});
	
	
	</script>
</body>
</html>