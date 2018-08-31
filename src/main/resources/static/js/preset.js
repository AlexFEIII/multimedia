$(document).ready(function () {
    console.log(window.location.search);
    if (window.location.search == "") {
        console.log("增加文章");
        //裁剪后的处理
        $('#sureCut').on('click', function () {
            if ($('#tailoringImg').attr('src') == null) {
                return false;
            } else {
                $.ajaxFileUpload({
                    url: "",
                    type: "post",
                    secureuri: false,
                    fileElementId: ["chooseImg"],
                    success: function (data) {
                        console.log(data);
                        if (data == 'IMAGE_N') {
                            layer.msg("图片涉及不良内容，请重新选择图片！");
                        } else if (data == 'BIG') {
                            layer.msg("图片过大，请上传小于2M的图片。")
                        } else if (data == 'WRONG_TYPE') {
                            layer.msg("图片格式错误！目前仅支持jpg/jpeg/bmp/png/gif格式。")
                        } else if (data == 'NO') {
                            layer.msg("权限错误！")
                        } else {
                            //去掉边框
                            $('.cutImg').css({
                                'border': '1px solid transparent'
                            });
                            //添加img属性
                            $('#finalImg').addClass('cutInsertImg');
                            var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
                            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
                            $('#finalImg').prop('src', base64url); //显示为图片的形式
                            //关闭裁剪框
                            closeTailor();
                        }
                    }
                })
            }
        });

        $('.Titlelabel,.BtnInput').on('click', function () {
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
        });
        // 点击显示下载按钮
        $('.FirstEditorArticle').on('click', function () {
            $('.MoveTop').css('transform', 'scaleY(1)');
            $(this).css('color', '#376956');
        });
    }else if (window.location.search.indexOf("type=forum") != -1) {
        console.log("增加议题问题收录文章");
        //裁剪后的处理
        $('#sureCut').on('click', function () {
            if ($('#tailoringImg').attr('src') == null) {
                return false;
            } else {
                $.ajaxFileUpload({
                    url: "",
                    type: "post",
                    secureuri: false,
                    fileElementId: ["chooseImg"],
                    success: function (data) {
                        console.log(data);
                        if (data == 'IMAGE_N') {
                            layer.msg("图片涉及不良内容，请重新选择图片！");
                        } else if (data == 'BIG') {
                            layer.msg("图片过大，请上传小于2M的图片。")
                        } else if (data == 'WRONG_TYPE') {
                            layer.msg("图片格式错误！目前仅支持jpg/jpeg/bmp/png/gif格式。")
                        } else if (data == 'NO') {
                            layer.msg("权限错误！")
                        } else {
                            //去掉边框
                            $('.cutImg').css({
                                'border': '1px solid transparent'
                            });
                            //添加img属性
                            $('#finalImg').addClass('cutInsertImg');
                            var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
                            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
                            $('#finalImg').prop('src', base64url); //显示为图片的形式
                            //关闭裁剪框
                            closeTailor();
                        }
                    }
                })
            }
        });

    }else if (window.location.search.indexOf("id=") != -1) {
        //裁剪后的处理
        $('#sureCut').on('click', function () {
            if ($('#tailoringImg').attr('src') == null) {
                return false;
            } else {
                $.ajaxFileUpload({
                    url: "",
                    type: "post",
                    secureuri: false,
                    fileElementId: ["chooseImg"],
                    success: function (data) {
                        console.log(data);
                        if (data == 'IMAGE_N') {
                            layer.msg("图片涉及不良内容，请重新选择图片！");
                        } else if (data == 'BIG') {
                            layer.msg("图片过大，请上传小于2M的图片。")
                        } else if (data == 'WRONG_TYPE') {
                            layer.msg("图片格式错误！目前仅支持jpg/jpeg/bmp/png/gif格式。")
                        } else if (data == 'NO') {
                            layer.msg("权限错误！")
                        } else {
                            //去掉边框
                            $('.cutImg').css({
                                'border': '1px solid transparent'
                            });
                            //添加img属性
                            $('#finalImg').addClass('cutInsertImg');
                            var cas = $('#tailoringImg').cropper('getCroppedCanvas'); //获取被裁剪后的canvas
                            var base64url = cas.toDataURL('image/png'); //转换为base64地址形式
                            $('#finalImg').prop('src', base64url); //显示为图片的形式
                            //关闭裁剪框
                            closeTailor();
                        }
                    }
                })
            }
        });

        console.log("修改文章")

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
                    url:"../doc/change",
                    type:"put",
                    success:function () {

                    }
                })
            }
        },300);

        $('.MoveTop a').on('click', function () {
            $('.FirstEditorArticle').html($(this).html());
            $('.MoveTop').css('transform', '');
            console.log("TYPEChange")
            var valueText = $(this).text();
            console.log(valueText.substring(valueText.length-2,valueText.length));
            $.ajax({
                url:"",
                type:"put",
                success:function () {

                },error:function () {

                }
            })
        });

        $.ajax({
            url:"/doc"+window.location.search,
            type:"get",
            success:function (data) {
                console.log(data);
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
                $("input").eq(0).val(data.document.title);
                console.log($(".MoveTop li"))
                if (data.document.kind == "internet"){
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
                }
                if (data.document.image != null){
                    $("#finalImg").attr("src",data.document.image);
                }
            },error:function () {

            }
        })
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
    } else if ($('#finalImg').attr('src') == '') {
        alert('请您选择文章的封面');
    } else {
        $(this).attr({
            'href': 'RichEditor.html',
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
