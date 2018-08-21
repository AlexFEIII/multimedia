package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.ForumRelay;
import com.example.multimedia.domain.MulUser;

//论坛文章回复 + 用户
public class ForumRUser {
    private ForumRelay forumRelay;
    private String nickname;
    private long id;
    private String rname;
    private long rid;

    public ForumRUser(){}
    public ForumRUser(ForumRelay forumRelay,String nickname,long id ,String rname,long rid){
        this.forumRelay = forumRelay;
        this.nickname = nickname;
        this.id = id;
        this.rname = rname;
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "ForumRUser{" +
                "forumRelay=" + forumRelay +
                ", nickname='" + nickname + '\'' +
                ", id=" + id +
                ", rname='" + rname + '\'' +
                ", rid=" + rid +
                '}';
    }

    public ForumRelay getForumRelay() {
        return forumRelay;
    }

    public void setForumRelay(ForumRelay forumRelay) {
        this.forumRelay = forumRelay;
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

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }
}
