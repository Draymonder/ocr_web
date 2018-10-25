<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="permission" tagdir="/WEB-INF/tags"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>南高齿OCR图像数据识别系统</title>
<link href="<c:url value="/resources/css/revision.css"/>"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/js/common/jquery-1.8.0.min.js"/>"></script>


	<script type="text/javascript" src="<c:url value="../resources/js/users/echarts-all.js"/>"></script>
	<script type="text/javascript" src="<c:url value="../resources/js/users/echarts.common.min.js"/>"></script>

</head>
<style>
.yqtx{
				/*width: 100%;*/
				margin-top: 20px;
				font-size: 13px;
				background-color: #F9F9F9;
				padding: 15px;
			}
			.yqtx_t{

			}
			.yqtx_list{
				display: flex;
				-webkit-flex-direction: row;
				/* Safari */
				flex-direction: row;
				/*row-reverse column column-reverse  */
				flex-wrap: wrap;
				/*nowrap(默认值) | wrap | wrap-reverse*/
				justify-content: space-between;
				/* flex-start(默认值) | flex-end | center | space-between | space-around */
				align-content: space-between;
				margin-top: 20px;
			}
			.yqtx_li{
				width: 30%;
				text-align: center;
				display: flex;
				height: 70px;
			}
			.yqtx_li_lf{
				width: 40%;
				background-color: #01AAEF;
				color: #fff;
				position: relative;
				border: 2px solid #01AAEF;
			}



			.yqtx_li_lf :after,#demo:before {
				border:solid transparent;
				content:' ';
				height: 0;
				left: 100%;
				position: absolute;
				width: 0;
			}

			.yqtx_li_lf :after {
				border-width: 10px;
				border-left-color: #01AAEF;
				top: 23px;
			}

			.yqtx_li_lf :before {
				border-width: 12px;
				border-left-color: #01AAEF;
				top: 20px;

			}



			.yqtx_li_rg{
				background-color: #fff;
				width: 60%;
				line-height: 70px;
				font-size: 20px;
			}
			.yqtx_li_lf_1{
				margin-top: 15px;
			}
			.yqtx_li_lf_2{
				margin-top: 24px;
			}


