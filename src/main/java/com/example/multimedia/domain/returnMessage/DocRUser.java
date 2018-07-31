package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.DocRelay;
import com.example.multimedia.domain.MulUser;

//文章回复+用户
public class DocRUser {
    private DocRelay docRelay;
    private MulUser mulUser;

    public DocRUser(){}
    public DocRUser(DocRelay docRelay,MulUser mulUser){
        this.docRelay = docRelay;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "DocRUser{" +
                "docRelay=" + docRelay +
                ", mulUser=" + mulUser +
                '}';
    }

    public DocRelay getDocRelay() {
        return docRelay;
    }

    public void setDocRelay(DocRelay docRelay) {
        this.docRelay = docRelay;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
