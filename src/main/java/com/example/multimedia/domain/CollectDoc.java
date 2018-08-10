package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CollectDoc {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private long userid;
    //文章id
    private long docid;

    public CollectDoc(){}
    public CollectDoc(long userid,long docid){
        this.userid = userid;
        this.docid = docid;
    }

    @Override
    public String toString() {
        return "CollectDoc{" +
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
