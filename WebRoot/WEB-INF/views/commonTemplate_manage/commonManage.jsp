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
            src="<c:url value="../resources/js/common/jquery-1.5.1.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/lyz.calendar.min.js"/>"></script>
    
    <script type="text/javascript" 
    src="<c:url value="../resources/js/common/timeTask/WdatePicker.js"/>"></script>            
            
    <%--<script type="text/javascript"--%>
            <%--src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>--%>
    <script type="text/javascript"
            src="<c:url value="../resources/js/CommonTemplate/CommonManage.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/common.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/coupon.js"/>"></script>

    <script>
        $(function () {
          

        });

    </script>
   
</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->

 <div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
        </a> > <a href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=1"><span
            class="cur">自定义模板</span>
    </a>
    </div>

    <div class="figure_model_ctrl">
        <div class="fmc_search">
            <div class="fmc_date">
                <div class="fmc_date_txt"  >
                 <input id="txtBeginDate" onfocus="WdatePicker({maxDate:'%y-%M-%d'})"  style="vertical-align:  middle;margin-top: 13px;"  placeholder="—— 请选择开始日期 ——" /> 
                </div>
            </div>
             &nbsp;&nbsp;&nbsp;
            <div class="fmc_date">
                <div class="fmc_date_txt"  >
                 <input id="txtBeginDate1" onfocus="WdatePicker({maxDate:'%y-%M-%d'})"  style="vertical-align:  middle;margin-top: 13px;"  placeholder="—— 请选择结束日期 ——" /> 
                </div>
            </div>
            <div class="fmc_search_input">
                <div class="fmc_search_input_txt">
                    <input type="text" name="" id="templateName" value="" placeholder="请输入模板名称"
                           onfocus="javascript:if(this.value=='请输入模板名称')this.value='';"
                           onblur="javascript:if(this.value=='')this.value='请输入模板名称';" />
                </div>
                <div class="fmc_search_input_img">
                    <img src="../resources/images/public_img/ic_search.png"
                         onclick = "TemplateManageJS.queryTemplateByCondition('1')"/>
                </div>
            </div>
             <div style="margin-top:-27px">
              <a  href="${path}/CommonTemplateManage/uploadCommonTemplate">
			 <div class="miaosha-add" style=""><img src="../resources/images/add_03.png" alt="">
			 添加自定义模版</div> </a>
			 </div>
        </div>
        <div class="fmc_table">
            <table border="0" cellspacing="" cellpadding="" id="templateList">
                <tr class="tr_h">
                    <th>模板名称</th>
                    <th>所属工厂</th>
                    <th>创建人</th>
                    <th>创建日期</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                <c:choose>
                    <c:when test="${fn:length(page.list) > 0}">
                        <c:forEach items="${page.list}" var="CommonTemplateBean" varStatus="order">
                            <tr class="tr_d">
                                <td>${CommonTemplateBean.template_name}</td>
                                <td>${CommonTemplateBean.factoryName}</td>
                                <td>${CommonTemplateBean.account_number}</td>
                                <td>${CommonTemplateBean.simpledate}</td>
                                <td>${CommonTemplateBean.remark}</td>
                                <td>
                                    <span class="coupon_submit" ><a target="view_window" style="color:#FFFFFF" href="${path}/CommonTemplateManage/showEditTemplate?pageNum=${page.pageNum}&templatename=${CommonTemplateBean.template_name}"
									class="">编辑</a>
                                    </span>
                                    <span class="coupon_pass"><a style="color:#FFFFFF" href="javascript:;" onclick="TemplateManageJS.showRemoveUser('${CommonTemplateBean.template_name}')"
									class="">删除</a>
                                    </span>
                                    <span class="coupon_chakan" ><a target="view_window" style="color:#FFFFFF" href="${path}/CommonTemplateManage/recreateTemplate?fileName=${CommonTemplateBean.filename}&templateName=${CommonTemplateBean.template_name}"
									class="">详情</a>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="11">未查询到符合条件的记录</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </div>
    <div class="coupon_pageList_query">
        <c:choose>
            <c:when test="${fn:length(page.list) > 0 }">
                <c:choose>
                    <c:when test="${page.pageNum == page.first}">
                        <a href="javascript:;" data-page="${page.prev}">上一页</a>
                    </c:when>
                    <c:otherwise>
                        <a
                                href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${page.prev}"
                                class="">上一页</a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${page.pageCount <= 6}">
                        <c:forEach var="nav" begin="1" end="${page.pageCount}">
                            <c:choose>
                                <c:when test="${page.pageNum == nav}">
                                    <a href="javascript:;" data-page="${nav}" class="current">${nav}</a>
                                </c:when>
                                <c:otherwise>
                                    <a
                                            href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${nav}"
                                            data-page="${nav}">${nav}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${page.pageNum <= 3}">
                                <c:forEach var="nav" begin="1" end="4">
                                    <c:choose>
                                        <c:when test="${page.pageNum == nav}">
                                            <a href="javascript:;" data-page="${nav}" class="current">${nav}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a
                                                    href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <a>...</a>
                                <a
                                        href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${page.pageCount}"
                                        data-page="${page.pageCount}">${page.pageCount}</a>
                            </c:when>
                            <c:when test="${page.pageNum > page.pageCount - 3}">
                                <a
                                        href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=1"
                                        data-page="1">1</a>
                                <a>...</a>
                                <c:forEach var="nav" begin="${page.pageCount - 3}"
                                           end="${page.pageCount}">
                                    <c:choose>
                                        <c:when test="${page.pageNum == nav}">
                                            <a href="javascript:;" data-page="${nav}" class="current">${nav}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a
                                                    href="${path}//CommonTemplateManage/showCommonTemplateManage?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <a
                                        href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=1"
                                        data-page="1">1</a>
                                <a>....</a>
                                <a
                                        href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${page.pageNum-1}"
                                        data-page="${page.pageNum-1}">${page.pageNum-1}</a>
                                <a href="javascript:;" data-page="${page.pageNum}"
                                   class="current">${page.pageNum}</a>
                                <a
                                        href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${page.pageNum+1}"
                                        data-page="${page.pageNum+1}">${page.pageNum+1}</a>
                                <a>...</a>
                                <a
                                        href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${page.pageCount}"
                                        data-page="${page.pageCount}">${page.pageCount}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${page.pageNum == page.last || page.next == 0}">
                        <a href="javascript:;" data-page="${page.next}">下一页</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=${page.next}"
                           class="">下一页</a>
                        <%--<span class="total-num">共${page.pageCount}页</span>--%>
                    </c:otherwise>
                </c:choose>
                <span class="total-num">共${page.pageCount}页</span> 跳转第 <input
                    type="text" value="${page.pageNum}" id="go-to" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onblur="this.value=this.value.replace(/[^\d]/g,'') "> 页 <a
                    href="javascript:;" class="certain-btn"
                    onclick="TemplateManageJS.goToPage('${page.pageCount}')">确定</a>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
    </div>
 </div>

<!-- 公共尾部开始 -->
<jsp:include page="../public_manage/common/foot.jsp" />
<!-- 公共尾部结束  -->

<!-- 公共弹窗开始 -->
<jsp:include page="../public_manage/common/bomb_box.jsp" />
<!-- 公共弹窗结束  -->

<!--确认注销用户弹窗 begin-->
<div class="coupon_tanckuang hide" id="confirmRemoveUser">
    <div class="coupon_tankuang_title">
        <img src="../resources/images/tijiao_03.png" alt=""> 注销模板
        <div class="close"></div>
    </div>

    <div class="coupon_tankuang_info">
        <div class="coupon_tankuang_text">是否确认注销？</div>
        <div class="coupon_btn">
            <div class="coupon_quxiao">取消</div>
            <div class="coupon_queren"
                 onclick="TemplateManageJS.confirmRemoveUser()">确认</div>
        </div>
    </div>
</div>
<!--确认注销用户弹窗 end-->
</body>
</html>


