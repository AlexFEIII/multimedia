package com.example.multimedia.service;

public interface HistoryService {
    //增加文章历史
    void dhistory(long docid);
    //删除文章历史
    void ddoc();
    //增加问答历史
    void fhistory(long forumid);
    //删除问答历史
    void dforum();
    //增加视频历史
    void vhistory(long videoid);
    //删除视频历史
    void dvideo();
    //增加搜索历史
    void shistory(String key);
    //删除搜索历史
    void dsearch();
}
