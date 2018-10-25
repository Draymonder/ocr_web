var tSellerId = "";//商家编号
var SellerManageJS={
		//跳转到指定页码
		goToPage : function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			window.location.href = path + "/seller/showSellerList?pageNum="+pageNum;
		},
		//对模糊查询后的结果进行页面跳转
		querySellerByConditiongoToPage: function(pageCount){
			var pageNum = $("#go-to").val();
			if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
				return;
			}
			SellerManageJS.selectSellerByCondition(pageNum);
		},
		selectSellerByCondition : function(pageNum){
			var tSellerName = $.trim($("#tSellerName").val());
			var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
			//密码空格校验
			if(tSellerName == '请输入商家名称'){
				window.location.href = path + "/seller/showSellerList?pageNum=1";
				return;
			}if(regEn.test(tSellerName)== true){
				showDialog1("查询条件不合法,请重新输入");
				return;
			}
			$.ajax({
				url:path+"/seller/selectSellerByCondition",
				type : 'post',
				dataType:'text',
				data:{
					"tSellerName" : tSellerName,// 商家名称
					"pageNum" : pageNum
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode == "0"){
						var page = result.page;
						var list = page.list;
						var userLevel = result.userLevel;
						var head = "";
						head += "<tr>";
						head += "<td class='coupon_table_title'>序 号</td>";
						head += "<td class='coupon_table_title'>商家编号</td>";
						head += "<td class='coupon_table_title'>归属渠道</td>";
						head += "<td class='coupon_table_title'>商家名称</td>";
						head += "<td class='coupon_table_title'>创建时间</td>";
						head += "<td class='coupon_table_title'>操作</td>";
						head += "</tr>";
						
						var html = "";
						
						//根据返回结果拼接 表单白名单信息
						if(list.length == 0){
							html = head + '<tr><td colspan="8" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
						}else{
							for(var i=0; i<list.length; i++){
								var sellerBean = list[i];
								html += "<tr>";
								html += "<td class='coupon_table_common'>"+eval(i+1+(page.pageNum-1)*page.size)+"</td>";
								html += "<td class='coupon_table_common'>"+sellerBean.tSellerId+"</td>";
								html += "<td class='coupon_table_common'>"+sellerBean.channelName+"</td>";
								html += "<td class='coupon_table_common'>"+sellerBean.tSellerName+"</td>";
								html += "<td class='coupon_table_common'>"+sellerBean.tCreateTime+"</td>";
								
								html += "<td class='coupon_table_common'>";
								if(userLevel != '1' && userLevel != '2'){
									html += "<a href='"+path+"/seller/showSellerDetail?tSellerId="+sellerBean.tSellerId+"' class='coupon_chakan' >查看</a>";
									html += "<a href='"+path+"/seller/showEditSeller?tSellerId="+sellerBean.tSellerId+"' class='coupon_submit' >编辑</a>";
									html += "<a href='"+path+"/shop/showShopList?tSellerId="+sellerBean.tSellerId+"&pageNum=1' class='coupon_commercial'>店铺管理</a>";
									html += "<a href='javascript:;' class='coupon_pass' onclick=SellerManageJS.showDeleteSeller('"+sellerBean.tSellerId+"')>删除</a>";
								}else{
									html += "<a href='"+path+"/shop/showShopList?tSellerId="+sellerBean.tSellerId+"&pageNum=1' class='coupon_commercial'>店铺管理</a>";
								}
								
								html += "</td>";
								html += "</tr>";
						  }
							html = head + html;
						}
						
						$("#sellerList").empty();
						$("#sellerList").append(html);
						$(".coupon_pageList_query").empty();
						if(list.length > 0){
						var pHtml = "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+page.prev+"') class=''  >上一页</a>";
						
						if(page.pageCount<6){
							for ( var i = 1; i <= page.pageCount; i++) {
								if(page.pageNum == i){
									pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
								}else{
									pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+i+"') class='' >"+i+"</a>";
								}
							}
							
						}else{
							if(page.pageNum<4){
								for ( var i = 1; i <= 4; i++) {
									if(page.pageNum == i){
										pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
									}else{
										pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+i+"') class='' >"+i+"</a>";
									}
								}
								pHtml += "<a>...</a>";
								pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
							}else if(page.pageNum > page.pageCount-3){
									pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition(1) class='' >1</a>";	 
									pHtml += "<a>...</a>";
								for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									
								}else{
									pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition(1) class='' >1</a>";
									pHtml += "<a>...</a>";
									for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
										if(page.pageNum == i){
											pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
										}else{
											pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+i+"') class='' >"+i+"</a>";
										}
										
									}
									pHtml += "<a>...</a>";
									pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
								}
							
						}
						
						 pHtml += "<a href='javascript:;' onclick=SellerManageJS.selectSellerByCondition('"+page.next+"') class='' >下一页</a>";	 
						 pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
						 pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'') >页";
						 pHtml += "<a href='javascript:;' onclick=SellerManageJS.querySellerByConditiongoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
		showDeleteSeller:function(sellerId){
			tSellerId = sellerId;
			$("#confirmDeleteSeller").removeClass("hide");
		},
		confirmDeleteSeller:function(){
			$.ajax({
				url:path+"/seller/deleteSeller",
				type : 'post',
				dataType:'text',
				data:{
					"tSellerId" : tSellerId,// 商户编号
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=="0"){
						window.location.href = path
						+ "/seller/showSellerList?pageNum=1";
					}
					else{
						showDialog1(result.msg);
					}
				},
		  		error:function(result){
		  			CommonComponent.showFailureDialog("网络繁忙");
		  	}});
	   }
}