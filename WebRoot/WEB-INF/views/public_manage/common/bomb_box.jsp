<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<div class="mask hide"></div>
<div class="coupon_tanckuang hide" id="bombBox1">
	<div class="coupon_tankuang_title">
		<img src="<c:url value="/resources/images/icon1.png"/>" alt="" /> <span
			id="bombBoxTitle1"></span>
		<div class="close" onclick="closeDialog1()"></div>
	</div>
	<div class="coupon_tankuang_info">
		<div class="coupon_tankuang_text" id="bombBoxText1"></div>
		<div class="coupon_btn">
			<div class="coupon_queren" onclick="closeDialog1()"
				style="margin-left: 28%;">确认</div>
		</div>
	</div>
</div>
<div class="coupon_tanckuang hide" id="bombBox2">
	<div class="coupon_tankuang_title">
		<img src="<c:url value="/resources/images/tijiao_03.png"/>" alt="" />
		<span id="bombBoxTitle2"></span>
		<div class="close" onclick="closeDialog1()"></div>
	</div>
	<div class="coupon_tankuang_info">
		<div class="coupon_tankuang_text" id="bombBoxText2"></div>
		<div class="coupon_btn">
			<div class="coupon_quxiao" onclick="closeDialog1()">取消</div>
			<div class="coupon_queren" onclick="" id="queren_btn">确认</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    function showDialog1(msg)
    {
        $('.mask').removeClass('hide');
        $('#bombBox1').removeClass('hide');
        $("#bombBoxText1").html(msg);
        $("#bombBoxTitle1").html("提示");
    }
    function closeDialog1()
    {
        $('.mask').addClass('hide');
        $('.coupon_tanckuang').addClass('hide');
        $('.coupon_tanckuang_shenhe').addClass('hide');
        $('.coupon_tanckuang_chehui').addClass('hide');
    }
</script>