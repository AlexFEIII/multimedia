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

$(document).on("click", '.same_a,.user_list a,.FocusList a,.layui-layer-btn0', function () {
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
    $('.all_content .Select').eq(index).css({
        display: 'flex'
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



var Issue = new Array(1);
Issue[0] = "../img/4.jpg";

var Video_list = $('#VideoList');
var article_list = $('.article_list');
var select_Btn = $('<div class="Select_Much" style="display:flex;"></div>');
var get_more_one = $('<div class="contain_a"><a class="contain_span" href="javascript:;"><span class="change_circle">Get More<i class="iconfont">&#xe6c3;</i></span></a></div>');
var get_more_two = $('<div class="contain_a"><a class="contain_span" href="javascript:;"><span class="change_circle">Get More<i class="iconfont">&#xe6c3;</i></span></a></div>');
var get_more_three = $('<div class="contain_a"><a class="contain_span" href="javascript:;"><span class="change_circle">Get More<i class="iconfont">&#xe6c3;</i></span></a></div>');


var answer_list = $('#AnswerList');

var collect_video = $('.collect_video');
var collect_article = $('.collect_article');
var collect_answer = $('.collect_answer');

for (var i = 0; i < 8; i++) {
    var collect_answer_list = $('<div class="issueOutContainer"><div class="ShadowBoxConatiner"><a href="javascript:;"><img src=""><span></span></a><span class="issue"></span></div></div>');
    $('.collect_answer .recommed_topic').append(collect_answer_list);
}

$('.collect_answer .recommed_topic').find("img").attr("src", Issue[0]);
$('.collect_answer .recommed_topic').find(".top_img_span").html("知识产权保卫战");
$('.collect_answer .recommed_topic').find(".issue").html("该议题被浏览 2223661 次");

for (var i = 0; i < 22; i++) {
    var c_video = $('<div class="same_module"><a href="javascript:;"><img src="../img/15.jpg"></a><span>梨视频</span></div>');
    collect_video.append(c_video);
}

$('.same_module a').hover(function () {
    $(this).addClass("a_hover a_hover_a");
}, function () {
    $(this).removeClass("a_hover a_hover_a");
});

var select_Article = $('<div class="Select_Much" style="display:flex;"></div>');

for (var i = 0; i < 7; i++) {
    var wen_list = $('<div class="other_module"><div class="left_part"><a href="javascript:;" class="under_line"></a><p class="draw_text"></p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a"></a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img"><img src=""/></aa></div>');
    select_Article.append(wen_list);
}

collect_article.append(select_Article);

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

//开播设置
$('.inputAndSave a').on('click', function () {
    $(this).css('color', '#AAA');
    //用jq的data方法保存数据，用于判断房间的标题名字是否是已经存在的
    $('#FocusTextForID').data('FocusTextForID', $('#FocusTextForID').val());
}); //点击保存
function InputChange() {
    var doing = false;
    var doSomething = function (e) {
        var WordChange = $('#FocusTextForID').val();
        if (WordChange == '') {
            $('.inputAndSave a').css('color', '#AAA');
        } else {
            //判断标题是否是已经存在的
            if ($('#FocusTextForID').data('FocusTextForID') != WordChange) {
                $('.inputAndSave a').css('color', '#FF5983');
            } else {
                $('.inputAndSave a').css('color', '#AAA');
            }
        }
    }

    // 限制房间标题的字数
    if ($('#FocusTextForID').val().length > 20) {
        $('#FocusTextForID').val($('#FocusTextForID').val().substring(0, 20));
    }
    $('.InputWord span').text('' + $('#FocusTextForID').val().length + '/20');

    //判断input的内容是否改变
    document.getElementById('FocusTextForID').addEventListener('compositionstart', function (e) {
        doing = true;
    }, false);
    document.getElementById('FocusTextForID').addEventListener('input', function (e) {
        if (!doing) {
            doSomething();
        }
    }, false);
    document.getElementById('FocusTextForID').addEventListener('compositionend', function (e) {
        doing = false;
        doSomething();
    }, false);
    //实时判断input里面的内容是否有所改变
}

(function ($) {
    $.fn.exist = function () {
        if ($(this).length >= 1) {
            return true;
        }
        return false;
    };
})(jQuery);

// 直播的封面
(window.onresize = function () {
    var win_height = $(window).height();
    var win_width = $(window).width();
    if (win_width <= 768) {
        $('.tailoring-content').css({
            top: (win_height - $('.tailoring-content').outerHeight()) / 2,
            left: 0,
        });
    } else {
        $('.tailoring-content').css({
            top: (win_height - $('.tailoring-content').outerHeight()) / 2,
            left: (win_width - $('.tailoring-content').outerWidth()) / 2,
        });
    }
})();
if ($('.SelectStudioSetting').exist()) { //判断改页面上是否有开播设置
    InputChange();
    //弹出图片裁剪框
    $('#replaceImg').on('click', function () {
        $('.tailoring-container').toggle();
    });
    //图像上传
    function selectImg(file) {
        if (!file.files || !file.files[0]) {
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            var replaceSrc = evt.target.result;
            //更换cropper的图片
            $('#tailoringImg').cropper('replace', replaceSrc, false); //默认false，适应高度，不失真
        };
        reader.readAsDataURL(file.files[0]);
    }
    //cropper图片裁剪
    $('#tailoringImg').cropper({
        viewMode: 1,
        aspectRatio: 2 / 1,
        preview: '.previewImg', //预览视图
        guides: false, //裁剪框的虚线(九宫格)
        autoCropArea: 0.5, //0-1之间的数值，定义自动剪裁区域的大小，默认0.8
        movable: false, //是否允许移动图片
        dragCrop: true, //是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
        movable: true, //是否允许移动剪裁框
        resizable: true, //是否允许改变裁剪框的大小
        zoomable: false, //是否允许缩放图片大小
        mouseWheelZoom: false, //是否允许通过鼠标滚轮来缩放图片
        touchDragZoom: true, //是否允许通过触摸移动来缩放图片
        rotatable: true, //是否允许旋转图片
        crop: function (e) {
            // 输出结果数据裁剪图像。
        },
    });
    //旋转
    $('.cropper-rotate-btn').on('click', function () {
        $('#tailoringImg').cropper('rotate', 45);
    });
    //复位
    $('.cropper-reset-btn').on('click', function () {
        $('#tailoringImg').cropper('reset');
    });
    //换向
    var flagX = true;
    $('.cropper-scaleX-btn').on('click', function () {
        if (flagX) {
            $('#tailoringImg').cropper('scaleX', -1);
            flagX = false;
        } else {
            $('#tailoringImg').cropper('scaleX', 1);
            flagX = true;
        }
        flagX != flagX;
    });
    //裁剪后的处理
    $('#sureCut').on('click', function () {
        if ($('#tailoringImg').attr('src') == null) {
            return false;
        } else {
            var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
            $('.PreviewDivForCover span').remove();
            $('.PreviewDivForCover').css('border', 'none');
            $('#ImgCoverPreviewForID').addClass('ImgCoverPreview');
            $('#ImgCoverPreviewForID').prop('src', base64url); //显示为图片的形式
            //关闭裁剪框
            closeTailor();
            var animatedLoading = $('<div class="Load-animated"><div class="spinner spinnerTwo"><span></span></div></div>');
            $('.PreviewDivForCover').append(animatedLoading);
            setTimeout(function () {
                animatedLoading.remove();
            }, 2000);
        }
    });
    //关闭裁剪框
    function closeTailor() {
        $('.tailoring-container').toggle();
    }
}

// 4个主要的分类
// 现已取消show
var LastAForHappy = $('.FourKindVideo a').eq(0);
var LastKindPart = $('.kind-part').eq(0);
$('.FourKindVideo a').on('click', function () {
    var index = $('.FourKindVideo a').index(this);
    LastAForHappy.css('color', '');
    LastKindPart.css('display', '');
    LastAForHappy = $(this);
    LastKindPart = $('.kind-part').eq(index);
    $('.kind-part').eq(index).css('display', 'block');
    $(this).css('color', '#FF5983');
});
// 点击选择分类
$('.topKindVideo a').on('click', function () {
    $('.SelectKindFixed').css('display', 'block');
    setTimeout(function () {
        $('.SelectKindFixed').css({
            'top': '50%',
            'opacity': '1'
        });
    }, 20);
});
$('.CancleBtn').on('click', function () {
    $('.SelectKindFixed').css({
        'display': '',
        'top': '',
        'opacity': ''
    });
});
var LastKind = $('.Kind').eq(0);
$('.Kind').on('click', function () {
    $('.topKindVideo span').html($(this).html());
    LastKind.css('background', '');
    LastKind = $(this);
    $(this).css('background', '#C1194E');
    $('.SelectKindFixed').css({
        'display': '',
        'top': '',
        'opacity': ''
    });
});

// 关注的列表
var oLdFocusA = $('.FocusList a').eq(0);
var oLdFocusSame = $('.Focus-Same').eq(0);
$('.FocusList a').on('click', function () {
    var index = $('.FocusList a').index(this);
    oLdFocusA.css({
        'background': ''
    });
    oLdFocusSame.css('display', '');
    oLdFocusSame = $('.Focus-Same').eq(index);
    oLdFocusA = $(this);
    $(this).css({
        'background': '#dbdada'
    });
    $('.Focus-Same').eq(index).css('display', 'flex');
});
for (var i = 0; i < 5; i++) {
    var userBaseInformation = $('<div class="user-base-information"><div class="contain-two-part"><div class="user-head-photo">' +
        '<img src="../img/11.jpg"></div><div class="user-name-focus-article"><a href="javascript:;">wumingzhi111</a><div class="base-message">' +
        '<span class="base-focus">关注</span><span class="base-num">39</span><span class="base-article">文章</span><span class="base-num">527</span>' +
        '</div><div class="simple-introduce"><p>遇见更好的自己</p></div></div></div><div class="focus-btn"><a href="javascript:;" data-focusWhetherOrNot="1"><i class="iconfont">&#xe642;</i>' +
        '<span>已关注</span></a></div></div>');
    $('.AuthorFocus').append(userBaseInformation);
}
// 关注按钮
$('.focus-btn a').on('click', function () {
    if ($(this).attr('data-focusWhetherOrNot') == '1') {
        $(this).attr('data-focusWhetherOrNot', '0');
        $(this).find('.iconfont').html('&#xe604;');
        $(this).find('span').html('关注');
        $(this).addClass('change-color');
    } else {
        $(this).attr('data-focusWhetherOrNot', '1');
        $(this).find('.iconfont').html('&#xe642;');
        $(this).find('span').html('已关注');
        $(this).removeClass('change-color');
    }
});
for (var i = 0; i < 5; i++) {
    var IssueMainContent = $('<div class="Issue-main-content"><div class="photp-title"><a href="topicContent.html" target="_blank"><img src="../img/newPeople.jpg"></a><p>职场新人须知</p>' +
        '</div><div class="introduce-for-issue"><p>盛夏，骄阳和暴雨在天空轮番控场，捉摸不定，路人衣衫被淋透又晒干，有点像初入职场的你，在「毕业生」和' +
        '「职场新人」角色切换中无所适从的样子。要如何才能快速脱掉身上的「学生气」？工作中人际关系怎么处理？提升工作效率有什么方法？本期圆桌，一起来聊聊初入' +
        '职场会面临的种种疑惑。</p></div><div class="main-host-institution"><i class="iconfont">&#xe60c;</i><span>组织单位</span><span>知乎圆桌</span>' +
        '</div><div class="problem-Focus-person"><div class="problem-num"><a href="topicContent.html" target="_blank"><span>问题</span><span>2</span>' +
        '</div><div class="Focus-person-num"><a href="topicContent.html" target="_blank"><span>关注者</span><span>52</span></a></div></div>' +
        '<div class="focus-whether-not"><a href="javascript:;" data-Whether="1">已关注</a></div></div>');
    $('.IssueFocus').append(IssueMainContent);
}
$('.focus-whether-not a').on('click', function () {
    if ($(this).attr('data-Whether') == '1') {
        $(this).attr('data-Whether', '0');
        $(this).css('background', '#C1194E');
        $(this).html('关注议题');
    } else {
        $(this).attr('data-Whether', '1');
        $(this).css('background', '');
        $(this).html('已关注');
    }
});
$('.introduce-for-issue p').each(function () {
    var maxwidth = 78;
    if ($(this).text().length > maxwidth) {
        $(this).text($(this).text().substring(0, maxwidth));
        $(this).html($(this).html() + "...");
    }
});
for (var i = 0; i < 5; i++) {
    var problemContent = $('<div class="problem-content"><a href="AnswerQuestion.html" target="_blank" class="skip-page">' +
        '林丹用左手打球对他的成功到底有没有影响？</a><div class="time-answer-focus-num"><span>2018-07-21</span><span>&nbsp;·&nbsp;</span>' +
        '<span>13个回答</span><span>&nbsp;·&nbsp;</span><span>60个关注</span></div><a href="javascript:;" class="stay-right">' +
        '<i class="iconfont">&#xe622;</i></a></div>');
    $('.problemFocus').append(problemContent);
}

// cancel-focus-btn
for (var i = 0; i < 5; i++) {
    var courseMainContent = $('<div class="course-main-content"><img src="../img/11.jpg"><div class="title-introduce-article-focus-num">' +
        '<a href="javascript:;">自由财务评论</a><p>霞乃云魂魄 蜂是花精神</p><span>共</span><span>43</span><span>篇文章</span><span>·</span>' +
        '<span>8432人关注</span></div><a href="javascript:;" class="cancel-focus-btn"><i class="iconfont">&#xe622;</i></a></div>');
    $('.courseFocus').append(courseMainContent);
}

$('.stay-right,.cancel-focus-btn').on('click', function () {
    var This = $(this);
    layer.confirm('确定要取消关注吗?', {
        btn: ['确定', '取消'], //按钮
        title: '提示',
    }, function (index) {
        This.parent().remove();
        layer.close(index);
    });
});
for (var i = 0; i < 5; i++) {
    var liveThreePart = $('<div class="live-three-part"><div class="live-message"><div class="photo-title-focus-btn">' +
        '<a href="livePage.html" target="_blank" class="img-a"><img src="../img/11.jpg"></a><div><p>养乐多了</p>' +
        '<a href="javascript:;" class="btn-whether-focus" data-btn-focus="1">取消关注</a></div></div><div class="live-room-status"><p>直播间</p>' +
        '<a href="livePage.html" target="_blank" class="status">直播中</a></div></div></div>');
    $('.all-live-content').append(liveThreePart);
}
$('.btn-whether-focus').on('click', function () {
    if ($(this).attr('data-btn-focus') == '1') {
        $(this).attr('data-btn-focus', '0');
        $(this).addClass('change-color-background');
        $(this).html('关注');
    } else {
        $(this).attr('data-btn-focus', '1');
        $(this).removeClass('change-color-background');
        $(this).html('取消关注');
    }
});

// 关注视频
var videoFocus = $('.videoFocus');
for (var i = 0; i < 12; i++) {
    var videoFocusOne = $('<div class="same_module"><a href="javascript:;"><i class="Delect-Video iconfont">&#xe624;</i></a><span>梨视频</span></div>');
    videoFocus.append(videoFocusOne);
}

// 移过视频的效果
$('.same_module a').hover(function () {
    $(this).addClass("a_hover a_hover_a Delect-show");
}, function () {
    $(this).removeClass("a_hover a_hover_a Delect-show");
});
// 删除按钮
$('.Delect-Video').on('click', function () {
    var This = $(this);
    layer.confirm('确定要删除此视频吗?', {
        btn: ['确定', '取消'], //按钮
        title: '提示',
    }, function (index) {
        This.parent().parent().remove();
        layer.close(index);
    });
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

var firstul = $(".first_ul");
var docul = $(".user_list").children("ul");
//标志位，标记是否已经请求
var vf = false;
var dif = false,dlf = false,dmf = false,def = false,dhf = false,dsf = false,daf = false;
var ff = false;
var hf = false;
var cvf = false,cdf = false,cff = false;
//页面加载的时候执行点击事件
$(document).ready(function () {
    //加载视频
    firstul.children("li:eq(0)").click();
    //判断是否已经登录
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
firstul.children("li:eq(0)").click(function () {
    if (vf == false){
        $.ajax({
            url:"../video/mine",
            type:"get",
            success:function (data) {
                vf = true;
                for (var i = 0;i < data.length;i ++){
                    var select_one = $('<div class="same_module"><a href="javascript:;"><i class="Delect-Video iconfont">&#xe624;</i></a><span>'+data[i].video.title+'</span></div>');
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
    $.ajax({
        url:"../doc/mine/"+type,
        type:"get",
        success:function (data) {
            console.log(data);
            var username;
            for (var i = 0;i < data.length;i ++){
                username = data[i].mulUser.nickname;
                var image = "";
                if (username == null) username = data[i].mulUser.username;
                if (data[i].document.image != null) image = '<img src="'+data[i].document.image+'"/>';
                var AddDiv = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data[i].document.id+'" target="_blank" class="under_line">'+data[i].document.title+'</a><p class="draw_text">'+data[i].document.summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'<div class="Delect-article"><i class="iconfont">&#xe622;</i></div></a></div>')
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
            url:"../forum/mine",
            type:"get",
            success:function (data) {
                ff = true;
                console.log(data);
                for (var i = 0;i < data.length;i ++){
                    var image = "../img/14.png";
                    if (data[i].image != null) image = data[i].image;
                    var five_block = $('<div class="issueOutContainer"><div class="ShadowBoxConatiner"><a href="topicContent.html?id="'+data[i].id+'><img src="'+image+'"><span>'+data[i].title+'</span></a><span class="issue">该议题被浏览'+data[i].sawnum+'次</span><div class="Delect-question"><i class="iconfont Del-this">&#xe622;</i><br><i class="iconfont enter-this">&#xe650;</i></div></div></div>');
                    $('.Select_Three_Div .recommed_topic').append(five_block);
                }
            },error:function (data) {
                //ignore
            }
        });
    }
});
firstul.children("li:eq(3)").click(function () {
    if (hf == false){
        var historyVF = false;
        $.ajax({
            url:"../history/video",
            type:"get",
            async:false,
            success:function (data) {
                var num = 15,count = data.length;
                if (count < 15) num = count;
                for (var i = 0;i < num;i ++){
                    var v_list = $('<div class="same_module"><a href="javascript:;"><i class="Delect-Video iconfont">&#xe624;</i></a><span>'+data[i].title+'</span></div>');
                    $("#VideoBox").append(v_list);
                }
                if (count > 15){
                    Video_list.append('<div id="pagingOne" class="pagingTool"></div>');
                    $('#pagingOne').Paging({
                        pagesize: 15,
                        count:data.length,
                        prevTpl: '<i class="iconfont">&#xe78c;</i>',
                        nextTpl: '<i class="iconfont">&#xe77c;</i>',
                        firstTpl: '<i class="iconfont">&#xe609;</i>',
                        lastTpl: '<i class="iconfont">&#xe6de;</i>',
                        callback:function (page,size,count) {
                            console.log("num: "+page);
                            $("#VideoBox").empty();
                            var n = page*15-1;
                            if (data.length-1 < n) n=data.length-1;
                            for (var i = (page-1)*15;i <= n;i ++){
                                var v_list = $('<div class="same_module"><a href="javascript:;"><img src="'+data[i].image+'"><i class="Delect-Video iconfont">&#xe624;</i></a><span>'+data[i].title+'</span></div>');
                                $("#VideoBox").append(v_list);
                            }
                        }
                    });
                    $('#pagingTwo').children("li").on('click', function () {
                        $('#'+name).css({
                            '-webkit-user-select': 'none',
                            '-moz-user-select': 'none',
                            '-ms-user-select': 'none',
                            'user-select': 'none'
                        });
                    });
                }else{
                    Video_list.append('<div class="contain_a" style="padding-bottom: 20px;"><span style="text-align: center;color: #8b8c8b;">——您当前仅浏览过这些视频——</span></div>')
                }
            },error:function (data) {
                //ignore
            }
        });
        Video_list.append(get_more_one);
        $.ajax({
            url:"../history/doc",
            type:"get",
            async:false,
            success:function (data) {
                var username,num = 8;
                if (data.length < 8) num = data.length;
                for (var i = 0;i < num;i ++){
                    username = data[i].mulUser.nickname;
                    var image = "";
                    if (username == null) username = data[i].mulUser.username;
                    if (data[i].document.image != null) image = '<img src="'+data[i].document.image+'"/>';
                    var a_list = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data[i].document.id+'" target="_blank" class="under_line">'+data[i].document.title+'</a><p class="draw_text">'+data[i].document.summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'<div class="Delect-article"><i class="iconfont">&#xe622;</i></div></a></div>');
                    select_Btn.append(a_list);
                }
                article_list.append(select_Btn);
                if (data.length > 8) {
                    article_list.append('<div id="pagingTwo" class="pagingTool"></div>');
                    $('#pagingTwo').Paging({
                        pagesize: 8,
                        count:data.length,
                        prevTpl: '<i class="iconfont">&#xe78c;</i>',
                        nextTpl: '<i class="iconfont">&#xe77c;</i>',
                        firstTpl: '<i class="iconfont">&#xe609;</i>',
                        lastTpl: '<i class="iconfont">&#xe6de;</i>',
                        callback:function (page,size,count) {
                            console.log("num: "+page);
                            select_Btn.empty();
                            var n = page*8-1;
                            if (data.length-1 < n) n=data.length-1;
                            for (var i = (page-1)*8;i <= n;i ++){
                                var a_list = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data[i].document.id+'" target="_blank" class="under_line">'+data[i].document.title+'</a><p class="draw_text">'+data[i].document.summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+username+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'<div class="Delect-article"><i class="iconfont">&#xe622;</i></div></a></div>');
                                select_Btn.append(a_list);
                            }
                        }
                    });
                    $('#pagingTwo').children("li").on('click', function () {
                        $('#'+name).css({
                            '-webkit-user-select': 'none',
                            '-moz-user-select': 'none',
                            '-ms-user-select': 'none',
                            'user-select': 'none'
                        });
                    });
                }else{
                    article_list.append('<div class="contain_a" style="padding-bottom: 20px;"><span style="text-align: center;color: #8b8c8b;">——您当前仅浏览过这些文章——</span></div>')
                }
            },error:function () {

            }
        });
        article_list.append(get_more_two);
        var historyF = false;
        $.ajax({
            url:"../history/forum",
            type:"get",
            async: false,
            success:function (data) {
                var image,num = 18;
                if (data.length < 18) num = data.length;
                for (var i = 0;i < num;i ++){
                    image = "../img/14.png";
                    if (data[i].forum.image != null) image = data[i].forum.image;
                    var w_list = $('<div class="issueOutContainer"><div class="ShadowBoxConatiner"><a href="javascript:;"><img src="'+image+'"><span>'+data[i].forum.title+'</span></a><span class="issue">该议题被浏览 '+data[i].forum.sawnum+' 次</span><div class="Delect-question"><i class="iconfont Del-this">&#xe622;</i><br><i class="iconfont enter-this">&#xe650;</i></div></div></div>');
                    answer_list.append(w_list);
                }
                if (data.length > 18) {
                    answer_list.append('<div id="pagingThree" class="pagingTool"></div>');
                    $('#pagingThree').Paging({
                        pagesize: 18,
                        count:data.length,
                        prevTpl: '<i class="iconfont">&#xe78c;</i>',
                        nextTpl: '<i class="iconfont">&#xe77c;</i>',
                        firstTpl: '<i class="iconfont">&#xe609;</i>',
                        lastTpl: '<i class="iconfont">&#xe6de;</i>',
                        callback:function (page,size,count) {
                            console.log("num: "+page);
                            $("#AnswerBox").empty();
                            var n = page*18-1;
                            if (data.length-1 < n) n=data.length-1;
                            for (var i = (page-1)*18;i <= n;i ++){
                                var w_list = $('<div class="issueOutContainer"><div class="ShadowBoxConatiner"><a href="javascript:;"><img src="'+image+'"><span>'+data[i].forum.title+'</span></a><span class="issue">该议题被浏览 '+data[i].forum.sawnum+' 次</span></div></div>');
                                $("#AnswerBox").append(v_list);
                            }
                        }
                    });
                    $('#pagingTwo').children("li").on('click', function () {
                        $('#'+name).css({
                            '-webkit-user-select': 'none',
                            '-moz-user-select': 'none',
                            '-ms-user-select': 'none',
                            'user-select': 'none'
                        });
                    });
                }else{
                    answer_list.append('<div class="contain_a" style="padding-bottom: 20px;margin:0;"><span style="text-align: center;color: #8b8c8b;">——您当前仅浏览过这些问答——</span></div>')
                }
            },error:function () {

            }
        });
        if (historyF){
            getPaging("pagingThree")
        }
        answer_list.append(get_more_three);
        hf = true;
    }
});
// //显示收藏的视频
// firstul.children("li:eq(4)").click(function () {
//     if (cvf == false){
//         $.ajax({
//             url:"../col/mine?kind=video",
//             type:"get",
//             success:function (data) {
//                 cvf = true;
//                 console.log(data);
//                 for (var i = 0;i < data.length;i ++){
//                     var select_one = $('<div class="same_module"><a href="javascript:;"><img src="'+data[i].video.image+'"></a><span>'+data[i].video.title+'</span></div>');
//                     Select_One_Div.append(select_one);
//                 }
//             },error:function (data) {
//                 console.log("获取收藏视频失败！")
//             }
//         });
//     }
// });
// //显示收藏的文章
// $(".second_list").eq(1).click(function () {
//     if (!cdf){
//         $.ajax({
//             url:"../col/mine?kind=doc",
//             type:"get",
//             success:function (data) {
//                 cdf = true;
//                 console.log(data)
//             },error:function () {
//                 console.log("获取收藏文章失败！")
//             }
//         })
//     }
// });
// //显示收藏的问答
// $(".second_list").eq(2).click(function () {
    // if (!cff){
    //     $.ajax({
    //         url:"../col/mine?kind=forum",
    //         type:"get",
    //         success:function (data) {
    //             cff = true;
    //             console.log(data)
    //         },error:function () {
    //             console.log("获取收藏问答失败！")
    //         }
    //     })
    // }
// });

//登录成功执行的方法
function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"></a><div class="msg_index_dance">进入个人中心</div></div><div class="editor_article"><a href="preset.html" target="_blank"><span><i class="iconfont">&#xe645;</i></span>写文章</a><a href="javascript:;" target="_blank" class="editor-first-a">发布</a><div class="three-part-for-article-video-issue"><a href="javascript:;" data-href="preset.html">发布文章</a><a href="javascript:;" data-href="UploadVideo.html">发布视频</a><a href="javascript:;" data-href="createIssue.html">发布议题</a></div></div>');
    var touphoto = $(".contain_tou_photo");
    touphoto.children("a").children("img").attr("src",image);
    var nickname = data.nickname;
    if (data.nickname == null) nickname = "您尚未设置昵称";
    var address = data.address;
    if (data.address == null) address = "您尚未设置地址";
    var personality = data.personality;
    if (data.personality == null) personality = "您尚未设置个人签名" ;
    touphoto.children("div").children("span").text(nickname);
    touphoto.children("div").children("p:eq(0)").text(address);
    touphoto.children("div").children("p:eq(1)").text(personality);
}

// 删除文章
$('.Delect-article').on('click', function () {
    var This = $(this);
    layer.confirm('确定要删除此文章吗?', {
        btn: ['确定', '取消'], //按钮
        title: '提示',
    }, function (index) {
        This.parent().parent().remove();
        layer.close(index);
    });
});

$('.ShadowBoxConatiner').hover(function () {
    $(this).find('.Delect-question').css('opacity', '1');
}, function () {
    $(this).find('.Delect-question').css('opacity', '');
});
$('.Del-this').on('click', function () {
    var This = $(this);
    layer.confirm('确定要删除此问答吗?', {
        btn: ['确定', '取消'], //按钮
        title: '提示',
    }, function (index) {
        This.parent().parent().parent().remove();
        layer.close(index);
    });
});
// 进入问答
// $('.enter-this').on('click', function () {
// });

