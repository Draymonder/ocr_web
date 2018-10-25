/**
 * 点击事件
 */
$(function(){ 
	$(".ui-btn").click(function(){
		//当前请求类型
		var value = $(this).attr("name");
		if(value=="11"){
			//查询可领取卡券（IT）
			qryEvent_1();
		}else if(value=="12"){
			//查询可领取卡券（领券中心）
			qryEvent_2();
			
		}else if(value=="13"){
			//查询异业券（领券中心）
			qryEvent_3();
		}else if(value=="14"){
			//查询店铺券（领券中心）
			qryEvent_4();
			
		}else if(value=="10"){
			//用户通用券查询（IT）
			qryEvent_5();
			
		}else if(value=="15"){
			//用户通用券查询（领券中心）
			qryEvent_6();
		}else if(value=="16"){
			//用户未使用异业券查询（领券中心）
			qryEvent_7();
		}else if(value=="17"){
			//用户已使用/已过期异业券查询（领券中心）
			qryEvent_8();
		}else if(value=="18"){
			//即将过期卡券查询（领券中心）
			qryEvent_9();
		}else{
			//用户卡券记录
			qryEvent_10();
		}
	});
}); 
/*
 * 组装结果时调用的方法一:对应com.xwtech.xwecp.service.logic.pojo.SingleCouponInfomation
 */
function assembleResult(result){
	var htmlStr = "";
	htmlStr+="<p>请求结果：</p>";
	htmlStr+="<table width='100%'>";
	htmlStr+="<tr><td>本次请求结果（0:成功 非0：失败）</td><td>请求失败错误信息</td><td>请求失败错误编码</td></tr>";
	htmlStr+="<tr>";
	htmlStr+="<td>"+result.isSuccess+"</td>";
	htmlStr+="<td>"+result.errorMsg+"</td>";
	htmlStr+="<td>"+result.errorCode+"</td>";
	htmlStr+="</tr>";
	htmlStr+="</table>";
	htmlStr+="<p><b>详细数据：</b></p>";
	htmlStr+= "<table width='100%'>";
	htmlStr+="<tr><td>序号</td><td>父券编码</td><td>子券编码</td><td>isShowChild</td><td>状态</td><td>cardId</td><td>prizeId</td></tr>";
	var list = result.cardList;
	if(list!=null){
	for(var i=0;i<list.length;i++){
		htmlStr+="<tr>";
		htmlStr+="<td>"+(i+1)+"</td>";
		htmlStr+="<td>"+list[i].parentNumber+"</td>";
		htmlStr+="<td>"+list[i].childNumber+"</td>";
		htmlStr+="<td>"+list[i].isShowChild+"</td>";
		htmlStr+="<td>"+list[i].state+"</td>";
		htmlStr+="<td>"+list[i].cardId+"</td>";
		htmlStr+="<td>"+list[i].prizeId+"</td>";
		htmlStr+="</tr>";
	  }
	}
	htmlStr+="</table>";
	return htmlStr;
}
/*
 * 组装结果时调用的方法二:对应com.xwtech.xwecp.service.logic.pojo.CardInfomation
 */
function assembleResult_1(result){
	var htmlStr = "";
	htmlStr+="<p>请求结果：</p>";
	htmlStr+="<table width='100%'>";
	htmlStr+="<tr><td>本次请求结果（0:成功 非0：失败）</td><td>请求失败错误信息</td><td>请求失败错误编码</td></tr>";
	htmlStr+="<tr>";
	htmlStr+="<td>"+result.isSuccess+"</td>";
	htmlStr+="<td>"+result.errorMsg+"</td>";
	htmlStr+="<td>"+result.errorCode+"</td>";
	htmlStr+="</tr>";
	htmlStr+="</table>";
	htmlStr+="<p><b>详细数据：</b></p>";
	htmlStr+= "<table width='100%'>";
	htmlStr+="<tr><td>序号</td><td>父券编码</td><td>子券编码</td><td>状态</td><td>lastBeginTime</td><td>lastEndTime</td><td>receiveTime</td></tr>";
	var list = result.cardList;
	if(list!=null){
	for(var i=0;i<list.length;i++){
		htmlStr+="<tr>";
		htmlStr+="<td>"+(i+1)+"</td>";
		htmlStr+="<td>"+list[i].parentNumber+"</td>";
		htmlStr+="<td>"+list[i].childNumber+"</td>";
		htmlStr+="<td>"+list[i].state+"</td>";
		htmlStr+="<td>"+list[i].lastBeginTime+"</td>";
		htmlStr+="<td>"+list[i].lastEndTime+"</td>";
		htmlStr+="<td>"+list[i].receiveTime+"</td>";
		htmlStr+="</tr>";
	  }
	}
	htmlStr+="</table>";
	return htmlStr;
}
/*------------------------------------------PART 1 获取可领取的通用券（IT） BEGIN------------------------------------------------------------*/
/**
 * 可领取通用券数据查询：IT接口部分
 */
