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
  aspectRatio: 1 / 1, //默认比例
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
    closeTailor();
  if ($('#tailoringImg').attr('src') == null) {
    return false;
  } else {
      var animatedLoading = $('<div class="Load-animated"><div class="spinner spinnerTwo"><span></span></div></div>');
      $('.change_img').append(animatedLoading);
      var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
      var img = cas.toDataURL('image/png'); //转换为base64地址形式
      var formdata = new FormData();
      function dataURLToBlob(dataurl){
          var arr = dataurl.split(',');
          var mime = arr[0].match(/:(.*?);/)[1];
          var bstr = atob(arr[1]);
          var n = bstr.length;
          var u8arr = new Uint8Array(n);
          while(n--){
              u8arr[n] = bstr.charCodeAt(n);
          }
          return new Blob([u8arr], {type:mime});
      }
      formdata.append("headimage",dataURLToBlob(img));
      $.ajax({
          url:"../user/changeImage",
          type:"post",
          data:formdata,
          contentType:false,
          cache: false,
          processData: false,
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
                  $('#finalImg').prop('src', img); //显示为图片的形式
                  //关闭裁剪框
                  animatedLoading.remove();
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

var oLd = $('.user_list a').eq(0);
var oLd_li = $('.user_information ul').eq(0);

$('.user_list a').on('click', function () {
  var index = $('.user_list a').index(this); //判断元素在当前的位置是第几个元素
  oLd_li.css({
    display: 'none',
  });
  oLd_li = $('.user_information ul').eq(index);
  $('.user_information ul')
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

function layerMsg(n, m) {
  layer.msg(n, {
    icon: m,
    time: 2000, //2秒关闭（如果不配置，默认是3秒）
  });
} //弹窗

function checkNumber(theObj) {
  var reg = /^[0-9]+$/; //判断一定需要全是数字
  if (reg.test(theObj)) {
    return true;
  }
  return false;
}

var InputText = $('.user_information .inputText');
var InputTextN = $('.user_information .inputTextN');

function Each(InputText) {
  InputText.each(function () {
    var index = InputText.index(this);
    InputText.eq(index).val('');
  });
}

$(function () {
  //刷新的时候清空input里面的内容
  Each(InputText);
  Each(InputTextN);
  $('.user_information textarea').val('');
  //结束
});

var userInformation = $(".user_information").children("ul");
var userdata;
$(document).ready(function () {
    $.ajax({
        url:"../user/isLogin",
        type:"get",
        async:false,
        success:function (data) {
            console.log(data);
            userdata = data;
            if (data != ""){
                loginSuccess(data)
            }
        }
    })
});

//保存信息按钮点击事件
$(".save").click(function () {
    layer.msg("确定保存？",{
        time: 0,
        btn:["确定","取消"],
        yes:function (index) {
            var checkvalue;
            for (var i = 0; i < 3; i++) {
                if ($("#inputRadio" + (i + 1)).prop('checked')) {
                    checkvalue = i + 1;
                    break;
                }
                if (checkvalue == 3) checkvalue = 0;
            }
            $.ajax({
                url: "../user/changeUser",
                type: "post",
                data: {
                    "sex": checkvalue,
                    "personality": $("textarea").val(),
                    "address": userInformation.children("li").eq(3).children("div").children("input").val(),
                    "qq": userInformation.children("li").eq(4).children("div").children("input").val(),
                    "job": userInformation.children("li").eq(6).children("div").children("input").val(),
                    "weburl": userInformation.children("li").eq(7).children("div").children("input").val()
                },
                success: function (data) {
                    console.log("changeSuccess")
                }, error: function () {

                }
            });
        }
    })
});

$(".saveN").on("click",function () {
    layer.msg("确定保存？",{
        time: 0,
        btn:["确定","取消"],
        yes:function (index) {
            layer.close(index);
            if ($('#inputEmail').val() != "") {
                if (!$('#inputEmail').val().match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
                    alert('您输入的邮箱格式不正确！请重新输入');
                    return;
                }
            }
            var nickul = $(".user_information ul").eq(1);
            if (nickul.children("li").eq(0).find("input").val() != ""){
                if (nickul.children("li").eq(0).find("input").val().length < 1 || nickul.children("li").eq(0).find("input").val().length > 16){
                    layer.msg("昵称长度请控制在16位以下！");
                    return;
                }
            }
            $.ajax({
                url:"../user/changeNick",
                type:"post",
                data:{"nickname":nickul.children("li").eq(0).find("input").val(),"email":$('#inputEmail').val()},
                success:function (data) {
                    console.log("changeNick: "+data);
                },error:function () {
                    console.log("更改用户昵称、邮箱失败！")
                }
            });
            if ($(".password1").val() != ""){
                if ($(".password1").val().length < 6 || $(".password1").val().length > 16){
                    layer.msg("密码长度请控制在6位至16位之间！");
                    return;
                }
                if ($(".password2").val() == ""){
                    layer.msg("请确定你要修改的密码");
                    return;
                }
                if ($(".password2").val() != $(".password1").val()){
                    layer.msg("两次输入的密码不一致！");
                    return;
                }
                console.log("发送修改密码请求");
                $.ajax({
                    url:"../user/changePass",
                    type:"post",
                    data:{"password":$(".password1").val()},
                    success:function (data) {
                        console.log(data);
                    },error:function () {
                        console.log("修改密码出错！")
                    }
                })
            }
        }
    });
});

$(".logOut").click(function () {
    layer.msg("确定保存？",{
        time: 0,
        btn:["确定","取消"],
        yes:function (index) {
            $.ajax({
                url: "../logout",
                success: function () {
                    window.location.replace("/html/index.html");
                }, error: function () {

                }
            })
        }
    })
});

$(".user_list").find("li").eq(1).on("click",function () {
    console.log("点击~")
    var nickul = $(".user_information ul").eq(1);
    nickul.children("li").eq(0).find("input").val(userdata.nickname);
    if (userdata.email != null) nickul.children("li").eq(1).find("input").val(userdata.email);
});

$(".user_list").find("li").eq(2).on("click",function () {
    console.log("点击点击~")
});

function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $("#finalImg").attr("src",image);
    if (data.sex == 1) $("#inputRadio1").click();
    else if (data.sex == 2) $("#inputRadio2").click();
    if (data.personality != "") $("textarea").val(data.personality);
    if (data.address != "") userInformation.children("li").eq(3).children("div").children("input").val(data.address);
    if (data.qq != "")  userInformation.children("li").eq(4).children("div").children("input").val(data.qq);
    console.log("username: "+data.username);
    userInformation.children("li").eq(5).children("div").children("span").eq(1).text(data.username);
    if (data.job != "") userInformation.children("li").eq(6).children("div").children("input").val(data.job);
    if (data.weburl != "") userInformation.children("li").eq(7).children("div").children("input").val(data.weburl);
}
