var FORUMDATA;
var USERDATA;
var PROPAGE = 1;
var COMPAGE = 1;
$(document).ready(function () {
    $.ajax({
        url:"../user/isLogin",
        type:"get",
        async:false,
        success:function (data) {
            USERDATA = data;
            console.log(data);
            if (data != ""){
                loginSuccess(data);
            }
        }
    });

    $.ajax({
        url:"../forum"+window.location.search,
       type:"get",
       async:false,
       success:function (data) {
           console.log(data);
           FORUMDATA = data;
           var image = "../img/14.png";
           if (data.forum.image != null) image = data.forum.image;
           $(".PeopleTitle h1").text(data.forum.title);
           $(".imgContain img").attr("src",image);
           $(".describe p").text(data.forum.content);
           $(".leftFocus").children("span").eq(0).text(data.forum.sawnum);
           $(".leftFocus").children("span").eq(3).text(data.colnum);
           $(".topRightTopic").find("img").attr("src",data.forum.kind.image);
           $(".owner").children("p").eq(0).text(data.forum.kind.kind);
           $(".owner").children("p").eq(1).children("span").text(data.kindnum);
           $(".TitleA").children("p").text("欢迎你参加 "+data.forum.title+" 议题");
           if (USERDATA != ""){
               if (data.follow){
                   $('.RealFocus').text('取消关注');
                   $('.RealFocus').addClass('rightFocusHover');
               }
               $('.RealFocus').off("click");
               $('.RealFocus').on('click', function () {
                   var num = $(".leftFocus").children("span").eq(3).text();
                   if ($(this).text() == '取消关注') {
                       $(this).text('关注');
                       $(this).removeClass('rightFocusHover');
                       $(".leftFocus").children("span").eq(3).text(parseInt(num)-1)
                   } else {
                       $(this).text('取消关注');
                       $(this).addClass('rightFocusHover');
                       $(".leftFocus").children("span").eq(3).text(parseInt(num)+1)
                   }
                   $.ajax({
                       url:"../col/forum?forumid="+FORUMDATA.forum.id,
                       type:"put",
                       success:function () {
                       },error:function () {
                           console.log("议题关注出错！")
                       }
                   })
               });
           }else{

           }
       },error:function () {
           console.log("获得议题信息失败！")
       }
   });


   //加载第一页的问题
    $.ajax({
        url:"/getFPro/"+FORUMDATA.forum.id+"/1",
        type:"get",
        async:false,
        success:function (data) {
            console.log(data);
            if (data!="") {
                $(".OneMiddle a").html("<span></span>个问题");
                $(".OneMiddle span").text(data[0].count);
            }else{
                $(".OneMiddle a").html("<span>添加问题</span>");
            }
            showPro(data);
        },error:function () {
            console.log("加载页面时获取评论失败！")
        }
    });

    //判断加载更多是否是在可视范围内。
    var PROFLAG = false,COMFLAG = false;
    $(window).scroll(function (event) {
        var WINTOP = $(window).scrollTop();
        if (PROFLAG == false && $(".OneList").css("display")!="none" && ($(".OneList .loading-more").offset().top+150) < WINTOP+window.screen.height){
            PROFLAG = true;
            $.ajax({
                url:"/getFPro/"+FORUMDATA.forum.id+"/"+PROPAGE,
                type:"get",
                async:false,
                success:function (data) {
                    console.log(data);
                    showPro(data);
                    if (!(data == "" || data[0].totalPage < PROPAGE)) {
                       PROFLAG = false;
                    }
                },error:function () {
                    console.log("加载页面时获取评论失败！")
                }
            });
        }else if(COMFLAG == false && $(".TwoList").css("display")!="none" && ($("#TwoList-loading-more").offset().top+150) < WINTOP+window.screen.height){
            COMFLAG = true;
            $.ajax({
                url:"../getFComment/"+FORUMDATA.forum.id+"/"+COMPAGE,
                type:"get",
                success:function (data) {
                    console.log(data);
                    showCom(data);
                    if (!(data == "" || data[0].totalPage < COMPAGE)) {
                        COMFLAG = false;
                    }
                },error:function () {
                    console.log("加载页面时获取评论失败！")
                }
            });
        }
    })
});

