var tChannelId = "";
var CreateSellerJS = {

		createSeller : function() {
		var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
		var tSellerName = $("#tSellerName").val();
		var tSellerAddress = $("#tSellerAddress").val();
		var tSellerPhone = $("#tSellerPhone").val();
		
		if (tSellerName == "" || tSellerName == null) {
			showDialog1("商户名称不能为空!");
			return;
		}if(tSellerName.length > 10){
			showDialog1("商户名称长度不能超过20!");
			return;
		}if(regEn.test(tSellerName)== true){
			showDialog1("商户名称含有特殊字符!");
			return;
		}
		
		if(tChannelId == "" || tChannelId == null){
			showDialog1("归属渠道不能为空!");
			return;
		}
		/*if(CommonJS.phoneNumberCheck(tSellerPhone,"商家电话") == false){
			return;
		}*/
		
		$.ajax({
			url : path + "/seller/insertSeller",
			type : 'post',
			dataType : 'text',
			data : {
				"tSellerName" : tSellerName,//商户名称
				"tSellerAddress" : tSellerAddress,//商户地址
				"tSellerPhone" : tSellerPhone, //二级商户电话
				"tChannelId" : tChannelId//商户渠道
			},
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == '0') {
					window.location.href = path
							+ "/seller/showSellerList?pageNum=1";
				} else {
					showDialog1(result.msg);
				}
			},
			error : function(result) {
				showDialog1("创建失败");
			}
		});
	},
	selectChannel : function(channelId) {
		tChannelId = channelId;
	}
 }