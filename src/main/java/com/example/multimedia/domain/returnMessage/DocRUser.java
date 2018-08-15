package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.DocRelay;
import com.example.multimedia.domain.MulUser;

//文章回复+用户
public class DocRUser {
    private DocRelay docRelay;
    private String nickname;
    private long id;
    private String rname;
    private long rid;

    public DocRUser(){}
    public DocRUser(DocRelay docRelay,String nickname,long id,String rname,long rid){
        this.docRelay = docRelay;
        this.nickname = nickname;
        this.id = id;
        this.rname = rname;
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "DocRUser{" +
                "docRelay=" + docRelay +
                ", nickname='" + nickname + '\'' +
                ", id=" + id +
                ", rname='" + rname + '\'' +
                ", rid=" + rid +
                '}';
    }

    public DocRelay getDocRelay() {
        return docRelay;
    }

    public void setDocRelay(DocRelay docRelay) {
        this.docRelay = docRelay;
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
