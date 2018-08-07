package com.example.multimedia.controller;

import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.SearchHistory;
import com.example.multimedia.domain.Video;
import com.example.multimedia.domain.returnMessage.DocUserView;
import com.example.multimedia.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {
    //登录时删除文章、视频、问答浏览记录，点击搜索时，删除搜索浏览记录
    @Autowired
    private HistoryService historyService;

    /**
     * 增加浏览文章历史
     * @param docid
     */
    @PutMapping("/doc/{docid}")
    public void dHistory(@PathVariable long docid){
        historyService.dhistory(docid);
    }

    /**
     * 得到文章浏览历史
     * @return
     */
    @GetMapping("/doc")
    public List<DocUserView> getDHistory(){
        return historyService.getDHistory();
    }

    /**
     * 删除文章浏览记录
     */
    @DeleteMapping("/ddoc")
    public void ddoc(){}

    /**
     * 增加浏览问答历史
     * @param forumid
     */
    @PutMapping("/forum/{forumid}")
    public void fHistory(@PathVariable long forumid){
        historyService.fhistory(forumid);
    }

    /**
     * 得到问答浏览历史
     * @return
     */
    @GetMapping("/forum")
    public List<Forum> getFHistory(){
        return historyService.getFHistory();
    }

    /**
     * 删除问答浏览记录
     */
    @DeleteMapping("/dforum")
    public void dforum(){

    }

    /**
     * 增加浏览视频历史
     * @param videoid
     */
    @PutMapping("/video/{videoid}")
    public void vHistory(@PathVariable long videoid){
        historyService.vhistory(videoid);
    }

    //得到视频浏览历史
    @GetMapping("/video")
    public List<Video> getVHistory(){
        return historyService.getVHistory();
    }

    /**
     * 删除视频浏览记录
     */
    @DeleteMapping("/dvideo")
    public void dvideo(){}

    /**
     * 增加搜索历史
     * @param key
     */
    @PutMapping(value = "/search",params = "key")
    public void sHistory(String key){
        historyService.shistory(key);
    }

    @GetMapping("/search")
    public List<SearchHistory> getSHistory(){
        return historyService.getSHistory();
    }

    /**
     * 删除搜索记录
     */
    @DeleteMapping("/dsearch")
    public void dsearch(){}
}
