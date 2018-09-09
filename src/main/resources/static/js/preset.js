var DOCDATA = "";
$(document).ready(function () {
    console.log(window.location.search);
    if (window.location.search == "") {
        $.ajax({
            url:"../doc/addDoc",
            type:"post",
            success:function (data) {
                if (data!=null){
                    window.location.replace("preset.html?id="+data)
                }
            },error:function () {
                console.log("增加新的文章失败！")
            }
        })
    }else if (window.location.search.indexOf("type=forum") != -1) {
        $.ajax({
            url:"../doc/addProDoc"+window.location.search,
            type:"post",
            success:function (data) {
                if (data!=null){
                    window.location.replace("preset.html?id="+data)
                }
            },error:function () {
                console.log("增加新的文章失败！")
            }
        })
    }else if (window.location.search.indexOf("id=") != -1) {
        //获取需要修改的文章信息
        $.ajax({
            url:"/doc"+window.location.search,
            type:"get",
            async:false,
            success:function (data) {
                DOCDATA = data;
                if (data.document.title != null){
                    $("input").eq(0).val(data.document.title);
                    $('.Titlelabel').css({
                        'top': -25,
                        'transform': 'none',
                        'color': '#376956'
                    });
                    $('.BtnInput').css({
                        'background': '#fff',
                        'border': '2px solid #376956'
                    });
                    $('.BtnInput').focus();
                    stopBubble();
                } else {
                    $('.Titlelabel,.BtnInput').on('click', function () {
                        $('.Titlelabel').css({
                            'top': -25,
                            'transform': 'none',
                            'color': '#376956',
                        });
                        $('.BtnInput').css({
                            'background': '#fff',
                            'border': '2px solid #376956',
                        });
                        $('.BtnInput').focus();
                        stopBubble();
                    });
                }
                console.log($(".MoveTop li"))
                if (data.document.kind == "internet"){
                    console.log($(".MoveTop li").eq(0).children("a").html());
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(0).children("a").html())
                } else if(data.document.kind == "law"){
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(1).children("a").html())
                } else if(data.document.kind == "medicine"){
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(2).children("a").html())
                } else if(data.document.kind == "economy"){
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(3).children("a").html())
                } else if(data.document.kind == "history"){
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(4).children("a").html())
                } else if(data.document.kind == "science"){
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(5).children("a").html())
                } else if(data.document.kind == "art"){
                    $('.FirstEditorArticle').html($(".MoveTop li").eq(6).children("a").html())
                } else if (data.document.kind == "forum"){
                    $('.FirstEditorArticle').html('<i class="iconfont">&#xe60b;</i>问答');
                    $('.MoveTop').remove();
                }
                if (data.document.image != null){
                    $('#finalImg').addClass('cutInsertImg');
                    $("#finalImg").attr("src",data.document.image);
                }
            },error:function () {

            }
        });
        window.onunload = function (ev) {
            $.ajax({
                url:"../doc/delete"+window.location.search,
                type:"delete",
                success:function (data) {
                },error:function () {
                    console.log("删除失败")
                }
            })
        };
        //裁剪后的处理
        $('#sureCut').on('click', function () {
            if ($('#tailoringImg').attr('src') == null) {
                return false;
            } else {
                var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
                var img = cas.toDataURL('image/png'); //转换为base64地址形式
                var formData = new FormData();
                // formData.append('image', convertBase64UrlToBlob(img));
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
                formData.append("image",dataURLToBlob(img));
                formData.append("documentid",DOCDATA.document.id);
                $.ajax({
                    url:"../doc/change",
                    type:"post",
                    data:formData,
                    contentType:false,
                    cache: false,
                    processData: false,
                    success:function (data) {
                        if (data == 'IMAGE_N') {
                            layer.msg("图片涉及不良内容，请重新选择图片！");
                        } else if (data == 'BIG') {
                            layer.msg("图片过大，请上传小于2M的图片。")
                        } else if (data == 'WRONG_TYPE') {
                            layer.msg("图片格式错误！目前仅支持jpg/jpeg/bmp/png/gif格式。")
                        } else if (data == 'NO') {
                            layer.msg("权限错误！")
                        } else if (data == "NoUser"){
                            layer.msg("用户已注销！")
                        } else {
                            console.log(data);
                            //去掉边框
                            $('.cutImg').css({
                                'border': '1px solid transparent'
                            });
                            //添加img属性
                            $('#finalImg').addClass('cutInsertImg');
                            $('#finalImg').prop('src', img); //显示为图片的形式
                            //关闭裁剪框
                            closeTailor();
                        }
                    },error:function () {
                        console.log("文章修改图片失败！")
                    }
                })
            }
        });
        //修改标题
        $(".BtnInput").keyup(function () {
            count = 0;
        });
        var cutTime = 5;
        var count = 6;
        window.setInterval(function () {
            count ++;
            if (count == cutTime){
                $("#Saved").animate({"opacity":"1"},250);
                $("#Saved").animate({"opacity":1},800);
                $("#Saved").animate({"opacity":0},250);
                $.ajax({
                    url:"../doc/changeTitle?docid="+DOCDATA.document.id+"&title="+$(".BtnInput").val(),
                    type:"put",
                    success:function (data) {
                        if (data == "ILLEGAL"){
                            layer.msg("标题含有违法，暴力等内容，请进行修改！")
                        } else if (data == "N"){
                            layer.msg("发生未知错误！")
                        }else if (data == "NoUser"){
                            layer.msg("用户已注销！")
                        }
                    },error:function () {
                        console.log("修改文章标题出错！")
                    }
                })
                if ($(".BtnInput").val() == ""){
                    $('.Titlelabel,.BtnInput').off("click");
                    $('.Titlelabel,.BtnInput').on('click', function () {
                        $('.Titlelabel').css({
                            'top': -25,
                            'transform': 'none',
                            'color': '#376956',
                        });
                        $('.BtnInput').css({
                            'background': '#fff',
                            'border': '2px solid #376956',
                        });
                        $('.BtnInput').focus();
                        stopBubble();
                    });
                } else{
                    $('.Titlelabel,.BtnInput').off("click");
                    $('.Titlelabel').css({
                        'top': -25,
                        'transform': 'none',
                        'color': '#376956'
                    });
                    $('.BtnInput').css({
                        'background': '#fff',
                        'border': '2px solid #376956'
                    });
                    $('.BtnInput').focus();
                    stopBubble();
                }
            }
        },300);
        //修改类别
        if (DOCDATA.document.kind != "forum"){
            $('.MoveTop a').on('click', function () {
                $('.FirstEditorArticle').html($(this).html());
                $('.MoveTop').css('transform', '');
                var valueText = $(this).text();
                console.log(valueText.substring(valueText.length-2,valueText.length));
                $.ajax({
                    url:"../doc/changeType?docid="+DOCDATA.document.id+"&type="+valueText.substring(valueText.length-2,valueText.length),
                    type:"put",
                    success:function (data) {
                        if (data == "N"){
                            layer.msg("发生未知错误！")
                        }else if (data == "NoUser"){
                            layer.msg("用户已注销！")
                        }
                    },error:function () {
                        console.log("修改文章类型失败！")
                    }
                })
            });
        }

        //选择类别
        $('.FirstEditorArticle').on('click', function () {
            $('.MoveTop').css('transform', 'scaleY(1)');
            $(this).css('color', '#376956');
        });
    }

    //窗口变化时发生
    (window.onresize = function () {
        var win_height = $(window).height();
        var win_width = $(window).width();
        if (win_width <= 768) {
            $('.tailoring-content').css({
                top: (win_height - $('.tailoring-content').outerHeight()) / 2,
                left: 0
            });
        } else {
            $('.tailoring-content').css({
                top: (win_height - $('.tailoring-content').outerHeight()) / 2,
                left: (win_width - $('.tailoring-content').outerWidth()) / 2
            });
        }
    })();

});

function closeTailor(){
    $('.tailoring-container').toggle();
}
//关闭裁剪框
$(".close-tailoring").on('click',function () {
    $('.tailoring-container').toggle();
});

//编辑按钮判断
$('.editArticle').on('click', function () {
    if ($('.BtnInput').val() == '') {
        alert('请您填写文章标题');
    } else if ($('.FirstEditorArticle').html() == '编辑文章类型') {
        alert('请您选择文章类型')
    } else {
        $(this).attr({
            'href': 'RichEditor.html'+window.location.search,
            'target': '_blank'
        });
    }
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
    flagX = !flagX;
});
//取消冒泡
function stopBubble(e) {
    if (e && e.stopPropagation)
    //因此它支持W3C的stopPropagation()方法
        e.stopPropagation();
    else
    //否则，我们需要使用IE的方式来取消事件冒泡
        window.event.cancelBubble = true;
}

//编辑图片
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
    aspectRatio: 5 / 4,
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
    }
});
$(document).on('click', function () {
    if ($('.BtnInput').val() == '') {
        $('.Titlelabel').css({
            'top': '',
            'transform': '',
            'color': ''
        });
        $('.BtnInput').css({
            'background': '',
            'border': ''
        });
        $('.BtnInput').blur();
    }
});