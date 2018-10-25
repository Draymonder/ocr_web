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
<title>编辑模板</title>
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
	src="<c:url value="../resources/js/template/editTemplate.js"/>"></script>
</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!--  公共头部结束 -->
	
<div class="coupon_contain">
    <div class="coupon_crumbs"><a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span></a> > <a href="<c:url value="/templateManage/showTemplateManage?pageNum=${pageNum}"/>"><span class="cur">定制模板—计量报告</span></a> > <span class="cur1">编辑模板</span></div>
    
    <div class="quanxian_line"></div>
        <div class="quanxian_box">
            <div class="quanxian_info">
                <div class="quanxian_table">
                    <table width="100%" border="0">
                        
                        <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>模板名称:</td>
                            <td class="quanxian_tianjia">
                              
                                <input type="text" class="quanxian_long" value="${TemplateBean.template_name}" id="templatename" title="${TemplateBean.template_name}"/>
                            </td>
                        </tr>
                       

                         <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>创建日期:</td>
                            <td class="quanxian_tianjia">
                                
     
                                <div class="quanxian_input jiaose" id="data" >${TemplateBean.simpledate}</div>
                                
                            </td>
                           
                        </tr>
                        
                         <tr>
                            <td class="quanxian_name"><span style="color: red;">*</span>备注:</td>
                            <td class="quanxian_tianjia">
                                
     
                                 <input type="text" class="quanxian_long" value="${TemplateBean.remark}" id="remark" />
                                
                            </td>
                           
                        </tr>
                        
                        
                       
                    </table>
                    <!-- <div class="quanxianfenpei"><a href="javascript:;">权限分配</a></div> --> 
                </div>
            </div>
            <div class="quanxian_btn">
                <a href="${path}/templateManage/showTemplateManage?pageNum=${pageNum}"><div class="quanxian_quxiao">取　消</div></a>
                <div class="quanxian_queren" onclick="editTemplateJS.editTemplate('${pageNum}')">确　认</div>
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
