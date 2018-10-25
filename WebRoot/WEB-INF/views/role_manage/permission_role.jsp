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
<title>权限分配</title>
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
	src="<c:url value="../resources/js/roles/permissionUser.js"/>"></script>
</head>
<body>

	<!-- 公共头部开始 -->
	<jsp:include page="../public_manage/common/head.jsp" />
	<!-- 公共头部结束 -->

<div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span 
            class="coupon_index">首页</span></a> > <a 
            href="<c:url value="/RoleManage/showRoleManage?pageNum=${pageNum}"/>"><span 
            class="cur">角色管理</span></a> > <span class="cur1">权限分配</span>
    </div>

		<div class="quanxian_line"></div>
		<div class="quanxian_box">
			<div class="quanxian_title">角色</div>
			<div class="quanxian_info">
				<div class="quanxian_left">
					<div class="quanxian_left_list">
						<ul>
							<li class="cur" id="fAccountNumber" >${roleName}</li>
							<li class="cur" id="roleID"  style="display:none">${roleID}</li>
						</ul>
					</div>
				</div>
				<div class="quanxian_zhiding">
					<img src="<c:url value='/resources/images/quanxian_07.png' />" alt="">
				</div>
				<div class="quanxian_right">
					<div class="quanxian_right_list">
						<ul>
							<c:forEach items="${list}" var="userPermissionBean"
								varStatus="status">
								<c:set var="index" value="1" />
								
										<c:if test="${bz==0}">
										<li
										onclick="PermissionUserJS.cancelPermission('${userPermissionBean.id}')">${userPermissionBean.name}
										<div class="quanxian"
											id="${userPermissionBean.id}"></div>
											</li>
										</c:if>
										<c:if test="${bz==1}">
										   <c:forEach items="${list1}" var="rolebean"
								          varStatus="status1">
								                 <c:if test="${rolebean.id==userPermissionBean.id}">
								                   <c:set var="index" value="${index-1}"/>
								                  <li onclick="PermissionUserJS.cancelPermission('${userPermissionBean.id}')">${userPermissionBean.name}
										            <div class="quanxian show"
											        id="${userPermissionBean.id}"></div>
											        </li>
											      </c:if>
											</c:forEach>
									     <c:if test="${index==1}">
									     <li
										onclick="PermissionUserJS.cancelPermission('${userPermissionBean.id}')">${userPermissionBean.name}
										<div class="quanxian"
											id="${userPermissionBean.id}"></div>
											</li>
									     
									     </c:if>
										</c:if>
										
									
							   <%-- 
								</c:if>
								--%>
								<%-- 
								<c:if test="${userPermissionBean.fState=='1' and userPermissionBean.fPermissionCode != 'P10018' and userPermissionBean.fPermissionCode != 'P10021' and userPermissionBean.fPermissionCode != 'P10020' and userPermissionBean.fPermissionCode != 'P10019' and userPermissionBean.fPermissionCode != 'P10017'}">
									<li
										onclick="PermissionUserJS.cancelPermission('${userPermissionBean.fPermissionCode}')">${userPermissionBean.moduleName}
										<div class="quanxian"
											id="${userPermissionBean.fPermissionCode}"></div>
									</li>
								</c:if>
								--%>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div style="clear:both"></div>
			<div class="quanxian_btn">
				<a href="${path}/RoleManage/showRoleManage?pageNum=${pageNum}"><div
						class="quanxian_quxiao">取 消</div> </a>
				<div class="quanxian_queren"
					onclick="PermissionUserJS.permissionUser('${pageNum}')">确 认</div>
			</div>


		</div>

	</div>

	<!-- 公共尾部开始 -->
	<jsp:include page="../public_manage/common/foot.jsp" />
	<!-- 公共尾部结束  -->

	<!-- 公共弹窗开始 -->
	<jsp:include page="../public_manage/common/bomb_box.jsp" />
	<!-- 公共弹窗结束  -->
</body>
</html>
