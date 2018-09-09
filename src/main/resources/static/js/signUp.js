$(".signIn_btn").click(function () {
    if($("#nickname").val() == "" || $("#nickname").val().length < 1 || $("#nickname").val().length > 16){
        layer.msg("请填写昵称内容且长度在16位以下！")
    }else if($("#nickname").val().toString().indexOf(" ") != -1){
       layer.msg("昵称不允许含有空格！");
    } else if ($("#phoneNumber").val() == "" || !checkNumber($("#phoneNumber").val())){
        layer.msg("请填写正确的手机号码！");
    } else if ($("#textPhone").val() == ""|| $("#textPhone").val().length != 6){
        layer.msg("请正确填写收到的手机验证码！")
    } else if ($("#password").val() == "" || $("#password").val().length < 6 || $("#password").val().length > 16) {
        layer.msg("请填写密码且长度位于6位至16位之间！")
    }else{
        console.log("发送注册请求")
        $.ajax({
            url:"../user/register",
            type:"post",
            data:{"username":$("#phoneNumber").val(),"password":$("#password").val(),"nickname":$("#nickname").val(),"code":$("#textPhone").val()},
            success:function (data) {
                if (data == "NICK-LENGTH"){
                    layer.msg("请填写昵称内容且长度位于6位至16位之间！")
                }else if (data == "PASS-LENGTH"){
                    layer.msg("请填写密码且长度位于6位至16位之间！")
                } else if(data == "RePhone"){
                    layer.msg("手机号已被注册！")
                }else if (data == "ReNick"){
                    layer.msg("昵称已被使用！")
                } else if (data == "NO"){
                    layer.msg("验证码错误！")
                } else {
                    parent.location.reload();
                }
            },error:function () {
                
            }
        })
    }
});

// 验证60秒倒计时
$('.confirmRight').on('click', function () {
    if($("#nickname").val() == "" || $("#nickname").val().length < 1 || $("#nickname").val().length > 16){
        layer.msg("请填写昵称内容且长度在16位以下！")
    }else if ($("#phoneNumber").val() == "" || !checkNumber($("#phoneNumber").val())){
        layer.msg("请填写正确的手机号码！");
    }else{
        $.ajax({
            url:"../user/getCode",
            type:"post",
            data:{"nickname":$("#nickname").val(),"phoneNum":$("#phoneNumber").val()},
            success:function (data) {
                if (data == "RePhone"){
                    layer.msg("手机号码已经注册！")
                } else if(data == "ReName"){
                    layer.msg("昵称已经被使用！")
                }else if (data == "HasSpace"){
                    layer.msg("昵称不允许含有空格！");
                }
            }
        })
        if ($(this).attr('stop') == '1') {
            $(this).off("click");
            $(this).attr('stop', '0');
            $(this).html('重新发送(60s)');
            var Time_num = 60;
            var This = $(this);
            var timer = setInterval(function () {
                Time_num--;
                This.html('重新发送(' + Time_num + 's)');
                if (Time_num == 0) {
                    clearInterval(timer);
                    This.html('发送验证码');
                    This.attr('stop', '1');
                    $('.confirmRight').on('click', function () {
                        if($("#nickname").val() == "" || $("#nickname").val().length < 1 || $("#nickname").val().length > 16){
                            layer.msg("请填写昵称内容且长度在16位以下！")
                        }else if ($("#phoneNumber").val() == "" || !checkNumber($("#phoneNumber").val())){
                            layer.msg("请填写正确的手机号码！");
                        }else{
                            $.ajax({
                                url:"../user/getCode",
                                type:"post",
                                data:{"nickname":$("#nickname").val(),"phoneNum":$("#phoneNumber").val()},
                                success:function (data) {
                                    if (data == "RePhone"){
                                        layer.msg("手机号码已经注册！")
                                    } else if(data == "ReName"){
                                        layer.msg("昵称已经被使用！")
                                    }else if (data == "HasSpace"){
                                        layer.msg("昵称不允许含有空格！");
                                    }
                                }
                            })
                            if ($(this).attr('stop') == '1') {
                                $(this).off("click");
                                $(this).attr('stop', '0');
                                $(this).html('重新发送(60s)');
                                var Time_num = 60;
                                var This = $(this);
                                var timer = setInterval(function () {
                                    Time_num--;
                                    This.html('重新发送(' + Time_num + 's)');
                                    if (Time_num == 0) {
                                        clearInterval(timer);
                                        This.html('发送验证码');
                                        This.attr('stop', '1');
                                    }
                                }, 1000);
                            } else {
                                alert('操作太过频繁,请您稍后再操作!!!');
                            }
                        }
                    });
                }
            }, 1000);
        } else {
            alert('操作太过频繁,请您稍后再操作!!!');
        }
    }
});

$('#phoneNumber').bind('input propertychange', function () {
    var Value = $('#phoneNumber').val();
    if (Value.length == 11 && checkNumber(Value)) {
        $('#textPhone').css({
            'z-index': '',
        });
        $('.confirmRight').css({
            'background': '#528870',
        });
    } else {
        $('#textPhone').css({
            'z-index': '1',
        });
        $('.confirmRight').css({
            'background': '#b0ecd1',
        });
    }
}); //实时监听input里面内容的改变

$('.confirmRight').hover(function () {
    $(this).css({
        'background': '#73b497',
    })
}, function () {
    $(this).css({
        'background': '#528870',
    });
});

function checkNumber(theObj) {
    var reg = /^[0-9]+$/; //判断一定需要全是数字
    if (reg.test(theObj)) {
        return true;
    }
    return false;
}