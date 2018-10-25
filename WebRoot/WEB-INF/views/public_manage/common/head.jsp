<%@page import="java.util.Date"%>
<%@page import="com.eqy.utils.DateTransfer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<div class="coupon_top">
	<div class="coupon1200">
		<div class="coupon_top_right">
			<div class="coupon_esc">
				<a href="<c:url value="/login/out"/>">退出</a>
			</div>
			<div class="coupon_user">
				<div class="middle">
					<p>
					
						<span>用&nbsp;户&nbsp;&nbsp;名：${user.username}</span>
					</p>
					<p>
						<span>所属工厂：${user.factoryname}</span>
					</p>
				</div>
			</div>
		</div>
		<%  
		   String date=DateTransfer.DateChangeA(new Date());
		   String weekday=DateTransfer.getWeekOfDate(new Date());
		%>
		<div class="coupon_marquee">
			<marquee behavior="" direction=""><%=date%>  <%=weekday%>    欢迎回來: ${user.username}</marquee>
		</div>
		<div class="coupon_logo">
			<img src="<c:url value="/resources/images/logo_03.png"/>" />
		</div>
	</div>
</div>