package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class FCView {
    private int totalPage;
    private boolean isUp;
    private boolean isFollow;
    private ForumCUser forumCUser;
    private List<ForumRUser> forumRUsers;

    public FCView(){}
    public FCView(int totalPage,ForumCUser forumCUser, List<ForumRUser> forumRUsers,boolean isUp,boolean isFollow){
        this.totalPage = totalPage;
        this.forumCUser = forumCUser;
        this.forumRUsers = forumRUsers;
        this.isUp = isUp;
        this.isFollow = isFollow;
    }

    @Override
    public String toString() {
        return "FCView{" +
                "totalPage=" + totalPage +
                ", isUp=" + isUp +
                ", isFollow=" + isFollow +
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

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
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
