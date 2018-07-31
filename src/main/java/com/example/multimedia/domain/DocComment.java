package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 用户发表文章的评论表
* */
@Entity
public class DocComment {
    @Id
    @GeneratedValue
    private Long id;
    //文章id
    private long docid;
    //评论内容
    private String content;
    //评论的用户id
    private long userid;
    //被评论的用户id
    private long replyid;
    //时间
    private Date date;

    public DocComment(){}

    public DocComment(String content,long docid,long userid,long replyid){
        this.content = content;
        this.docid = docid;
        this.userid = userid;
        this.replyid = replyid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "DocComment{" +
                "id=" + id +
                ", docid=" + docid +
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

    public long getDocid() {
        return docid;
    }

    public void setDocid(long docid) {
        this.docid = docid;
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
