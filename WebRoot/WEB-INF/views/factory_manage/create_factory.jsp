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
<title>新建工厂</title>
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
	src="<c:url value="../resources/js/factory/createUser.js"/>"></script>
</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->
	
<div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span 
            class="coupon_index">首页</span></a> > <a 
            href="<c:url value="/factoryManage/showfactoryManage?pageNum=${pageNum}"/>"><span 
            class="cur">工厂管理</span></a> > <span class="cur1">新建工厂</span>
    </div>
    
    <div class="quanxian_line"></div>
        <div class="quanxian_box">
            <div class="quanxian_info">
                <div class="quanxian_table">
                    <table width="100%" border="0">
                        <%-- 
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>账户:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="userAccount"/>
                            </td>
                        </tr>
                       
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>密码:</td>
                            <td class="quanxian_tianjia">
                                <input style="position: absolute;left: 9999999px" />
                                <input type="password" class="quanxian_long" value="" id="userPassword" maxlength="12"/>
                            </td>
                        </tr>
                         
                        <tr>
                           <td><div class="tishiyu_pwd">（密码为8-12位英文大小写字母、数字和特殊符号四种字符组成。）</div></td>
                        </tr>
                        --%>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>工厂名称:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="factoryName"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>工厂编号:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="factoryNumber"/>
                            </td>
                        </tr>
                          <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>工厂地址:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="factoryAddress"/>
                            </td>
                        </tr>
                        <%--
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>角色编号:</td>
                            <td class="quanxian_tianjia">
                                <input type="text" class="quanxian_long" value="" id="roleNumber"/>
                            </td>
                        </tr>
                         --%>
                        <%-- 
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>归属渠道:</td>
                            <td class="quanxian_tianjia">
                               
                                <div  class="quanxian_input guishu" id="firstCommercialName"></div>
                                <div class="quanxian_anniu"></div>

                                <!--下拉内容-->
                                <div class="quanxian_guishu quanxian_zhankai">
                                    <ul>
                                     <c:forEach items="${channelList}" var="channel" varStatus="status">
                                    <li onclick="CreateUserJS.showChannelId('${channel.tChannelId}')">${channel.tChannelName}</li>                               
                                    </c:forEach>
                                    </ul>
                                </div>
                                <!--下拉内容end-->
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
                            <td class="quanxian_name"><span style="color: red;">*</span>所属地市:</td>
                            <td class="quanxian_tianjia">
                                <div class="quanxian_input shiqu" id="userCityName"></div>
                                <div class="quanxian_anniu"></div>
                                <div class="quanxian_city quanxian_zhankai">
                                    <ul id="allCity" style="display:none;">
                                        <li>南京市</li>
                                        <li>苏州市</li>
                                        <li>淮安市</li>
                                        <li>宿迁市</li>
                                        <li>徐州市</li>
                                        <li>常州市</li>
                                        <li>镇江市</li>
                                        <li>无锡市</li>
                                        <li>南通市</li>
                                        <li>泰州市</li>
                                        <li>盐城市</li>
                                        <li>扬州市</li>
                                        <li>连云港市</li>                                     
                                    </ul>
                                    <ul id="oneCity" style="display:none;">
                                        <li>江苏省</li>
                                    </ul>
                                    <ul id="sysCity" style="display:none;">
                                        <li>系统</li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        
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
                        </tr>--%>
                    </table>
                </div>
            </div>
            <div class="quanxian_btn">
                <a href="${path}/factoryManage/showfactoryManage?pageNum=${pageNum}"><div class="quanxian_quxiao">取　消</div></a>
                <div class="quanxian_queren" onclick="CreateUserJS.createUser()">确　认</div>
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
