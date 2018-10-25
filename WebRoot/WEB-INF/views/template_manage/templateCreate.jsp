<%--
  Created by IntelliJ IDEA.
  User: chengkang
  Date: 2018/6/8
  Time: 上午10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>自定义模板</title>
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
            src="<c:url value="../resources/js/common/lyz.calendar.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/template/templateManage.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/template/datactrl.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/common.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/coupon.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/template/templateManage.js"/>"></script>
    
</head>
<body>
<script type="">

$(function(){
        var msg=$("#msg").html();
		if(msg!=""){
	        var xx=msg.substring(msg.indexOf(":")+1,msg.length);
	        $("#templateName").val(xx);
            if(msg == " ") return;
            showDialog1(msg);
		}
});

</script>
<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->

<div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
        </a> ><a href="${path}/templateManage/showTemplateManage?pageNum=1">
        <span class="cur">定制模板—计量报告</span>
        <span id="msg" style="display:none">${msg}</span>
        <span id="result1" style="display:none">${result}</span>
        <span id="fileName" style="display:none">${fileName}</span>
        <span id="width" style="display:none">${width}</span>
        <span id="height" style="display:none">${height}</span>
    </a>>
    </div>
    <div class="data_ctrl">
        <div class="data_ctrl_top" >
            <img src="../resources/images/public_img/ic_visibility.png"/>
            模板预览
        </div>
        <div class="data_ctrl_C">
            <div class="data_ctrl_C_lf">

                <div class="data_ctrl_C_lf_sf">
                    <div class="data_ctrl_C_lf_sf_1">
                        -
                    </div>
                    <div class="data_ctrl_C_lf_sf_2">
                        100%
                    </div>
                    <div class="data_ctrl_C_lf_sf_3">
                        +
                    </div>
                </div>

                <div class="data_ctrl_C_lf_page">
                    <c:forEach  items="${jpgList}" var="showDateBean"   varStatus="st"   >
                        <c:choose>
                            <c:when test="${st.count==1}">
                                <div class="page_num choose_page" data-page="${st.count}"  data-src="${showDateBean}">${st.count}</div>
                            </c:when>
                            <c:otherwise>
                                <div class="page_num " data-page="${st.count}"  data-src="${showDateBean}">${st.count}</div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <%--
                    <div class="page_num" data-page=2  data-src="${fileNameB}">
                        2
                    </div>
                    --%>
                </div>

                <div class="data_ctrl_C_lf_img"  ondrop="drop(event)" ondragover="allowDrop(event)">
                    <img  ondragstart="dragStart(event)" ondrag="drag(event)" ondragend="dragEnd(event)" draggable="true" id="dragtarget" width="100%" src="${jpgList[0]}"/>
                    <div class="img_meng" id="bd" onmousedown="huoqu()" onmouseup="dingge()" >
                        <div id="coords"></div>
                        <div id="signSpanId" onclick="clea()"></div>
                        <div class="printscreen_over" id="printscreen_over">
                            <div class="printscreen_over_cel" >
                                ×
                            </div>
                            <div class="printscreen_over_sure">
                                √
                            </div>
                        </div>
                    </div>
                    <div class="data_ctrl_kuang">

                    </div>
                </div>

            </div>
            <div class="data_ctrl_C_rg">
                <div class="data_ctrl_C_rg_btn">
                    <div class="data_ctrl_C_rg_btn_printscreen">
                        <img src="../resources/images/public_img/printscreen.png"/>
                    </div>
                    <div class="data_ctrl_C_rg_btn_cancel">
                       点击图标进行区域捕捉
                    </div>
                </div>
                <div class="data_ctrl_C_rg_select">
                    <div class="data_ctrl_C_rg_select_title">
                        <div id="title_div">请选择要保存的字段</div>
                        <div id="jt"></div>
                        <div class="clear"></div>
                    </div>
                    <div class="data_ctrl_C_rg_select_list">
                        <c:choose>
                            <c:when test="${result!=''}">
                                <c:forEach var="map" items="${nameMap}">
                                    <div class="data_ctrl_C_rg_select_li" data-id="${map.key}" data-name="${map.value}">
                                        <div>${map.value}</div>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="fieldName" items="${nameList}">
                                    <div class="data_ctrl_C_rg_select_li" data-name="${fieldName}">
                                        <div>${fieldName}</div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="data_ctrl_C_rg_input">
                    <div class="data_ctrl_C_rg_input_1">
                        识别结果：
                    </div>
                    <div class="data_ctrl_C_rg_input_2">
                        <input type="text" name="" id="result" value="" placeholder="" readonly="readonly" />
                    </div>
                </div>
                <div class="data_ctrl_C_rg_input">
                    <div class="data_ctrl_C_rg_input_1">
                        模板名称：
                    </div>
                    <div class="data_ctrl_C_rg_input_2">
                         <c:choose>
										<c:when test="${msg!=''}">
											 <input type="text" name="" id="templateName"  readonly="readonly" value=""  />
												
										</c:when>
										<c:otherwise>
											 <input type="text" name="" id="templateName"  onchange="TemplateManageJS.countBean()" value="" placeholder="请输入模板名称" />  
										</c:otherwise>
						 </c:choose> 
                        
                    </div>
                </div>
                 <div class="data_ctrl_C_rg_input">
                    <div class="data_ctrl_C_rg_input_1">
                       模板备注：
                    </div>
                    <div class="data_ctrl_C_rg_input_2">
                        <input type="text" name="" id="remark" value="" placeholder="请输入模板备注" />
                    </div>
                </div>
                <div class="data_ctrl_C_rg_save" style="margin-left: 25px;margin-right: 60px;background-color: #1497e1;float: left;">
                    提交字段坐标
                </div>
               <div class="data_ctrl_C_rg_submit" onclick="TemplateManageJS.Submit()" style="background-color: #FF001F;width: 120px;text-align: center;color: #fff;margin-top: 20px;height: 45px;line-height: 45px;font-size: 17px;cursor: pointer;float: left;margin-left:20px">
                    完成制作
                </div>
                
             
                <!--<div class="" style="">
                    <div id="coord" style="width:150px;border:2px solid #336699;">&nbsp;</div>
                    <div id="coord2" style="width:150px;border:2px solid #336699;">&nbsp;</div>
                    <div id="coord3" style="width:150px;border:2px solid #336699;">&nbsp;</div>
                </div>-->
            </div>

        </div>
    </div>
</div>

<!-- 公共尾部开始 -->
<jsp:include page="../public_manage/common/foot.jsp" />
<!-- 公共尾部结束  -->

<!-- 公共弹窗开始 -->
<jsp:include page="../public_manage/common/bomb_box.jsp" />
<!-- 公共弹窗结束  -->

<!--确认注销用户弹窗 begin-->
<div class="coupon_tanckuang hide" id="confirmSubmit">
    <div class="coupon_tankuang_title">
        <img src="../resources/images/tijiao_03.png" alt=""> 模板制作提交
        <div class="close"></div>
    </div>

    <div class="coupon_tankuang_info">
        <div class="coupon_tankuang_text">是否确认提交？</div>
        <div class="coupon_btn">
            <div class="coupon_quxiao">取消</div>
            <div class="coupon_queren"
                 onclick="TemplateManageJS.confirmSubmit()">确认</div>
        </div>
    </div>
</div>
<!--确认注销用户弹窗 end-->
</body>
</html>



