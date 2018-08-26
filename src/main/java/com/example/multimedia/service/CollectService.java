package com.example.multimedia.service;

public interface CollectService {
    //改变文章收藏
    void changeDocC(long docid);
    //改变文章专题收藏
    void changeDocK(String kind);
    //改变问答收藏
    void changeForumC(long forumid);
    //改变议题问题（评论）收藏
    void changeForumCC(long forumid,long cid);
    //改变视频收藏
    void changeVideoC(long videoid);
    //改变用户关注
    void changeUserC(long cuserid);
}
