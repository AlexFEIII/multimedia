package com.example.multimedia.domain.returnMessage;

import com.example.multimedia.domain.Document;
import com.example.multimedia.domain.MulUser;

public class DocUserView {
    private Document document;
    private MulUser mulUser;

    public DocUserView(){}
    public DocUserView(Document document,MulUser mulUser){
        this.document = document;
        this.mulUser = mulUser;
    }

    @Override
    public String toString() {
        return "DocUserView{" +
                "document=" + document +
                ", mulUser=" + mulUser +
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
}
