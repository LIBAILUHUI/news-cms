<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的文章</title>
</head>
<body>
	<div>
		<h1 class="text-info">我的收藏夹</h1>
		<hr>
		<ul>
			<c:forEach items="${info.list}" var="collection">
				<li>
					<a href="/my/articleByTitle?title=${collection.text}" target="_blank">
						${collection.text}
					</a>
				</li>
				<li>
					时间：
					<fmt:formatDate value="${collection.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
				<button class="btn btn-warning" onclick="unCollect(${collection.id})">删除</button>
				</li>
				<hr>
			</c:forEach>
		</ul>
		
		
		<!--引入分页  -->
		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
	</div>


	<script type="text/javascript">
		function unCollect(id){
			$.post("/my/unCollect",{id:id},function(flag){
				if(flag){
					//alert("取消收藏成功");
					$("#center").load("/my/collections");
				}else{
					alert("取消收藏失败");
				}
			});
		}
		
	
	
		function goPage(pageNum) {
			$("#center").load("/my/collections?pageNum=" + pageNum);
		}
		
		
	</script>
</body>
</html>