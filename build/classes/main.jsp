<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" tagdir="/WEB-INF/tags"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>OCR</title>
<link href="<c:url value="/resources/css/revision.css"/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/common/jquery-1.8.0.min.js"/>"></script>
</head>
<body>
	<jsp:include page="public_manage/common/head.jsp" />
	<div class="coupon_contain_new">
		<permission:hasAnyPermissions name="yonghu">
			<div class="index_list" style="display: none;" id="box_div9">
				<ul>
					<shiro:hasPermission name="yonghu">
						<li><a href="${path}/templateManage/showTemplateManage?pageNum=1">
							<span class="jiekouguanli"> </span>
							<div class="text">模板管理</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="yonghu">
						<li><a href="${path}/templateManage/uploadTemplate">
							<span class="hezuoquanguishuqudao"> </span>
							<div class="text">数字化建模管理</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="yonghu">
						<li><a
								href="${path}/interfaceSwith/showInterfaceList?pageNum=1"> <span
								class="yonghu"> </span>
							<div class="text">数字转换任务管理</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="jiekouguanli">
						<li><a href="${path}/actCard/tmp"> <span
								class="jiekouguanli"> </span>
							<div class="text">前端数据临时管理区</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="redis">
						<li>
							<a href="${path}/ziquanredis/rrredis11?pageNum=1">
								<span class="huancunguanli"> </span>
								<div class="text">缓存</div>
							</a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="fenleimokuai">
						<li><a href="${path}/Modelclassification/showModel?pageNum=1">
							<span class="zhutipeizhi"></span>
							<div class="text">分类模块</div> </a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</permission:hasAnyPermissions>
		<!-- 系统管理  -->
		<permission:hasAnyPermissions
			name="yonghu,hezuoquanguishuqudao,jiekouguanli,redis,fenleimokuai">
			<div class="index_list" style="display: none;" id="box_div8">
				<ul>
					<shiro:hasPermission name="yonghu">
						<li><a href="${path}/userManage/showUserManage?pageNum=1">
								<span class="yonghu"> </span>
								<div class="text">用户管理</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="yonghu">
						<li><a href="${path}/channel/showChannelList?pageNum=1">
								<span class="hezuoquanguishuqudao"> </span>
								<div class="text">合作渠道管理</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="jiekouguanli">
						<li><a
							href="${path}/interfaceSwith/showInterfaceList?pageNum=1"> <span
								class="jiekouguanli"> </span>
								<div class="text">接口管理</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="jiekouguanli">
					<li><a href="${path}/actCard/tmp"> <span
							class="jiekouguanli"> </span>
							<div class="text">前端数据临时管理区</div> </a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="redis">
						<li>
							<a href="${path}/ziquanredis/rrredis11?pageNum=1">
								<span class="huancunguanli"> </span>
								<div class="text">缓存</div>
							</a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="fenleimokuai">
						<li><a href="${path}/Modelclassification/showModel?pageNum=1">
								<span class="zhutipeizhi"></span>
								<div class="text">分类模块</div> </a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</permission:hasAnyPermissions>
		<div class="left_list">
			<div class="left_index">
				<a href="<c:url value="/login/main?type=1"/>"> <img
					src="${path}/resources/images/left_icon1.png" /> <span>首页</span>
					<span> <img src="${path}/resources/images/left_arrow.png" />
				</span> </a>
			</div>
			<ul>
				<permission:hasAnyPermissions
					name="youhuiquan,wentipaicha,shujutongji">
					<a href="${path}/login/main?type=1">
						<li id="left_nav1"><img
							src="${path}/resources/images/left_icon7.png" /> <span>查询管理</span>
					</li> </a>
				</permission:hasAnyPermissions>

				<permission:hasAnyPermissions name="xinjian,hezuoshanghu">
					<a href="${path}/login/main?type=2">
						<li id="left_nav2"><img
							src="${path}/resources/images/left_icon2.png" /> <span>合作券管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				
				<permission:hasAnyPermissions name="huodongquanweihu">
					<a href="${path}/login/main?type=3">
						<li id="left_nav3"><img
							src="${path}/resources/images/left_icon9.png" /> <span>活动券管理</span>
					</li> </a>
				</permission:hasAnyPermissions> 
				<permission:hasAnyPermissions name="daitijiao,daixiugai">
					<a href="${path}/login/main?type=4">
						<li id="left_nav4"><img
							src="${path}/resources/images/left_icon3.png" /> <span>待提交管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions name="shenheguanli,chehui,daishenhe">
					<a href="${path}/login/main?type=5">
						<li id="left_nav5"><img
							src="${path}/resources/images/left_icon4.png" /> <span>运营审核</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions name="ceshi,baimingdan">
					<a href="${path}/login/main?type=6">
						<li id="left_nav6"><img
							src="${path}/resources/images/left_icon5.png" /> <span>测试管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions name="lingquanzhongxinweihu">
					<a href="${path}/login/main?type=9">
						<li id="left_nav9"><img
							src="${path}/resources/images/left_icon9.png" /> <span>领券中心维护</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions name="fabu,fabushenghe">
					<a href="${path}/login/main?type=7">
						<li id="left_nav7"><img
							src="${path}/resources/images/left_icon6.png" /> <span>发布管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions
					name="yonghu,hezuoquanguishuqudao,jiekouguanli">
				<a href="${path}/login/main?type=8">
					<li id="left_nav8"><img
							src="${path}/resources/images/left_icon8.png" /> <span>系统管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions
						name="yonghu,hezuoquanguishuqudao,jiekouguanli">
					<a href="${path}/login/main?type=9">
						<li id="left_nav10"><img
								src="${path}/resources/images/left_icon9.png" /> <span>数据管理</span>
						</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions
						name="yonghu,hezuoquanguishuqudao,jiekouguanli">
					<a href="${path}/login/main?type=10">
						<li id="left_nav11"><img
								src="${path}/resources/images/left_icon3.png" /> <span>展示管理</span>
						</li> </a>
				</permission:hasAnyPermissions>
			</ul>
		</div>
	</div>
	<jsp:include page="public_manage/common/foot.jsp" />
</body>
<script type="text/javascript">
    $(document).ready(function()
    {
        var type = "${type}";
        $("#left_nav" + type).addClass("cur");
        $("#box_div" + type).show();
    });
</script>
</html>
