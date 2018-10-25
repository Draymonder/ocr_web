var tChannelId = "";
var userLevel = "";
var userState = "";
var editTemplateJS = {
	//编辑用户
		editTemplate : function(pageNum){
		var oldtemplatename = $("#templatename").attr("title");//旧模板名
		var templatename = $("#templatename").val();//新模板名
		var data = $("#data").html();//日期
		var remark = $("#remark").val();//备注

		if (templatename == "" || templatename == null) {
			showDialog1("请填写模板名称");
			return;
		}
		
		
		$.ajax({
			url:path+"/templateManage/editTemplate",
			type : 'post',
			dataType:'text',
			data:{
				"oldtemplatename" : oldtemplatename,
        		"templatename" : templatename,
				"data" : data,
				"remark" : remark
				
				
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/templateManage/showTemplateManage?pageNum="+pageNum;
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


	selectUseState : function(state){
		userState = state ;
	}
	}	
	
	
		

