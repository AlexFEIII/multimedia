$(".signIn_btn").on('click',function () {
    var username = $("input:first").val();
    var password = $("input").eq(1).val();
    $.ajax({
        url:"/login",
        type:"post",
        data:{"username":username,"password":password},
        success:function (data) {
            if (data.msg != null){
                if (data.msg == "NoUser"){
                    alert("用户名不存在！");
                }else if (data.msg == "NoEmail") {
                    alert("邮箱不存在")
                }else {
                    console.log("登录发生未知错误");
                }
            } else{
                parent.$(".layui-layer-close").click();
                parent.$(".last_li").empty();
                var image = "../img/14.png";
                if (data.image != null) image = data.image;
                parent.$(".last_li").append('<div class="location_div_a"><a href="../html/personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"> </a> <div class="msg_index_dance">进入个人中心 </div> </div> <div class="editor_article"> <a href="RichEditor.html" target="_blank"> <span> <i class="iconfont">&#xe645;</i></span>写文章</a></div>');
            }
        },error:function (data) {
        }
    })
});