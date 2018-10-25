var tChannelId = "";
var userLevel = "";
var userState = "";
var EditUserJS = {
	
	//编辑角色
	editUser : function(pageNum){
		var authId = $("#authId").html();
		var authName = $("#authName").val();
		//var roleNumber = $("#roleNumber").val();
		
		
		
	//	if(userPassword=="如不填,密码不修改"){
		//	userPassword = "";
		//}
		//else{
			//密码长度校验
		//	if(userPassword.length < 8){
		//		showDialog1("密码必须在8-12位之间");
		//		return;
		//	}
			// 密码格式校验
		//	if (!(/(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,12}/.test(userPassword))) {
		//		showDialog1("密码必须由英文大小写字母、数字和特殊符号四种字符组成,请重新输入");
		//		return;
			//}
		//}
		//手机号码合法性校验
		//if(!CommonJS.phoneNumberCheck(userNumber,"联系电话")){
		//	return;
		//}
		//if(tChannelId==""){
		//	tChannelId=$("#tChannelId").val();
		//}
	 // if(roleNumber=="" || roleNumber==null){
		//	showDialog1("角色编号不能为空");
		//	return;
	  //}
	  if(authName=="" || authName==null){
		showDialog1("权限不名称能为空");
		return;
      }//现在编辑用户时限制用户的角色与地市不可修改，渠道可以修改,如果用户的渠道变了,它创建(如果有权限的话)的合作、活动券对应的渠道都应该改变
//	if(userCityName=="" || userCityName==null){
//		showDialog1("请选择用户所属地市");
//		return;
//	}
		
		$.ajax({
			url:path+"/authorityManage/editAuthority",
			type : 'post',
			dataType:'text',
			data:{
//				"userCityName" : userCityName,
//				"citycode" : citycode,
				"authId" : authId,
				"authName" :authName
				
			
//				
				
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/authorityManage/showAuthorityManage?pageNum="+pageNum;
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
	
	
		

