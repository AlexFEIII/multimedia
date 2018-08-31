package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 论坛文章问题
* */
@Entity
public class ForumProblem {
    @Id
    @GeneratedValue
    private Long id;
    //论坛文章id
    private long forumid;
    //问题题目
    private String title;
    //问题内容
    private String content;
    //提出问题的用户id
    private long userid;
    //被评论的用户id
    private long replyid;
    //点赞数
    private long upvotenum;
    //时间
    private Date date;
    //封面
    private String image;

    public ForumProblem(){}
    public ForumProblem(String title,long forumid,long userid,long replyid){
        this.title = title;
        this.forumid = forumid;
        this.userid = userid;
        this.replyid = replyid;
        this.date = new Date();
    }
    public ForumProblem(String title,String content, long forumid, long userid, long replyid){
        this.title = title;
        this.content = content;
        this.forumid = forumid;
        this.userid = userid;
        this.replyid = replyid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "ForumProblem{" +
                "id=" + id +
                ", forumid=" + forumid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", replyid=" + replyid +
                ", upvotenum=" + upvotenum +
                ", date=" + date +
                ", image='" + image + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getForumid() {
        return forumid;
    }

    public void setForumid(long forumid) {
        this.forumid = forumid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getReplyid() {
        return replyid;
    }

    public void setReplyid(long replyid) {
        this.replyid = replyid;
    }

    public long getUpvotenum() {
        return upvotenum;
    }

    public void setUpvotenum(long upvotenum) {
        this.upvotenum = upvotenum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
