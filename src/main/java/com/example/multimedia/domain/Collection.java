package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 用户收藏表
* */
@Entity
public class Collection {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private long userid;
    //视频id
    private long videoid;
    //文章id
    private long documentid;
    //论坛文章id
    private long forumid;

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", userid=" + userid +
                ", videoid=" + videoid +
                ", documentid=" + documentid +
                ", forumid=" + forumid +
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

    public long getDocumentid() {
        return documentid;
    }

    public void setDocumentid(long documentid) {
        this.documentid = documentid;
    }

    public long getForumid() {
        return forumid;
    }

    public void setForumid(long forumid) {
        this.forumid = forumid;
    }
}
