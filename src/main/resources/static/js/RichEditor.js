$(document).ready(function () {
    $.ajax({
        url:"../doc"+window.location.search,
        type:"get",
        success:function (data) {
            console.log(data);
            $("#input_Time").val(data.document.title);
            $(".w-e-text").html(data.document.content);
        },error:function (data) {
            console.log("请求文章信息失败！")
        }
    })
})