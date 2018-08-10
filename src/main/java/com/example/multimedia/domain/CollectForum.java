package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CollectForum {
    @Id
    @GeneratedValue
    private Long id;
    private long userid;
    private long forumid;

    public CollectForum(){}
    public CollectForum(long userid,long forumid){
        this.userid = userid;
        this.forumid = forumid;
    }

    @Override
    public String toString() {
        return "CollectForum{" +
                "id=" + id +
                ", userid=" + userid +
                ", forumid=" + forumid +
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

    public long getForumid() {
        return forumid;
    }

    public void setForumid(long forumid) {
        this.forumid = forumid;
    }
}
