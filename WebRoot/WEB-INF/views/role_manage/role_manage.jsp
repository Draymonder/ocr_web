<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>角色管理</title>
<script type="text/javascript">
    var path = "${path}";
</script>
<link href="<c:url value="../resources/css/coupon.css"/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/common/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/common/coupon.js"/>"></script>
<script type="text/javascript"
	<%--src="<c:url value="../resources/js/users/userManage.js"/>"></script>--%>
	src="<c:url value="../resources/js/roles/userManage.js"/>"></script>
</head>
<body>

	<!-- 公共头部开始 -->
	<jsp:include page="../public_manage/common/head.jsp" />
	<!-- 公共头部结束 -->

	<div class="coupon_contain">
		<div class="coupon_crumbs">
			<a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
			</a> > <a href="${path}/RoleManage/showRoleManage?pageNum=1"><span
				class="cur">角色管理</span>
			</a>
		</div>

		<div class="coupon_search">
			<input type="text" class="coupon_search_info" value="请输入角色编号或者角色名称"
				onfocus="javascript:if(this.value=='请输入角色编号或者角色名称')this.value='';"
				onblur="javascript:if(this.value=='')this.value='请输入角色编号或者角色名称';"
				id="userName" /> <a href="javascript:;" class="coupon_search_btn"><img
				src="../resources/images/search.png" alt=""
				onclick="UserManageJS.queryUserByCondition('1')" /> </a>
		</div>
		<a href="javascript:;" onclick="UserManageJS.createUser('${page.pageNum}')">
			 <div class="miaosha-add"><img src="../resources/images/add_03.png" alt="" />
			 添加角色</div> </a>
		<div class="coupon_table">
			<table border="0" width="100%" id="userList">
				<tr>
					<td class="coupon_table_title">序 号</td>
					<td class="coupon_table_title">角色名称</td>
					<td class="coupon_table_title">角色编号</td>
					<%-- 
					<td class="coupon_table_title">所属地市</td>
					<td class="coupon_table_title">角色</td>
					<td class="coupon_table_title">联系方式</td>
					<td class="coupon_table_title">是否启用</td>
					--%>
					<td class="coupon_table_title">操作</td>
				</tr>

				<c:choose>
					<c:when test="${fn:length(page.list) > 0}">
						<c:forEach items="${page.list}" var="roleBean" varStatus="order">
							<tr>
								<td class="coupon_table_common">${order.index+1+(page.pageNum-1)*page.size}</td>
								<td class="coupon_table_common">${roleBean.roleName}</td>
								<%-- 
								<c:choose>
								  <c:when test="${userBean.fUserLevel == '0' }">
								    <td class="coupon_table_common">--</td>
								  </c:when>
								  <c:otherwise>
								    <td class="coupon_table_common">${userBean.channelName}</td>
								  </c:otherwise>
								</c:choose>
								<td class="coupon_table_common">${userBean.fCity}</td>
								<td class="coupon_table_common">${userBean.roleName}</td>
								<td class="coupon_table_common">${userBean.fUserNumber}</td>
								--%>
								<td class="coupon_table_common">${roleBean.roleNumber}</td>

								<td class="coupon_table_common"><a
									href="${path}/RoleManage/roleDetail?id=${roleBean.id}&pageNum=${page.pageNum}"
									class="coupon_chakan">查看</a> <a
									href="${path}/RoleManage/showEditRole?id=${roleBean.id}&pageNum=${page.pageNum}"
									class="coupon_submit">编辑</a> <a
									href="${path}/RoleManage/showPermission?id=${roleBean.id}&pageNum=${page.pageNum}"
									class="coupon_submit">权限管理</a> <c:choose>
									
										<c:when test="${roleBean.id != '2'&&roleBean.id != '1'&&roleBean.id != '3'}">
											<a href="javascript:;" class="coupon_pass"
												onclick="UserManageJS.showRemoveUser('${roleBean.id}')">注销</a>
										</c:when>
										<c:otherwise>
											<a href="javascript:;" class="coupon_pass gray">注销</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="11">未查询到符合条件的记录</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="coupon_pageList_query">
			<c:choose>
			  <c:when test="${fn:length(page.list) > 0 }">
			     <c:choose>
					<c:when test="${page.pageNum == page.first}">
						<a href="javascript:;" data-page="${page.prev}">上一页</a>
					</c:when>
					<c:otherwise>
						<a
						href="${path}/RoleManage/showRoleManage?pageNum=${page.prev}"
						class="">上一页</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
				  <c:when test="${page.pageCount <= 6}">
				     <c:forEach var="nav" begin="1" end="${page.pageCount}">
							<c:choose>
								<c:when test="${page.pageNum == nav}">
									<a href="javascript:;" data-page="${nav}" class="current">${nav}</a>
								</c:when>
								<c:otherwise>
									<a
										href="${path}/RoleManage/showRoleManage?pageNum=${nav}"
										data-page="${nav}">${nav}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				  </c:when>
				  <c:otherwise>
				      <c:choose>
							<c:when test="${page.pageNum <= 3}">
								<c:forEach var="nav" begin="1" end="4">
									<c:choose>
										<c:when test="${page.pageNum == nav}">
											<a href="javascript:;" data-page="${nav}" class="current">${nav}</a>
										</c:when>
										<c:otherwise>
											<a
												href="${path}/RoleManage/showRoleManage?pageNum=${nav}"
												data-page="${nav}">${nav}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<a>...</a>
								<a
									href="${path}/RoleManage/showRoleManage?pageNum=${page.pageCount}"
									data-page="${page.pageCount}">${page.pageCount}</a>
							</c:when>
							<c:when test="${page.pageNum > page.pageCount - 3}">
								<a
									href="${path}/RoleManage/showRoleManage?pageNum=1"
									data-page="1">1</a>
								<a>...</a>
								<c:forEach var="nav" begin="${page.pageCount - 3}"
									end="${page.pageCount}">
									<c:choose>
										<c:when test="${page.pageNum == nav}">
											<a href="javascript:;" data-page="${nav}" class="current">${nav}</a>
										</c:when>
										<c:otherwise>
											<a
												href="${path}/RoleManage/showRoleManage?pageNum=${nav}"
												data-page="${nav}">${nav}</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<a
									href="${path}/RoleManage/showRoleManage?pageNum=1"
									data-page="1">1</a>
								<a>...</a>
								<a
									href="${path}/RoleManage/showRoleManage?pageNum=${page.pageNum-1}"
									data-page="${page.pageNum-1}">${page.pageNum-1}</a>
								<a href="javascript:;" data-page="${page.pageNum}"
									class="current">${page.pageNum}</a>
								<a
									href="${path}/RoleManage/showRoleManage?pageNum=${page.pageNum+1}"
									data-page="${page.pageNum+1}">${page.pageNum+1}</a>
								<a>...</a>
								<a
									href="${path}/RoleManage/showRoleManage?pageNum=${page.pageCount}"
									data-page="${page.pageCount}">${page.pageCount}</a>
							</c:otherwise>
						</c:choose>
				  </c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${page.pageNum == page.last || page.next == 0}">
						<a href="javascript:;" data-page="${page.next}">下一页</a>
					</c:when>
					<c:otherwise>
						<a
						href="${path}/RoleManage/showRoleManage?pageNum=${page.next}"
						class="">下一页</a> <span class="total-num">共${page.pageCount}页</span>
					</c:otherwise>
				</c:choose>
			    <span class="total-num">共${page.pageCount}页</span> 跳转第 <input
					type="text" value="${page.pageNum}" id="go-to" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onblur="this.value=this.value.replace(/[^\d]/g,'') "> 页 <a
					href="javascript:;" class="certain-btn"
					onclick="UserManageJS.goToPage('${page.pageCount}')">确 定</a>
			   </c:when>
			   <c:otherwise></c:otherwise>
			</c:choose>
			
		</div>

	</div>



	<!-- 公共尾部开始 -->
	<jsp:include page="../public_manage/common/foot.jsp" />
	<!-- 公共尾部结束  -->

	<!-- 公共弹窗开始 -->
	<jsp:include page="../public_manage/common/bomb_box.jsp" />
	<!-- 公共弹窗结束  -->

	<!--确认注销用户弹窗 begin-->
	<div class="coupon_tanckuang hide" id="confirmRemoveUser">
		<div class="coupon_tankuang_title">
			<img src="../resources/images/tijiao_03.png" alt=""> 注销角色
			<div class="close"></div>
		</div>

		<div class="coupon_tankuang_info">
			<div class="coupon_tankuang_text">是否确认注销？</div>
			<div class="coupon_btn">
				<div class="coupon_quxiao">取消</div>
				<div class="coupon_queren"
					onclick="UserManageJS.confirmRemoveUser()">确认</div>
			</div>
		</div>
	</div>
	<!--确认注销用户弹窗 end-->
</body>
</html>
