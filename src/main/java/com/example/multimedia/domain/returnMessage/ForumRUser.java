package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.ForumRelay;
import com.example.multimedia.domain.MulUser;

//论坛文章回复 + 用户
public class ForumRUser {
    private ForumRelay forumRelay;
    private MulUser mulUser;

    public ForumRUser(){}
    public ForumRUser(ForumRelay forumRelay,MulUser mulUser){
        this.forumRelay = forumRelay;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "ForumRUser{" +
                "forumRelay=" + forumRelay +
                ", mulUser=" + mulUser +
                '}';
    }

    public ForumRelay getForumRelay() {
        return forumRelay;
    }

    public void setForumRelay(ForumRelay forumRelay) {
        this.forumRelay = forumRelay;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
