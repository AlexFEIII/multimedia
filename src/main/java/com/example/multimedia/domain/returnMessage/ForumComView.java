package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.ForumComment;

public class ForumComView {
    private String nickname;
    private long id;
    private String ruser;
    private long rid;
    private int totalPage;
    private long count;
    private ForumComment forumComment;

    public ForumComView(){}
    public ForumComView(String nickname,long id,String ruser,long rid,ForumComment forumComment,int totalPage,long count){
        this.nickname = nickname;
        this.id = id;
        this.ruser = ruser;
        this.rid = rid;
        this.forumComment = forumComment;
        this.totalPage = totalPage;
        this.count = count;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRuser() {
        return ruser;
    }

    public void setRuser(String ruser) {
        this.ruser = ruser;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public ForumComment getForumComment() {
        return forumComment;
    }

    public void setForumComment(ForumComment forumComment) {
        this.forumComment = forumComment;
    }
}
