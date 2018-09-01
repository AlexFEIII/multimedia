var oLdA = $('.nav .same_a').eq(0);
$('.nav .same_a').on('click', function () {
    oLdA.css({
        "border-bottom": "",
    });
    oLdA = $(this);
    $(this).css({
        "border-bottom": "2px solid #1A2D27",
    });
});

$(document).on("click", '.same_a,.user_list a,.second_list a', function () {
    $("body").getNiceScroll().resize();
});
//通过class绑定click事件，可以只点击一次就触发事件，否则需要点击两次

var Select_One_Div = $('.Select_One_Div');

var oLd_div = $('.all_content .Select').eq(0);

$('.nav .same_a').on('click', function () {
    var index = $('.nav .same_a').index(this); //判断元素在当前的位置是第几个元素
    oLd_div.css({
        display: 'none',
    });
    oLd_div = $('.all_content .Select').eq(index);
    $('.all_content .Select')
        .eq(index)
        .css({
            display: 'flex',
        });
    $('.lastLong').css({
        'display': 'none',
    });
});

var length = $('.user_list a').length;
var Select_Two_Div = $('.Select_Two_Div');

for (var i = 0; i < length; i++) {
    var select_two = $('<div class="Select_Much"></div>');
    Select_Two_Div.append(select_two);
}

$('.Select_Much').eq(0).css({
    "display": "block",
});

var oLd = $('.user_list a').eq(0);
var oLdDiv = $('.Select_Much').eq(0);
$('.user_list a').on('click', function () {
    var index = $('.user_list a').index(this); //判断元素在当前的位置是第几个元素
    oLdDiv.css({
        display: 'none',
    });
    oLdDiv = $('.Select_Much').eq(index);
    $('.Select_Much')
        .eq(index)
        .css({
            display: 'block',
        });
    oLd.css({
        background: '',
    });
    oLd = $(this);
    $(this).css({
        background: '#dbdada',
    });
    //清空上一个的属性，给当前的元素添加属性
});
//左边书籍的列表

var SelectDiv = $('.Select_Much');

var ImgArray = new Array(7);

// SelectDiv.eq().find("img").attr("src", ImgArray[]);
// SelectDiv.eq().find(".under_line").html("");
// SelectDiv.eq().find(".bottom_first_a").html("");
// SelectDiv.eq().find(".bottom_two_a").append("<b></b>");
// SelectDiv.eq().find(".bottom_first_span").append("<b></b>");
// SelectDiv.eq().find(".bottom_two_span").append("<b></b>");
// SelectDiv.eq().find("p").html("");
//模板


$(function () {
    $(".draw_text").each(function () {
        var maxwidth = 72;
        if ($(this).text().length > maxwidth) {
            $(this).text($(this).text().substring(0, maxwidth));
            $(this).html($(this).html() + "...");
        };
    });
    //限制字数，超过显示...
});

$('.same_module a').hover(function () {
    $(this).addClass("a_hover a_hover_a");
}, function () {
    $(this).removeClass("a_hover a_hover_a");
});

var select_Article = $('<div class="Select_Much" style="display:flex;"></div>');

select_Article.eq(0).find("img").attr("src", ImgArray[0]);
select_Article.eq(0).find(".under_line").html("五一，差点只剩半条命！");
select_Article.eq(0).find(".bottom_first_a").html("5312Ana");
select_Article.eq(0).find(".bottom_two_a").append("<b>20</b>");
select_Article.eq(0).find(".bottom_first_span").append("<b>19</b>");
select_Article.eq(0).find(".bottom_two_span").append("<b>1</b>");
select_Article.eq(0).find("p").html("原本打算五一跟朋友跑完半程马拉松后就去北海拍海景，然而不幸的是，她准备跑到终点时突然晕倒了，虽然我没体验过这种晕倒的感觉，但可以想象出这种从鬼门关出来人的有多不易。");

$('.nav .same_a').eq(4).on('click', function () {
    $('.lastLong').css({
        'display': 'block',
    });
});

var OldElement = $('.collect').eq(0);

$('.second_list a').on('click', function () {
    var index = $('.second_list a').index(this);
    OldElement.css({
        'display': 'none',
    });
    OldElement = $('.collect').eq(index);
    $('.collect').eq(index).css({
        'display': 'flex',
    });
});

