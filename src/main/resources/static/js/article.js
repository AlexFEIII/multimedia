var DataTitle = $('.bottom_message').attr('data-title');
var alertTitle = $('<span class="alertSpan"></span>');
var onOff = true;
var Judge = true;

var DOCDATA;
var USERDATA;

var ListLan = $('.circle').attr('list-title');

//删除按钮不显示
$('.DEl').css("display","none");

$('.timeBar').hover(
  function () {
    $('.bottom_message').append(alertTitle);
    $('.alertSpan').html(DataTitle);
    setTimeout(function () {
      $('.alertSpan').css({
        opacity: '1'
      });
    }, 10);
  },
  function () {
    $('.alertSpan').css({
      opacity: ''
    });
    setTimeout(function () {
      alertTitle.remove();
    }, 300);
  }
);

$('.circle').hover(
  function () {
    $('.ContentShare').append(alertTitle);
    $('.alertSpan').html(ListLan);
    setTimeout(function () {
      $('.alertSpan').css({
        opacity: '1'
      });
    }, 10);
  },
  function () {
    $('.alertSpan').css({
      opacity: ''
    });
    setTimeout(function () {
      alertTitle.remove();
    }, 300);
  }
);

var UrlAddress = 'https://music.163.com';

$('.circle').on('click', function () {
  if (Judge) {
    Judge = false;
      $('.listTop').eq(0).css({
          top: '50%',
          transform: 'translate(-50%,-50%) rotateZ(-45deg)',
      });
      $('.listTop').eq(2).css({
          top: '50%',
          transform: 'translate(-50%,-50%) rotateZ(45deg)',
      });
      $('.listTop').eq(1).css({
          display: 'none',
      });
    $('.social-share').css({
      display: 'block',
      height: '600px',
    });
  } else {
    Judge = true;
      $('.listTop').eq(0).css({
          top: '',
          transform: '',
      });
      $('.listTop').eq(2).css({
          top: '',
          transform: '',
      });
      $('.listTop').eq(1).css({
          display: '',
      });
    $('.social-share').css({
      height: '',
    });
    setTimeout(function () {
      $('.social-share').css({
        display: '',
      });
    }, 200);
  }
});

//分享菜单可移动
var ContentShare = document.getElementsByClassName('ContentShare')[0];
var C_x = 0;
var C_y = 0;
var C_l = 0;
var C_t = 0;
var isDown = false;
//鼠标按下事件
ContentShare.onmousedown = function (e) {
  //获取x坐标和y坐标
  C_x = e.clientX;
  C_y = e.clientY;

  //获取左部和顶部的偏移量
  C_l = ContentShare.offsetLeft;
  C_t = ContentShare.offsetTop;
  //开关打开
  isDown = true;
};
//鼠标移动
window.onmousemove = function (e) {
  if (isDown == false) {
    return;
  }
  //获取x和y
  var nx = e.clientX;
  var ny = e.clientY;
  //计算移动后的左偏移量和顶部的偏移量
  var nl = nx - (C_x - C_l);
  var nt = ny - (C_y - C_t);

  if (nt >= 150) {
    ContentShare.style.left = nl + 'px';
    ContentShare.style.top = nt + 'px';
  }

  $('body').css({
    '-moz-user-select': 'none',
    '-webkit-user-select': 'none',
    '-ms-user-select': 'none',
    'user-select': 'none',
  });
};
//鼠标抬起事件
ContentShare.onmouseup = function () {
  //开关关闭
  isDown = false;

  $('body').css({
    '-moz-user-select': '',
    '-webkit-user-select': '',
    '-ms-user-select': '',
    'user-select': '',
  });
};

//创建一个新的编辑器
var E = window.wangEditor;
var editor = new E('#top_list', '#bottom_content');
editor.customConfig.menus = ['bold', 'italic', 'link', 'emoticon'];
editor.customConfig.zIndex = 0;
editor.create();

