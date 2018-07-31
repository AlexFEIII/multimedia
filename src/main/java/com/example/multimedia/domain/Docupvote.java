package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 文章点赞表
* */
@Entity
public class Docupvote {
    @Id
    @GeneratedValue
    private Long id;
    //点赞的用户id
    private long userid;
    //点赞的文章编号
    private long docid;

    public Docupvote(){}

    public Docupvote(long userid,long docid){
        this.userid = userid;
        this.docid = docid;
    }

    @Override
    public String toString() {
        return "DocUpvote{" +
                "id=" + id +
                ", userid=" + userid +
                ", docid=" + docid +
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
}
