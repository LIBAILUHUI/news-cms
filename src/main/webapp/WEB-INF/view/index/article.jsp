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
		
		<div class="row mb-3">
			<div class="col-md-2 pl-5">
				
			</div>
			<div class="col-md-7 mt-4">
				<a href="/">首页</a>/${article.channel.name}/正文
			</div>
			<div class="col-md-3">
				
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
					<fmt:formatDate value="${article.created}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
				<div>
					${article.content}
					<hr>
					<div align="right">
						<c:if test="${isCollect==0 || isCollect==null}">
							<a href="javascript:collect()"> ☆收藏 </a>
						</c:if>

						<c:if test="${isCollect==1}">
							<span class="text-danger">★已收藏</span>
						</c:if>
					</div>
					
					
					
					<form>
						<div class="form-group">
							<label for="exampleFormControlTextarea1" class="text-danger">${article.commentNum}条评论</label>
							<c:if test="${sessionScope.user!=null}">
							<textarea class="form-control" name="content"
								rows="3"></textarea>
						</div>
						<button type="button" class="btn btn-primary" onclick="comment()">评论</button>
					</form>
					<hr>
					</c:if>
					
					<!-- 显示评论 -->
					<div>
						<c:forEach items="${info.list}" var="comment">
							<span class="text-info">${comment.user.username}</span>
							<fmt:formatDate value="${comment.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
							<br>
							<span>${comment.content}</span>
							<hr>					
						</c:forEach>
					</div>
					
					
					
					
					
					
				</div>

			</div>
			<div class="col-md-3"></div>
		</div>


	</div>

	<script type="text/javascript">
	
		/* 收藏 */
		function collect() {
			var text = '${article.title}';
			//获取当前地址
			var url = window.location.href;
			/* alert(url);
			alert(text); */
			$.post("/collect", {
				text : text,
				url : url
			}, function(flag) {
				if (flag) {
					alert("收藏成功");
					window.location.reload();
				} else {
					alert("收藏失败，请登录后再试");
				}
			});
		}
		
		
		function comment(){
			
			var content = $("[name='content']").val();
			var articleId = '${article.id}';
			$.post("/addComment",{content:content,articleId:articleId},function(flag){
				if(flag){
					alert("评论成功");
					location.reload();
				}else{
					alert("评论失败，请登录后重试");
				}
			});
		}
	</script>
</body>
</html>