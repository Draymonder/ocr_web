var userAccount = "";//账号
var ruleID="";
var newstatus="";
var newId="";
var PageNum="";
var RuleManageJS = {
	
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount)){
				return;
			}
			window.location.href = path + "/ruleManage/showRuleManage?pageNum="+pageNum;
		},
		//按用户名或手机号模糊查询的结果分页后跳转到指定页码
		queryRuleByConditiongoToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			RuleManageJS.queryRuleByCondition(pageNum);
		},
	//进入创建用户页面
	createRule : function(pageNum){
		window.location.href=path+"/ruleManage/showCreateRule?pageNum="+pageNum;
	},
		
	//显示注销用户确认弹窗
	showRemoveRule : function(id){
		ruleID = id;
		$(".mask").removeClass("hide");
		$("#confirmRemoveUser").removeClass("hide");
	},
	
	//确认注销用户
	confirmRemoveUser : function(){
		$.ajax({
			url:path+"/ruleManage/removeRule",
			type : 'post',
			dataType:'text',
			data:{
				"ruleID" : ruleID// ID
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/ruleManage/showRuleManage?pageNum=1";
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},
	//启用或者停用规则弹窗 
	ShowUpdateStatus: function(id,status,pageNum){
		newstatus =status;
		newId=id;
		PageNum=pageNum;
		$(".mask").removeClass("hide");
		$("#updateStatus").removeClass("hide");
	},
	//确认启用或者停用规则
	updateStatus : function(){
		$.ajax({
			url:path+"/ruleManage/UpdateRuleStatus",
			type : 'post',
			dataType:'text',
			data:{
				"status" :newstatus,
				"id":newId,
				
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/ruleManage/showRuleManage?pageNum="+PageNum;
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},
	queryRuleByCondition : function(pageNum){
		var condition = $.trim($("#ruleName").val());
		var data = $.trim($("#txtBeginDate").val());
		var begindate=$("#txtBeginDate").val();//时间
        var enddate=$("#txtBeginDate1").val();//时间
		//校验查询条件是否为空
		if(condition=='请输入规则名称'&&begindate==''&&enddate==''){
			window.location.href = path + "/ruleManage/showRuleManage?pageNum=1";
			return;
		}
		
		$.ajax({
			url:path+"/ruleManage/queryRuleByCondition",
			type : 'post',
			dataType:'text',
			data:{
				"condition" : condition,//查询条件
				 "begindate":begindate,//日期
	             "enddate":enddate,//日期
				"pageNum":pageNum
				
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.resultCode == "0"){
					var page = result.page;
					var list = page.list;
					var head = "";
					head += "<tr class='tr_h'>";
					head += "<th >编号规则</th>";  //class='coupon_table_title'
					head += "<th >规则名称</th>"; //class='coupon_table_title'
					head += "<th >转换类型</th>"; //class='coupon_table_title'
					head += "<th >创建日期</th>";  //class='coupon_table_title'
					head += "<th >操作</th>";  //class='coupon_table_title'
					head += "</tr>";
					
					var html = "";
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0){
							html = head + '<tr><td colspan="11" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for ( var i = 0; i < list.length; i++) {
							var RuleBean = list[i];
							html += "<tr class='tr_d'>";
							//html += "<td class='coupon_table_common'>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
							html += "<td >"+RuleBean.ruleNumber+"</td>"; 
							html += "<td >"+RuleBean.ruleName+"</td>";
							html += "<td >"+RuleBean.covertype+"</td>";
							html += "<td >"+RuleBean.simpleDate+"</td>";
							html += "<td >";
							
							if(RuleBean.status!='0'){
								html += "<a href='javascript:;' class='coupon_chakan' onclick=RuleManageJS.ShowUpdateStatus('"+RuleBean.id+"','"+RuleBean.status+"','"+page.pageNum+"')>启用</a>";		
							}else{
								html += "<a href='javascript:;' class='coupon_pass gray' onclick=RuleManageJS.ShowUpdateStatus('"+RuleBean.id+"','"+RuleBean.status+"','"+page.pageNum+"')>停用</a>";		
							}
							//html += "<a href='"+path+"/ruleManage/ruleDetail?id="+RuleBean.id+"&pageNum="+page.pageNum+"' class='coupon_chakan' >查看</a>";
							html += "<a href='"+path+"/ruleManage/showEditRule?id="+RuleBean.id+"&pageNum="+page.pageNum+"' class='coupon_submit' >编辑</a>";
                            html += "<a href='javascript:;' class='coupon_pass' onclick=RuleManageJS.showRemoveRule('"+RuleBean.id+"')>注销</a>";
							html += "</td>";
							html += "</tr>";
						}
							html = head + html;
						
					}
					$("#RuleList").empty();
					$("#RuleList").append(html);
					$(".coupon_pageList_query").empty();
					if(list.length > 0){
					var pHtml = "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+page.prev+"') class=''  >上一页</a>";
					
					 if(page.pageCount<6){
							for ( var i = 1; i <= page.pageCount; i++) {
								if(page.pageNum == i){
									pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
								}else{
									pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+i+"') class='' >"+i+"</a>";
								}
							}
							
						}else{
							if(page.pageNum<4){
								for ( var i = 1; i <= 4; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+i+"') class='' >"+i+"</a>";
									}
								}
								pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}else if(page.pageNum > page.pageCount-3){
									pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition(1) class='' >1</a>";	 
									pHtml += "<a>...</a>";
								for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									
								}else{
									pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition(1) class='' >1</a>";
									pHtml += "<a>...</a>";
									for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									pHtml += "<a>...</a>";
									pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
								}
							
						}
					
					 pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByCondition('"+page.next+"') class='' >下一页</a>";	 
					 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
					 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
					 pHtml += "<a href='javascript:;' onclick=RuleManageJS.queryRuleByConditiongoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
					$(".coupon_pageList_query").append(pHtml);
				  }
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
	
	
		

