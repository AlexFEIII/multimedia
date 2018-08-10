package com.example.multimedia.service;

public interface CollectService {
    //改变文章收藏
    void changeDocC(long docid);
    //改变问答收藏
    void changeForumC(long forumid);
    //改变视频收藏
    void changeVideoC(long videoid);
    //改变用户关注
    void changeUserC(long cuserid);
}
