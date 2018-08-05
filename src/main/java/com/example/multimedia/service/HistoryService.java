package com.example.multimedia.service;

public interface HistoryService {
    //增加文章历史
    void dhistory(long docid);
    //增加问答历史
    void fhistory(long forumid);
    //增加视频历史
    void vhistory(long videoid);
    //增加搜索历史
    void shistory(String key);
}
