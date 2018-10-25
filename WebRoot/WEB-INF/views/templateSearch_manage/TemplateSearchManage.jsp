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
    <title>历史任务查询</title>
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
     <%--   <script type="text/javascript"
            src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>--%>
    <script type="text/javascript" 
    src="<c:url value="../resources/js/common/timeTask/WdatePicker.js"/>"></script>       
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/lyz.calendar.min.js"/>"></script>


    <script type="text/javascript"
            src="<c:url value="../resources/js/common/common.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/coupon.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/templateSearch/templatesearchManage.js"/>"></script>
    <style>
    td input{
    zoom: 1.3;
    position: relative;
    margin-right: 7px;
    vertical-align: middle;
    }
    </style>
</head>
<body>
<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->

 <div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
        </a> > <a href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1"><span
            class="cur">历史任务查询</span>
    </a>
    </div>
    
    <div class="figure_model_ctrl">
        <div class="fmc_search">
            <div class="fmc_date">
            <%-- 
                <div class="fmc_date_img">
                                                      开始日期
                </div>
             --%>
                <div class="fmc_date_txt"  >
                   <%--  <input id="txtBeginDate"  type="text" name="" id="" value="" placeholder="—— 请选择开始日期 ——" /> --%>
                     <input id="txtBeginDate" onfocus="WdatePicker({maxDate:'%y-%M-%d'})"  style="vertical-align:  middle;margin-top: 13px;"  placeholder="—— 请选择开始日期 ——" /> 
                </div>
            </div>
            &nbsp;&nbsp;
            <div class="fmc_date">
                <div class="fmc_date_txt"  >
                   <%-- <input id="txtBeginDate1"  type="text" name="" id="" value="" placeholder="—— 请选择结束日期 ——" />--%> 
                   <input id="txtBeginDate1" onfocus="WdatePicker({maxDate:'%y-%M-%d'})"  style="vertical-align:  middle;margin-top: 13px;"  placeholder="—— 请选择结束日期 ——" /> 
                </div>
            </div>
            <div class="fmc_search_input">
                <div class="fmc_search_input_txt"  style="width:150px ">
                    <input type="text" name="" id="templateName" value="" placeholder="请输入模板名称"
                           onfocus="javascript:if(this.value=='请输入模板名称')this.value='';"
                           onblur="javascript:if(this.value=='')this.value='请输入模板名称';" />
                </div>
                 &nbsp;&nbsp;
                 <div class="fmc_search_input_txt"  style="width:150px ">
                    <input type="text" name="" id="filenumber" value="" placeholder="请输入文件名"
                           onfocus="javascript:if(this.value=='请输入文件名')this.value='';"
                           onblur="javascript:if(this.value=='')this.value='请输入文件名';" />
                </div>
                &nbsp;&nbsp;
                <div class="fmc_search_input_txt"  style="width:150px ">
                    <input type="text" name="" id="FactoryName" value="" placeholder="请输入工厂名称"
                           onfocus="javascript:if(this.value=='请输入工厂名称')this.value='';"
                           onblur="javascript:if(this.value=='')this.value='请输入工厂名称';" />
                 </div> 
                 &nbsp;&nbsp;  
                <div class="fmc_search_input_txt" style="width:150px ">
                    <input type="text" name="" id="userName" value="" placeholder="请输入人员名称"
                           onfocus="javascript:if(this.value=='请输入人员名称')this.value='';"
                           onblur="javascript:if(this.value=='')this.value='请输入人员名称';" />
                </div>
            </div>
            <div class="fmc_search_input_img" style="height:50px">
                    <img src="../resources/images/public_img/ic_search.png"
                         onclick = "templateSearchManageJS.queryUserByCondition('1')"/> 
                </div>
        
        </div>
       
           <div class="fmc_search" style="margin-top:10px">
               
                 <input type="checkbox" id="allchecked" style="vertical-align:middle;margin-top: 10px;margin-left: 0px;margin-right: 5px;zoom: 1.4;" onclick="templateSearchManageJS.checkedAll()" ><span style="margin-top:12px">全选</span></input>
                    <input type="button" class="coupon_submit" onclick="templateSearchManageJS.updateStatus('${page.pageNum}')"
	                value="确认"/>
	                <input type="text"  id="Authority" value="${SearchAuthority}" style="display:none" />
           </div>
        
        

        <div class="fmc_table">
            <table border="0" cellspacing="" cellpadding="" id="templateList">
                <tr class="tr_h">
                    <th>序号</th>
                    <th>任务ID</th>
                    <th>模板名称</th>
                    <th>文件名</th>
                    <th>创建人</th>
                    <th>工厂</th>
                    <th>创建日期</th>
                    <th>是否异常</th>
                    <th>是否确认</th>
                    <th>操作</th>
                    
                </tr>
                <c:choose>
                    <c:when test="${fn:length(page.list) > 0}">
                        <c:forEach items="${page.list}" var="Data" varStatus="order" >
                            <tr class="tr_d">
                                <td ><input type="checkbox" id="${Data.id}"  title="${Data.templateName}" ></input>${Data.id}</td>  
                                <td >${Data.taskId}</td>
                                <td >${Data.templateName}</td>
                                <td>${Data.fileNumber}</td>
                                <td>${Data.userName}</td>
                                <td>${Data.factoryName}</td>
                                <td>${Data.simpleTime}</td>
									
									<c:choose>
										<c:when test="${Data.flag2=='0'}">    
										<td style="color:red">是</td>
                                         </c:when>
										<c:otherwise>  
                                           <td>否</td>
                                        </c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${Data.flag1=='0'}">    
										<td style="color:red">未确认</td>
                                         </c:when>
										<c:otherwise>  
                                           <td style="color:green">已确认</td>
                                        </c:otherwise>
									</c:choose>

                                <td>
                                    <span class="">
                                     <a class="coupon_submit" target="_blank" href="${path}/templateSearchManage/showDetail?id=${Data.id}&pageNum=${page.pageNum}"
									class="" target="_blank" >详情</a>
                                    </span>
                                    <span class="">
                                        <%--<a class="coupon_submit" onclick="templateSearchManageJS.download(${Data.fileNumber})">导出</a>--%>
                                        <a class="coupon_download"  href="${path}/uploads/tiff/${Data.fileNumber}" target="_blank" >导出文件</a>
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
                                href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${page.prev}"
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
                                            href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${nav}"
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
                                                    href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <a>...</a>
                                <a
                                        href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${page.pageCount}"
                                        data-page="${page.pageCount}">${page.pageCount}</a>
                            </c:when>
                            <c:when test="${page.pageNum > page.pageCount - 3}">
                                <a
                                        href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1"
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
                                                    href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <a
                                        href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1"
                                        data-page="1">1</a>
                                <a>....</a>
                                <a
                                        href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${page.pageNum-1}"
                                        data-page="${page.pageNum-1}">${page.pageNum-1}</a>
                                <a href="javascript:;" data-page="${page.pageNum}"
                                   class="current">${page.pageNum}</a>
                                <a
                                        href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${page.pageNum+1}"
                                        data-page="${page.pageNum+1}">${page.pageNum+1}</a>
                                <a>...</a>
                                <a
                                        href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${page.pageCount}"
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
                        <a href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=${page.next}"
                           class="">下一页</a>
                        <%--<span class="total-num">共${page.pageCount}页</span>--%>
                    </c:otherwise>
                </c:choose>
                <span class="total-num">共${page.pageCount}页</span> 跳转第 <input
                    type="text" value="${page.pageNum}" id="go-to" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onblur="this.value=this.value.replace(/[^\d]/g,'') "> 页 <a
                    href="javascript:;" class="certain-btn"
                    onclick="templateSearchManageJS.goToPage('${page.pageCount}')">确定</a>
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
        <img src="../resources/images/tijiao_03.png" alt=""> 注销用户
        <div class="close"></div>
    </div>

    <div class="coupon_tankuang_info">
        <div class="coupon_tankuang_text">是否确认注销？</div>
        <div class="coupon_btn">
            <div class="coupon_quxiao">取消</div>
            <div class="coupon_queren"
                 onclick="templateSearchManageJS.confirmRemoveUser()">确认</div>
        </div>
    </div>
</div>
<!--确认注销用户弹窗 end-->
</body>
</html>