//显示问题用的方法
function showPro(data) {
    if (data == "" || data[0].totalPage < PROPAGE ){
        $(".OneList").children(".loading-more").remove();
    }else{
        PROPAGE ++;
        if (PROPAGE > data[0].totalPage)  $(".OneList").children(".loading-more").remove();
        for (var i = 0;i < data.length;i ++){
            var DELPro = "";
            if (USERDATA != "" && USERDATA.id == FORUMDATA.mulUser.id) DELPro = '<a href="javascript:;" class="DeleteProblemASpecial"><i class="iconfont">&#xe622;</i><span>删除问题</span></a>'
            var addComments = $('<li class="countLiNum"><div class="TitleA"><span style="display: none" class="ProID">'+data[i].forumCUser.forumProblem.id+'</span><a href="AnswerQuestion.html?id='+data[i].forumCUser.forumProblem.id+'" target="_blank" class="TitleAfterA">'+data[i].forumCUser.forumProblem.title+'</a></div>' +
                '<div class="SocialTool"><a href="javascript:;" class="GuanFocus"><i class="iconfont">&#xe6e0;</i><span>关注</span></a>' +
                '<a href="javascript:;" class="ZanA"><i class="iconfont">&#xe60a;</i><span>赞</span><span class="Zan">'+data[i].forumCUser.forumProblem.upvotenum+'</span></a>' +
                '<a href="javascript:;" class="commentsAndjoin" packUp="1" isLoad="0"><i class="iconfont">&#xe66f;</i><span>参与讨论</span></a>' +
                DELPro+'<div class="comments-not-or-yes">还没有讨论</div><div class="Load-animated"><div class="spinner spinnerTwo"><span></span></div></div></div></li>');
            $('.OneList li:last').after(addComments);

            if (data[i].follow) {
                $('.GuanFocus').last().html('<i class="iconfont">&#xe76a;</i> <span>取消关注</span>');
                add($('.GuanFocus').last());
            }
            if (data[i].up){
                add($('.ZanA').last());
            }
        }
        $('body').getNiceScroll().resize();
        // 删除问题
        $('.DeleteProblemASpecial').unbind('click').on('click', function () {
            var This = $(this);
            layer.confirm('确定要删除此评论吗?', {
                btn: ['确定', '取消'], //按钮
                title: '提示'
            }, function (index) {
                layer.close(index);
                $.ajax({
                    url:"../deleteC?type=forum&docid="+FORUMDATA.forum.id+"&commentid="+This.parent().parent().find(".ProID").text(),
                    type:"delete",
                    success:function (data) {
                        This.parent().parent().remove();
                        var Len = parseInt($('.OneMiddle span').text());
                        if (Len-1 > 0) {
                            $('.OneMiddle span').html((Len-1) + '个问题');
                        } else {
                            $('.OneMiddle span').html('添加问题');
                        }
                    },error:function () {
                    }
                })
            });
        }); //删除评论
        if (USERDATA == ""){
            $('.GuanFocus').on('click', function () {
                layer.msg("请先登录！");
            });
            $('.ZanA').on('click',function () {
                layer.msg("请先登录！");
            });
            $('.commentsAndjoin').on('click',function () {
                layer.msg("请先登录！");
            });
        }else{
            //关注
            $(".GuanFocus").off("click");
            $('.GuanFocus').on('click', function () {
                if (!$(this).children("span").hasClass('HoverA')) {
                    $(this).html('<i class="iconfont">&#xe76a;</i> <span>取消关注</span>');
                    add($(this));
                } else {
                    $(this).html('<i class="iconfont">&#xe6e0;</i> <span>关注</span>')
                    remove($(this));
                }
                $.ajax({
                    url:"../col/forumC?forumid="+FORUMDATA.forum.id+"&cid="+$(this).parent().parent().find(".ProID").text(),
                    type:"put",
                    success:function (data) {
                    },error:function () {
                        console.log("问题关注出错！")
                    }
                })
            });

            //点赞
            $(".ZanA").off("click");
            $('.ZanA').on('click', function () {
                console.log("num: "+$(this).find('.Zan').text());
                var ZAN = $(this).find(".Zan");
                if (!ZAN.hasClass('HoverA')) {
                    add($(this));
                    var Num = parseInt(ZAN.text());
                    if (ZAN.text() == '') {
                        ZAN.text('1');
                    } else {
                        ZAN.text(Num + 1);
                    }
                } else {
                    remove($(this));
                    var Num = parseInt(ZAN.text());
                    if (ZAN.text() == '1') {
                        ZAN.text('');
                    } else {
                        ZAN.text(Num - 1);
                    }
                }
                $.ajax({
                    url:"../upvote?type=FComment&objid="+$(this).parent().parent().find(".ProID").text(),
                    type:"put",
                    success:function () {
                    },error:function () {
                        console.log("文章点赞出错！")
                    }
                });
            });

            // 参与讨论
            $('.commentsAndjoin').off("click");
            $('.commentsAndjoin').on('click', function () {
                var This = $(this);
                if (This.attr("isLoad") == 0){
                    $(this).parent().find('.Load-animated').css('display', 'flex');
                    $.ajax({
                        url:"../getFCRelay/"+$(this).parent().parent().find(".ProID").text(),
                        type:"get",
                        success:function (data) {
                            console.log(data);
                            if (data.length == 0) $(this).parent().find('.comments-not-or-yes').css('display', 'block');
                            This.parent().find('.Load-animated').css('display', '');
                            for (var i = 0;i < data.length;i ++){
                                var rUserInfo = "";
                                var DRTime = new Date(data[i].forumRelay.date);
                                if (data[i].forumRelay.rcommentid != 0){
                                    rUserInfo = '：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+data[i].rid+'">@'+data[i].rname+'</a>';
                                }
                                var DelButton = "";
                                if (USERDATA.nickname == data[i].nickname){
                                    DelButton = '<a href="javascript:;" class="DEl">删除</a>'
                                }
                                $('.NewGoodEditor').after('<div class="insertComment"><span style="display: none" class="ReID">'+data[i].forumRelay.id+'</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+data[i].id+'">'+data[i].nickname+'</a>'+rUserInfo+'</span><p class="TwoSecond">'+data[i].forumRelay.content+'</p><div><span class="oneSpanTWO">'+DRTime.getFullYear()+'/'+DRTime.getMonth()+'/'+DRTime.getDate()+'</span><span class="TwoSpanTWO">'+DRTime.getHours()+':'+DRTime.getMinutes()+':'+DRTime.getMilliseconds()+'</span></div><div><a href="javascript:;" class="ADDCommit">评论</a>'+DelButton+'</div></div>');
                            }
                            $('body').getNiceScroll().resize();
                            $('.OneList .ADDCommit').unbind('click').on('click', function () {
                                var That = $(this);
                                getNewEditor($(this).parent(), 0,That.parent().parent().children(".twoUser").children("a").eq(0).href,That.parent().parent().children(".twoUser").children("a").eq(0).text(),That.parent().parent().parent().find(".ProID").text(),That.parent().parent().children(".ReID").text());
                                $(this).parent().parent().parent().find('.publish_A').on('click', function () {
                                    if (That.parent().parent().parent().find('.comments-not-or-yes').html() == '还没有讨论') {
                                        That.parent().parent().parent().find('.comments-not-or-yes').html('1条讨论');
                                    } else {
                                        var Len = That.parent().parent().parent().find('.insertComment').length;
                                        That.parent().parent().parent().find('.comments-not-or-yes').html('' + Len + '条讨论');
                                    }
                                });
                            });
                            $('.OneList .DEl').unbind('click').on('click', function () {
                                var There = $(this);
                                layer.confirm('确定要删除此评论吗?', {
                                    btn: ['确定', '取消'], //按钮
                                    title: '提示'
                                }, function (index) {
                                    var Len = There.parent().parent().parent().find('.insertComment').length - 1;
                                    if (Len > 0) {
                                        There.parent().parent().parent().find('.comments-not-or-yes').html('' + Len + '条讨论');
                                    } else {
                                        There.parent().parent().parent().find('.comments-not-or-yes').html('还没有讨论');
                                    }
                                    layer.close(index);
                                    console.log($(this));
                                    $.ajax({
                                        url:"../deleteR?type=forum&docid="+FORUMDATA.forum.id+"&commentid="+There.parent().parent().parent().find(".ProID").text()+"&rcommentid="+There.parent().parent().children(".ReID").text(),
                                        type:"delete",
                                        success:function (data) {
                                            There.parent().parent().remove();
                                            var list = $(".ReID");
                                            for (var i = 0;i < data.length;i ++){
                                                console.log(i);
                                                for(var j = 0;j < list.length;j ++){
                                                    if (list[j].innerText == data[i]){
                                                        list.eq(j).parent().remove();
                                                        break;
                                                    }
                                                }
                                            }
                                        },error:function () {                                            }
                                    })
                                });
                            });
                            This.attr("isLoad",1)
                        }
                    });
                }

                if ($(this).attr('packUp') == '1') {
                    statistic_data();
                    $(this).attr('packUp', '0');
                    $(this).find('span').html('收起讨论');
                    getNewEditor($(this), 0,"","",This.parent().parent().find(".ProID").text(),0);
                    $(this).parent().parent().data('statistic_data', '1');
                    This.parent().parent().find('.insertComment').css('display', 'flex');
                    $('body').getNiceScroll().resize();
                    $(this).parent().parent().find('.publish_A').on('click', function () {
                        if (This.parent().parent().find('.comments-not-or-yes').html() == '还没有讨论') {
                            This.parent().parent().find('.comments-not-or-yes').html('1条讨论');
                        } else {
                            var Len = This.parent().parent().find('.insertComment').length;
                            This.parent().parent().find('.comments-not-or-yes').html('' + Len + '条讨论');
                        }
                    });
                } else {
                    $(this).attr('packUp', '1');
                    if ($(this).parent().parent().find('.comments-not-or-yes').html() == '还没有讨论') {
                        $(this).find('span').html('参与讨论');
                    } else {
                        $(this).find('span').html($(this).parent().parent().find('.comments-not-or-yes').html());
                    }
                    $(this).parent().find('.comments-not-or-yes').css('display', '');
                    $(this).parent().parent().find('.insertComment').css('display', 'none');
                    CodeSame($('.NewGoodEditor'));
                }
            });

        }

    }

}
//登录成功执行的方法
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
    //添加评论
    $(".AndJoin").off("click");
    $('.AndJoin').on('click', function () {
        statistic_data();
        getNewEditor($(this), 1,"","","",0);
    });

    $(".FirstAndJoin").off("click");
    $('.FirstAndJoin').on('click', function () {
        statistic_data();
        //提问弹出的富文本编辑
        getNewFirstEditor($(this).parent());
    });
}

