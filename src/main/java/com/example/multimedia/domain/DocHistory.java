package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
public class DocHistory {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private long userid;
    //文章id
    private long docid;
    //浏览时间
    private Date date;

    public DocHistory(){}
    public DocHistory(long userid,long docid){
        this.userid = userid;
        this.docid = docid;
        this.date = Date.from(LocalDateTime.now().plusWeeks(2).atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public String toString() {
        return "DocHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", docid=" + docid +
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

    public long getDocid() {
        return docid;
    }

    public void setDocid(long docid) {
        this.docid = docid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
