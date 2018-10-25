
$(function () {
	/*
	var covertype=$("#title_div").html();//类型
	alert(covertype);
	if(covertype=="0"){
		$("#title_div").html("强转");
		$("#zhgz1").css("display","flex");
		$("#zhgz2").css("display","none");
	}else{
		$("#title_div").html("提示");
		$("#zhgz1").css("display","none");
		$("#zhgz2").css("display","flex");
	}
	
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
	
	*/
	
})



var EditJS = {

		
		// 创建用户
		EditRule : function(pageNum) {

			var id=$("#xjgz_title").attr("title");
			var list="";
			//console.log(123);
			$(".xjgz_table_tr input[type='text']").each(function(i){
				var jg=$(this).attr("id")+","+$(this).val()+";";
				list+=jg;
			})
			//alert(list);
			if (list == "" || list == null) {
				//showDialog1("账户不能为空");
				alert("识别字段不能全部为空");
				return;
			}
			//alert(path);
			$.ajax({
				url : path + "/CommonTemplateSearchManage/Edit",
				type : 'post',
				dataType : 'text',
				data : {
					
					 "list": list,//数据字符串拼接
					 "id":id //data表数据id
					 
				},
				success : function(result) {
					var result = $.parseJSON(result);
					if (result.resultCode == "0") {
						window.location.href = path + "/CommonTemplateSearchManage/showCommonTemplateSearchManage?pageNum="+pageNum;
					} else if (result.resultCode == "1") {
						//showDialog1(result.msg);
						alert(result.msg);
					} else {
						//showDialog1("用户创建失败");
						alert("用户创建失败");
					}
				},
				error : function(result) {
					alert("网络繁忙");
					//showDialog1("网络繁忙");
				}
			});
	},
	
	

}
