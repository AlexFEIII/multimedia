package com.example.multimedia.controller;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.returnMessage.FKind;
import com.example.multimedia.domain.returnMessage.ForumKNum;
import com.example.multimedia.domain.returnMessage.ForumUser;
import com.example.multimedia.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("/forum")
@RestController
public class ForumController {

    @Autowired
    private ForumService forumService;

    /*
     * 获得所有文章
     * 需要参数：页码，个数，排序方式，排序依靠的关键字
     * */
    @PostMapping("/getAll")
    public List<ForumUser> getAllForum(@RequestParam int pageNum,
                                 @RequestParam int size,
                                 @RequestParam String sort,
                                 @RequestParam String key){
        Sort.Direction direction = null;
        if (sort.equals("asc"))
            direction = Sort.Direction.ASC;
        else
            direction = Sort.Direction.DESC;
        return forumService.getAllForum(pageNum,size,direction,key);
    }

    /**
     * 取得一篇问答内容
     * @param id
     * @return
     */
    @GetMapping(params = "id")
    public ForumUser getOneForum(long id){
        return forumService.getOneForum(id);
    }

    /*
     * 增加文章
     * 需要参数：文章标题，文章概要（可空），文章内容，文章图片（可空），类别
     * */
    @PostMapping("/add")
    public String addDoc(@RequestParam String title,
                         @RequestParam(value = "summary",required = false) String summary,
                         @RequestParam String content,
                         @RequestParam(value = "image",required = false) MultipartFile image,
                         @RequestParam String type){
        return forumService.addForum(title,summary,content,image,type);
    }

    /**
     * 取得我的论坛文章
     * @return
     */
    @GetMapping("/mine")
    public List<Forum> getMineForum(){
        return forumService.getMineForum();
    }

    /**
     * 取得别人的问答
     * @param id 用户id
     * @return
     */
    @GetMapping(value = "others",params = "id")
    public List<Forum> getOthersForum(long id){
        return forumService.getOthersForum(id);
    }

    /*
     * 修改文章，可修改标题，概要，内容，图片，类别
     * */
    @PostMapping("/change")
    public String changeDoc(@RequestParam long documentid,
                            @RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "summary",required = false) String summary,
                            @RequestParam(value = "content",required = false) String content,
                            @RequestParam(value = "image",required = false) MultipartFile image,
                            @RequestParam(value = "type",required = false) String type){
        return forumService.changeForum(documentid,title,summary,content,image,type);
    }

    /**
     * 增加问答问题
     * @param forumid 议题的id
     * @param title 问题的题目
     * @return "ILLEGAL"非法。“Y”正常
     */
    @PutMapping(value = "addPro",params = {"forumid","title"})
    public String addPro(long forumid,String title){
        return forumService.addPro(forumid,title);
    }

    /**
     * 增加问答评论
     * @param forumid 议题id
     * @param rcommentid 被回复的评论id（如果是评论则为0）
     * @param content 回复内容
     * @return
     */
    @PutMapping(value = "addCom",params = {"forumid","rcommentid","content"})
    public Map<Long,String> addCom(long forumid, long rcommentid, String content){
        return forumService.addComment(forumid,rcommentid,content);
    }

    /**
     * 删除评论
     * @param forumid
     * @param commentid
     * @return
     */
    @DeleteMapping(value = "delCom",params = {"forumid","commentid"})
    public List<Long> delCom(long forumid,long commentid){
        return forumService.deleteComment(forumid,commentid);
    }

    /*
    * 设置最佳评论
    * */
    @PostMapping("/best")
    public String best(@RequestParam long forumid,
                       @RequestParam long commentid){
        return forumService.best(forumid,commentid);
    }

    /*
    * 删除问答
    * */
    @DeleteMapping("/delete/{id}")
    public String deleteForum(@PathVariable("id") long id){
        return forumService.deleteForum(id);
    }

    /**
     * 增加问答类别
     * @param kind
     */
    @PutMapping(value = "/addKind",params = {"kind"})
    public void addKind(String kind,@RequestParam MultipartFile file){
        forumService.addKind(kind,file);
    }

    /**
     * 返回所有问答及其信息
     * @return
     */
    @GetMapping("/allkind")
    public List<ForumKNum> allKind(){
        return forumService.allKind();
    }

    @GetMapping(value = "/kind",params = {"id","pagenum"})
    public FKind getKind(int id,int pagenum){
        return forumService.getKind(id,pagenum);
    }
}
