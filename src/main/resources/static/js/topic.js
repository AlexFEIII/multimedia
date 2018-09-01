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
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"></a><div class="msg_index_dance">进入个人中心</div></div><div class="editor_article"><a href="javascript:;" target="_blank" class="editor-first-a">发布</a><div class="three-part-for-article-video-issue"><a href="javascript:;" data-href="preset.html">发布文章</a><a href="javascript:;" data-href="UploadVideo.html">发布视频</a><a href="javascript:;" data-href="createIssue.html">发布议题</a></div></div>');
    // 发布
    $('.editor_article').hover(function () {
        $('.three-part-for-article-video-issue').css('transform', 'scaleY(1)');
    }, function () {
        $('.three-part-for-article-video-issue').css('transform', '');
    });

    $('.three-part-for-article-video-issue a').on('click', function () {
        var This = $(this);
        $('.three-part-for-article-video-issue').css('transform', '');
        setTimeout(function () {
            if (This.attr('data-href') != '1') { //为了实现本页面不跳转
                window.open(This.attr('data-href'));
            }
        }, 300);
    });
}