function qryEvent_1(){
    var phoneNumber=$.trim($("#mobile_1").val());
   //手机号码合法性校验
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
    
    $.ajax({
			url:path+"/problem/ask",
			type : 'post',
			dataType:'text',
			data:{
				"phoneNumber" : phoneNumber,
				"operType":"1"
			},
			success:function(result){
				var result = $.parseJSON(result);
				delResult_1(result);
			},
			error:function(result){
				alert("网络繁忙");
		}});
}

function delResult_1(result){
	$("#part_1").empty();
	var htmlStr = "";
	htmlStr+="<p>请求结果：</p>";
	htmlStr+="<table width='100%'>";
	htmlStr+="<tr><td>本次请求结果（0:成功 非0：失败）</td><td>请求失败错误信息</td><td>请求失败错误编码</td></tr>";
	htmlStr+="<tr>";
	htmlStr+="<td>"+result.isSuccess+"</td>";
	htmlStr+="<td>"+result.errorMsg+"</td>";
	htmlStr+="<td>"+result.errorCode+"</td>";
	htmlStr+="</tr>";
	htmlStr+="</table>";
	htmlStr+="<p><b>详细数据：</b></p>";
	htmlStr+= "<table width='100%'>";
	htmlStr+="<tr><td>序号</td><td>父券编码</td></tr>";
	var list = result.idList;
	if(list!=null){
	for(var i=0;i<list.length;i++){
		htmlStr+="<tr>";
		htmlStr+="<td>"+(i+1)+"</td>";
		htmlStr+="<td>"+list[i].couponid+"</td>";
		htmlStr+="</tr>";
	  }
	}
	htmlStr+="</table>";
	$("#part_1").html(htmlStr);	
	$("#part_1").show();
}
/*------------------------------------------PART 1 获取可领取的通用券（IT） END------------------------------------------------------------*/
/*------------------------------------------PART 2 获取可领取的通用券（领券中心） BEGIN-----------------------------------------------------*/
//可领优惠券查询（领券中心）
function qryEvent_2(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_2").val());
//	alert("phoneNumber==>"+phoneNumber);  
	//领取渠道
	var getChannel=$("#receivechannel_2").val();
	//调用环境
	var isTest=$("#isTest_2").val();
	//地市
	var city=$("#city_2").val();
	//手机号码合法性校验
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	
    $.ajax({
		url:path+"/problem/ask",
		type : 'post',
		dataType:'text',
		data:{
			"phoneNumber" : phoneNumber,
			"operType":"2",
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city
		},
		success:function(result){
			var result = $.parseJSON(result);
			delResult_2(result);
		},
		error:function(result){
			alert("网络繁忙");
	}});
}

function delResult_2(result){
	$("#part_2").empty();
	var htmlStr=assembleResult(result);
	$("#part_2").html(htmlStr);	
	$("#part_2").show();
}
/*------------------------------------------PART 2 获取可领取的通用券（领券中心） END-----------------------------------------------------*/
/*------------------------------------------PART 3 获取可领取的异业券（领券中心） BEGIN-----------------------------------------------------*/
//可领异业券查询（领券中心）
function qryEvent_3(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_3").val());
//	alert("phoneNumber==>"+phoneNumber); 
	//券类型
	var cardType=$("#cardType_3").val();
	//领取渠道
	var getChannel=$("#receivechannel_3").val();
	//调用环境
	var isTest=$("#isTest_3").val();
	//地市
	var city=$("#city_3").val();
	//手机号码合法性校验
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	$.ajax({
		url:path+"/problem/ask",
		type:'post',
		dataType:'text',
		data:{
			"phoneNumber":phoneNumber,
			"cardType":cardType,
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city,
			"operType":"3"
		},
		success:function(result){
			var result = $.parseJSON(result);
			delResult_3(result);
		},
		error:function(result){
			alert("网络繁忙");
		}
		
	});
}
/*
 * 拼接结果
 */
