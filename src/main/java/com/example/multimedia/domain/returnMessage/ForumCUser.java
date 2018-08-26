package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.ForumProblem;
import com.example.multimedia.domain.MulUser;

//论坛文章评论 + 用户
public class ForumCUser {
    private ForumProblem forumProblem;
    private MulUser mulUser;

    public ForumCUser(){}
    public ForumCUser(ForumProblem forumProblem, MulUser mulUser){
        this.forumProblem = forumProblem;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "ForumCUser{" +
                "forumProblem=" + forumProblem +
                ", mulUser=" + mulUser +
                '}';
    }

    public ForumProblem getForumProblem() {
        return forumProblem;
    }

    public void setForumProblem(ForumProblem forumProblem) {
        this.forumProblem = forumProblem;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
