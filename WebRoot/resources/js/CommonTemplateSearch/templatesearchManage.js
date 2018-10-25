var userAccount = "";//账号

var templateSearchManageJS = {
	
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount)){
				return;
			}
			window.location.href = path + "/CommonTemplateSearchManage/showCommonTemplateSearchManage?pageNum="+pageNum;
		},
		//按用户名或手机号模糊查询的结果分页后跳转到指定页码
		queryUserByConditiongoToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			templateSearchManageJS.queryUserByCondition(pageNum);
		},
	
	//显示注销用户确认弹窗
	showRemoveUser : function(uniqueUserAccount){
		userAccount = uniqueUserAccount;
		$(".mask").removeClass("hide");
		$("#confirmRemoveUser").removeClass("hide");
	},
	
	
	
	
	checkedAll: function(){
		$(".tr_d input[type='checkbox']").each(function(i){
			if($("#allchecked").is(':checked')){
			$(this).attr("checked",true);
			}else{
			$(this).attr("checked",false);	
			}
		})
	},
	
	updateStatus: function(pageNum){
		var ids="";//要改变状态的数据id集合
		var templatename="";//模板名称集合
		$(".tr_d input[type='checkbox']").each(function(i){
			templatename=$(this).attr("title"); 
			if($(this).is(':checked')&&templatename!=""){
			ids+=$(this).attr("id")+",";
		
			}
		}) 
	
		//showDialog1(ids);
		if(ids==""){
			//showDialog1("请先勾选check框！！！");
			return;
		}
		$.ajax({
			url:path+"/CommonTemplateSearchManage/updateStatus",
			type : 'post',
			dataType:'text',
			data:{
				"ids" : ids,
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/CommonTemplateSearchManage/showCommonTemplateSearchManage?pageNum="+pageNum;
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},
	
	//确认注销用户
	confirmRemoveUser : function(){
		$.ajax({
			url:path+"/userManage/removeUser",
			type : 'post',
			dataType:'text',
			data:{
				"userAccount" : userAccount// 账号
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/taskSearchManage/showTaskSearchManage?pageNum=1";
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},

    queryUserByCondition : function(pageNum){
		var templateName = $.trim($("#templateName").val());//模板名称
		var factoryName=  $.trim($("#FactoryName").val());//工厂名称
		var fileNumber=  $.trim($("#filenumber").val());//文件名
		var userName=  $.trim($("#userName").val());//用户姓名
		var date=$.trim($("#txtBeginDate").val());//开始日期
		var date1=$.trim($("#txtBeginDate1").val());//结束日期1
		//校验查询条件是否为空
		if(templateName=='请输入模板名称'&&factoryName=='请输入工厂名称'&&fileNumber=='请输入文件名'&&userName=='请输入人员名称'&&date==''&&date1==''){
			window.location.href = path + "/CommonTemplateSearchManage/showCommonTemplateSearchManage?pageNum=1";
			return;
		}
		
		if(templateName=='请输入模板名称'){
			templateName="";
		}
		
		if(factoryName=='请输入工厂名称'){
			factoryName="";
		}
		
		if(fileNumber=='请输入文件名'){
			fileNumber="";
			
		}
		if(userName=='请输入人员名称'){
			userName="";
		}
	
		
		$.ajax({
			url:path+"/CommonTemplateSearchManage/queryByCondition",
			type : 'post',
			dataType:'text',
			data:{
				"templateName" : templateName,//查询条件
				"factoryName" : factoryName,//查询条件
				"fileNumber" : fileNumber,//查询条件
				"userName" : userName,//查询条件
				"begindate" : date,//查询条件日期
				"enddate" : date1,//查询条件日期
				"pageNum" : pageNum
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.resultCode == "0"){
					var page = result.page;
					var list = page.list;
					var head = "";
					head += "<tr class='tr_h'>";
					head += "<th >序 号</th>";
					head += "<th >任务ID</th>";
					head += "<th >模板名称</th>";
					head += "<th >文件名</th>";
					head += "<th >创建人</th>";
					head += "<th >工厂</th>";
					head += "<th >创建日期</th>";
					head += "<th >是否异常</th>";
					head += "<th >是否确认</th>";
					head += "<th >操作</th>";
					//head += "<td class='coupon_table_title'>是否启用</td>";
					//head += "<td class='coupon_table_title'>操作</td>";
					head += "</tr>";
					
					var html = "";
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0){
							html = head + '<tr><td colspan="11" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for ( var i = 0; i < list.length; i++) {
							var DataBean = list[i];
							html += "<tr class='tr_d'>";
							html += "<td ><input type='checkbox' id='" +DataBean.id+"'  title='" +DataBean.templateName+"'></input>"+DataBean.id+"</td>";  
							//html += "<td ><input type='checkbox' id='" +DataBean.id+"'></input>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
							html += "<td >"+DataBean.taskId+"</td>";
							html += "<td >"+DataBean.templateName+"</td>";
                            html += "<td >"+DataBean.fileNumber+"</td>";
							html += "<td >"+DataBean.userName+"</td>";
							html += "<td >"+DataBean.factoryName+"</td>";
							html += "<td >"+DataBean.simpleTime+"</td>";
						
							if(DataBean.flag2=='0'){
								html += "<td style='color:red'>是</td>";
							}else{
								html += "<td >否</td>";
							}
							if(DataBean.flag1=='0'){
								html += "<td style='color:red' >未确认</td>";
							}else{
								html += "<td style='color:green'>已确认</td>";
							}
							
							html += " <td><span><a class='coupon_submit' target='_blank' href='"+path+"/CommonTemplateSearchManage/showDetail?id="+DataBean.id+"&pageNum="+page.pageNum+"' target='_blank'>详情</a></span>" +
                                "<span><a class='coupon_download' target='_blank' href='"+path+"/uploads/common/"+DataBean.fileNumber+"' target='_blank'>导出文件</a></span></td>";

							html += "</tr>";
						}
							html = head + html;
						
					}
					$("#templateList").empty();
					$("#templateList").append(html);
					$(".coupon_pageList_query").empty();
					if(list.length > 0){
					var pHtml = "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+page.prev+"') class=''  >上一页</a>";
					
					 if(page.pageCount<6){
							for ( var i = 1; i <= page.pageCount; i++) {
								if(page.pageNum == i){
									pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
								}else{
									pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
								}
							}
							
						}else{
							if(page.pageNum<4){
								for ( var i = 1; i <= 4; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
									}
								}
								pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}else if(page.pageNum > page.pageCount-3){
									pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition(1) class='' >1</a>";	 
									pHtml += "<a>...</a>";
								for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									
								}else{
									pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition(1) class='' >1</a>";
									pHtml += "<a>...</a>";
									for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									pHtml += "<a>...</a>";
									pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
								}
							
						}
					
					 pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByCondition('"+page.next+"') class='' >下一页</a>";	 
					 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
					 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
					 pHtml += "<a href='javascript:;' onclick=templateSearchManageJS.queryUserByConditiongoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
	
	
		

