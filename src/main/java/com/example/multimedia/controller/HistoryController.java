package com.example.multimedia.controller;

import com.example.multimedia.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("key:"+key);
        historyService.shistory(key);
    }

    /**
     * 删除搜索记录
     */
    @DeleteMapping("/dsearch")
    public void dsearch(){}
}
