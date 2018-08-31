package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.ForumProblem;

public class ForumProblemView {
    private long colnum;
    private long docnum;
    private boolean col;
    private ForumProblem forumProblem;
    private DocType docType;

    public ForumProblemView(){}
    public ForumProblemView(long colnum,long docnum,ForumProblem forumProblem,DocType docType,boolean col){
        this.colnum = colnum;
        this.docnum = docnum;
        this.forumProblem = forumProblem;
        this.docType = docType;
        this.col = col;
    }

    public long getColnum() {
        return colnum;
    }

    public void setColnum(long colnum) {
        this.colnum = colnum;
    }

    public long getDocnum() {
        return docnum;
    }

    public void setDocnum(long docnum) {
        this.docnum = docnum;
    }

    public ForumProblem getForumProblem() {
        return forumProblem;
    }

    public void setForumProblem(ForumProblem forumProblem) {
        this.forumProblem = forumProblem;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public boolean isCol() {
        return col;
    }

    public void setCol(boolean col) {
        this.col = col;
    }
}
