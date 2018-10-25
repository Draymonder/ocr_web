var busiCode = "";//接口编码
var interfaceState = "0";//接口当前使用状态
var interfaceJS =
{
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
				window.location.href = path + "/interfaceSwith/showInterfaceList?pageNum="+pageNum;
			},
		//模糊查询的分页跳转页面
		selectInterfaceByConditionGoToPage : function(pageCount){
				var pageNum = $("#go-to").val();
				if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
					return;
				}
				interfaceJS.selectInterfaceByCondition(pageNum);
				
			},
		selectInterfaceByCondition : function(pageNum){
			var busiCode = $.trim($("#busiCode").val());
			//密码空格校验
			if(/\s/.test(busiCode) || busiCode == "请输入接口编码" ){
//				showDialog1("查询条件不合法,请重新输入");
//				return;
				window.location.href = path + "/interfaceSwith/showInterfaceList?pageNum=1";
			}
			$.ajax({
				url:path+"/interfaceSwith/selectInterfaceByCondition",
				type : 'post',
				dataType:'text',
				data:{
					"busiCode" : busiCode,// 接口编码
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
						head += "<td class='coupon_table_title'>接口名称</td>";
						head += "<td class='coupon_table_title'>接口编码</td>";
						head += "<td class='coupon_table_title'>状态</td>";
						head += "<td class='coupon_table_title'>管理</td>";
						head += "</tr>";
						
						var html = "";
						
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0 ){
							html = head + '<tr><td colspan="8"  class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for(var i=0; i<list.length ; i++){
								var bean = list[i];
								html += "<tr>";
								html += "<td class='coupon_table_common'>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
								html += "<td class='coupon_table_common'>"+bean.interfaceClass+"</td>";
								html += "<td class='coupon_table_common'>"+bean.busiCode+"</td>";
								if(bean.interfaceSate == "0"){
									html += "<td class='coupon_table_common'>已启用</td>";
								}else{
									html += "<td class='coupon_table_common'>未启用</td>";
								}
								
								html += "<td class='coupon_table_common'>";
								if(bean.interfaceState == "0"){
									html += "<a href='javascript:;' class='coupon_pass gray' >启用</a>";
									html += "<a href='javascript:;' class='coupon_pass' onclick=interfaceJS.showUpdateInterface('"+bean.busiCode+"','1')>停用</a>";
								}else{
									html += "<a href='javascript:;' class='coupon_pass' onclick=interfaceJS.showUpdateInterface('"+bean.busiCode+"','0')>启用</a>";
									html += "<a href='javascript:;' class='coupon_pass gray'>停用</a>";
								}
								html += "</td>";
								html += "</tr>";
							}
							html = head + html;
						}
						
						$("#interfaceList").empty();
						$("#interfaceList").append(html);
						$(".coupon_pageList_query").empty();
						if(list.length > 0){
							var pHtml = "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+page.prev+"') class=''  >上一页</a>";
							
							if(page.pageCount<6){
								for ( var i = 1; i <= page.pageCount; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+i+"') class='' >"+i+"</a>";
									}
								}
								
							}else{
								if(page.pageNum<4){
									for ( var i = 1; i <= 4; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+i+"') class='' >"+i+"</a>";
										}
									}
									pHtml += "<a>...</a>";
									pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
								}else if(page.pageNum > page.pageCount-3){
										pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition(1) class='' >1</a>";	 
										pHtml += "<a>...</a>";
									for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
											if(page.pageNum == i){
												pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
											}else{
												pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+i+"') class='' >"+i+"</a>";
											}
											
										}
										
									}else{
										pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition(1) class='' >1</a>";
										pHtml += "<a>...</a>";
										for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
											if(page.pageNum == i){
												pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
											}else{
												pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+i+"') class='' >"+i+"</a>";
											}
											
										}
										pHtml += "<a>...</a>";
										pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
									}
								
							}
							
							 pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByCondition('"+page.next+"') class='' >下一页</a>";	 
							 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
							 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
							 pHtml += "<a href='javascript:;' onclick=interfaceJS.selectInterfaceByConditionGoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
			},
			//新增接口
		createInterface : function(){
			var busiCode = $("#busiCode").val();
			var interfaceNum = $("#interfaceNum").val();
			var interfaceClass = $("#interfaceClass").val();
			var interfaceMethod = $("#interfaceMethod").val();
			var interfaceUrl = $("#interfaceUrl").val();
			var interfaceDesc = $("#interfaceDesc").val();
			
			if(busiCode==""||busiCode==null){
				showDialog1("请输入接口编码");
				return;
			}
			if(! /^[A-Z]+$/.test(busiCode)){
				showDialog1("接口编码为大写字母");
				return;
			}
			if(! /^[0-9]+$/.test(interfaceNum)){
				showDialog1("接口号码为数字");
				return;
			}
			if(interfaceClass==""||interfaceClass==null){
				showDialog1("请输入接口类名");
				return;
			}
			if(interfaceMethod==""||interfaceMethod==null){
				showDialog1("请输入方法名");
				return;
			}
			if(interfaceUrl==""||interfaceUrl==null){
				showDialog1("请输入使用路径");
				return;
			}
			$.ajax({
				url:path+"/interfaceSwith/insertInterface",
				type : 'post',
				dataType:'text',
				data:{
					"busiCode" : busiCode,
					"interfaceNum" : interfaceNum,
					"interfaceClass" : interfaceClass,
					"interfaceMethod" : interfaceMethod,
					"interfaceUrl" : interfaceUrl,
					"interfaceDesc" : interfaceDesc,
					"interfaceState" : interfaceState
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						window.location.href = path + "/interfaceSwith/showInterfaceList?pageNum=1";
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
		},
			
		//显示更新接口弹窗
		showUpdateInterface : function(uniqueBusiCode,state){
			busiCode = uniqueBusiCode;
			interfaceState = state;
	        var msg = "";
	        if(interfaceState == "1"){
	        	msg = "确认关闭编号为："+busiCode+" 的接口吗？";
	        }else if(interfaceState == "0"){
	        	msg = "确认开启编号为："+busiCode+" 的接口吗";
	        }
	        $("#confirmTip").html(msg);
	        $('.mask').removeClass('hide');
	        $('#confirmUpdateInterface').removeClass('hide');
		},
		
		//确认更新接口
		confirmUpdateInterface : function(){
			$.ajax({
				url:path+"/interfaceSwith/interfaceSwith",
				type : 'post',
				dataType:'text',
				data:{
					"busiCode" : busiCode,
					"interfaceState" : interfaceState
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						window.location.href = path + "/interfaceSwith/showInterfaceList?pageNum=1";
					}
					else{
						showDialog1("接口启用/调用失败");
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
		},
		selectUseState : function(state){
			interfaceState = state ;
		}
	};