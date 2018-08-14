package com.example.multimedia.controller;

import com.example.multimedia.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/col")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 如果已经收藏取消收藏，如果没有增加收藏
     * @param docid
     */
    @PutMapping(value = "/doc",params = "docid")
    public void changeDocC(long docid){
        collectService.changeDocC(docid);
    }

    /**
     * 改变文章收藏
     * @param forumid
     */
    @PutMapping(value = "/forum",params = "forumid")
    public void changeForumC(long forumid){
        collectService.changeForumC(forumid);
    }

    /**
     * 改变视频收藏
     * @param videoid
     */
    @PutMapping(value = "/video",params = "videoid")
    public void changeVideoC(long videoid){
        collectService.changeVideoC(videoid);
    }

    /**
     * 关注用户
     * @param cuserid
     */
    @PutMapping(value = "/user",params = "cuserid")
    public void changeUserC(long cuserid){
        collectService.changeUserC(cuserid);
    }
}
