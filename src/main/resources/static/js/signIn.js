$(document).ready(function () {
   $("input").eq().focus();
});

$("#main_content").keyup(function (event) {
    if (event.keyCode == 13){
        $(".signIn_btn").click();
    }
});

$(".signIn_btn").click(function () {
    var username = $("input:first").val();
    var password = $("input").eq(1).val();
    $.ajax({
        url:"/login",
        type:"post",
        data:{"username":username,"password":password},
        success:function (data) {
            if (data.msg != null){
                if (data.msg == "NoUser"){
                    layer.msg("用户名不存在！");
                }else if (data.msg == "NoEmail") {
                    layer.msg("邮箱不存在")
                }else {
                    console.log("登录发生未知错误");
                }
            } else{
                parent.location.reload();
            }
        },error:function (data) {
        }
    })
});
