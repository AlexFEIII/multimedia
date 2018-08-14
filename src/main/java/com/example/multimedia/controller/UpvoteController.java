package com.example.multimedia.controller;

import com.example.multimedia.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpvoteController {
    @Autowired
    private UpvoteService upvoteService;

    /*
    * 点赞功能
    * 需要参数：点赞类型，用户id，点赞的事物id
    * 点赞类型：文章、文章评论、论坛、论坛评论、视频
    **/
    @PutMapping(value = "upvote",params = {"type","objid"})
    public void upvote(String type,long objid){
        upvoteService.upvote(type,objid);
    }

}
