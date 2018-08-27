package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ForumComment {
    @Id
    @GeneratedValue
    private Long id;
    //评论内容
    private String content;
    //评论的用户
    private long userid;
    //被评论的用户
    private long relayid;
    //被回复的评论id
    private long rcommentid;
    //评论所在的议题id
    private long forumid;
    private Date date;

    public ForumComment(){}
    public ForumComment(String content,long userid,long relayid,long rcommentid,long forumid){
        this.content = content;
        this.userid = userid;
        this.relayid = relayid;
        this.rcommentid = rcommentid;
        this.forumid = forumid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "ForumComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userid=" + userid +
                ", relayid=" + relayid +
                ", rcommentid=" + rcommentid +
                ", forumid=" + forumid +
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getRelayid() {
        return relayid;
    }

    public void setRelayid(long relayid) {
        this.relayid = relayid;
    }

    public long getRcommentid() {
        return rcommentid;
    }

    public void setRcommentid(long rcommentid) {
        this.rcommentid = rcommentid;
    }

    public long getForumid() {
        return forumid;
    }

    public void setForumid(long forumid) {
        this.forumid = forumid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
