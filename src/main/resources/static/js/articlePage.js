// function test() {
//     var BodyHeight = $('#main').outerHeight(true);
//     var BodyWidth = $('body').outerWidth();
//     //背景的高度
//     $('#MoveCanvas').attr({
//         'width': BodyWidth,
//         'height': BodyHeight - 100,
//     });
// }
//
// test();
//
// $(window).resize(function () {
//     test();
// });
//
// //控制文字的多少
// $('.testText').each(function () {
//     var maxwidth = 35;
//     if ($(this).text().length > maxwidth) {
//         $(this).text($(this).text().substring(0, maxwidth));
//         $(this).html($(this).html() + "...");
//     };
// });

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

    $.ajax({
        url:"../doc/typemsg",
        type:"get",
        success:function (data) {
            $(".MuchSame").eq(0).find(".bottomDiv").children("span").eq(0).text(data[0]);
            $(".MuchSame").eq(0).find(".bottomDiv").children("span").eq(1).text(data[1]);
            $(".MuchSame").eq(1).find(".bottomDiv").children("span").eq(0).text(data[2]);
            $(".MuchSame").eq(1).find(".bottomDiv").children("span").eq(1).text(data[3]);
            $(".MuchSame").eq(2).find(".bottomDiv").children("span").eq(0).text(data[4]);
            $(".MuchSame").eq(2).find(".bottomDiv").children("span").eq(1).text(data[5]);
            $(".MuchSame").eq(3).find(".bottomDiv").children("span").eq(0).text(data[6]);
            $(".MuchSame").eq(3).find(".bottomDiv").children("span").eq(1).text(data[7]);
            $(".MuchSame").eq(4).find(".bottomDiv").children("span").eq(0).text(data[8]);
            $(".MuchSame").eq(4).find(".bottomDiv").children("span").eq(1).text(data[9]);
            $(".MuchSame").eq(5).find(".bottomDiv").children("span").eq(0).text(data[10]);
            $(".MuchSame").eq(5).find(".bottomDiv").children("span").eq(1).text(data[11]);
            $(".MuchSame").eq(6).find(".bottomDiv").children("span").eq(0).text(data[12]);
            $(".MuchSame").eq(6).find(".bottomDiv").children("span").eq(1).text(data[13]);
        },error:function () {
            console.log("获取文章类别信息错误");
        }
    })
});

function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"> </a> <div class="msg_index_dance">进入个人中心 </div> </div> <div class="editor_article"> <a href="preset.html" target="_blank"> <span> <i class="iconfont">&#xe645;</i></span>写文章</a></div>');
    $(".MuchSameSpan img").attr("src",image);
}
