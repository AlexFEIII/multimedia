package com.example.multimedia.service;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.SearchHistory;
import com.example.multimedia.domain.Video;
import com.example.multimedia.domain.returnMessage.DocUserView;

import java.util.List;

public interface HistoryService {
    //增加文章历史
    void dhistory(long docid);
    //得到文章历史
    List<DocUserView> getDHistory();
    //删除文章历史
    void ddoc();
    //增加问答历史
    void fhistory(long forumid);
    //得到问答历史
    List<Forum> getFHistory();
    //删除问答历史
    void dforum();
    //增加视频历史
    void vhistory(long videoid);
    //得到视频历史
    List<Video> getVHistory();
    //删除视频历史
    void dvideo();
    //增加搜索历史
    void shistory(String key);
    //得到搜索历史
    List<SearchHistory> getSHistory();
    //删除搜索历史
    void dsearch();
}
