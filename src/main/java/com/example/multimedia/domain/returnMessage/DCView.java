package com.example.multimedia.domain.returnMessage;

import java.util.List;
import java.util.Map;

public class DCView {
    private int totalPage;
    private Map<DocCUser, List<DocRUser>> maps;

    public DCView(){}
    public DCView(int totalPage,Map<DocCUser,List<DocRUser>> maps){
        this.totalPage = totalPage;
        this.maps = maps;
    }

    @Override
    public String toString() {
        return "DCView{" +
                "totalPage=" + totalPage +
                ", maps=" + maps +
                '}';
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Map<DocCUser, List<DocRUser>> getMaps() {
        return maps;
    }

    public void setMaps(Map<DocCUser, List<DocRUser>> maps) {
        this.maps = maps;
    }
}
