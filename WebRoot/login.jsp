<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>南高齿OCR图像数据识别系统</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/coupon.css"/>">
<script type="text/javascript"
	src="<c:url value="/resources/js/common/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/common/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/login.js"/>"></script>
</head>
<body>
	<div class="coupon_top">
		<div class="coupon1200">
			<div class="coupon_logo">
				<img src="<c:url value="/resources/images/logo_03.png"/>" alt="" />
			</div>
			<div class="coupon_pingtai">
				<div class="coupon_pingtai_list">
					<!-- <ul>
						<li><a href="javascript:;">运营管理平台</a></li>
						<li><a href="javascript:;">营销活动模板化平台</a></li>
						<li><a href="javascript:;">淘流量</a></li>
					</ul>
					 -->
				</div>
			</div>
		</div>
	</div>
	<div class="coupon_Login">
		<div class="coupon_loginBox">
			<div class="coupon_loginInfo">
				<input type="text" class="coupon_loginInfo_user" value=""
					id="userAccount" /> <input type="password"
					class="coupon_loginInfo_psw" value="" id="userPassword" /> <a
					href="javascript:;" onclick="LoginJS.login()">
					<div class="login_btn"></div> </a>
			</div>
		</div>
	</div>
	<div class="coupon_bottom">
		<div class="version">南高齿OCR图像数据识别系统  版本号：V2018</div>
		<div class="copyright">翼企云提供技术支持</div>
	</div>
	<div class="mask hide"></div>
	<div class="coupon_tanckuang hide" id="bombBox1">
		<div class="coupon_tankuang_title">
			<img src="<c:url value="/resources/images/icon1.png"/>" alt="" /> <span
				id="bombBoxTitle1">提示</span>
			<div class="close" onclick="closeDialog1()"></div>
		</div>
		<div class="coupon_tankuang_info">
			<div class="coupon_tankuang_text" id="bombBoxText1"></div>
			<div class="coupon_btn">
				<div class="coupon_queren" onclick="closeDialog1()"
					style="margin-left: 28%;">确认</div>
			</div>
		</div>
	</div>
</body>
</html>