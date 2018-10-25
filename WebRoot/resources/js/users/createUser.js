var tChannelId = "";
var userLevel = "";// 角色编码
var userState = "";//是否可用
var CreateUserJS = {

		selectUseState : function(state){
			userState = state;
		},
		// 创建用户
		xxxx: function(){
			
			$(".mask").removeClass("hide");
			$("#confirmCreateUser").removeClass("hide");
		},
	createUser : function() {
		var userCityName = $("#userCityName").html();

		var username = $("#username").val();
		//var mail = $("#mail").val();
		//var code = $("#code").val();
		var userAccount = $("#userAccount").val();
		var userPassword = $("#userPassword").val();
		var userNumber = $("#userNumber").val();
		var factory = $("#factory").val();
		var department = $("#department").val();
		 userLevel= $("#userLevel").val();
		//地市字符串转换为相应数字代号
		var citycode = CommonJS.getCityCode(userCityName);
		if($("#userState").text() == "已启用"){
			userState = "0";
		}
		
		if (userAccount == "" || userAccount == null) {
			showDialog1("账户不能为空");
			return;
		}
		// 账户空格校验
		if (/\s/.test(userAccount)) {
			showDialog1("账户不合法,请重新输入");
			return;
		}
//		console.log(/^[A-Za-z0-9]{4,10}$/.test("人民共和国"));
//		console.log(/^[\u4e00-\u9fa5]{2,4}$/.test("人民共和国"));
		
//		if(!/^[\u4e00-\u9fa5]{2,4}$/.test(userAccount)&&!/^[A-Za-z0-9]{4,10}$/.test(userAccount)){
//			showDialog1("账户由2~4位汉字或者4~10的字母数字组成,请重新输入");
//			return;
//		} 

		// 密码非空校验
		if (userPassword == "" || userPassword == null) {
			showDialog1("密码不能为空");
			return;
		}
		// 密码长度校验
		if (userPassword.length < 6) {
			showDialog1("密码必须在6-12位之间");
			return;
		}
		// 密码格式校验/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,12}/
		if (!(/^[A-Za-z0-9]{6,12}$/.test(userPassword))) {
			showDialog1("密码必须由英文大小写字母、数字组成,请重新输入");
			return;
		}
		//手机号码合法性校验
		if(!CommonJS.phoneNumberCheck(userNumber,"联系电话")){
			return;
		}
		
		//if(tChannelId=="" || tChannelId==null){
		//	showDialog1("归属渠道不能为空");
		//	return;
		//}

		if (userLevel == "" || userLevel == null) {
			showDialog1("请选择用户角色");
			return;
		}
		if (username == "" || username == null) {
			showDialog1("请填写用户姓名");
			return;
		}
//		if ( mail== "" || mail == null) {
//			showDialog1("请填写邮箱");
//			return;
//		}
//		if (code == "" || code == null) {
//			showDialog1("请填写邮箱6位数验证码");
//			return;
//		}
		if (factory == "" || factory  == null) {
			showDialog1("请填写工厂名称");
			return;
		}
		if (department == "" || department == null) {
			showDialog1("请填写部门名称");
			return;
		}
		//if (userCityName == "" || userCityName == null) {
		//	showDialog1("请选择用户所属地市");
		//	return;
		//}
		if (userState == "" || userState == null) {
			showDialog1("请选择用户是否启用");
			return;
		}
		
		

		$.ajax({
			url : path + "/userManage/CreateUser",
			type : 'post',
			dataType : 'text',
			data : {
			//	"userCityName" : userCityName,
			//	"citycode" : citycode,
				"userAccount" : userAccount,
				"userPassword" : userPassword,
				"userNumber" : userNumber,
				"userLevel" : userLevel,
				"userState" : userState,
				"username" : username,
				"factory" : factory,
				"department" : department
				//"mail" : mail,
				//"code" : code
			},
			success : function(result) {
				var result = $.parseJSON(result);
				debugger;
				if (result.resultCode == "0") {
					//xxxx();
				window.location.href = path + "/userManage/showUserManage?pageNum=1";
				} else if (result.resultCode == "1") {
					showDialog1(result.msg);
				} else {
					showDialog1("用户创建失败");
				}
			},
			error : function(result) {
				showDialog1("网络繁忙");
			}
		});
	},
	
	//显示注销用户确认弹窗
	

	//confirmCreateUser :function(){
	//	window.location.href = path + "/userManage/showUserManage?pageNum=1"
    //},
	showChannelId: function(data) {
		tChannelId=data;
	},
	// 如果选择省级操作员,省级审核员,去除地市选择,默认显示江苏省
	showAllCity : function() {
		$("#userCityName").html("");
		$("#oneCity").hide();
		$("#allCity").show();
	},
	//必先选角色才能后选所属地市，两者是关联的
	// 如果选择地市操作员,地市审核员,插入地市选择列表
	showOneCity : function() {
		$("#userCityName").html("");
		$("#allCity").hide();
		$("#oneCity").show();
	},
	showCity : function(data) {
		userLevel = data;
		$("#userLevel").attr("value",userLevel);
		if (data == "0") {
			$("#userCityName").html("");
			$("#allCity").hide();
			$("#oneCity").hide();
			$("#sysCity").show();
		} 
		else if(data == "2"||data == "4"){
			$("#userCityName").html("");
			$("#allCity").hide();
			$("#sysCity").hide();
			$("#oneCity").show();
		}
		else{
			$("#userCityName").html("");
			$("#oneCity").hide();
			$("#sysCity").hide();
			$("#allCity").show();
		}
	},
	showCity111 : function(data) {
		var b= data;
		$("#factory").attr("value",b);
		if (data == "0") {
			$("#userCityName").html("");
			$("#allCity").hide();
			$("#oneCity").hide();
			$("#sysCity").show();
		} 
		else if(data == "2"||data == "4"){
			$("#userCityName").html("");
			$("#allCity").hide();
			$("#sysCity").hide();
			$("#oneCity").show();
		}
		else{
			$("#userCityName").html("");
			$("#oneCity").hide();
			$("#sysCity").hide();
			$("#allCity").show();
		}
	},
	
	
//	mail : function() {
//		//var code=  $("#code").val();//邮箱验证码
//	    var mail = $("#mail").val();//邮箱地址符不符合规范
//	    //首先验证邮箱
//	    if( /^@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(mail)){//邮箱符合规范
//	    	
//	    
//		$.ajax({
//			url : path + "/mailManage/mail_yz",
//			type : 'post',
//			dataType : 'text',
//			data : {
//			//	"userCityName" : userCityName,
//			//	"citycode" : citycode,
//				"mail" : mail
//		
//			},
//			success : function(result) {
//				var result = $.parseJSON(result);
//				if (result.resultCode == "0") {
//					//window.location.href = path + "/userManage/showUserManage?pageNum=1";
//					showDialog1("获取验证码成功,请注意查收");
//				} else if (result.resultCode == "1") {
//					showDialog1(result.msg);
//				} else {
//					showDialog1("用户创建失败");
//				}
//			},
//			error : function(result) {
//				showDialog1("网络繁忙");
//			}
//		});
//	    }else{
//	    	showDialog1("邮箱不符合规范,请重新输入，再获取验证码！！");
//			return;
//	    }
//	},
	

	}
