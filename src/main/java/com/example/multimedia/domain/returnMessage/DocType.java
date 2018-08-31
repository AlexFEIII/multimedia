package com.example.multimedia.domain.returnMessage;

import org.springframework.data.domain.Page;

import java.util.List;

public class DocType {
    //关注该类型文章的人数
    private long colnum;
    //该类型文章的数目
    private long docnum;
    //总页数
    private int totalPage;
    //用户是否关注该类型
    private boolean isCol;
    //文章列表
    private List<DocUserView> docUserViews;

    public DocType(){}
    public DocType(long colnum,long docnum,int totalPage,boolean isCol,List<DocUserView> docUserViews){
        this.colnum = colnum;
        this.docnum = docnum;
        this.totalPage = totalPage;
        this.isCol = isCol;
        this.docUserViews = docUserViews;
    }

    @Override
    public String toString() {
        return "DocType{" +
                "colnum=" + colnum +
                ", docnum=" + docnum +
                ", totalPage=" + totalPage +
                ", isCol=" + isCol +
                ", docUserViews=" + docUserViews +
                '}';
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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isCol() {
        return isCol;
    }

    public void setCol(boolean col) {
        isCol = col;
    }

    public List<DocUserView> getDocUserViews() {
        return docUserViews;
    }

    public void setDocUserViews(List<DocUserView> docUserViews) {
        this.docUserViews = docUserViews;
    }
}
