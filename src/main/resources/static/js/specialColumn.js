$('.leftColumns a').on('click', function () {
    layer.msg("请先登录！")
});

$('.articleContainer').append('<div class="Select_Much"></div>');

var SelectDiv = $('.Select_Much');

function CutWordColumns(n, num) {
    $(n).each(function () {
        var maxwidth = num;
        if ($(this).text().length > maxwidth) {
            $(this).text($(this).text().substring(0, maxwidth));
            $(this).html($(this).html() + "...");
        }
    });
}

$(document).ready(function () {
    var H;
    if (window.location.search == "?type=internet"){
        H = "互联网";
    } else if (window.location.search == "?type=law") {
        H = "法律";
    } else if (window.location.search == "?type=medicine") {
        H = "医药";
    } else if (window.location.search == "?type=economy") {
        H = "经济";
    } else if (window.location.search == "?type=history") {
        H = "历史";
    } else if (window.location.search == "?type=science") {
        H = "理工";
    } else if (window.location.search == "?type=art") {
        H = "艺术";
    }
    $(".leftColumns").children("h1").text(H);
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

    $.ajax({
        url:"../doc"+window.location.search+"&pagenum=1",
        type:"get",
        success:function (data) {
            console.log(data);
            $(".leftColumns").children("p").eq(1).children("span").text(data.colnum);
            $(".leftColumns").children("p").eq(2).children("span").text(data.docnum);
            if (!data.col){
                $('.leftColumns a').html('关注教程');
                $('.leftColumns a').css({
                    'background': ''
                });
            } else {
                $('.leftColumns a').html('已关注');
                $('.leftColumns a').css({
                    'background': '#C1194E'
                });
            }
            var username;
            for (var i = 0;i < data.docUserViews.length;i ++){
                username = data.docUserViews[i].mulUser.nickname;
                var image = "";
                if (username == null) username = data.docUserViews[i].mulUser.username;
                if (data.docUserViews[i].document.image != null) image = '<img src="'+data.docUserViews[i].document.image+'"/>';
                var AddDiv = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data.docUserViews[i].document.id+'" target="_blank" class="under_line">'+data.docUserViews[i].document.title+'</a><p class="draw_text">'+data.docUserViews[i].document.summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data.docUserViews[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data.docUserViews[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'</aa></div>');

                SelectDiv.append(AddDiv);
            }
            CutWordColumns('.wordP', 60);
            CutWordColumns('.draw_text', 72);
            if (data.totalPage > 1){
                $(".Select_Much").after('<div class="pagingTool"></div>');
                $(".pagingTool").Paging({
                    pagesize: 1,
                    count:data.totalPage,
                    prevTpl: '<i class="iconfont">&#xe78c;</i>',
                    nextTpl: '<i class="iconfont">&#xe77c;</i>',
                    firstTpl: '<i class="iconfont">&#xe609;</i>',
                    lastTpl: '<i class="iconfont">&#xe6de;</i>',
                    callback:function (page,size,count) {
                        console.log("num: "+page);
                        $('.commentsList').empty();
                        $.ajax({
                            url:"../doc"+window.location.search+"&pagenum="+page,
                            type:"get",
                            success:function (data) {
                                $(".Select_Much").empty();
                                var username;
                                for (var i = 0;i < data.docUserViews.length;i ++){
                                    username = data.docUserViews[i].mulUser.nickname;
                                    var image = "";
                                    if (username == null) username = data.docUserViews[i].mulUser.username;
                                    if (data.docUserViews[i].document.image != null) image = '<img src="'+data.docUserViews[i].document.image+'"/>';
                                    var AddDiv = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data.docUserViews[i].document.id+'" target="_blank" class="under_line">'+data.docUserViews[i].document.title+'</a><p class="draw_text">'+data.docUserViews[i].document.summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data.docUserViews[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data.docUserViews[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'</aa></div>');

                                    SelectDiv.append(AddDiv);
                                }
                            },error:function() {
                                console.log("请求文章失败！")
                            }
                        })
                    }
                });
            }
        },error:function () {
            console.log("获取文章失败！")
        }
    })
});

function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"></a><div class="msg_index_dance">进入个人中心</div></div><div class="editor_article"><a href="preset.html" target="_blank"><span><i class="iconfont">&#xe645;</i></span>写文章</a><a href="javascript:;" target="_blank" class="editor-first-a">发布</a><div class="three-part-for-article-video-issue"><a href="javascript:;" data-href="preset.html">发布文章</a><a href="javascript:;" data-href="UploadVideo.html">发布视频</a><a href="javascript:;" data-href="createIssue.html">发布议题</a></div></div>');
    $(".rightColumns").attr("src",image);
    $(".leftColumns a").off("click");
    $('.leftColumns a').on('click', function () {
        if ($(this)[0].style.background == "rgb(193, 25, 78)") {
            $(this).html('关注问题');
            $(this).css({
                'background': '',
            });
            if (parseInt($(".leftColumns").children("p").eq(1).children("span").text()) > 1){
                $(".leftColumns").children("p").eq(1).children("span").text(parseInt($(".leftColumns").children("p").eq(1).children("span").text())-1)
            } else{
                $(".leftColumns").children("p").eq(1).children("span").text("无")
            }
        } else {
            $(this).html('已关注');
            $(this).css({
                'background': '#C1194E',
            });
            if ($(".leftColumns").children("p").eq(1).children("span").text() == "无"){
                $(".leftColumns").children("p").eq(1).children("span").text(1)
            } else{
                $(".leftColumns").children("p").eq(1).children("span").text(parseInt($(".leftColumns").children("p").eq(1).children("span").text())+1)
            }
        }
        $.ajax({
            url:"../col/docK"+window.location.search,
            type:"put",
            success:function () {
            },error:function () {
                console.log("文章类型关注出错！")
            }
        })
    });
}