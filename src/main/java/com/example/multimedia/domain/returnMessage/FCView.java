package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class FCView {
    private int totalPage;
    private ForumCUser forumCUser;
    private List<ForumRUser> forumRUsers;

    public FCView(){}
    public FCView(int totalPage,ForumCUser forumCUser, List<ForumRUser> forumRUsers){
        this.totalPage = totalPage;
        this.forumCUser = forumCUser;
        this.forumRUsers = forumRUsers;
    }

    @Override
    public String toString() {
        return "FCView{" +
                "totalPage=" + totalPage +
                ", forumCUser=" + forumCUser +
                ", forumRUsers=" + forumRUsers +
                '}';
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ForumCUser getForumCUser() {
        return forumCUser;
    }

    public void setForumCUser(ForumCUser forumCUser) {
        this.forumCUser = forumCUser;
    }

    public List<ForumRUser> getForumRUsers() {
        return forumRUsers;
    }

    public void setForumRUsers(List<ForumRUser> forumRUsers) {
        this.forumRUsers = forumRUsers;
    }
}
