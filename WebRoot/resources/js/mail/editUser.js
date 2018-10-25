var tChannelId = "";
var userLevel = "";
var userState = "";
var EditUserJS = {
	
	//编辑角色
	editUser : function(pageNum){
		
		//var mailid = $("#mailId").html();
		var mailaccount = $("#mailaccount").val();
		var mailpassword = $("#mailpassword").val();
		var mailsmtphost = $("#mailsmtphost").val();
		var mailsmtpport = $("#mailsmtpport").val();
	
		
		
		
	
		if(mailaccount=="" ||mailaccount==null){
			showDialog1("服务器邮箱账号不能为空");
			return;
		}
		if(mailpassword=="" || mailpassword==null){
			showDialog1("服务器邮箱密码不能为空");
			return;
		}
		if(mailsmtphost=="" || mailsmtphost==null){
			showDialog1("SMTP服务器地址不能为空");
			return;
		}
		
	  //现在编辑用户时限制用户的角色与地市不可修改，渠道可以修改,如果用户的渠道变了,它创建(如果有权限的话)的合作、活动券对应的渠道都应该改变
//	if(userCityName=="" || userCityName==null){
//		showDialog1("请选择用户所属地市");
//		return;
//	}
		
		$.ajax({
			url:path+"/mailManage/edit_mail",
			type : 'post',
			dataType:'text',
			data:{

				//"mailid" : mailid,
				"mailaccount" : mailaccount,
				"mailpassword" : mailpassword,
				"mailsmtphost" : mailsmtphost,
				"mailsmtpport" : mailsmtpport
				

		
				
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/login/main?type=8";
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
	//如果选择地市操作员,地市审核员,插入地市选择列表.如果选择省级操作员,省级审核员,去除地市选择,默认显示江苏省
	//必先选角色才能后选所属地市，两者是关联的
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
	},
	selectUseState : function(state){
		userState = state ;
	}
	}	
	
	
		

