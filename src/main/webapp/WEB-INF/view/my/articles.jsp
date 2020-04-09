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
	<!-- <p>全球新型肺炎情况怎么样了?</p>
	<p>有哪些可以在愚人节发的俏皮朋友圈</p>
	<p>如果大通缩和大萧条来了，普通人应该怎么做</p>
	<p>知乎推出船新[B乎]模式</p> -->

	<div>
		<ul class="list-unstyled">
			<c:forEach items="${alist}" var="article">

				<li class="media"><img width="60px" height="60px"
					src="/pic/${article.picture}" class="mr-3" alt="...">
					<div class="media-body">
						<h5 class="mt-0 mb-1">${article.title}</h5>
						<p class="mt-2">
							<fmt:formatDate value="${article.created}"
								pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
								<!-- Button trigger modal -->
						<button type="button" class="btn btn-link" data-toggle="modal"
							data-target="#exampleModalLong" onclick="detail(${article.id})">详情</button>
						</p>
						
					</div></li>
				<hr>
			</c:forEach>
		</ul>

		<!--引入分页  -->
		<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLongTitle"><span id="title"></span></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="content">
	      
	      
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>


	<script type="text/javascript">
		function goPage(pageNum) {
			$("#center").load("/my/articles?pageNum=" + pageNum);
		}
		
		//在模态框中显示文章的详情
		function detail(id){
			
			$("#title").empty();
			$("#content").empty();
			
			$.get("/my/article",{id:id},function(article){
				$("#title").append(article.title);
				$("#content").append(article.content);
			});
		}
	</script>
</body>
</html>