//点击评论显示第一页评论
$(".TwoMiddle").click(function () {
    var This = $(this);
    if ($(this).attr("isload") == 0){
        $.ajax({
            url:"../getFComment/"+FORUMDATA.forum.id+"/1",
            type:"get",
            success:function (data) {
                console.log(data);
                if (data != ""){
                    $(".TwoMiddle a").html("<span></span>个评论");
                    $(".TwoMiddle span").text(data[0].count)
                }else{
                    $(".TwoMiddle a").html("<span>添加评论</span>");
                }
                showCom(data);
                This.attr("isload",1)
            },error:function () {
                console.log("获取议题评论失败！")
            }
        })
    }
});

//显示评论的方法
function showCom(data) {
    if (data == "" || data[0].totalPage < COMPAGE ){
        $(".OneList").children(".loading-more").remove();
    }else {
        COMPAGE++;
        if (COMPAGE > data[0].totalPage) $(".TwoList").children(".loading-more").remove();
        for (var i = 0; i < data.length; i++) {
            var rUserInfo = "";
            var DRTime = new Date(data[i].forumComment.date);
            if (data[i].forumComment.rcommentid != 0) {
                rUserInfo = '：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id=' + data[i].rid + '">@' + data[i].ruser + '</a>';
            }
            var DelButton = "";
            if (USERDATA.nickname == data[i].nickname) {
                DelButton = '<a href="javascript:;" class="DEl">删除</a>'
            }
            $(".TwoList .loading-more").before('<div class="insertComment"><span style="display: none" class="ReID">' + data[i].forumComment.id + '</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id=' + data[i].id + '">' + data[i].nickname + '</a>' + rUserInfo + '</span><p class="TwoSecond">' + data[i].forumComment.content + '</p><div><span class="oneSpanTWO">' + DRTime.getFullYear() + '/' + DRTime.getMonth() + '/' + DRTime.getDate() + '</span><span class="TwoSpanTWO">' + DRTime.getHours() + ':' + DRTime.getMinutes() + ':' + DRTime.getMilliseconds() + '</span></div><div><a href="javascript:;" class="ADDCommit">评论</a>' + DelButton + '</div></div>');
        }
        $('body').getNiceScroll().resize();
        if (USERDATA == "") {
            $('.TwoList .ADDCommit').unbind('click').on('click', function () {
                layer.msg("请先登录！")
            });
            $('.TwoList .DEl').unbind('click').on('click', function () {
                layer.msg("请先登录！")
            })
        } else {
            $('.TwoList .ADDCommit').unbind('click').on('click', function () {
                var That = $(this);
                statistic_data();
                getNewEditor($(this), 1, That.parent().parent().children(".twoUser").children("a").eq(0).href, That.parent().parent().children(".twoUser").children("a").eq(0).text(), "", That.parent().parent().children(".ReID").text());
            });
            $('.TwoList .DEl').unbind('click').on('click', function () {
                var There = $(this);
                layer.confirm('确定要删除此评论吗?', {
                    btn: ['确定', '取消'], //按钮
                    title: '提示'
                }, function (index) {
                    layer.close(index);
                    var Len = parseInt($('.TwoMiddle span').text());
                    if (Len-1 > 0) {
                        $('.TwoMiddle span').html((Len-1) + '个评论');
                    } else {
                        $('.TwoMiddle span').html('添加评论');
                    }
                    $.ajax({
                        url:"../forum/delCom?forumid="+FORUMDATA.forum.id+"&commentid="+There.parent().parent().children(".ReID").text(),
                        type:"delete",
                        success:function (data) {
                            There.parent().parent().remove();
                            var list = $(".ReID");
                            for (var i = 0;i < data.length;i ++){
                                console.log(i);
                                for(var j = 0;j < list.length;j ++){
                                    if (list[j].innerText == data[i]){
                                        list.eq(j).parent().remove();
                                        break;
                                    }
                                }
                            }
                        },error:function () {}
                    })
                });
            });
        }
    }
}

