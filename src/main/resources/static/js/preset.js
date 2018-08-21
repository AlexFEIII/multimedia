$(document).ready(function () {
   $.ajax({
       url:"/doc"+window.location.search,
       type:"get",
       success:function (data) {
           console.log(data);
            $("input").eq(0).val(data.document.title);
            $("input").eq(1).val(data.document.kind);
            $("input").eq(2).val(data.document.image);
       },error:function () {

       }
   })
});