function delResult_3(result){
	$("#part_3").empty();
	var htmlStr=assembleResult(result);
	$("#part_3").html(htmlStr);
	$("#part_3").show();	
}
/*------------------------------------------PART 3 获取可领取的异业券（领券中心） END-----------------------------------------------------*/
/*------------------------------------------PART 4 获取可领取的店铺券（领券中心） BEGIN-----------------------------------------------------*/
//可领店铺券（领券中心）
function qryEvent_4(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_4").val());
//	alert("phoneNumber==>"+phoneNumber);
	//领取渠道
	var getChannel=$("#receivechannel_4").val();
	//调用环境
	var isTest=$("#isTest_4").val();
	//地市
	var city=$("#city_4").val();
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	$.ajax({
		url:path+"/problem/ask",
		type:'post',
		data:{
			"phoneNumber":phoneNumber,
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city,
			"operType":"4"
		},
		dataType:'text',
		success:function(result){
			var result = $.parseJSON(result);
			delResult_4(result);
		},
		error:function(result){
			alert("网络繁忙");
		}
	});
	
}
/*
 * 组装结果
 */
function delResult_4(result){
	$("#part_4").empty();
	var htmlStr=assembleResult(result);
	$("#part_4").html(htmlStr);
	$("#part_4").show();
}


/*------------------------------------------PART 4 获取可领取的店铺券（领券中心） END-----------------------------------------------------*/
/*------------------------------------------PART 5 用户通用券查询（IT） BEGIN---------------------------------------------------------*/
/**
 * 可用优惠券查询（CRM）：IT接口部分
 */
function qryEvent_5(){
	
    var phoneNumber=$.trim($("#mobile_0").val());
    var ticketType=$("#ticketType_0").val();
    if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
    
    $.ajax({
			url:path+"/problem/ask",
			type : 'post',
			dataType:'text',
			data:{
				"phoneNumber" : phoneNumber,
				"ticketType" : ticketType,
				"operType":"5"
			},
			success:function(result){
				var result = $.parseJSON(result);
				delResult_0(result);
			},
			error:function(result){
				alert("网络繁忙");
		}});
}
/**
 * 拼接第四个查询结果
 * @param result
 */
function delResult_0(result){
	$("#part_0").empty();
	var htmlStr = "";
	htmlStr+="<p>请求结果：</p>";
	htmlStr+="<table width='100%'>";
	htmlStr+="<tr><td>本次请求结果（0:成功 非0：失败）</td><td>请求失败错误信息</td><td>请求失败错误编码</td></tr>";
	htmlStr+="<tr>";
	htmlStr+="<td>"+result.isSuccess+"</td>";
	htmlStr+="<td>"+result.errorMsg+"</td>";
	htmlStr+="<td>"+result.errorCode+"</td>";
	htmlStr+="</tr>";
	htmlStr+="</table>";
	htmlStr+="<p><b>详细数据：</b></p>";
	htmlStr+= "<table width='100%'>";
	htmlStr+="<tr><td>序号</td><td>父券编码</td><td>子券编码</td><td>开始时间</td><td>结束时间</td></tr>";
	var list = result.idList;
    if(list!=null){
	for(var i=0;i<list.length;i++){
		htmlStr+="<tr>";
		htmlStr+="<td>"+(i+1)+"</td>";
		htmlStr+="<td>"+list[i].mticketID+"</td>";
		htmlStr+="<td>"+list[i].ticketID+"</td>";
		htmlStr+="<td>"+list[i].startDate+"</td>";
		htmlStr+="<td>"+list[i].endDate+"</td>";
		htmlStr+="</tr>";
	  }
	}
	htmlStr+="</table>";
	$("#part_0").html(htmlStr);	
	$("#part_0").show();
}
/*------------------------------------------PART 5 用户通用券查询（IT） END------------------------------------------------------------*/
/*------------------------------------------PART 6 用户通用券查询（领券中心） BEGIN---------------------------------------------------------*/
/**
 * 可用通用券查询（领券中心）
 */
