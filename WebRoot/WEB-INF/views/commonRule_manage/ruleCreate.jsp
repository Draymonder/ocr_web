
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
<title>新建规则</title>
<script type="text/javascript">
    var path = "${path}";
</script>
    <link rel="stylesheet" href="<c:url value ="../resources/css/mui.min.css"/>"
          type="text/css" />
    <link rel="stylesheet" href="<c:url value ="../resources/css/public_x.css"/>"
          type="text/css" />
    <link rel="stylesheet" href="<c:url value ="../resources/css/lyz.calendar.css"/>"
          type="text/css" />
    <link rel="stylesheet" href="<c:url value="../resources/css/coupon.css"/>"
          type="text/css" />
<script type="text/javascript"
	src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/common/common.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/common/coupon.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/login.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="../resources/js/CommonRule/CreateRule.js"/>"></script>
</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->
<div class="newgz">

<div class="coupon_crumbs" style="margin-top:-20px;margin-left:5px;"><a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
    </a> > <a href="${path}/CommonRuleManage/showCommonRuleManage?pageNum=1"><span
class="coupon_index">规则管理</span></a>><span class="cur1">新建规则</span><span id="Template_Id" style="display:none">${template_id}</span></div>
			<div class="newgz_name">
				<div class="newgz_lf">
					规则名称：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_input">
						<input type="text" name="" id="ruleName" value="" placeholder="请输入规则名称" />
					</div>
				</div>
			</div>
			<div class="newgz_table">
			    <div class="newgz_lf">
					规则编号：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_input">
						<input type="text" name="" id="ruleNumber" value="" placeholder="请输入规则编号" />
					</div>
				</div>
			<%-- 
				<div class="newgz_lf">
					请选择表：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_select">
						<div class="newgz_rg_select_title">
							<div id="title_div">请选择要保存的字段</div>
							<div id="jt"></div>
							<div class="clear"></div>
						</div>
						<div class="newgz_rg_select_list">
							<div class="newgz_rg_select_li"  data-id="1" data-name="字段1">
								<div class="li_1">左边内容</div>
								<div class="li_2">右边内容</div>
							</div>
							<div class="newgz_rg_select_li"  data-id="2" data-name="字段2">
								<div class="li_1">左边内容</div>
								<div class="li_2">右边内容</div>
							</div>
							<div class="newgz_rg_select_li"  data-id="3" data-name="字段3">
								<div class="li_1">左边内容</div>
								<div class="li_2">右边内容</div>
							</div>
							<div class="newgz_rg_select_li"  data-id="3" data-name="字段3">
								<div class="li_1">左边内容</div>
								<div class="li_2">右边内容</div>
							</div>
							<div class="newgz_rg_select_li"  data-id="3" data-name="字段3">
								<div class="li_1">左边内容</div>
								<div class="li_2">右边内容</div>
							</div>
							<div class="newgz_rg_select_li"  data-id="3" data-name="字段3">
								<div class="li_1">左边内容</div>
								<div class="li_2">右边内容</div>
							</div>
						</div>
					</div>
				</div>--%>
			</div>
			<div class="newgz_zd">
				<div class="newgz_lf">
					请选择应用字段：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_zdlist">
					<c:forEach items="${colums}" var="map"   begin="0" >
					  <div class="newgz_rg_zdli">
							<input type="checkbox" name="" id="${map.key}" value="" />
							<span>${map.value}</span>
						</div>
					</c:forEach>
					
					</div>
				</div>
			</div>
			<div class="newgz_table">
				<div class="newgz_lf">
					转换规则类型：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_select">
						<div class="newgz_rg_select_title">
							<div id="title_div">请选择要保存的字段</div>
							<div id="jt"></div>
							<div class="clear"></div>
						</div>
						<div class="newgz_rg_select_list">
							<div class="newgz_rg_select_li"  data-id="1" data-name="强转">
								<div class="li_1">强转</div>
								<div class="li_2">内容转换</div>
							</div>
							<div class="newgz_rg_select_li"  data-id="2" data-name="提示">
								<div class="li_1">提示</div>
								<div class="li_2">内容提示</div>
							</div>
								
						</div>
					</div>
				</div>
			</div>
			<div class="newgz_zhgz" id="zhgz1">
				<div class="newgz_lf">
					转换规则：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_zhgz_input">
						<input type="text" name="" id="flagA" value="" placeholder="请输入要转换的字符" />
					</div>
					<img class="newgz_rg_img" src="../resources/images/public_img/icon_zh.png"/>
					<div class="newgz_rg_zhgz_input">
						<input type="text" name="" id="flagB" value="" placeholder="请输入转换后的字符" />
					</div>
				</div>
			</div>
			<div class="newgz_zhgz" id="zhgz2">
				<div class="newgz_lf">
					转换规则：
				</div>
				<div class="newgz_rg">
					<div class="newgz_rg_zhgz_input">
						<input type="text" name="" id="flagC" value="" placeholder="请输入要提示的字符" />
					</div>
					<!--<img class="newgz_rg_img" src="img/public_img/icon_zh.png"/>
					<div class="newgz_rg_zhgz_input">
						<input type="text" name="" id="" value="" placeholder="请输入转换后的字符" />
					</div>-->
				</div>
			</div>
			<div class="newgz_save"  onclick="CreateUserJS.CreateRule()">
				保存
			</div>
		</div>
	</body>
</html>
<!-- 公共弹窗开始 -->
<jsp:include page="../public_manage/common/bomb_box.jsp" />
<!-- 公共弹窗结束  -->
