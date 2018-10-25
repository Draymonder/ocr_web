var name;

var TemplateManageJS = {
    //跳转到指定页码

    goToPage : function(pageCount){
        //alert("ss");
        var pageNum = $("#go-to").val();
        if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount)){
            return;
        }
        window.location.href = path + "/CommonTemplateManage/showCommonTemplateManage?pageNum="+pageNum;
    },
    
    queryUserByConditiongoToPage : function(pageCount){
		var pageNum = $("#go-to").val();
		if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
			return;
		}
		TemplateManageJS.queryTemplateByCondition(pageNum);
	},
    
    queryTemplateByCondition : function(pageNum){
        var condition = $.trim($("#templateName").val());
        var begindate=$("#txtBeginDate").val();//时间
        var enddate=$("#txtBeginDate1").val();//时间
        //alert(condition);
        //校验查询条件是否为空
        if(condition=='请输入模板名称'&&begindate==''&&enddate=='') {
            window.location.href = path + "/CommonTemplateManage/showCommonTemplateManage?pageNum=1";
            return;
        }

        //密码空格校验
        if(/\s/.test(condition)){
            alert("查询条件不合法,请重新输入");
            return;
        }
        $.ajax({
            url:path+"/CommonTemplateManage/queryTemplateByCondition",
            type : 'post',
            dataType:'text',
            data:{
                "condition" : condition,//查询条件
                "begindate":begindate,//日期
                "enddate":enddate,//日期
                "pageNum" : pageNum
            },
            success:function(result){
               // alert("ss");
                var result = $.parseJSON(result);
                debugger;
                if(result.resultCode == "0") {
                    var page = result.page;
                    var list = page.list;
                    var head = "";
                    head += "<tr class='tr_h'>";
                    head += "<th>模板名称</th>";
                    head += "<th>所属工厂</th>";
                    head += "<th>创建人</th>";
                    head += "<th>创建日期</th>";
                    head += "<th>备注</th>";
                    head += "<th>操作</th>";
                    head += "</tr>";

                    var html = "";
                    //根据返回结果拼接 表单白名单信息
                    if(list.length == 0){
                        html = head + '<tr><td colspan="11" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
                    }else{
                        for ( var i = 0; i < list.length; i++) {
                            var TemplateBean = list[i];
                            html += "<tr class='tr_d'>";
                            html += "<td>"+TemplateBean.template_name+"</td>";
                            html += "<td>"+TemplateBean.factoryName+"</td>";
                            html += "<td>"+TemplateBean.account_number+"</td>";
                            html += "<td>"+TemplateBean.simpledate+"</td>";
                            html += "<td>"+TemplateBean.remark+"</td>";
                            html += "<td class='coupon_table_common'>";
                            html += "<span class='coupon_submit' ><a target='view_window' style='color:#FFFFFF' href='"+path+"/CommonTemplateManage/showEditTemplate?pageNum="+page.pageNum+"&templatename="+TemplateBean.template_name+"' >编辑</a></span>";
                            html += " <span class='coupon_pass'><a style='color:#FFFFFF' href='javascript:;' onclick=TemplateManageJS.showRemoveUser('"+TemplateBean.template_name+"')>删除</a></span>";
                            html += " <span class='coupon_chakan'><a target='view_window' style='color:#FFFFFF' href='"+path+"/CommonTemplateManage/recreateTemplate?fileName="+TemplateBean.filename+"&templateName="+TemplateBean.template_name+"' >详情</a></span>";
                            html += "</td>";
                            html += "</tr>";

                        }
                        html = head + html;

                    }

                    $("#templateList").empty();
                    $("#templateList").append(html);
                    $(".coupon_pageList_query").empty();
                   
                    if(list.length > 0){
                        var pHtml = "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+page.prev+"') class=''  >上一页</a>";

                        if(page.pageCount<6){
                            for ( var i = 1; i <= page.pageCount; i++) {
                                if(page.pageNum == i){
                                    pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                }else{
                                    pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+i+"') class='' >"+i+"</a>";
                                }
                            }

                        }else{
                            if(page.pageNum<4){
                                for ( var i = 1; i <= 4; i++) {
                                    if(page.pageNum == i){
                                        pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                    }else{
                                        pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+i+"') class='' >"+i+"</a>";
                                    }
                                }
                                pHtml += "<a>...</a>";
                                pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
                            }else if(page.pageNum > page.pageCount-3){
                                pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition(1) class='' >1</a>";
                                pHtml += "<a>...</a>";
                                for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
                                    if(page.pageNum == i){
                                        pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                    }else{
                                        pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+i+"') class='' >"+i+"</a>";
                                    }

                                }

                            }else{
                                pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition(1) class='' >1</a>";
                                pHtml += "<a>...</a>";
                                for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
                                    if(page.pageNum == i){
                                        pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                    }else{
                                        pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+i+"') class='' >"+i+"</a>";
                                    }

                                }
                                pHtml += "<a>...</a>";
                                pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
                            }

                        }

                        pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryTemplateByCondition('"+page.next+"') class='' >下一页</a>";
                        pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
                        pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
                        pHtml += "<a href='javascript:;' onclick=TemplateManageJS.queryUserByConditiongoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
                        $(".coupon_pageList_query").append(pHtml);
                    }
                }
                else{
                    showDialog1(result.msg);
                }
            },
            error : function() {
                alert('上传出错');
            }
        })
    },
  //显示注销模板确认弹窗
	showRemoveUser : function(uniqueUserAccount){
		 name = uniqueUserAccount;
		$(".mask").removeClass("hide");
		$("#confirmRemoveUser").removeClass("hide");
	},
	
	//确认模板用户
	confirmRemoveUser : function(){
		$.ajax({
			url:path+"/CommonTemplateManage/delTemplate",
			type : 'post',
			dataType:'text',
			data:{
				"templatename" : name// 模板name
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/CommonTemplateManage/showCommonTemplateManage?pageNum=1";
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},
	 //显示提交模板确认弹窗
	Submit: function(uniqueUserAccount){
		 name = uniqueUserAccount;
		$(".mask").removeClass("hide");
		$("#confirmSubmit").removeClass("hide");
	},
	//确认提交用户
	confirmSubmit : function(){
		//alert("111");
		debugger;
		var templateName=$("#templateName").val();
		var result=$("#result1").html();
		//alert(templateName);
		if(templateName==""){
			showDialog1("提交前模板名称不能为空！！！");
			return;
		}else{
			window.location.href = path + "/CommonTemplateManage/showCommonTemplateManage?pageNum=1";
//		$.ajax({
//			url:path+"/CommonTemplateManage/SubmitTemplate",
//			type : 'post',
//			dataType:'text',
//			data:{
//				"templateName" : templateName,// 模板name
//				"result":result
//			},
//			success:function(result){
//				var result = $.parseJSON(result);
//				if (result.resultCode == "0") {
//					window.location.href = path + "/CommonTemplateManage/showCommonTemplateManage?pageNum=1";
//				} else {
//					showDialog1(result.msg);
//				}
//			},
//	  		error:function(result){
//	  			showDialog1("网络繁忙");
//	  	}});
		}
	},
	
	countBean: function(){
		//alert("111");
		var templateName=$("#templateName").val();
		
		if(templateName==""){
			showDialog1("模板名称不能为空！！！");
			return;
		}else{
	
		$.ajax({
			url:path+"/CommonTemplateManage/CountBean",
			type : 'post',
			dataType:'text',
			data:{
				"templateName" : templateName// 模板name
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					//window.location.href = path + "/CommonTemplateManage/showCommonTemplateManage?pageNum=1";
				} else {
					
					$("#templateName").val("");
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
		}
	},
}