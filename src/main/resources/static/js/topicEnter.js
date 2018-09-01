$(document).ready(function () {
    $.ajax({
        url: "../forum/kind" + window.location.search + "&pagenum=1",
        type: "get",
        success: function (data) {
            console.log(data);
            $(".LogoImg img").attr("src",data.forumKind.image);
            for (var i = 0; i < data.forums.content.length; i++) {
                var image = '<img src="../img/14.png">';
                if (data.forums.content[i].image != null) image =  '<img src="'+data.forums.content[i].image+'">';
                var five_block = $(
                    '<div class="recommedOuter"><div class="InRecommed"><a href="/html/topicContent.html?id='+data.forums.content[i].id+'">'+image+'<span>'+data.forums.content[i].title+'</span></a><span class="issue">该议题被浏览'+data.forums.content[i].sawnum+'次</span></div><img src="../img/skin.png"></div>'
                );
                $('.recommed_topic').append(five_block);
            }
            if (data.totalPage > 1) {
                $('.pagingTool').Paging({
                    pagesize: 1,
                    count: data.totalPage,
                    prevTpl: '<i class="iconfont">&#xe78c;</i>',
                    nextTpl: '<i class="iconfont">&#xe77c;</i>',
                    firstTpl: '<i class="iconfont">&#xe609;</i>',
                    lastTpl: '<i class="iconfont">&#xe6de;</i>',
                    callback:function (page,size,count) {
                        $(".recommed_topic").empty();
                        $.ajax({
                            url: "../forum/kind" + window.location.search + "&pagenum="+page,
                            type: "get",
                            success: function (data) {
                                for (var i = 0; i < data.forums.content.length; i++) {
                                    var image = '<img src="../img/14.png">';
                                    if (data.forums.content[i].image != null) image =  '<img src="'+data.forums.content[i].image+'">';
                                    var five_block = $(
                                        '<div class="recommedOuter"><div class="InRecommed"><a href="/html/topicContent.html?id='+data.forums.content[i].id+'">'+image+'<span>'+data.forums.content[i].title+'</span></a><span class="issue">该议题被浏览'+data.forums.content[i].sawnum+'次</span></div><img src="../img/skin.png"></div>'
                                    );
                                    $('.recommed_topic').append(five_block);
                                }
                            },errpr:function () {

                            }
                        });
                        console.log("num: "+page);
                        $(".recommed_topic").empty();
                        var n = page*15-1;
                        if (data.length-1 < n) n=data.length-1;
                        for (var i = (page-1)*15;i <= n;i ++){
                            var v_list = $('<div class="same_module"><a href="javascript:;"><img src="'+data[i].image+'"></a><span>'+data[i].title+'</span></div>');
                            $("#VideoBox").append(v_list);
                        }
                    }
                });

                $('.pagingTool li').on('click', function () {
                    $('.pagingTool').css({
                        '-webkit-user-select': 'none',
                        '-moz-user-select': 'none',
                        '-ms-user-select': 'none',
                        'user-select': 'none'
                    });
                });
            } else {
                $('.pagingTool').append('<div class="NOMore"><i class="iconfont">&#xe670;</i><p>没有更多了</p></div>');
            }
        }, error: function () {
            console.log("获取某一类型问答失败！")
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
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"></a><div class="msg_index_dance">进入个人中心</div></div><div class="editor_article"><a href="preset.html" target="_blank"><span><i class="iconfont">&#xe645;</i></span>写文章</a><a href="javascript:;" target="_blank" class="editor-first-a">发布</a><div class="three-part-for-article-video-issue"><a href="javascript:;" data-href="preset.html">发布文章</a><a href="javascript:;" data-href="UploadVideo.html">发布视频</a><a href="javascript:;" data-href="createIssue.html">发布议题</a></div></div>');
}