</style>
<body>
	<jsp:include page="public_manage/common/head.jsp" />
	<div class="coupon_contain_new">
		<!-- 首页  -->
		<permission:hasAnyPermissions
			name="guanliyuan,yonghu,yonghu1">
			<div class="index_list" style="display: none;" id="box_div1">
			 <div id="container" style="width:1000px;height:350px;background-color:white"></div>
			 <div> 
			  <input>
	         <%--   <a href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1&qx=0" style="color:red">个人未确认数量：<span id="db"></span>条</a>--%>
	           <span id="sz" style="display:none"></span>
	           <span id="month" style="display:none"></span>
	          </input>
	          </div>
				<div class="yqtx">
			<div class="yqtx_t">
				友情提醒
			</div>
			<div class="yqtx_list">
				<div class="yqtx_li">
					<div class="yqtx_li_lf">
						<div  class="yqtx_li_lf_1">累计<br />识别</div>
					</div>
					<div class="yqtx_li_rg" id="total"></div>
				</div>
				<div class="yqtx_li">
					<div class="yqtx_li_lf">
						<div class="yqtx_li_lf_1">错误<br />纠正</div>
					</div>
					<div class="yqtx_li_rg" id="finish"><a id="finished" style="color:red;cursor:pointer" href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1&qx=0&flag2=0"  target="_blank" ></a></div>
				</div>
				<div class="yqtx_li">
					<div class="yqtx_li_lf">
						<div class="yqtx_li_lf_2">待确认</div>
					</div>
					<div class="yqtx_li_rg"><a id="wait" style="color:orange;cursor:pointer" href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1&qx=0"  target="_blank" ></a></div>
				</div>
			</div>
		</div>
			</div>
		</permission:hasAnyPermissions>

		<!-- 系统管理  -->
		<permission:hasAnyPermissions
			name="guanliyuan">
			<div class="index_list" style="display: none;" id="box_div2">
				<ul>
					<shiro:hasPermission name="guanliyuan">
						<li><a href="${path}/userManage/showUserManage?pageNum=1">
								<span class="yonghu"> </span>
								<div class="text">用户管理</div> </a></li>
				       <li><a href="${path}/RoleManage/showRoleManage?pageNum=1">
								<span class="role"> </span>
								<div class="text">角色管理</div> </a></li>
						<li><a href="${path}/authorityManage/showAuthorityManage?pageNum=1">
								<span class="authority"> </span>
								<div class="text">权限管理</div> </a></li>
						</shiro:hasPermission>
						<shiro:hasPermission name="guanliyuan">		
					    <li><a href="${path}/factoryManage/showfactoryManage?pageNum=1">
								<span class="factory"> </span>
								<div class="text">工厂管理</div> </a></li>
						<li><a href="${path}/mailManage/showmailManage?pageNum=1">
								<span class="mail"> </span>
								<div class="text">邮箱服务器</div> </a></li>
						<%-- 
						<li><a href="${path}/factoryManage/showfactoryManage?pageNum=1">
								<span class="removeFile"> </span>
								<div class="text">清除文件</div> </a></li>--%>
						<li><a href="${path}/TraineddataManage/showDataManage?pageNum=1">
								<span class="mail"> </span>
								<div class="text">训练集</div> </a></li>
					    </shiro:hasPermission>
		
				</ul>
			</div>
		</permission:hasAnyPermissions>
		
		
		<!-- 模板管理  -->
		<permission:hasAnyPermissions
			name="yonghu">
			<div class="index_list" style="display: none;" id="box_div3">
				<ul>
					<shiro:hasPermission name="yonghu">

						<li><a href="${path}/templateManage/showTemplateManage?pageNum=1">
							<span class="templateManage"> </span>
							<div class="text">模板管理-计量报告</div> </a></li>

						<li><a href="${path}/ruleManage/showRuleManage?pageNum=1">
							<span class="rule"> </span>
							<div class="text">规则管理-计量报告</div> </a></li>

						<li><a href="${path}/CommonTemplateManage/showCommonTemplateManage?pageNum=1">
							<span class="templateManage"> </span>
							<div class="text">自定义模板</div> </a></li>

						<li><a href="${path}/CommonRuleManage/showCommonRuleManage?pageNum=1">
							<span class="rule"> </span>
							<div class="text">自定义规则</div> </a></li>

					</shiro:hasPermission>
				</ul>
			</div>
		</permission:hasAnyPermissions>
		
		<!-- 展示管理  -->
		<permission:hasAnyPermissions
			name="yonghu1">
			<div class="index_list" style="display: none;" id="box_div4">
				<ul>
					<shiro:hasPermission name="yonghu1">
						
					    <li><a href="${path}/recognizeManage/recognitionTask?pageNum=1">
							<span class="recognition"> </span>
							<div class="text">新增转换任务</div> </a></li>
					    <li><a href="${path}/templateSearchManage/showTemplateSearchManage?pageNum=1">
							<span class="fabu"> </span>
							<div class="text">历史数据查询</div> </a></li>
					    <li><a href="${path}/CommonRecognizeManage/recognitionTask?pageNum=1">
							<span class="recognition"> </span>
							<div class="text">新增自定义转换任务</div> </a></li>
						 <li><a href="${path}/CommonTemplateSearchManage/showCommonTemplateSearchManage?pageNum=1">
							<span class="fabu"> </span>
							<div class="text">自定义历史数据查询</div> </a></li>	
					</shiro:hasPermission>
				</ul>
			</div>
		</permission:hasAnyPermissions>
		
			<!-- 个人信息 -->
		<permission:hasAnyPermissions
			name="">
			<div class="index_list" style="display: none;" id="box_div5">
			
				<ul>
					    <li><a href="${path}/userManage/showEditMyself?fAccountNumber=${user.fAccountNumber}&pageNum=1">
							<span class="recognition"> </span>
							<div class="text">个人信息修改</div> </a></li>
				</ul>

			</div>
		</permission:hasAnyPermissions>
		
		
		<div class="left_list">
			<div class="left_index">
				<a href="<c:url value="/login/main?type=1"/>"> <img
					src="${path}/resources/images/left_icon1.png" /> <span>首页</span>
					<span> <img src="${path}/resources/images/left_arrow.png" />
				</span> </a>
			</div>
			<ul>

				<permission:hasAnyPermissions
					name="guanliyuan">
					<a href="${path}/login/main?type=2">
						<li id="left_nav2"><img
							src="${path}/resources/images/left_icon8.png" /> <span>系统管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				
				<permission:hasAnyPermissions
					name="guanliyuan,yonghu,yonghu1">
					<a href="${path}/login/main?type=3">
						<li id="left_nav3"><img
							src="${path}/resources/images/left_icon9.png" /> <span>模板管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions
					name="guanliyuan,yonghu,yonghu1">
					<a href="${path}/login/main?type=4">
						<li id="left_nav4"><img
							src="${path}/resources/images/left_icon6.png" /> <span>任务管理</span>
					</li> </a>
				</permission:hasAnyPermissions>
				<permission:hasAnyPermissions
					name="guanliyuan,yonghu,yonghu1">
					<a href="${path}/login/main?type=5">
						<li id="left_nav5"><img
							src="${path}/resources/images/left_icon2.png" /> <span>个人信息</span>
					</li> </a>
				</permission:hasAnyPermissions>
			</ul>
		</div>
	</div>
	<jsp:include page="public_manage/common/foot.jsp" />
