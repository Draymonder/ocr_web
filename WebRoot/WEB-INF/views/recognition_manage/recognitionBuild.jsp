
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
    <title>新增转换任务</title>
    <script type="text/javascript">
        var path = "${path}";
    </script>
        <link rel="stylesheet" href="<c:url value ="../resources/css/mui.min.css"/>"
          type="text/css" />
       <link rel="stylesheet" href="<c:url value ="../resources/css/recognition.css"/>"
          type="text/css" />
    <link rel="stylesheet" href="<c:url value ="../resources/css/public_x.css"/>"
          type="text/css" />
    <link rel="stylesheet" href="<c:url value ="../resources/css/lyz.calendar.css"/>"
          type="text/css" />
    <link rel="stylesheet" href="<c:url value="../resources/css/coupon.css"/>"
          type="text/css" />
          
     <%--     <script type="text/javascript"
            src="<c:url value="../resources/js/common/jquery-1.5.1.js"/>"></script>  --%>    
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/jquery-3.3.1.min.js"/>"></script> 

    <script type="text/javascript" 
    src="<c:url value="../resources/js/common/timeTask/WdatePicker.js"/>"></script>       

    <script type="text/javascript"
            src="<c:url value="../resources/js/common/lyz.calendar.min.js"/>"></script>
    <%--<script type="text/javascript"--%>
            <%--src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>--%>

    <script type="text/javascript"
            src="<c:url value="../resources/js/common/common.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/coupon.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/task/TaskManage.js"/>"></script>
<style>  
.loadingWrap{  
    position:fixed;  
    top:0;  
    left:0;  
    width:100%;  
    height:100%;  
    z-index:300;  
    background-image:  url(../resources/images/load.gif) ;   
    background-repeat:no-repeat;  
    background-position:center center;  
    background-color:#000; 
    background-color:rgba(0,0,0,0.5);  
    filter:alpha(opacity=50);  
}  
</style>	
</head>

<script type="text/javascript">
    function upload(){
    	
        var f = document.getElementById("upload").files;
        if(f.length==0){
            showDialog1("请选择文件");
            return;
        }

        for (var i = 0; i < f.length; i++ ){
            if (f[i].type != "image/tiff") {
                showDialog1("选择文件中包含不支持的图片类型!")
                return;
            }
        }

        var formData = new FormData($("#formId")[0]);
        debugger;
        $.ajax({
            type: "post",
            url: path + "/recognizeManage/newTask",
            data: formData,
           // async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data){//从后端返回数据进行处理
            	
                if(data=="0"){
                    showDialog1("上传成功！");
                }else{
                    showDialog1("上传失败！");
                }
            },
            error: function(err) {//提交出错
                showDialog1("服务器无响应");
             },
             
             beforeSend: function(){    
   	           $('<div class="loadingWrap"></div>').appendTo("body");
   	        },   
   	        complete: function(){    
   	           $(".loadingWrap").remove();
   	        }
            
        });
    }

    $(function () {
        $("#upload").change(function(){
            var f = document.getElementById("upload").files;
            var name=f.length+"个文件";
            var i;
            $(".dataupload_btn_div1").html(name);
            for (i = 0; i < f.length; i++ ){
                if (f[i].type != "image/tiff") {
                    showDialog1("选择文件中包含非tiff类型图片!");
                    return;
                }
            }
        })
    })
</script>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!--  公共头部结束 -->

