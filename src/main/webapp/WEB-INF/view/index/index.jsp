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
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/css/index.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<style type="text/css">
.wchannel-item:visited {
	color: #444;
}

a:visited {
	color: #999;
}

.wchannel-item {
	display: block;
	width: 110px;
	height: 40px;
	line-height: 40px;
	text-align: center;
	color: #444;
	border-radius: 4px;
	margin-bottom: 2px;
	-webkit-transition-property: color, background-color;
	transition-property: color, background-color;
}

a, a:hover, del, ins, s, u {
	text-decoration: none;
}

a, button {
	cursor: pointer;
}

li {
	list-style: none;
}

.ex1 {
	width: 250px;
	white-space: nowrap; /*不换行的*/
	overflow: hidden; /*超出范围隐藏*/
	text-overflow: ellipsis; /*超出用省略号*/
}

.ex {
	width: 250px;
	overflow: hidden;/*超出范围隐藏*/
	text-overflow: ellipsis;/*超出用省略号 */
	display: -webkit-box;
	-webkit-line-clamp: 2; /*控制多行的行数*/
	-webkit-box-orient: vertical;


	/* width: 250px;
	overflow: hidden; 
	text-overflow: ellipsis; 
	display:-webkit-box; 
	-webkit-box-orient:vertical;
	-webkit-line-clamp:2; */
}
</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12 bg-dark pt-2">
				<font color="white">下载APP</font>
				<div class="float-sm-right">
					<c:if test="${sessionScope.user==null}">
						<button class="btn btn-link btn-sm text-decoration-none"
							data-toggle="modal" data-target="#staticBackdrop" onclick="reg()">
							<font color="white">注册</font>
						</button>
						<button class="btn btn-link btn-sm text-decoration-none">
							<font color="white" data-toggle="modal"
								data-target="#staticBackdrop" onclick="login()">登录</font>
						</button>
					</c:if>
					<c:if test="${sessionScope.user!=null}">
						<!-- Default dropleft button -->
						<div class="btn-group dropleft">
							<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">${sessionScope.user.username}</button>
							<div class="dropdown-menu">
								<ul>
									<li><a href="/my">个人中心</a></li>
									<li><a href="/passport/logout">注销</a></li>
								</ul>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-2 mt-3">
				<ul>
					<li class="mb-3"><a href="/"><img alt=""
							src="/resource/images/logo.271e845.png" width="108px"
							height="27px"></a></li>
					<li><a class="channel-item ${article.channelId==null?"
						active":""}" href="/">热点</a></li>
					<c:forEach items="${channels}" var="channel">
						<li><a class="channel-item ${article.channelId==channel.id?"
							active":""}" href="/?channelId=${channel.id}">${channel.name}</a></li>
					</c:forEach>
				</ul>

			</div>
			<div class="col-md-7 mt-3">
				<!--  如果栏目空，默认显示-->
				<c:if test="${article.channelId==null}">
					<!-- 广告 轮播图 -->
					<div>
						<div id="carouselExampleCaptions" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach var="slide" items="${slist}" varStatus="i">
									<li data-target="#carouselExampleCaptions"
										data-slide-to="${i.index}" class="active"></li>

								</c:forEach>

							</ol>
							<div class="carousel-inner">
								<c:forEach items="${slist}" var="slide" varStatus="i">
									<div class="carousel-item ${i.index==0?"active":""}">
										<img style="height: 320px" src="/pic/${slide.picture}"
											class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block">
											<h5>${slide.title}</h5>
											<p>Nulla vitae elit libero, a pharetra augue mollis
												interdum.</p>
										</div>
									</div>
								</c:forEach>

							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next"> <span
								class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
						</div>
					</div>




					<!-- 热点文章 -->
					<div>
						<c:forEach items="${info.list}" var="hotArticle">
							<div class="media mb-3 mt-3">
								<a href="/detail?id=${hotArticle.id}" target="_blank"> <img
									class="rounded" src="/pic/${hotArticle.picture}" class="mr-3"
									alt="" width="156px" height="101.8px">
								</a>
								<div class="media-body ml-3 mt-3">
									<h5 class="mt-0">
										<a href="/detail?id=${hotArticle.id}" target="_blank">${hotArticle.title}</a>
									</h5>
									<p>${hotArticle.user.username}
										<fmt:formatDate value="${hotArticle.created}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</p>
								</div>
							</div>
							<hr>
						</c:forEach>
						<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
					</div>
				</c:if>


				<c:if test="${article.channelId!=null}">
					<!-- 文章的分类 -->
					<div>
						<ul class="subchannel">

							<li class="sub-item ${article.categoryId==null?"sub-selected":""}"><a
								href="/?channelId=${article.channelId}">全部</a></li>
							<c:forEach items="${categories}" var="category">
								<li class="sub-item ${article.categoryId==category.id?"sub-selected":""}"><a
									href="/?channelId=${article.channelId}&categoryId=${category.id}">${category.name}</a></li>

							</c:forEach>
						</ul>


						<!-- 栏目下全部文章或某个栏目下文章 -->
						<div>
							<c:forEach items="${info.list}" var="article">
								<div class="media mb-3">
									<a href="/detail?id=${article.id}" target="_blank"><img
										class="rounded" src="/pic/${article.picture}" class="mr-3"
										alt="" width="156px" height="101.8px"> </a>
									<div class="media-body ml-3 mt-3">
										<h5 class="mt-0">
											<a href="/detail?id=${article.id}" target="_blank">${article.title}</a>
										</h5>
										<p>${article.user.username}
											<fmt:formatDate value="${article.created}"
												pattern="yyyy-MM-dd" />
										</p>


									</div>
								</div>
								<hr>

							</c:forEach>
							<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>

						</div>


					</div>
				</c:if>
			</div>

			<!-- 右边部分 -->
			<div class="col-md-3">
			  <!-- 最新图片集 -->
			  <div class="card" style="width: 18rem;">
					<div class="card-header">最新图片集</div>
					<div class="card-body">
						<ul class="list-unstyled">
							<c:forEach items="${picArticles.list }" var="article">
								<li class="media"><img style="width: 40px; height: 40px"
									src="/pic/${article.picture }" class="rounded" alt="...">
									<div class="media-body ex">
										<a href="/detailPic?id=${article.id }" target="_blank">${article.title }</a>
									</div></li>
								<hr>
							</c:forEach>
						</ul>
					</div>
				</div>
			
			
			
			
			<!-- 右侧边栏显示最新的5篇文章 -->
			
				<div class="card" style="width: 18rem;">
					<div class="card-header">最新文章</div>

					<div class="card-body">
						<c:forEach items="${lastArticles}" var="lastArticle">
							<ul class="list-unstyled">
								<li class="media"><a href="/detail?id=${lastArticle.id}"
									target="_blank"> <img width="40px" height="40px"
										src="/pic/${lastArticle.picture}" class="mr-2" alt="...">
								</a>
									<div class="media-body ex">
										
											<h6 class="mt-0 mb-1 ex" style="width: 205px"><a href="/detail?id=${lastArticle.id}" target="_blank">${lastArticle.title}</a></h6>
											<h6 class="mt-0 mb-1 ex" style="width: 205px">
												<%-- <fmt:formatDate value="${lastArticle.created}"
													pattern="yyyy-MM-dd HH:mm:ss" /> --%>
											</h6>
									</div></li>
							</ul>
							<hr>
						</c:forEach>
					</div>
				</div>


				<!-- 24小时热文 -->
				<div class="card mt-3" style="width: 18rem;">
					<div class="card-header">24小时热文</div>

					<div class="card-body">
						<c:forEach items="${randomArticles}" var="article24">
							<ul class="list-unstyled">
								<li class="media"><a href="/detail?id=${article24.id}"
									target="_blank"> <img width="40px" height="40px"
										src="/pic/${article24.picture}" class="mr-2" alt="...">
								</a>
									<div class="media-body ex">
										
											<h6 class="mt-0 mb-1 ex" style="width: 205px"><a href="/detail?id=${article24.id}" target="_blank">${article24.title}</a></h6>
											<h6 class="mt-0 mb-1 ex" style="width: 205px">
												<%-- <fmt:formatDate value="${article24.created}"
													pattern="yyyy-MM-dd HH:mm:ss" /> --%>


											</h6>
										
									</div></li>
							</ul>
							<hr>
						</c:forEach>
					</div>
				</div>

			</div>




		</div>

	</div>


	<!-- Modal -->
	<div class="modal fade" id="staticBackdrop" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">
						<span id="title"></span>
					</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="passport">...</div>
				<!-- <div class="modal-footer"> -->

				<!--  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Understood</button> -->
				<!-- </div> -->
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function goPage(pageNum) {
			var channelId = '${article.channelId}';
			var categoryId = '${article.categoryId}';
			var url = "/?pageNum=" + pageNum;

			if (channelId) {
				url += "&channelId=" + channelId;
			}
			if (categoryId) {
				url += "&categoryId=" + categoryId;
			}

			location = url;
		}

		function reg() {
			$("#passport").load("/passport/reg");
			$("#title").text("用户注册");
		}

		function login() {
			$("#passport").load("/passport/login");
			$("#title").text("用户登录");
		}
	</script>
</body>
</html>