</body>
<script type="text/javascript">
var path = "${path}";
    $(document).ready(function()
    {  //alert("1");
        var type = "${type}";
        $("#left_nav" + type).addClass("cur");
        $("#box_div" + type).show();
        if(type==1){
        	fistpage();
        	
        }
    });
    
    
    function echrats(){
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var sj=$("#sz").html();
    var myArray=new Array();
    myArray=sj.split(",");
    var month=$("#month").html();
    var myArray1=new Array();
    myArray1=month.split(",");
    $("#db").html(myArray[2]);
    option = null;
    option = {
       // color: ['#3398DB'],

        title: {
        text: '您近期的数据概况'
        },
        tooltip : {
            //trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            //left: '3%',
            //right: '4%',
            // bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : myArray,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                //name:'直接访问',
                type:'line',
                itemStyle: {  
                    normal: {  
                        color: function(params) {  
                            // build a color map as your need.  
                            var colorList = [  

                              '#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10','#FCCE10',


                            ];  
                            return colorList[params.dataIndex]  
                        },  
                        label: {  
                            show: true,  
                            position: 'top',  
                           //formatter: '{b}\n{c}%' 
                           formatter: '{c}条'     
                        }  
                    }  
                },  
                //data:[100,10,2] '#B5C334','#C1232B','#E87C25','#27727B',
               // '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                //'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                data:myArray1
            }
        ]
    };

    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
 }   
    function fistpage(){
    	// alert("3");
    	//$("#sz").html("2,1,1");
    	//echrats();
    	var sj="";
    	var MonthDate="";
    	var monthMap="";

    	$.ajax({
			url:path+"/IndexPageManage/countBean",
			type : 'post',
			dataType:'text',
			data:{
				//"userAccount" : userAccount// 账号
			},
			success:function(result){
				
				var result = $.parseJSON(result);
				sj=result.sj;
				MonthDate=result.MonthDate;
				monthMap=result.monthMap;
				debugger;
				//alert(result.sj);
				if (result.resultCode == "0") {
					//window.location.href = path + "/userManage/showUserManage?pageNum=1";
					//showDialog1("xx"+result.resultCode);
					fz(sj,MonthDate,monthMap);
				} else {
					alert(result.msg);
				}
				
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
    	
    	
    }
    function fz(sj,MonthDate,monthMap){
    	var myArray=new Array();
        myArray=sj.split(",");
        $("#total").html(myArray[0]+"条");
        $("#finished").html(myArray[1]+"条");
        $("#wait").html(myArray[2]+"条");
    	$("#sz").html(monthMap);
    	$("#month").html(MonthDate);
    	
    	//$("#sz").html(sj);
    	echrats();
    }
    
    
  </script>
</html>
