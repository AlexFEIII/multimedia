package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class VideoHistory {
    @Id
    @GeneratedValue
    private Long id;
    //用户id;
    private long userid;
    //浏览的视频id
    private long videoid;
    //浏览的时间
    private Date date;

    public VideoHistory(){}
    public VideoHistory(long userid,long videoid){
        this.userid = userid;
        this.videoid = videoid;
        this.date = Date.from(LocalDateTime.now().plusWeeks(2).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "VideoHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", videoid=" + videoid +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
