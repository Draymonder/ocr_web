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
<title>新建用户</title>
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
	src="<c:url value="../resources/js/users/createUser.js"/>"></script>
</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->
	
<div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span 
            class="coupon_index">首页</span></a> > <a 
            href="<c:url value="/userManage/showUserManage?pageNum=${pageNum}"/>"><span 
            class="cur">用户管理</span></a> > <span class="cur1">新建用户</span>
    </div>
    
    <div class="quanxian_line"></div>
        <div class="quanxian_box">
            <div class="quanxian_info">
                <div class="quanxian_table">
                    <table width="100%" border="0">
                        
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>账户:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="userAccount"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>密码:</td>
                            <td class="quanxian_tianjia">
                                <%-- 
                                <input style="position: absolute;left: 9999999px" />
                                --%>
                                <input type="password" class="quanxian_long" value="" id="userPassword" maxlength="12"/>
                            </td>
                        </tr>
                        <tr>
                           <td><div class="tishiyu_pwd">（密码为6-12位英文字母、数字组成。）</div></td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>用户姓名:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="username"/>
                            </td>
                        </tr>
                         <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>部门名称:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="department"/>
                            </td>
                        </tr>
                         <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>联系电话:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="userNumber"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>角色:</td>
                            <td class="quanxian_tianjia">
                                <div  class="quanxian_input jiaose" id="userLevel"></div>    
                                <div class="quanxian_anniu"></div>
                                <!--下拉内容-->
                                <div class="quanxian_jiaose quanxian_zhankai">
                                    <ul>
                                    <c:forEach items="${roleMap}" var="role" varStatus="status">
                                    <li onclick="CreateUserJS.showCity('${role.id}')">${role.roleName}</li>                               
                                    </c:forEach>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>工厂:</td>
                            <td class="quanxian_tianjia">
                                <div  class="quanxian_input jiaose" id="factory"></div>    
                                <div class="quanxian_anniu"></div>
                                <!--下拉内容-->
                                <div class="quanxian_jiaose quanxian_zhankai">
                                    <ul>
                                    <c:forEach items="${factoryMap}" var="factory" varStatus="status">
                                    <li onclick="CreateUserJS.showCity111('${factory.id}')">${factory.name}</li>                               
                                    </c:forEach>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        
                        <%-- 
                         <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>邮箱:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="mail"/>
                                
                            </td>
                           
                        </tr>
                        <tr>
                         <td>
                        <input class="button" onclick="CreateUserJS.mail()" style="color:red" value="获取邮箱验证码"></input>
                        </td>   
                        </tr>
                         <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>验证码:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="code"/>
                                
                            </td>
                           
                        </tr>
                        --%>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>是否启用:</td>
                            <td class="quanxian_tianjia">
                                <div  class="quanxian_input qiyong" id="userState">已启用</div>
                                <div class="quanxian_anniu"></div>

                                <div class="quanxian_qiyong quanxian_zhankai">
                                    <ul>
                                        <li value="0" onclick="CreateUserJS.selectUseState('0')">已启用</li>
                                        <li value="1" onclick="CreateUserJS.selectUseState('1')">未启用</li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="quanxian_btn">
                <a href="${path}/userManage/showUserManage?pageNum=${pageNum}"><div class="quanxian_quxiao">取　消</div></a>
                <div class="quanxian_queren" onclick="CreateUserJS.createUser()">确　认</div>
            </div>
		</div>





</div>
	<!--确认创建用户弹窗 begin-->
	<div class="coupon_tanckuang hide" id="confirmCreateUser">
		<div class="coupon_tankuang_title">
			<img src="../resources/images/tijiao_03.png" alt=""> 创建用户
			<div class="close"></div>
		</div>

		<div class="coupon_tankuang_info">
			<div class="coupon_tankuang_text">创建用户成功</div>
			<div class="coupon_btn">
				
				<div class="coupon_queren"
					onclick="CreateUserJS.confirmCreateUser()">确认</div>
			</div>
		</div>
	</div>
	<!--确认创建用户弹窗 end-->


<!-- 公共尾部开始 -->
<jsp:include page="../public_manage/common/foot.jsp" />
<!-- 公共尾部结束  -->

<!-- 公共弹窗开始 -->
<jsp:include page="../public_manage/common/bomb_box.jsp" />
<!-- 公共弹窗结束  -->
</body>
</html>
