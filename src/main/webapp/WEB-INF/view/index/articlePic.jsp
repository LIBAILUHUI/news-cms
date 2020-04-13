<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${title }</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- top -->

	<div class="container">

		<dl>
			<!-- 标题 -->
			<dt>
				<h2 align="center">
					<strong>${title }</strong>
				</h2>
			</dt>
			<hr>
			<dd>
				<div>
				     	<div id="carouselExampleCaptions" class="carousel slide"
								data-ride="carousel">
								<ol class="carousel-indicators">
								 <c:forEach items="${picList}" var="a" varStatus="i">
									<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }"
										class="${i.index==0?"active":"" }"></li>
								 </c:forEach>
								</ol>
								<div class="carousel-inner">
								
								  <c:forEach items="${picList}" var="a" varStatus="i">
									<div class="carousel-item ${i.index==0?"active":"" }">
										<img src="/pic/${a.url}" alt="..."
										width="700px" height="400px">
										<div class="carousel-caption d-none d-md-block mr-5">
											<h5>${a.descr}</h5>

									</div>
									</div>
									
									</c:forEach>
								</div>
								<a class="carousel-control-prev" href="#carouselExampleCaptions"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> <a class="carousel-control-next"
									href="#carouselExampleCaptions" role="button" data-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
				</div>
			</dd>
		</dl>


	</div>



</body>
</html>