var xstrt;
var ystrt;
var xend;
var yend;
var xs1;
var xs2;
var ys1;
var ys2;
var k_lf=0;
var k_top1=0;
var k_x1=0, k_y1=0, k_x2=0, k_y2=0;
var fieldName;
var lf=0;
var top1=0;
var lf2=0;
var top2=0;
//初始倍数
var scale=100;
//图片初始大小
var img_w;
var img_h;

var trainname="";//字体库名称

function dragStart(event) {
    var pointer = getCoordInDocument(event)
    xstrt =pointer.x;
    ystrt =pointer.y;
    var  obj= $(".data_ctrl_C_lf_img img");
    lf = parseInt(obj.css('left'));
    top1 = parseInt(obj.css('top'));
    var  obj= $(".data_ctrl_kuang");
    k_lf = parseInt(obj.css('left'));
    k_top1 = parseInt(obj.css('top'));
}
function drag(event) {
    var pointer = getCoordInDocument(event);
    var xx =pointer.x;
    var yy =pointer.y;
    var x = xx-xstrt;
    var y = yy-ystrt;
    var  obj= $(".data_ctrl_C_lf_img img");
    obj.css('left',lf+x);
    obj.css('top',top1+y);
    lf2 = parseInt(obj.css('left'));
    top2 = parseInt(obj.css('top'));

    var  obj2= $(".data_ctrl_kuang");
    obj2.css('left',k_lf+x);
    obj2.css('top',k_top1+y);
    lf2 = parseInt(obj.css('left'));
    top2 = parseInt(obj.css('top'));

    xend=pointer.x;
    yend =pointer.y;
}
function dragEnd(event) {
}
function allowDrop(event) {
    event.preventDefault();
}
function drop(event) {
    event.preventDefault();
}
var getCoordInDocument = function(e) {
    e = e || window.event;
    var x = e.pageX || (e.clientX +
        (document.documentElement.scrollLeft
            || document.body.scrollLeft));
    var y = e.pageY || (e.clientY +
        (document.documentElement.scrollTop
            || document.body.scrollTop));
    return {'x':x,'y':y};
}
var x1,x2,x3,y1,y2,y3;
var getCoordInDocumentExample = function(){
    img_w = $("#width").html();
    img_h = $("#height").html();
    var coords = document.getElementById("coords");

    coords.onmousedown = function(e){
        $("#printscreen_over").hide();
        var pointer = getCoordInDocument(e);
        x1=pointer.x;
        y1=pointer.y;

        coords.onmousemove = function(e){
            var pointer = getCoordInDocument(e);
            x3=pointer.x;
            y3=pointer.y;
        }
    }

    coords.onmouseup = function(e){
        var pointer = getCoordInDocument(e);
        x2=pointer.x;
        y2=pointer.y;
        //$("#coords").removeAttr("onmousemove");
        $("#coords").prop("onmousemove",false);

        var printscreen_over = document.getElementById("printscreen_over");
        var data_ctrl_C = document.getElementsByClassName("data_ctrl_C_lf_img")[0];
        var tt = data_ctrl_C.offsetTop;
        var ll = data_ctrl_C.offsetLeft;
        printscreen_over.style.top = y2 - tt + "px";
        printscreen_over.style.left = x2-ll-64 + "px";
        $("#printscreen_over").show();
    }
}
window.onload = function(){
    getCoordInDocumentExample();
}


function huoqu(){
    var bd = document.getElementById("bd");
    var data_ctrl_C = document.getElementsByClassName("data_ctrl_C_lf_img")[0];
    var tt = data_ctrl_C.offsetTop;
    var ll = data_ctrl_C.offsetLeft;

    bd.onmousemove = function() {
        var signSpan = document.getElementById("signSpanId");

        if(y1 < y3){
            signSpan.style.top = y1-tt + "px";
            signSpan.style.height = y3 - y1 + "px";

        }
        else{
            signSpan.style.top = y3-tt + "px";
            signSpan.style.height = y1 - y3 + "px";
        }
        if(x1 < x3){
            signSpan.style.left = x1-ll + "px";
            signSpan.style.width = x3 - x1 + "px";

        }
        else{
            signSpan.style.left =  x3-ll + "px";
            signSpan.style.width = x1 - x3 + "px";
        }

        signSpan.style.display = "block";
    }
}

