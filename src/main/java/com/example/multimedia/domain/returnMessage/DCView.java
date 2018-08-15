package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class DCView {
    private int totalPage;
    private long totalNum;
    private boolean isUp;
    private DocCUser docCUser;
    private List<DocRUser> docRUsers;

    public DCView(){}
    public DCView(int totalPage,long totalNum,boolean isUp,DocCUser docCUser,List<DocRUser> docRUsers){
        this.totalPage = totalPage;
        this.totalNum = totalNum;
        this.isUp = isUp;
        this.docCUser = docCUser;
        this.docRUsers = docRUsers;
    }

    @Override
    public String toString() {
        return "DCView{" +
                "totalPage=" + totalPage +
                ", totalNum=" + totalNum +
                ", isUp=" + isUp +
                ", docCUser=" + docCUser +
                ", docRUsers=" + docRUsers +
                '}';
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    public DocCUser getDocCUser() {
        return docCUser;
    }

    public void setDocCUser(DocCUser docCUser) {
        this.docCUser = docCUser;
    }

    public List<DocRUser> getDocRUsers() {
        return docRUsers;
    }

    public void setDocRUsers(List<DocRUser> docRUsers) {
        this.docRUsers = docRUsers;
    }
}
