var name;
var TaskManageJS = {
    //跳转到指定页码

    goToPage : function(pageCount){
        //alert("ss");
        var pageNum = $("#go-to").val();
        if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount)){
            return;
        }
        window.location.href = path + "/recognizeManage/recognitionTask?pageNum="+pageNum;
    },
    
    queryTaskByConditiongoToPage : function(pageCount){
		var pageNum = $("#go-to").val();
		if(pageNum < 1 || parseInt(pageNum) > parseInt(pageCount) ){
			return;
		}
		TaskManageJS.queryTaskByCondition(pageNum);
	},
    
    
    
    
    queryTaskByCondition : function(pageNum){
        var condition = $.trim($("#task_number").val());//任务编号
        var begindate=$("#txtBeginDate").val();//时间
        var enddate=$("#txtBeginDate1").val();//时间
        //alert(condition);
        //校验查询条件是否为空
        if(condition=='请输入工厂名称'&&begindate==''&&enddate=='') {
            window.location.href = path + "/recognizeManage/recognitionTask?pageNum=1";
            return;
        }

       
        $.ajax({
            url:path+"/recognizeManage/queryTaskListByCondition",
            type : 'post',
            dataType:'text',
            data:{
                "condition" : condition,//查询条件
                "begindate":begindate,//日期
                "enddate":enddate,//日期
                "pageNum" : pageNum
            },
            success:function(result){
                //alert("ss");
                var result = $.parseJSON(result);
                debugger;
                if(result.resultCode == "0") {
                    var page = result.page;
                    var list = page.list;
                    var head = "";
                    head += "<tr class='tr_h'>";
                    head += "<th>任务编号</th>";
                    head += "<th>创建人</th>";
                    head += "<th>工厂</th>";
                    head += "<th>创建时间</th>";
                    head += "<th>结束时间</th>";
                    head += "<th>状态</th>";
                    //head += "<th>操作</th>";
                    head += "</tr>";

                    var html = "";
                    //根据返回结果拼接 表单白名单信息
                    if(list.length == 0){
                        html = head + '<tr><td colspan="11" class="coupon_table_common">未查询到符合条件的记录</td></tr>';
                    }else{
                        for ( var i = 0; i < list.length; i++) {
                            var TaskBean = list[i];
                            html += "<tr class='tr_d'>";
                            html += "<td>"+TaskBean.id+"</td>";
                            html += "<td>"+TaskBean.creator+"</td>";
                            html += "<td>"+TaskBean.factory_name+"</td>";
                            html += "<td>"+TaskBean.simpleTime+"</td>";
                            if(TaskBean.stringFinishTime==null){
                                html += "<td>"+""+"</td>";
                            }else{
                                html += "<td>"+TaskBean.stringFinishTime+"</td>";
                            }
                            if(TaskBean.status=='0'){
                            	html += "<td style='color:red'>未完成</td>";
                            }else{
                            	html += "<td style='color:green'>已完成</td>";
                            }
                            
                            //html += "<td>"+TemplateBean.simpledate+"</td>";
                            //html += "<td>"+TemplateBean.remark+"</td>";
                            //html += "<td class='coupon_table_common'>";
                            //html += "<a href='"+path+"/templateManage/showEditTemplate?templatename="+TemplateBean.template_name+"&pageNum="+page.pageNum+"' class='edit' >编辑</a>";
                           // html += "<a href='javascript:;'  onclick=TaskManageJS.showRemoveUser('"+TemplateBean.template_name+"') class='del'>删除</a>";
                           // html += "</td>";
                            html += "</tr>";
                        }
                        html = head + html;

                    }

                    $("#templateList").empty();
                    $("#templateList").append(html);
                    $(".coupon_pageList_query").empty();
                   
                    if(list.length > 0){
                        var pHtml = "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+page.prev+"') class=''  >上一页</a>";

                        if(page.pageCount<6){
                            for ( var i = 1; i <= page.pageCount; i++) {
                                if(page.pageNum == i){
                                    pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                }else{
                                    pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+i+"') class='' >"+i+"</a>";
                                }
                            }

                        }else{
                            if(page.pageNum<4){
                                for ( var i = 1; i <= 4; i++) {
                                    if(page.pageNum == i){
                                        pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                    }else{
                                        pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+i+"') class='' >"+i+"</a>";
                                    }
                                }
                                pHtml += "<a>...</a>";
                                pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
                            }else if(page.pageNum > page.pageCount-3){
                                pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition(1) class='' >1</a>";
                                pHtml += "<a>...</a>";
                                for ( var i = page.pageCount-3; i <= page.pageCount; i++) {
                                    if(page.pageNum == i){
                                        pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                    }else{
                                        pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+i+"') class='' >"+i+"</a>";
                                    }

                                }

                            }else{
                                pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition(1) class='' >1</a>";
                                pHtml += "<a>...</a>";
                                for ( var i = page.pageNum-1; i <= page.pageNum+1; i++) {
                                    if(page.pageNum == i){
                                        pHtml += "<a href='javascript:;' class='current'>"+i+"</a>";
                                    }else{
                                        pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+i+"') class='' >"+i+"</a>";
                                    }

                                }
                                pHtml += "<a>...</a>";
                                pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+page.pageCount+"') class='' >"+page.pageCount+"</a>";
                            }

                        }

                        pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByCondition('"+page.next+"') class='' >下一页</a>";
                        pHtml += "<span class='total-num'>共"+page.pageCount+"页</span>";
                        pHtml += "跳转第<input type='text' id='go-to' value='"+page.pageNum+"' onkeyup=this.value=this.value.replace(/[^\\d]/g,'') onblur=this.value=this.value.replace(/[^\\d]/g,'')>页";
                        pHtml += "<a href='javascript:;' onclick=TaskManageJS.queryTaskByConditiongoToPage('"+page.pageCount+"') class='certain-btn'>确 定</a>";
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
			url:path+"/templateManage/delTemplate",
			type : 'post',
			dataType:'text',
			data:{
				"templatename" : name// 模板name
			},
			success:function(result){
				var result = $.parseJSON(result);
				if (result.resultCode == "0") {
					window.location.href = path + "/recognizeManage/recognitionTask?pageNum=1";
				} else {
					showDialog1(result.msg);
				}
			},
	  		error:function(result){
	  			showDialog1("网络繁忙");
	  	}});
	},
}