<div class="coupon_contain">
    <div class="coupon_crumbs"><a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span></a> > <a href="<c:url value="/recognizeManage/recognitionTask?pageNum=1"/>"><span class="cur" title="点击刷新">任务管理</span></a> </div>
   <%--
    <div>
    <form id="formId" enctype="multipart/form-data" >
        <input type="file" multiple="multiple" id="file_upload" name="files" />
        <input type="button" value="上传" onclick="upload()" />
    </form>
    </div>
    --%>
    <form  id="formId"  enctype="multipart/form-data">
             <div class="dataupload1">
                 <div class="dataupload_btn1">
                     <img src="../resources/images/public_img/ic_file_upload.png" style="width: 10px;margin-right: 5px">
                     选择上传文件
                     <div class="dataupload_btn_div1">
                         未选择文件
                     </div>
                     <input type="file" multiple="multiple" name="files" id="upload" value="" placeholder="" >
                 </div>
                 <div class="dataupload_sure1" onclick="upload()">
                     确定上传
                 </div>
             </div>
    </form>
    <div id = "msg"></div>
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
                    <input type="text" name="" id="task_number" value="" placeholder="请输入工厂名称"
                           onfocus="javascript:if(this.value=='请输入工厂名称')this.value='';"
                           onblur="javascript:if(this.value=='')this.value='请输入工厂名称';" />
                </div>
                <div class="fmc_search_input_img">
                    <img src="../resources/images/public_img/ic_search.png"
                         onclick = "TaskManageJS.queryTaskByCondition('1')"/>
                </div>
            </div>
        </div>
        <div class="fmc_table">
            <table border="0" cellspacing="" cellpadding="" id="templateList">
                <tr class="tr_h">
                    <th>任务编号</th>
                    <th>创建人</th>
                    <th>工厂</th>
                    <th>创建时间</th>
                    <th>结束时间</th>
                    <th>状态</th>
                    <%-- 
                    <th>备注</th>
                    <th>操作</th>
                    --%>
                </tr>
                <c:choose>
                    <c:when test="${fn:length(page.list) > 0}">
                        <c:forEach items="${page.list}" var="TaskBean" varStatus="order" >
                            <tr class="tr_d">
                                <td>${TaskBean.id}</td>
                                <td>${TaskBean.creator}</td>
                                <td>${TaskBean.factory_name}</td>
                                <td>${TaskBean.simpleTime}</td>
                                <td>${TaskBean.stringFinishTime}</td>
                                <c:choose>
										<c:when test="${TaskBean.status=='0'}">    
										<td style="color:red">未完成</td>
                                         </c:when>
										<c:otherwise>  
                                           <td style="color:green">已完成</td>
                                        </c:otherwise>
								</c:choose>
                                
                                <%-- 
                                <td>${TemplateBean.remark}</td>
                                <td>
                                    <span class="edit" ><a style="color:#FFFFFF" href="${path}/templateManage/showEditTemplate?templatename=${TemplateBean.template_name}&pageNum=${page.pageNum}"
									class="">编辑</a>
                                    </span>
                                    <span class="del"><a style="color:#FFFFFF" href="javascript:;" onclick="TaskManageJS.showRemoveUser('${TemplateBean.template_name}')"
									class="">删除</a>
                                    </span>
                                </td>
                                --%>
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
                                href="${path}/recognizeManage/recognitionTask?pageNum=${page.prev}"
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
                                            href="${path}/recognizeManage/recognitionTask?pageNum=${nav}"
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
                                                    href="${path}/recognizeManage/recognitionTask?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <a>...</a>
                                <a
                                        href="${path}/recognizeManage/recognitionTask?pageNum=${page.pageCount}"
                                        data-page="${page.pageCount}">${page.pageCount}</a>
                            </c:when>
                            <c:when test="${page.pageNum > page.pageCount - 3}">
                                <a
                                        href="${path}/recognizeManage/recognitionTask?pageNum=1"
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
                                                    href="${path}//recognizeManage/recognitionTask?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <a
                                        href="${path}/recognizeManage/recognitionTask?pageNum=1"
                                        data-page="1">1</a>
                                <a>....</a>
                                <a
                                        href="${path}/recognizeManage/recognitionTask?pageNum=${page.pageNum-1}"
                                        data-page="${page.pageNum-1}">${page.pageNum-1}</a>
                                <a href="javascript:;" data-page="${page.pageNum}"
                                   class="current">${page.pageNum}</a>
                                <a
                                        href="${path}/recognizeManage/recognitionTask?pageNum=${page.pageNum+1}"
                                        data-page="${page.pageNum+1}">${page.pageNum+1}</a>
                                <a>...</a>
                                <a
                                        href="${path}/recognizeManage/recognitionTask?pageNum=${page.pageCount}"
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
                        <a href="${path}/recognizeManage/recognitionTask?pageNum=${page.next}"
                           class="">下一页</a>
                        <%--<span class="total-num">共${page.pageCount}页</span>--%>
                    </c:otherwise>
                </c:choose>
                <span class="total-num">共${page.pageCount}页</span> 跳转第 <input
                    type="text" value="${page.pageNum}" id="go-to" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onblur="this.value=this.value.replace(/[^\d]/g,'') "> 页 <a
                    href="javascript:;" class="certain-btn"
                    onclick="TaskManageJS.goToPage('${page.pageCount}')">确定</a>
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
</body>
</html>
