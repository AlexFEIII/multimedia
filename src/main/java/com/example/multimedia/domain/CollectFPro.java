package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//用户收藏问答 问题表
@Entity
public class CollectFPro {
    @Id
    @GeneratedValue
    private Long id;
    private long forumid;
    private long commentid;
    private long userid;

    public CollectFPro(){}
    public CollectFPro(long forumid, long commentid, long userid){
        this.forumid = forumid;
        this.commentid = commentid;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "CollectFPro{" +
                "id=" + id +
                ", forumid=" + forumid +
                ", commentid=" + commentid +
                ", userid=" + userid +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getForumid() {
        return forumid;
    }

    public void setForumid(long forumid) {
        this.forumid = forumid;
    }

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }
}