function dingge(){
    $("#bd").prop("onmousemove", false);
}
function clea(){
    document.getElementById("signSpanId").style.display="none";
}

$(function () {
    $(".data_ctrl_C_lf_sf_1").on('click',function(){
        var scale1=$(".data_ctrl_C_lf_sf_2").html().trim().substring(0,3);//100%到300%
        if(Number(scale1)>100){
            scale1=Number(scale1)-Number(10);
            $(".data_ctrl_C_lf_sf_2").html(scale1+"%");
            $(".data_ctrl_C_lf_img img").width(scale1+"%");
            showFrame(k_x1,k_y1,k_x2,k_y2);
        }else{
            return;
        }
    })
    $(".data_ctrl_C_lf_sf_3").on('click',function(){

        var scale1=$(".data_ctrl_C_lf_sf_2").html().trim().substring(0,3);//100%
        if(scale1==""){//初始赋值
            scale1=Number(100);
        }
        if(Number(scale1)<300){
            scale1=Number(scale1)+Number(10);
            $(".data_ctrl_C_lf_sf_2").html(scale1+"%");
            $(".data_ctrl_C_lf_img img").width(scale1+"%");
            showFrame(k_x1,k_y1,k_x2,k_y2);
        }else{
            return;
        }
    })
    $(".data_ctrl_C_rg_btn_printscreen").on('click',function () {
        $("#bd").show();
    })
    $(".printscreen_over_cel").on('click',function(){
        $("#bd").hide();
        $("#signSpanId").hide();
        $("#printscreen_over").hide();
    })
    $(".printscreen_over_sure").on('click',function(){
        $("#bd").hide();
        $("#signSpanId").hide();
        $("#printscreen_over").hide();

        var data_ctrl_C = document.getElementsByClassName("data_ctrl_C_lf_img")[0];
        var tt = data_ctrl_C.offsetTop;
        var ll = data_ctrl_C.offsetLeft;


        //获取图片当前宽高
        var imgW = $(".data_ctrl_C_lf_img").width();
        var imgH =Number( imgW*img_h/img_w ).toFixed(2);

        var scale1=$(".data_ctrl_C_lf_sf_2").html().trim().substring(0,3);
        //初始xy x1,y1;
        //最终xy x2,y2;
        //图片移动的xy lf2,top2;
        //图片初始位置 ll,tt
        //图片缩放大小scale
        //计算最终相对图片坐标公式
        // 截取起始点 xs1 = ((x1-tt-lf) / (imgW*scale/100))*img_w;
        xs1 = ((x1-ll-lf2)/(imgW*scale1/100))*img_w;
        xs1 = Number(xs1).toFixed(0);
        // 截取起始点 ys1 = ((x1-tt-lf) / (imgW*scale/100))*img_w;
        ys1 = ((y1-tt-top2)/(imgH*scale1/100))*img_h;
        ys1 = Number(ys1).toFixed(0);
        // 截取终点 xs2 = ((x2-tt-lf) / (imgW*scale/100))*img_w;
        xs2 = ((x2-ll-lf2)/(imgW*scale1/100))*img_w;
        xs2 = Number(xs2).toFixed(0);
        // 截取终点 ys2 = ((y2-tt-lf) / (imgW*scale/100))*img_w;
        ys2 = ((y2-tt-top2)/(imgH*scale1/100))*img_h;
        ys2 = Number(ys2).toFixed(0);
        if(trainname!=""){
            $.ajax({
                url: path+"/CommonTemplateManage/tempOCR",
                method: "POST",
                dataType: 'text',
                data:{
                    "x1" : xs1,
                    "y1" : ys1,
                    "x2" : xs2,
                    "y2" : ys2,
                    "src" : $("#dragtarget").attr("src"), //源路径
                    "trainName":trainname
                },
                success : function(result) {
                    document.getElementById("result").setAttribute("value", result);
                },
                error : function() {
                    showDialog1('error');
                }
            })
        }else {
            showDialog1("请选择字体库!");
            return;
        }


    })

    $(".data_ctrl_C_rg_save").on('click',function (){

        if(typeof(fieldName)=="undefined"){
            showDialog1("先选择坐标字段");
        }
        else if($("#templateName").val().trim()==""){
            showDialog1("请输入模板名!")
        }else if($("#note").val().trim()==""){
            showDialog1("请输入字段注释")
        }
        else{
            $.ajax({
                url: path+"/CommonTemplateManage/saveCommonCoordinate",
                method: "POST",
                dataType: 'text',
                data:{
                    "fieldName" : fieldName,
                    "x1" : xs1,
                    "y1" : ys1,
                    "x2" : xs2,
                    "y2" : ys2,
                    "templateName" : $("#templateName").val(),
                    "mapper" : $("#note").val(),//input值
                    "remark" : $("#remark").val(),
                    "fileName" : $("#fileName").html(),
                },
                success : function(data1) {
                    if("0"==data1) {
                        showDialog1("坐标入库成功!");
                        $("#note").val("");
                    }
                },
                error : function() {
                    alert('error');
                }
            })
        }
    })

    //下拉选择字段
    $(".data_ctrl_C_rg_select").toggle(
        function () {
            $(this).find(".data_ctrl_C_rg_select_list").show();
        },function () {
            $(this).find(".data_ctrl_C_rg_select_list").hide();
        }
    )
    $(".data_ctrl_C_rg_select_li").click(function () {
        console.log($(this).attr("data-id"));
        fieldName = $(this).attr("data-name");
        console.log(fieldName);
        $(this).parent().parent().find("#title_div").html(fieldName);
        $(this).parent().parent().find("#title_div").attr("title",$(this).attr("data-id"));
        fieldName=$(this).attr("data-id");
         $.ajax({
             url: path+"/CommonTemplateManage/findCoordinateAndnote",
             method: "POST",
             dataType: 'text',
             data:{
                 "fieldName" : fieldName,
                 "templateName" : $("#templateName").val(),
             },
             success : function(data) {
            	 var result=data.trim();
            	 var data1=result.substring(0,result.indexOf(";"));

                 var data2=result.substring(result.indexOf(";")+1);
                 if(data1 != null && data1 != ""){
                     var arr = data1.split(",");
                     showFrame(arr[0],arr[1],arr[2],arr[3]);
                 }
                 else{
                     //showDialog1("未制作此区域！");
                     $(".data_ctrl_kuang").hide();
                     //return;
                 }
                 if(data2 != null && data2 != ""){
                	 $("#note").val(data2);
                 }
                 
             },
             error : function() {
                 alert('error');
             }
         })
    })
     $(".data_ctrl_C_rg_select_li2").click(function () {
        console.log($(this).attr("data-id"));
        trainname = $(this).attr("data-name");
        console.log(trainname);
        $(this).parent().parent().find("#title_div1").html(trainname);
        $(this).parent().parent().find("#title_div1").attr("title",$(this).attr("data-id"));
        trainname=$(this).attr("data-name");
       
    })

    function showFrame(x1,y1,x2,y2) {
        k_x1 = x1;
        k_y1 = y1;
        k_x2 = x2;
        k_y2 = y2;
        var data_ctrl_C = document.getElementsByClassName("data_ctrl_C_lf_img")[0];
        var tt = data_ctrl_C.offsetTop;
        var ll = data_ctrl_C.offsetLeft;

        //获取图片当前宽高
        var imgW = $(".data_ctrl_C_lf_img img").width();
        var imgH =Number( imgW*img_h/img_w ).toFixed(2);

        var wi = (x2-x1)*imgW/img_w;
        var hi = (y2-y1)*imgW/img_w;

//		var xs1 = ((x1-ll-lf2)/(imgW*scale/100))*img_w;
//		var xs1 = Number(xs1).toFixed(2);
        //计算显示框的起点x
        var x = x1/img_w*(imgW*scale/100)+lf2;
        var x = Number(x).toFixed(2);
        //计算显示框的起点y
        var y = y1/img_h*(imgH*scale/100)+top2;
        var y = Number(y).toFixed(2);
        k_lf = x;
        k_top1= y;
        $(".data_ctrl_kuang").css("width",wi+"px");
        $(".data_ctrl_kuang").css("height",hi+"px");
        $(".data_ctrl_kuang").css("left",x+"px");
        $(".data_ctrl_kuang").css("top",y+"px");
        $(".data_ctrl_kuang").css("top",y+"px");
        $(".data_ctrl_kuang").show();


    }
    $(".data_ctrl_kuang").click(function () {
        $(".data_ctrl_kuang").hide();
    })
})