var H1People = $('.PeopleTitle h1').html();
$('.TitleA p').html('欢迎你参加' + H1People + '议题');

$('.RealFocus').on('click', function () {
    layer.msg("请先登录！")
});

//限制描述的字数
$(".describe p").each(function () {
    var maxwidth = 150;
    if ($(this).text().length > maxwidth) {
        $(this).text($(this).text().substring(0, maxwidth));
        $(this).html($(this).html() + "...");
    }
});

function add(n) {
    n.find('.iconfont').addClass('HoverA');
    n.find('span').addClass('HoverA');
}

function remove(n) {
    n.find('.iconfont').removeClass('HoverA');
    n.find('span').removeClass('HoverA');
}

//添加评论
$('.AndJoin').on('click', function () {
    layer.msg("请先登录！")
});

$('.FirstAndJoin').on('click', function () {
    layer.msg("请先登录！")
});

// 记录上一个countLiNum的数据
function statistic_data() {
    $('.countLiNum').each(function () {
        if ($(this).data('statistic_data') == '1') {
            $(this).data('statistic_data', '0');
            if ($(this).find('.comments-not-or-yes').html() == '还没有讨论') {
                $(this).find('.commentsAndjoin span').html('参加讨论');
            } else {
                $(this).find('.commentsAndjoin span').html($(this).find('.comments-not-or-yes').html());
                $(this).find('.commentsAndjoin').attr('packUp', '1');
            }
            $(this).find('.comments-not-or-yes').css('display', '');
            $(this).find('.insertComment').css('display', 'none');
        }
    });
}

