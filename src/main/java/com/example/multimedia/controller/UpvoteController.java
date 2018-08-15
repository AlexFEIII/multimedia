package com.example.multimedia.controller;

import com.example.multimedia.service.UpvoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpvoteController {
    @Autowired
    private UpvoteService upvoteService;

    /**
     * 点赞
     * @param type doc文章 DComment文章平评论 forum\video相同
     * @param objid
     */
    @PutMapping(value = "upvote",params = {"type","objid"})
    public void upvote(String type,long objid){
        upvoteService.upvote(type,objid);
    }

}
