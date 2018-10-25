/**
 * 获取项目路径
 */
var path = CommonJS.getContextPath();
/**
 * 监听页面回车事件,回车登录
 * 
 * @param e
 */
document.onkeydown = keyDownSearch;
function keyDownSearch(e)
{
    // 兼容FF和IE和Opera
    var theEvent = e || window.event;
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if(code == 13)
    {
        // 点击enter键，调用登录方法
        LoginJS.login();
    }
}
/**
 * 登录使用JS
 */
var LoginJS =
{
    // 登录方法
    login : function()
    {
        var userAccount = $.trim($("#userAccount").val());// 用户名
        var userPassword = $.trim($("#userPassword").val());// 密码
        // 判空处理
        if(CommonJS.isNull(userAccount))
        {
            showDialog1("用户名不能为空11");
            return;
        }
        // 判空处理
        if(CommonJS.isNull(userPassword))
        {
            showDialog1("密码不能为空");
            return;
        }
        // ajax调用登录
        $.ajax(
        {
            url : path + "/login/into",
            type : 'post',
            dataType : 'text',
            data :
            {
                "userAccount" : userAccount,
                "userPassword" : userPassword
            },
            success : function(result)
            {
                // 返回结果json转换
                var result = $.parseJSON(result);
                // 成功则跳转主页
                if(result.resultCode == "0")
                {
                    window.location.href = path + '/login/main?type=1';
                }
                else
                {
                    showDialog1(result.msg);
                }
            },
            error : function(result)
            {
                showDialog1("网络异常,请稍后重试");
            }
        });
    }
}
/**
 * 提示语弹窗
 * 
 * @param msg
 */
function showDialog1(msg)
{
    $('.mask').removeClass('hide');
    $('#bombBox1').removeClass('hide');
    $("#bombBoxText1").html(msg);
    $("#bombBoxTitle1").html("提示");
}
/**
 * 关闭提示语弹窗
 */
function closeDialog1()
{
    $('.mask').addClass('hide');
    $('.coupon_tanckuang').addClass('hide');
}


