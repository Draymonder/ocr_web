var tChannelId="";
var EditSellerJS={
		editSeller:function(){
			var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
			var tSellerId = $("#tSellerId").val();
			var tSellerName = $("#tSellerName").val();
			var tSellerAddress = $("#tSellerAddress").val();
			var tSellerPhone = $("#tSellerPhone").val();
			/*if(CommonJS.phoneNumberCheck(tSellerPhone,"商家电话") == false){
				return;
			}*/
			if (tSellerName == "" || tSellerName == null) {
				showDialog1("商户名称不能为空!");
				return;
			}if(tSellerName.length > 10){
				showDialog1("商户名称长度不能超过20!");
				return;
			}if(regEn.test(tSellerName)== true){
				showDialog1("商户名称不能还有特殊字符!");
				return;
			}
			$.ajax({
				url:path+"/seller/editSeller",
				type : 'post',
				dataType:'text',
				data:{
					"tSellerId" : tSellerId,//商户ID
					"tSellerName" : tSellerName,//商户名称
					"tSellerAddress" : tSellerAddress,//商户地址
					"tSellerPhone" : tSellerPhone, //二级商户电话
					"tChannelId" : tChannelId//商户渠道
				},
				success:function(result){
					var result = $.parseJSON(result);
					if(result.resultCode=='0')
					{
						window.location.href = path
						+ "/seller/showSellerList?pageNum=1";
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
		selectChannel : function(channelId) {
			tChannelId = channelId;
		}
 }