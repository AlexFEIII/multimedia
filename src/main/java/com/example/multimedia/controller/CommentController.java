package com.example.multimedia.controller;

import com.example.multimedia.domain.*;
import com.example.multimedia.domain.returnMessage.*;
import com.example.multimedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 返回议题的问题
     * @param pagenum 获取问题页数（第一页为1）
     * @return 返回一个Map
     * */
    @GetMapping("/getFPro/{docid}/{pagenum}")
    public List<FCView> getFPro(@PathVariable long docid,@PathVariable int pagenum){
        return commentService.getForumPro(docid,pagenum);
    }

    /**
     * 返回一个议题问题的具体内容 以及 收录的第一页文章
     * @param id
     * @return
     */
    @GetMapping(value = "getOnePro",params = "id")
    public ForumProblemView getOnePro(long id){
        return commentService.getOnePro(id);
    }

    /**
     * 修改议题问题的简介
     * @param id 问题的id
     * @param content
     * @return 401说明权限错误 200说明返回成功 403表示有违规信息
     */
    @PutMapping(value = "proContent",params = {"id","content"})
    public Integer proContent(long id,String content){
        return commentService.proContent(id,content);
    }

    @PostMapping(value = "proimage",params = "id")
    public String proImage(long id,MultipartFile file){
        return  commentService.proImage(id,file);
    }

    /**
     * 返回评论回复
     * @param proid
     * @return
     */
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

    /**
     * 取得议题评论
     * @param forumid
     * @param pagenum
     * @return
     */
    @GetMapping("getFComment/{forumid}/{pagenum}")
    public List<ForumComView> getFComment(@PathVariable long forumid,@PathVariable int pagenum){
        return commentService.getForumComment(forumid,pagenum);
    }
}
