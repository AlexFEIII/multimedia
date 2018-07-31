package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 视频点赞
* */
@Entity
public class Videoupvote {
    @Id
    @GeneratedValue
    private Long id;
    //点赞的用户id
    private long userid;
    //点赞的视频id
    private long videoid;

    public Videoupvote(){}

    public Videoupvote(long userid,long videoid){
        this.userid = userid;
        this.videoid = videoid;
    }

    @Override
    public String toString() {
        return "VideoUpvote{" +
                "id=" + id +
                ", userid=" + userid +
                ", videoid=" + videoid +
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
}
