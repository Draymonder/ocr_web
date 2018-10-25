var tShopId = "";//店铺编号
var tSellerId = "";//商家编号
var ShopManageJS={
		//跳转到指定页码
		goToPage : function(tSellerId,pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			window.location.href = path + "/shop/showShopList?tSellerId="+tSellerId+"&pageNum="+pageNum;	
			
		},
		
		//对模糊查询后的结果进行页面跳转
		queryShopByConditiongoToPage: function(tSellerId,pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			ShopManageJS.selectShopByCondition(tSellerId,pageNum);
		},
		selectShopByCondition : function(tSellerId,pageNum){
			
			var tShopName = $.trim($("#tShopName").val());
			var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
			//密码空格校验
			if(tShopName == '请输入店铺名称'){
				window.location.href = path + "/shop/showShopList?tSellerId="+tSellerId+"&pageNum=1";
				return;
			}if(regEn.test(tShopName)== true){
				showDialog1("查询条件不合法,请重新输入");
				return;
			}
			$.ajax({
				url:path+"/shop/selectShopByCondition",
				type : 'post',
				dataType:'text',
				data:{
					"tShopName" : tShopName,// 商家名称
					"tSellerId" : tSellerId,
					"pageNum" : pageNum,
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						var page = result.page;
						var list = result.shopBeanList;
						var head = "";
						head += "<tr>";
						head += "<td class='coupon_table_title'>序 号</td>";
						head += "<td class='coupon_table_title'>店铺编号</td>";
						head += "<td class='coupon_table_title'>归属商家</td>";
						head += "<td class='coupon_table_title'>归属地</td>";
						head += "<td class='coupon_table_title'>店铺名称</td>";
						head += "<td class='coupon_table_title'>店铺地址</td>";
						head += "<td class='coupon_table_title'>操作</td>";
						head += "</tr>";
						
						var html = "";
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0 ){
							html = head + '<tr><td colspan="8" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for(var i=0; i<list.length; i++){
								var shopBean = list[i];
								html += "<tr>";
								html += "<td class='coupon_table_common'>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
								html += "<td class='coupon_table_common'>"+shopBean.tShopId+"</td>";
								html += "<td class='coupon_table_common'>"+shopBean.tSellerName+"</td>";
								html += "<td class='coupon_table_common'>"+shopBean.tShopProvince+""+shopBean.tShopCity+"</td>";
								html += "<td class='coupon_table_common'>"+shopBean.tShopName+"</td>";
								html += "<td class='coupon_table_common'>"+shopBean.tShopAddress+"</td>";
								
								html += "<td class='coupon_table_common'>";
								html += "<a href='"+path+"/shop/showShopDetail?tShopId="+shopBean.tShopId+"' class='coupon_chakan' >查看</a>";
								html += "<a href='"+path+"/shop/showEditShop?tShopId="+shopBean.tShopId+"' class='coupon_submit' >编辑</a>";
								html += "<a href='javascript:;' class='coupon_pass' onclick=ShopManageJS.showDeleteShop('"+shopBean.tShopId+"','"+shopBean.tSellerId+"')>删除</a>";
								html += "</td>";
								html += "</tr>";
							}
							html = head + html;
						}
						$("#shopList").empty();
						$("#shopList").append(html);
						$(".coupon_pageList_query").empty();
						if(list.length > 0){
						var pHtml = "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+page.prev+"') class=''  >上一页</a>";
						
						if(page.pageCount<6){
						for ( var i = 1; i <= page.pageCount; i++) {
							if(page.pageNum == i){
								pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
							}else{
								pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+i+"') class='' >"+i+"</a>";
							}
						}
							
						}else{
							if(page.pageNum<4){
								for ( var i = 1; i <= 4; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+i+"') class='' >"+i+"</a>";									
										
									}
							}
								pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}else if(page.pageNum > page.pageCount-3){
								pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"',1) class='' >1</a>";	 
								pHtml += "<a>...</a>";
								for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+i+"') class='' >"+i+"</a>";
									}
									
								}
								
							}else{
								pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"',1) class='' >1</a>";
								pHtml += "<a>...</a>";
								for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+i+"') class='' >"+i+"</a>";
									}
									
								}pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}
								
						}	
						 pHtml += "<a href='javascript:;' onclick=ShopManageJS.selectShopByCondition('"+tSellerId+"','"+page.next+"') class='' >下一页</a>";	 
						 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
						 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
						 pHtml += "<a href='javascript:;' onclick=ShopManageJS.queryShopByConditiongoToPage('"+tSellerId+"','"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
		//删除店铺
		showDeleteShop:function(shopId,sellerId){
			tShopId = shopId;
			tSellerId = sellerId;
			$("#confirmDeleteShop").removeClass("hide");
		},
		confirmDeleteShop:function(){
			$.ajax({
				url:path+"/shop/deleteShop",
				type : 'post',
				dataType:'text',
				data:{
					"tShopId" : tShopId,// 商户编号
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=="0"){
						window.location.href = path
						+ "/shop/showShopList?tSellerId="+tSellerId+"&pageNum=1 ";
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			CommonComponent.showFailureDialog("网络繁忙");
		  	}});
	   },
		
		//批量导入店铺
		bulkImportCommercial:function(){
			$("#bulkImportCommercial").removeClass("hide");
		},
		uploadCommercial:function(tSellerId){
			$.ajaxFileUpload({
				type : "POST",
				url : path + "/shop/uploadExcle",
				data : {
					"sellerId" : tSellerId
				},// 要传到后台的参数，没有可以不写
				secureuri : false,// 是否启用安全提交，默认为false
				fileElementId : 'commercialMultiple',// 文件选择框的id属性
				dataType : 'text',// 服务器返回的格式
				async : false,
				success : function(result) {
					var result = $.parseJSON(result);
					if (result.resultCode == "0") {
						$("#uploadedFile").val(result.fileName);
						showDialog1("导入成功");
						$(".coupon_queren").click( function () {
							ShopManageJS.bulkImportCommercial();
						});
					} else {
						showDialog1(result.msg);
					}
				},
				error : function(result, status, e) {
					showDialog1("网络繁忙");
				}
			});
		},
		querenruku:function(tSellerId){
			
			var a = $("#uploadedFile").val();
			
			if(a != ""){
				window.location.href = path + "/shop/showShopList?tSellerId="+tSellerId+"&pageNum=1 ";
				
			}else{
				showDialog1("导入不能为空");
				$(".coupon_queren").click( function () {
					ShopManageJS.bulkImportCommercial();
				});
				return;

			}
		},
		
		checkData:function(){
			
			var file = $("#upfile").val();
			var fileSuffix = file.substring(file.lastIndexOf(".")+1);
			if(fileSuffix === "xlsx" || fileSuffix === "xls"){
				return true;
			}else{
				showDialog1("导入的文件格式不对,请导入.xlsx或者.xls格式的文件！");
				return false;
			}
			
		}
		
	}