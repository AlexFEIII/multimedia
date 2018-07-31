package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 视频评论回复
* */
@Entity
public class VideoRelay {
    @Id
    @GeneratedValue
    private Long id;
    //评论内容
    private String content;
    //回复的评论 id
    private long commentid;
    //回复的回复id
    private long rcommentid;
    //评论的用户id
    private long userid;
    //被评论的用户id
    private long replyid;
    //时间
    private Date date;

    public VideoRelay(){}

    public VideoRelay(String content,long commentid,long userid,long replyid){
        this.content = content;
        this.commentid = commentid;
        this.userid = userid;
        this.replyid = replyid;
        this.date = new Date();
    }
    public VideoRelay(String content,long commentid,long rcommentid,long userid,long replyid){
        this.content = content;
        this.commentid = commentid;
        this.rcommentid = rcommentid;
        this.userid = userid;
        this.replyid = replyid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "VideoRelay{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", commentid=" + commentid +
                ", rcommentid=" + rcommentid +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public long getRcommentid() {
        return rcommentid;
    }

    public void setRcommentid(long rcommentid) {
        this.rcommentid = rcommentid;
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
