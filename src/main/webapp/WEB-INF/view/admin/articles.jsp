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
<style type="text/css">
	.ex{
		width:250px;
		white-space:nowrap;/*不换行的*/
		overflow:hidden;/*超出范围隐藏*/
		text-overflow:ellipsis;/*超出用省略号*/
	}
</style>
</head>
<body>
	<div class="form-group form-inline">
		<div class="form-group">
			<label class="title">文章标题</label>
			<input class="form-control" id="title" type="text" name="title" value="${requestScope.article.title}">
		</div>
		
		
		<div class="form-group form-inline">
		审核状态：
		<div class="form-group form-check">
			<input id="check1" class="form-check-input" type="radio" name="status" value="0" ${requestScope.article.status==0?'checked':''}>
			<label class="form-check-label" for="check1">待审</label>
		</div>
		
		<div class="form-group form-check">
			<input id="check2" class="form-check-input" type="radio" name="status" value="1" ${requestScope.article.status==1?'checked':''}>
			<label class="form-check-label" for="check2">已审</label>
		</div>
		
		
		<div class="form-group form-check">
			<input id="check3" class="form-check-input" type="radio" name="status" value="-1" ${requestScope.article.status==-1?'checked':''}>
			<label class="form-check-label" for="check3">驳回</label>
		</div>
		
		</div>
		
		<div>
			<button class="btn btn-info" onclick="query()">查询</button>
		</div>
	
	
	</div>

	<table class="table table-hover table-bordered text-center" style="width:1000px">
		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>所属栏目</td>
			<td>所属分类</td>
			<td>作者</td>
			<td>发布时间</td>
			<td>状态</td>
			<td>是否热门</td>
			<td>操作</td>
		</tr>


		<c:forEach items="${alist}" var="article" varStatus="index">
			<tr>
				<%-- <td>${index.count}</td> --%>
				<td>${article.id}</td>
				<td><div class="ex">${article.title}</div></td>
				<td>${article.channel.name}</td>
				<td>${article.category.name}</td>
				<td>${article.user.username}</td>
				<td>
				<fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${article.status==0?"待审":article.status==1?"已审":"驳回"}</td>
				<td>
					<c:if test="${article.hot==0}"><button class="btn btn-info" style="margin-top:-5px" type="button" onclick="updateHot(${article.id},this)">否</button> </c:if>
				  <c:if test="${article.hot==1}"><button class="btn btn-danger" style="margin-top:-5px" type="button" onclick="updateHot(${article.id},this)">是</button> </c:if>
				</td>
				<td>
					<button type="button" class="btn btn-link" style="margin-top:-5px" data-toggle="modal" data-target="#exampleModal" onclick="detail(${article.id})">详情</button>
				</td>
			</tr>
		
		</c:forEach>
		
		<tr>
			<td colspan="100">
				<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"><span id="aTitle"></span></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="aContent">
	        ...
	      </div>
	      <div class="modal-footer">
	      	
	      	<span id="msg" class="text-danger"></span>
	      	<button type="button" class="btn btn-success" onclick="check(1)">通过</button>
	      	<button type="button" class="btn btn-danger" onclick="check(-1)">驳回</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>

	<script type="text/javascript">
		function goPage(pageNum) {
			var title = $("[name='title']").val();
			var status = $("[name='status']:checked").val();
			$("#center").load("/admin/articles?pageNum="+pageNum+"&title="+title+"&status="+status);
		}
		
		function query(){
			var title = $("[name='title']").val();
			var status = $("[name='status']:checked").val();
			$("#center").load("/admin/articles?title="+title+"&status="+status);
		}
		var aid;
		function detail(id){
			aid = id;
			$.get("/admin/article",{id:id},function(article){
				$("#aContent").html(article.content);
				$("#aTitle").html(article.title);
			});
		}
		
		function check(status){
			$.post("/admin/article/update",{id:aid,status:status},function(flag){
				if(flag){
					$("#msg").text("操作成功");
					
				}else{
					$("#msg").text("操作失败");
				}
			});
			//location.reload();
		}
		
		//关闭模态框时刷新页面
		$("#exampleModal").on("hide.bs.modal",function(){
			$(".modal-backdrop").remove();
			$("body").removeClass("modal-open");
			$("body").removeAttr("style");
			goPage(1);
		}); 
		
		//修改热点
		//传按钮对象过来，是为了不刷新整个页面，只改按钮
		function updateHot(id,obj){
			var hot = $(obj).text()=='否'?1:0;
			$.post("/admin/article/update",{id:id,hot:hot},function(flag){
				if(flag){
					hot==1?$(obj).text("是"):$(obj).text("否");
					hot==1?$(obj).attr("class","btn btn-danger"):$(obj).attr("class","btn btn-info");
					//$(obj).attr("class",hot==1?"btn btn-danger":"btn btn-info");
					$(obj).attr("style","margin-top:-5px");
				}
			});
		}
	</script>

</body>
</html>