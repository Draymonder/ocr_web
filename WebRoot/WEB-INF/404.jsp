<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404 报错页面 | 请联系管理员</title>
<STYLE type=text/css>
.mod-notfound {
	BORDER-RIGHT: #e1e1e1 1px solid;
	BORDER-TOP: #e1e1e1 1px solid;
	MARGIN-TOP: 10px;
	BACKGROUND: #fff;
	BORDER-LEFT: #e1e1e1 1px solid;
	BORDER-BOTTOM: #e1e1e1 1px solid;
	HEIGHT: 580px;
	TEXT-ALIGN: center;
	border-radius: 10px
}
</STYLE>
</head>
<BODY>
	<DIV class="mod-page-main wordwrap clearfix">
		<DIV class=x-page-container>
			<DIV class="mod-notfound grid-98">
				<IMG class=img-notfound height=320
					src="<c:url value="/resources/images/notfound.gif"/>" width=520>
				<P style="FONT-SIZE: 24px; LINE-HEIGHT: 70px">啊~哦~
					您的操作出现异常了，请稍后再试！</P>
				<P style="MARGIN-BOTTOM: 30px">请检查您输入的网址是否正确，或者点击链接继续浏览页面</P>
				<P style="FONT-SIZE: 14px; LINE-HEIGHT: 20px">
					您可以回到 <A href="<c:url value="/"/>">网站首页重新登陆</A> <BR>如若不能解决您的问题，请联系系统管理人员^_^
				</P>
			</DIV>
		</DIV>
	</DIV>

</BODY>
</html>