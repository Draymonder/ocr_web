
$(function () {
	
	$("#title_div").html("强转");//默认赋值
	//下拉选择字段
	$(".newgz_rg_select").toggle(
		function () {
			$(this).find(".newgz_rg_select_list").show();
		},function () {
			$(this).find(".newgz_rg_select_list").hide();
		}
	)
	$(".newgz_rg_select_li").click(function () {
		//console.log($(this).attr("data-id"));
		var dataname = $(this).attr("data-name");
		//console.log(dataname);
		$(this).parent().parent().find("#title_div").html(dataname);
	})
	$(".newgz_rg_select_li").click(function () {
		
		var dataname = $(this).attr("data-name");
		if(dataname=='强转'){
			$("#zhgz1").css("display","flex");
			$("#zhgz2").css("display","none");
		}else{
			$("#zhgz1").css("display","none");
			$("#zhgz2").css("display","flex");
		}
		
	})
	
	
	
})



var CreateUserJS = {

		
		// 创建用户
		CreateRule : function() {
			var ruleName = $("#ruleName").val();
			var ruleNumber = $("#ruleNumber").val();
			var coverType =$("#title_div").html();
			var Template_Id=$("#Template_Id").html();//模板id
			if(coverType=="强转"){
				coverType="0";
			}else{
				coverType="1";
			}
			var flagA =$("#flagA").val();
			var flagB =$("#flagB").val();
			var flagC =$("#flagC").val();
			debugger;
			if (ruleName == "" || ruleName == null) {
				//showDialog1("账户不能为空");
				showDialog1("规则名称不能为空");
				return;
			}
			if (ruleNumber == "" || ruleNumber == null) {
				//showDialog1("账户不能为空");
				showDialog1("规则名称不能为空");
				return;
			}
			if (coverType == "" || coverType == null) {
				//showDialog1("账户不能为空");
				showDialog1("类型不能为空");
				return;
			}
			if(coverType=="0"){
				if (flagA == "" || flagA == null) {
					//showDialog1("账户不能为空");
					showDialog1("要转换的字符不能为空");
					return;
				}
				if (flagB == "" || flagB == null) {
					//showDialog1("账户不能为空");
					showDialog1("转换后的字符不能为空");
					return;
				}
			}else{//提示
				if (flagC == "" || flagC == null) {
					//showDialog1("账户不能为空");
					showDialog1("要提示的字符不能为空");
					return;
				}
				flagA=flagC;//把值赋予A
			}
			
			var list="";
			//console.log(123);
			$(".newgz_rg_zdlist input[type='checkbox']").each(function(i){
				i++;
				//console.log(i);
				var jg=$("#a"+i).is(":checked");
				if(jg){
					list+="a"+i+","
				}
				
			})
			if (list == "" || list == null) {
				//showDialog1("账户不能为空");
				showDialog1("规则字段不能为空");
				return;
			}
			
			$.ajax({
				url : path + "/CommonRuleManage/CreateRule",
				type : 'post',
				dataType : 'text',
				data : {
					 "ruleName": ruleName,
					 "ruleNumber": ruleNumber,
					 "coverType": coverType,
					 "flagA": flagA ,
					 "flagB": flagB,
					 "list": list,
					 "Template_Id":Template_Id
				},
				success : function(result) {
					var result = $.parseJSON(result);
					if (result.resultCode == "0") {
						window.location.href = path + "/CommonRuleManage/showCommonRuleManage?pageNum=1";
					} else if (result.resultCode == "1") {
						//showDialog1(result.msg);
						showDialog1(result.msg);
					} else {
						//showDialog1("用户创建失败");
						showDialog1("用户创建失败");
					}
				},
				error : function(result) {
					showDialog1("网络繁忙");
					//showDialog1("网络繁忙");
				}
			});

	},
	
	

}
