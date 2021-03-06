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
<title>用户详情</title>
<link href="<c:url value="../resources/css/coupon.css"/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/common/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/common/coupon.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/users/userManage.js"/>"></script>
</head>
<body>

	<!-- 公共头部开始 -->
	<jsp:include page="../public_manage/common/head.jsp" />
	<!-- 公共头部结束 -->

	<div class="coupon_contain">
		<div class="coupon_crumbs">
			<a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
			</a> > <a href="<c:url value="/userManage/showUserManage?pageNum=${pageNum}"/>"><span class="cur">用户管理</span></a> > <span class="cur1">用户详情</span>
		</div>

		<div class="quanxian_line"></div>
		<div class="quanxian_box">
			<div class="quanxian_info">
				<div class="quanxian_table">
					<table width="100%" border="0">

						<tr>
							<td class="quanxian_name">账户:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.fAccountNumber}</div></td>
						</tr>
						<tr>
							<td class="quanxian_name">联系电话:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.fUserNumber}</div></td>
						</tr>
						<%-- 
						<tr>
							<td class="quanxian_name">归属渠道:</td>
							<td class="quanxian_tianjia">
							    <c:choose>
							      <c:when test="${userBean.fUserLevel == '0' }">
							       <div class="quanxian_input">--</div>
							      </c:when>
							      <c:otherwise>
							       <div class="quanxian_input">${userBean.channelName}</div>
							      </c:otherwise>
							    </c:choose>
								</td>
						</tr>
						--%>
						<tr>
							<td class="quanxian_name">角色:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.roleName}</div></td>
						</tr>

						<tr>
							<td class="quanxian_name">用户名:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.username}</div></td>
						</tr>
						<tr>
							<td class="quanxian_name">邮箱:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.mail}</div></td>
						</tr>
						
						<tr>
							<td class="quanxian_name">部门:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.department}</div></td>
						</tr>
						<tr>
							<td class="quanxian_name">工厂:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.factoryname}</div></td>
						</tr>

						<tr>
							<td class="quanxian_name">是否启用:</td>
							<td class="quanxian_tianjia">
								<div class="quanxian_input">${userBean.fState}</div></td>
						</tr>

					</table>
					<!--                     <div class="quanxianfenpei"><a href="javascript:;">权限分配</a></div> -->
				</div>
			</div>
			<div class="quanxian_btn_queren">
				<!--                 <div class="quanxian_quxiao">取　消</div> -->
				<a href="${path}/userManage/showUserManage?pageNum=${pageNum}"><div
						class="quanxian_queren">确 认</div>
				</a>
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
