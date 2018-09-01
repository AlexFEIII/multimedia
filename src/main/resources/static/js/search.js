$(document).on("click", '.leftParts a,.navParts a', function () {
    $("body").getNiceScroll().resize();
});

var Old = $('.leftParts a').eq(0);
var OldParts = $('.Parts').eq(0);
$('.leftParts .topA').on('click', function () {
    var index = $('.leftParts a').index(this); //判断元素在当前的位置是第几个元素
    OldParts.css({
        'display': 'none'
    });
    OldParts = $('.Parts').eq(index);
    $('.Parts').eq(index).css({
        'display': 'flex'
    });
    Old.css({
        'background': ''
    });
    Old = $(this);
    $(this).css({
        'background': '#dbdada'
    });
});

var firstPart = $('.firstPart');

var secondPart = $('.secondPart');

$('.same_module a').hover(function () {
    $(this).addClass("a_hover a_hover_a");
}, function () {
    $(this).removeClass("a_hover a_hover_a");
});

var length = $('.navParts a').length;
var thirdPart = $('.thirdPart');

for (var i = 0; i < length; i++) {
    var select_two = $('<div class="Select_Much"></div>');
    thirdPart.append(select_two);
}

$('.Select_Much').eq(0).css({
    "display": "block",
});

var olDNav = $('.navParts a').eq(0);
var oLdDiv = $('.Select_Much').eq(0);
$('.navParts a').on('click', function () {
    var index = $('.navParts a').index(this);
    oLdDiv.css({
        display: 'none',
    });
    oLdDiv = $('.Select_Much').eq(index);
    $('.Select_Much').eq(index).css({
        display: 'block',
    });
    olDNav.css({
        'background': '',
        'color': '',
    });
    olDNav = $(this);
    $(this).css({
        'background': '#C9CABB',
        'color': '#34352C',
    });
});

var SelectDiv = $('.Select_Much');

var HistoryPast = $('<div class="historySearch"><div class="lastContent"><p>最近搜索</p><a href="javascript:;" class="ClearAll">' +
    '清空</a><ul class="lastTime"><li><a href="javascript:;" class="bottomA"><i class="iconfont">&#xe6c0;</i>' +
    '<p class="IBottom">是什么造就了《三生三世十里桃花》的火爆？</p><span class="HiddenSpan"><i class="iconfont">&#xe61a;</i>' +
    '</span></a></li></ul></div></div>');
$('.leftParts').append(HistoryPast);

//控制文字的多少

CutWordSearch('.IBottom', 12);

CutWordSearch('.draw_text', 72);

function CutWordSearch(n, num) {
    $(n).each(function () {
        var maxwidth = num;
        if ($(this).text().length > maxwidth) {
            $(this).text($(this).text().substring(0, maxwidth));
            $(this).html($(this).html() + "...");
        };
    });
}


//清空按钮
$('.ClearAll').on('click', function () {
    $('.historySearch').empty();
    $('.historySearch').remove();
});

//删除按钮，当只有一个li时与清空的操作时一样的
$('.HiddenSpan').on('click', function () {
    var length = $('.bottomA').length;
    if (length == 1) {
        $('.historySearch').empty();
        $('.historySearch').remove();
    } else {
        $(this).parent().parent().remove();
    }
});

// 设置图片的高度
function SetImgHeight() {
    var cutHalf = (parseFloat($('.same_module a').css('width'))) / 2;
    $('.same_module a').css('height', cutHalf);
}
// 设置图片的高度
$(function () {
    SetImgHeight();
});
// 设置图片的高度
$(window).resize(function () {
    SetImgHeight();
});

var content = window.location.search;
var lf = false,vf = false,df = false,ff = false;
$(document).ready(function () {
   // console.log("content: "+content);
   //  $.ajax({
   //      url:"../search/video"+content,
   //      type:"get",
   //      success:function (data) {
   //          console.log(data);
   //          for (var i = 0;i < data.length;i ++){
   //              var select_one = $('<div class="same_module"><a href="javascript:;"></a><span>'+data[i].title+'</span></div>');
    //              firstPart.append(select_one);
   //          }
   //      }
   //  })
});
var clickUl = $(".leftParts ul");
clickUl.children("li").eq(1).click(function () {
    if (!vf){
        $.ajax({
            url:"../search/video"+content,
            type:"get",
            success:function (data) {
                vf = true;
                for (var i in data){
                    var select_one = $('<div class="same_module"><a href="javascript:;"></a><span>'+data[i].title+'</span></div>');
                    secondPart.append(select_one);
                }
            }
        })
    }

});
clickUl.children("li").eq(2).click(function () {
    if (!df){
        $.ajax({
            url:"../search/doc"+content,
            type:"get",
            success:function (data) {
                console.log(data);
                df = true;
                var obj,img,username;
                for (var i in data){
                    if (data[i].kind == "internet") obj = 0;
                    else if (data[i].kind == "law") obj = 1;
                    else if (data[i].kind == "medicine") obj = 2;
                    else if (data[i].kind == "economy") obj = 3;
                    else if (data[i].kind == "history") obj = 4;
                    else if (data[i].kind == "science") obj = 5;
                    else obj = 6;
                    img = "";
                    username = data[i].nickname;
                    if (username == null) username = data[i].nickname;
                    try {
                        if (data[i].image != null) image = '<img src="'+data[i].image+'"/>';
                    }catch (e) {
                        //ignore
                    }
                    var AddDiv = $('<div class="other_module"><div class="left_part"><a href="article.html?id='+data[i].id+'" class="under_line">'+data[i].title+'</a><p class="draw_text">'+data[i].summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data[i].commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data[i].upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+img+'</aa></div>');
                    SelectDiv.eq(obj).append(AddDiv);
                }
            }
        })
    }

});
clickUl.children("li").eq(3).click(function () {
    if (!ff){
        $.ajax({
            url:"../search/forum"+content,
            type:"get",
            success:function (data) {
                console.log(data);
                ff = true;
                var obj,img,username;
                for (var i in data){
                    img = "../img/14.png";
                    username = data[i].nickname;
                    if (username == null) username = data[i].nickname;
                    try {
                        if (data[i].image != null) image = data[i].image;
                    }catch (e) {
                        //ignore
                    }
                    var five_block = $('<div class="issueOutContainer"><div class="ShadowBoxConatiner"><a href="javascript:;"><img src="'+image+'"><span>'+data[i].title+'</span></a><span class="issue">该议题被浏览 '+data[i].sawnum+' 次</span></div></div>');
                    $('.recommed_topic').append(five_block);
                }
            }
        })
    }

});

$(document).ready(function () {
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
