package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 论坛文章点赞
* */
@Entity
public class Forumupvote {
    @Id
    @GeneratedValue
    private Long id;
    //点赞的用户id
    private long userid;
    //点赞的文章编号
    private long forumid;

    public Forumupvote(){}

    public Forumupvote(long userid,long forumid){
        this.userid = userid;
        this.forumid = forumid;
    }

    @Override
    public String toString() {
        return "ForumUpvote{" +
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
