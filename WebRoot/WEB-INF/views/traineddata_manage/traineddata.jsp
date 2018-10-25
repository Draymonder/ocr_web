
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
    <title>训练集</title>
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
            src="<c:url value="../resources/js/traineddata/traineddata.js"/>"></script>

</head>

<script type="text/javascript">
var trainname="";//全局变量
var msg="";
    function upload(){
        var formData = new FormData($("#formId")[0]);
        var f = document.getElementById("upload").files;
        var a=f[0].name;
        debugger;
        if(f.length==0){
        	 showDialog1("请先上传文件");	
        }
        else if(f[0].name.indexOf(".traineddata")<0){
            showDialog1("文件类型错误,请上传traineddata文件");
        }else if(msg.indexOf(f[0].name)>=0){
        	
        	showRemoveUser(f[0].name);
        	
        }else{  
        	
        	//showDialog1("新增");
         
            $.ajax({
                type: "post",
                url: path + "/TraineddataManage/new",
                //dataType: "json",
                data: formData,
                async: false,
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
                }
            });
        }
    }
    function  showRemoveUser(name){
    	debugger;
    	trainname=name
		$(".mask").removeClass("hide");
		$("#confirmRemoveUser").removeClass("hide");
	}
  //确认模板用户
	function  confirmRemoveUser(){ //替换
		debugger;
		//showDialog1("替换："+trainname);
		  var formData = new FormData($("#formId")[0]);
		 $.ajax({
                type: "post",
                url: path + "/TraineddataManage/update",
                //dataType: "json",
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function(data){//从后端返回数据进行处理
            	debugger;
                    if(data=="0"){
                    	  window.location.href = path + "/TraineddataManage/showDataManage?pageNum=1";
                    }else{
                        showDialog1("替换失败！");
                    }
                },
                error: function(err) {//提交出错
                    showDialog1("服务器无响应");
                }
            });
		 
	} 
  
    $(function () {
        
        $("#upload").change(function(){
            var f = document.getElementById("upload").files;
            //名称
            console.log(f[0].name);
            console.log(f.length);
            var name=f.length+"个文件";
            $(".dataupload_btn_div1").html(name);
            msg=$("#msg").html();
            //大小 字节
            console.log(f[0].size);
            console.log( $("#msg").html());
            //类型
            console.log(f[0].type);
        })
      
    })
</script>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!--  公共头部结束 -->

<div class="coupon_contain">
    <div class="coupon_crumbs"><a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span></a> > <a href="<c:url value="/TraineddataManage/showDataManage?pageNum=1"/>"><span class="coupon_index">训练集</span></a> </div>

   <form  id="formId"  enctype="multipart/form-data">
             <div class="dataupload1">
                 <div class="dataupload_btn1">
                     <img src="../resources/images/public_img/ic_file_upload.png" style="width: 10px;margin-right: 5px">
                     选择上传文件
                     <div class="dataupload_btn_div1">
                         未选择文件
                     </div>
                     <input type="file"  name="files" id="upload" value="" placeholder="" >
                 </div>
                 <div class="dataupload_sure1" onclick="upload()">
                     确定上传
                 </div>
             </div>
    </form>
    
     
    <div id = "msg" style="display:none">${result}</div>
   
    
    <div class="figure_model_ctrl2">
    <%-- 
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
        --%>
        <div class="fmc_table">
            <table border="0" cellspacing="" cellpadding="" id="templateList">
                <tr class="tr_h">
                    <th>ID</th>
                    <th>训练集名称</th>
                   
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
                                <td>${TaskBean.traineddata}</td>
                              
                                
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
                                href="${path}/TraineddataManage/showDataManage?pageNum=${page.prev}"
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
                                            href="${path}/TraineddataManage/showDataManage?pageNum=${nav}"
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
                                                    href="${path}/TraineddataManage/showDataManage?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <a>...</a>
                                <a
                                        href="${path}/TraineddataManage/showDataManage?pageNum=${page.pageCount}"
                                        data-page="${page.pageCount}">${page.pageCount}</a>
                            </c:when>
                            <c:when test="${page.pageNum > page.pageCount - 3}">
                                <a
                                        href="${path}/TraineddataManage/showDataManage?pageNum=1"
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
                                                    href="${path}//TraineddataManage/showDataManage?pageNum=${nav}"
                                                    data-page="${nav}">${nav}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <a
                                        href="${path}/TraineddataManage/showDataManage?pageNum=1"
                                        data-page="1">1</a>
                                <a>....</a>
                                <a
                                        href="${path}/TraineddataManage/showDataManage?pageNum=${page.pageNum-1}"
                                        data-page="${page.pageNum-1}">${page.pageNum-1}</a>
                                <a href="javascript:;" data-page="${page.pageNum}"
                                   class="current">${page.pageNum}</a>
                                <a
                                        href="${path}/TraineddataManage/showDataManage?pageNum=${page.pageNum+1}"
                                        data-page="${page.pageNum+1}">${page.pageNum+1}</a>
                                <a>...</a>
                                <a
                                        href="${path}/TraineddataManage/showDataManage?pageNum=${page.pageCount}"
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
                        <a href="${path}/TraineddataManage/showDataManage?pageNum=${page.next}"
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

<!--确认注销用户弹窗 begin-->
	<div class="coupon_tanckuang hide" id="confirmRemoveUser">
		<div class="coupon_tankuang_title">
			<img src="../resources/images/tijiao_03.png" alt=""> 替换
			<div class="close"></div>
		</div>

		<div class="coupon_tankuang_info">
			<div class="coupon_tankuang_text">是否确认注销？</div>
			<div class="coupon_btn">
				<div class="coupon_quxiao">取消</div>
				<div class="coupon_queren"
					onclick="confirmRemoveUser()">确认</div>
			</div>
		</div>
	</div>
	<!--确认注销用户弹窗 end-->
</body>
</html>
