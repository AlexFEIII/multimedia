var USERDATA = "";
var PRODATA = "";
$(document).ready(function () {
    $.ajax({
        url:"../user/isLogin",
        type:"get",
        async:false,
        success:function (data) {
            console.log(data);
            if (data != ""){
                USERDATA = data;
                loginSuccess(data);
            }
        }
    });

    $.ajax({
        url:"../getOnePro"+window.location.search,
        type:"get",
        async:false,
        success:function (data) {
            console.log(data);
            PRODATA = data.forumProblem;
            if (data.colnum != 0){
                $(".OneFirstFocus span").text(i)
            }
            if (data.col){
                $(".FocusQuestion").html('已关注');
                $(".FocusQuestion").css({
                    'background': '#C1194E'
                });
            }
            if (USERDATA != ""){
                $(".leftColumns a").eq(1).attr("href","preset.html?type=forum&proid="+PRODATA.id);
            }
            //写问答添加链接
            $(".leftColumnsH1").text(data.forumProblem.title);
            if (data.forumProblem.content != null){
                $(".wordP").text(data.forumProblem.content);
            }
            $(".TwoCollectionArticle").text("收集了"+data.docnum+"篇文章");
            if (data.forumProblem.image != null){
                $("#finalImg").attr("src",data.forumProblem.image)
            }
            if (data.forumProblem.userid != USERDATA.id){
                $("#replaceImg").remove();
                $("#editor-word").remove();
                $(".tailoring-container").remove();
                $(".editor-question-word").remove();
            }
            var firstName;
            for (var i = 0;i < data.docType.docUserViews.length;i ++){
                firstName = data.docType.docUserViews[i].mulUser.nickname;
                var image = "";
                if (firstName == null) firstName = data.docType.docUserViews[i].mulUser.username;
                var summary = "作者尚未增加内容";
                if ( data.docType.docUserViews[i].document.summary != null){
                    summary = data.docType.docUserViews[i].document.summary;
                }
                if (data.docType.docUserViews[i].document.image != null) image = '<img src="'+data.docType.docUserViews[i].document.image+'"/>';
                var AddDiv = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data.docType.docUserViews[i].document.id+'" target="_blank" class="under_line">'+data.docType.docUserViews[i].document.title+'</a><p class="draw_text">'+summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+firstName+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data.docType.docUserViews[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data.docType.docUserViews[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'</aa></div>');
                SelectDiv.append(AddDiv);
            }
            if (data.docType.totalPage > 1){
                $(".Select_Much").after('<div class="pagingTool"></div>');
                $(".pagingTool").Paging({
                    pagesize: 1,
                    count:data.docType.totalPage,
                    prevTpl: '<i class="iconfont">&#xe78c;</i>',
                    nextTpl: '<i class="iconfont">&#xe77c;</i>',
                    firstTpl: '<i class="iconfont">&#xe609;</i>',
                    lastTpl: '<i class="iconfont">&#xe6de;</i>',
                    callback:function (page,size,count) {
                        console.log("num: "+page);
                        $('.commentsList').empty();
                        $.ajax({
                            url:"../doc?type=forum&pagenum="+page,
                            type:"get",
                            success:function (data) {
                                $(".Select_Much").empty();
                                var firstName;
                                for (var i = 0;i < data.docType.docUserViews.length;i ++){
                                    firstName = data.docType.docUserViews[i].mulUser.nickname;
                                    var image = "";
                                    if (firstName == null) firstName = data.docType.docUserViews[i].mulUser.username;
                                    var summary = "作者尚未增加内容";
                                    if ( data.docType.docUserViews[i].document.summary != null){
                                        summary = data.docType.docUserViews[i].document.summary;
                                    }
                                    if (data.docType.docUserViews[i].document.image != null) image = '<img src="'+data.docType.docUserViews[i].document.image+'"/>';
                                    var AddDiv = $('<div class="other_module"><div class="left_part"><a style="color: #333;" href="article.html?id='+data.docType.docUserViews[i].document.id+'" target="_blank" class="under_line">'+data.docType.docUserViews[i].document.title+'</a><p class="draw_text">'+summary+'</p><div class="bottom_meta"><a href="javascript:;" class="bottom_first_a">'+firstName+'</a><a href="javascript:;" class="bottom_two_a"><i class="iconfont">&#xe684;</i><b>'+data.docType.docUserViews[i].document.commentnum+'</b></a><span class="bottom_first_span"><i class="iconfont">&#xe602;</i><b>'+data.docType.docUserViews[i].document.upvotenum+'</b></span><span class="bottom_two_span"><i class="iconfont">&#xe672;</i></span></div></div><a href="javascript:;" class="replace_img">'+image+'</aa></div>');
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
            console.log("获取问题具体信息失败！")
        }
    })
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
    $(".MuchSameSpan img").attr("src",image);

    $('.leftColumns a').off('click');
    //关注问题
    $('.leftColumns .FocusQuestion').on('click', function () {
        if ($(this)[0].style.background == "rgb(193, 25, 78)") {
            $(this).html('关注问题');
            $(this).css({
                'background': ''
            });
            if (parseInt($(".OneFirstFocus span").text()) > 1){
                $(".OneFirstFocus span").text(parseInt($(".OneFirstFocus span").text())-1)
            } else{
                $(".OneFirstFocus span").text("无")
            }
        } else {
            $(this).html('已关注');
            $(this).css({
                'background': '#C1194E'
            });
            if ($(".OneFirstFocus span").text() == "无"){
                $(".OneFirstFocus span").text(1)
            } else{
                $(".OneFirstFocus span").text(parseInt($(".OneFirstFocus span").text())+1)
            }
        }
        $.ajax({
            url:"../col/forumC?c"+window.location.search.substring(1),
            type:"put",
            success:function () {
            },error:function () {
                console.log("收藏问题出错！")
            }
        })
    });

    $('#editor-word').on('click', function () {
        $('.editor-question-word').css('display', 'flex');
        $('#editor-question-word-textarea').focus();
        $('#editor-question-word-textarea').val($(".wordP").text());
    });

    //编辑封面
    $('#replaceImg').on('click', function () {
        $('.tailoring-container').toggle();
    });
}

$('.leftColumns a').on('click', function () {
    layer.msg("请先登录！")
});

$('.articleContainer').append('<div class="Select_Much"></div>');

var SelectDiv = $('.Select_Much');

CutWordColumns('.draw_text', 72);
CutWordColumns('.wordP', 60);

function CutWordColumns(n, num) {
    $(n).each(function () {
        var maxwidth = num;
        if ($(this).text().length > maxwidth) {
            $(this).text($(this).text().substring(0, maxwidth));
            $(this).html($(this).html() + "...");
        }
    });
}

$('.confirm-btn').on('click', function () {
    var Del_space_newline = $('#editor-question-word-textarea').val().replace(/\ +/g, "").replace(/[\r\n]/g, "");
    $('.editor-question-word').css('display', '');
    if (Del_space_newline != "" && Del_space_newline != $(".wordP").text()) {
        $.ajax({
            url:"../proContent"+window.location.search+"&content="+Del_space_newline,
            type:"put",
            success:function (data) {
                console.log(data);
                if (data == 401){
                    layer.msg("权限错误！")
                } else if (data == 403){
                    layer.msg("简介中含有违法，暴力等信息，请进行修改！")
                } else{
                    $('.topImgColumns .wordP').html(Del_space_newline);
                }
            },error:function () {
                console.log("修改问题简介发生错误！")
            }
        });

    }
    CutWordColumns('.wordP', 100);
    $('#editor-question-word-textarea').val('');
});

//获取地址栏传递的信息
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return decodeURI(r[2]);
    return null; //返回参数值
}

//获得上一个页面传递的值
var addRess = getUrlParam('SendAContent');
$('.leftColumnsH1').html(addRess);

//记录文章的篇数
var NumLength = $('.other_module').length;
if (NumLength > 0) {
    $('.TwoCollectionArticle').html('收集了' + NumLength + '篇文章');
}

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
    aspectRatio: 4 / 3,
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
        $.ajaxFileUpload({
            url:"../proimage"+window.location.search,
            type:"post",
            secureuri:false,
            dataType:"text",
            fileElementId:["chooseImg"],
            success:function (data) {
                console.log(data);
                if (data == 'IMAGE_N'){
                    layer.msg("图片涉及不良内容，请重新选择图片！");
                } else if(data == 'BIG'){
                    layer.msg("图片过大，请上传小于2M的图片。")
                }else if(data == 'WRONG_TYPE'){
                    layer.msg("图片格式错误！目前仅支持jpg/jpeg/bmp/png/gif格式。")
                }else if (data == 'NO'){
                    layer.msg("权限错误！")
                } else{
                    var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
                    var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
                    $('#finalImg').prop('src', base64url); //显示为图片的形式
                    //关闭裁剪框
                    closeTailor();
                }

            }, error:function () {
                console.log("上传头像出错！");
                layer.msg("图片过大，请上传小于2M的图片。")
            }
        })
    }
});

//关闭裁剪框
function closeTailor() {
    $('.tailoring-container').toggle();
}

//窗口变化时发生
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