package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.MulUser;

public class ForumUser {
    private Forum forum;
    private MulUser mulUser;
    private boolean isFollow;
    private long kindnum;
    private int colnum;

    public ForumUser(){}
    public ForumUser(Forum forum,MulUser mulUser,boolean isFollow,long kindnum,int colnum){
        this.forum = forum;
        this.mulUser = mulUser;
        this.isFollow = isFollow;
        this.kindnum = kindnum;
        this.colnum = colnum;
    }

    @Override
    public String toString() {
        return "ForumUser{" +
                "forum=" + forum +
                ", mulUser=" + mulUser +
                ", isFollow=" + isFollow +
                ", kindnum=" + kindnum +
                ", colnum=" + colnum +
                '}';
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public long getKindnum() {
        return kindnum;
    }

    public void setKindnum(long kindnum) {
        this.kindnum = kindnum;
    }

    public int getColnum() {
        return colnum;
    }

    public void setColnum(int colnum) {
        this.colnum = colnum;
    }
}