var firstul = $(".first_ul");
var docul = $(".user_list").children("ul");
//标志位，标记是否已经请求
var vf = false;
var dif = false,dlf = false,dmf = false,def = false,dhf = false,dsf = false,daf = false;
var ff = false;
//页面加载的时候执行点击事件
$(document).ready(function () {
    //加载视频
    firstul.children("li:eq(0)").click();
    //加载该页用户信息
    $.ajax({
        url:"../user"+window.location.search,
        type:"get",
        success:function (data) {
            loginSuccess(data)
        },error:function () {
            console.log("获取用户信息失败！")
        }
    })
});
firstul.children("li:eq(0)").click(function () {
    if (vf == false){
        $.ajax({
            url:"../video/others"+window.location.search,
            type:"get",
            success:function (data) {
                vf = true;
                for (var i = 0;i < data.length;i ++){
                    var select_one = $('<div class="same_module"><a href="javascript:;"><img src="'+data[i].video.image+'"></a><span>'+data[i].video.title+'</span></div>');
                    Select_One_Div.append(select_one);
                }
            },error:function (data) {
                //ignore
            }
        });
    }
});
firstul.children("li:eq(1)").click(function () {
    if (dif == false){
        dif = getMineDoc("internet",0);
    }
});
docul.children("li:eq(1)").click(function () {
    if (dlf == false){
        dlf = getMineDoc("law",1)
    }
});
docul.children("li:eq(2)").click(function () {
    if (dmf == false){
        dmf = getMineDoc("medicine",2)
    }
});
docul.children("li:eq(3)").click(function () {
    if (def == false){
        def = getMineDoc("economy",3)
    }
});
docul.children("li:eq(4)").click(function () {
    if (dhf == false){
        dhf = getMineDoc("history",4)
    }
});
docul.children("li:eq(5)").click(function () {
    if (dsf == false){
        dsf = getMineDoc("science",5)
    }
});
docul.children("li:eq(6)").click(function () {
    if (daf == false){
        daf = getMineDoc("art",6)
    }
});
function getMineDoc(type,num) {
    console.log(window.location.search);
    $.ajax({
        url:"../doc/others/"+type+window.location.search,
        type:"get",
        success:function (data) {
            console.log(data);
            var username;
            for (var i = 0;i < data.length;i ++){
                username = data[i].mulUser.nickname;
                var image = "";
                if (username == null) username = data[i].mulUser.username;
                if (data[i].document.image != null) image = '<img src="'+data[i].document.image+'"/>';
                var AddDiv = $('<div class="other_module"><div class="left_part"><a href="article.html?id='+data[i].document.id+'" target="_blank" class="under_line">'+data[i].document.title+'</a><p class="draw_text">'+data[i].document.summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'</aa></div>')
                SelectDiv.eq(num).append(AddDiv);
            }
            return true;
        },error:function (data) {
            //ignore
            return false;
        }
    });
}
firstul.children("li:eq(2)").click(function () {
    if (ff == false){
        $.ajax({
            url:"../forum/others"+window.location.search,
            type:"get",
            success:function (data) {
                ff = true;
                for (var i = 0;i < data.length;i ++){
                    var image = "../img/14.png";
                    if (data[i].forum.image != null) image = data[i].forum.image;
                    var five_block = $('<div class="issueOutContainer"><div class="ShadowBoxConatiner"><a href="javascript:;"><img src="'+image+'"><span>'+data[i].forum.title+'</span></a><span class="issue"></span></div></div>');
                    $('.recommed_topic').append(five_block);
                }
            },error:function (data) {
                //ignore
            }
        });
    }
});

function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"></a><div class="msg_index_dance">进入个人中心</div></div><div class="editor_article"><a href="preset.html" target="_blank"><span><i class="iconfont">&#xe645;</i></span>写文章</a><a href="javascript:;" target="_blank" class="editor-first-a">发布</a><div class="three-part-for-article-video-issue"><a href="javascript:;" data-href="preset.html">发布文章</a><a href="javascript:;" data-href="UploadVideo.html">发布视频</a><a href="javascript:;" data-href="createIssue.html">发布议题</a></div></div>');
    var touphoto = $(".contain_tou_photo");
    touphoto.children("a").children("img").attr("src",image);
    var nickname = data.nickname;
    if (data.nickname == null) nickname = "Ta尚未设置昵称";
    var address = data.address;
    if (data.address == null) address = "Ta尚未设置地址";
    var personality = data.personality;
    if (data.personality == null) personality = "Ta尚未设置个人签名" ;
    touphoto.children("div").children("span").text(nickname);
    touphoto.children("div").children("p:eq(0)").text(address);
    touphoto.children("div").children("p:eq(1)").text(personality);
}