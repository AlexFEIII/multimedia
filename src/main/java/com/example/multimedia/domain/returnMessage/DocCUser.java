package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.DocComment;
import com.example.multimedia.domain.MulUser;

//文章评论 + 用户名，头像
public class DocCUser {
    private DocComment docComment;
    private MulUser mulUser;

    public DocCUser(){}
    public DocCUser(DocComment docComment,MulUser mulUser){
        this.docComment = docComment;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "DocCUser{" +
                "docComment=" + docComment +
                ", mulUser=" + mulUser +
                '}';
    }

    public DocComment getDocComment() {
        return docComment;
    }

    public void setDocComment(DocComment docComment) {
        this.docComment = docComment;
    }

    public MulUser getMulUser() {
        return mulUser;
    }

    public void setMulUser(MulUser mulUser) {
        this.mulUser = mulUser;
    }
}
