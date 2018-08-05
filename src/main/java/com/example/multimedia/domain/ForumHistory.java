package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ForumHistory {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private long userid;
    //问答id
    private long forumid;
    //时间
    private Date date;

    public ForumHistory(){}
    public ForumHistory(long userid,long forumid){
        this.userid = userid;
        this.forumid = forumid;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "ForumHistory{" +
                "id=" + id +
                ", userid=" + userid +
                ", forumid=" + forumid +
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