function qryEvent_6(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_5").val());
//	alert("phoneNumber==>"+phoneNumber);  
	//领取渠道
	var getChannel=$("#receivechannel_5").val();
	//调用环境
	var isTest=$("#isTest_5").val();
	//地市
	var city=$("#city_5").val();
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	
    $.ajax({
		url:path+"/problem/ask",
		type : 'post',
		dataType:'text',
		data:{
			"phoneNumber" : phoneNumber,
			"operType":"6",
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city
		},
		success:function(result){
			var result = $.parseJSON(result);
			delResult_5(result);
		},
		error:function(result){
			alert("网络繁忙");
	}});
}

function delResult_5(result){
	$("#part_5").empty();
	var htmlStr=assembleResult_1(result);
	$("#part_5").html(htmlStr);	//最终显示效果图未知，暂定如此com.xwtech.xwecp.service.logic.pojo.CardInfomation
	$("#part_5").show();
}
/*------------------------------------------PART 6 用户通用券查询（领券中心） END---------------------------------------------------------*/
/*------------------------------------------PART 7 用户未使用异业券查询（领券中心） BEGIN--------------------------------------------------*/
function qryEvent_7(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_6").val());
//	alert("phoneNumber==>"+phoneNumber); 
	//券类型
	var cardType=$("#cardType_6").val();
	//领取渠道
	var getChannel=$("#receivechannel_6").val();
	//调用环境
	var isTest=$("#isTest_6").val();
	//地市
	var city=$("#city_6").val();
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	$.ajax({
		url:path+"/problem/ask",
		type:'post',
		dataType:'text',
		data:{
			"phoneNumber":phoneNumber,
			"cardType":cardType,
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city,
			"operType":"7",
			"useCondition":"0"
		},
		success:function(result){
			var result = $.parseJSON(result);
			delResult_6(result);
		},
		error:function(result){
			alert("网络繁忙");
		}
		
	});
	
}
function delResult_6(result){
	$("#part_6").empty();
	var htmlStr=assembleResult_1(result);
	$("#part_6").html(htmlStr);	//最终显示效果图未知，暂定如此com.xwtech.xwecp.service.logic.pojo.CardInfomation
	$("#part_6").show();
}
/*------------------------------------------PART 7 用户未使用异业券查询（领券中心） END---------------------------------------------------*/
/*------------------------------------------PART 8 用户已使用/已过期异业券查询（领券中心） BEGIN-------------------------------------------*/
function qryEvent_8(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_7").val());
//	alert("phoneNumber==>"+phoneNumber); 
	//券类型
	var cardType=$("#cardType_7").val();
	//领取渠道
	var getChannel=$("#receivechannel_7").val();
	//调用环境
	var isTest=$("#isTest_7").val();
	//地市
	var city=$("#city_7").val();
	//状态
	var ticketType=$("#ticketType_1").val();
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	$.ajax({
		url:path+"/problem/ask",
		type:'post',
		dataType:'text',
		data:{
			"phoneNumber":phoneNumber,
			"cardType":cardType,
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city,
			"ticketType":ticketType,
			"operType":"7",
			"useCondition":"1"
		},
		success:function(result){
			var result = $.parseJSON(result);
			delResult_7(result);
		},
		error:function(result){
			alert("网络繁忙");
		}
		
	});
	
}
function delResult_7(result){
	$("#part_7").empty();
	var htmlStr=assembleResult_1(result);
	$("#part_7").html(htmlStr);	//最终显示效果图未知，暂定如此com.xwtech.xwecp.service.logic.pojo.CardInfomation
	$("#part_7").show();
}
/*------------------------------------------PART 8 用户已使用/已过期异业券查询（领券中心） END----------------------------------------*/
/*------------------------------------------PART 9 即将过期卡券查询查询（领券中心） BEGIN-------------------------------------------*/
function qryEvent_9(){
	//手机号码
	var phoneNumber=$.trim($("#mobile_8").val());
//	alert("phoneNumber==>"+phoneNumber); 
	//券类型
	var cardType=$("#cardType_8").val();
	//领取渠道
	var getChannel=$("#receivechannel_8").val();
	//调用环境
	var isTest=$("#isTest_8").val();
	//地市
	var city=$("#city_8").val();
	//状态
	var ticketType=$("#ticketType_2").val();
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	$.ajax({
		url:path+"/problem/ask",
		type:'post',
		dataType:'text',
		data:{
			"phoneNumber":phoneNumber,
			"cardType":cardType,
			"getChannel":getChannel,
			"isTest":isTest,
			"city":city,
			"ticketType":ticketType,
			"operType":"8",
			"useCondition":"1"
		},
		success:function(result){
			var result = $.parseJSON(result);//$.parseJSON() 函数用于将符合标准格式的的JSON字符串转为与之对应的JavaScript对象。
			delResult_8(result);
		},
		error:function(result){
			alert("网络繁忙");
		}
		
	});
	
}
function delResult_8(result){
	$("#part_8").empty();
	var htmlStr = "";
	htmlStr+="<p>请求结果：</p>";
	htmlStr+="<table width='100%'>";
	htmlStr+="<tr><td>本次请求结果（0:成功 非0：失败）</td><td>请求失败错误信息</td><td>请求失败错误编码</td></tr>";
	htmlStr+="<tr>";
	htmlStr+="<td>"+result.isSuccess+"</td>";
	htmlStr+="<td>"+result.errorMsg+"</td>";
	htmlStr+="<td>"+result.errorCode+"</td>";
	htmlStr+="</tr>";
	htmlStr+="</table>";
	htmlStr+="<p><b>详细数据：</b></p>";
	htmlStr+= "<table width='100%'>";
	htmlStr+="<tr><td>序号</td><td>父券编码</td><td>子券编码</td><td>useBeginTime</td><td>useEndTime</td></tr>";
	var list = result.cardList;
	if(list!=null){
	for(var i=0;i<list.length;i++){
		htmlStr+="<tr>";
		htmlStr+="<td>"+(i+1)+"</td>";
		htmlStr+="<td>"+list[i].parentNumber+"</td>";
		htmlStr+="<td>"+list[i].childNumber+"</td>";
		htmlStr+="<td>"+list[i].useBeginTime+"</td>";
		htmlStr+="<td>"+list[i].useEndTime+"</td>";
		htmlStr+="</tr>";
	  }
	}
	htmlStr+="</table>";
	$("#part_8").html(htmlStr);	//com.xwtech.xwecp.service.logic.pojo.SingleCardInfomation
	$("#part_8").show();
}

