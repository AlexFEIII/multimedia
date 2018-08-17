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
  if ($('#tailoringImg').attr('src') == null) {
    return false;
  } else {
    $.ajaxFileUpload({
        url:"../user/changeImage",
        type:"post",
        secureuri:false,
        fileElementId:["chooseImg"],
        success:function (data) {
          if (data == "IMAGE_N"){
            alert("图片涉及不良内容，请重新选择图片！");
          } else if(data == "BIG"){
            alert("图片过大，请上传小于2M的图片。")
          }else if(data == "WRONG_TYPE"){
            alert("图片格式错误！目前仅支持jpg/jpeg/bmp/png/gif格式。")
          }else{
              var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
              var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
              $('#finalImg,.photo_cicle img').prop('src', base64url); //显示为图片的形式
              //关闭裁剪框
              closeTailor();
          }

         }, error:function () {
           console.log("上传头像出错！")
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

$('.save').on('click', function () {
  var val_number = $('.inputText').eq(1).val(); //QQ号码
  var val_num = $('.inputText').eq(2).val(); //电话号码
  if (!checkNumber(val_number) && val_number != '') {
    alert("请重新输入正确的QQ号");
    $('.inputText').eq(1).val('');
  }
  if (!checkNumber(val_num) && val_num != '') {
    alert("请重新输入正确的电话号码");
    $('.inputText').eq(2).val('');
  }
  if (checkNumber(val_num) && checkNumber(val_number)) {
    layerMsg('保存成功', 6);
  }
});

function checkNumber(theObj) {
  var reg = /^[0-9]+$/; //判断一定需要全是数字
  if (reg.test(theObj)) {
    return true;
  }
  return false;
}

$('.saveN').on('click', function () {
  if ($('#inputEmail').val() == '') {
    alert('邮箱为空，请输入正确的邮箱地址');
  } else if (!$('#inputEmail')
    .val()
    .match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)
  ) {
    alert('您输入的邮箱格式不正确！请重新输入');
    //判断邮箱的输入格式是否正确
    $('.inputTextN').eq(1).val('');
  }

  if ($('.password1').val() == '') {
    layerMsg('请确保您的密码正确，请重新输入您的密码', 7);
  } else if ($('.password2').val() == '') {
    layerMsg("请设置您的新密码",7);
  }

  if (
    $('#inputEmail').val() != '' &&
    $('#inputEmail')
    .val()
    .match(
      /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
    ) &&
    $('.password1').val() != '' &&
    $(".password2").val() != ''
  ) {
    layerMsg('保存成功', 6);
  }
});

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
  var message = layer.confirm('确定保存?', {
                    btn: ['确定', '取消'], //按钮
                    title: '提示'
                });
  if (message){
    var checkvalue;
    for(var i = 0;i < 3;i ++){
      if ($("#inputRadio"+(i+1)).prop('checked')){
        checkvalue = i+1;
        break;
      }
      if (checkvalue == 3) checkvalue = 0;
    }
    $.ajax({
        url:"../user/changeUser",
        type:"post",
        data:{"sex":checkvalue,"personality":$("textarea").val(),"address":userInformation.children("li").eq(3).children("div").children("input").val(),
            "qq":userInformation.children("li").eq(4).children("div").children("input").val(),"job":userInformation.children("li").eq(6).children("div").children("input").val(),
            "weburl":userInformation.children("li").eq(7).children("div").children("input").val()},
        success:function (data) {
            console.log("changeSuccess")
        },error:function () {

        }
    });
  }
});

$(".logOut").click(function () {
    var msg = confirm("确认注销账号？");
    if (msg){
        $.ajax({
            url:"../logout",
            success:function () {
                window.location.replace("/html/index.html");
            },error:function () {

            }
        })
    }

});

$(".user_list").find("li").eq(1).on("click",function () {
    console.log("点击~")
});

$(".user_list").find("li").eq(2).on("click",function () {
    console.log("点击点击~")
});

function loginSuccess(data) {
    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"> </a> <div class="msg_index_dance">进入个人中心 </div> </div> <div class="editor_article"> <a href="preset.html" target="_blank"> <span> <i class="iconfont">&#xe645;</i></span>写文章</a></div>');
    $("#finalImg").attr("src",image);
    if (data.sex == 1) $("#inputRadio1").click();
    else if (data.sex == 2) $("#inputRadio2").click();
    if (data.personality != "") $("textarea").val(data.personality);
    if (data.address != "") userInformation.children("li").eq(3).children("div").children("input").val(data.address);
    if (data.qq != "")  userInformation.children("li").eq(4).children("div").children("input").val(data.qq);
    console.log("username: "+data.username);
    userInformation.children("li").eq(5).children("div").children("span").eq(1).val(data.username);
    if (data.job != "") userInformation.children("li").eq(6).children("div").children("input").val(data.job);
    if (data.weburl != "") userInformation.children("li").eq(7).children("div").children("input").val(data.weburl);
}
