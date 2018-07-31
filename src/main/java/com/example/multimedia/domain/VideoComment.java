package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 视频评论表
* */
@Entity
public class VideoComment {
    @Id
    @GeneratedValue
    private Long id;
    //评论的视频编号
    private long videoid;
    //评论内容
    private String content;
    //评论的用户id
    private long userid;
    //被评论的用户id
    private long replyid;
    //时间
    private Date date;

    public VideoComment(){}

    public VideoComment(String content,long videoid,long userid,long replyid){
        this.content = content;
        this.videoid = videoid;
        this.userid = userid;
        this.replyid = replyid;
    }

    @Override
    public String toString() {
        return "VideoComment{" +
                "id=" + id +
                ", videoid=" + videoid +
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

    public long getVideoid() {
        return videoid;
    }

    public void setVideoid(long videoid) {
        this.videoid = videoid;
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
