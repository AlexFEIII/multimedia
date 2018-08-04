package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DocHistory {
    @Id
    @GeneratedValue
    private long id;
    //用户id
    private long userid;
    //文章id
    private long docid;
    //浏览时间
    private Date date;

    @Override
    public String toString() {
        return "DocHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", docid=" + docid +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