//增加问题的编辑器
function getNewFirstEditor(n) {
    var NewGoodEditor = $('<div class="NewGoodEditor" style="padding-top: 0px;padding-bottom: 20px;"><div class="NewEditor">' +
        '<div id="Newtoolbar" class="NewToolbar" style="width:100%;background: #fff;border-bottom: 1px solid #DDD;"></div>' +
        '<div id="NewUser_edit" class="EditorNew" style="width:100%;height:200px;display: flex;justify-content: center;' +
        'align-content: center;flex-wrap:wrap;background:#fff;"></div></div></div>');
    CodeSame($('.NewGoodEditor'));
    n.parent().after(NewGoodEditor);
    setTimeout(function () {
        $('.NewGoodEditor').css({
            opacity: '1',
            top: '0'
        });
        $('.NewGoodEditor .cancel_A').css({
            display: 'flex'
        });
        $('.NewGoodEditor .publish_A').html('<i class="iconfont">&#xe815;</i>发送');
    }, 10);
    var M = window.wangEditor;
    var NewEditor = new M('#Newtoolbar', '#NewUser_edit');
    NewEditor.customConfig.menus = ['undo'];
    NewEditor.customConfig.zIndex = 0;
    NewEditor.create();

    cancel();

    improveAreduceZIndex();

    //解决火狐不能自动去除占位符的问题
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isFF = userAgent.indexOf('Firefox') > -1; //判断是否Firefox浏览器
    if (isFF) {
        $('.w-e-text p').eq(0).find('br').css({
            'display': 'none',
        });
    }

    $('.NewGoodEditor .publish_A').on('click', function () {
        var ContentNew = $('.NewGoodEditor .w-e-text').html().replace(/<(?!img).*?>/g, "");
        console.log(ContentNew);
        var addComments = $('<li class="countLiNum"><div class="TitleA"><span style="display: none" class="ProID"></span><a href="javascript:;" target="_blank" class="TitleAfterA">不合法的身份和第三方第三方电脑</a></div>' +
            '<div class="SocialTool"><a href="javascript:;" class="GuanFocus"><i class="iconfont">&#xe6e0;</i><span>关注</span></a>' +
            '<a href="javascript:;" class="ZanA"><i class="iconfont">&#xe60a;</i><span>赞</span><span class="Zan">0</span></a>' +
            '<a href="javascript:;" class="commentsAndjoin" packUp="1"  isLoad="0"><i class="iconfont">&#xe66f;</i><span>参与讨论</span></a>' +
            '<a href="javascript:;" class="DeleteProblemASpecial"><i class="iconfont">&#xe622;</i><span>删除问题</span></a><div class="comments-not-or-yes">还没有讨论</div><div class="Load-animated"><div class="spinner spinnerTwo"><span></span></div></div></div></li>');
        if (ContentNew == '') {
            alert('请您写一点内容再发送，当前状态不可发送');
        } else {
            //添加问题
            $.ajax({
                url:"../forum/addPro?forumid="+FORUMDATA.forum.id+"&title="+ContentNew,
                type:"put",
                success:function (data) {
                    if (data == "ILLEGAL") {
                        layer.msg("问题含有非法、敏感内容！")
                    }else{
                        if ($('.OneMiddle span').text() == "添加问题") {
                            $('.OneMiddle span').text('1');
                        } else {
                            $('.OneMiddle span').text(parseInt($('.OneMiddle span').text())+1);
                        }
                        $('.OneList li:first').after(addComments);
                        $('.OneList li').eq(1).find('.TitleAfterA').html(ContentNew);
                        $('.OneList li').eq(1).find('.ProID').html(data);
                        $('.OneList li').eq(1).find('.TitleAfterA').attr("href","AnswerQuestion.html?id="+data);
                        // 删除问题
                        $('.DeleteProblemASpecial').unbind('click').on('click', function () {
                            var This = $(this);
                            layer.confirm('确定要删除此评论吗?', {
                                btn: ['确定', '取消'], //按钮
                                title: '提示'
                            }, function (index) {
                                layer.close(index);
                                $.ajax({
                                    url:"../deleteC?type=forum&docid="+FORUMDATA.forum.id+"&commentid="+This.parent().parent().find(".ProID").text(),
                                    type:"delete",
                                    success:function (data) {
                                        This.parent().parent().remove();
                                        var Len = parseInt($('.OneMiddle span').text());
                                        if (Len-1 > 0) {
                                            $('.OneMiddle span').html(Len-1);
                                        } else {
                                            $('.OneMiddle a').html('<span>添加问题</span>');
                                        }
                                    },error:function () {
                                    }
                                })
                            });
                        }); //删除评论
                        //关注
                        $(".GuanFocus").off("click");
                        $('.GuanFocus').on('click', function () {
                            if (!$(this).children("span").hasClass('HoverA')) {
                                $(this).html('<i class="iconfont">&#xe76a;</i> <span>取消关注</span>');
                                add($(this));
                            } else {
                                $(this).html('<i class="iconfont">&#xe6e0;</i> <span>关注</span>')
                                remove($(this));
                            }
                            $.ajax({
                                url:"../col/forumC?forumid="+FORUMDATA.forum.id+"&cid="+$(this).parent().parent().find(".ProID").text(),
                                type:"put",
                                success:function (data) {
                                },error:function () {
                                    console.log("问题关注出错！")
                                }
                            })
                        });

                        //点赞
                        $(".ZanA").off("click");
                        $('.ZanA').on('click', function () {
                            console.log("num: "+$(this).find('.Zan').text());
                            var ZAN = $(this).find(".Zan");
                            if (!ZAN.hasClass('HoverA')) {
                                add($(this));
                                var Num = parseInt(ZAN.text());
                                if (ZAN.text() == '') {
                                    ZAN.text('1');
                                } else {
                                    ZAN.text(Num + 1);
                                }
                            } else {
                                remove($(this));
                                var Num = parseInt(ZAN.text());
                                if (ZAN.text() == '1') {
                                    ZAN.text('');
                                } else {
                                    ZAN.text(Num - 1);
                                }
                            }
                            $.ajax({
                                url:"../upvote?type=FComment&objid="+$(this).parent().parent().find(".ProID").text(),
                                type:"put",
                                success:function () {
                                },error:function () {
                                    console.log("文章点赞出错！")
                                }
                            });
                        });
                        // 参与讨论
                        $('.commentsAndjoin').unbind('click').on('click', function () {
                            var This = $(this);
                            if ($(this).attr('packUp') == '1') {
                                statistic_data();
                                $(this).attr('packUp', '0');
                                $(this).find('span').html('收起讨论');
                                $(this).parent().find('.comments-not-or-yes').css('display', 'block');
                                $(this).parent().find('.Load-animated').css('display', 'flex');
                                setTimeout(function () {
                                    This.parent().find('.Load-animated').css('display', '');
                                    This.parent().parent().find('.insertComment').css('display', 'flex');
                                }, 900);
                                getNewEditor($(this), 0,"","",This.parent().parent().find(".ProID").text(),0);
                                $(this).parent().parent().data('statistic_data', '1');
                                $(this).parent().parent().find('.publish_A').on('click', function () {
                                    if (This.parent().parent().find('.comments-not-or-yes').html() == '还没有讨论') {
                                        This.parent().parent().find('.comments-not-or-yes').html('1条讨论');
                                    } else {
                                        var Len = This.parent().parent().find('.insertComment').length;
                                        This.parent().parent().find('.comments-not-or-yes').html('' + Len + '条讨论');
                                    }
                                });
                            } else {
                                $(this).attr('packUp', '1');
                                if ($(this).parent().parent().find('.comments-not-or-yes').html() == '还没有讨论') {
                                    $(this).find('span').html('参与讨论');
                                } else {
                                    $(this).find('span').html($(this).parent().parent().find('.comments-not-or-yes').html());
                                }
                                $(this).parent().find('.comments-not-or-yes').css('display', '');
                                $(this).parent().parent().find('.insertComment').css('display', 'none');
                                CodeSame($('.NewGoodEditor'));
                            }
                        });
                    }
                }
            });
        }
        $('.NewGoodEditor .w-e-text').html('<p><br></p>');

        cancel();

        $('.TitleA a').on('click', function () {
            var index = $(this).html();
            var url = "AnswerQuestion.html?SendAContent=" + index;
            window.open(encodeURI(url));
        });

    });
}

