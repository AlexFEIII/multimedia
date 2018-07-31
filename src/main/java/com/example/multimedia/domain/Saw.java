package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/*
* 记录用户浏览记录（按时间删除）
* */
@Entity
public class Saw {
    @Id
    @GeneratedValue
    private Long id;
    //用户编号
    private long userid;
    //看过的视频id
    private long videoid;
    //看过的文章id
    private long docid;
    //看过的论坛文章id
    private long forumid;
    //看的时间
    private Date date;

    @Override
    public String toString() {
        return "Saw{" +
                "id=" + id +
                ", userid=" + userid +
                ", videoid=" + videoid +
                ", docid=" + docid +
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getVideoid() {
        return videoid;
    }

    public void setVideoid(long videoid) {
        this.videoid = videoid;
    }

    public long getDocid() {
        return docid;
    }

    public void setDocid(long docid) {
        this.docid = docid;
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
