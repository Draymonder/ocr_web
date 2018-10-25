var tChannelId1="";//渠道编号
var channelManageJS={
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			window.location.href = path + "/channel/showChannelList?pageNum="+pageNum;
		},
		getChannelByCondition : function(){
			var commercialChannelName = $.trim($("#commercialChannelName").val());
			//判定是否输入渠道名称
			if(commercialChannelName=="请输入渠道名称"){
//				showDialog1("渠道名称不能为空！");
				window.location.href = path + "/channel/showChannelList?pageNum=1";
				return;
			}
			//密码空格校验
			if(/\s/.test(commercialChannelName)){
				showDialog1("查询条件不合法,请重新输入");
				return;
			}
			$.ajax({
				url:path+"/channel/getChannelByCondition",
				type : 'post',
				dataType:'text',
				data:{
					"commercialChannelName" : commercialChannelName// 渠道名称
				},
				success:function(result){
					var result = $.parseJSON(result);
					var channelBean = result.channelBean;
					if(result.resultCode=="0"){
						var head = "";
						head += "<tr>";
						head += "<td class='coupon_table_title'>序 号</td>";
						head += "<td class='coupon_table_title'>渠道编号</td>";
						head += "<td class='coupon_table_title'>渠道名称</td>";
						head += "<td class='coupon_table_title'>创建时间</td>";
						head += "<td class='coupon_table_title'>操作</td>";
						head += "</tr>";
						var html = "";
					    if(channelBean != undefined && channelBean != "undefined" ){
					    	html += "<tr>";
							html += "<td class='coupon_table_common'>1</td>";
							html += "<td class='coupon_table_common'>"+channelBean.tChannelId+"</td>";
							html += "<td class='coupon_table_common'>"+channelBean.tChannelName+"</td>";
							html += "<td class='coupon_table_common'>"+channelBean.tCreateTime+"</td>";
							
							html += "<td class='coupon_table_common'>";
							html += "<a href='"+path+"/channel/selectByChannelId?tChannelId="+channelBean.tChannelId+"&pageNum=1' class='coupon_chakan' >查看</a>";
							html += "<a href='"+path+"/channel/showEditChannel?tChannelId="+channelBean.tChannelId+"&pageNum=1' class='coupon_submit' >编辑</a>"; 
							html += "<a href='JavaScript:void(0)' onclick=channelManageJS.showDeleteChannel('"+channelBean.tChannelId+"') class='coupon_pass'>删除</a>";
							html += "</td>";
							html += "</tr>";
					    	html = head + html; 
					    }else{
					    	html = head + '<tr><td colspan="8" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
					    }
					    $("#commercialChannel").empty();
					    $("#commercialChannel").append(html);
					    $(".coupon_pageList_query").empty();
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
	   },
		editChannel : function(pageNum){
			var commercialFirstNum = $("#commercialFirstNum").val();
			var commercialFirstName = $("#commercialFirstName").val();
			var commercialFirstRemark = $("#commercialFirstRemark").val();
			if(commercialFirstName=="" || commercialFirstName==null){
				showDialog1("渠道名称不能为空");
				return;
			}
			$.ajax({
				url:path+"/channel/editChannel",
				type : 'post',
				dataType:'text',
				data:{
					"commercialFirstNum" : commercialFirstNum,// 渠道编号
					"commercialFirstName" : commercialFirstName,// 渠道名称
					"commercialFirstRemark" : commercialFirstRemark// 备注
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=="0"){
					    window.location.href=path+"/channel/showChannelList?pageNum="+pageNum;
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
	   },
	   showDeleteChannel : function (tChannelId){
		   tChannelId1 = tChannelId;
		   $("#confirmDeleteCard").removeClass("hide");
	   },
	   confirmDeleteCard : function(){
		   $.ajax({
				url:path+"/channel/deleteChannel",
				type : 'post',
				dataType:'text',
				data:{
					"commercialFirstNum" : tChannelId1// 渠道编号
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=="0"){
					    window.location.href=path+"/channel/showChannelList?pageNum=1";
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
	   },
	   insertChannel : function(){
			var channelName = $("#channelName").val();
			var channelRemark = $("#channelRemark").val();
			if(channelName=="" || channelName==null){
				showDialog1("渠道名称不能为空");
				return;
			}
			$.ajax({
				url:path+"/channel/insertChannel",
				type : 'post',
				dataType:'text',
				data:{
					"channelName" : channelName,// 渠道名称
					"channelRemark" : channelRemark// 备注
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=="0"){
					    window.location.href=path+"/channel/showChannelList?pageNum=1";
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
	   }
}