//增加评论、回复 ,href 被评论的用户个人中心链接 name被评论的用户名 rcommentid 被评论的问题id reid被评论的回复id（如果是0，则表示直接评论问题）
function getNewEditor(n, m,href,name,rcommentid,reid) {
    var NewGoodEditor = $('<div class="NewGoodEditor"><div class="NewEditor">' +
        '<div id="Newtoolbar" class="NewToolbar" style="width:100%;background: #fff;border-bottom: 1px solid #DDD;"></div>' +
        '<div id="NewUser_edit" class="EditorNew" style="width:100%;height:200px;display: flex;justify-content: center;' +
        'align-content: center;flex-wrap:wrap;background:#fff;"></div></div></div>');
    CodeSame($('.NewGoodEditor'));
    if (m){
        n.parent().parent().after(NewGoodEditor);
    } else{
        n.parent().after(NewGoodEditor);
    }

    setTimeout(function () {
        $('.NewGoodEditor').css({
            opacity: '1',
            top: '0'
        });
        $('.NewGoodEditor .cancel_A').css({
            display: 'flex'
        });
        $('.NewGoodEditor .publish_A').html('<i class="iconfont">&#xe815;</i>发送');
    }, 10);
    var M = window.wangEditor;
    var NewEditor = new M('#Newtoolbar', '#NewUser_edit');
    NewEditor.customConfig.menus = ['bold', 'italic', 'link', 'emoticon'];
    NewEditor.customConfig.zIndex = 0;
    NewEditor.create();

    cancel();

    improveAreduceZIndex();

    //解决火狐不能自动去除占位符的问题
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isFF = userAgent.indexOf('Firefox') > -1; //判断是否Firefox浏览器
    if (isFF) {
        $('.w-e-text p').eq(0).find('br').css({
            'display': 'none'
        });
    }

    $('.NewGoodEditor .publish_A').on('click', function () {
        var Now = new Date();
        var Year = Now.getFullYear();
        var Month = Now.getMonth() + 1;
        var Day = Now.getDate();
        var Hour = Now.getHours();
        var Minute = Now.getMinutes();
        var Second = Now.getSeconds();
        var ContentNew = $('.NewGoodEditor .w-e-text').html().replace(/<(?!img).*?>/g, "");
        if (ContentNew == '') {
            layer.msg('请您写一点内容再发送，当前状态不可发送');
        } else {
            if (m){
                $.ajax({
                    url:"../forum/addCom?forumid="+FORUMDATA.forum.id+"&rcommentid="+reid+"&content="+ContentNew,
                    type:"put",
                    success:function (data) {
                        console.log("data: "+data);
                        if (data != ""){
                            for(var i in data){
                                if ($('.TwoMiddle span').text() == '添加评论') {
                                    $('.TwoMiddle span').text(1);
                                } else {
                                    $('.TwoMiddle span').text(parseInt($('.TwoMiddle span').text())+1);
                                }
                                console.log("data: "+data);
                                var rUserInfo = "";
                                console.log("reid : "+reid);
                                if (reid != 0){
                                    rUserInfo = '：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="'+href+'">@'+name+'</a> ';
                                }
                                $('.TwoList').append('<div class="insertComment"><span style="display: none" class="ReID">'+i+'</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+USERDATA.id+'">'+USERDATA.nickname+'</a>'+rUserInfo+'</span><p class="TwoSecond">'+data[i]+'</p><div><span class="oneSpanTWO">'+Year+'/'+Month+'/'+Day+'</span><span class="TwoSpanTWO">'+Hour+':'+Minute+':'+Second+'</span></div><div><a href="javascript:;" class="ADDCommit">评论</a><a href="javascript:;" class="DEl">删除</a></div></div>');
                                $('body').getNiceScroll().resize();
                                $('.TwoList .ADDCommit').unbind('click').on('click', function () {
                                    var That = $(this);
                                    statistic_data();
                                    getNewEditor($(this), 1,That.parent().parent().children(".twoUser").children("a").eq(0).href,That.parent().parent().children(".twoUser").children("a").eq(0).text(),"",That.parent().parent().children(".ReID").text());
                                });
                                $('.TwoList .DEl').unbind('click').on('click', function () {
                                    var There = $(this);
                                    layer.confirm('确定要删除此评论吗?', {
                                        btn: ['确定', '取消'], //按钮
                                        title: '提示'
                                    }, function (index) {
                                        layer.close(index);
                                        var Len = parseInt($('.TwoMiddle span').text());
                                        if (Len-1 > 0) {
                                            $('.TwoMiddle span').html((Len-1) + '个评论');
                                        } else {
                                            $('.TwoMiddle a').html('<span>添加评论</span>');
                                        }
                                        $.ajax({
                                            url:"../forum/delCom?forumid="+FORUMDATA.forum.id+"&commentid="+There.parent().parent().children(".ReID").text(),
                                            type:"delete",
                                            success:function (data) {
                                                There.parent().parent().remove();
                                                var list = $(".ReID");
                                                for (var i = 0;i < data.length;i ++){
                                                    console.log(i);
                                                    for(var j = 0;j < list.length;j ++){
                                                        if (list[j].innerText == data[i]){
                                                            list.eq(j).parent().remove();
                                                            break;
                                                        }
                                                    }
                                                }
                                            },error:function () {}
                                        })
                                    });
                                });
                            }
                        }
                    },error:function () {
                        console.log("议题评论回复出错！")
                    }
                })
            } else{
                $.ajax({
                    url:"../reCom",
                    type:"post",
                    data:{"type":"forumRR","objid":rcommentid,"content":ContentNew,"rcommentid":reid},
                    success:function (data) {
                        if (data != ""){
                            for(var i in data){
                                var rUserInfo = "";
                                console.log("reid : "+reid);
                                if (reid != 0){
                                    rUserInfo = '：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="'+href+'">@'+name+'</a> ';
                                }
                                $('.NewGoodEditor').parent().append('<div class="insertComment"><span style="display: none" class="ReID">'+i+'</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+USERDATA.id+'">'+USERDATA.nickname+'</a>'+rUserInfo+'</span><p class="TwoSecond">'+data[i]+'</p><div><span class="oneSpanTWO">'+Year+'/'+Month+'/'+Day+'</span><span class="TwoSpanTWO">'+Hour+':'+Minute+':'+Second+'</span></div><div><a href="javascript:;" class="ADDCommit">评论</a><a href="javascript:;" class="DEl">删除</a></div></div>');
                            }
                            $('body').getNiceScroll().resize();
                            $('.OneList .ADDCommit').unbind('click').on('click', function () {
                                var That = $(this);
                                getNewEditor($(this).parent(), 0,That.parent().parent().children(".twoUser").children("a").eq(0).href,That.parent().parent().children(".twoUser").children("a").eq(0).text(),That.parent().parent().parent().find(".ProID").text(),That.parent().parent().children(".ReID").text());
                                $(this).parent().parent().parent().find('.publish_A').on('click', function () {
                                    if (That.parent().parent().parent().find('.comments-not-or-yes').html() == '还没有讨论') {
                                        That.parent().parent().parent().find('.comments-not-or-yes').html('1条讨论');
                                    } else {
                                        var Len = That.parent().parent().parent().find('.insertComment').length;
                                        That.parent().parent().parent().find('.comments-not-or-yes').html('' + Len + '条讨论');
                                    }
                                });
                            });
                            $('.OneList .DEl').unbind('click').on('click', function () {
                                var There = $(this);
                                layer.confirm('确定要删除此评论吗?', {
                                    btn: ['确定', '取消'], //按钮
                                    title: '提示'
                                }, function (index) {
                                    var Len = There.parent().parent().parent().find('.insertComment').length - 1;
                                    if (Len > 0) {
                                        There.parent().parent().parent().find('.comments-not-or-yes').html('' + Len + '条讨论');
                                    } else {
                                        There.parent().parent().parent().find('.comments-not-or-yes').html('还没有讨论');
                                    }
                                    layer.close(index);
                                    console.log($(this));
                                    $.ajax({
                                        url:"../deleteR?type=forum&docid="+FORUMDATA.forum.id+"&commentid="+There.parent().parent().parent().find(".ProID").text()+"&rcommentid="+There.parent().parent().children(".ReID").text(),
                                        type:"delete",
                                        success:function (data) {
                                            There.parent().parent().remove();
                                            var list = $(".ReID");
                                            for (var i = 0;i < data.length;i ++){
                                                console.log(i);
                                                for(var j = 0;j < list.length;j ++){
                                                    if (list[j].innerText == data[i]){
                                                        list.eq(j).parent().remove();
                                                        break;
                                                    }
                                                }
                                            }
                                        },error:function () {}
                                    })
                                });
                            });
                        }
                    }
                })
            }
       }

        $('.NewGoodEditor .w-e-text').html('<p><br></p>');

        cancel();

    });
}

function CodeSame(n) {
    $('.NewGoodEditor').css({
        opacity: '',
        top: '',
    });
    $('.NewGoodEditor .w-e-text').remove();
    n.remove();
}

function cancel() {
    $('.NewGoodEditor .cancel_A').on('click', function () {
        CodeSame($('.NewGoodEditor'));
    });
}

function addZero(n) {
    if (n < 10) {
        n = '0' + n + '';
    }
    return n;
}

var oLd = $('.middleTopic a').eq(0);
var oLdDiv = $('.NewDIvList').eq(0);
$('.middleTopic a').on('click', function () {
    var index = $('.middleTopic a').index(this); //判断元素在当前的位置是第几个元素
    oLdDiv.css({
        display: 'none'
    });
    oLdDiv = $('.NewDIvList').eq(index);
    $('.NewDIvList').eq(index).css({
        display: 'block'
    });
    oLd.css({
        background: '',
        'color': ''
    });
    oLd = $(this);
    $(this).css({
        background: '#7A023C',
        'color': '#fff'
    });
});

//改变滚条的区域
$(document).on('click', '.commentsAndjoin,.cancel_A,.publish_A,.middleTopic a,.FirstAndJoin,.AndJoin,.ADDCommit,.DEl', function () {
    $('body').getNiceScroll().resize();
});

// 最佳评论
for (var i = 0; i < 5; i++) {
    var BestDiscuss = $('<div class="MainMessageImg ChangeImgMessage"><a href="javascript:;"><img src="../img/11.jpg"></a><div class="owner"><p>邹小强</p>' +
        '<p class="discuss-number">测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字测试文字</p>' +
        '</div></div>');
    $('.bottomRightTopic .Hold').append(BestDiscuss);
}

// 最佳评论的限定字数
// 限定100个字
$('.discuss-number').each(function () {
    var maxwidth = 100;
    if ($(this).text().length > maxwidth) {
        $(this).text($(this).text().substring(0, maxwidth));
        $(this).html($(this).html() + "...");
    }
});

// 检测一个页面中是否存在一个元素
(function ($) {
    $.fn.exist = function () {
        if ($(this).length >= 1) {
            return true;
        }
        return false;
    };
})(jQuery);
// 提高和减低一个元素的层级
function improveAreduceZIndex() {
    $('.hoverSameA5,.hoverSameA17').on('click', function () {
        var This = $(this);
        var timer = null;
        timer = setInterval(function () {
            if ($('.cover_big').exist()) {
                $('.title').css('z-index', 'initial');
                $('.toTop').css('z-index', '-1');
                $('.NewGoodEditor').css('position', 'static');
                $('.NewToolbar').css('z-index', '-1');
                This.parent().parent().parent().parent().parent().css('background', 'transparent');
            } else {
                $('.title').css('z-index', '');
                $('.toTop').css('z-index', '');
                $('.NewGoodEditor').css('position', '');
                $('.NewToolbar').css('z-index', '');
                This.parent().parent().parent().parent().parent().css('background', '');
                clearInterval(timer);
            }
        }, 0);
    });
}
// 加载更多
var JudgeAnimate = true;
$('.loading-more a').on('click', function () {
    if (JudgeAnimate) {
        JudgeAnimate = false;
        var animatedLoading = $('<div class="Load-animated" style="display:flex;padding:0 0 35px 0;"><div class="spinner spinnerTwo"><span></span></div></div>');
        $(this).parent().before(animatedLoading);
        setTimeout(function () {
            animatedLoading.remove();
            JudgeAnimate = true;
        }, 900);
    }
});