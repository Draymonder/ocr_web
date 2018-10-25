var tChannelId = "";
var userLevel = "";
var userState = "";
var EditUserJS = {
	//编辑用户
	editUser : function(pageNum){
		var userCityName = $("#userCityName").html();
		var username = $("#username").val();//用户名
		var mail = $("#mail").val();//邮箱
		var userAccount = $("#userAccount").html();//密码
		var userPassword = $("#userPassword").val();//账号
		var userNumber = $("#userNumber").val();//电话
		var factory = $("#factory").attr("value");//工厂id
		var department = $("#department").val();//部门
		var code = $("#code").val();//邮箱验证码
		var rolename= $("#userLevel").attr("value");//角色ID 
		//地市字符串转换为相应数字代号
		var citycode = CommonJS.getCityCode(userCityName);
		
		if(userPassword=="如不填,密码不修改"){
			userPassword = "";
		}
		else{
			//密码长度校验
			if(userPassword.length < 6){
				showDialog1("密码必须在6-12位之间");
				return;
			}
			// 密码格式校验
			if (!(/^[A-Za-z0-9]{6,12}$/.test(userPassword))) {
				showDialog1("密码必须由英文大小写字母、数字组成,请重新输入");
				return;
			}
		}
		//手机号码合法性校验
		if(!CommonJS.phoneNumberCheck(userNumber,"联系电话")){
			return;
		}
		

		if (rolename == "" || rolename == null) {
			showDialog1("请选择用户角色");
			return;
		}
		if (username == "" || username == null) {
			showDialog1("请填写用户姓名");
			return;
		}
		//if ( mail== "" || mail == null) {
		//	showDialog1("请填写邮箱");
		//	return;
		//}
		if (factory == "" || factory  == null) {
			showDialog1("请填写工厂名称");
			return;
		}
		if (department == "" || department == null) {
			showDialog1("请填写部门名称");
			return;
		}
		/*
		if (code == "" || code == null) {
			showDialog1("请填写邮箱6位数验证码");
			return;
		}*/
		$.ajax({
			url:path+"/userManage/editUser",
			type : 'post',
			dataType:'text',
			data:{
        		"userAccount" : userAccount,
				"userPassword" : userPassword,
				"userNumber" : userNumber,
				"rolename" : rolename,
				"userState" : userState,
				//"tChannelId" : tChannelId,
				"username" : username,
				"mail" : mail,
				"factory" : factory,
				"department" : department,
				"code" : code
				
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/userManage/showUserManage?pageNum="+pageNum;
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  		}});
	},
	showChannelId: function(data) {
		tChannelId=data;
	},
	showOneCity : function() {
		$("#userCityName").html("");
		$("#allCity").hide();
		$("#oneCity").show();
	},
	//如果选择地市操作员,地市审核员,插入地市选择列表.如果选择省级操作员,省级审核员,去除地市选择,默认显示江苏省
	//必先选角色才能后选所属地市，两者是关联的
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
	mail : function() {
		//var code=  $("#code").val();//邮箱验证码
	    var mail = $("#mail").val();//邮箱地址符不符合规范
	    //首先验证邮箱

	    //if(/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/.test(mail)){//邮箱符合规范


	    //if(/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/.test(mail)){//邮箱符合规范


	    
		$.ajax({
			url : path + "/mailManage/mail_yz",
			type : 'post',
			dataType : 'text',
			data : {
			//	"userCityName" : userCityName,
			//	"citycode" : citycode,
				"mail" : mail
		
			},
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					//window.location.href = path + "/userManage/showUserManage?pageNum=1";
					showDialog1("获取验证码成功,请注意查收");
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

	    //}else{
	    //	showDialog1("邮箱不符合规范,请重新输入，再获取验证码！！");
		//	return;
	   //  }

	    //}else{
	    //	showDialog1("邮箱不符合规范,请重新输入，再获取验证码！！");
		//	return;
	    //}

	},
	selectUseState : function(state){
		userState = state ;
	}
	}	
	
	
		

