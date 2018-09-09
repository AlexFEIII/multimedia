var DOCDATA = "";
$(document).ready(function () {
    $.ajax({
        url:"../doc"+window.location.search,
        type:"get",
        success:function (data) {
            console.log(data);
            DOCDATA = data;
            if (data.document.title != null && data.document.title != ""){
                $(".input_Time").val(data.document.title);
            }
            $(".w-e-text").html(data.document.content);
        },error:function (data) {
            console.log("请求文章信息失败！")
        }
    })
});

var tcount = 6;
var time = 5;
var count = 6;
window.setInterval(function () {
    count ++;
    tcount ++;
    if (count == time){
        $('.publish_A').html('<div class="loading"><span></span><span></span><span></span></div>' +
            '<span class="save_ing">保存中...</span>');
        $('.publish_A').hover(function () {
            $(this).find('.loading').addClass('loading_hover');
        },function(){
            $(this).find('.loading').removeClass('loading_hover');
        });
        //保存
        console.log("content: "+$(".w-e-text").html());
        $.ajax({
            url:"../doc/changeContent"+window.location.search,
            type:"post",
            data:{"content":$(".w-e-text").html()},
            success:function (data) {
                if (data == "ILLEGAL"){
                    layer.msg("内容含有违法，暴力等内容，请进行修改！")
                } else if (data == "N"){
                    layer.msg("发生未知错误！")
                }else if (data == "NoUser"){
                    layer.msg("用户已注销！")
                }
                $(".publish_A").html('<i class="iconfont">&#xe815;</i>发布文章');
                $(".publish_A").on("click",function () {
                    layer.msg("发布成功！")
                })
            }
        });
    }
    if (tcount == time){
        $('.publish_A').html('<div class="loading"><span></span><span></span><span></span></div>' +
            '<span class="save_ing">保存中...</span>');
        $('.publish_A').hover(function () {
            $(this).find('.loading').addClass('loading_hover');
        },function(){
            $(this).find('.loading').removeClass('loading_hover');
        });
        //保存
        $.ajax({
            url:"../doc/changeTitle?docid="+DOCDATA.document.id+"&title="+$(".input_Time").val(),
            type:"put",
            success:function (data) {
                if (data == "ILLEGAL"){
                    layer.msg("标题含有违法，暴力等内容，请进行修改！")
                } else if (data == "N"){
                    layer.msg("发生未知错误！")
                }else if (data == "NoUser"){
                    layer.msg("用户已注销！")
                }
                $(".publish_A").html('<i class="iconfont">&#xe815;</i>发布文章');
                $(".publish_A").on("click",function () {
                    $.ajax({
                        url:"../doc/changeContent"+window.location.search+"&content="+$(".w-e-text").html(),
                        type:"put",
                        success:function (data) {
                            if (data == "ILLEGAL"){
                                layer.msg("内容含有违法，暴力等内容，请进行修改！")
                            } else if (data == "N"){
                                layer.msg("发生未知错误！")
                            }else if (data == "NoUser"){
                                layer.msg("用户已注销！")
                            }else{
                                layer.msg("发布成功！")
                            }
                        }
                    });
                })
            },error:function () {
            }
        });
    }
},300);
$(".w-e-text").on("keyup",function () {
    count = 0;
});
$(".input_Time").on("keyup",function () {
    tcount = 0;
});
