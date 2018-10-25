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
<title>邮箱服务器</title>
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
	src="<c:url value="../resources/js/mail/editUser.js"/>"></script>
</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!--  公共头部结束 -->
	
<div class="coupon_contain">
    <div class="coupon_crumbs"><a href="<c:url value="/login/main?type=8"/>"><span class="coupon_index">首页</span></a> </div> 
    
    <div class="quanxian_line"></div>
        <div class="quanxian_box">
            <div class="quanxian_info">
                <div class="quanxian_table">
                    <table width="100%" border="0">
                       <%-- 
                        <tr>
                          <td class="quanxian_name"><span style="color: red;">*</span>ID:</td>
                            <td class="quanxian_tianjia">
                                <div class="quanxian_input jiaose" id="mailId">${mail.id}</div>
                           </td>
                        </tr>
                        --%> 
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>邮箱账号:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="${mail.myemailaccount}" id="mailaccount"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>邮箱密码:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="${mail.myemailpassword}" id="mailpassword"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>SMTP:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="${mail.myemailsmtphost}" id="mailsmtphost"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>端口号:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="${mail.smtpport}" id="mailsmtpport"/>
                            </td>
                        </tr>
                      
                       
                    </table>
                    <!-- <div class="quanxianfenpei"><a href="javascript:;">权限分配</a></div> --> 
                </div>
            </div>
            <div class="quanxian_btn">
                <a href="${path}/login/main?type=8"><div class="quanxian_quxiao">取　消</div></a>
                <div class="quanxian_queren" onclick="EditUserJS.editUser('1')">确　认</div>
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
