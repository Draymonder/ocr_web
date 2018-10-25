var userAccount = "";//账号

var UserManageJS = {
	
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount)){
				return;
			}
			window.location.href = path + "/authorityManage/showAuthorityManage?pageNum="+pageNum;
		},
		//按用户名或手机号模糊查询的结果分页后跳转到指定页码
		queryUserByConditiongoToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			UserManageJS.queryUserByCondition(pageNum);
		},
	//进入创建用户页面
	createUser : function(pageNum){
		window.location.href=path+"/authorityManage/showCreateAuthority?pageNum="+pageNum;
	},
		
	//显示注销用户确认弹窗
	showRemoveUser : function(uniqueUserAccount){
		userAccount = uniqueUserAccount;
		$(".mask").removeClass("hide");
		$("#confirmRemoveUser").removeClass("hide");
	},
	
	//确认注销用户
	confirmRemoveUser : function(){
		//alert(userAccount);
		$.ajax({
			url:path+"/authorityManage/removeAuthority",
			type : 'post',
			dataType:'text',
			data:{
				"authID" : userAccount // 权限ID
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/authorityManage/showAuthorityManage?pageNum=1";
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},
	queryUserByCondition : function(pageNum){
		var condition = $.trim($("#userName").val());//权限名称
		//校验查询条件是否为空
		if(condition=='请输入权限名称'){
			window.location.href = path + "/authorityManage/showAuthorityManage?pageNum=1";
			return;
		}
		//密码空格校验
		if(/\s/.test(condition)){
			showDialog1("查询条件不合法,请重新输入");
			return;
		}
		$.ajax({
			url:path+"/authorityManage/queryAuthorityByCondition",
			type : 'post',
			dataType:'text',
			data:{
				"condition" : condition,//查询条件
				"pageNum" : pageNum
			},
			success:function(result){
				var result = $.parseJSON(result);
				if(result.resultCode == "0"){
					var page = result.page;
					var list = page.list;
					var head = "";
					head += "<tr>";
					head += "<td class='coupon_table_title'>序 号</td>";
					head += "<td class='coupon_table_title'>权限名称</td>";
					//head += "<td class='coupon_table_title'>角色编号</td>";
					//head += "<td class='coupon_table_title'>所属地市</td>";
					//head += "<td class='coupon_table_title'>角色</td>";
					//head += "<td class='coupon_table_title'>联系方式</td>";
					//head += "<td class='coupon_table_title'>是否启用</td>";
					head += "<td class='coupon_table_title'>操作</td>";
					head += "</tr>";
					
					var html = "";
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0){
							html = head + '<tr><td colspan="11" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for ( var i = 0; i < list.length; i++) {
							var userBean = list[i];
							html += "<tr>";
							html += "<td class='coupon_table_common'>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
							html += "<td class='coupon_table_common'>"+userBean.name+"</td>";
							//if(userBean.fUserLevel == '0'){
							//	html += "<td class='coupon_table_common'>--</td>";
							//}else{
							//	html += "<td class='coupon_table_common'>"+userBean.channelName+"</td>";
							//}
							
							//html += "<td class='coupon_table_common'>"+userBean.fCity+"</td>";
							//html += "<td class='coupon_table_common'>"+userBean.roleName+"</td>";
							//html += "<td class='coupon_table_common'>"+userBean.fUserNumber+"</td>";
							//html += "<td class='coupon_table_common'>"+userBean.roleNumber+"</td>";
							
							html += "<td class='coupon_table_common'>";
							//html += "<a href='"+path+"/RoleManage/roleDetail?id="+userBean.id+"&pageNum="+page.pageNum+"' class='coupon_chakan' >查看</a>";
							html += "<a href='"+path+"/authorityManage/showEditAuthority?id="+userBean.id+"&pageNum="+page.pageNum+"' class='coupon_submit' >编辑</a>";
							//html += "<a href='"+path+"/authorityManage/showPermission?id="+userBean.id+"&pageNum="+page.pageNum+"' class='coupon_submit'>权限管理</a>";
							if(userBean.id != '1'&&userBean.id != '2'&&userBean.id != '3'&&userBean.id != '7'){
								html += "<a href='javascript:;' class='coupon_pass' onclick=UserManageJS.showRemoveUser('"+userBean.id+"')>注销</a>";
							}else{
								html += "<a href='javascript:;' class='coupon_pass gray' >注销</a>";
							}
							html += "</td>";
							html += "</tr>";
						}
							html = head + html;
						
					}
					$("#userList").empty();
					$("#userList").append(html);
					$(".coupon_pageList_query").empty();
					if(list.length > 0){
					var pHtml = "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+page.prev+"') class=''  >上一页</a>";
					
					 if(page.pageCount<6){
							for ( var i = 1; i <= page.pageCount; i++) {
								if(page.pageNum == i){
									pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
								}else{
									pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
								}
							}
							
						}else{
							if(page.pageNum<4){
								for ( var i = 1; i <= 4; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
									}
								}
								pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}else if(page.pageNum > page.pageCount-3){
									pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition(1) class='' >1</a>";	 
									pHtml += "<a>...</a>";
								for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									
								}else{
									pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition(1) class='' >1</a>";
									pHtml += "<a>...</a>";
									for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									pHtml += "<a>...</a>";
									pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
								}
							
						}
					
					 pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByCondition('"+page.next+"') class='' >下一页</a>";	 
					 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
					 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
					 pHtml += "<a href='javascript:;' onclick=UserManageJS.queryUserByConditiongoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
	
	
		

