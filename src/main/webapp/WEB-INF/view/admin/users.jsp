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

</style>
</head>
<body>
	<div class="form-group form-inline">
		<div class="form-group">
			<label class="username">用户姓名</label>
			<input class="form-control" id="username" type="text" name="username" value="${requestScope.user.username}">
		</div>
		
		
		<div class="form-group form-inline">
		用户状态：
		<div class="form-group form-check">
			<input id="check1" class="form-check-input" type="radio" name="locked" value="0" ${requestScope.user.locked==0?'checked':''}>
			<label class="form-check-label" for="check1">正常</label>
		</div>
		
		<div class="form-group form-check">
			<input id="check2" class="form-check-input" type="radio" name="locked" value="1" ${requestScope.user.locked==1?'checked':''}>
			<label class="form-check-label" for="check2">禁用</label>
		</div>
		
		
		</div>
		
		<div>
			<button class="btn btn-info" onclick="query()">查询</button>
		</div>
	
	
	</div>

	<table class="table table-hover table-bordered text-center" style="width:1000px">
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>注册日期</td>
			<td>生日</td>
			<td>状态</td>
			<td>操作</td>
		</tr>


		<c:forEach items="${ulist}" var="user" varStatus="index">
			<tr>
				<%-- <td>${index.count}</td> --%>
				<td>${user.id}</td>
				<td><div class="ex">${user.username}</div></td>
				<td>${user.nickname}</td>
				<td>
				<fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${user.role==1}">
						<button class="btn btn-success" style="margin-top:-5px" type="button">管理员</button>
					</c:if>
					 
					 <c:if test="${user.role==0}">
					 	<c:if test="${user.locked==0}"><button class="btn btn-info" style="margin-top:-5px" type="button" onclick="updateLocked(${user.id},this)">正常</button> </c:if>
				  		<c:if test="${user.locked==1}"><button class="btn btn-danger" style="margin-top:-5px" type="button" onclick="updateLocked(${user.id},this)">禁用</button> </c:if>
					 </c:if>
				</td>
				<td>
					<button type="button" class="btn btn-link" style="margin-top:-5px" data-toggle="modal" data-target="#exampleModal" onclick="detail(${user.id})">详情</button>
				</td>
			</tr>
		
		</c:forEach>
		
		<tr>
			<td colspan="100">
				<jsp:include page="/WEB-INF/view/common/pages.jsp"></jsp:include>
			</td>
		</tr>
	</table>
	
	
	

	<script type="text/javascript">
		function goPage(pageNum) {
			var username = $("[name='username']").val();
			var locked = $("[name='locked']:checked").val();
			$("#center").load("/admin/users?pageNum="+pageNum+"&locked="+locked+"&username="+username);
		}
		
		 function query(){
			var username = $("[name='username']").val();
			var locked = $("[name='locked']:checked").val();
			$("#center").load("/admin/users?username="+username+"&locked="+locked);
		} 
		/* var aid;
		function detail(id){
			aid = id;
			$.get("/my/article",{id:id},function(article){
				$("#aContent").html(article.content);
				$("#aTitle").html(article.title);
			});
		} */
		
		/* function check(status){
			$.post("/admin/update",{id:aid,status:status},function(flag){
				if(flag){
					$("#msg").text("操作成功");
					
				}else{
					$("#msg").text("操作失败");
				}
			});
			//location.reload();
		} */
		
		/* //关闭模态框时刷新页面
		$("#exampleModal").on("hide.bs.modal",function(){
			$(".modal-backdrop").remove();
			$("body").removeClass("modal-open");
			$("body").removeAttr("style");
			goPage(1);
		});  */
		
		//修改用户状态locked1禁用  locked0正常
		//传按钮对象过来，是为了不刷新整个页面，只改按钮
		function updateLocked(id,obj){
			var locked = $(obj).text()=='正常'?1:0;
			$.post("/admin/user/update",{id:id,locked:locked},function(flag){
				if(flag){
					locked==1?$(obj).text("禁用"):$(obj).text("正常");
					locked==1?$(obj).attr("class","btn btn-danger"):$(obj).attr("class","btn btn-info");
					//$(obj).attr("class",hot==1?"btn btn-danger":"btn btn-info");
					$(obj).attr("style","margin-top:-5px");
				}
			});
		}
	</script>

</body>
</html>