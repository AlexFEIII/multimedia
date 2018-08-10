package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CollectVideo {
    @Id
    @GeneratedValue
    private Long id;
    private long videoid;
    private long userid;

    public CollectVideo(){}
    public CollectVideo(long userid,long videoid){
        this.userid = userid;
        this.videoid = videoid;
    }

    @Override
    public String toString() {
        return "CollectVideo{" +
                "id=" + id +
                ", videoid=" + videoid +
                ", userid=" + userid +
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

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}
