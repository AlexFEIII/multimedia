package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.Forum;
import com.example.multimedia.domain.ForumComment;
import com.example.multimedia.domain.MulUser;

//论坛文章评论 + 用户
public class ForumCUser {
    private ForumComment forumComment;
    private MulUser mulUser;

    public ForumCUser(){}
    public ForumCUser(ForumComment forumComment,MulUser mulUser){
        this.forumComment = forumComment;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "ForumCUser{" +
                "forumComment=" + forumComment +
                ", mulUser=" + mulUser +
                '}';
    }

    public ForumComment getForumComment() {
        return forumComment;
    }

    public void setForumComment(ForumComment forumComment) {
        this.forumComment = forumComment;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
