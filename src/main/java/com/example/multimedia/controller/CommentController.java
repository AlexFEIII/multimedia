package com.example.multimedia.controller;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.*;
import com.example.multimedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * @param type 类型：doc 文章  forum 论坛文章  video 视频 docR 文章评论 forumR 论坛评论 videoR 视频评论
     * @param objid 该类型某对象的id
     * @param content 评论的内容
     * @return 敏感词过滤之后的评论内容
     */
    @PostMapping("/comment")
    public Map<Long,String> comment(@RequestParam String type,
                          @RequestParam long objid,
                          @RequestParam String content){
        return commentService.comment(type,objid,content);
    }

    /**
     *
     * @param type 类型：docRR 回复文章的回复 forumRR  videoRR
     * @param content 内容
     * @param objid 所在评论的id
     * @param rcommentid 回复的回复id
     * @return
     */
    @PostMapping("/reCom")
    public Map<Long,String> rRelay(@RequestParam String type,
                         @RequestParam String content,
                         @RequestParam long objid,
                         @RequestParam long rcommentid){
        return commentService.replyR(type,content,objid,rcommentid);
    }

    /**
     * 删除评论
     * @param type 类型：doc/forum/video
     * @param docid 文章id
     * @param commentid 评论id
     * @return
     */
    @DeleteMapping(value = "/deleteC",params = {"type","docid","commentid"})
    public String deleteC(String type, long docid, long commentid){
        return commentService.deleteComment(type,docid,commentid);
    }

    /**
     * 删除回复
     * @param type 类型：doc/forum/video
     * @param docid 文章id
     * @param commentid 评论id
     * @param rcommentid 回复id
     * @return
     */
    @DeleteMapping(value = "/deleteR",params = {"type","docid","commentid","rcommentid"})
    public List<Long> deleteR(String type, long docid, long commentid, long rcommentid){
        return commentService.deleteComment(type,docid,commentid,rcommentid);
    }

    /**
    * @param pagenum 获取评论页数（第一页为1）
     * @return 返回一个Map
    * */
    @GetMapping("/getDComment/{docid}/{pagenum}")
    public List<DCView> getDComment(@PathVariable long docid,@PathVariable int pagenum){
        return commentService.getDocComment(docid,pagenum);
    }

    /**
     * @param pagenum 获取评论页数（第一页为1）
     * @return 返回一个Map
     * */
    @GetMapping("/getFComment/{docid}/{pagenum}")
    public List<FCView> getFComment(@PathVariable long docid,@PathVariable int pagenum){
        return commentService.getForumComment(docid,pagenum);
    }

    @GetMapping("getFCRelay/{proid}")
    public List<ForumRUser> getFCRelay(@PathVariable long proid){
        return commentService.getForumCRelay(proid);
    }

    /**
     * @param pagenum 获取评论页数（第一页为1）
     * @return 返回一个Map
     * */
    @GetMapping("/getVComment/{docid}/{pagenum}")
    public List<VCView> getVComment(@PathVariable long docid,@PathVariable int pagenum){
        return commentService.getVideoComment(docid,pagenum);
    }
}
