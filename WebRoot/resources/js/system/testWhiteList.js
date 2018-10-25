var oldPhoneNumber ="";
var oldPhoneNumber1 = "";
var oldCity = "";
var TestWhiteListJS =
{
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum="+pageNum;
		},
		//按手机号模糊查询的结果分页后跳转到指定页码
		selectWhiteListgoToPage:function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			TestWhiteListJS.selectWhiteList(pageNum);
			
		},
		//显示新增测试白名单弹窗
		showInsertWhiteList : function(){
			//先关闭提示框
			$('.mask').addClass('hide');
		    $('.coupon_tanckuang').addClass('hide');
		    $('.coupon_tanckuang_shenhe').addClass('hide');
		    $('.coupon_tanckuang_chehui').addClass('hide');
		    //再显示新增测试白名单弹窗
			$("#phoneNumber_insert").val("");
			$("#city_insert").html("");
			$(".mask").removeClass("hide");
			$("#insertWhiteList").removeClass("hide");
		},
		selectWhiteList : function(pageNum){
			var phoneNum = $("#phoneNumber").val();
			
			if(phoneNum == '请输入测试号码'){
				window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum=1";
				return;	
			}
			
//			if(phoneNum != '请输入测试号码'){
				//模糊查询的手机号(完整手机号或者手机号的一部分)
//				if(!CommonJS.phoneNumberCheck(phoneNum)){
//					return;
//				}
//			}
			
			$.ajax({
				url:path+"/testWhiteList/selectTestWhiteList",
				type : 'post',
				dataType:'text',
				data:{
					"phoneNum" : phoneNum,
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
						head += "<td class='coupon_table_title'>测试号码</td>";
						head += "<td class='coupon_table_title'>归属地</td>";
//						测试白名单里面的号码一经新建后状态就是默认启用并且不设置停用按钮，在表格里面不显示状态这个字段
//						head += "<td class='coupon_table_title'>状态</td>";
						head += "<td class='coupon_table_title'>操作</td>";
						head += "</tr>";
						
						var html = "";
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0){
							html = head + '<tr><td colspan="8" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for(var i=0;i<list.length;i++){
								var testWhite = list[i];
								html += "<tr>";
								html += "<td class='coupon_table_common'>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
								html += "<td class='coupon_table_common'>"+testWhite.phonenum+"</td>";
								html += "<td class='coupon_table_common'>"+testWhite.city+"</td>";
//								html += "<td class='coupon_table_common'>"+testWhite.state+"</td>";
								html += "<td class='coupon_table_common'>";
								html += "<a href='javascript:;' class='coupon_submit' onclick=TestWhiteListJS.showEditWhiteList('"+testWhite.phonenum+"','"+testWhite.city+"')>修改</a>";
								html += "<a href='javascript:;' class='coupon_pass' onclick=TestWhiteListJS.showDeleteWhiteList('"+testWhite.phonenum+"')>删除</a>";
								html += "</td>";
								html += "</tr>";
							}
							html = head + html;
						}
						
						$("#whiteList").empty();
						$("#whiteList").append(html);
						$(".coupon_pageList_query").empty();
						if(list.length > 0){
						var pHtml = "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+page.prev+"') class=''  >上一页</a>";
						
						if(page.pageCount<6){
							for ( var i = 1; i <= page.pageCount; i++) {
								if(page.pageNum == i){
									pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
								}else{
									pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+i+"') class='' >"+i+"</a>";
								}
							}
							
						}else{
							if(page.pageNum<4){
								for ( var i = 1; i <= 4; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+i+"') class='' >"+i+"</a>";
									}
								}
								pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}else if(page.pageNum > page.pageCount-3){
									pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList(1) class='' >1</a>";	 
									pHtml += "<a>...</a>";
								for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									
								}else{
									pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList(1) class='' >1</a>";
									pHtml += "<a>...</a>";
									for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									pHtml += "<a>...</a>";
									pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
								}
							
						}
							
							 pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteList('"+page.next+"') class='' >下一页</a>";	 
							 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
							 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
							 pHtml += "<a href='javascript:;' onclick=TestWhiteListJS.selectWhiteListgoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
		//新增测试白名单
		insertWhiteList : function(){
			$('.mask').addClass('hide');
	        $('.bai_menu').addClass('hide');
			var phoneNum = $("#phoneNumber_insert").val();
			var city = $("#city_insert").html();
			//地市字符串转换为相应数字代号
			city = CommonJS.getCityCode(city);
			//手机号码合法性校验
			if(!CommonJS.phoneNumberCheck(phoneNum,"测试号码")){
				$(".coupon_queren").click( function () {
//					window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum=1";
					TestWhiteListJS.showInsertWhiteList();
					});
				return;
			}
			if(city==""||city==null){
				showDialog1("请选择归属地");
				$(".coupon_queren").attr('onclick', '').unbind('click').click( function () {
//					window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum=1";
					TestWhiteListJS.showInsertWhiteList();
				});
				return;
			}
			
			$.ajax({
				url:path+"/testWhiteList/insertTestWhite",
				type : 'post',
				dataType:'text',
				data:{
					"phoneNum" : phoneNum,// 测试白名单号码
					"city" : city//城市
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum=1";
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
		},
		
		//显示修改测试白名单弹窗
		showEditWhiteList : function(phoneNumber,city){
			//先关闭提示框
			$('.mask').addClass('hide');
		    $('.coupon_tanckuang').addClass('hide');
		    $('.coupon_tanckuang_shenhe').addClass('hide');
		    $('.coupon_tanckuang_chehui').addClass('hide');
		    //后显示修改测试白名单弹窗
			oldPhoneNumber = phoneNumber;
			oldPhoneNumber1 = phoneNumber;
			oldCity = city;
			$(".mask").removeClass("hide");
			$("#editWhiteList").removeClass("hide");
			$("#phoneNumber_edit").val(phoneNumber);
			$("#city_edit").html(city);
		},
		
		//修改测试白名单
		editWhiteList : function(pageNum){
			$('.mask').addClass('hide');
	        $('.bai_menu').addClass('hide');
			
			var phoneNum = $("#phoneNumber_edit").val();
			var city = $("#city_edit").html();
			//地市字符串转换为相应数字代号
			city = CommonJS.getCityCode(city);
			//手机号码合法性校验
			if(!CommonJS.phoneNumberCheck(phoneNum,"测试号码")){
				$(".coupon_queren").click( function () {
					TestWhiteListJS.showEditWhiteList(oldPhoneNumber1,oldCity);
					});
				return;
			}
			
			if(city==""||city==null){
				showDialog1("请选择归属地");
				$(".coupon_queren").attr('onclick', '').unbind('click').click( function () {
					TestWhiteListJS.showEditWhiteList(oldPhoneNumber1,oldCity);
				});
				return;
			}
			
			$.ajax({
				url:path+"/testWhiteList/editWhiteList",
				type : 'post',
				dataType:'text',
				data:{
					"oldPhoneNumber" : oldPhoneNumber,//测试白名单旧号码
					"phoneNumber" : phoneNum,// 测试白名单新号码
					"city" : city//城市
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum="+pageNum;
					}
					else{
						showDialog1(result.resultMsg);
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}});
		},
		
		//显示删除测试白名单弹窗
		showDeleteWhiteList : function(phoneNumber){
			$("#phoneNumber_delete").html(phoneNumber);
			$(".mask").removeClass("hide");
			$("#deleteWhiteList").removeClass("hide");
		},
		
		//删除测试白名单
		deleteWhiteList : function(){
			$('.mask').addClass('hide');
	        $('.bai_menu').addClass('hide');
			
			var phoneNumber = $("#phoneNumber_delete").html();
			
			$.ajax({
				url:path+"/testWhiteList/deleteWhiteList",
				type : 'post',
				dataType:'text',
				data:{
					"phoneNumber" : phoneNumber// 测试白名单新号码
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						window.location.href = path + "/testWhiteList/showTestWhiteList?pageNum=1";
					}
					else{
						showDialog1("删除失败");
					}
				},
		  		error:function(result){
		  			showDialog1("网络繁忙");
		  	}
		});
	},
	closeDialog1 : function ()
    {
        $('.mask').addClass('hide');
        $('#insertWhiteList').addClass('hide');
    }
		}