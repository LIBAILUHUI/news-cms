<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link href="/resource/css/jquery/screen.css" rel="stylesheet">
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
</head>
<body>
	<div class="container-fluid">
	<span id="msg" class="text-danger"></span>
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
			<label for="repassword">确认密码</label> <input
				type="password" class="form-control" id="repassword"
				name="repassword"> 
		</div>
		
		<div class="form-group form-inline">
		<div class="form-group form-check">
			<label class="mr-1">性别：</label> 
			<input
				type="radio" class="form-check-input" id="boy"
				name="gender" value="0" checked> 
				<label class="form-check-label" for="boy">男</label> 
		</div>
		
		<div class="form-group form-check ml-1">
			
			<input
				type="radio" class="form-check-input" id="girl"
				name="gender" value="0"> 
				<label class="form-check-label" for="girl">女</label>
		</div>
		</div>
		
		
		<div class="form-group">
			<button class="btn btn-info" type="submit">注册</button>
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
					rangelength:[2,6],//用户名长度2-6之间
				},
				password:{
					required:true,//密码非空
					rangelength:[6,12],//密码长度6-12
				},
				repassword:{
					equalTo:"#password",
				}
			},
			//错误消息提示
			messages:{
				username:{
					required:"用户名不能为空",
					rangelength:"用户名长度2-6之间",
				},
				password:{
					required:"密码不能为空",
					rangelength:"密码长度6-12之间",
				},
				repassword:{
					equalTo:"两次密码输入不一致",
				}
			},
			submitHandler:function(){
				$.post("/passport/reg",$("#form1").serialize(),function(result){
					if(result){
						//如果注册成功，跳转到登录页面
						if(result.code==200){
							$("#title").text(result.msg);
							$("#passport").load("/passport/login");
						}
					}else{
						//注册失败
						$("#msg").text(result.msg);
					}
				});
			}
			
			
		});
	
	
	</script>
</body>
</html>