/*------------------------------------------PART 9 即将过期卡券查询查询（领券中心） END-------------------------------------------*/
/*------------------------------------------PART 10 用户卡券记录 BEGIN-------------------------------------------------------*/
function qryEvent_10(){
	//获取用户领取卡券的手机号码
	var phoneNumber = $.trim($("#mobile_9").val());
	//获取领取时间
	var receiveTime = $("#receiveTime").val().replace(/-/g,"");
	if(!CommonJS.phoneNumberCheck(phoneNumber,"手机号码")){
		return;
	}
	if(receiveTime == "" || receiveTime == null){
		showDialog1("请选择领取时间后再查询!");
		return;
	}
	
	$.ajax({
		url:path+"/problem/ask",
	    type:"post",
	    dataType:"text",//返回纯文本字符串
	    data:
	    	{
	    	  "phoneNumber" :phoneNumber,
	    	  "receiveTime" :receiveTime,
	    	  "operType" : "9"
	    	},
	    success:function(result){
	    	var result = $.parseJSON(result);
	    	var list = result.cardList;
	    	delResult_9(result);
	    },
	    error:function(){
	    	alert("网络繁忙");
	    }
	});
	
function delResult_9(result) {
	$("#part_9").empty();
	var htmlStr = "";
	htmlStr+= "<table width='100%'>";
	htmlStr+="<tr><td>序号</td><td>父券编码</td><td>子券编码</td><td>领取时间</td><td>使用时间</td></tr>";
	var list = result.cardList;
//	
	for ( var i = 0; i < list.length; i++) {
		htmlStr+="<tr>";
		htmlStr+="<td>"+(i+1)+"</td>";
		htmlStr+="<td>"+list[i].fParentNumber+"</td>";
		htmlStr+="<td>"+list[i].fCardNumber+"</td>";
		htmlStr+="<td>"+list[i].fReceiveTime+"</td>";
		htmlStr+="<td>"+((list[i].fUserTime==null)?"未用":list[i].fUserTime)+"</td>";
		htmlStr+="</tr>";
	   }
	htmlStr+="</table>";
	
	$("#part_9").html(htmlStr);
	$("#part_9").show();
   }
	
}




/*------------------------------------------PART 10 用户卡券记录 END-------------------------------------------------------*/
