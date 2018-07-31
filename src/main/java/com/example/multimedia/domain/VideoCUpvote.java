package com.example.multimedia.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
* 视频评论点赞
* */
@Entity
public class VideoCUpvote {
    @Id
    @GeneratedValue
    private Long id;
    //点赞的用户id
    private long userid;
    //点赞的视频编号
    private long commentid;

    public VideoCUpvote(){}

    public VideoCUpvote(long userid,long commentid){
        this.userid = userid;
        this.commentid = commentid;
    }

    @Override
    public String toString() {
        return "VideoCUpvote{" +
                "id=" + id +
                ", userid=" + userid +
                ", commentid=" + commentid +
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

    public long getCommentid() {
        return commentid;
    }

    public void setCommentid(long commentid) {
        this.commentid = commentid;
    }
}
