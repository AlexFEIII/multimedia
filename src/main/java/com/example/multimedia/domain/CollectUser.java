package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CollectUser {
    @Id
    @GeneratedValue
    private Long id;
    //用户id
    private long userid;
    //收藏的用户id
    private long cuserid;

    public CollectUser(){}
    public CollectUser(long userid,long cuserid){
        this.userid = userid;
        this.cuserid = cuserid;
    }

    @Override
    public String toString() {
        return "CollectUser{" +
                "id=" + id +
                ", userid=" + userid +
                ", cuserid=" + cuserid +
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

    public long getCuserid() {
        return cuserid;
    }

    public void setCuserid(long cuserid) {
        this.cuserid = cuserid;
    }
}
