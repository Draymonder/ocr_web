﻿
var PermissionUserJS = {

	// 为用户赋权
	permissionUser : function(pageNum) {
		var id_array = new Array(); 
		$('div.show').each(function() {
		    id_array.push($(this).attr('id'));
		});
		var idstr = id_array.join(',');
		var fAccountNumber = $("#fAccountNumber").html();
		if (idstr == "" || idstr == null) {
			showDialog1("用户权限不能为空");
			return;
		}
		$.ajax({
			url : path + "/userManage/rePermission",
			type : 'post',
			dataType : 'text',
			data : {
				"fAccountNumber" : fAccountNumber,
				"idstr" : idstr
			},
			success : function(result) {
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/userManage/showUserManage?pageNum="+pageNum;
				} else if (result.resultCode == "1") {
					showDialog1(result.msg);
				} 
			},
			error : function(result) {
				showDialog1("网络繁忙");
			}
		});
	},

	//移除选中项目
	cancelPermission : function(data) {	
		if($("#"+data).hasClass("show")){
			$("#"+data).removeClass("show");	
		}
		else{
			$("#"+data).addClass("show");
		}		
	}
 }
