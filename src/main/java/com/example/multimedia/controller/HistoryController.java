package com.example.multimedia.controller;

import com.example.multimedia.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    /**
     * 增加浏览文章历史
     * @param docid
     */
    public void dHistory(long docid){
        historyService.dhistory(docid);
    }

    /**
     * 增加浏览问答历史
     * @param forumid
     */
    public void fHistory(long forumid){
        historyService.fhistory(forumid);
    }

    /**
     * 增加浏览视频历史
     * @param videoid
     */
    public void vHistory(long videoid){
        historyService.vhistory(videoid);
    }

    /**
     * 增加搜索历史
     * @param key
     */
    public void sHistory(String key){
        historyService.shistory(key);
    }
}
