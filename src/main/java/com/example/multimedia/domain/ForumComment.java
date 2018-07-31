package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 论坛文章评论
* */
@Entity
public class ForumComment {
    @Id
    @GeneratedValue
    private Long id;
    //论坛文章id
    private long forumid;
    //评论内容
    private String content;
    //评论的用户id
    private long userid;
    //被评论的用户id
    private long replyid;
    //时间
    private Date date;

    public ForumComment(){}

    public ForumComment(String content,long forumid,long userid,long replyid){
        this.content = content;
        this.forumid = forumid;
        this.userid = userid;
        this.replyid = replyid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "ForumComment{" +
                "id=" + id +
                ", forumid=" + forumid +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", replyid=" + replyid +
                ", date=" + date +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
