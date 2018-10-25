var tChannelId = "";
var userLevel = "";// 角色编码
var userState = "";//是否可用
var CreateUserJS = {

		selectUseState : function(state){
			userState = state;
		},
		// 创建用户
	createUser : function() {
		var roleName = $("#roleName").val();
		var roleNumber = $("#roleNumber").val();
		
		//地市字符串转换为相应数字代号
		/*
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
		if(!/^[\u4e00-\u9fa5]{2,4}$/.test(userAccount)&&!/^[A-Za-z0-9]{4,10}$/.test(userAccount)){
			showDialog1("账户由2~4位汉字或者4~10的字母数字组成,请重新输入");
			return;
		}

		// 密码非空校验
		if (userPassword == "" || userPassword == null) {
			showDialog1("密码不能为空");
			return;
		}
		// 密码长度校验
		if (userPassword.length < 8) {
			showDialog1("密码必须在8-12位之间");
			return;
		}
		// 密码格式校验
		if (!(/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,12}/.test(userPassword))) {
			showDialog1("密码必须由英文大小写字母、数字和特殊符号四种字符组成,请重新输入");
			return;
		}
		//手机号码合法性校验
		if(!CommonJS.phoneNumberCheck(userNumber,"联系电话")){
			return;
		}
		
		

		if (userLevel == "" || userLevel == null) {
			showDialog1("请选择用户角色");
			return;
		}
		
		if (userCityName == "" || userCityName == null) {
			showDialog1("请选择用户所属地市");
			return;
		}
		if (userState == "" || userState == null) {
			showDialog1("请选择用户是否启用");
			return;
		}*/
		if(roleName=="" || roleName==null){
			showDialog1("角色名称不能为空");
			return;
		}
		
		if(roleNumber=="" || roleNumber==null){
			showDialog1("角色编号不能为空");
			return;
		}
		$.ajax({
			url : path + "/RoleManage/CreateRole",
			type : 'post',
			dataType : 'text',
			data : {
				"roleName" : roleName,
				"roleNumber" : roleNumber
				
			},
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/RoleManage/showRoleManage?pageNum=1";
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
	}

	}
