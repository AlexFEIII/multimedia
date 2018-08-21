$(document).ready(function () {
    $.ajax({
        url:"../forum/allkind",
        type:"get",
        success:function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
                var MuchSame = $(
                    '<div class="MuchSame"><a href="topicEnter.html?id='+data[i].forumKind.id+'" target="_blank"><span class="MuchSameSpan"><img src="'+data[i].forumKind.image+'"></span>' +
                    '<h4>'+data[i].forumKind.kind+'</h4><div class="bottomDiv"><p>举办过</p><span>'+data[i].count+'</span><p>场圆桌</p></div><span class="replaceA">进入会场</span></a></div>'
                );
                $('.containMuch').append(MuchSame);
            }
            $('.MuchSame h4').each(function () {
                var maxwidth = 7;
                if ($(this).text().length > maxwidth) {
                    $(this).text($(this).text().substring(0, maxwidth));
                    $(this).html($(this).html() + "...");
                }
            });
        },error:function () {
            console.log("获取全部类型问答出错！")
        }
    });

    $.ajax({
        url:"../user/isLogin",
        type:"get",
        success:function (data) {
            console.log(data);
            if (data != ""){
                loginSuccess(data);
            }
        }
    });
});

function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"> </a> <div class="msg_index_dance">进入个人中心 </div> </div> <div class="editor_article"> <a href="preset.html" target="_blank"> <span> <i class="iconfont">&#xe645;</i></span>写文章</a></div>');
}
