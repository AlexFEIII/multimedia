package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.MulUser;

public class ForumUser {
    private Forum forum;
    private MulUser mulUser;

    public ForumUser(){}
    public ForumUser(Forum forum,MulUser mulUser){
        this.forum = forum;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "ForumUser{" +
                "forum=" + forum +
                ", mulUser=" + mulUser +
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
}
