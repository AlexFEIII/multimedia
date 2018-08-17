package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//保存收藏的文章专题
@Entity
public class CollectDKind {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private long userid;
    //收藏的文章类别
    private String kind;

    public CollectDKind(){}
    public CollectDKind(long userid,String kind){
        this.userid = userid;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "CollectDKind{" +
                "id=" + id +
                ", userid=" + userid +
                ", kind='" + kind + '\'' +
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
