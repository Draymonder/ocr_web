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
    <title>上传图像文件</title>
    <script type="text/javascript">
        var path = "${path}";
    </script>
    <link href="<c:url value="../resources/css/coupon.css"/>"
          rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<c:url value ="../resources/css/public_x.css"/>"
          type="text/css" />
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/jquery-1.8.0.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/common.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/common/coupon.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="../resources/js/users/userManage.js"/>"></script>
    <script>
        $(function () {

            $("#upload").change(function(){
                var f = document.getElementById("upload").files;
                //名称
                console.log(f[0].name);
                $(".dataupload_btn_div").html(f[0].name);


            })
            $(".dataupload_sure").click(function () {
                var f = document.getElementById("upload").files;
                if(f[0].type != "image/tiff"){
                    showDialog1("请上传tiff格式计量报告!")
                    return;
                }
                if(undefined==f[0]){
                    showDialog1("请先选择上传文件");
                    return;
                }else{
                    $("#from").submit();
                }
            })
        })
    </script>

</head>
<body>

<!-- 公共头部开始 -->
<jsp:include page="../public_manage/common/head.jsp" />
<!-- 公共头部结束 -->

<div class="coupon_contain">
    <div class="coupon_crumbs">
        <a href="<c:url value="/login/main"/>"><span class="coupon_index">首页</span>
        </a> ><a href="${path}/templateManage/showTemplateManage?pageNum=1"><span
            class="cur">定制模板—计量报告</span>
    </a>><span class="cur1">数字化建模管理</span>
    
    </div>
         <form action="${path}/templateManage/createTemplate"  id="from" method="post" enctype="multipart/form-data">
             <div class="dataupload">
                 <div class="dataupload_btn">
                     <img src="../resources/images/public_img/ic_file_upload.png"/>
                     选择上传文件
                     <div class="dataupload_btn_div">
                         未选择文件
                     </div>
                     <input type="file" name="file" id="upload" value="" placeholder=""/>
                 </div>
                 <div class="" style="width: 260px;height: 30px;margin-top: 10px;position: relative;left: 50%;margin-left: -130px;color: #5E5E5E;z-index: 100;line-height: 30px;font-size: 14px;">
                     (仅支持tif格式图片)
                 </div>
                 <div class="dataupload_sure">
                     确定上传
                 </div>
             </div>
         </form>

    <%--<form action="/templateManage/createTemplate" method="post" enctype="multipart/form-data">--%>
        <%--<div class="dataupload_btn"></div>选择文件<input type="file" name="file"/>--%>
        <%--<div class="dataupload_sure">  <input type="submit" value="提交" /></div>--%>
        <%--<input type="submit" value="提交" />--%>
    <%--</form>--%>

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
                 onclick="UserManageJS.confirmRemoveUser()">确认</div>
        </div>
    </div>
</div>
<!--确认注销用户弹窗 end-->
</body>
</html>
