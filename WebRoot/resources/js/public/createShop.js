var tShopState = "";//店铺状态
var tShopProvince = "";//省份代码
var tShopCity = "";//地市代码
var tShopArea = "";//地区代码
$(function(){
	tShopProvince = '北京市';
	tShopCity = "北京市市辖区";
	tShopArea = "";
    $("#tShopProvince").change(function() {
    	tShopProvince = $(this).find("option:checked").attr("value");
    	if(tShopProvince == "北京市"){
    		tShopCity = "北京市市辖区";
    	}
    	if(tShopProvince == "天津市"){
    		tShopCity = "天津市市辖区";
    	}
    	if(tShopProvince == "河北省"){
    		tShopCity = "石家庄市";
    	}
    	if(tShopProvince == "山西省"){
    		tShopCity = "太原市";
    	}
    	if(tShopProvince == "内蒙古自治区"){
    		tShopCity = "呼和浩特市";
    	}
    	if(tShopProvince == "辽宁省"){
    		tShopCity = "沈阳市";
    	}
    	if(tShopProvince == "吉林省"){
    		tShopCity = "长春市";
    	}
    	if(tShopProvince == "黑龙江省"){
    		tShopCity = "哈尔滨市";
    	}
    	if(tShopProvince == "上海市"){
    		tShopCity = "上海市市辖区";
    	}
    	if(tShopProvince == "江苏省"){
    		tShopCity = "南京市";
    	}
    	if(tShopProvince == "浙江省"){
    		tShopCity = "杭州市";
    	}
    	if(tShopProvince == "安徽省"){
    		tShopCity = "合肥市";
    	}
    	if(tShopProvince == "福建省"){
    		tShopCity = "福州市";
    	}
    	if(tShopProvince == "江西省"){
    		tShopCity = "南昌市";
    	}
    	if(tShopProvince == "山东省"){
    		tShopCity = "济南市";
    	}
    	if(tShopProvince == "河南省"){
    		tShopCity = "郑州市";
    	}
    	if(tShopProvince == "湖北省"){
    		tShopCity = "武汉市";
    	}
    	if(tShopProvince == "湖南省"){
    		tShopCity = "长沙市";
    	}
    	if(tShopProvince == "广东省"){
    		tShopCity = "广州市";
    	}
    	if(tShopProvince == "广西壮族自治区"){
    		tShopCity = "南宁市";
    	}
    	if(tShopProvince == "海南省"){
    		tShopCity = "海口市";
    	}
    	if(tShopProvince == "重庆市"){
    		tShopCity = "重庆市市辖区";
    	}
    	if(tShopProvince == "四川省"){
    		tShopCity = "成都市";
    	}
    	if(tShopProvince == "贵州省"){
    		tShopCity = "贵阳市";
    	}
    	if(tShopProvince == "云南省"){
    		tShopCity = "昆明市";
    	}
    	if(tShopProvince == "西藏自治区"){
    		tShopCity = "拉萨市";
    	}
    	if(tShopProvince == "陕西省"){
    		tShopCity = "西安市";
    	}
    	if(tShopProvince =="甘肃省"){
    		tShopCity = "兰州市";
    	}
    	if(tShopProvince == "青海省"){
    		tShopCity = "西宁市";
    	}
    	if(tShopProvince == "宁夏回族自治区"){
    		tShopCity = "银川市";
    	}
    	if(tShopProvince == "新疆维吾尔自治区"){
    		tShopCity = "乌鲁木齐市";
    	}
    	if(tShopProvince == "台湾省"){
    		tShopCity = "请选择地市";
    	}
    	if(tShopProvince == "香港特别行政区"){
    		tShopCity = "中西区";
    	}
    	if(tShopProvince == "澳门特别行政区"){
    		tShopCity = "花地玛堂区";
    	}
    	
    	
    });
    $("#tShopCity").change(function() {
    	tShopCity = $(this).find("option:checked").attr("value");
    });
    $("#tShopArea").change(function() {
    	tShopArea = $(this).find("option:checked").attr("value");
    });
});
var CreateShopJS = {

		createShop : function() {
			var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
			var tShopName = $("#tShopName").val();
			var tShopAddress = $("#tShopAddress").val();
			var tShopLongitude = $("#tShopLongitude").val();
			var tShopLatitude = $("#tShopLatitude").val();
			var tShopPhone = $("#tShopPhone").val();
			var tSellerId = $("#tSellerId").val();
			var tSellerName = $("#tSellerName").val();
			if(tShopProvince == ""){
				showDialog1("归属地不能为空!");
				return;
			}
			
			if (tShopAddress == "" || tShopAddress == null) {
				showDialog1("店铺地址不能为空!");
				return;
			}
			if (tShopName == "" || tShopName == null) {
				showDialog1("店铺名称不能为空!");
				return;
			}
			if(tShopName.length > 10){
				showDialog1("店铺名称长度不能超过20!");
				return;
			}if(regEn.test(tShopName)== true){
				showDialog1("店铺名称含有特殊字符!");
				return;
			}
			/*if(CommonJS.phoneNumberCheck(tShopPhone,"店铺电话") == false){
				return;
			}*/
		$.ajax({
			url : path + "/shop/insertShop",
			type : 'post',
			dataType : 'text',
			data : {
				"tSellerId" : tSellerId,//商户ID
				"tShopName" : tShopName,//店铺名称
				"tShopAddress" : tShopAddress,//店铺地址
				"tShopLongitude" : tShopLongitude, //店铺经度
				"tShopLatitude" : tShopLatitude, //店铺纬度
				"tShopPhone" : tShopPhone, //店铺电话
				"tShopProvince" : tShopProvince, //省代码
				"tShopCity" : tShopCity, //地市代码
				"tShopArea" : tShopArea, //地区代码
				"tShopState" : tShopState //店铺状态
			},
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == '0') {
					window.location.href = path
							+ "/shop/showShopList?tSellerId="+tSellerId+"&pageNum=1 ";
				} else {
					showDialog1(result.msg);
				}
			},
			error : function(result) {
				showDialog1("创建失败");
			}
		});
	},
	selectUseState : function(state) {
		tShopState = state;
	},
	selectProvinceCode : function(provinceCode) {
		 $("select").onchange(function() {
	          alert($(this).find("option:checked").attr("value"));
	        });
		tShopProvince = provinceCode;
	},
	selectCityCode : function(cityCode) {
		tShopCity = cityCode;
		var addddd= $("#tCityShop").val();
	},
	selectAreaCode : function(areaCode) {
		tShopArea = areaCode;
	}
	 
 }