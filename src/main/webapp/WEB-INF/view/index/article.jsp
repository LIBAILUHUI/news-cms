<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${article.title}</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>

</head>
<body>

<!-- 热点文章的超链接过来的文章详情 -->

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 bg-dark pt-2">
				<font color="white">下载APP</font>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-2 pl-5">
				<p class="mt-4">转发</p>
				<p>QQ</p>
				<p>微信</p>
				<p>微博</p>
			</div>
			
			<!-- 文章详情 -->
			<div class="col-md-7 mt-4">
				<h3>${article.title}</h3>
				<p>${article.user.username} 
				<fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</p>
				<div>
					${article.content}
				
				</div>
			
			</div>
			<div class="col-md-3"></div>
		</div>
	
	
	</div>
</body>
</html>