$('.publish_A').html('<i class="iconfont">&#xe815;</i>发送');

$('#QRCode').qrcode({
  width: 105,
  height: 105,
  text: UrlAddress
});


//关注
$('.focus').click(function () {
   layer.msg("请先登录！")
});

//点赞
var isGood = true;
var isBad = true;
var initNum;

$('.TwoPart a').on('click',function () {
    layer.msg("请先登录！");
});

function Change(n, m, o) {
  if (n) {
    if (o) {
      initNum++;
    }
    m.css({
      color: '#FF9500'
    });
  } else {
    if (o) {
      initNum--;
    }
    m.css({
      color: ''
    });
  }
  $('.numCount').html(initNum);
}

// description.replace(/<(?!img).*?>/g, "");//去除所有的html标签除了img
$('.NewEditor .publish_A').on('click', function () {
        layer.msg("请先登录！");
});

function addZero(n) {
  if (n < 10) {
    n = '0' + n + '';
  }
  return n;
}

function onNotSame(m, n) {
  if (n) {
    m.css({
      color: '#00B38C'
    });
  } else {
    m.css({
      color: ''
    });
  }
    $.ajax({
        url:"../upvote?type=DComment&objid="+m.parent().parent().parent().parent().children('.ComID').text(),
        type:"put",
        success:function () {
        },error:function () {
            console.log("文章评论点赞出错！")
        }

    })
}
function cancel() {
    $('.NewGoodEditor .cancel_A').on('click', function () {
        CodeSame($('.NewGoodEditor'));
        $('.replyBack').css({
            color: ''
        });
    });
}
function getNewEditor(n,type,href,name,rcommentid,reid) {
    var NewGoodEditor = $('<div class="NewGoodEditor"><div class="NewEditor">' +
        '<div id="Newtoolbar" class="NewToolbar" style="width:100%;background: #fff;border-bottom: 1px solid #DDD;"></div>' +
        '<div id="NewUser_edit" class="EditorNew" style="width:100%;height:200px;display: flex;justify-content: center;' +
        'align-content: center;flex-wrap:wrap;background:#fff;"></div></div></div>');
    $('.replyBack').css({
        color: ''
    });
    CodeSame($('.NewGoodEditor'));
    n.parent().after(NewGoodEditor);
    setTimeout(function () {
        $('.NewGoodEditor').css({
            opacity: '1',
            top: '0'
        });
        $('.NewGoodEditor .cancel_A').css({
            display: 'flex'
        });
        $('.NewGoodEditor .publish_A').html('<i class="iconfont">&#xe815;</i>发送');
    }, 10);
    var M = window.wangEditor;
    var NewEditor = new M('#Newtoolbar', '#NewUser_edit');
    NewEditor.customConfig.menus = ['bold', 'italic', 'link', 'emoticon'];
    NewEditor.customConfig.zIndex = 0;
    NewEditor.create();
    cancel();

    //解决火狐不能自动去除占位符的问题
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isFF = userAgent.indexOf('Firefox') > -1; //判断是否Firefox浏览器
    if (isFF) {
        $('#NewUser_edit .w-e-text p').eq(0).find('br').css({
            'display': 'none',
        });
    }
    $('.NewGoodEditor .publish_A').on('click', function () {
        var Now = new Date();
        var Year = Now.getFullYear();
        var Month = Now.getMonth() + 1;
        var Day = Now.getDate();
        var Hour = Now.getHours();
        var Minute = Now.getMinutes();
        var Second = Now.getSeconds();
        var ContentNew = $('.NewGoodEditor .w-e-text').html();
        console.log(ContentNew)
        if (ContentNew == '<p><br></p>') {
            layer.msg('请您写一点内容再发送，当前状态不可发送');
        } else {
            if (type == "reCom"){
                $.ajax({
                    url:"../comment",
                    type:"post",
                    async:false,
                    data:{"type":"docR","objid":reid,"content":ContentNew},
                    success:function (data) {
                        for(var i in data){
                            console.log(n.parent().parent().children(".insertComment"));
                            if (n.parent().parent().children(".insertComment").length > 0){
                                n.parent().parent().children(".insertComment").last().after('<div class="insertComment"><span style="display: none" class="ReID">'+i+'</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+USERDATA.id+'">'+USERDATA.nickname+'</a>：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="'+href+'">@'+name+'</a></span><p class="TwoSecond">'+data[i]+'</p><span class="oneSpanTWO" style="padding-right: 5px;">'+Year+'/'+Month+'/'+Day+'</span><span class="TwoSpanTWO">'+Hour+':'+Minute+':'+Second+'</span><a href="javascript:;" class="ADDCommit">评论</a><a href="javascript:;" class="DEl">删除</a></div>')
                            } else {
                                n.parent().after('<div class="insertComment"><span style="display: none" class="ReID">'+i+'</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+USERDATA.id+'">'+USERDATA.nickname+'</a>：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="'+href+'">@'+name+'</a></span><p class="TwoSecond">'+data[i]+'</p><span class="oneSpanTWO" style="padding-right: 5px;">'+Year+'/'+Month+'/'+Day+'</span><span class="TwoSpanTWO">'+Hour+':'+Minute+':'+Second+'</span><a href="javascript:;" class="ADDCommit">评论</a><a href="javascript:;" class="DEl">删除</a></div>')
                            }
                        }
                    }
                });
            } else if (type == "RRCom"){
                $.ajax({
                    url:"../reCom",
                    type:"post",
                    async:false,
                    data:{"type":"docRR","objid":rcommentid,"content":ContentNew,"rcommentid":reid},
                    success:function (data) {
                        for (var i in data) {
                            n.parent().parent().children(".insertComment").last().after('<div class="insertComment"><span style="display: none" class="ReID">' + i + '</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id=' + USERDATA.id + '">' + USERDATA.nickname + '</a>：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="' + href + '">@' + name + '</a></span><p class="TwoSecond">' + data[i] + '</p><span class="oneSpanTWO" style="padding-right: 5px;">' + Year + '/' + Month + '/' + Day + '</span><span class="TwoSpanTWO">' + Hour + ':' + Minute + ':' + Second + '</span><a href="javascript:;" class="ADDCommit">评论</a><a href="javascript:;" class="DEl">删除</a></div>')
                        }
                    }
                });
            }

        }
        $('.NewGoodEditor .w-e-text').html('<p><br></p>');
        cancel();
        $('.DEl').unbind('click').on('click', function () {
            var This = $(this);
            layer.confirm('确定要删除此评论吗?', {
                btn: ['确定', '取消'], //按钮
                title: '提示'
            }, function (index) {
                This.parent().remove();
                layer.close(index);
            });
        }); //删除评论
        $('.ADDCommit').on('click', function () {
            getNewEditor($(this),"RRCom",$(this).parent().children(".twoUser").children("a").eq(0).attr("href"),$(this).parent().children(".twoUser").children("a").eq(0).text(),$(this).parent().parent().parent().children(".ComID").text(),$(this).parent().children(".ReID").text());
        });
    });
}
function CodeSame(n) {
  $('.NewGoodEditor').css({
    opacity: '',
    top: '',
  });
  $('.NewGoodEditor .w-e-text').remove();
  n.remove();
}

$(document).on('click', '.toolBar_Btn a,.NewEditor .publish_A,.layui-layer-btn0', function () {
    $('body').getNiceScroll().resize();
});
//重载滚动条
var userinfor = $("#userInformation");
$(document).ready(function () {
   //获取url内容取得文章内容
    $.ajax({
        url:"../doc"+ window.location.search,
        type:"get",
        async:false,
        success:function (data) {
            DOCDATA = data;
            console.log(data);
            var indiv = '<a class="userName" href="OthersCenter.html?id=' + data.mulUser.id + '">' + data.mulUser.nickname
                + '</a><a class="focus" href="javascript:;"><i class="iconfont">&#xe604;</i><span>关注</span></a>';
            $(".top_message").append(indiv);
            $(".focus").click(function () {
               layer.msg("请先登录！")
            });
            userinfor.children(".photoIMg").children("img").attr("src",data.mulUser.headimage);
            var bMsg = $(".bottom_message");
            var Doctime = new Date(data.document.date);
            bMsg.children("span").eq(0).text(Doctime.getFullYear()+"/"+(Doctime.getMonth()+1)+"/"+Doctime.getDate());
            bMsg.children("span").eq(1).text("阅读"+data.document.sawnum);
            bMsg.children("span").eq(2).text("评论"+data.document.commentnum);
            bMsg.children("span").eq(3).text("喜欢"+data.document.upvotenum);
            $(".w-e-text").children("h1").text(data.document.title);
            $("#docid").text(data.document.id);
            initNum = data.document.upvotenum;
            $('.TwoPart a').eq(0).children("span").text(data.document.upvotenum);
            $("#ContainEditor").append(data.document.content);
            var titleImg = $('#ContainEditor img').eq(0).attr('src');
            var getFirstContent = $('.w-e-text h1').eq(0).html();
            //配置share.js的参数
            socialShare('.social-share', {
                title: getFirstContent, //分享的标题
                image: titleImg, //分享的图片  一般是正文的第一张图片
                url: UrlAddress, //填写当前页面的地址     window.location.href      分享的地址
                description: getFirstContent //分享到额描述
            });
        },error:function () {
            console.log("获取文章信息失败！");
        }
    });
//页面加载时获取第一页的评论内容
    $.ajax({
        url:"/getDComment/"+DOCDATA.document.id+"/1",
        type:"get",
        async:false,
        success:function (data) {
            console.log(data);
            if (data != ""){
                $('.commentsNum span').html(data[0].totalNum);
                showComment(data);
                if (data.length > 12){
                    $(".commentsDetail").after('<div class="pagingTool"></div>');
                    $(".pagingTool").Paging({
                        pagesize: 12,
                        count:data.length,
                        prevTpl: '<i class="iconfont">&#xe78c;</i>',
                        nextTpl: '<i class="iconfont">&#xe77c;</i>',
                        firstTpl: '<i class="iconfont">&#xe609;</i>',
                        lastTpl: '<i class="iconfont">&#xe6de;</i>',
                        callback:function (page,size,count) {
                            console.log("num: "+page);
                            $('.commentsList').empty();
                            $.ajax({
                                url:"getDComment/"+DOCDATA.document.id+"/"+page,
                                type:"get",
                                success:function (data) {
                                    showComment(data);
                                },error:function() {
                                    console.log("请求评论失败！")
                                }
                            })
                        }
                    });
                }
                $('.toolBar_Btn a').on('click', function () {
                    layer.msg("请先登录！");
                });
                $('.ADDCommit').on('click',function () {
                    layer.msg("请先登录！");
                });
                $('.DEl').on('click',function () {
                    layer.msg("请先登录！");
                })
            }
        },error:function () {
            console.log("加载页面时获取评论失败！")
        }
    });
    //获取登陆信息
    $.ajax({
        url:"../user/isLogin",
        type:"get",
        success:function (data) {
            if (data != ""){
                USERDATA = data;
                loginSuccess(data);
            }
        },error:function () {
            console.log("获取登陆信息失败！")
        }
    });


});
function loginSuccess(data) {
    $.ajax({
        url:"../doc/is?docid="+DOCDATA.document.id,
        type:"get",
        success:function (data) {
            if (data[0]) {
                onOff = true;
                $('.focus').css({
                    background: '#869B74'
                });
                $('.focus .iconfont').html('&#xe642;');
                $('.focus span').html('已关注');
            }
            if (data[1]){
                $('.TwoPart a').eq(0).css("color", '#FF9500');
                isGood = true;
            }
            if (data[2]){
                $('.TwoPart a').eq(1).css("color", '#FF9500');
                isBad = true;
            }
        }
    });

    $(".layui-layer-close").click();
    $(".last_li").empty();
    var image = "../img/14.png";
    if (data.headimage != null) image = data.headimage;
    $(".last_li").append('<div class="location_div_a"><a href="personalCenter.html" class="photo_cicle" target="_blank"><img src="'+image+'"> </a> <div class="msg_index_dance">进入个人中心 </div> </div> <div class="editor_article"> <a href="preset.html" target="_blank"> <span> <i class="iconfont">&#xe645;</i></span>写文章</a></div>');
    //点赞事件
    $('.TwoPart a').off('click');
    $('.TwoPart a').eq(0).on('click', function () {
        if (isGood) {
            isGood = false;
            Change(1, $(this), 1);
        } else {
            isGood = true;
            Change(0, $(this), 1);
        }
        $.ajax({
            url:"../upvote?type=doc&objid="+DOCDATA.document.id,
            type:"put",
            success:function () {
            },error:function () {
                console.log("文章点赞出错！")
            }
        });
    });
    $('.TwoPart a').eq(1).on('click', function () {
        if (isBad) {
            isBad = false;
            Change(1, $(this), 0);
        } else {
            isBad = true;
            Change(0, $(this), 0);
        }
        $.ajax({
            url:"../col/doc?docid="+DOCDATA.document.id,
            type:"put",
            success:function () {
            },error:function () {
                console.log("文章收藏出错！")
            }
        });
    });
    //关注事件
    $(".focus").off("click");
    $('.focus').on('click', function () {
        if (onOff) {
            onOff = false;
            $('.focus').css({
                background: '#869B74'
            });
            $('.focus .iconfont').html('&#xe642;');
            $('.focus span').html('已关注');
        } else {
            onOff = true;
            $('.focus').css({
                background: ''
            });
            $('.focus .iconfont').html('&#xe604;');
            $('.focus span').html('关注');
        }
        $.ajax({
            url:"../col/user?cuserid="+DOCDATA.mulUser.id,
            type:"put",
            success:function () {
            },error:function () {
                console.log("文章收藏出错！")
            }
        });
    });
    //评论事件
    $(".PhotoImg img").attr("src",data.headimage);
    $('.NewEditor .publish_A').off('click');
    $('.NewEditor .publish_A').on('click', function () {
        var Now = new Date();
        var Year = Now.getFullYear();
        var Month = Now.getMonth() + 1;
        var Day = Now.getDate();
        var Hour = Now.getHours();
        var Minute = Now.getMinutes();
        var Second = Now.getSeconds();
        var image = "../img/14.png";
        if (data.headimage != null) image = data.headimage;
        var addComments = $(
            '<li class="Number"><div class="commentsMessage"><span style="display: none" class="ComID"></span><div class="topMessage"><a href="OthersCenter.html?id=' + USERDATA.id + '"><img src="'+image+'"></a>' +
            '<div class="rightMessage"><div class="commentsName">'+data.nickname+'</div><div class="timeMessage"><span></span><span></span>' +
            '</div></div></div><div class="bottomMessage"><p class="OneFirst"></p><div class="toolBar_Btn"><a href="javascript:;" class="upComment"><i class="iconfont">&#xe606;</i>' +
            '<span class="goodNum">0</span><span>人赞</span></a><a href="javascript:;" class="reComment"><i class="iconfont replyBack">&#xe61b;</i><span>回复</span>' +
            '</a><a href="javascript:;" class="DeleteCommentSpecial"><i class="iconfont">&#xe622;</i>删除评论</a></div></div></div></li>'
        );
        var Content = $('.NewEditor .w-e-text').html();
        console.log("Content: "+Content);
        if (Content == '<p><br></p>') {
            layer.msg("请输入内容后再发送！");
        } else {
            $.ajax({
                url:"../comment",
                type:"post",
                data:{"type":"doc","objid":DOCDATA.document.id,"content":Content,"ruserid":DOCDATA.mulUser.id},
                success:function (data) {
                    $('.commentsList').bind('DOMNodeInserted', function () {
                        var NumNumber = $('.commentsList li').length;
                        $('.commentsNum span').html(NumNumber); //用于记录有多少条的评论
                    });
                    $('.commentsList').prepend(addComments);
                    for(var i in data){
                        $('.commentsList li:first-child .ComID').text(i);
                        $('.commentsList li:first-child .OneFirst').html(data[i]);
                    }
                    $('.commentsList li:first-child .timeMessage span').eq(0).html('' + Year + '/' + Month + '/' + Day + '');
                    $('.commentsList li:first-child .timeMessage span').eq(1).html('' + addZero(Hour) + ':' + addZero(Minute) + ':' + addZero(Second) + '');
                    //点赞事件
                    $(".toolBar_Btn a").off("click");
                    $('.upComment').on('click', function () {
                        if ($(this).children("i")[0].style.color == "") {
                            onNotSame($(this).find('.iconfont'), 1);
                            $(this).find('.goodNum').html(parseInt($(this).find(".goodNum").text())+1);
                        } else {
                            onNotSame($(this).find('.iconfont'), 0);
                            $(this).find('.goodNum').html(parseInt($(this).find(".goodNum").text())-1);
                        }
                    });
                    //回复
                    $('.reComment').on('click', function () {
                        getNewEditor($(this),"reCom",$(this).parent().parent().parent().children(".topMessage").children("a").eq(0).attr("href"),$(this).parent().parent().parent().find(".commentsName").text(),"",$(this).parent().parent().parent().children(".ComID").text());
                    });

                    //删除评论
                    $('.DeleteCommentSpecial').unbind('click').on('click', function () {
                        var This = $(this);
                        layer.confirm('确定要删除此评论吗?', {
                            btn: ['确定', '取消'], //按钮
                            title: '提示'
                        }, function (index) {
                            $.ajax({
                                url:"../deleteC?type=doc&docid="+DOCDATA.document.id+"&commentid="+This.parent().parent().parent().children(".ComID").text(),
                                type:"delete",
                                success:function (data) {
                                    if (data == "Y"){
                                        This.parent().parent().parent().parent().remove();
                                        var NumCommentSpan = parseInt($('.commentsNum span').html());
                                        $('.commentsNum span').html(--NumCommentSpan);
                                        layer.close(index);
                                    }
                                },error:function () {
                                    console.log("删除评论失败！")
                                }
                            })
                        });
                    });
                }
            });

        }
        $('.NewEditor .w-e-text').html('<p><br></p>');

    });
    var GoodNum = parseInt($('.goodNum').html());
    $(".toolBar_Btn a").off("click");
    $('.upComment').on('click', function () {
        if ($(this).children("i")[0].style.color == "") {
            onNotSame($(this).find('.iconfont'), 1);
            $(this).find('.goodNum').html(parseInt($(this).find(".goodNum").text())+1);
        } else {
            onNotSame($(this).find('.iconfont'), 0);
            $(this).find('.goodNum').html(parseInt($(this).find(".goodNum").text())-1);
        }
    });

    //回复
    $('.reComment').on('click', function () {
        getNewEditor($(this),"reCom",$(this).parent().parent().parent().children(".topMessage").children("a").eq(0).attr("href"),$(this).parent().parent().parent().find(".commentsName").text(),"",$(this).parent().parent().parent().children(".ComID").text());
    });

    //删除评论
    $('.DeleteCommentSpecial').unbind('click').on('click', function () {
        var This = $(this);
        layer.confirm('确定要删除此评论吗?', {
            btn: ['确定', '取消'], //按钮
            title: '提示'
        }, function (index) {
            $.ajax({
                url:"../deleteC?type=doc&docid="+DOCDATA.document.id+"&commentid="+This.parent().parent().parent().children(".ComID").text(),
                type:"delete",
                success:function (data) {
                    if (data == "Y"){
                        This.parent().parent().parent().parent().remove();
                        var NumCommentSpan = parseInt($('.commentsNum span').html());
                        $('.commentsNum span').html(--NumCommentSpan);
                        layer.close(index);
                    }
                },error:function () {
                    console.log("删除评论失败！")
                }
            })
        });
    });

    $(".ADDCommit").off("click");
    $('.ADDCommit').on('click', function () {
        getNewEditor($(this),"RRCom",$(this).parent().children(".twoUser").children("a").eq(0).attr("href"),$(this).parent().children(".twoUser").children("a").eq(0).text(),$(this).parent().parent().parent().children(".ComID").text(),$(this).parent().children(".ReID").text());
    });

    //如果文章用户和登录用户相同。显示编辑按钮 和 删除按钮
    showCDButton(data);
    //如果评论用户 登录用户相同。显示删除按钮

    //增加浏览历史信息
}
//显示评论的方法
function showComment(data) {
    for(var i = 0;i < data.length;i ++){
        var image = "../img/14.png";
        var DCtime = new Date(data[i].docCUser.docComment.date);
        if (data[i].docCUser.mulUser.headimage != null) image = data[i].docCUser.mulUser.headimage;
        $('.commentsList').append('<li class="Number"><div class="commentsMessage"><span style="display: none" class="ComID">'+data[i].docCUser.docComment.id+'</span><div class="topMessage"><a href="OthersCenter.html?id=' + data[i].docCUser.mulUser.id + '"><img src="'+image+'"></a>' +
            '<div class="rightMessage"><div class="commentsName">'+data[i].docCUser.mulUser.nickname+'</div><div class="timeMessage"><span>'+DCtime.getFullYear()+'/'+DCtime.getMonth()+'/'+DCtime.getDate()+'</span><span>'+DCtime.getHours()+':'+DCtime.getMinutes()+':'+DCtime.getMilliseconds()+'</span>' +
            '</div></div></div><div class="bottomMessage"><p class="OneFirst">'+data[i].docCUser.docComment.content+'</p><div class="toolBar_Btn"><a href="javascript:;" class="upComment"><i class="iconfont">&#xe606;</i>' +
            '<span class="goodNum">'+data[i].docCUser.docComment.upvotenum+'</span><span>人赞</span></a><a href="javascript:;" class="reComment"><i class="iconfont replyBack">&#xe61b;</i><span>回复</span>' +
            '</a><a style="display: none;" href="javascript:;" class="DeleteCommentSpecial"><i class="iconfont">&#xe622;</i>删除评论</a></div></div></div></li>');
        if (data[i].docRUsers != null){
            console.log(data[i].docRUsers);
            for (var j = 0;j < data[i].docRUsers.length;j ++){
                var DRtime = new Date(data[i].docRUsers[j].docRelay.date);
                $(".Number").last().children(".commentsMessage").children(".bottomMessage").append('<div class="insertComment"><span style="display: none" class="ReID">'+data[i].docRUsers[j].docRelay.id+'</span><span class="twoUser"><a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+data[i].docRUsers[j].id+'">'+data[i].docRUsers[j].nickname+'</a>：回复<a style="color: #2D93CA;display: inline;" target="_blank" href="OthersCenter.html?id='+data[i].docRUsers[j].rid+'">@'+data[i].docRUsers[j].rname+'</a></span><p class="TwoSecond">'+data[i].docRUsers[j].docRelay.content+'</p><span class="oneSpanTWO" style="padding-right: 5px;">'+DRtime.getFullYear()+'/'+DRtime.getMonth()+'/'+DRtime.getDate()+'</span><span class="TwoSpanTWO">'+DRtime.getHours()+':'+DRtime.getMinutes()+':'+DRtime.getMilliseconds()+'</span><a href="javascript:;" class="ADDCommit">评论</a><a href="javascript:;" class="DEl">删除</a></div>')
            }
        }
        if (data[i].up){$(".upComment").last().children("i").css('color', '#00B38C')}
        if (USERDATA != null){
            showCDButton(USERDATA)
        }
    }
}

function showCDButton(data) {
    //如果文章用户和登录用户相同。显示编辑按钮 和 删除按钮
    if(data.id == DOCDATA.mulUser.id){
        $("#userInformation").append('<a href="preset.html?id='+DOCDATA.document.id+'" target="_blank" class="editorAgain">编辑文章</a>');
        $('.DEl').unbind('click').on('click', function () {
            var This = $(this);
            layer.confirm('确定要删除此评论吗?', {
                btn: ['确定', '取消'], //按钮
                title: '提示'
            }, function (index) {
                $.ajax({
                    url:"../deleteR?type=doc&docid="+DOCDATA.document.id+"&commentid="+This.parent().parent().parent().children(".ComID").text()+"&rcommentid="+This.parent().children(".ReID").text(),
                    type:"delete",
                    success:function (data) {
                        console.log(data);
                        var list = This.parent().parent().find(".ReID");
                        console.log(list);
                        for (var i = 0;i < data.length;i ++){
                            for(var j = 0;j < list.length;j ++){
                                if (list[j].innerText == data[i]){
                                    list.eq(j).parent().remove();
                                    break;
                                }
                            }
                        }
                        layer.close(index);
                    },error:function () {
                        console.log("删除回复出错！")
                    }
                });
            });
        }); //删除评论

        $('.insertComment').hover(function () {
            $(this).children(".DEl").css({
                "display": "block",
                "padding-right": "30px"
            })
        },function () {
            $(this).children(".DEl").css({
                "display": "none"
            })
        });

        $('.DeleteCommentSpecial').css("display","inline-flex")
    }else{
        var ccclist = $(".commentsName");
        var rrrlist = $(".twoUser");
        for(var i = 0;i < ccclist.length;i ++){
            if (ccclist[i].innerText == data.nickname){
                ccclist.eq(i).parent().parent().parent().find(".DeleteCommentSpecial").css("display","inline-flex");
            }
        }
        for(var i = 0;i < rrrlist.length;i ++){
            if (rrrlist.eq(i).children("a").eq(0).text() == data.nickname){
                console.log(rrrlist.eq(i).children("a").eq(0).text());
                rrrlist.eq(i).parent().hover(function () {
                    $(this).children(".DEl").css({
                        "display": "block",
                        "padding-right": "30px"
                    })
                },function () {
                    $(this).children(".DEl").css({
                        "display": "none"
                    })
                });
                rrrlist.eq(i).parent().children(".DEl").unbind('click').on('click', function () {
                    var This = $(this);
                    layer.confirm('确定要删除此评论吗?', {
                        btn: ['确定', '取消'], //按钮
                        title: '提示'
                    }, function (index) {
                        $.ajax({
                            url:"../deleteR?type=doc&docid="+DOCDATA.document.id+"&commentid="+This.parent().parent().parent().children(".ComID").text()+"&rcommentid="+This.parent().children(".ReID").text(),
                            type:"delete",
                            success:function (data) {
                                console.log(data);
                                var list = This.parent().parent().find(".ReID");
                                console.log(list);
                                for (var i = 0;i < data.length;i ++){
                                    for(var j = 0;j < list.length;j ++){
                                        if (list[j].innerText == data[i]){
                                            list.eq(j).parent().remove();
                                            break;
                                        }
                                    }
                                }
                                layer.close(index);
                            },error:function () {
                                console.log("删除回复出错！")
                            }
                        });
                    });
                }); //删除评论
            }
        }
    }
}
