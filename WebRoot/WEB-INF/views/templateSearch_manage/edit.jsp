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
<title>数据详情</title>
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
	src="<c:url value="../resources/js/templateSearch/Edit.js"/>"></script>
	<style type="text/css">
		.pop_mouseover_img{
			position: absolute;
			display: none;
			max-width: 620px;
			z-index: 10000;
		}
	</style>
</head>
<body>
	<script>
		$(function () {
			var fileFullName = $("#xjgz_title").attr("name");
			var fileName = fileFullName.substring(0, fileFullName.lastIndexOf("."));
			var src = "../uploads/temp/"+fileName+"/";
            function getMousePos(event) {
                var e = event || window.event;
                var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
                var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
                var x = e.pageX || e.clientX + scrollX;
                var y = e.pageY || e.clientY + scrollY;
                //alert('x: ' + x + '\ny: ' + y);
                return { 'x': x, 'y': y };
            }
		    $(".td1").each(function(){
				
				var a=$(this).attr("title");
				var imgSrc = src + a+".jpg";//小图路径拼接
				//alert(src);
				getName(a,imgSrc);
				
			})
			function getName(a,imgSrc){
		    	$("#"+a).mousemove(function () {
					var x = getMousePos(event).x;
					var y = getMousePos(event).y;
					$(".pop_mouseover_img").css('top',y+"px");
					$(".pop_mouseover_img").css('left',x+10+"px");
					$(".pop_mouseover_img img").attr("src",imgSrc);
					$(".pop_mouseover_img").show();

		    	})
		        $("#"+a).mouseout(function () {

                $(".pop_mouseover_img").hide();
                })
		    }

        })
	</script>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->
	<div class="xjgz">
			<div class="coupon_crumbs" style="margin-top:-20px;margin-left:0px;">
<a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
    </a> > <a href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${pageNum}"><span
class="coupon_index">历史任务查询</span></a>><span class="cur1">数据详情</span>
			</div>
			<div class="xjgz_title" id="xjgz_title" name="${data.fileNumber}" title="${data.id}">文件名称:${data.fileNumber},创建账号:${data.accountNumber}</div>
			<div class="xjgz_table">
				<div class="xjgz_table_th">
					<div class="th1">
						字段名
					</div>
					<div class="th2">
						数据
					</div>
					<div class="th3">
						描述
					</div>
				</div>
				
				
				<div class="xjgz_table_tr">
				<c:forEach  items="${showList}" var="showDateBean">
					<div class="xjgz_table_td">
						<div class="td1" title="${showDateBean.columns}">
							${showDateBean.fieldName}
						</div>
						<div class="pop_mouseover_img">
							<img src="../resources/images/public_img/ic_visibility.png"/>
						</div>
						   <c:choose>

							<c:when test="${showDateBean.flag==0}">    
							<div class="td2" style="color:#CD8500">
							</c:when>
							<c:when test="${showDateBean.flag==1}">
							<div class="td2" style="color:red"> 
							</c:when>
							<c:otherwise>
							<div class="td2">
							</c:otherwise>
							</c:choose>
							  <input type="text" class="val" name="" id="${showDateBean.columns}" value="${showDateBean.fieldVal}" placeholder="填写数据" />
						   </div>
						    <c:choose>
						    <c:when test="${showDateBean.flag==0}">   
						    <div class="td3" style="color:#CD8500">
						    </c:when>
						    <c:when test="${showDateBean.flag==1}">   
							<div class="td3" style="color:red"> 
							</c:when>
						    <c:otherwise> 
							<div class="td3"> 
							</c:otherwise> 
							 </c:choose>
							${showDateBean.description}
						   </div>
                      
					</div>
			      </c:forEach>
					
				</div>
			</div>
			<div class="xjgz_sure" onclick="EditJS.EditRule('${pageNum}')">
				确定
			</div>
		</div>
	</body>
</html>
