$(".signIn_btn").on('click',function () {
    var username = $("input:first").val();
    var password = $("input").eq(1).val();
    $.ajax({
        url:"/login",
        type:"post",
        data:{"username":username,"password":password},
        success:function (data) {
            console.log(data.msg);
            if (data.msg == "NoUser"){
                alert("用户名不存在！");
            }else if (data == "NoEmail") {
                console.log("邮箱不存在")
            }else if (data == "UnKnown") {
                console("登录发生未知错误");
            }else{

            }
        },error:function (data) {
        }
    })
});