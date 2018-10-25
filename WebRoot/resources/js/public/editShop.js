var tShopState="";
var EditShopJS={
		editShop:function(tSellerId){
			var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
			var tShopId = $("#tShopId").val();
			var tShopName = $("#tShopName").val();
			var tShopAddress = $("#tShopAddress").val();
			var tShopLongitude = $("#tShopLongitude").val();
			var tShopLatitude = $("#tShopLatitude").val();
			var tShopPhone = $("#tShopPhone").val();
			/*if(CommonJS.phoneNumberCheck(tShopPhone,"店铺电话") == false){
				return;
			}*/
			if (tShopName == "" || tShopName == null) {
				showDialog1("店铺名称不能为空!");
				return;
			}
			if (tShopAddress == "" || tShopAddress == null) {
				showDialog1("店铺地址不能为空!");
				return;
			}
			if(tShopName.length > 10){
				showDialog1("店铺名称长度不能超过20!");
				return;
			}if(regEn.test(tShopName)== true){
				showDialog1("店铺名称不能还有特殊字符!");
				return;
			}
			$.ajax({
				url:path+"/shop/editShop",
				type : 'post',
				dataType:'text',
				data:{
					"tShopId" : tShopId,//店铺ID
					"tShopName" : tShopName,//店铺名称            
					"tShopAddress" : tShopAddress,//店铺地址 
					"tShopLongitude" : tShopLongitude,//店铺经度
					"tShopLatitude" : tShopLatitude, //店铺纬度
					"tShopPhone" : tShopPhone,//联系电话
					"tShopState" : tShopState//店铺状态
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=='0')
					{
						window.location.href = path
						+ "/shop/showShopList?tSellerId="+tSellerId+"&pageNum=1 ";
					}
					else
					{
						showDialog1(result.msg);	
					}
				},
				error:function(result){
					showDialog1("网络繁忙");
				}});
		},
		selectUseState : function(state) {
			tShopState = state;
		}
 }