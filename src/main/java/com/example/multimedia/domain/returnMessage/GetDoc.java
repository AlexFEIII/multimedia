package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.MulUser;

public class GetDoc {
    private Document document;
    private MulUser mulUser;
    private boolean isfollow;
    private boolean isupvote;
    private boolean iscollect;

    public GetDoc(){}
    public GetDoc(Document document,MulUser mulUser,boolean isfollow,boolean isupvote,boolean iscollect){
        this.document = document;
        this.mulUser = mulUser;
        this.isfollow = isfollow;
        this.isupvote = isupvote;
        this.iscollect = iscollect;
    }

    @Override
    public String toString() {
        return "GetDoc{" +
                "document=" + document +
                ", mulUser=" + mulUser +
                ", isfollow=" + isfollow +
                ", isupvote=" + isupvote +
                ", iscollect=" + iscollect +
                '}';
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }

    public boolean isIsfollow() {
        return isfollow;
    }

    public void setIsfollow(boolean isfollow) {
        this.isfollow = isfollow;
    }

    public boolean isIsupvote() {
        return isupvote;
    }

    public void setIsupvote(boolean isupvote) {
        this.isupvote = isupvote;
    }

    public boolean isIscollect() {
        return iscollect;
    }

    public void setIscollect(boolean iscollect) {
        this.iscollect = iscollect;
    }
}
